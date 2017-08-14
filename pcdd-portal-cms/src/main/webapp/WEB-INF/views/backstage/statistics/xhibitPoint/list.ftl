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
	<link href="<@full_path path='js/lib/zTree/css/zTreeStyle/zTreeStyle.css' /> " rel="stylesheet" >
	<!--<link href="<@full_path path='css/tree.css' />" rel="stylesheet" >-->
				 
				 
	<#include "/views/backstage/common/common_script.ftl"/>
    <!-- Include all compiled plugins (below), or include individual files as needed -->
    
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/xhibitPoinFinancialStatistics/?act=QUERY' />"  >
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/xhibitPoinFinancialStatistics/?act=DELETE' />"  >
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/xhibitPoinFinancialStatistics/?act=SAVE' />"  >
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  >
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  >
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl" />
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">亏赢统计</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">系统管理</a> <span class="divider">/</span></li>
	            <li class="active">亏赢统计</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查詢-->
		  	<div class="pull-right" >
		  		<input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="请输入查询日期"  onclick="laydate({max: laydate.now()})">
			  	 
			  	 
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root,null)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
	             
	             <button type="button" class="btn btn-info btn-sm" data-bind="click: $root.searchByCondition.bind($root,0)">
                                            今天
                 </button>
                 
                 <button type="button" class="btn btn-info btn-sm" data-bind="click: $root.searchByCondition.bind($root,1)">
                                            昨天
                 </button>
                 
                 <button type="button" class="btn btn-info btn-sm" data-bind="click: $root.searchByCondition.bind($root,2)">
		                         前天
			     </button>
			   </div>
		    </div>

		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th width="10%">id</th>
								<th width="20%">账号</th>
								<th width="20%">昵称</th>
								<th width="50%" style="cursor: pointer;" data-bind="attr:{sort:model.search.xhibitPointSort()},click: function($root, event){serachSort('xhibitPointSort',event,$root)}">亏盈金额
								<span class="glyphicon glyphicon-arrow-down" data-bind="clickBubble: false,visible:model.search.xhibitPointSort()=='desc'"></span>
                                    <span class="glyphicon glyphicon-arrow-up" data-bind="clickBubble: false,visible:model.search.xhibitPointSort()=='asc'"></span>
								</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: item.id"></td>
								<td data-bind="text: item.account" ></td>
								<td data-bind="text: item.nickName" ></td>
								<td data-bind="html: $root.fmt.xhibitPoint(item.xhibitPoint)" >
								    
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
<script type="text/javascript" src="<@full_path path='js/backstage/statistics/xhibitPoint/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/statistics/xhibitPoint/list.js'/>" ></script>	
  </body>
</html>
