<!DOCTYPE html>
<html>
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=9; IE=8; IE=7; IE=EDGE">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>首页</title>

    <!-- Bootstrap -->
    
    <link href="<@full_path path='js/lib/bootstrap-3.3.5/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<@full_path path='css/index.css'/>" rel="stylesheet">
    <#include "/views/backstage/common/common_script.ftl"/>
    <style>
    .dropdown-menu>li>a {
        font-weight: 100 !important;
        font-size:14px !important;
    }
    </style>
    <script>
    /**
        if(typeof(EventSource)!=="undefined"){
          var source=new EventSource(fullPath+"backstage/userAccountLog/noticeNewUserAccountLog");
          source.onmessage=function(event){
            Lobibox.notify("info",{msg:"您有信息收款信息需要处理"+event.data})
          };
          source.onerror=function(e){
           console.log(e);
          }
          source.onopen=function(e){
            console.log(e);
          }
     } else {
          Lobibox.notify("error",{msg:"抱歉，您的浏览器不支持 server-sent 事件 ..."});
     }
     **/
    </script>
  </head>
  <body>
  
  		<!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
        <!-- 消息通知二次确认页面 s-->
        <div class="modal fade" id="showNotice" tabindex="-1" 
          aria-labelledby="noticeLabel" aria-hidden="true">
          <div class="modal-dialog">
            <div class="modal-content">
              <div  class="alert alert-info">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">
                  &times;
                </button>
                <h3 class="modal-title " id="noticeLabel" >
                                                消息通知
                </h3>
              </div>
              <div class="modal-body">
                <!--添加的内容-->
                 <span id="contentMsg">您有新的收款信息需要处理!</span>
              </div>
              <div class="modal-footer" style="border-top:0;">
                <div class="row"  >
                  <div class="col-lg-2 col-md-2 col-sm-2  col-xs-2 col-lg-offset-4 col-md-offset-4 col-sm-offset-4 col-xs-offset-4" >
                    <button type="button" id="cancelNoticeButton" class="btn btn-default center-block"  data-dismiss="modal">
                                                             取消
                    </button>
                  </div>
                  <div class="col-lg-2 col-md-2 col-sm-2 col-xs-2 " >
                    <button id="submitNoticeButton" type="button" class="btn btn-info center-block"  >
                                                               确定
                    </button>
                  </div>
                </div>
              </div>
            </div><!-- /.modal-content -->
          </div><!-- /.modal -->
        </div>                              
        <!-- 二次确认页面e -->



       <div class="container" >
       		<!--头部s-->
					<div class="row"  id="topMenu" style="height:60px;">
							<div class="col-md-12 col-xs-12" style="line-height:60px;color:#fff;font-size:18px;text-align:center; vertical-align:middle;" >
							          超级管理后台
									<span id="headRight" style="position:absolute;right:10px;font-size:15px">
										欢迎您，<span class="glyphicon glyphicon-user"></span><span>${Session.manager.name!}</span>&nbsp;&nbsp;&nbsp;&nbsp;
										<a href="<@full_path path='admin/logout'/>" >退出</a>
									</span>
							</div>
							<div id="noticeDrop" class="dropdown" style="display: none;position: absolute;left:10px;top: 13px;">
                                    <button type="button" class="btn  btn-info dropdown-toggle" id="dropdownMenu1" data-toggle="dropdown">消息通知
                                        <span class="caret"></span>
                                    </button>
                                    <ul class="dropdown-menu" role="menu" aria-labelledby="dropdownMenu1">
                                        <li role="presentation">
                                            <a id="super_account_log_info" style="display:none;" href="javascript:void(0)"  data-bind="click:$root.goToCodeMenu.bind($root,'super_account_log_info')" >收款账号消息<span class="badge" style="background-color: #c9302c;"></span>  
                                                <span class="label label-success" data-bind="css:{'label-danger':!model.store.accountNotice()}">
                                                    <!-- ko if: model.store.accountNotice() -->
                                                                                                                                                开启
                                                    <!-- /ko -->
                                                    <!-- ko if: !model.store.accountNotice() -->
                                                                                                                                                  关闭
                                                    <!-- /ko -->
                                                </span>
                                            </a>
                                            <a id="super_withdrawals_log" style="display:none;" href="javascript:void(0)"  data-bind="click:$root.goToCodeMenu.bind($root,'super_withdrawals_log')" >提现消息<span class="badge" style="background-color: #c9302c;"></span> 
                                                <span class="label label-success" data-bind="css:{'label-danger':!model.store.widthdrawalsNotice()}">
                                                    <!-- ko if: model.store.widthdrawalsNotice() -->
                                                                                                                                                开启
                                                    <!-- /ko -->
                                                    <!-- ko if: !model.store.widthdrawalsNotice() -->
                                                                                                                                                  关闭
                                                    <!-- /ko -->
                                                </span>
                                            </a>
                                            <a id="super_exchange_gift_log" style="display:none;" href="javascript:void(0)"  data-bind="click:$root.goToCodeMenu.bind($root,'super_exchange_gift_log')" >礼物兑换消息<span class="badge" style="background-color: #c9302c;"></span>
                                                   <span class="label label-success" data-bind="css:{'label-danger':!model.store.exchangeNotice()}">
                                                    <!-- ko if: model.store.exchangeNotice() -->
                                                                                                                                                开启
                                                    <!-- /ko -->
                                                    <!-- ko if: !model.store.exchangeNotice() -->
                                                                                                                                                  关闭
                                                    <!-- /ko -->
                                                    </span>
                                            </a>
                                        </li>
                                    </ul>
                             </div>
					</div>
					<!--头部e-->
					
				<!--底部s-->	
				<div id="bottomContainer" class="row" style="padding-bottom: 30px;">
					<!--左边菜单s-->
					<div class="col-md-2 col-xs-2 text-justify clearLeftMenuPadding" >			
						<div class="sidebar-nav">
					        <a id="templateA" href="javascript:void(0);" class="nav-header" style="display:none;"><img class="icon-briefcase" alt="" /><span class="glyphicon glyphicon-chevron-up"></span></a>
					        <ul id="templateUl" class="nav nav-list collapse" style="display:none;">
					            
					        </ul>
			            </div>
					</div>
					<!--左边菜单e-->
					
					<!--主页s-->
					<div class="col-md-10 col-xs-10 clearRightMenuPadding"  id="rightMain"  name="rightMain">
						
						<iframe id="right_iframe" src="" name="right_iframe">
						
						</iframe>
					</div>
				</div>
				<#include "/views/backstage/common/footer.ftl"/>
				<!--主页-->
</div>
   
   <audio src="<@full_path path='media/dingdong.wav' />" id="dingdongPlayer" />	
   <script type="text/javascript" src="<@full_path path='js/backstage/index/service.js'/>" ></script>	
   <script type="text/javascript" src="<@full_path path='js/backstage/index/list.js'/>" ></script>	
  </body>
</html>
