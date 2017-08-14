;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				areaType:"",
				areaName:"",
				gameType:"",
				status:1
			},
			formEntity: {
				id:"",
				gameType:"",
			    areaType:"",
			    areaName:"",
			    areaPhoto:"",
			    feedbackDesc:"",
			    minPoint:"",
			    status:""
			},
			currManagerType:"",
			allAreaTypes:[],
			areaTypes:[{id:"",areaName:"请选择房间类型"}],
			searchAreaTypes:[{id:"",areaName:"所有"}]
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
					areaName: {
						required   : true
					},
					feedbackDesc: {
						required: true
					},
					minPoint: {
						required:true,
						isCurrencyofTwoDecimal:true
					}
				},
				messages : {
					areaName: {
						required   : "房间名称必填"
					},
					feedbackDesc: {
						required: "回水描述必填"
					},
					minPoint: {
						required:"所需最低金额必填",
						isCurrencyofTwoDecimal: "请输入合法金额"
					}
				}
			}
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
			areaType : function(areaType){
				if(areaType=="1"){
					return "初级";
				}
				if(areaType=="2"){
					return "中级";
				}
				if(areaType=="3"){
					return "高级";
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
			this.model.validator.resetForm();
			var that = this;
			var $form = $("#form");
			$form.find(".form-group").removeClass("has-error").removeClass("has-success").removeClass("has-error has-success");
			if(data.id) {
				if(data && data.areaPhoto) {
					if($.type(data.areaPhoto) === "string") {
						data.areaPhoto = (data.areaPhoto || "") .split(",");	
					}
				}else {
					data.areaPhoto = [];
				}
			} else {
				data.areaPhoto=[];
			}
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		searchByCondition: function() {
			this.list(1);
		},
		beforeSubmit: function () {
			/*if(this.model.formEntity.roomPhoto().length<=0 ){
				return Lobibox.notify("warning",{msg:"请先上传房间图标"});
			}*/
			this.submitForm();
		},
		afterUpload: function (resJson, type) {
			if(type==1){
			}
		},
		removeImg : function(type,index){
			if(type==1){
			}
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