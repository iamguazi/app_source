;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				createTimeBegin:"",
				createTimeEnd:"",
				xhibitPointSort:"desc"
			},
			formEntity: {
				id: "", 
				time: "",
				newUser: "",
				beijing28Exhibit: "",
				canada28Exhibit: "",
				backWater: "",
				withdrawalFee: "",
				rechargeFee: "",
				account:"",
				xhibitPoint:""
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
		fmt:{
			xhibitPoint:function(xhibitPoint){
				if(!xhibitPoint && xhibitPoint!=0){
					return "";
				}
				if(xhibitPoint>=0){
					return "<span style='color: green;font-size: 16px;font-weight: bold;'>+"+this.currency(xhibitPoint,{places: 2,symbol: ''})+"</span>";
				}else{
					return "<span style='color: red;font-size: 16px;font-weight: bold;'>"+this.currency(xhibitPoint,{places: 2,symbol: ''})+"</span>";
				}
			}	
		},
		serachSort : function(sortName,event,$root){
			var $this = $(event.target);
			var sortType = "";
			if ($this[0].tagName.toLocaleUpperCase() == "TH"){
				sortType = this.getSortType($this);
			}else{
				sortType = this.getSortType($this.parents());
			}
			 
			if(sortName=="xhibitPointSort"){
				this.model.search.xhibitPointSort(sortType);
			}
			this.sortSearchByCondition();
		},
		getSortType: function($this){
			var sortType = $this.attr("sort");
			if(!sortType){
				return "desc";
			}
			if(sortType == "asc"){
				return "desc";
			}
			
			if (sortType == "desc"){
				return "asc";
			}
			
			return "desc";
		},
		/**
		 * 显示新增/编辑框
		 * @param data
		 */
		showForm: function (data){
			
		},
		searchByCondition: function(day) {
			if (!day && day!=0){
				var start = $("#createTimeBegin").val();
				if(!start){
					return Lobibox.notify("info",{msg:"请选择您要查询的日期"})
				}
				this.model.search.createTimeBegin($("#createTimeBegin").val());
			}else{
				var now = new Date();
				var setDay = now.getDate()- (+day);
				var searchDay = this.fmt.date(new Date(now.setDate(setDay)).getTime(),'yyyy-MM-dd');
				this.model.search.createTimeBegin(searchDay);
			}
			
			this.list(1);
		},
		sortSearchByCondition: function(){
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
		}
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());