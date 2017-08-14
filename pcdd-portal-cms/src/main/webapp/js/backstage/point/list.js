;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				userId:userId,
				pointDesc:"",
				account:"",
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id: "", 
				point: "",
			    pointDesc: "",
			    createTime: "",
			},
			pointDescs:"",
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
			return  { };
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
			} else {
				$("#editorLabel").html("新增");
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
		getPointDescs: function(){
			var that = this;
			var indexOption = "请选择详情";
			service.getPointDescs().then(function(resJson){
				if(resJson.status && (resJson.status===Ajax.CODE.SUCCESS_DATA || resJson.status===Ajax.CODE.SUCCESS_NODATA)){
					var pointDescs  = [{id:"",name:indexOption}];
					for(var i=0,len=resJson.entity.length;i<len;i++){
						pointDescs.push({id:resJson.entity[i],name:resJson.entity[i]});
					}
					that.model.pointDescs(pointDescs);
				}else{
					that.model.pointDescs([{id:"",name:indexOption}]);
					Lobibox.notify("error",{msg:"获取描述详情列表失败"});
				}
			},function(){
				that.model.pointDescs([{id:"",name:indexOption}]);
				Lobibox.notify("error",{msg:"系统异常"})
			});
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			this.list();
			if(!userId){
				this.getPointDescs();
			}
			this.initValidate();
			ko.applyBindings(this);
			this.model.hasInited(true);
		}
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());