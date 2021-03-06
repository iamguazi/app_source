;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:id,
				userType:"1",
				nickName:"",
				mobile:"",
				account:"",
				realName:"",
				status:"",
				pointSort:"",
				xhibitPointSort:"desc",
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id:"",
				account:"",
			    password:"",
			    nickName:"",
			    sex:"",
			    mobile:"",
			    point:"",
			    bandId:"",
			    bandType:"",
			    registrationId:"",
			    code:"",
			    status:"",
			    imAccount:"",
			    level:"",
			    levelName:"",
			    userType:"",
			    xhibitPoint:"",
			    realName:"",
			    bankName:"",
			    bankNo:"",
			    openCardAddress:""
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
			return  {rules:{
				bankNo:{
					required:true,
					digits:true
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
		searchByCondition: function() {
			this.list(1);
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
			
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#editor").modal("show");
			
			$("body").uncover();
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
			this.list();
			var that = this;
			this.initValidate();
			ko.applyBindings(this);
			this.model.hasInited(true);
		}
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());