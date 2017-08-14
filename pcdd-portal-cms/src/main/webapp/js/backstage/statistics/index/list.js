;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				dayTime:""
			},
			formEntity: {
				id: "", 
				allUser:"",  //用户总数
				dayActiveUser:"", //今日活跃会员
				dayUser:"", //今日新增会员
				weekUser:"", //周新增会员
				monthUser:"",  //月新增会员
				platformWinOrLoseFee:"",  //平台总输赢
				dishAllFee:"",  //盘内总额
				givingAllFee:"", //赠送总额
				onlineUser:"", //在线人数
				toDayFloatExhibit:"",//今日浮动盈亏
				dayPayFee:"",  //今日充值
				dayOnlinePayFee:"", //线上充值
				dayOfflinePayFee:"", //线下转账
				dayWithdrawalsFee:"", //今日提现
				dayWinOrLoseFee:"", //今日总输赢
				totalPoint:""     //会员总余额
			},
			currManagerType:"",
			validator:{}
		},
		/**
		 * 清空查询内容
		 */
		clearSearch: function () {
			var pageNo = this.model.search.pageNo();
			var pageSize = this.model.search.pageSize();
			fillKOEntity( this.model.search, { pageNo: pageNo, pageSize: pageSize} );
		},
		/**
		 * 显示新增/编辑框
		 * @param data
		 */
		showForm: function (data){
			
		},
		searchByCondition: function() {
			this.model.search.dayTime($("#dayTime").val());
			this.list();
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
		},
		list: function (pageNo, pageSize) {
			var that = this;
			that.initView();
			if (pageNo) {
				this.model.search.pageNo(pageNo);
			}
			if (pageSize) {
				this.model.search.pageSize(pageSize);
			}
			
			var search = ko.mapping.toJS(this.model.search);
			this.hideModal();
			service.list(search).then(function(resJson){
				$(".con").css("overflow-y","hidden");
				if(resJson.status === Ajax.CODE.SUCCESS_DATA){
					that.model.dataList(resJson.dataList);
					that.model.checkItems([]);
					//that.initClickEvent();
					fillKOEntity( that.model.formEntity,resJson.dataList[0] );
				} else if (resJson.status === Ajax.CODE.SUCCESS_NODATA) {
					// 删除等操作导致的最后一页唯一一条记录被删除后要跳转到前一页
					if ( that.model.search.pageNo() > 1 ) {
						Lobibox.notify("info",{msg:"当前页没有数据，自动加载上一页,请耐心等待......"});
						that.list(that.model.search.pageNo() - 1);
							
					} else {
						Lobibox.notify("info",{msg:"暂无数据"});
						that.model.dataList([]);
						that.model.checkItems([]);
						//that.initClickEvent();
					}
				} else {
					Lobibox.notify("error",{msg:"查询失败"});
				}
			},function(error){
				Lobibox.notify("info",{msg:"服务器异常"});
			}).always(function(){
				$("body").uncover();
			});
		},
		goToCodeMenu: function (menuCode){
//			$("#noticeDrop").hide();
			top.viewModel.goToCodeMenu(menuCode);
		 }
		 
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());