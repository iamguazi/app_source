;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				realName:"",
				status:"",
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id: "", 
				giftId:"",
			    userId:"",
			    giftName:"",
			    giftCount:"",
			    nickName:"",
			    status:"",
			    createTime:"",
			    realName:"",
			    mobile:"",
			    address:"",
			    userAccount:"",
			    cityId:"",
			    point:"",
			    giftPoint:"",
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
			return  {};
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
				if(status=="0"){
					return "<span class='label label-info'>审核中</span>";
				}
				if(status=="1"){
					return "<span class='label label-success'>已处理</span>";
				}
				if(status=="2"){
					return "<span class='label label-danger'>拒绝处理</span>";
				}
				return "";
			},
			source : function (source){
				switch (source) {
				case "1":
					return "支付宝";
				case "2":
					return "微信";
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
		updateStatus : function(status,item){
			this.hideModal();
			
			var dataArray = [];
			if(item && item.id && typeof item.id == "number" ){
				if(item.status=="0"){
					dataArray.push(item.id);
				}
			}else{
				var items = this.model.checkItems();
				$.each(items,function(i,e){
					//只处理未处理状态
					if(e.status=="0"){
						dataArray.push(e.id);
					}
					
				});
			}
			
			if(dataArray.length<=0){
				Lobibox.notify("info",{msg:"当前选中的数据已处理过了"});
				return ;
			}
			
			var data = "status="+status;
			for(var i=0,len=dataArray.length;i<len;i++){
				data+="&ids="+dataArray[i];
			}
			
			var that = this;
			service.updateStatus(data).then(function(data){
				if(data && data==true){
					Lobibox.notify("success",{msg:"操作成功"});
					that.list(that.model.search.pageNo());
				}else{
					Lobibox.notify("error",{msg:"操作失败"});
				}
			},function(){
				Lobibox.notify("error",{msg:"操作异常"});
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