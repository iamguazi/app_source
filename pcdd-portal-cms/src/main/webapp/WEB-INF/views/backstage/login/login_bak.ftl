<!DOCTYPE html>
<html>
  <head>
    <meta charset="utf-8">
    <title>登录</title>
    <meta content="IE=edge,chrome=1" http-equiv="X-UA-Compatible">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
     <link href="<@full_path path='js/lib/bootstrap-3.3.5/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<@full_path path='css/index.css'/>" rel="stylesheet">
	<link href="<@full_path path='js/lib/cover/jquery.cover.css'/>" rel="stylesheet">
		
	<script type="text/javascript" src="<@full_path path='js/lib/jquery-1.11.1.min.js'/>" ></script>
    <script type="text/javascript" src="<@full_path path='js/lib/jquery.validate.min.js'/>" ></script>
	<script type="text/javascript" src="<@full_path path='js/lib/cover/jquery.cover.js'/>" ></script>
    <!-- Demo page code -->

    <style type="text/css">

				.btn {
					   color: #fff;
						-webkit-border-radius: 0;
						-ms-border-radius: 0;
						-moz-border-radius: 0;
						border-radius: 0;
						outline:none;
				}
				.btn:focus,.btn:hover,.btn:active {
					 background-color: #414959;  
			     border-color: #414959;
						outline:none  !important;
				}
				
        .btn-purple-grey  {
        	color:#ffffff;
        	background-color: #414959;
				  background-image: -webkit-gradient(linear, 0 0, 0 100%, from(#6c7994), to(#414959));
				  background-image: -webkit-linear-gradient(top, #6c7994, #414959);
				  background-image: -o-linear-gradient(top, #6c7994, #414959);
				  background-image: linear-gradient(to bottom, #6c7994, #414959);
				  background-image: -moz-linear-gradient(top, #6c7994, #414959);
				  filter: progid:dximagetransform.microsoft.gradient(startColorStr='#6c7994', EndColorStr='#414959');
				  -ms-filter: "progid:DXImageTransform.Microsoft.gradient(startColorStr='#6c7994',EndColorStr='#414959')";
				  border: 1px solid #363d4a;
				  outline:none;
        }
        
        
				
				.btn-purple-grey:focus,.btn-purple-grey:hover,.btn-purple-grey:active {
				   color: #ffffff;  
			     background-color: #414959;  
			     border-color: #414959;
			     outline:none;  
				  *background-color: #414959;
				}
				
				.error {
					color: #a94442;
				}

    </style>
<script type="text/javascript">
$(function(){

	 $.extend($.validator.messages, {
	            required    : "  必填",
	            remote      : "  请修正字段",
	            email       : "  必须为合法的电子邮件",
	            url         : "  必须为合法的网址",
	            date        : "  必须为合法的日期格式",
	            dateIOS     : "  必须为合法的日期格式(ISO)",
	            number      : "  必须为合法的数字",
	            digits      : "  必须为整数",
	            creditcard  : "  必须为合法的信用卡号",
	            equalTo     : "  两次输入的不同",
	            accept      : "  只允许合法后缀名的字符串",
	            maxlength   : $.validator.format("  必须是一个 长度最多是 {0} 的字符串"),
	            minlength   : $.validator.format("  必须是一个 长度最少是 {0} 的字符串"),
	            rangelength : $.validator.format("  必须是一个长度介于 {0} 和 {1} 之间的字符串"),
	            range       : $.validator.format("  必须是一个介于 {0} 和 {1} 之间的值"),
	            max         : $.validator.format("  必须是一个最大为{0} 的值"),
	            min         : $.validator.format("  必须是一个最小为{0} 的值")
	        } );
	        
	            var $form = $( "#form" );
	            var $errorForm = $form.find( ".errorForm" );
	            var opts = {
	            	errorElement: "span", 
	                highlight: function( element ) {
	                    jQuery( element ).closest( '.form-group' )
	                        .removeClass( 'has-success' )
	                        .addClass( 'has-error' );
	                },
	                success: function( element ) {
	                    jQuery( element ).closest( '.form-group' )
	                        .removeClass( 'has-error' ).addClass("has-success");
	                }
	            };
	            if ( $errorForm.length ) {
	            	opts.errorLabelContainer = $errorForm;
	            } else {
	            	opts.errorPlacement = function( $error, $element ) {
	                    //error是错误提示元素span对象  element是触发错误的input对象
	                    //发现即使通过验证 本方法仍被触发
	                    //当通过验证时 error是一个内容为空的span元素
	            		
	            		var $parent = $element.parent( ".input-group" );
	            		
	            		$error.text( $error.text() );
	            		if ( $parent && $parent.length ) {
	            			$error.insertAfter( $parent );        
	            		} else {
	            			$error.insertAfter( $element );            			
	            		}
	                }
	            }
	            
	            $form.validate( $.extend(true,opts,
	            		{
							rules : {
								name : {
									required : true
								},
								password: {
									required : true
								}
							},
							messages : {
								  name : {
									required : "用户必填",
								  },
								  password: {
									required : "密码必填"
								  }
							 }
	                    } 
	            ));

});

	        
function login(){

		if($("#form").length>0){
				if(!$("#form").valid()){
					return;
				}
			}
	$("body").cover("正在登录中......");		
	$.ajax({
		url:'<@full_path path="admin/login"/>',
		type:"POST",
		data : "password="+$("#psw").val()+"&name="+$("#user").val(),
		dataType:"json",
		success:function(data){
		    if(data.result_code==1){
				top.location.href='<@full_path path="backstage/index"/>'+"?time"+new Date().getTime();
		    }else{
		    	$("#errorMessage").empty().text(data.result_msg).show();
		    }
		    $("body").uncover();
		},
		error:function(){
			$("#errorMessage").empty().text("系统异常").show();
			$("body").uncover();
		}
	
	});

}

</script>
    
  </head>
<body>
    
    <div class="navbar">
        <div class="navbar-inner" style="min-height:60px;">
             
                <a class="brand" href="javascript:void(0);" style="line-height:60px;"><span class="first" style="font-size:1.6em;">微码</span> <span class="second" style="font-size:1.6em;">直播</span></a>
        </div>
    </div>

    <div class="row" style="width:100%">
   		<div style="width: 400px;margin: 0 auto;margin-top: 5em;border: 1px solid #ccc;background-color: #fff;">
   			<p style="color: #505050; background-color:#dddddd;background: -ms-linear-gradient(bottom, #dddddd, #fafafa); background: rgba(0, 0, 0, 0) -moz-linear-gradient(center bottom , #dddddd 0%, #fafafa 100%) repeat scroll 0 0 ; background: -webkit-gradient(linear, left bottom, left top, color-stop(0, #dddddd), color-stop(1, #fafafa));padding-left: 1em; display: block;outline : none;line-height: 3em; font-size: .85em; font-weight: bold;border-bottom: 1px solid #a6a6a6;" >登录</p>
   			
   			<div style="background-color: #fff; margin: 1em;">
   				<form id="form" class="sh-validate">
	   				<div class="form-group">
					    <label style="font-weight:normal;" class="control-label">用户名</label>
					    <input id="user" type="text"  name="name" class="form-control" style="border-radius: 0;" placeholder="用户名">
					  </div>
					  <div class="form-group">
					    <label style="font-weight:normal;" class="control-label">密码</label>
					    <input id="psw" type="password"  name="password" class="form-control" style="border-radius: 0;" placeholder="密码">
					  </div>
	   				<button type="button" class="btn btn-purple-grey btn-block btn-sm" onclick="login()" >登录</button>
	   				<div id="errorMessage" style="color: #a94442; text-align: center;padding: 1em; font-size: 18px;display: none;"></div>
   			  </form>
   			</div>
   		</div>
    </div>
</div>
  </body>
</html>
