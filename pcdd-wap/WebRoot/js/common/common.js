/**
 * Created by pan.wang on 2015-06-19.
 */
function showPop() {
    var t = $(document).height();

    if ($('#pop-share').length <= 0) {
        $('body').append(' <div class="mod-pop" id="pop-share" onclick="hidePop(\'#pop-share\')"> <span class="text-share"></span> </div>');
    };
    var $obj=$('#pop-share');
    $($obj).height(t).fadeIn();
}
function hidePop(e) {
    $(e).fadeOut()
}

$.fn.tips=function(content){
    var tips=$(this);
    $(tips).append('<div class="ui-poptips ui-poptips-success am-animation-fade" > <div class="ui-poptips-cnt"><i></i>'+content+'</div> </div>');
    setTimeout(function(){$("div.ui-poptips").remove();},2000);
}



//用来配置alert是否弹出
//true--可以弹出  false--不可弹出
var is_alert_message = true;
var is_defined_alert_function = true; //是否使用自定义的alert弹出方式  

//重写alert，使其可以配置
var old_alert = window.alert;
window.alert = function(msg){
	if(is_alert_message == true){
		if(is_defined_alert_function == true){
			$("body").tips(msg);
		} else {
			old_alert(msg);			
		}
	} else {
		return;
	}
};


window.subContent = function(content, sLength){
	if(content === undefined || content == null || content == ""){
		return "";
	}
	var length = content.length;
	if(length >= sLength){
		return content.substr(0, sLength) + "……";
	}
	return content;
}


var App = function(){
	var TOKEN = "AO_RMB";
	// IE mode
    var isRTL = false;
    var isIE8 = false;
    var isIE9 = false;
    var isIE10 = false;
    var cookieData = {};
    var pageSize = "20"; //分页大小
    var loginUrl = "";
    var projectName = "";
    
    
    var handleInit = function(){
		isIE8 = !! navigator.userAgent.match(/MSIE 8.0/);
	    isIE9 = !! navigator.userAgent.match(/MSIE 9.0/);
	    isIE10 = !! navigator.userAgent.match(/MSIE 10.0/);
	    pageSize = "20";
	    //登入的路径
	    projectName = "aomenfangchan";
	    loginUrl = "/" + projectName + "/views/module/client/login/login.html";
    }
    
    
	var isLogin = function(){
		var rmb = $.cookie(TOKEN);
		if(typeof(rmb) === "undefined" || rmb == null || rmb == "" || rmb == "null"){
			cookieData = {};
			return false;
		}
		cookieData = JSON.parse(rmb);
		var user_id = cookieData["user_id"];
		if( typeof(user_id) === "undefined" || user_id == null || user_id == ""){
			return false;
		}
		return true;
	}
	
	
	
	/** 
	 * 对日期进行格式化， 
	 * @param date 要格式化的日期   时间戳
	 * @param format 进行格式化的模式字符串
	 *     支持的模式字母有： 
	 *     y:年, 
	 *     M:年中的月份(1-12), 
	 *     d:月份中的天(1-31), 
	 *     h:小时(0-23), 
	 *     m:分(0-59), 
	 *     s:秒(0-59), 
	 *     S:毫秒(0-999),
	 *     q:季度(1-4)
	 * @return String
	 * @author 
	 */
	var dateFormat = function (date, format) {
	    if (typeof date === "string") {
	        var mts = date.match(/(\/Date\((\d+)\)\/)/);
	        if (mts && mts.length >= 3) {
	            date = parseInt(mts[2]);
	        }
	    }
	    date = new Date(date);
	    if (!date || date.toUTCString() == "Invalid Date") {
	        return "";
	    }
	    var map = {
	        "M": date.getMonth() + 1, //月份 
	        "d": date.getDate(), //日 
	        "h": date.getHours(), //小时 
	        "m": date.getMinutes(), //分 
	        "s": date.getSeconds(), //秒 
	        "q": Math.floor((date.getMonth() + 3) / 3), //季度 
	        "S": date.getMilliseconds() //毫秒 
	    };
	    
	    format = format.replace(/([yMdhmsqS])+/g, function(all, t){
	        var v = map[t];
	        if(v !== undefined){
	            if(all.length > 1){
	                v = '0' + v;
	                v = v.substr(v.length-2);
	            }
	            return v;
	        }
	        else if(t === 'y'){
	            return (date.getFullYear() + '').substr(4 - all.length);
	        }
	        return all;
	    });
	    return format;
	}
	
	//yyyy-MM-dd时间的比较
	var yyyymmddDuibi = function(begin, end){
		var arr = begin.split("-");
	    var starttime = new Date(arr[0], arr[1], arr[2]);
	    var starttimes = starttime.getTime();
	    var arrs = end.split("-");
	    var endtime = new Date(arrs[0], arrs[1], arrs[2]);
	    var endtimes = endtime.getTime();
	    if (starttimes > endtimes) {
	        return false;
	    }
	    return true;
	}

	
	
	var _trim = function(v){
		if (!v) return v;
        return v.replace(/^\s+/g, '').replace(/\s+$/g, '');
	}

	
	var subContent = function(content, sLength){
		if(content === undefined || content == null || content == ""){
			return "";
		}
		var length = content.length;
		if(length >= sLength){
			return content.substr(0, sLength) + "……";
		}
		return content;
	}
	
	
	
	return {
		//初始化
		init : function(){
			handleInit();
		},
		
		// check IE8 mode
        isIE8: function () {
            return isIE8;
        },

        // check IE9 mode
        isIE9: function () {
            return isIE9;
        },
        
        isIE10 : function(){
        	return isIE10;
        },
        
        checkLogin : function(){
        	return isLogin();
        },
        
        loginUrl : function(){
        	return loginUrl;
        },
        
        dateFormat : function(date, format){
        	return dateFormat(date, format);
        },
        
        isPassword : function(value){
        	//只能是6-12个字母或数字
        	return (/[0-9a-zA-Z]{6,12}/).test(_trim(value));
        },
        
        isEmpty : function(value){
    		return !!_trim(value);
        },
        
        _trim : function(value) {
        	return _trim(value);
        },
        
        isNumber : function(value) {
        	value = _trim(value);
        	return (/^\d+(.\d*)?$/).test(value);
        },
        getCookeData : function(){
        	return cookieData;
        },
        
        getPageSize : function(){
        	return pageSize;
        },
        yyyymmddDuibi : function (begin, end) {
        	return yyyymmddDuibi(begin, end);
        },
        subContent : function(content, sLength){
        	if(sLength == undefined || sLength == null || sLength == ""){
        		sLength = 15;
        	}
        	return subContent(content, sLength);
        },
        
	};
}();


/**
 ** 加法函数，用来得到精确的加法结果
 ** 说明：javascript的加法结果会有误差，在两个浮点数相加的时候会比较明显。这个函数返回较为精确的加法结果。
 ** 调用：accAdd(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
function accAdd(arg1, arg2) {
    var r1, r2, m, c;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    c = Math.abs(r1 - r2);
    m = Math.pow(10, Math.max(r1, r2));
    if (c > 0) {
        var cm = Math.pow(10, c);
        if (r1 > r2) {
            arg1 = Number(arg1.toString().replace(".", ""));
            arg2 = Number(arg2.toString().replace(".", "")) * cm;
        } else {
            arg1 = Number(arg1.toString().replace(".", "")) * cm;
            arg2 = Number(arg2.toString().replace(".", ""));
        }
    } else {
        arg1 = Number(arg1.toString().replace(".", ""));
        arg2 = Number(arg2.toString().replace(".", ""));
    }
    return (arg1 + arg2) / m;
}




//给Number类型增加一个add方法，调用起来更加方便。
Number.prototype.add = function (arg) {
    return accAdd(arg, this);
};
		  
/**
 ** 减法函数，用来得到精确的减法结果
 ** 说明：javascript的减法结果会有误差，在两个浮点数相减的时候会比较明显。这个函数返回较为精确的减法结果。
 ** 调用：accSub(arg1,arg2)
 ** 返回值：arg1加上arg2的精确结果
 **/
function accSub(arg1, arg2) {
    var r1, r2, m, n;
    try {
        r1 = arg1.toString().split(".")[1].length;
    }
    catch (e) {
        r1 = 0;
    }
    try {
        r2 = arg2.toString().split(".")[1].length;
    }
    catch (e) {
        r2 = 0;
    }
    m = Math.pow(10, Math.max(r1, r2)); //last modify by deeka //动态控制精度长度
    n = (r1 >= r2) ? r1 : r2;
    return ((arg1 * m - arg2 * m) / m).toFixed(n);
}

// 给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.sub = function (arg) {
    return accSub(this, arg);
};

/**
 ** 乘法函数，用来得到精确的乘法结果
 ** 说明：javascript的乘法结果会有误差，在两个浮点数相乘的时候会比较明显。这个函数返回较为精确的乘法结果。
 ** 调用：accMul(arg1,arg2)
 ** 返回值：arg1乘以 arg2的精确结果
 **/
function accMul(arg1, arg2) {
    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
    try {
        m += s1.split(".")[1].length;
    }
    catch (e) {
    }
    try {
        m += s2.split(".")[1].length;
    }
    catch (e) {
    }
    var num=Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
    return num.toFixed(2);
}

// 给Number类型增加一个mul方法，调用起来更加方便。
Number.prototype.mul = function (arg) {
    return accMul(arg, this);
};


/***拼接url
**/
function parse_forward_url(targetLocation, paramObject, removeEmpty){
	var type = $.type( targetLocation ),
         factorIndex = -1,
         name,
         paramStr = "";
    if ( type !== "string" ) {
        throw new TypeError( "targetLocation 必须是string类型，实际上是 " + type );
    }
    removeEmpty = !!removeEmpty;
    factorIndex = targetLocation.indexOf( "?" );
    type = $.type( paramObject );
    if ( type === "object" ) {
        for( name in paramObject ) {
            if ( paramObject.hasOwnProperty( name ) ) {
                if ( paramObject[name] == null ) {
                    if ( !removeEmpty ) {
                        paramStr += "&" + name + "=";
                    }
                } else {
                    paramStr += "&" + name + "=" + paramObject[name];
                }
            }
        }
    } else {
        paramStr = "&" + paramObject;
    }
    if ( paramStr !== "" ) {
        if ( factorIndex === -1 ) {
            targetLocation = targetLocation + "?" + paramStr.substring( 1 );
        } else if ( factorIndex === targetLocation.length - 1 ) {
            targetLocation = targetLocation + paramStr.substring( 1 );
        } else {
            targetLocation = targetLocation + paramStr;
        }
    }
    return encodeURI( targetLocation );
}