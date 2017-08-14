
;(function (validator) {
	
	jQuery.validator.addMethod("isChinese", function(value, element) {       
		return this.optional(element) || /^[\u0391-\uFFE5]+$/.test(value);       
	}, "只能包含中文字符。");
	
	jQuery.validator.addMethod("isMobile", function(value, element) {    
	    var length = value.length;    
	    return this.optional(element) || (length == 11 && /^1[3|4|5|7|8]\d{9}$/.test(value));    
	}, "请正确填写您的手机号码。");
	
	jQuery.validator.addMethod("isPhone", function(value, element) {    
	    var tel = /^(\d{3,4}-?)?\d{7,9}$/g;    
	    return this.optional(element) || (tel.test(value));    
	}, "请正确填写您的电话号码。");
	
	jQuery.validator.addMethod("isTel", function(value,element) {   
        var length = value.length;   
        var mobile = /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/;   
        var tel = /^(\d{3,4}-?)?\d{7,9}$/g;       
        return this.optional(element) || tel.test(value) || (length==11 && mobile.test(value));   
    }, "请正确填写您的联系方式"); 
	
	jQuery.validator.addMethod("isQq", function(value, element) {       
        return this.optional(element) || /^[1-9]\d{4,12}$/;       
	}, "匹配QQ");
	
	jQuery.validator.addMethod("isZipCode", function(value, element) {    
	    var zip = /^[0-9]{6}$/;    
	    return this.optional(element) || (zip.test(value));    
	}, "请正确填写您的邮政编码。");
	
	jQuery.validator.addMethod("isAccount", function(value, element) {       
		return this.optional(element) || /^[a-zA-Z]\w{5,12}$/.test(value);       
	}, "以字母开头，长度在6-12之间，只能包含字符、数字和下划线。");
	
	jQuery.validator.addMethod("isLetterAndNum", function(value, element) {       
		return this.optional(element) || /^[a-zA-Z0-9]{6,12}$/.test(value);       
	}, "只能包含字符、数字和下划线。");
	
	jQuery.validator.addMethod("isPwd", function(value, element) {       
        return this.optional(element) || /^[a-zA-Z]\w{5,12}$/.test(value);       
	}, "以字母开头，长度在6-12之间，只能包含字符、数字和下划线。");
	
	jQuery.validator.addMethod("isLettersOrNum", function(value, element) {       
		return this.optional(element) || /^[a-zA-Z]*\w*$/.test(value);       
	}, "只能包含字符、数字。");
	
	jQuery.validator.addMethod("isStartLettersOrIncloudNum", function(value, element) {       
		return this.optional(element) || /^[a-zA-Z]+\w*$/.test(value);       
	}, "字符、数字组合且以字母开头");
	
	jQuery.validator.addMethod("isCurrency", function(value, element) {       
		return this.optional(element) || /^\d+(\.\d{1,2})?$/.test(value);       
	}, "请输入正确的货币值。");
	
	jQuery.validator.addMethod("isCurrencyofTwoDecimal", function(value, element) {       
		return this.optional(element) || /^\d+$/.test(value) || /^\d+\.\d{1,2}$/.test(value);       
	}, "请输入合法货币值。");

	jQuery.validator.addMethod("isNomalCurrencyofTwoDecimal", function(value, element) {       
		return this.optional(element) || /^(-)?\d+$/.test(value) || /^(-)?\d+\.\d{1,2}$/.test(value);       
	}, "请输入合法货币值。");
	
	jQuery.validator.addMethod("isBili", function(value, element) { 
		//var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;   
		return this.optional(element) ||  /^0\.\d{1,2}$/.test(value) || value == 0;    
	}, "请输入正确的比例。");
	jQuery.validator.addMethod("isBigger", function(value, element,params) { 
		//var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;
		if( !value && value != 0 ){
			return this.optional(element) || false;
		}
		
		var compareValue = $(params).val();
		var comparaResult = false;
		if (compareValue || compareValue == 0){
			if(isNaN(compareValue)){
				comparaResult = true;
			}else{
				comparaResult = +value > +compareValue;
			}
		}
		
		return this.optional(element) || comparaResult;    
	}, "输入的值偏小。");
	
	jQuery.validator.addMethod("isSmaller", function(value, element,params) {
		if( !value && value != 0 ){
			return this.optional(element) || false;
		}
		
		var compareValue = $(params).val();
		var comparaResult = false;
		if (compareValue || compareValue == 0){
			if(isNaN(compareValue)){
				comparaResult = true;
			}else{
				comparaResult = +value < +compareValue;
			}
		}
		
		return this.optional(element) || comparaResult; 
	}, "输入的值偏大。");
	jQuery.validator.addMethod("isInvalChar", function(value, element) {       
		return this.optional(element) || /^[^\|\;]+$/g.test(value);       
	}, "不能包含非法字符。");
	
	jQuery.validator.addMethod("isIdCardNo", function(value, element) { 
	    //var idCard = /^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/;   
	    return this.optional(element) || isIdCardNo(value);    
	}, "请输入正确的身份证号码。");
	
	jQuery.validator.addMethod("ip", function(value, element) {    
	    return this.optional(element) || /^(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.)(([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))\.){2}([1-9]|([1-9]\d)|(1\d\d)|(2([0-4]\d|5[0-5])))$/.test(value);    
	}, 

	 // 身份证号码的验证规则
    function isIdCardNo(num){ 
	    　   // if (isNaN(num)) {alert("输入的不是数字！"); return false;}
	    　　 var len = num.length, re; 
	    　　 if (len == 15){ 
	    　　 	re = new RegExp(/^(\d{6})()?(\d{2})(\d{2})(\d{2})(\d{2})(\w)$/); 
	    　　 }else if (len == 18){
	    	 re = new RegExp(/^(\d{6})()?(\d{4})(\d{2})(\d{2})(\d{3})(\w)$/);	 
	    　　 } else {
	        // alert("输入的数字位数不对。");
	        return false;
	    } 
	    　　 var a = num.match(re); 
	    　　 if (a != null) { 
		    　　 if (len==15)  { 
			    　　 var D = new Date("19"+a[3]+"/"+a[4]+"/"+a[5]); 
			    　　 var B = D.getYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
		    　　 } else { 
			    　　 var D = new Date(a[3]+"/"+a[4]+"/"+a[5]); 
			    　　 var B = D.getFullYear()==a[3]&&(D.getMonth()+1)==a[4]&&D.getDate()==a[5]; 
		    　　 } 
	    　　 	if (!B) {
	            // alert("输入的身份证号 "+ a[0] +" 里出生日期不对。");
	            return false;
	        } 
	    　　 } 
	    　　 if(!re.test(num)){
	        // alert("身份证最后一位只能是数字和字母。");
	        return false;
	    }
	    　　 return true; 
    })
	
}(jQuery.validator, window ))