;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				managerName:""
			},
			formEntity: {
				id: "", 
				managerName:"",
                managerPasswd:"",
                managerType:"",
			    valid:"",
			    createTime:"",
			    bannerImgurl:[]
			},
			validator:{},
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
		initValidate: function () {
			var that = this; 
			that.model.validator = $("#form").validate({
				
			});
			
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
			var that = this;
			var $form = $("#form");
			that.model.validator.resetForm();
			$form.find(".form-group").removeClass("has-error has-success");
			if(data.id) {
				$("#editorLabel").html("编辑");
				//图片
				data.bannerImgurl =  (data.bannerImgurl || "").split(",") ;
			} else {
				$("#editorLabel").html("新增");
				//图片
				this.model.formEntity.bannerImgurl( [] );
			}
			fillKOEntity( this.model.formEntity, data);
			$("#editor").modal("show");
		},
		searchByCondition: function() {
			this.list();
		},
		beforeSubmit: function () {
			this.submitForm();
		},
		//上传图片后的回调
		afterUpload: function (resJson, type) {
			if(type==1){
				this.model.formEntity.bannerImgurl.push(resJson.data.paths[0]);
			}
		},
		//删除图片
		removeImg : function(type,index){
			if(type==1){
				this.model.formEntity.bannerImgurl.splice(index, 1);
			}
		},
		
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
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