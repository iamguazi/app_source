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
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/versionInfo/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/versionInfo/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/versionInfo/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
       	<!--新增或修改-->	
		<#include "/views/backstage/version/form.ftl"/>			
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">版本管理</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">系统管理</a> <span class="divider">/</span></li>
	            <li class="active">版本管理</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<!--<div class="pull-right" >
			  	 <input type="text" class="form-control"  placeholder="标题名称" data-bind="textinput:model.search.title">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
			</div>-->
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						
						<!-- 按钮触发新增框 -->
						<!--<button class="btn btn-primary btn-sm" data-toggle="modal" data-bind=" click:$root.showForm.bind($root)"
						    style="margin: .8em;border-radius: 0; " >
						   <span class="glyphicon glyphicon-plus" style="margin-right:.8em;"></span>添加等级
						</button>-->
						<!-- 按钮触发删除框 -->
						<!--<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的等级记录吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-trash" style="margin-right:.8em;"></span>批量删除
						</button>-->
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover" style="table-layout: fixed;white-space: nowrap;text-overflow: ellipsis;overflow: hidden;">
						<thead>
							<tr>
								<th width="5%">ID</th>
								<th width="10%">版本号</th>
								<th width="25%">更新内容</th>
								<th width="30%">下载地址</th>
								<th width="10%">更新类型</th>
								<th width="10%">系统类型</th>
								<th width="10%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: item.id,click: $root.showForm.bind($root, item)"></td>
								<td data-bind="text: item.versionNo,click: $root.showForm.bind($root, item),attr:{title:item.versionNo}"></td>
								<td data-bind="text: item.updateContent,click: $root.showForm.bind($root, item),attr:{title:item.updateContent}"></td>
								<td data-bind="text: item.versionUrl,click: $root.showForm.bind($root, item),attr:{title:item.versionUrl}"></td>
								<td data-bind="text: $root.fmt.status(item.status),click: $root.showForm.bind($root, item)"></td>
								<td data-bind="text: $root.fmt.client(item.client),click: $root.showForm.bind($root, item)"></td>
								<td >
								    <a href="javascritp:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-pencil"></span>编辑</a> 
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
<script type="text/javascript" src="<@full_path path='js/backstage/version/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/version/list.js'/>" ></script>	
  </body>
</html>
