;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				accountId:accountId,
				accountType:"",
				userId:"",
				status:"",
				realName:"",
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id:"",
				accountId:"",
			    account:"",
			    accountType:"",
			    realName:"",
			    bankName:"",
			    point:"",
			    status:"",
			    createTime:"",
			    userId:"",
			    addType:""
			},
			currManagerType:"",
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
			return  {}
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
			accountType: function(accountType){
				if(accountType=="1"){
					return "银行卡";
				}
				if(accountType=="2"){
					return "支付宝";
				}
				return "";
			},
			status : function (status){
				if ("0" ==  status) {
					return '<span class="label label-default">待确认</span>';
				}
				if ("1" ==  status) {
					return '<span class="label label-success">已确认</span>';
				}
				if ("2" ==  status) {
					return '<span class="label label-danger">已取消</span>';
				}
				
				return "";
			}
		
		},
		showAccountLog: function(data){
		},
		/**
		 * 显示新增/编辑框
		 * @param data
		 */
		showForm: function (data){
			$("body").cover("正在加载数据......");
			this.model.validator.resetForm();
			var that = this;
			var $form = $("#form");
			$form.find(".form-group").removeClass("has-error").removeClass("has-success").removeClass("has-error has-success");
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
		updateStatus : function(item,status){
			this.hideModal();
			var msg = status==1?"确认转账":"取消转账";
			var that = this;
			service.updateStatus({id:item.id,status:status,userId:item.userId,point:item.point}).then(function(data){
				if(data && data==true){
					Lobibox.notify("success",{msg:msg+"成功"});
					that.list(that.model.search.pageNo());
				}else{
					Lobibox.notify("error",{msg:(msg+"失败")});
				}
			},function(){
				Lobibox.notify("error",{msg:msg+"异常"});
			});
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			var that = this;
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			this.list();
			this.initValidate();
			ko.applyBindings(this);
			this.model.hasInited(true);
		}
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());