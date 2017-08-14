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
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/userHuiShuiLog/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/userHuiShuiLog/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/userHuiShuiLog/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">回水记录</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">资金管理</a> <span class="divider">/</span></li>
	            <li class="active">回水记录</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  		状态:
		  		<select  class="form-control" data-bind="value:model.search.status" style="width:90px;">
		  			<option value="">所有</option>
		  			<option value="0">待处理</option>
		  			<option value="1">已处理</option>
		  			<option value="2">已拒绝</option>
		  		</select>
		  		申请时间:
		  		 <input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.createTimeBegin" onclick="laydate()">
		  		 	至
			  	 <input type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.createTimeEnd" onclick="laydate()">
			  	 &nbsp;&nbsp;
			  	 <input type="text" class="form-control"  placeholder="搜索用户账号" data-bind="textinput:model.search.account">
			  	 &nbsp;&nbsp;
			  	 <input type="text" class="form-control"  placeholder="搜索昵称" data-bind="textinput:model.search.nickName">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
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
								<th width="8%">昵称</th>
								<th width="8%">下注金额</th>
								<th width="8%">组合金额</th>
								<th width="6%">投注总数</th>
								<th width="8%">组合比例</th>
								<th width="7%">回水比例</th>
								<th width="8">回水金额</th>
								<th width="8">亏赢金额</th>
								<th width="14%">申请时间</th>
								<th width="6%">状态</th>
								<th width="11%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<!--<td class="border_none not_show c_align" >
										<label class="check_box"  data-bind="attr: { id: 'chk' + $index(),for: 'checkbox_a' + $index()},click: $root.clickCheckbox.bind($root,$index(),item)">
											<input type="checkbox" class="chk_1" data-bind="attr: { id: 'checkbox_a' + $index() }" />
										</label>
								</td>-->
								<td data-bind="text: item.id"></td>
								<td data-bind="text: item.nickName" ></td>
								<td data-bind="text: $root.fmt.currency(item.point,{places: 2,symbol: '',thousand: ','})"></td>
								<td data-bind="text: $root.fmt.currency(item.zuhePoint,{places: 2,symbol: '',thousand: ','})" ></td>
								<td data-bind="text: item.pointNum" ></td>
								<td data-bind="text: $root.fmt.currency(+(item.zuhePoint)/item.point,{places: 3,symbol: '',thousand: ','})" ></td>
								<td data-bind="text: $root.fmt.currency(item.bili,{places: 2,symbol: '',thousand: ''})" ></td>
								<td data-bind="text: $root.fmt.currency(item.huiShuiPoint,{places: 2,symbol: '',thousand: ','})" ></td>
								<td data-bind="html: $root.fmt.xhibitPoint(item.xhibitPoint)" ></td>
								<td data-bind="text: $root.fmt.date(item.createTime,'yyyy-MM-dd hh:mm')" ></td>
								<td data-bind="html: $root.fmt.status(item.status)" ></td>
								<td>
								<span class="label label-default" data-bind="visible:item.status==1" >处理</span>
								<span class="label label-default" data-bind="visible:item.status==2" >拒绝</span>
									<a href="javascritp:void(0);" style="color:#4cae4c;" data-bind="click:$root.showpop.bind($root,'delete','您确定要通过该回水记录？',$root.updateStatus.bind($root,1,item),1,'通过','success'),visible:item.status==0"><span class="glyphicon glyphicon-ok-circle"></span>通过</a>
										&nbsp;&nbsp;
									<a href="javascritp:void(0);" style="color:#f0ad4e;" data-bind="click:$root.showpop.bind($root,'delete','您确定要拒绝该回水记录？',$root.updateStatus.bind($root,2,item),1,'拒绝','warning'),visible:item.status==0"><span class="glyphicon glyphicon-ban-circle"></span>拒绝</a>
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
<script type="text/javascript" src="<@full_path path='js/backstage/huishui/log/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/huishui/log/list.js'/>" ></script>	
  </body>
</html>
