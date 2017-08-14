;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				levelName:""
			},
			formEntity: {
				id: "", 
				levelName:"",
		        levelIcon:[],
		        rechargeFee:""
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
					levelName : {
						required : true,
					},
					rechargeFee : {
						required : true,
						isCurrency   : true
					}
				},
				messages : {
					levelName : {
						required : "等级名称必填",
					},
					rechargeFee : {
						required : "序号必填",
						isCurrency : "请输入正确的金额",
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
				if(data && data.levelIcon) {
					if($.type(data.levelIcon) === "string") {
						data.levelIcon = (data.levelIcon || "") .split(",");	
					}
				}else {
					data.levelIcon = [];
				}
			} else {
				$("#editorLabel").html("新增");
				data.levelIcon = [];
			}
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		searchByCondition: function() {
			this.list(1);
		},
		beforeSubmit: function () {
			/*if(!this.model.formEntity.levelIcon() || this.model.formEntity.levelIcon().length<=0){
				Lobibox.notify("warning",{msg:"等级图标必填"});
				return ;
			}*/
			this.submitForm();
		},
		afterUpload: function (resJson, type) {
			if(type==1){
				this.model.formEntity.levelIcon.push(resJson.data.paths[0]);
			}
		},
		removeImg : function(type,index){
			if(type==1){
				this.model.formEntity.levelIcon.splice(index, 1);
			}
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