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
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/robot/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/robot/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/robot/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	<!--新增或修改-->	
		<#include "/views/backstage/robot/form.ftl"/>	
       	<!--详情-->	
		<#include "/views/backstage/robot/detail.ftl"/>	
       						
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">机器人管理</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">用户管理</a> <span class="divider">/</span></li>
	            <li class="active">机器人管理</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  		状态:
		  		<select  class="form-control" data-bind="value:model.search.status">
		  			<option value="">所有</option>
		  			<option value="1">正常</option>
		  			<option value="0">拉黑</option>
		  		</select>    
		  		&nbsp;&nbsp;&nbsp;&nbsp;
		  		注册时间
		  		 <input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.createTimeBegin" onclick="laydate()">
		  		 	至
			  	 <input type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.createTimeEnd" onclick="laydate()">
			  	 &nbsp;&nbsp;&nbsp;&nbsp;
			  	 <input style="width:120px;" type="text" class="form-control"  placeholder="搜索用户账号" data-bind="textinput:model.search.account">
			  	 <input type="text" class="form-control"  placeholder="搜索昵称" data-bind="textinput:model.search.nickName">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
		    </div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						<!-- 按钮触发新增框 -->
						<!--<button class="btn btn-primary btn-sm" data-toggle="modal" data-bind=" click:$root.showForm.bind($root)"
						    style="margin: .8em;border-radius: 0; " >
						   <span class="glyphicon glyphicon-plus" style="margin-right:.8em;"></span>添加
						</button>-->
						
						<!-- 按钮触发删除框 -->
						<!--<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的用户吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-remove" style="margin-right:.8em;"></span>删除
						</button>-->
						
						<!--导出-->
						<div class="btn-group" style="margin: .8em;border-radius: 0;">
									<button type="button" class="btn btn-sm btn-primary btn-block dropdown-toggle"
										data-toggle="dropdown" aria-haspopup="true"
										aria-expanded="false" style="border-radius: 0; ">
										<span class="glyphicon glyphicon-open" style="margin-right:.8em;"></span>导出Excel <span class="caret"></span>
									</button>
									<ul class="dropdown-menu">
										<li><a href="javascript: void(0);" data-bind="click: $root.exportExcel.bind($root, 10)">10条</a></li>
										<li><a href="javascript: void(0);" data-bind="click: $root.exportExcel.bind($root, 30)">30条</a></li>
										<li><a href="javascript: void(0);" data-bind="click: $root.exportExcel.bind($root, 50)">50条</a></li>
										<li><a href="javascript: void(0);" data-bind="click: $root.exportExcel.bind($root, 0)">全部</a></li>
									</ul>
						</div>
						<!--导入-->
						<input type="file" id="import" onchange="viewModel.uploadXls(this, 'backstage/robot/import',2)" accept=".xls,.xlsx" id="file_sequence_id_2" style="opacity: 0;cursor: pointer;line-height: inherit;filter: alpha(opacity=0);width: inherit;height: inherit;position: absolute;top: 0;left: 0;margin: 0;padding: 0;z-index: 1;display:none;" />
						<button class="btn btn-primary btn-sm" data-toggle="modal" 
							   style="margin: .8em;border-radius: 0;"  
							   data-bind="click:function(e){$('#import').click();console.log(2222)}" >
							<span class="glyphicon glyphicon-save" style="margin-right:.8em;"></span>导入Excel
						</button>
						<!--模板下载-->
						<button class="btn btn-primary btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;"  
						   data-bind="click:function(){location.href=fullPath+'backstage/robot/downLoadExcelTemplate'}" >
						   <span class="glyphicon glyphicon-download" style="margin-right:.8em;"></span>下载模板
						</button>
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th width="10%">账号</th>
								<th width="10%">昵称</th>
								<th width="8%">头像</th>
								<th width="5%">等级</th>
								<th width="11%">账户余额</th>
								<th width="10%">亏盈金额</th>
								<th width="12%">最近上线时间</th>
								<!--<th width="4%">是否在线</th>-->
								<th width="6%">状态</th>
								<th width="24%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: item.account"></td>
								<td data-bind="text: item.nickName" ></td>
								<td data-bind="html: $root.fmt.img(item.userPhoto),click:$root.fmt.biggerShowImg.bind($root,$root.fmt.img4Null(item.userPhoto,defaultUserIcon))" ></td>
								<td data-bind="text: item.levelName" ></td>
								<td data-bind="text: $root.fmt.currency(item.point,{places: 2,symbol: ''})" ></td>
								<td data-bind="html: $root.fmt.xhibitPoint(item.xhibitPoint)" ></td>
								<td data-bind="text: $root.fmt.date(item.loginTime,'yyyy-MM-dd hh:mm')" ></td>
								<!--<td data-bind="html: $root.fmt.isOnline(item.loginTime)" ></td>-->
								<td data-bind="html: $root.fmt.status(item.status)" ></td>
								<td>
									<a href="javascritp:void(0);" style="color:#4cae4c;" data-bind="click:$root.showpop.bind($root,'delete','您确定要恢复该用户？',$root.updateStatus.bind($root,1,item.id),1,'恢复','success'),visible:item.status==0"><span class="glyphicon glyphicon-ok-circle"></span>恢复</a>
									<a href="javascritp:void(0);" style="color:#f0ad4e;" data-bind="click:$root.showpop.bind($root,'delete','您确定要拉黑该用户？',$root.updateStatus.bind($root,0,item.id),1,'拉黑','warning'),visible:item.status==1"><span class="glyphicon glyphicon-ban-circle"></span>拉黑</a>
									&nbsp; &nbsp; 
									<a href="javascritp:void(0);" data-bind="click:$root.showDetail.bind($root, item)"><span class="glyphicon glyphicon-eye-open"></span>详情</a> 
									&nbsp; &nbsp; 
									<a href="javascritp:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-pencil"></span>编辑</a> 
									&nbsp; &nbsp; 
									<a href="javascritp:void(0);" style="color:#4cae4c;" data-bind="click:$root.showpop.bind($root,'delete','您确定要将该用户密码重置为:123456？',$root.resetPassword.bind($root,item.id),1,'重置密码','success')"><span class="glyphicon glyphicon-ok-circle"></span>重置</a>
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
<script type="text/javascript" src="<@full_path path='js/backstage/robot/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/robot/list.js'/>" ></script>	
  </body>
</html>
