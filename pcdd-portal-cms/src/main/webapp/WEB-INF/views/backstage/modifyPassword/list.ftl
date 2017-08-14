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
    
    <style>
    	.form-required {
    		color: red;
		}
		
		.btn-primary {
		    background-color: #1FB5AD;
		    color: #fff;
		    border-color: #1fb5ad;
		}
		
		.form-horizontal .control-label {
		    font-weight: 100;
		    text-align: right;
		}
		
		.form-control {
    		border-radius: 3px; 
		}

		.btn-submit {
			display: none !important;
		}
    </style>
  </head>
  <body style="background-color: #f1f4fb;">
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
   <div class="container" style="width:100%;">   
	    <div class="row">
		  <!--栏目路径-->
		  <div class="header">
		    <h1 class="page-title">修改密码</h1>
		  </div>
		  <ul class="breadcrumb">
		    <li><a href="javascript:void(0);">修改密码</a> <span class="divider">/</span></li>
		    <li class="active">修改密码</li>
	      </ul>
	    </div>
	    
		<div class="row" style="padding: 20px;">
			<div class="panel-group" id="accordion">
				<div class="panel panel-info">
					<div class="panel-heading">
						<h4 class="panel-title">
							<a data-toggle="collapse" data-parent="#accordion" 
							   href="#collapseOne">
								<h3>修改密码</h3>
							</a>
						</h4>
					</div>
					<div id="collapseOne" class="panel-collapse collapse in">
						<div class="panel-body">
							<form class="form-horizontal sh-validate" id="form">
				                
				                <div class="form-group">
				                    <label class="col-sm-2 control-label">原密码：<span class="form-required">*</span></label>
				                    <div class="col-sm-4">
				                        <input type="password" name="oldPassword" placeholder="原密码" class="form-control" required maxlength="20" data-bind="textinput: model.data.oldPassword"/>
				                    </div>
	                			</div>
	                
				                <div class="form-group">
				                    <label class="col-sm-2 control-label">新密码：<span class="form-required">*</span></label>
				                    <div class="col-sm-4">
				                        <input type="password" id="newPassword" name="newPassword" placeholder="新密码" class="form-control" required maxlength="20" data-bind="textinput: model.data.newPassword"/>
				                    </div>
				                </div>
				                
				                <div class="form-group">
				                    <label class="col-sm-2 control-label">重复新密码：<span class="form-required">*</span></label>
				                    <div class="col-sm-4">
				                        <input type="password" name="reNewPassword" placeholder="重复新密码" class="form-control" required maxlength="20" data-bind="textinput: model.data.reNewPassword"/>
				                    </div>
				                </div>
				                
				                <div class="form-group">
				                    <div class="col-sm-offset-2 col-sm-4">
				                        <button type="submit" class="btn btn-primary btn-lg btn-block" data-bind="click: $root.resetPassword">确定</button>
				                    </div>
				                </div>
							</form>
						</div>
					</div>
				</div>
			</div>
		</div>
   </div>
     
	<script type="text/javascript" src="<@full_path path='js/backstage/modifyPassword/list.js'/>" ></script>	
  </body>
</html>
