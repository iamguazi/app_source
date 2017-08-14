;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id: "", 
				time: "",
				newUser: "",
				beijing28Exhibit: "",
				canada28Exhibit: "",
				backWater: "",
				withdrawalFee: "",
				rechargeFee: ""
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
			var start = $("#createTimeBegin").val() ,end = $("#createTimeEnd").val();
			if(!start || !end){
				return Lobibox.notify("info",{msg:"请选择您要查询的日期"})
			}
			this.model.search.createTimeBegin($("#createTimeBegin").val());
			this.model.search.createTimeEnd($("#createTimeEnd").val());
			this.list(1);
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
		exportXls:function(){
			var exportData = this.model.dataList();
			window.open(fullPath+"backstage/previewFinancialStatistics/exportExcel?exportData="+JSON.stringify(exportData))
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
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());