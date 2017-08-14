;(function () {
	
	var service = {
		list: function () {
			return Ajax.ajax({
				url: fullPath + "backstage/otherInfo/?act=QUERY",
		        type: "GET",
		        autoCloseCover:false,
		        data: {
		        	pageNo: 1,
		        	pageSize: 9999
		        }
			});
		},
		saveOrUpdate: function (data) {
			return Ajax.ajax({
				url: fullPath + "backstage/otherInfo/saveOrUpdate",
		        type: "POST",
		        dataType: "json",
		        data: data
			});
		}
	};
	
	
	$.extend(true, viewModel, {
		model: {
			bili:{
				tixian_bili:"",
				tixian_free_count:"",
				tixian_min_fee:"",
				get_all_point:"",
				zhuan_qian_bili:"",
				choice_num:""
			},
			other:{
				kefu_qq:"",
				kefu_weixin:"",
				kefu_guanwang:"",
				people_num:""
			},
			share:{
				share_title:"",
				share_content:"",
				share_url:"",
				share_logo:""
			},
			hasInited: false
		},
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.list();
			this.initValidate();
			this.addValidateOption();
			ko.applyBindings(this);
			this.model.hasInited(true);
		},
		addValidateOption: function(){
			
			
			/**分销设置验证start**/
			$("[name='tixian_bili']").rules("add",{
				  required : true,
					isBili:true,
					messages:{
						required : "提现比例必填",
						isBili:"请输入合法提现比例"
					}
			 });
			$("[name='tixian_free_count']").rules("add",{
				required : true,
				digits:true,
				messages:{
					required : "免费提现次数必填",
					digits:"请输入合法免费提现次数"
				}
			});
			$("[name='tixian_min_fee']").rules("add",{
				required : true,
				isCurrencyofTwoDecimal:true,
				messages:{
					required : "最低提现金额必填",
					isCurrencyofTwoDecimal:"请输入合法最低提现金额"
				}
			});
			$("[name='get_all_point']").rules("add",{
				required : true,
				isCurrencyofTwoDecimal:true,
				messages:{
					required : "为用户赢取金额必填",
					isCurrencyofTwoDecimal:"请输入合法为用户赢取金额"
				}
			});
			$("[name='zhuan_qian_bili']").rules("add",{
				required : true,
				isBili:true,
				messages:{
					required : "赚钱比例必填",
					isBili:"请输入合法赚钱比例"
				}
			});
			$("[name='choice_num']").rules("add",{
				required : true,
				digits:true,
				messages:{
					required : "分销下注次数必填",
					digits:"请输入合法分销下注次数"
				}
			});
			/**分销设置验证end**/
        	
			
			/**其它设置start**/
			$("[name='people_num']").rules("add",{
				required : true,
				digits:true,
				messages:{
					required : "在线人数必填",
					digits:"请输入合法在线人数"
				}
			});
			/**其它设置end**/
			
		},
		list: function () {
			var that = this;
			service.list().then(function (resJson) {
				if(resJson.status === Ajax.CODE.SUCCESS_DATA) {
					that.handleResult(resJson.page.result);
				}
			}).always(function(){
				$("body").uncover();
			});
		},
		
		validateOption: function () {
	            return {
	            }
		},
		
		handleResult: function (result) {
			var that = this;
			if($.isArray(result)){
				$.each(result, function (index, item) {
					if($("[name='"+item.otherKey+"']").length > 0){
						$("[name='"+item.otherKey+"']").val(item.otherValue).change();
					}
//					if(item.otherKey=="share_logo"){
//						if(item.otherValue) {
//							if($.type(item.otherValue) === "string") {
//								that.model.other.share_logo((item.otherValue || "") .split(","));	
//							}
//						}else {
//							that.model.other.share_logo([]);
//						}
//					}
				});
			}
			
		},
		updateOther: function(){
			var data = ko.mapping.toJS(this.model.other);
			this.update("other", data);
		},
		updateBili: function(){
			var data = ko.mapping.toJS(this.model.bili);
			this.update("bili", data);
		},
		updateShare: function(){
			var data = ko.mapping.toJS(this.model.share);
//			data.share_logo = this.model.other.share_logo().join();
			this.update("share", data);
		},
		update: function (otherGroup, data) {
			var that = this;
			if(!this.model[otherGroup]){
				that.showInfo("不存在组为" + otherGroup + "的分组","alert alert-warning");
				return;
			} else {
				data.otherGroup = otherGroup;
				service.saveOrUpdate(data).then(function (resJson) {
						if (resJson.status === Ajax.CODE.SUCCESS_NODATA) {
							that.showInfo("修改成功","alert alert-success");
						} else {
							that.showInfo("修改失败," + resJson.desc);
						}
					},function(){
						that.showInfo("系统异常");
					});
			}
		}
		,afterUpload: function (resJson, type) {
//			this.model.other.share_logo.push(resJson.data.paths[0]);
//			$("#share_logo").val(this.model.other.share_logo().join(","));
		},
		removeImg: function (index) {
//			this.model.other.share_logo.splice(index, 1);
//			$("#share_logo").val(this.model.other.share_logo().join(","));
		},
		/**
	     * 初始化 jquery.validate
	     */
		initValidate : function(){
	    	
	        if ( !$.fn.validate ) { return; }
	        
	        $.extend($.validator.messages, {
	            required    : "  为必填字段",
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
	        var that = this;
	        $( ".sh-validate" ).each( function () {
	            var $form = $( this );
	            var $errorForm = $form.find( ".errorForm" );
	            var opts = {
	            	errorElement: "span", 
	            	submitHandler: function ( form ) {
	            		if($( form ).valid()){
	            			$( form ).find( ".btn-submit" ).click();
	            		}
	            	},
	                highlight: function( element ) {
	                    jQuery( element ).closest( '.form-group' )
	                        .removeClass( 'has-success' )
	                        .addClass( 'has-error' );
	                },
	                success: function( element ) {
	                    jQuery( element ).closest( '.form-group' )
	                        .removeClass( 'has-error' ).addClass("has-success");
	                },
	                ignore:""
	            };
	            if ( $errorForm.length ) {
	            	opts.errorLabelContainer = $errorForm;
	            } else {
	            	opts.errorPlacement = function( $error, $element ) {
	                    //error是错误提示元素span对象  element是触发错误的input对象  只触发一次
	                    //发现即使通过验证 本方法仍被触发
	                    //当通过验证时 error是一个内容为空的span元素
	            	    //var label = ( $element.parents( ".form-group" ).find( "label:first" ).text() || "" ).replace( /[\*|\:|\：]/g, " " );
	            		var $parent = $element.parent( ".input-group" );
	            		
	            		//$error.text( label + $error.text() );
	            		$error.text( $error.text() );
	            		if ( $parent && $parent.length ) {
	            			$error.insertAfter( $parent );        
	            		} else {
	            			$error.insertAfter( $element );            			
	            		}
	                }
	            }
	            
	            that.model.validator=$( this ).validate( $.extend(true,opts,that.validateOption() ));
	        } );
	    }
	});
	
	
	$(function () {
		viewModel.initViewModel();
	});
	
	
}());