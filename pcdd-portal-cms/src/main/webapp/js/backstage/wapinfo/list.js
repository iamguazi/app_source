;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				managerName:""
			},
			formEntity: {
				id: "", 
				wapTitle:"",
		        wapContent:"",
		        wapKey:""
			},
			currManagerType:""
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
		showForm: function (wapKey,root){
			$("body").cover("正在加载数据......");
			$("#nav li").removeClass("active");
			$("#"+wapKey).addClass("active");
			var that = this;
			var $form = $("#form");
			service.getWapInfoByKey({wapKey:wapKey}).then(function(data){
				if(data && data.id>0){
					CKEDITOR.instances.content.setData(data.wapContent);
					fillKOEntity( that.model.formEntity, data);
				}else{
					that.model.formEntity.wapKey(wapKey);
					that.model.formEntity.id("");
					CKEDITOR.instances.content.setData("");
				}
				$("body").uncover();
			},function(){
				that.showInfo("获取数据失败");
				$("body").uncover();
			});
		},
		isRefash:function(){
			return false;
		},
		searchByCondition: function() {
			this.list(1);
		},
		beforeSubmit: function () {
			this.model.formEntity.wapContent(CKEDITOR.instances["content"].getData());
			this.submitForm();
		},
		
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			var that =this;
			this.model = ko.mapping.fromJS(this.model);
			CKEDITOR.on('instanceReady', function (e) { 
				that.showForm("USER_AGREEMENT",$("#nav li").first());
			});
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