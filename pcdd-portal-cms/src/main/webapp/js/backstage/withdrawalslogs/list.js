;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				account:"",
				nickName:"",
				status:"",
				source:"",
				userId:userId,
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id: "", 
				userId: "",
			    type: "",
			    fee: "",
			    realFee:"",
			    account: "",
			    status: "",
			    source: "",
			    createTime: "",
			    updateTime: "",
			    realName: "",
			    mobile: "",
			    bankName: "",
			    point: "",
			    nickName: ""
			},
			currManagerType:"",
			validator:{}
		},
		/**
		 * 表单验证初始化
		 * eg. $form.find("[name='name']").rules("add", {
			  		maxlength: 8,
					minlength: 6,
					messages: {
				    	maxlength: "xxxxx",
				    	minlength: "xxxxx"
				    }
		  		});
		 */
		validateOption : function(){
			var that = this;
			return  {};
		},
		/**
		 * 清空查询内容
		 */
		clearSearch: function () {
			var pageNo = this.model.search.pageNo();
			var pageSize = this.model.search.pageSize();
			fillKOEntity( this.model.search, { pageNo: pageNo, pageSize: pageSize,name:""} );
		},
		fmt  : {
			status : function(status){
				if(status=="0"){
					return "<span class='label label-info'>处理中</span>";
				}
				if(status=="1"){
					return "<span class='label label-success'>已处理</span>";
				}
				if(status=="2"){
					return "<span class='label label-warning'>拒绝处理</span>";
				}
				if(status=="3"){
					return "<span class='label label-danger'>异常处理</span>";
				}
				return "";
			},
			source : function (source){
				switch (source) {
				case "1":
					return "支付宝";
				case "2":
					return "微信";
				case "3":
					return "银行卡";
				default:
					return "";
				}
			}
			
		},
		/**
		 * 显示新增/编辑框
		 * @param data
		 */
		showForm: function (data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			var $form = $("#form");
			$form.find(".form-group").removeClass("has-error has-success");
			if(data.id) {
				$("#editorLabel").html("编辑");
			} else {
				$("#editorLabel").html("新增");
			}
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		searchByCondition: function() {
			this.model.search.createTimeBegin($("#createTimeBegin").val());
			this.model.search.createTimeEnd($("#createTimeEnd").val());
			this.list(1);
		},
		beforeSubmit: function () {
			this.submitForm();
		},
		updateStatus : function(status,item, title, content){
			if ( this.model.onFormSubmitting() ) {
				Lobibox.notify("warning",{msg:"正在提交数据......"});
				return;
			}
			this.model.onFormSubmitting(true);
			this.hideModal();
			var dataArray = [];
			if(item && item.id && typeof item.id == "number" ){
				var data={};
				data.id = item.id;
				data.status = status;
				data.point = item.fee;
				data.userId = item.userId;
				data.title = title;
				data.content = content;
				dataArray.push(data);
			}else{
				var items = this.model.checkItems();
				$.each(items,function(i,e){
					//只处理未处理状态
					if(e.status=="0"){
						var data={};
						data.id = e.id;
						data.status = status;  //固定使用传入状态
						data.point = e.fee;
						data.userId = e.userId;
						data.title = title;
						data.content = content;
						dataArray.push(data);
					}
					
				});
			}
			
			if(dataArray.length<=0){
				Lobibox.notify("info",{msg:"当前选中的数据已处理过了"});
				return ;
			}
			
			var that = this;
			service.updateStatus(JSON.stringify(dataArray),status).then(function(data){
				if(data=="true"){
					Lobibox.notify("success",{msg:"操作成功"});
					that.list(that.model.search.pageNo());
				}else{
					Lobibox.notify("error",{msg:"操作失败"});
				}
				that.model.onFormSubmitting(false);
			},function(){
				Lobibox.notify("error",{msg:"操作异常"});
				that.model.onFormSubmitting(false);
			});
		},
		/**
		 * 通知页面
		 */
		showRefuseForm: function (status, data){
			$("body").cover("正在加载数据......");
			var that = this;
			var $form = $("#refuseForm"),validator = $form.data( "validator" );
			validator.resetForm();
			$form.find(".form-group").removeClass("has-error has-success");
			CKEDITOR.instances.content.setData("");
			$("#content").val("");
			$("#title").val("");
			var refuse = {};
			refuse.status = status;
			refuse.title = "";
			refuse.content = "";
			refuse.userId = "";
			data = data || {};
			if(data && data.id && data.userId){
				refuse.userId=data.userId;
				$form.data("refuse",refuse);
				$form.data("formEntity",data);
				
			}else{
				refuse.userId = "";
				$form.data("refuse",refuse);
				$form.data("formEntity",data);
			}
			
			$("#refuseModal").modal("show");
			$("body").uncover();
		},
		/**
		 * 拒绝通知
		 */
		refuseForm: function () {
			var content = CKEDITOR.instances["content"].getData(),title = $("#title").val();
			$("#content").val(content);
			var $form = $("#refuseForm")
			if($form.length>0){
				if(!$form.valid()){
					return;
				}
			}
			
			var refuse = $form.data("refuse") || {}, formEntity = $form.data("formEntity") || {};
			
			if(!refuse) return ;
			if (refuse.userId){
				//点击单个提现按钮
				formEntity.content = content;
				formEntity.title = title;
				this.updateStatus(refuse.status, formEntity, title, content);
			} else {
				//点击批量提现按钮
				this.updateStatus(refuse.status,null, title, content);
			}
			
		},
		refuseValidate: function(){
			var $form = $( "#refuseForm" );
            var opts = {
            	errorElement: "span", 
            	submitHandler: function ( form ) {
            		//$( form ).find( ".btn-submit" ).click();
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
                rules:{
                	content:{
                		required:true
                	}
                },
            	messages:{
            		content:{
            			required:"内容必填"
            		}
            	},
            	ignore:""
            };
            	opts.errorPlacement = function( $error, $element ) {
                    //error是错误提示元素span对象  element是触发错误的input对象  只触发一次
                    //发现即使通过验证 本方法仍被触发
                    //当通过验证时 error是一个内容为空的span元素
            	    //var label = ( $element.parents( ".form-group" ).find( "label:first" ).text() || "" ).replace( /[\*|\:|\：]/g, " " );
//            		var $parent = $element.parent( ".input-group" );
            		
            		//$error.text( label + $error.text() );
            		$error.text( $error.text() );
            		$element.parent().append($error);
                }
            	$form.validate( opts );
		},
		addValidate:function(){
			this.refuseValidate();
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			this.list();
			this.initValidate();
			this.addValidate();
			ko.applyBindings(this);
			this.model.hasInited(true);
		}
	});
	
	$(function(){
		CKEDITOR.replace('content',{
			customConfig:fullPath+"js/lib/ckeditor/config/normal.js",
			 height: "120px"
		});
		viewModel.initViewModel();
	});
	
}());