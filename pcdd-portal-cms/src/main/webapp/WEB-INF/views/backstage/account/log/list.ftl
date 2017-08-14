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
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/userAccountLog/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/userAccountLog/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/userAccountLog/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
		 <#if accountId??>
		  <#else>
		   <div class="row">
    		 <div class="header">
                      <h1 class="page-title">资金管理</h1>
             </div>
                    <ul class="breadcrumb">
                        <li><a href="javascript:void(0);">资金管理</a> <span class="divider">/</span></li>
                        <li class="active">转账详情</li>
                    </ul>
		 	</div>
		 </#if>		
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  		收款账号类型&nbsp;
                <select style="width: 90px;" class="form-control" data-bind="value:model.search.accountType">
                    <option value="">所有</option>
                    <option value="1">银行卡</option>
                    <option value="2">支付宝</option>
                </select>
		  		状态&nbsp;
                <select style="width: 90px;" class="form-control" data-bind="value:model.search.status">
                    <option value="">所有</option>
                    <option value="0">待确认</option>
                    <option value="1">已确认</option>
                    <option value="2">已取消</option>
                </select>
                &nbsp;
                                         转账时间
                 &nbsp;
                 <input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.createTimeBegin" onclick="laydate()">
                                             至
                 <input type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.createTimeEnd" onclick="laydate()">
                 &nbsp;&nbsp;
                 <input type="text" style="width: 115px;" class="form-control"  placeholder="搜索收款人用户id" data-bind="textinput:model.search.userId">
                 &nbsp;&nbsp;
                 <input type="text" style="width: 115px;" class="form-control"  placeholder="搜索收款人开户名" data-bind="textinput:model.search.realName">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th width="8%">用户ID</th>
								<th width="10%">转账账号</th>
								<th width="6%">转账账号类型</th>
								<th width="10%">收款账号</th>
								<th width="6%">收款账号类型</th>
								<th width="8%">开户名</th>
								<th width="10%">银行名称</th>
								<th width="8%">金额</th>
								<!--<th width="8%">存款方式</th>-->
								<th width="6%">状态</th>
								<th width="9%">转账时间</th>
								<th width="11%">操作</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: item.userId"></td>
								<td data-bind="text: item.account"></td>
								<td data-bind="text: $root.fmt.accountType(item.accountType)" ></td>
								<td data-bind="text: item.accountInfo"></td>
								<td data-bind="text: $root.fmt.accountType(item.collectionType)" ></td>
								<td data-bind="text: item.realName" ></td>
								<td data-bind="text: item.bankName" ></td>
								<td data-bind="text: $root.fmt.currency(item.point,{places: 2,symbol: '￥'})" ></td>
								<!--<td data-bind="text: item.addType" ></td>-->
								<td data-bind="html: $root.fmt.status(item.status)" ></td>
								<td data-bind="text: $root.fmt.date(item.createTime,'yyyy-MM-dd')" ></td>
								<td>
								    <a href="javascritp:void(0);" style="color:#4cae4c;" data-bind="click:$root.showpop.bind($root,'delete','您确认要转账？',$root.updateStatus.bind($root,item,1),1,'确认转账','success'),visible:item.status==0"><span class="glyphicon glyphicon-ok-sign"></span>确认</a>
                                    <a href="javascritp:void(0);" style="color:#f0ad4e;" data-bind="click:$root.showpop.bind($root,'delete','您要取消转账？',$root.updateStatus.bind($root,item,2),1,'取消转账','warning'),visible:item.status==0"><span class="glyphicon glyphicon-remove-sign"></span>取消</a>
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
<script>
    var accountId = '${accountId!}',accountType = '${accountType!}' || 0;
</script>
<script type="text/javascript" src="<@full_path path='js/backstage/account/log/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/account/log/list.js'/>" ></script>	
  </body>
</html>
