;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				areaType:""
			},
			formEntity: {
				id: "", 
				areaType:"",
		        bili:"",
		        startPoint:"",
			    endPoint:""
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
					bili : {
						required : true,
						isBili:true
					},
					startPoint : {
						required : true,
						isCurrencyofTwoDecimal   : true
					},
					endPoint : {
						required : true,
						isCurrencyofTwoDecimal   : true
					}
				},
				messages : {
					bili : {
						required : "回水比例必填",
						isBili: "请输入正确的回水比例"
					},
					startPoint : {
						required : "亏损起始积分必填",
						isCurrencyofTwoDecimal   : "情输入正确的亏损起始积分"
					},
					endPoint : {
						required : "亏损结束积分必填",
						isCurrencyofTwoDecimal   : "情输入正确的亏损结束积分"
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
			fillKOEntity( this.model.search, { pageNo: pageNo, pageSize: pageSize,name:""} );
		},
		fmt  : {
			
			areaType: function(areaType){
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