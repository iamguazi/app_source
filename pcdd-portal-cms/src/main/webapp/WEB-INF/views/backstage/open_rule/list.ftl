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
	
	<style>
	   .conterFlag {
	     line-height: 34px;
         height: 34px;
         font-size: 34px;
	   }
	</style>
    
    
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/openRule/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/openRule/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/openRule/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
       	<!--新增或修改-->	
		<#include "/views/backstage/open_rule/form.ftl"/>			
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">13,14开奖规则</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">游戏记录</a> <span class="divider">/</span></li>
	            <li class="active">13,14开奖规则</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  		游戏类型
		  		<select  class="form-control" data-bind="value:model.search.gameType,options:model.gameTypeArray,optionsText:'name',optionsValue:'id',event:{change:getSearchAreaTypeByGameType}">
		  		</select>
		  		&nbsp;&nbsp;
		  		房间类型
		  		<select class="form-control" data-bind="value:model.search.areaId,options:model.searchAreaTypes,optionsText:'areaName',optionsValue:'id',event:{focus:$root.areaTypeFocus.bind($root,1)}">
		  			
		  		</select>
			  	 &nbsp;&nbsp;&nbsp;&nbsp;
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						<!-- 按钮触发新增框 -->
						<!--
						<button class="btn btn-primary btn-sm" data-toggle="modal" data-bind="click:$root.showForm.bind($root)"
						    style="margin: .8em;border-radius: 0; " >
						   <span class="glyphicon glyphicon-plus" style="margin-right:5px;"></span>新增房间
						</button>
						-->
						
						<!-- 按钮触发删除框 -->
						<!--
						<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的广告吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-trash" style="margin-right:5px;"></span>删除
						</button>
						-->
						
						<!-- 刷新按钮 -->
						<!--<button class="btn btn-success btn-sm pull-right" 
						   style="margin: .8em;border-radius: 0;margin-right: 15px;"  
						   onclick = "location.reload(true)" >
						   <span class="glyphicon glyphicon-repeat" style="margin-right:5px;"></span>刷新
						</button>-->
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th width="6%">游戏类型</th>
								<th width="6%">房间类型</th>
								<th width="6%">下注类型</th>
								<th width="14%">下注范围1</th>
								<th width="5%">赔率1</th>
								<th width="14%">下注范围2</th>
								<th width="5%">赔率2</th>
								<th width="14%">下注范围3</th>
								<th width="5%">赔率3</th>
								<th width="14%">下注范围4</th>
								<th width="5%">赔率4</th>
								<th width="7%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: $root.fmt.gameType(item.gameType)"></td>
								<td data-bind="text: $root.fmt.areaName(item.areaId)" ></td>
								<td data-bind="text: item.typeName"></td>
								<td data-bind="text: $root.fmt.scope(item.start1,item.end1)" ></td>
								<td data-bind="text: $root.fmt.currency(item.bili1,{places: 2,symbol: '',thousand: ''})" ></td>
								<td data-bind="text: $root.fmt.scope(item.start2,item.end2)" ></td>
								<td data-bind="text: $root.fmt.currency(item.bili2,{places: 2,symbol: '',thousand: ''})" ></td>
								<td data-bind="text: $root.fmt.scope(item.start3,item.end3)" ></td>
								<td data-bind="text: $root.fmt.currency(item.bili3,{places: 2,symbol: '',thousand: ''})" ></td>
								<td data-bind="text: $root.fmt.scope(item.start4,item.end4)" ></td>
								<td data-bind="text: $root.fmt.currency(item.bili4,{places: 2,symbol: '',thousand: ''})" ></td>
								<td>
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
<#include "/views/backstage/common/common_script.ftl"/>
<script type="text/javascript" src="<@full_path path='js/backstage/open_rule/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/open_rule/list.js'/>" ></script>	
  </body>
</html>
