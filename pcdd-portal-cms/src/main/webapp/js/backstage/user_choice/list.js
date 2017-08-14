;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				userId:"",
				account:"",
				nickName:"",
				status:"",
				source:"",
				roomName:"",
				roomId:"",
				gameType:"",
				areaId:"",
				createTimeBegin:"",
				createTimeEnd:""
			},
			formEntity: {
				id:"",
				userId:"",
			    roomId:"",
			    roomLevel:"",
			    choiceNo:"",
			    choiceResult:"",
			    bili:"",
			    point:"",
			    realResult:"",
			    resultType:"",
			    getPoint:"",
			    createTime:"",
			    gameType:"",
			    isZhong:"",
			    biliId:"",
			    xhibitPoint:"",
			    account:"",
			    nickName:"",
			    userPhoto:[],
			    areaName:"",
			    roomName:"",
			    status:"",
			    accountPoint:""
			},
			recharge:{
				nickName:"",
				account:"",
				userId:"",
				rechargePoint:"",
				remark:""
			},
			totalXhibitPoint:"0.00",
			currManagerType:"",
			allAreaTypes:[],
			allRoomInfos:[],
			roomInfos:[{id:"",roomName:"请选择房间"}],
			areaTypes:[{id:"",areaName:"请选择房间类型"}],
			searchAreaTypes:[{id:"",areaName:"所有"}],
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
			img:function(url){
				return "<img src='" +this.img4Null(url,defaultUserIcon) + "' class='list_photo' />";
			},
			xhibitPoint:function(xhibitPoint){
				if(!xhibitPoint && xhibitPoint!=0){
					return "";
				}
				if(xhibitPoint>=0){
					return "<span style='color: green;font-size: 14px;font-weight: bold;'>+"+this.currency(xhibitPoint,{places: 2,symbol: ''})+"</span>";
				}else{
					return "<span style='color: red;font-size: 14px;font-weight: bold;'>"+this.currency(xhibitPoint,{places: 2,symbol: ''})+"</span>";
				}
				
			},
			result:function(item){
				if(item.isZhong == -1){
					return "未开奖";
				}else{
					return item.realResult+" "+item.resultType;
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
			
			util.stringToArray(data, "userPhoto");
			if(data.userPhoto && data.userPhoto.length == 0 ){
				data.userPhoto.push(defaultUserIcon);
			}
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		showRechargeForm: function(data){
			$("body").cover("正在加载数据......");
			var that = this;
			var $form = $("#rechargeForm"),validator = $form.data( "validator" );
			validator.resetForm();
			$form.find(".form-group").removeClass("has-error has-success");
			fillKOEntity( this.model.recharge, data || {});
			
			$("#recharge").modal("show");
			
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
			
			var msg = status==1?"恢复":"拉黑";
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
		 * 充值
		 */
		rechargeForm: function () {
			var that = this,
				data = ko.mapping.toJS(this.model.recharge);
			for ( var name in data ) {
				var type = $.type( data[name] );
				if ( type === "string" ) {
					data[name] = $.trim( data[name] );
				} else if ( type === "array" ) {
					data[name] = data[name].join( "," );
				}
			}
			if ( this.model.onFormSubmitting() ) {
				Lobibox.notify("warning",{msg:"正在提交数据......"});
				return;
			}
			
			if($("#rechargeForm").length>0){
				if(!$("#rechargeForm").valid()){
					return;
				}
			}
			
			this.model.onFormSubmitting(true);
			
			var $ladda = $(".ladda-button"),isHasLadda = $ladda.length > 0 ? true : false,laddaObject;
			if(isHasLadda){
				laddaObject = $ladda.ladda();
				laddaObject.ladda("start");
			}
			
			service.rechargePoint(data).then(function (resJson) {
				if(resJson.status && (resJson.status===Ajax.CODE.SUCCESS_DATA || resJson.status===Ajax.CODE.SUCCESS_NODATA)) {
					Lobibox.notify("success",{msg:"充值成功"});
					if (data.id) {
						that.updateAfter(data,resJson);
						if(that.isRefash()){
							that.list(that.model.search.pageNo());
						}
					} else {
						that.saveAfter(data,resJson);
						if(that.isRefash()){
							that.list(1);
						}
					}
					that.model.onFormSubmitting(false);
				} else if(resJson.status && resJson.status === Ajax.CODE.SERVER_EXCEPTION  || resJson.status === Ajax.CODE.PARAM_ERROR  || resJson.status === Ajax.CODE.SERVICE_EXCEPTION) {
					Lobibox.notify("warning",{msg:resJson.desc});
					that.model.onFormSubmitting(false);
					return;
				} else {
					Lobibox.notify("error",{msg:"充值失败"});
					that.model.onFormSubmitting(false);
				}
				if(laddaObject){
					laddaObject.ladda("stop");
				}
				that.hideModal();
				that.hideMask();
			}, function () {
				Lobibox.notify("error",{msg:"系统异常"});
				that.model.onFormSubmitting(false);
				that.hideModal();
				that.hideMask();
				if(laddaObject){
					laddaObject.ladda("stop");
				}
			}).always(function(){
				$("body").uncover();
			});;
		},
		addValidate:function(){
			$("#rechargeForm").find("[name='rechargePoint']").rules("add",{
				isNomalCurrencyofTwoDecimal:true,
				messages:{
					rechargePoint:{
						required:"充值金额必填",
						isNomalCurrencyofTwoDecimal:"请输入合法的充值金额"
					}
					
				}
			});
		},
		exportXls:function(){
			var exportData = this.model.dataList();
			window.open(fullPath+"backstage/userChoiceLog/exportExcel?pageSize=999999");
		},
		
		getTotalXhibitPoint: function(dataList){
			var totalXhibitPoint = 0;
			for (var i=0,len= dataList.length;i<len;i++){
				totalXhibitPoint+=dataList[i].xhibitPoint;
			}
			
			return totalXhibitPoint;
		},
		/**
		 * 取数据列表
		 * @param pageNo 非必填，不填写表示当前页码
		 * @param pageSize 非必填，不填写表示当前页数
		 */
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
					var totalXhibitPoint = resJson.entity;
					that.model.dataList(resJson.page.result);
					that.page(transformPage(resJson.page));
					that.model.checkItems([]);
					that.model.totalXhibitPoint(totalXhibitPoint);
					//that.initClickEvent();
					if(totalXhibitPoint>0){
						$("#totalXhibitPoint").removeClass().addClass("win").text(that.fmt.currency(totalXhibitPoint,{places: 2,symbol: ''}));
					}else {
						$("#totalXhibitPoint").removeClass().addClass("lose").text(that.fmt.currency(totalXhibitPoint,{places: 2,symbol: ''}));
					}
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
						that.page(transformPage(resJson.page));
						$("#totalXhibitPoint").removeClass().addClass("win").text("0.00")
					}
				} else {
					Lobibox.notify("error",{msg:"查询失败"});
				}
			},function(error){
				Lobibox.notify("error",{msg:"系统异常"});
			})
			.always(function(){
				$("body").uncover();
			});
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
		getRoomInfos: function(){

			var allRoomInfos = this.model.allRoomInfos().slice(0),that = this,areaId = this.model.search.areaId();
			
			that.model.roomInfos.splice(1,that.model.roomInfos().length-1);

			if(!areaId){
				return ;
			}
			
			if(allRoomInfos.length <= 0){
				return Lobibox.notify("warning",{msg:"暂无房间数据"});
			}
			
			$.each(allRoomInfos,function(i,v){
				if(v.areaId == areaId)
				that.model.roomInfos.push(v);
			})
			if(that.model.roomInfos().length <= 0){
				return Lobibox.notify("warning",{msg:"暂无对应房间数据"});
			}
			return;
		},
		roomInfosFocus: function(type){
			if(type==1){
				//搜索
				if(!this.model.search.areaId()){
					Lobibox.notify("info",{msg:"请先选择房间类型"})
					return;
				}else if(this.model.allRoomInfos().length <= 1 ){
					Lobibox.notify("info",{msg:"暂无对应房间数据,请联系管理员"})
					return ;
				}
				
			}else if(type==2){
				//修改
				
			}
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			var that = this;
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
			
			service.getAllRoomInfo({}).then(function(resJson){
				if(resJson.status === Ajax.CODE.SUCCESS_DATA){
					that.model.allRoomInfos(resJson.entity);
				} else if (resJson.status === Ajax.CODE.SUCCESS_NODATA) {
					Lobibox.notify("info",{msg:"暂无房间数据"});
				} else {
					Lobibox.notify("error",{msg:"查询房间失败"});
				}
			},function(){
				Lobibox.notify("error",{msg:"系统异常"});
			})
			this.list();
			this.initValidate();
			this.addValidate();
			ko.applyBindings(this);
			this.model.hasInited(true);
		}
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());