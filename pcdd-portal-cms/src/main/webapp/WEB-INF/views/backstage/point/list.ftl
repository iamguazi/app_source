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
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/pointChangeLog/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/pointChangeLog/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/pointChangeLog/?act=SAVE' />"  />
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
            <#if userId?? && userId!="">
             <#else>
                <div class="header">
                  <h1 class="page-title">帐变记录</h1>
                </div>
                <ul class="breadcrumb">
                    <li><a href="javascript:void(0);">资金管理</a> <span class="divider">/</span></li>
                    <li class="active">帐变记录</li>
                </ul>
            </#if>
            
          </div>    
          
		  <div class="row search"  >
		  	<!--查询-->
		  	<div class="pull-right" >
		  	   <#if userId??  && userId!="" >
               <#else>
                                        描述详情:
                <select  class="form-control" data-bind="value:model.search.pointDesc,options:model.pointDescs,optionsText:'name',optionsValue:'id'" style="width:120px;">
                </select>
                &nbsp;
                <input type="text"  id="userId" class="form-control"  placeholder="请输入用户id" data-bind="textinput:model.search.userId" >
                &nbsp;
                <input type="text"  id="account" class="form-control"  placeholder="请输入账号" data-bind="textinput:model.search.account" >
                  &nbsp;              
               </#if>
		  		
		  		变动时间
		  		 <input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.createTimeBegin" onclick="laydate()">
		  		 	至
			  	 <input type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.createTimeEnd" onclick="laydate()">
			  	 &nbsp;&nbsp;
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
				</div>
		  </div>

		  <div class="row" style="padding-left:.45em">
						
						<!-- 按钮触发删除框 -->
						<!--<button class="btn btn-danger btn-sm" data-toggle="modal" 
						   style="margin: .8em;border-radius: 0;display: none;"  
						   data-bind="visible:model.checkItems().length,click:$root.showpop.bind($root,'delete','您确定要删除选中的充值记录吗？',$root.delItem.bind($root)) " >
						   <span class="glyphicon glyphicon-trash" style="margin-right:.8em;"></span>删除
						</button>-->
		  </div>
		  
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th width="10">用户ID</th>
								<th width="15%">昵称</th>
								<th width="15%">账号</th>
								<th width="20%">变动金额</th>
								<th width="20%">变动描述</th>
								<th width="20%">变动时间</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: item.userId"></td>
								<td data-bind="text: item.nickName" ></td>
								<td data-bind="text: item.account" ></td>
								<td data-bind="text: $root.fmt.currency(item.point,{'symbol':'￥','places':2,})"></td>
								<td data-bind="text: item.pointDesc" ></td>
								<td data-bind="text: $root.fmt.date(item.createTime,'yyyy-MM-dd hh:mm:ss')" ></td>
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
	var userId = '${userId!}';
</script>
<script type="text/javascript" src="<@full_path path='js/backstage/point/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/point/list.js'/>" ></script>	
  </body>
</html>
