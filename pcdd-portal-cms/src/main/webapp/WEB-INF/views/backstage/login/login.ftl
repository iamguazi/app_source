<!DOCTYPE html>
<!-- saved from url=(0036)http://47.90.89.147:50001/gameMouse/ -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>欢迎登陆后台管理系统</title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<style>
	html,body,div,ul,li,dl,dt,dd,ul,ol,li,h1,h2,h3,h4,h5,h6,pre,form,fieldset,input,textarea,blockquote,p{padding:0;margin:0;}
	ul,li{list-style: none}
	body{font:12px/1.6 "\5B8B\4F53", Arail;}
	/* login box */
	.login-box {width:340px;height:440px;padding:22px 40px;margin:40px auto 0 auto; background:url("<@full_path path='img/login/login_bg.gif'/>") no-repeat;}
	.login-logo {height:36px;padding:10px;margin:40px auto 10px auto;background:url("<@full_path path='img/login/login_logo.gif' />") no-repeat center top;
	-webkit-animation:zoomInDown 1.5s 0s ease-out both;
	-moz-animation:zoomInDown 1.5s 0s ease-out both;
	-o-animation:zoomInDown 1.5s 0s ease-out both;
	animation:zoomInDown 1.5s 0s ease-out both;
	}
	.line{position:relative;height:0;margin-top:40px;border-bottom:1px solid #1f87c6;}
	.login-title {position:absolute;left:104px;top:-14px;font-family:"microsoft yahei";font-size:18px;padding:0 10px;color:#1f87c6;text-align:center;background:#fff;}
	.login-form{margin-top:40px;}
	.login-form ul li{height:42px;}
	.login-form ul li input{float:left;height:28px;line-height:28px;padding:7px 4px;border:0 none;font-family:"microsoft yahei";outline:none;}
	.login-form i{display:inline-block; float:left;width:16px;height:42px; margin:0 10px;background:url("<@full_path path='img/login/login_icons.png'/>") no-repeat;}
	.user{border:1px solid #ddd;border-radius:2px 2px 0 0;}
	.user i{background-position: 0 12px;}
	.user input,.password input{width:290px;}
	.password{border-left:1px solid #ddd;border-right:1px solid #ddd;border-bottom: 1px solid #ddd;}
	.password i{background-position: -26px 12px;}
	.code{border:1px solid #ddd;border-radius:0 0 2px 2px;}
	.code i{background-position: -52px 12px;}
	.code a{float:right;display:block;width:85px;height:27px; margin:0 8px; cursor:pointer;}
	.login-btn {display:inline-block;width:100%;height:42px;line-height:42px;margin:20px 0;border-radius:4px;color:#fff;font-size:14px;font-family:"microsoft yahei";background:#1f87c6;text-align:center;cursor:pointer;transition:background .3s linear 0s; overflow:hidden;}
	.login-btn:hover{background:#2797da;}
	.rember-password{margin-top:40px;}
	.rember-password  input{float:left; }
	.rember-password  label{float:left;padding-left:5px;margin-top:-3px;}
	/**.footer{height:32px;margin-top:20px; line-height:32px;text-align:center; color:#0c3c59;}**/
	
	.messager-tip{display:none;overflow: hidden;margin-top:8px;max-height:50px; line-height:24px;padding:0 6px; text-indent:24px; border:1px solid #ffd2d2;background:#fff0f0 url("<@full_path path='/img/login/error.png'/>") no-repeat 4px 4px;}
	
	@keyframes zoomInDown {
	  from { opacity: 0; -webkit-transform: scale3d(.1, .1, .1) translate3d(0, -1000px, 0); transform: scale3d(.1, .1, .1) translate3d(0, -1000px, 0); -webkit-animation-timing-function: cubic-bezier(0.550, 0.055, 0.675, 0.190); animation-timing-function: cubic-bezier(0.550, 0.055, 0.675, 0.190);
	  }
	
	  60% { opacity: 1;-webkit-transform: scale3d(.475, .475, .475) translate3d(0, 60px, 0); transform: scale3d(.475, .475, .475) translate3d(0, 60px, 0); -webkit-animation-timing-function: cubic-bezier(0.175, 0.885, 0.320, 1);animation-timing-function: cubic-bezier(0.175, 0.885, 0.320, 1);}
	}

    .footer {
        position: fixed; 
        height: 20px;
        line-height: 20px;
        clear:both;
        text-align: center;
        bottom:10px;
        width: 100%;
        font-size: 14px;
    }
</style>
<link href="<@full_path path='js/lib/cover/jquery.cover.css'/>" rel="stylesheet">
<script type="text/javascript" src="<@full_path path='js/lib/jquery-1.11.1.min.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/jquery.validate.min.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/cover/jquery.cover.js'/>" ></script>
</head>
<body style="background:#1f87c6;">
<div class="login-box">
  <div class="login-logo"></div>
  <div class="line">
    <p class="login-title">后台管理系统</p>
  </div>
  <div class="login-form">
    <form id="login" method="post" action="http://47.90.89.147:50001/gameMouse/login">
      <ul>
        <li class="user clearfix"><i></i>
          <input id="userName" name="userName" type="text" placeholder="请输入用户名" autocomplete="off">
        </li>
        <li class="password clearfix"><i></i>
          <input id="password" name="password" type="password" placeholder="请输入密码" autocomplete="off">
        </li>
        <li id="errorLi" style="display:none;">
        	<div class="messager-tip">请输入用户名</div>
        </li>
        <li><a id="submitBtn" class="login-btn" onclick="login()" >登录</a></li>
      </ul>
      <!--<p class="rember-password clearfix">
        <input id="checked" type="checkbox">
        <label for="checked">记住密码</label>
      </p>-->
    </form>
  </div>
</div>


<script type="text/javascript">
   function login(){

	$("body").cover("正在登录中......");		
	$.ajax({
		url:'<@full_path path="admin/login"/>',
		type:"POST",
		data : "password="+$("#password").val()+"&name="+$("#userName").val(),
		dataType:"json",
		success:function(data){
		    if(data.result_code==1){
				top.location.href='<@full_path path="backstage/index"/>'+"?time"+new Date().getTime();
		    }else{
		    	$("#errorLi").show();
		    	$(".messager-tip").text(data.result_msg).show();
		    }
		    $("body").uncover();
		},
		error:function(e){
			$("#errorLi").show();
		    $(".messager-tip").text("服务器异常").show();
			$("body").uncover();
		}
	
	});

}

    $(function(){
      $("body").keydown(function(event) {
             if (event.keyCode == "13") {//keyCode=13是回车键
                 login();
             }
       });
   });
</script>

 <#include "/views/backstage/common/footer.ftl"/>
</body></html>