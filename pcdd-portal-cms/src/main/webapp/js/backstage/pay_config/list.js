;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				name:"",
				status:"",
				type:""
			},
			formEntity: {
				id:"",
				name:"",
		        remarks:"",
		        type:"",
			    status:"",
			    payOrder:""
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
			return  {
				rules : {
					payOrder : {
						digits:true
					}
				},
				messages : {
					payOrder : {
						digits: "请输入正确的排序序号"
					}
				}
			};
		},
		/**
		 * 清空查询内容
		 */
		clearSearch: function () {
			var pageNo = this.model.search.pageNo();
			var pageSize = this.model.search.pageSize();
			fillKOEntity( this.model.search, { pageNo: pageNo, pageSize: pageSize} );
		},
		fmt  : {
			type:function(type){
				if(type == 1){
					return "线上支付";
				}
				
				if (type == 2){
					return "线下支付";
				}
				
				return "";
			},
			status:function(status){
				if( status == 0 ){
					return "<span class='label label-default'>拉黑</span>";
				}
				
				if( status == 1 ){
					return "<span class='label label-success'>正常</span>";
				}
				return "";
			}
			
		},
		/**
		 * 显示新增/编辑框
		 * @param data
		 */
		showForm: function (data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			var $form = $("#form"),validator = $form.data( "validator" );
			validator.resetForm();
			$form.find(".form-group").removeClass("has-error has-success");
			
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		searchByCondition: function() {
			this.list(1);
		},
		beforeSubmit: function () {
			this.submitForm();
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			var that = this;
			that.list();
			this.initValidate();
			ko.applyBindings(this);
			this.model.hasInited(true);
		}
		
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());