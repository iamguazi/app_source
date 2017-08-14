;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				title:"",
				userId:"0"
			},
			formEntity: {
				id: "", 
				title:"",
		        content:"",
		        noticeType:"",
		        userId:""
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
					title : {
						required : true,
					},
					content : {
						required : true
					}
				},
				messages : {
					title : {
						required : "标题必填",
					},
					rechargeFee : {
						required : "内容必填"
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
				CKEDITOR.instances.content.setData(data.content);
			} else {
				$("#editorLabel").html("新增");
				CKEDITOR.instances.content.setData("");
			}
			fillKOEntity( this.model.formEntity, data || {});
			this.model.formEntity.userId(0);
			this.model.formEntity.noticeType(1);
			$("#editor").modal("show");
			$("body").uncover();
		},
		searchByCondition: function() {
			this.list(1);
		},
		beforeSubmit: function () {
			this.model.formEntity.content(CKEDITOR.instances["content"].getData());
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
		CKEDITOR.replace('content',{
			customConfig:fullPath+"js/lib/ckeditor/config/normal.js"
		});
		viewModel.initViewModel();
	});
	
}());