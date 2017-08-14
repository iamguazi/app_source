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
        .win {
            color: green;
            font-size: 18px;
            font-weight: bold;
        }
        
        .lose {
            color: red;
            font-size: 18px;
            font-weight: bold;
        }
    </style>
    
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/userChoiceLog/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/userChoiceLog/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/userChoiceLog/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       <!--详情-->
       <#include "/views/backstage/user_choice/form.ftl" />
       <!--充值-->
       <#include "/views/backstage/user_choice/recharge.ftl" />
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">下注记录</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">游戏记录</a> <span class="divider">/</span></li>
	            <li class="active">下注记录</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  		游戏类型
                <select   style="width: 90px;margin-top:10px;" class="form-control" data-bind="value:model.search.gameType,options:model.gameTypeArray,optionsText:'name',optionsValue:'id',event:{change:getSearchAreaTypeByGameType}">
                </select>
                &nbsp;&nbsp;
                                    房间类型
                <select style="width: 90px;margin-top:10px;" class="form-control" data-bind="value:model.search.areaId,options:model.searchAreaTypes,optionsText:'areaName',optionsValue:'id',event:{change:getRoomInfos,focus:$root.areaTypeFocus.bind($root,1)}">
                    
                </select>
                &nbsp;
                房间名称:
                <select style="width: 90px;margin-top:10px;" class="form-control" data-bind="value:model.search.roomId,options:model.roomInfos,optionsText:'roomName',optionsValue:'id',event:{focus:$root.roomInfosFocus.bind($root,1)}">
                </select>
		  		&nbsp;
		  		状态:
		  		<select style="width: 90px;margin-top:10px;" class="form-control" data-bind="value:model.search.status" style="width:90px;">
		  			<option value="">所有</option>
		  			<option value="1">正常</option>
		  			<option value="0">拉黑</option>
		  		</select>
		  		&nbsp;&nbsp;
		  		下注时间:
		  		 <input style="width: 110px;margin-top:10px;" type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.createTimeBegin" onclick="laydate()">
		  		 	至
			  	 <input style="width: 110px;margin-top:10px;" type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.createTimeEnd" onclick="laydate()">
			  	 &nbsp;&nbsp;
			  	 <input style ="width: 120px;margin-top:10px;" type="text" class="form-control"  placeholder="搜索用户ID" data-bind="textinput:model.search.userId">
			  	 &nbsp;&nbsp;
			  	 <input style="width: 120px;margin-top:10px;" type="text" class="form-control"  placeholder="搜索用户账号" data-bind="textinput:model.search.account">
			  	 &nbsp;&nbsp;
			  	 <input style ="width: 120px;margin-top:10px;" type="text" class="form-control"  placeholder="搜索昵称" data-bind="textinput:model.search.nickName">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em;position: relative;" >
			  <div class="col-sm-6">
						<!-- 按钮批量通过按钮 -->
						<!--<button class="btn btn-success btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:false,visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要通过选中的提现记录？',$root.updateStatus.bind($root,1),0,'通过','success') " >
						   <span class="glyphicon glyphicon-ok-circle" style="margin-right:.8em;"></span>批量通过
						</button>-->
						
						<!-- 按钮批量拒绝按钮 -->
						<!--<button class="btn btn-warning btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:false,visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要拒绝选中的提现记录？',$root.updateStatus.bind($root,2),0,'拒绝','warning') " >
						   <span class="glyphicon glyphicon-ban-circle" style="margin-right:.8em;"></span>批量拒绝
						</button>-->
						
						<!--导出-->
                        <button style="margin: 10px;border-radius: 0;" class="btn btn-primary btn-sm"  data-bind="click:exportXls" >
                          <span class="glyphicon glyphicon-open" style="margin-right:.8em;"></span>导出Excel
                        </button>
				</div>
				  <div style="position: absolute;right: 25px;top: 50%;transform: translateY(-50%);-webkit-transform: translateY(-50%);-moz-transform: translateY(-50%);-ms-transform: translateY(-50%);">
				    总亏赢:
                          <span id="totalXhibitPoint">
                          </span>
				  </div>  
				    
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th width="4.8%">用户ID</th>
								<th width="6.8%">账号</th>
								<th width="6.8%">昵称</th>
								<th width="6.8%">头像</th>
								<th width="6.8%">游戏类型</th>
								<th width="6.8%">房间类型</th>
								<th width="6.8%">房间名称</th>
								<th width="7.8%">下注金额</th>
								<th width="6.8%">下注类型</th>
								<th width="6.8%">开奖结果</th>
								<th width="4%">期数</th>
								<th width="8.8%">盈亏金额</th>
								<th width="9%">下注时间</th>
								<th width="12%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: item.userId"></td>
								<td data-bind="text: item.account"></td>
								<td data-bind="text: item.nickName" ></td>
								<td data-bind="html: $root.fmt.img(item.userPhoto),click:$root.fmt.biggerShowImg.bind($root,$root.fmt.img4Null(item.userPhoto,defaultUserIcon))"></td>
								<td data-bind="text: $root.fmt.gameType(item.gameType)" ></td>
								<td data-bind="text: item.areaName" ></td>
								<td data-bind="text: item.roomName" ></td>
								<td data-bind="text: $root.fmt.currency(item.point,{places: 2,symbol: ''})" ></td>
								<td data-bind="text: item.choiceName" ></td>
								<td data-bind="text: $root.fmt.result(item)" ></td>
								<td data-bind="text: item.choiceNo" ></td>
								<td data-bind="html: $root.fmt.xhibitPoint(item.xhibitPoint)" ></td>
								<td data-bind="text: $root.fmt.date(item.createTime,'yyyy-MM-dd hh:mm')" ></td>
								<td>
									<a href="javascritp:void(0);" style="color:#4cae4c;" data-bind="click:$root.showpop.bind($root,'delete','您确定要恢复该用户？',$root.updateStatus.bind($root,1,item.userId),1,'恢复','success'),visible:item.status==0"><span class="glyphicon glyphicon-ok-circle"></span>恢复</a>
									<a href="javascritp:void(0);" style="color:#f0ad4e;" data-bind="click:$root.showpop.bind($root,'delete','您确定要拉黑该用户？',$root.updateStatus.bind($root,0,item.userId),1,'拉黑','warning'),visible:item.status==1"><span class="glyphicon glyphicon-ban-circle"></span>拉黑</a>
									<a href="javascritp:void(0);" style="color:#58B75B;" data-bind="click:$root.showRechargeForm.bind($root, item)"><span class="glyphicon glyphicon-credit-card"></span>充值</a> 
									<a href="javascritp:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-eye-open"></span>详情</a> 
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
<script type="text/javascript" src="<@full_path path='js/backstage/user_choice/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/user_choice/list.js'/>" ></script>	
  </body>
</html>
