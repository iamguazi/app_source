;( function () {
    "use strict";
    var param_map=Result.getLocationParamObject();
     var artistInfo;
     var WXConfigParam=null;
    var localUrl=window.location.href.split("?")[0];
     var timerc=60; //全局时间变量（秒数）
     var flag=true;//是否可以更新接单状态
     
     //用来配置alert是否弹出
    //true--可以弹出  false--不可弹出
    var is_alert_message = true;
    var is_defined_alert_function = true; //是否使用自定义的alert弹出方式     
     	
 
	 $( function () {
 		 WXConfigParam=getMyWXConfig();
 		 if(WXConfigParam==null){
 			 return;
 		 }
	 	wx.config({
	      debug: false,
	      appId: WXConfigParam.app_id,
	      timestamp: parseInt(WXConfigParam.timestamp),
	      nonceStr: WXConfigParam.nonce_str,
	      signature: WXConfigParam.signature,
	       jsApiList: [
	        'checkJsApi',
	        'onMenuShareTimeline',
	        'onMenuShareAppMessage',
	        'onMenuShareQQ',
	        'onMenuShareWeibo',
	        'hideMenuItems',
	        'showMenuItems',
	        'hideAllNonBaseMenuItem',
	        'showAllNonBaseMenuItem',
	        'translateVoice',
	        'startRecord',
	        'stopRecord',
	        'onRecordEnd',
	        'playVoice',
	        'pauseVoice',
	        'stopVoice',
	        'uploadVoice',
	        'downloadVoice',
	        'chooseImage',
	        'previewImage',
	        'uploadImage',
	        'downloadImage',
	        'getNetworkType',
	        'openLocation',
	        'getLocation',
	        'hideOptionMenu',
	        'showOptionMenu',
	        'closeWindow',
	        'scanQRCode',
	        'chooseWXPay',
	        'openProductSpecificView',
	        'addCard',
	        'chooseCard',
	        'openCard'
	      ]
 		 });
        
         wx.error(function (res) { 
        	 getReWXConfig();
		});
	
		wx.ready(function () {
			wx.hideOptionMenu(); //分享到QQ: "menuItem:share:qq"
			wx.hideMenuItems({
			    menuList: ['menuItem:share:qq','menuItem:share:weiboApp',
			    	'menuItem:favorite','menuItem:share:facebook','menuItem:share:QZone'] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
			});
			wx.hideAllNonBaseMenuItem();
			wx.showMenuItems({
			    menuList: ['menuItem:share:appMessage','menuItem:share:timeline'] // 要隐藏的菜单项，只能隐藏“传播类”和“保护类”按钮，所有menu项见附录3
			});
			
				
			//分享朋友圈
			wx.onMenuShareTimeline({
				 title: '动起来', // 分享标题
				 link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxefc04dffc89285dc&redirect_uri=http%3A%2F%2F365.v-ma.net%2F365project%2Fviews%2Fmodule%2FforwardUrl.html%3FforwardUrl%3Dclient%2Findex%2Findex&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect', // 分享链接
			     imgUrl: 'http://365.v-ma.net/upload/logo/logo.png', // 分享图标
				 success: function () { 
			        // 用户确认分享后执行的回调函数
			        sharePoint();
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    }
			});
			
			
			//分享朋友
			wx.onMenuShareAppMessage({
			    title: '动起来', // 分享标题
				 link: 'https://open.weixin.qq.com/connect/oauth2/authorize?appid=wxefc04dffc89285dc&redirect_uri=http%3A%2F%2F365.v-ma.net%2F365project%2Fviews%2Fmodule%2FforwardUrl.html%3FforwardUrl%3Dclient%2Findex%2Findex&response_type=code&scope=snsapi_userinfo&state=STATE#wechat_redirect', // 分享链接
			     imgUrl: 'http://365.v-ma.net/upload/logo/logo.png', // 分享图标
				 success: function () { 
			        // 用户确认分享后执行的回调函数
			          sharePoint();
			    },
			    cancel: function () { 
			        // 用户取消分享后执行的回调函数
			    }
			});
			
				
				
	 	 });
 
	 });

	 
	
	
	window.getMyWXConfig=function(){
		var payObj={};
		payObj.is_time_out="0";
		payObj.url=localUrl;
		var param=null;
		 Result.doResult( {
			url: Interface.dong.getWXConfigParam,
			data: payObj,
            type: "POST",
			async:false
        }, function ( data ) {
        	param=data;
        }, function ( desc ) {
            alert( "fail: " + desc );
        }, function ( err ) {
            alert( "error2:" + JSON.stringify(err) );
        } );
		 return param;
	};
	
	
	
	 
    
	
	//刷新ticket
	window.getReWXConfig=function(){
		var payObj={};
		payObj.is_time_out="1";
		payObj.url=localUrl;
		var param=null;
		 Result.doResult( {
			url: Interface.dong.getWXConfigParam,
			data: payObj,
            type: "POST",
			async:false
        }, function ( data ) {
        	param=data;
        }, function ( desc ) {
            alert( "fail: " + desc );
        }, function ( err ) {
            alert( "error:" + JSON.stringify(err) );
        } );
		 return param;
	};
	
	

}( window ) );
