;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				account:"",
				nickName:"",
				userId:"",
				status:"",
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id: "", 
				userId: "",
			    point: "",
			    fenxiaoPoint: "",
			    status: "",
			    zuhePoint: "",
			    pointNum: "",
			    getPoint: "",
			    xhibitPoint: "",
			    createTime: "",
			    fenxiaoUserId: "",
			    nickName: "",
			    friendName: "",
			    account: "",
			    mobile: ""
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
			fillKOEntity( this.model.search, { pageNo: pageNo, pageSize: pageSize,name:""} );
		},
		fmt  : {
			status : function(status){
				if(status=="0"){
					return "<span class='label label-info'>待处理</span>";
				}
				if(status=="1"){
					return "<span class='label label-success'>已处理</span>";
				}
				if(status=="2"){
					return "<span class='label label-danger'>已拒绝</span>";
				}
				return "";
			},
			source : function (source){
				switch (source) {
				case "1":
					return "支付宝";
				case "2":
					return "微信";
				case "3":
					return "银行卡";
				default:
					return "";
				}
			},
			xhibitPoint:function(xhibitPoint){
				if(!xhibitPoint && xhibitPoint!=0){
					return "";
				}
				if(xhibitPoint>=0){
					return "<span style='color: green;font-weight: bold;'>+"+this.currency(xhibitPoint,{places: 2,symbol: ''})+"</span>";
				}else{
					return "<span style='color: red;font-weight: bold;'>"+this.currency(xhibitPoint,{places: 2,symbol: ''})+"</span>";
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
				var data={};
				data.id = item.id;
				data.status = status;
				data.point = item.point;
				data.fenxiaoPoint = item.fenxiaoPoint;
				data.friendName = item.friendName;
				data.mobile = item.mobile;
				data.createTime = (item.createTime && this.fmt.date(item.createTime,'yyyy-MM-dd hh:mm')) ||  this.fmt.date(new Date().getTime(),'yyyy-MM-dd hh:mm');
				data.userId = item.userId;
				dataArray.push(data);
			}else{
				var items = this.model.checkItems();
				$.each(items,function(i,e){
					//只处理未处理状态
					if(e.status=="0"){
						var data={};
						data.id = e.id;
						data.status = status;  //固定使用传入状态
						data.point = item.point;
						data.fenxiaoPoint = item.fenxiaoPoint;
						data.friendName = item.friendName;
						data.mobile = item.mobile;
						data.createTime = (item.createTime && this.fmt.date(item.createTime,'yyyy-MM-dd hh:mm')) ||  this.fmt.date(new Date().getTime(),'yyyy-MM-dd hh:mm');
						data.userId = item.userId;
						dataArray.push(data);
					}
					
				});
			}
			
			if(dataArray.length<=0){
				Lobibox.notify("info",{msg:"当前选中的数据都已处理过了"});
				return ;
			}
			
			var that = this;
			service.updateStatus(JSON.stringify(dataArray),status).then(function(data){
				if(data=="true"){
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