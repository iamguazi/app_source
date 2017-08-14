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
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/roomInfo/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/roomInfo/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/roomInfo/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
       	<!--新增或修改-->	
		<#include "/views/backstage/roomInfo/form.ftl"/>			
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">房间管理</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">房间管理</a> <span class="divider">/</span></li>
	            <li class="active">房间管理</li>
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
		  		&nbsp;&nbsp;
		  		注册时间
		  		 <input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.createTimeBegin" onclick="laydate()">
		  		 	至
			  	 <input type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.createTimeEnd" onclick="laydate()">
			  	 &nbsp;&nbsp;&nbsp;&nbsp;
			  	 <input type="text" class="form-control"  placeholder="搜索房间名称" data-bind="textinput:model.search.roomName">
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
						<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的广告吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-trash" style="margin-right:5px;"></span>删除
						</button>
						
						
						<!-- 刷新按钮 -->
						<button class="btn btn-success btn-sm pull-right" 
						   style="margin: .8em;border-radius: 0;margin-right: 15px;"  
						   onclick = "location.reload(true)" >
						   <span class="glyphicon glyphicon-repeat" style="margin-right:5px;"></span>刷新
						</button>
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th class="c_align" width="5%" >
									<label class="check_box_all " for="checkbox" id="check_all" data-bind="click: $root.clickCheckall.bind($root)">
			    					<input type="checkbox" id="checkbox" class="chk_all" />
			    				</label>
								</th>
								<th width="8%">ID</th>
								<th width="10%">游戏类型</th>
								<th width="8%">房间类型</th>
								<th width="10%">房间名称</th>
								<!--<th width="10%">房间图标</th>-->
								<th width="6%">机器人数量</th>
								<th width="6%">房间人数上限</th>
								<th width="6%">房间实际人数</th>
								<th width="15%">创建时间</th>
								<th width="6%">状态</th>
								<th width="20%">操作</th>
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
								<td data-bind="text: $root.fmt.gameType(item.gameType),click: $root.showForm.bind($root, item)"></td>
								<td data-bind="text: item.areaTypeName,click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="text: item.roomName,click: $root.showForm.bind($root, item)"></td>
								<!--<td data-bind="html: $root.fmt.img(item.roomPhoto),click:$root.fmt.biggerShowImg.bind($root,item.roomPhoto)" ></td>-->
								<td data-bind="text: item.rebotMaxCount,click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="text: item.peopleMaxCount,click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="text: item.realUser,click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="text: $root.fmt.date(item.createTime,'yyyy-MM-dd hh:mm:ss'),click: $root.showForm.bind($root, item)" ></td>
								<td data-bind="html: $root.fmt.status(item.status),click: $root.showForm.bind($root, item)" ></td>
								<td>
									<a href="javascritp:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-pencil"></span>编辑</a> 
									&nbsp; &nbsp; 
									<a href="javascritp:void(0);" data-bind="click:$root.showpop.bind($root,'delete','您确定要启用该房间？',$root.updateStatus.bind($root,1,item.id),1,'启用','success'),visible:item.status==0"><span class="glyphicon glyphicon-ok-circle"></span>启用</a>
									<a href="javascritp:void(0);" data-bind="click:$root.showpop.bind($root,'delete','您确定要拉黑该房间？',$root.updateStatus.bind($root,0,item.id),1,'拉黑','warning'),visible:item.status==1"><span class="glyphicon glyphicon-ban-circle"></span>停用</a>
									&nbsp; &nbsp;
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
<script type="text/javascript" src="<@full_path path='js/backstage/roomInfo/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/roomInfo/list.js'/>" ></script>	
  </body>
</html>
