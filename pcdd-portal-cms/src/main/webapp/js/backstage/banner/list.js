;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				bannerName:"",
				status:"",
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id: "", 
				bannerName:"",
		        bannerImgurl:[],
		        content:"",
		        bannerOrder:"",
			    isGo:"",
			    status:"",
			    bannerPlace:"",
			    url:""
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
					bannerName : {
						required : true,
					},
					bannerOrder : {
						required : true,
						digits   : true
					}
				},
				messages : {
					bannerName : {
						required : "名称必填",
					},
					bannerOrder : {
						required : "序号必填",
						digits : "只能输入数字",
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
			
			bannerPlace: function(place){
				if(place=="1"){
					return "app";
				}
				
				if(place=="2"){
					return "网站首页";
				}
				
				return "";
			},
			status : function(status){
				if(status=="1"){
					return "<span style='color:#5cb85c;font-weight:bold;' >启用</span>";
				}
				if(status=="0"){
					return "<span style='color:#d9534f;font-weight:bold; '>禁用</span>";
				}
				return "";
			},
			isGo : function (isGo){
				switch (isGo) {
				case 1:
					
					return "是";
				case 0:
					return "否";
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
			$form.data("validator").resetForm();
			$form.find(".form-group").removeClass("has-error has-success");
//			$form.find(".form-control:first").focus();
			if(data.id) {
				data.isGo = data.isGo+"";
				$("#editorLabel").html("编辑");
				if(data && data.bannerImgurl) {
					if($.type(data.bannerImgurl) === "string") {
						data.bannerImgurl = (data.bannerImgurl || "") .split(",");	
					}
				}else {
					data.bannerImgurl = [];
				}
				CKEDITOR.instances.content.setData(data.content);
			} else {
				$("#editorLabel").html("新增");
				data.isGo = "1";
				data.bannerImgurl=[];
				CKEDITOR.instances.content.setData("");
			}
			this.showGoType(data.isGo);
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		showGoType:function(type){
			if(!type && type!="0" || type=="null"){
				return true;
			}
			
			this.removeUrlRules();
			
			if(type==1){
				$("#url").rules("add", {
//					required: true,
					url:true,
					messages:{
//						required:"跳转地址必填",
						url:"请输入合法的网址"
					}
				});
			}
			return true;
		},
		removeUrlRules: function(){
			var $url = $("#url"), urlRules = $url.rules();
			
			if(!$.isEmptyObject(urlRules)){
				$url.rules("remove");
			}
		},
		searchByCondition: function() {
			this.model.search.createTimeBegin($("#createTimeBegin").val());
			this.model.search.createTimeEnd($("#createTimeEnd").val());
			this.list(1);
		},
		beforeSubmit: function () {
			this.model.formEntity.content(CKEDITOR.instances["content"].getData());
			this.submitForm();
		},
		afterUpload: function (resJson, type) {
			if(type==1){
				this.model.formEntity.bannerImgurl.push(resJson.data.paths[0]);
			}
		},
		removeImg : function(type,index){
			if(type==1){
				this.model.formEntity.bannerImgurl.splice(index, 1);
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
		CKEDITOR.replace('content',{
			customConfig:fullPath+"js/lib/ckeditor/config/normal.js"
		});
		viewModel.initViewModel();
	});
	
}());