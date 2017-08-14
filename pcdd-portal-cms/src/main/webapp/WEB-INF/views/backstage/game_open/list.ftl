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
    
    
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/gameOpenLog/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/gameOpenLog/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/gameOpenLog/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       <!--详情-->
       <#include "/views/backstage/game_open/detail.ftl" />
        <!--新增-->
       <#include "/views/backstage/game_open/form.ftl" />
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">开奖记录</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">游戏记录</a> <span class="divider">/</span></li>
	            <li class="active">开奖记录</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  		游戏类型:
		  		<select  class="form-control" data-bind="value:model.search.gameType,options:model.gameTypeArray,optionsText:'name',optionsValue:'id'" style="width:90px;">
		  		</select>
		  		&nbsp;&nbsp;&nbsp;&nbsp;
		  		是否派奖:
		  		<select  class="form-control" data-bind="value:model.search.isGive" style="width:90px;">
		  			<option value="">所有</option>
		  			<option value="1">是</option>
		  			<option value="0">否</option>
		  		</select>
		  		&nbsp;&nbsp;&nbsp;&nbsp;
		  		开奖时间:
		  		 <input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.openTimeBegin" onclick="laydate()">
		  		 	至
			  	 <input type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.openTimeEnd" onclick="laydate()">
			  	 &nbsp;&nbsp;&nbsp;&nbsp;
			  	 <!--<input type="text" class="form-control"  placeholder="搜索昵称" data-bind="textinput:model.search.nickName">-->
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em;" >
				<button class="btn btn-primary btn-sm" data-toggle="modal" data-bind="click:$root.showForm.bind($root,null)"
                            style="margin: .8em;border-radius: 0; " >
                           <span class="glyphicon glyphicon-plus" style="margin-right:5px;"></span>新增
                </button>		
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th width="10%">游戏类型</th>
								<th width="8%">期数</th>
								<th width="8%">是否派奖</th>
								<th width="16%">开奖号码</th>
								<th width="16%">开奖类型</th>
								<th width="8%">总下注金额</th>
								<th width="11%">盈亏金额</th>
								<th width="12%">开奖时间</th>
								<th width="14%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: $root.fmt.gameType(item.gameType)" ></td>
								<td data-bind="text: item.gameNum" ></td>
								<td data-bind="text: $root.fmt.isGive(item.isGive)" ></td>
								<td data-bind="text: item.gameResultDesc" ></td>
								<td data-bind="text: item.resultType" ></td>
								<td data-bind="text: $root.fmt.currency(item.totalPoint,{places: 2,symbol: ''})" ></td>
								<td data-bind="html: $root.fmt.xhibitPoint(item.xhibitPoint)" ></td>
								<td data-bind="text: $root.fmt.date(item.openTime,'yyyy-MM-dd hh:mm')" ></td>
								<td>
									
									<!-- ko if: item.source==2 -->
									  <a href="javascritp:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-pencil"></span>编辑</a>
									  &nbsp;&nbsp;
									<!-- /ko -->
									
									<a href="javascritp:void(0);" data-bind="click:$root.showDetail.bind($root, item)"><span class="glyphicon glyphicon-eye-open"></span>详情</a> 
									
									<!-- ko if: item.isGive==0 -->
									  &nbsp;&nbsp;
									  <a href="javascritp:void(0);" data-bind="click:$root.showpop.bind($root,'delete','您确定要手动开奖?',$root.openGame.bind($root,item.id),1,'手动开奖','success')"><span class="glyphicon glyphicon-saved"></span>开奖</a> 
									<!-- /ko -->
									
									
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
<script type="text/javascript" src="<@full_path path='js/backstage/game_open/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/game_open/list.js'/>" ></script>	
  </body>
</html>
