<!DOCTYPE html>
<html>
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>财务统计</title>

    <!-- Bootstrap -->
    <link href="<@full_path path='js/lib/bootstrap-3.3.5/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<@full_path path='css/main.css'/>" rel="stylesheet">
    <link href="<@full_path path='js/lib/cover/jquery.cover.css'/>" rel="stylesheet">
	
	<#include "/views/backstage/common/common_script.ftl"/>
    
    <style>
    	
    	.line_height_title {
    		line-height:45px;
    		padding-left: 10px;
    		height:45px;
    		font-weight:bold;
    		border-bottom: 1px solid #ccc;
    		position:relative;
    	}
    	.line_height_body {
    		height:120px;
    		text-align: center;
    		font-size:20px;
    		position:relative;
    	}
    	
    	.middleText {
    	   position:absolute;
    	   font-weight:bold;
    	   top:50%;
    	   left:0;
    	   right:0;
    	   word-wrap: break-word;
    	   transform: translateY(-50%);
           -webkit-transform:translateY(-50%);
           -moz-transform:transformY(-50%);
           -ms-transform:transformY(-50%);
    	}
    	.full_div {
    		width: 100%;
    		background-color:#fff;
    		border-radius: 5px;
    		cursor:pointer;
    	}
    	
    	.successColor {
    	   color: #009688;
    	}
    	
    	.blueColor {
    	   color:#337AB7;
    	}
    	
    	.redColor {
    	   color:#337AB7
    	}
    	
    	.label {
    	   position:absolute;
    	   right:10px;top:50%;
    	   transform: translateY(-50%);
    	   -webkit-transform:translateY(-50%);
    	   -moz-transform:transformY(-50%);
    	   -ms-transform:transformY(-50%);
    	}
    	
    	.label-success {
    	   background-color:#1FB5AD;
    	   border-color:#1fb5ad;
    	}
    </style>
    
  </head>
  <body style="background-color: #f1f4fb;">
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/financialStatistics/query' />"  />
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
	            <h1 class="page-title">快速导航</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">系统管理</a> <span class="divider">/</span></li>
	            <li class="active">快速导航</li>
            </ul>
		  </div>		  
			
		
		  
		  <!--详情-->
		 
		 <div class="row" >
				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" data-bind="click:$root.goToCodeMenu.bind($root,'super_user_info')" >
						<div class="line_height_title" >
						会员<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:model.formEntity.allUser()">0</span>个</span>
						</div>
					</div>
				</div>
				
				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" data-bind="click:$root.goToCodeMenu.bind($root,'super_user_info')">
						<div class="line_height_title" >
						今日新增会员<span class="label label-success" >新</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText successColor"><span data-bind="text:model.formEntity.dayUser()" >0</span>个</span>
						</div>
					</div>
				</div>

				<!--<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" >
						<div class="line_height_title" >
						今日活跃会员<span class="label label-success" >新</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText successColor"><span data-bind="text:model.formEntity.dayActiveUser()">0</span>个</span>
						</div>
					</div>
				</div>-->

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;" >
					<div class="full_div" data-bind="click:$root.goToCodeMenu.bind($root,'super_user_info')" >
						<div class="line_height_title" >
						周新增会员<span class="label label-success" >新</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText successColor"><span data-bind="text:model.formEntity.weekUser()">0</span>个</span>
						</div>
					</div>
				</div>

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;" >
					<div class="full_div" data-bind="click:$root.goToCodeMenu.bind($root,'super_user_info')" >
						<div class="line_height_title" >
						月新增会员<span class="label label-success" >新</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText successColor"><span data-bind="text:model.formEntity.monthUser()">0</span>个</span>
						</div>
					</div>
				</div>

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" >
						<div class="line_height_title" >
						平台总输赢<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:$root.fmt.currency(model.formEntity.platformWinOrLoseFee())">0</span>元</span>
						</div>
					</div>
				</div>

				<!--<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" >
						<div class="line_height_title" >
						盘内总额<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:$root.fmt.currency(model.formEntity.dishAllFee())">0</span>元</span>
						</div>
					</div>
				</div>

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div"  >
						<div class="line_height_title" >
						赠送总额<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:$root.fmt.currency(model.formEntity.givingAllFee())">0</span>元</span>
						</div>
					</div>
				</div>

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" >
						<div class="line_height_title" >
						在线人数<span class="label label-success" >今</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText successColor"><span data-bind="text:model.formEntity.onlineUser()">0</span>人</span>
						</div>
					</div>
				</div>

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" >
						<div class="line_height_title" >
						今日浮动盈亏<span class="label label-success" >今</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText successColor"><span data-bind="text:$root.fmt.currency(model.formEntity.toDayFloatExhibit())">0</span>元</span>
						</div>
					</div>
				</div>-->

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" data-bind="click:$root.goToCodeMenu.bind($root,'super_user_pay_log')" >
						<div class="line_height_title" >
						今日第三方充值额<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:$root.fmt.currency(model.formEntity.dayPayFee())">0</span>元</span>
						</div>
					</div>
				</div>

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" data-bind="click:$root.goToCodeMenu.bind($root,'super_point_change_log_info')" >
						<div class="line_height_title" >
						今日后台充值<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:$root.fmt.currency(model.formEntity.dayOnlinePayFee())">0</span>元</span>
						</div>
					</div>
				</div>

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" data-bind="click:$root.goToCodeMenu.bind($root,'super_account_log_info')" >
						<div class="line_height_title" >
						今日线下转账<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:$root.fmt.currency(model.formEntity.dayOfflinePayFee())">0</span>元</span>
						</div>
					</div>
				</div>

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" data-bind="click:$root.goToCodeMenu.bind($root,'super_withdrawals_log')" >
						<div class="line_height_title" >
						今日提现额<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:$root.fmt.currency(model.formEntity.dayWithdrawalsFee())">0</span>元</span>
						</div>
					</div>
				</div>
				
				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" >
						<div class="line_height_title" >
						今日总输赢额<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:$root.fmt.currency(model.formEntity.dayWinOrLoseFee())">0</span>元</span>
						</div>
					</div>
				</div>

				<div class="col-md-2 col-xs-2 col-sm-2 col-lg-2" style="padding: 10px;">
					<div class="full_div" >
						<div class="line_height_title" >
						会员总余额<span class="label label-primary" >总</span>
						</div>
						<div class="full_div line_height_body" >
							<span class="middleText blueColor"><span data-bind="text:$root.fmt.currency(model.formEntity.totalPoint())">0</span>元</span>
						</div>
					</div>
				</div>
					
		 </div>
		  
</div>
<script type="text/javascript" src="<@full_path path='js/backstage/statistics/index/service.js'/>" ></script>	
<script type="text/javascript" src="<@full_path path='js/backstage/statistics/index/list.js'/>" ></script>	
  </body>
</html>
