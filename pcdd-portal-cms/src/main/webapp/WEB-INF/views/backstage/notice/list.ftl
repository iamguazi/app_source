<!DOCTYPE html>
<html>
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>主页</title>

    <!-- Bootstrap -->
    <link href="<@full_path path='js/lib/bootstrap-3.3.5/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<@full_path path='css/main.css'/>" rel="stylesheet">
    <link href="<@full_path path='js/lib/cover/jquery.cover.css'/>" rel="stylesheet">
	
	<#include "/views/backstage/common/common_script.ftl"/>
    
    <style>
        .table.table-bordered.table-hover td {
            white-space: nowrap;
            text-overflow: ellipsis;
            overflow: hidden;
        }
    </style>
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/noticeInfo/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/noticeInfo/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/noticeInfo/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
       	<!--新增或修改-->	
		<#include "/views/backstage/notice/form.ftl"/>			
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">通知管理</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">系统管理</a> <span class="divider">/</span></li>
	            <li class="active">通知管理</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
			  	 <input type="text" class="form-control"  placeholder="请输入标题名称查询" data-bind="textinput:model.search.title">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						
						<!-- 按钮触发新增框 -->
						<button class="btn btn-primary btn-sm" data-toggle="modal" data-bind=" click:$root.showForm.bind($root)"
						    style="margin: .8em;border-radius: 0; " >
						   <span class="glyphicon glyphicon-plus" style="margin-right:.8em;"></span>发布通知
						</button>
						<!-- 按钮触发删除框 -->
						<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的通知记录吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-trash" style="margin-right:.8em;"></span>批量删除
						</button>
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover" style="table-layout: fixed;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
						<thead>
							<tr>
							    <th class="c_align" width="5%" >
                                    <label class="check_box_all " for="checkbox" id="check_all" data-bind="click: $root.clickCheckall.bind($root)">
                                        <input type="checkbox" id="checkbox" class="chk_all" />
                                    </label>
                                </th>
								<th width="10%">ID</th>
								<th width="20%">标题</th>
								<th width="35%">内容</th>
								<th width="15%">创建时间</th>
								<th width="15%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
							    <td class="border_none not_show c_align" >
                                    <label class="check_box"  data-bind="attr: { id: 'chk' + $index(),for: 'checkbox_a' + $index()},click: $root.clickCheckbox.bind($root,$index(),item)">
                                       <input type="checkbox" class="chk_1" data-bind="attr: { id: 'checkbox_a' + $index() }" />
                                    </label>
                                </td>
								<td data-bind="text: item.id,click: $root.showForm.bind($root, item)"></td>
								<td data-bind="text: item.title,click: $root.showForm.bind($root, item),attr:{title:item.title}"></td>
								<td data-bind="text: item.content,click: $root.showForm.bind($root, item),attr:{title:item.content}"></td>
								<td data-bind="text: $root.fmt.date(item.createTime),click: $root.showForm.bind($root, item)"></td>
								<td >
						            <a href="javascritp:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-pencil"></span>编辑</a> 
								    &nbsp; &nbsp; &nbsp;
                                    <a href="javascritp:void(0);" data-bind="click:$root.showpop.bind($root,'delete','您确定要删除该通知吗？',$root.delItem.bind($root,item.id),1)"><span class="glyphicon glyphicon-trash"></span>删除</a>
								</td>
							</tr>
						</tbody>
						<tfoot data-bind="visible: !model.dataList().length" >
							<tr>
								<td colspan="100" class="c_align">暂无数据</td>
							</tr>
						</tfoot>
					</table>
			   </div>
			</div>
			<!--列表数据e-->
			<!--分页-->
		    <#include "/views/backstage/common/page.ftl"/>
</div>
<script type="text/javascript" src="<@full_path path='js/lib/ckeditor/ckeditor.js'/>" ></script>    
<script type="text/javascript" src="<@full_path path='js/backstage/notice/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/notice/list.js'/>" ></script>	
  </body>
</html>
