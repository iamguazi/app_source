;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				roomName:"",
				gameType:"",
				areaId:"",
				status:"",
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id:"",
				imGourpId:"",
			    gameType:"",
			    areaId:"",
			    roomName:"",
			    roomPhoto:[],
			    rebotMaxCount:"",
			    rebotMinCount:"",
			    peopleMaxCount:"",
			    perMaxPoint:"",
			    perMinPoint:"",
			    allMaxPoint:"",
			    status:"",
			    areaTypeName:"",
			    realUser:"",
			    robot:"",
			    minPeopleCount:"",
			    password:""
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
					rebotMinCount: {
						digits   : true
					},
					rebotMaxCount: {
						digits:true
					},
					peopleMaxCount: {
						digits:true
					},
					minPeopleCount:{
						digits:true
					},
					perMaxPoint: {
						isCurrencyofTwoDecimal:true
					},
					perMinPoint: {
						isCurrencyofTwoDecimal:true
					},
					allMaxPoint: {
						isCurrencyofTwoDecimal:true
					}
				},
				messages : {
					rebotMinCount : {
						digits : "请输入合法机器人最小数量"
					},
					rebotMaxCount: {
						digits:"请输入合法机器人最大数量"
					},
					peopleMaxCount: {
						digits : "请输入合法人数上限"
					},
					minPeopleCount:{
						digits:"请输入合法房间最小人数"
					},
					perMaxPoint: {
						isCurrencyofTwoDecimal: "请输入合法单注最高金额"
					},
					perMinPoint: {
						isCurrencyofTwoDecimal: "请输入合法单注最低金额"
					},
					allMaxPoint: {
						isCurrencyofTwoDecimal: "请输入合法总注封顶金额"
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
			status : function(status){
				if(status=="1"){
					return "<span class='label label-success'>启用</span>";
				}
				if(status=="0"){
					return "<span class='label label-default'>拉黑</span>";
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
			if(data.id) {
				if(data && data.roomPhoto) {
					if($.type(data.roomPhoto) === "string") {
						data.roomPhoto = (data.roomPhoto || "") .split(",");	
					}
				}else {
					data.roomPhoto = [];
				}
			} else {
				data.roomPhoto=[];
				data.password=-1;
			}
			fillKOEntity( this.model.formEntity, data || {});
			if(data.id){
				this.getAreaTypeByGameType();
				this.model.formEntity.areaId(data.areaId);
			}
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		searchByCondition: function() {
			this.model.search.createTimeBegin($("#createTimeBegin").val());
			this.model.search.createTimeEnd($("#createTimeEnd").val());
			this.list(1);
		},
		formValidSuccess:function(){
			if(this.model.formEntity.rebotMinCount() > this.model.formEntity.rebotMaxCount()){
				Lobibox.notify("warning",{msg:"机器人最小个数不能大于机器人最大个数"})
				return false;
			}
		    return true;
		},
		beforeSubmit: function () {
			/*if(this.model.formEntity.roomPhoto().length<=0 ){
				return Lobibox.notify("warning",{msg:"请先上传房间图标"});
			}*/
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
			
			var msg = status==1?"启用":"拉黑";
			var that = this;
			service.updateStatus(data).then(function(data){
				if(data && data==true){
					Lobibox.notify("success",{msg:msg+"成功"});
					that.list(that.model.search.pageNo());
				}else{
					Lobibox.notify("error",{msg:(msg+"失败")});
				}
			},function(){
				Lobibox.notify("error",{msg:msg+"异常"});
			});
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			var that = this;
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			service.getAreaType({}).then(function(resJson){
				if(resJson.status === Ajax.CODE.SUCCESS_DATA){
					that.model.allAreaTypes(resJson.entity);
					that.list();
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