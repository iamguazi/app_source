<!DOCTYPE html>
<html>
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>分销管理</title>

    <!-- Bootstrap -->
    <link href="<@full_path path='js/lib/bootstrap-3.3.5/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<@full_path path='css/main.css'/>" rel="stylesheet">
    <link href="<@full_path path='js/lib/cover/jquery.cover.css'/>" rel="stylesheet">
	
	<#include "/views/backstage/common/common_script.ftl"/>
    
     <style>
        .lightTitle {
            color: #2797DA;
        }
        
        .glyphicon-user-notice:before {
            content:url(<@full_path path='img/notice.png'/>);
            position: relative;
            top: 3px;
        }
    </style>
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/userInfo/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/userInfo/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/userInfo/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	<!--新增或修改-->	
		<#include "/views/backstage/user/form.ftl"/>	
       	<!--详情-->	
		<#include "/views/backstage/user/detail.ftl"/>	
		<!--充值-->
        <#include "/views/backstage/user_choice/recharge.ftl" />
		<!--账变详情-->
        <#include "/views/backstage/user/pointChangeLog.ftl" />
		<!--提现详情-->
        <#include "/views/backstage/user/withdrawalLogs.ftl" />
		<!--隐含卡详情-->
        <#include "/views/backstage/user/userBankInfos.ftl" />
        <!--将线下转移到其他用户名下页面-->
        <#include "/views/backstage/user/modifyParentId.ftl" />
        <!--通知页面-->
        <#include "/views/backstage/user/notice.ftl" />
       						
		 <div class="row">
			<!--栏目路径-->
	        <#if code?? && code!="">
                <div class="header">
                  <h1 class="page-title"><span class="lightTitle">【${account!}】</span>下级分销</h1>
                </div>
             <#else>
                <div class="header">
                  <h1 class="page-title">用户管理</h1>
                </div>
                <ul class="breadcrumb">
                    <li><a href="javascript:void(0);">用户管理</a> <span class="divider">/</span></li>
                    <li class="active">用户管理</li>
                </ul>
            </#if>
	        
		  </div>		  
		  
		  <div class="row"  >
              <button class="pull-right btn btn-primary btn-sm"  data-bind="click:$root.searchByTime.bind($root,'this_month')"
                     style="margin: .8em;" >
                                        本月新增
              </button>
		      <button class="pull-right btn btn-primary btn-sm"  data-bind="click:$root.searchByTime.bind($root,'this_week')"
                     style="margin: .8em;" >
                                        本周新增
              </button>
              <button class="pull-right btn btn-primary btn-sm"  data-bind="click:$root.searchByTime.bind($root,'yesterday')"
                     style="margin: .8em;" >
                                        昨日新增
              </button>
              <button class="pull-right btn btn-primary btn-sm"  data-bind="click:$root.searchByTime.bind($root,'today')"
                     style="margin: .8em;" >
                                        今日新增
              </button>
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
			  	 <input style="width:120px;" type="text" class="form-control"  placeholder="搜索用户id" data-bind="textinput:model.search.id">
			  	 <input style="width:120px;" type="text" class="form-control"  placeholder="搜索用户账号" data-bind="textinput:model.search.account">
			  	 <input style="width:120px;" type="text" class="form-control"  placeholder="搜索昵称" data-bind="textinput:model.search.nickName">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
		    </div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						<!-- 按钮触发新增框 -->
						<button class="btn btn-primary btn-sm" data-toggle="modal" style="display: none;" data-bind="click:$root.showForm.bind($root)"
						    style="margin: .8em;border-radius: 0; " >
						   <span class="glyphicon glyphicon-plus" style="margin-right:.8em;"></span>添加
						</button>
						
						<!-- 按钮触发删除框 -->
						<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的用户吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-remove" style="margin-right:.8em;"></span>删除
						</button>
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
							    <th>ID</th>
								<th width="6%">账号</th>
								<th width="8%">昵称</th>
								<th width="8%">手机号</th>
								<th width="8%">头像</th>
								<th width="5%">等级</th>
								<th width="10%" style="cursor: pointer;" data-bind="attr:{sort:model.search.pointSort()},click: function($root, event){serachSort('pointSort',event,$root)}">
								账户余额
								    <span class="glyphicon glyphicon-arrow-down" data-bind="clickBubble: false,visible:model.search.pointSort()=='desc'"></span>
								    <span class="glyphicon glyphicon-arrow-up" data-bind="clickBubble: false,visible:model.search.pointSort()=='asc'"></span>
								</th>
								<th width="10%" style="cursor: pointer;" data-bind="attr:{sort:model.search.xhibitPointSort()},click: function($root, event){serachSort('xhibitPointSort',event,$root)}">
								    亏盈金额
								    <span class="glyphicon glyphicon-arrow-down" data-bind="clickBubble: false,visible:model.search.xhibitPointSort()=='desc'"></span>
                                    <span class="glyphicon glyphicon-arrow-up" data-bind="clickBubble: false,visible:model.search.xhibitPointSort()=='asc'"></span>
								</th>
								<th width="12%">最近上线时间</th>
								<th width="6%">IP</th>
								<th width="5%">状态</th>
								<th width="22%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
							    <td data-bind="text:item.id"></td>
								<td data-bind="text: item.account"></td>
								<td data-bind="text: item.nickName" ></td>
								<td data-bind="text: item.mobile" ></td>
								<td data-bind="html: $root.fmt.img(item.userPhoto),click:$root.fmt.biggerShowImg.bind($root,$root.fmt.img4Null(item.userPhoto,defaultUserIcon))" ></td>
								<td data-bind="text: item.levelName" ></td>
								<td data-bind="text: $root.fmt.currency(item.point,{places: 2,symbol: ''})" ></td>
								<td data-bind="html: $root.fmt.xhibitPoint(item.xhibitPoint)" ></td>
								<td data-bind="text: $root.fmt.date(item.loginTime,'yyyy-MM-dd hh:mm')" ></td>
								<td data-bind="text: item.ipAddr" ></td>
								<td data-bind="html: $root.fmt.status(item.status)" ></td>
								<td>
									<a href="javascript:void(0);" style="color:#4cae4c;" data-bind="click:$root.showpop.bind($root,'delete','您确定要恢复该用户？',$root.updateStatus.bind($root,1,item.id),1,'恢复','success'),visible:item.status==0"><span class="glyphicon glyphicon-ok-circle"></span>恢复</a>
									<a href="javascript:void(0);" style="color:#f0ad4e;" data-bind="click:$root.showpop.bind($root,'delete','您确定要拉黑该用户？',$root.updateStatus.bind($root,0,item.id),1,'拉黑','warning'),visible:item.status==1"><span class="glyphicon glyphicon-ban-circle"></span>拉黑</a>
									&nbsp;
									<a href="javascript:void(0);" style="color:#58B75B;" data-bind="click:$root.showRechargeForm.bind($root, item)"><span class="glyphicon glyphicon-credit-card"></span>充值</a> 
									&nbsp;
									<a href="javascript:void(0);" data-bind="click:$root.showDetail.bind($root, item)"><span class="glyphicon glyphicon-eye-open"></span>详情</a> 
									&nbsp;
									<a href="javascript:void(0);" data-bind="click:$root.showForm.bind($root, item)"><span class="glyphicon glyphicon-pencil"></span>编辑</a> 
									&nbsp; 
									<a href="javascript:void(0);" style="color:#4cae4c;" data-bind="click:$root.showpop.bind($root,'delete','您确定要将该用户密码重置为:123456？',$root.resetPassword.bind($root,item.id),1,'重置密码','success')"><span class="glyphicon glyphicon-ok-circle"></span>重置</a>
									&nbsp; 
									<a href="javascript:void(0);" data-bind="click:$root.showChangePointLog.bind($root, item)"><span class="glyphicon glyphicon-eye-open"></span>帐变详情</a> 
									&nbsp; 
									<a href="javascript:void(0);" data-bind="click:$root.showWithdraswaLog.bind($root, item)"><span class="glyphicon glyphicon-eye-open"></span>提现详情</a> 
								    &nbsp;
									<a href="javascript:void(0);" data-bind="click:$root.showAccountInfo.bind($root, item)"><span class="glyphicon glyphicon-eye-open"></span>银行卡</a> 
								    &nbsp;
									<a href="javascript:void(0);" data-bind="visible:!item.code,click:$root.showModifyParentId.bind($root, item)"><span class="glyphicon glyphicon-cog"></span>上级变更</a> 
								    &nbsp;
								    <a href="javascript:void(0);" data-bind="click:$root.showNoticeForm.bind($root, item)" ><span class="glyphicon glyphicon-user-notice"></span>通知</a>
                                    &nbsp;
								    <a href="" target="_blank" data-bind="attr:{href:fullPath+'backstage/userInfo/toIndex?parentId='+item.id+'&account='+item.account+'&mobile='+item.mobile+'&nickName='+item.nickName}"><span class="glyphicon glyphicon-share-alt"></span>下级分销</a>
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
<script>
    var parentCode = '${code!}';
</script>
<script type="text/javascript" src="<@full_path path='js/lib/ckeditor/ckeditor.js'/>" ></script>    
<script type="text/javascript" src="<@full_path path='js/ckedit_model_conflict.js'/>" ></script>  
<script type="text/javascript" src="<@full_path path='js/backstage/user/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/user/list.js'/>" ></script>	
  </body>
</html>
