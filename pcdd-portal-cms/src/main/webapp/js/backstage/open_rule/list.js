;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				gameType:"",
				areaId:""
			},
			formEntity: {
				id:"",
				gameType:"",
			    areaId:"",
			    typeName:"",
			    start1:"",
			    end1:"",
			    bili1:"",
			    start2:"",
			    end2:"",
			    bili2:"",
			    start3:"",
			    end3:"",
			    bili3:"",
			    start4:"",
			    end4:"",
			    bili4:""
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
					start1 : {
						required : true,
						isCurrencyofTwoDecimal:true,
						isSmaller:"#end1"
					},
					end1:{
						required : true,
						isCurrencyofTwoDecimal:true,
						isBigger:"#start1"
					},
					bili1:{
						required : true,
						isCurrencyofTwoDecimal:true
					},
					start2 : {
						required : true,
						isCurrencyofTwoDecimal:true,
						isSmaller:"#end2"
					},
					end2:{
						required : true,
						isCurrencyofTwoDecimal:true,
						isBigger:"#start2"
					},
					bili2:{
						required : true,
						isCurrencyofTwoDecimal:true
					},
					start3 : {
						required : true,
						isCurrencyofTwoDecimal:true,
						isSmaller:"#end3"
					},
					end3:{
						required : true,
						isCurrencyofTwoDecimal:true,
						isBigger:"#start3"
					},
					bili3:{
						required : true,
						isCurrencyofTwoDecimal:true
					},
					start4 : {
						required : true,
						isCurrencyofTwoDecimal:true,
						isSmaller:"#end4"
					},
					end4:{
						required : true,
						isCurrencyofTwoDecimal:true,
						isBigger:"#start4"
					},
					bili4:{
						required : true,
						isCurrencyofTwoDecimal:true
					}
				},
				messages : {
					start1 : {
						required : "起始金额必填",
						isCurrencyofTwoDecimal: "请输入正确的起始金额",
						isSmaller:"起始金额不能大于截止金额"
					},
					end1:{
						required : "截止金额必填",
						isCurrencyofTwoDecimal:"请输入正确的截止金额",
						isBigger:"截止金额不能小于与起始金额"
					},
					bili1:{
						required : "赔率1必填",
						isCurrencyofTwoDecimal:"请输入正确的赔率1"
					},
					start2 : {
						required : "起始金额必填",
						isCurrencyofTwoDecimal: "请输入正确的起始金额",
						isSmaller:"起始金额不能大于截止金额"
					},
					end2:{
						required : "截止金额必填",
						isCurrencyofTwoDecimal:"请输入正确的截止金额",
						isBigger:"截止金额不能小于与起始金额"
					},
					bili2:{
						required : "赔率2必填",
						isCurrencyofTwoDecimal:"请输入正确的赔率2"
					},
					start3 : {
						required : "起始金额必填",
						isCurrencyofTwoDecimal: "请输入正确的起始金额",
						isSmaller:"起始金额不能大于截止金额"
					},
					end3:{
						required : "截止金额必填",
						isCurrencyofTwoDecimal:"请输入正确的截止金额",
						isBigger:"截止金额不能小于与起始金额"
					},
					bili3:{
						required : "赔率3必填",
						isCurrencyofTwoDecimal:"请输入正确的赔率3"
					},
					start4 : {
						required : "起始金额必填",
						isCurrencyofTwoDecimal: "请输入正确的起始金额",
						isSmaller:"起始金额不能大于截止金额"
					},
					end4:{
						required : "截止金额必填",
						isCurrencyofTwoDecimal:"请输入正确的截止金额",
						isBigger:"截止金额不能小于与起始金额"
					},
					bili4:{
						required : "赔率4必填",
						isCurrencyofTwoDecimal:"请输入正确的赔率4"
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
			areaName:function(areaId){
				var areaData = viewModel.model.allAreaTypes();
				for(var i=0,len=areaData.length;i<len;i++){
					if(areaId == areaData[i].id){
						return areaData[i].areaName;
					}
				}
				return "";
			},
			scope:function(start,end){
				return this.currency(start,{places: 2,symbol: '',thousand: ''})+"~"+this.currency(end,{places: 2,symbol: '',thousand: ''})
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