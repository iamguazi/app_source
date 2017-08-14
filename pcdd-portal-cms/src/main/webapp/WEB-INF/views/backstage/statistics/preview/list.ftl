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
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/previewFinancialStatistics/query' />"  >
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/previewFinancialStatistics/?act=DELETE' />"  >
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/previewFinancialStatistics/?act=SAVE' />"  >
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
	            <h1 class="page-title">统计预览</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">系统管理</a> <span class="divider">/</span></li>
	            <li class="active">统计预览</li>
            </ul>
		  </div>		  
			
		  <div class="row search"  >
		  	<!--查詢-->
		  	<div class="pull-right" >
		  		<input type="text"  id="createTimeBegin" class="form-control laydate-icon" placeholder="起始日期" data-bind="textinput:model.search.createTimeBegin" onclick="laydate({max: laydate.now(),choose: function(datas){checkCreateTimeBegin(datas)}})">
                    至
                 <input type="text"  id="createTimeEnd" class="form-control laydate-icon"  placeholder="结束日期" data-bind="textinput:model.search.createTimeEnd" onclick="laydate({max: laydate.now(),choose: function(datas){checkCreateTimeEnd(datas)}})">
			  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
	               <span class="glyphicon glyphicon-search"></span> Search
	             </button>
			   </div>
		    </div>

		   <div class="row" style="padding-left:.45em">
             <!--导出-->
             <button style="margin: 10px;border-radius: 0;" class="btn btn-primary btn-sm"  data-bind="click:exportXls" ><span class="glyphicon glyphicon-open" style="margin-right:.8em;"></span>导出Excel</button>
          </div>
          
			<!--列表数据s-->
			<div class="row"  >
				<div class="col-md-12" >
					<table class="table table-bordered table-hover">
						<thead>
							<tr>
								<th width="10%">时间</th>
								<th width="10%">新用户</th>
								<!-- ko foreach:model.gameTypeArray -->
								   <!-- ko if: $data.id() -->
                                      <th data-bind="text:$data.name,attr:{width:35/($root.model.gameTypeArray().length-1)+'%'}"></th>
								    <!-- /ko -->
								<!-- /ko -->
								<th width="15%">回水</th>
								<th width="15%">提现</th>
								<th width="15%">充值</th>
							</tr>
						</thead>
						<tbody data-bind="foreach: {data: model.dataList, as: 'item'}, visible: model.dataList().length" >
							<tr data-bind="css : { evenTr: $index()%2==1 }">
								<td data-bind="text: item.time"></td>
								<td data-bind="text: item.newUser" ></td>
								<!-- ko foreach:$root.model.gameTypeArray -->
                                   <!-- ko if: $rawData.id() -->
                                        <td data-bind="text: $root.fmt.currency(item['gameType'+$rawData.id()],{places: 2,symbol: ''})" ></td>
                                    <!-- /ko -->
                                <!-- /ko -->
								<td data-bind="text: $root.fmt.currency(item.backWater,{places: 2,symbol: ''})"></td>
								<td data-bind="text: $root.fmt.currency(item.withdrawalFee,{places: 2,symbol: ''})"></td>
								<td data-bind="text: $root.fmt.currency(item.rechargeFee,{places: 2,symbol: ''})"></td>
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
</div>
<script>
    function checkCreateTimeBegin(dates){
        var end = $("#createTimeEnd").val();
        if(end ){
            var startTime = new Date(dates).getTime(), endTime = new Date(end).getTime() ;
            if( startTime > endTime){ 
                $("#createTimeBegin").val("");
                alert("起始日期不能大于结束日期");
            }
            
            if( (endTime - startTime)/(3600*1000*24) > 9 ){ 
                $("#createTimeBegin").val("");
                alert("最多只能查询10天内的数据");
            } 
        }
    }
    
    
    function checkCreateTimeEnd(dates){
        var begin = $("#createTimeBegin").val();
        if(begin){
            var endTime = new Date(dates).getTime(), startTime = new Date(begin).getTime() ;
            if( startTime > endTime){ 
                $("#createTimeEnd").val("");
                alert("结束日期不能小于起始日期");
            }
            
            if( (endTime - startTime)/(3600*1000*24) > 9 ){ 
                $("#createTimeEnd").val("");
                alert("最多只能查询10天内的数据");
            } 
        }
    }
</script>
<script type="text/javascript" src="<@full_path path='js/backstage/statistics/preview/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/statistics/preview/list.js'/>" ></script>	
  </body>
</html>
