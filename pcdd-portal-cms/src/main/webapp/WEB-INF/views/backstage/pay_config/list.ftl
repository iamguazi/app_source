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
	
	
    
    
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/payListConfig/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/payListConfig/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/payListConfig/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
       	<!--新增或修改-->	
		<#include "/views/backstage/pay_config/form.ftl"/>			
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">支付配置</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">系统设置</a> <span class="divider">/</span></li>
	            <li class="active">支付配置</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  		支付类型
		  		<select  class="form-control" data-bind="value:model.search.type">
		  		  <option value="">--请选择支付类型--</option>
		  		  <option value="1">线上支付</option>
		  		  <option value="2">线下支付</option>
		  		</select>
		  		&nbsp;&nbsp;
		  		状态
		  		<select class="form-control" data-bind="value:model.search.status">
		  			<option value="">--请选择状态--</option>
                      <option value="1">启用</option>
                      <option value="0">禁用</option>
		  		</select>
		  		&nbsp;&nbsp;
			  	 <input type="text" class="form-control"  placeholder="请输入支付名称" data-bind="textinput:model.search.name">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						<!-- 按钮触发新增框 -->
						<button class="btn btn-primary btn-sm" data-toggle="modal" data-bind="click:$root.showForm.bind($root)"
						    style="margin: .8em;border-radius: 0; " >
						   <span class="glyphicon glyphicon-plus" style="margin-right:5px;"></span>添加
						</button>
						
						<!-- 按钮触发删除框 -->
						<!--<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的广告吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-trash" style="margin-right:5px;"></span>删除
						</button>-->
						
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<!--<th class="c_align" width="5%" >
									<label class="check_box_all " for="checkbox" id="check_all" data-bind="click: $root.clickCheckall.bind($root)">
			    					<input type="checkbox" id="checkbox" class="chk_all" />
			    				</label>
								</th>-->
								<th width="8%">ID</th>
								<th width="15%">支付名称</th>
								<th width="10%">支付类型</th>
								<th width="8%">状态</th>
								<th width="10%">排序</th>
								<th width="27%">备注</th>
								<th width="15%">创建时间</th>
								<th width="7%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<!--<td class="border_none not_show c_align" >
										<label class="check_box"  data-bind="attr: { id: 'chk' + $index(),for: 'checkbox_a' + $index()},click: $root.clickCheckbox.bind($root,$index(),item)">
											<input type="checkbox" class="chk_1" data-bind="attr: { id: 'checkbox_a' + $index() }" />
										</label>
								</td>-->
								<td data-bind="text: item.id,click: $root.showForm.bind($root, item)"></td>
								<td data-bind="text: item.name,click: $root.showForm.bind($root, item)"></td>
								<td data-bind="text: $root.fmt.type(item.type),click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="html: $root.fmt.status(item.status),click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="text: item.payOrder,click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="text: item.remarks,click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="text: $root.fmt.date(item.createTime,'yyyy-MM-dd hh:mm:ss'),click: $root.showForm.bind($root, item)" ></td>
								<td>
									<a href="javascritp:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-pencil"></span>编辑</a> 
									&nbsp; &nbsp; 
									<!--<a href="javascritp:void(0);" data-bind="click:$root.showpop.bind($root,'delete','您确定要启用该房间？',$root.updateStatus.bind($root,1,item.id),1,'启用','success'),visible:item.status==0"><span class="glyphicon glyphicon-ok-circle"></span>启用</a>
									<a href="javascritp:void(0);" data-bind="click:$root.showpop.bind($root,'delete','您确定要拉黑该房间？',$root.updateStatus.bind($root,0,item.id),1,'拉黑','warning'),visible:item.status==1"><span class="glyphicon glyphicon-ban-circle"></span>停用</a>
									&nbsp; &nbsp;-->
									<!--<a href="javascritp:void(0);" data-bind="click:$root.showpop.bind($root,'delete','您确定要删除该房间吗？',$root.delItem.bind($root,item.id),1)"><span class="glyphicon glyphicon-trash"></span>删除</a>-->
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
<#include "/views/backstage/common/common_script.ftl"/>
<script type="text/javascript" src="<@full_path path='js/backstage/pay_config/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/pay_config/list.js'/>" ></script>	
  </body>
</html>
