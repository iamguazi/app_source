;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				giftName:"",
				status:"",
				createTimeBegin:"",
				createTimeEnd:"",
			},
			formEntity: {
				id: "", 
			    giftName:"",
			    giftPhoto:[],
			    giftPoint:"",
			    status:"",
			    giftOrder:""
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
					giftName : {
						required : true,
					},
					giftOrder : {
						required : true,
						digits   : true
					},
					giftPoint : {
						required : true,
						isCurrencyofTwoDecimal:true
					}
//					giftKey : {
//						required : true,
//						isStartLettersOrIncloudNum: true,
//						remote : {
//							url : fullPath+"backstage/giftInfo/isExist",
//							type : "post",
//							dataType : "json",
//							// async:false,
//							data : {
//								id : function() {
//									return that.model.formEntity.id();
//								},
//								giftKey: function() {
//									return that.model.formEntity.giftKey();
//								},
//							}
//						}
//					}
					
				},
				messages : {
					giftName : {
						required : "名称必填",
					},
					giftOrder : {
						required : "序号必填",
						digits : "只能输入数字",
					},
					giftPoint : {
						required : "礼品价格必填",
						isCurrencyofTwoDecimal: "请输入合法的价格(保留两位小数)"
					}
//					giftKey : {
//						required : "礼物编号必填",
//						isStartLettersOrIncloudNum : "只能包含字母或数字,且字母开头",
//						remote : "编号已存在"
//					}
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
			status : function(status){
				if(status=="1"){
					return "<span class='label label-success'>启用</span>";
				}
				if(status=="0"){
					return "<span class='label label-danger'>停用</span>";
				}
				return "";
			},
			giftType : function (giftType){
				switch (giftType) {
				case 1:
					return "普通";
				case 2:
					return "连发";
				case 3:
					return "红包";
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
				if(data && data.giftPhoto) {
					if($.type(data.giftPhoto) === "string") {
						data.giftPhoto = (data.giftPhoto || "") .split(",");	
					}
				}else {
					data.giftPhoto = [];
				}
			} else {
				$("#editorLabel").html("新增");
				data.giftPhoto=[];
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
		afterUpload: function (resJson, type) {
			if(type==1){
				this.model.formEntity.giftPhoto.push(resJson.data.paths[0]);
			}
		},
		removeImg : function(type,index){
			if(type==1){
				this.model.formEntity.giftPhoto.splice(index, 1);
			}
		},
		updateStatus : function(status,id){
			this.hideModal();
			var ids = [];
			if(id && typeof id == "number" ){
				ids[0] = id;
			}else{
				ids = this.model.delId();
			}
			
			var data = "status="+status;
			for(var i=0,len=ids.length;i<len;i++){
				data+="&ids="+ids[i];
			}
			
			var msg = status==1?"启用":"停用";
			var that = this;
			service.updateStatus(data).then(function(data){
				if(data){
					Lobibox.notify("success",{msg:msg+"成功"});
					that.list(that.model.search.pageNo());
				}else{
					Lobibox.notify("error",{msg:msg+"失败"});
				}
			},function(){
				Lobibox.notify("error",{msg:msg+"异常"});
			});
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
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