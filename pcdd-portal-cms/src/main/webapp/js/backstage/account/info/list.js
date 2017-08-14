;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				accountType:"",
				realName:"",
				account:"",
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id:"",
				account:"",
			    accountType:"",
			    realName:"",
			    bankName:"",
			    openCardAddress:"",
			    photo:[]
			},
			currManagerType:"",
			searchPayList:[],
			formPayList:[],
			
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
				
				if(accountType == "3"){
					return "微信";
				}
				return "";
			}
		
		},
		showAccountLog: function(data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			
			var showData = (data || {});
			$("#accountLogIframe").prop("src",fullPath+"backstage/userAccountLog/toIndex?accountId="+data.id+"&accountType="+data.accountType+"&account="+data.account+"&time="+new Date().getTime());
			
			$("#accountLog").modal("show");
			
			$("body").uncover();
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
			
			util.stringToArray(data, "photo");
			
			fillKOEntity( this.model.formEntity, data || {});
			
			this.showAccountType();
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		showAccountType:function(){
			this.model.validator.resetForm();
			this.removeRules();
			var payConfigId = this.model.formEntity.accountType();
			
			if(!payConfigId && payConfigId!="0" || payConfigId=="null"){
				return true;
			}
			
			var type = payConfigId;
//			var formPlayList = this.model.formPayList();
//			for(var i=0,len=formPlayList.length;i<len;i++){
//				if(formPlayList[i].id == payConfigId){
//					type = formPlayList[i].type;
//				}
//			}
			
			if(type==1){
				
				$("#realName").rules("add", {
					required: true,
					messages:{
						required:"开户名"
					}
				});
				
				$("#bankName").rules("add", {
					required: true,
					messages:{
						required:"银行名称"
					}
				});
				
				$("#openCardAddress").rules("add", {
					required: true,
					messages:{
						required:"开户行"
					}
				});
				
			}
			return true;
		},
		removeRules: function(){
			var $realName = $("#realName"), realNameRules = $realName.rules(),
			    $bankName = $("#bankName"), bankNameRules = $bankName.rules(),
			    $openCardAddress = $("#openCardAddress"), openCardAddressRules = $openCardAddress.rules();
			
			if(!$.isEmptyObject(realNameRules)){
				$realName.rules("remove");
			}
			
			if(!$.isEmptyObject(bankNameRules)){
				$bankName.rules("remove");
			}
			
			if(!$.isEmptyObject(openCardAddressRules)){
				$openCardAddress.rules("remove");
			}
		},
		searchByCondition: function() {
			this.model.search.createTimeBegin($("#createTimeBegin").val());
			this.model.search.createTimeEnd($("#createTimeEnd").val());
			this.list(1);
		},
		afterUpload: function (resJson, type) {
			if(type==1){
				this.model.formEntity.photo.push(resJson.data.paths[0]);
			}
		},
		removeImg : function(type,index){
			if(type==1){
				this.model.formEntity.photo.splice(index, 1);
			}
		},
		beforeSubmit: function () {
			this.submitForm();
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			var that = this;
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			/*service.getPayListConfig({}).then(function(resJson){
				if(resJson.status === Ajax.CODE.SUCCESS_DATA){
					var searchPayList = resJson.entity.slice(0);
					searchPayList.splice(0,0,{id:"",name:"请选择支付方式..."});
					that.model.searchPayList(searchPayList);
					var formPayList = resJson.entity.slice(0);
					formPayList.splice(0,0,{id:"",name:"请选择支付方式..."});
					that.model.formPayList(formPayList);
				} else if (resJson.status === Ajax.CODE.SUCCESS_NODATA) {
					Lobibox.notify("info",{msg:"暂无支付类型数据"});
				} else {
					Lobibox.notify("error",{msg:"查询支付类型失败"});
				}
			},function(){
				Lobibox.notify("error",{msg:"系统异常"});
			})*/
			
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