;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				biliName:"",
				gameType:"",
				areaId:"",
				status:""
			},
			formEntity: {
				id:"",
				gameType:"",
			    biliType:"",
			    biliName:"",
			    bili:"",
			    result:"",
			    areaId:"",
			    areaTypeName:"",
			    minPoint:"",
			    maxPoint:""
			},
			currManagerType:"",
			allAreaTypes:[],
			areaTypes:[{id:"",areaName:"请选择房间类型"}],
			searchAreaTypes:[{id:"",areaName:"所有"}]
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
					bili : {
						required : true,
						isCurrencyofTwoDecimal:true
					},
					minPoint:{
						isCurrencyofTwoDecimal:true
					},
					maxPoint:{
						isCurrencyofTwoDecimal:true
					}
				},
				messages : {
					bili : {
						required : "比例必填",
						isCurrencyofTwoDecimal: "请输入正确的比例"
					},
					minPoint:{
						isCurrencyofTwoDecimal:"请输入正确的最小投注金额"
					},
					maxPoint: {
						isCurrencyofTwoDecimal:"请输入正确的最大投注金额"
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
			fillKOEntity( this.model.search, { pageNo: pageNo, pageSize: pageSize} );
		},
		fmt  : {
			biliType: function(biliType){
				if(biliType=="1"){
					return "大小单双";
				}
				if(biliType=="2"){
					return "猜数字";
				}
				
				if(biliType == "3"){
					return "特殊玩法";
				}
				return "";
			}
		
		},
		/**
		 * 显示新增/编辑框
		 * @param data
		 */
		showForm: function (data){
			$("body").cover("正在加载数据......");
			this.model.validator.resetForm();
			var that = this;
			var $form = $("#form");
			$form.find(".form-group").removeClass("has-error").removeClass("has-success").removeClass("has-error has-success");
			fillKOEntity( this.model.formEntity, data || {});
			
			if(data.id){
				this.getAreaTypeByGameType();
				this.model.formEntity.areaId(data.areaId);
			}
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		searchByCondition: function() {
			this.list(1);
		},
		beforeSubmit: function () {
			this.submitForm();
		},
		afterUpload: function (resJson, type) {
			if(type==1){
				this.model.formEntity.roomPhoto.push(resJson.data.paths[0]);
			}
		},
		removeImg : function(type,index){
			if(type==1){
				this.model.formEntity.roomPhoto.splice(index, 1);
			}
		},
		getAreaTypeByGameType: function(){
			var allAreaTypes = this.model.allAreaTypes().slice(0),that = this,gameType = this.model.formEntity.gameType();
			
			that.model.areaTypes.splice(1,that.model.areaTypes().length-1);
			
			if(!gameType){
				return ;
			}
			
			if(allAreaTypes.length <= 0){
				return Lobibox.notify("warning",{msg:"暂无房间类型数据"});
			}
			
			$.each(allAreaTypes,function(i,v){
				if(v.gameType == gameType)
				that.model.areaTypes.push(v);
			})
			if(that.model.areaTypes().length <= 0){
				return Lobibox.notify("warning",{msg:"暂无对应房间类型数据"});
			}
			return;
		},
		getSearchAreaTypeByGameType: function(){
			var allAreaTypes = this.model.allAreaTypes().slice(0),that = this,gameType = this.model.search.gameType();
			
			that.model.searchAreaTypes.splice(1,that.model.searchAreaTypes().length-1);
			
			if(!gameType){
				return ;
			}
			
			if(allAreaTypes.length <= 0){
				return Lobibox.notify("warning",{msg:"暂无房间类型数据"});
			}
			
			$.each(allAreaTypes,function(i,v){
				if(v.gameType == gameType)
				that.model.searchAreaTypes.push(v);
			})
			if(that.model.searchAreaTypes().length <= 0){
				return Lobibox.notify("warning",{msg:"暂无对应房间类型数据"});
			}
			return;
		},
		areaTypeFocus: function(type){
			if(type==1){
				//搜索
				if(!this.model.search.gameType()){
					Lobibox.notify("info",{msg:"请先选择游戏类型"})
				}else if(this.model.searchAreaTypes().length <= 1 ){
					Lobibox.notify("info",{msg:"暂无对应房间类型数据,请联系管理员"})
				}
			}else if(type==2){
				//修改
				if(!this.model.formEntity.gameType()){
					Lobibox.notify("info",{msg:"请先选择游戏类型"})
				}else if(this.model.areaTypes().length <= 1 ){
					Lobibox.notify("info",{msg:"暂无对应房间类型数据,请联系管理员"})
				}
			}
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			var that = this;
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			this.list();
			service.getAreaType({}).then(function(resJson){
				if(resJson.status === Ajax.CODE.SUCCESS_DATA){
					that.model.allAreaTypes(resJson.entity);
				} else if (resJson.status === Ajax.CODE.SUCCESS_NODATA) {
					Lobibox.notify("info",{msg:"暂无房间类型数据"});
				} else {
					Lobibox.notify("error",{msg:"查询房间类型失败"});
				}
			},function(){
				Lobibox.notify("error",{msg:"系统异常"});
			});
			this.initValidate();
			ko.applyBindings(this);
			this.model.hasInited(true);
		}
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());