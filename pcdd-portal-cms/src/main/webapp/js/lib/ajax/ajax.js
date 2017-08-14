/**
 * Created by chennh on 2014/10/17 0017.
 */
;(function (window, $) {

    var Ajax = function () {

    };

    $.extend(true, Ajax, {
        setting: {
            ajax: {
                type: "POST",
                asyn: true,
                contentType: "application/x-www-form-urlencoded",
                data: null,
                url: "",
                dataType: "json",
                success: null,
                error: null,
                showError: true,
                autoCloseCover:true,
                cover: function () {
                	return $("body");
                }
            }
        },
        ajax: function (s) {
        	var that = this;
        	var success = s.success || $.noop();
        	var error = s.error || $.noop();
        	var $cover = s.cover !== false && $.fn.cover !== undefined && ( s.cover && s.cover() || this.setting.ajax.cover());
        	var showError = s.showError !== false;
        	if($cover) {
        		if(s.coverTitle){
        			$cover.cover(s.coverTitle);
        		}else{
        			$cover.cover();
        		}
        	}
        	
        	return this.doAjax($.extend(true, s,
        		{
        		success: function () {
//        			if($.isFunction(success)){
//        				success.apply(null, arguments);	
//        			}
                	if($cover && (s.autoCloseCover==undefined || s.autoCloseCover===true || s.autoCloseCover==="true")) {
                		$cover.uncover();
                	}
        		},
        		error: function (errorMsg) {
//        			if($.isFunction(error)){
//        				error.apply(null, arguments);	
//        			}
        			if($cover && s.autoCloseCover && (s.autoCloseCover==undefined || s.autoCloseCover===true || s.autoCloseCover==="true")) {
                		$cover.uncover();
                	}
//        			if(showError){
////        				that.showError(arguments[1], arguments[2]);
//        				that.showError(arguments[0], arguments[1]);
//        			}
        		}
        	}
        	));
        },
        doAjax: function (s) {
            s = $.extend(true, {}, Ajax.setting, s);
            return $.ajax(s);
        },
        getFormData: function ($form) {
            var data = {}
            if($form && $form.length && $form.length > 0){
                $form.find("[name]").each(function () {
                    var tagName = this.tagName.toLowerCase(), $this = $(this);
                    if("|input|select|textarea|".indexOf("|" + tagName + "|") > -1){
                        data[this.name] = $this.val();
                    }else{
                        data[this.name] = $this.text() || $this.html();
                    }
                });
            }
            return data;
        },
        showError: function (title, content) {
        	if ($.gritter) {
        		jQuery.gritter.add({
        			title: title || '警告',
                    text: content,
                    class_name: 'growl-danger',
                    image: 'images/screen.png',
                    sticky: true,
                    time: ''
        		});
        	} else {
        		alert(content);
        	}
        }
    });
    
    $.extend(true, Ajax, {
    	CODE: {
    		SUCCESS_DATA		: 10000,
    		SUCCESS_NODATA		: 10001,
    		PARAM_ERROR			: 10002,
    		SERVICE_EXCEPTION	: 10003,
    		SERVER_EXCEPTION	: 10004
    	}
    });
    
    $.fn.submitForm= function (s) {
	      return $(this).each(function () {
	          var $this = $(this),
	              data = Ajax.getFormData($this);
	          s = $.extend(true, {}, Ajax.setting.ajax, {
	              data: data,
	              url: $this.attr("action") || "",
	              type: $this.attr("method") || "POST"
	          }, s);
	          Ajax.doAjax(s);
	      });
    }
//    $.extend(true, $, {
//        fn: {
//            submitForm: function (s) {
//                return $(this).each(function () {
//                    var $this = $(this),
//                        data = Ajax.getFormData($this);
//                    s = $.extend(true, {}, Ajax.setting.ajax, {
//                        data: data,
//                        url: $this.attr("action") || "",
//                        type: $this.attr("method") || "POST"
//                    }, s);
//                    Ajax.doAjax(s);
//                });
//            }
//        }
//    });
    
    window.Ajax = Ajax;

}(window, jQuery));
