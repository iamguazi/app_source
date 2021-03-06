﻿<!DOCTYPE html>
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
    
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/userGiveLog/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/userGiveLog/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/userGiveLog/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
       	<!--新增或修改-->	
		<#--<#include "/views/backstage/paylog/form.ftl"/>-->			
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">消费记录</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">资金管理</a> <span class="divider">/</span></li>
	            <li class="active">消费记录</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  		赠送日期
		  		 <input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.createTimeBegin" onclick="laydate()">
		  		 	至
			  	 <input type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.createTimeEnd" onclick="laydate()">
			  	 &nbsp;&nbsp;&nbsp;&nbsp;
			  	 <input type="text" class="form-control"  placeholder="搜索用户" data-bind="textinput:model.search.userNickName">
			  	 <input type="text" class="form-control"  placeholder="搜索主播" data-bind="textinput:model.search.hostNickName">
			  	 <input type="text" class="form-control"  placeholder="搜索礼物" data-bind="textinput:model.search.giftName">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						<!-- 按钮触发删除框 -->
						<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的充值记录吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-remove" style="margin-right:.8em;"></span>删除
						</button>
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th width="8%">ID</th>
								<th width="8%">收支类型</th>
								<th width="12%">用户</th>
								<th width="12%">主播</th>
								<th width="12%">礼物名称</th>
								<th width="10%">礼物数量</th>
								<th width="9%">消耗钻石</th>
								<th width="9%">直播id</th>
								<th width="15%">赠送时间</th>
								<th width="5%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: item.id"></td>
								<td>支出</td>
								<td data-bind="text: item.userNickName"></td>
								<td data-bind="text: item.hostNickName"></td>
								<td data-bind="text: item.giftName"></td>
								<td data-bind="text: item.count"></td>
								<td data-bind="text: $root.fmt.diamond(item.allFee)" ></td>
								<td data-bind="text: item.roomId" ></td>
								<td data-bind="text: $root.fmt.date(item.createTime,'yyyy-MM-dd hh:mm:ss')" ></td>
								<td>
									
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
<script type="text/javascript" src="<@full_path path='js/backstage/givelog/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/givelog/list.js'/>" ></script>	
  </body>
</html>
