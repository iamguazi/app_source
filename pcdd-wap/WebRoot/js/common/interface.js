
;( function ( window, undefined ) {
	
	

    "use strict";

     // 对浏览器的UserAgent进行正则匹配，不含有微信独有标识的则为其他浏览器
//    var useragent = navigator.userAgent;
//    if (useragent.match(/MicroMessenger/i) != 'MicroMessenger') {
//        // 这里警告框会阻塞当前页面继续加载
//        alert('已禁止本次访问：您必须使用微信内置浏览器访问本页面！');
//        // 以下代码是用javascript强行关闭当前页面
//        var opened = window.open('about:blank', '_self');
//        opened.opener = null;
//        opened.close();
//    }
    /**
     * 定义接口对象
     * 接口定义命名写死 "Interface.siguo." + 接口地址的方法名，参照demo
     * 接口定义注释简单的接口说明
     * @constructor
     */
    var Interface = { 
        /**
         * 服务器地址
         */
        host: {
        		dong : "http://xxx:50001/"
        },
        /**
         * 模块对象
         */
        dong: {}
    };

    window["Interface"] = Interface;


    // ************************************ 以下为接口定义 *************************************
    
    
    Interface.dong.noticeDetails = Interface.host.dong + "pcdd-api-client-interface/" + "notice/details";
    
    Interface.dong.bannerDetails = Interface.host.dong + "pcdd-api-client-interface/" + "banner/details";
    
    //开奖记录列表接口
    Interface.dong.getOpenList = Interface.host.dong + "pcdd-api-client-interface/" + "wap/open/log";
  
     //回水接口
    Interface.dong.gethuishuiList = Interface.host.dong + "pcdd-api-client-interface/" + "wap/huishui";
  
       //wap详情
    Interface.dong.wapDetails = Interface.host.dong + "pcdd-api-client-interface/" + "wap/details";
  
     
    Interface.dong.register = Interface.host.dong + "pcdd-api-client-interface/" + "user/register";
  
     
     
     
} ( window ) );