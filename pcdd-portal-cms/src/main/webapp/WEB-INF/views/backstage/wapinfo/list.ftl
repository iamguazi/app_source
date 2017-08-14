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
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/wapInfo/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/wapInfo/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/wapInfo/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	
  	
  	 <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       
   <div class="container" style="width:100%;">   
	    <div class="row">
		  <!--栏目路径-->
		  <div class="header">
		    <h1 class="page-title">文章设置</h1>
		  </div>
		  <ul class="breadcrumb">
		    <li><a href="javascript:void(0);">系统设置</a> <span class="divider">/</span></li>
		    <li class="active">文章设置</li>
	      </ul>
	    </div>
	    
		    <ul id="nav" class="nav nav-tabs nav-justified" style="margin-top:20px;">
				<li id="USER_AGREEMENT" class="active" data-bind="click:$root.showForm.bind($root, 'USER_AGREEMENT')" ><a href="javascript:void(0);" >用户协议</a></li>
				<li id="ABOUT_US" data-bind="click:$root.showForm.bind($root, 'ABOUT_US')" ><a href="javascript:void(0);">关于我们</a></li>
				<li id="TUI_JIAN" data-bind="click:$root.showForm.bind($root, 'TUI_JIAN')" ><a href="javascript:void(0);">推荐有礼</a></li>
				<li id="WITHDRAWALS_AGREEMENT" data-bind="click:$root.showForm.bind($root, 'WITHDRAWALS_AGREEMENT')"><a href="javascript:void(0);" >用户提现协议</a></li>
				<li id="CHU_JI" data-bind="click:$root.showForm.bind($root, 'CHU_JI')"><a href="javascript:void(0);" >初级房赔率说明</a></li>
				<li id="ZHONG_JI" data-bind="click:$root.showForm.bind($root, 'ZHONG_JI')"><a href="javascript:void(0);" >中级房赔率说明</a></li>
				<li id="GAO_JI" data-bind="click:$root.showForm.bind($root, 'GAO_JI')"><a href="javascript:void(0);" >高级房赔率说明</a></li>
				<!--<li id="SECTET_AGREEMENT" data-bind="click:$root.showForm.bind($root, 'SECTET_AGREEMENT')"><a href="javascript:void(0);" >保密协议</a></li>
				<li id="COMMON_PROBLEMS" data-bind="click:$root.showForm.bind($root, 'COMMON_PROBLEMS')" ><a href="javascript:void(0);" >常见问题</a></li>-->
			</ul>
		
		<form id="form">
			<textarea id="content" name ="content">
				
			</textarea>	
		</form>
		
		<button type="button" class="btn btn-primary center-block" data-bind="click:$root.beforeSubmit.bind($root)" style="margin-top:20px;">
			   <span class="glyphicon glyphicon-floppy-disk" style="margin-right: .8em;"></span>保存
			 </button>
   </div>
     
     
 
	<script type="text/javascript" src="<@full_path path='js/lib/ckeditor/ckeditor.js'/>" ></script>	
	<script type="text/javascript" src="<@full_path path='js/backstage/wapinfo/service.js'/>" ></script>	
	<script type="text/javascript" src="<@full_path path='js/backstage/wapinfo/list.js'/>" ></script>	
  </body>
</html>
