;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				userNickName:"",
				hostNickName:"",
				giftName:"",
				createTimeBegin:"",
				createTimeEnd:"",
			},
			formEntity: {
				id: "", 
				userId:"",
			    giveUserId:"",
			    giftId:"",
			    giftName:"",
			    count:"",
			    allFee:"",
			    createTime:"",
			    roomId:"",
			    perFee:""
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
		fmt  : {
			
			diamond:function(totalFee){
				return this.currency(totalFee*6,{places:0,symbol:""});
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
			this.list();
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
					that.showInfo(msg+"成功","alert alert-success");
					that.list(that.model.search.pageNo());
				}else{
					that.showInfo(msg+"失败");
				}
			},function(){
				that.showInfo(msg+"异常");
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