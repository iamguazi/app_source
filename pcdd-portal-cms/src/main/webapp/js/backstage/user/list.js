;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				userType:"1",
				nickName:"",
				status:"",
				pointSort:"",
				account:"",
				xhibitPointSort:"",
				searchByTime:"",
				createTimeBegin:"",
				createTimeEnd:"",
				code:parentCode
			},
			formEntity: {
				id:"",
				account:"",
			    password:"",
			    userPhoto:[],
			    detailUserPhoto:[],
			    nickName:"",
			    sex:"",
			    mobile:"",
			    point:"",
			    bandId:"",
			    bandType:"",
			    registrationId:"",
			    code:"",
			    status:"",
			    imAccount:"",
			    level:"",
			    levelName:"",
			    userType:"",
			    xhibitPoint:"",
			    ipAddr:""
			},
			recharge:{
				nickName:"",
				account:"",
				userId:"",
				rechargePoint:"",
				remark:""
			},
			modifyParentId:{
				userId:"",
				parentId:""
			},
			notice: {
				title:"",
		        content:"",
		        noticeType:"",
		        userId:""
			},
			currManagerType:"",
			validator:{},
			levelOptions:""
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
			isOnline:function(loginTime){
				if(!loginTime){
					return "<span class='label label-default'>否</span>";
				}
				var time = new Date().getTime();
				if(Math.abs(loginTime-time) <= 180000){
					return "<span class='label label-success'>是</span>";
				}else{
					return "<span class='label label-default'>否</span>";
				}
				
			},
			status:function(status){
				if( status == 0 ){
					return "<span class='label label-default'>拉黑</span>";
				}
				
				if( status == 1 ){
					return "<span class='label label-success'>正常</span>";
				}
				return "";
			},
			img:function(url){
				return "<img src='" +this.img4Null(url,defaultUserIcon) + "' class='list_photo' />";
			},
			level :function(level ){
				if(!level && level != 0 ){
					return "";
				}
				return "LV"+level;
			},
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
		/**
		 * 显示新增/编辑框
		 * @param data
		 */
		showForm: function (data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			var $form = $("#form"),validator = $form.data( "validator" );
			validator.resetForm();
			util.resetRemote($form.find("[name='account']"));
			$form.find(".form-group").removeClass("has-error has-success");
			
			util.stringToArray(data, "userPhoto");
			util.stringToArray(data, "detailUserPhoto");
			data.detailUserPhoto = data.userPhoto.slice(0);
			if(data.detailUserPhoto && data.detailUserPhoto.length == 0 ){
				data.detailUserPhoto.push(defaultUserIcon);
			}
			data.userType = 1;
			data.bandType = 0;
			
			if(data.id){
				if(data.sex){
					data.sex = data.sex+"";
				}else{
					data.sex = "";
				}
				
			}else{
				data.point = 0;
				data.xhibitPoint = 0;
				data.sex = "";
//				data.level = 1;
				data.status = 1;
			}
			
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		/**
		 * 显示详情
		 */
		showDetail: function (data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			util.stringToArray(data, "userPhoto");
			util.stringToArray(data, "detailUserPhoto");
			data.detailUserPhoto = data.userPhoto.slice(0);
			if(data.detailUserPhoto && data.detailUserPhoto.length == 0 ){
				data.detailUserPhoto.push(defaultUserIcon);
			}
			if(data.sex){
				data.sex = data.sex+"";
			}else{
				data.sex = "";
			}
			
			
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#detail").modal("show");
			
			$("body").uncover();
		},
		/**
		 * 显示帐变详情
		 */
		showChangePointLog: function (data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			
			var showData = (data || {});
			$("#pointChangeLogIframe").prop("src",fullPath+"backstage/pointChangeLog/toIndex?userId="+data.id+"&time="+new Date().getTime());
			
			$("#changePointLog").modal("show");
			
			$("body").uncover();
		},
		/**
		 * 显示提现详情
		 */
		showWithdraswaLog: function (data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			
			var showData = (data || {});
			$("#withdraswaLogsIframe").prop("src",fullPath+"backstage/withdrawalsLogs/toIndex?userId="+data.id+"&account="+data.account+"&nickName="+data.nickName+"&time="+new Date().getTime());
			
			$("#withdraswaLogs").modal("show");
			
			$("body").uncover();
		},
		/**
		 * 显示提现详情
		 */
		showAccountInfo: function (data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			
			var showData = (data || {});
			$("#userBankInfosIframe").prop("src",fullPath+"backstage/userBankInfos/toIndex?userId="+data.id+"&time="+new Date().getTime());
			
			$("#userBankInfos").modal("show");
			
			$("body").uncover();
		},
		/**
		 * 充值
		 */
		showRechargeForm: function(data){
			$("body").cover("正在加载数据......");
			var that = this;
			var $form = $("#rechargeForm"),validator = $form.data( "validator" );
			validator.resetForm();
			$form.find(".form-group").removeClass("has-error has-success");
			this.model.recharge.userId(data.id);
			this.model.recharge.nickName(data.nickName);
			this.model.recharge.account(data.account);
			this.model.recharge.rechargePoint("");
			this.model.recharge.remark("");
			$("#recharge").modal("show");
			
			$("body").uncover();
		},
		/**
		 * 显示修改上级页面
		 */
		showModifyParentId: function(data){
			$("body").cover("正在加载数据......");
			var that = this;
			var $form = $("#modifyParentIdForm"),validator = $form.data( "validator" );
			validator.resetForm();
			$form.find(".form-group").removeClass("has-error has-success");
			this.model.modifyParentId.userId(data.id);
			this.model.modifyParentId.parentId("");
			$("#modifyParentId").modal("show");
			
			$("body").uncover();
		},
		/**
		 * 通知页面
		 */
		showNoticeForm: function (data){
			$("body").cover("正在加载数据......");
			var that = this;
			var $form = $("#noticeForm"),validator = $form.data( "validator" );
			validator.resetForm();
			$form.find(".form-group").removeClass("has-error has-success");
			fillKOEntity( this.model.notice, data || {});
			this.model.notice.userId(data.id);
			this.model.notice.noticeType(2);
			this.model.notice.title("");
			CKEDITOR.instances.content.setData("");
			$("#noticeModal").modal("show");
			$("body").uncover();
		},
		searchByCondition: function() {
			this.model.search.createTimeBegin($("#createTimeBegin").val());
			this.model.search.createTimeEnd($("#createTimeEnd").val());
			this.model.search.searchByTime("");
			this.list(1);
		},
		searchByTime : function(type){
			this.model.search.searchByTime(type);
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
		 * 重置密码
		 */
		resetPassword : function(id){
			this.hideModal();
			var that = this;
			service.resetPassword({id:id}).then(function(resJson){
				if(resJson.status && (resJson.status===Ajax.CODE.SUCCESS_DATA || resJson.status===Ajax.CODE.SUCCESS_NODATA)){
					Lobibox.notify("success",{msg:"重置密码成功"});
					that.list(that.model.search.pageNo());
				}else{
					Lobibox.notify("error",{msg:"重置密码失败"});
				}
			},function(){
				Lobibox.notify("error",{msg:"重置密码异常"});
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
			});
		},
		/**
		 * 修改有用户code
		 */
		modifyParentIdForm: function () {
			var that = this,
				data = ko.mapping.toJS(this.model.modifyParentId);
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
			
			if($("#modifyParentIdForm").length>0){
				if(!$("#modifyParentIdForm").valid()){
					return;
				}
			}
			
			this.model.onFormSubmitting(true);
			
			var $ladda = $(".ladda-button"),isHasLadda = $ladda.length > 0 ? true : false,laddaObject;
			if(isHasLadda){
				laddaObject = $ladda.ladda();
				laddaObject.ladda("start");
			}
			
			service.modifyParentId(data).then(function (resJson) {
				if(resJson.status && (resJson.status===Ajax.CODE.SUCCESS_DATA || resJson.status===Ajax.CODE.SUCCESS_NODATA)) {
					Lobibox.notify("success",{msg:"修改成功"});
					if (data.id) {
						if(that.isRefash()){
							that.list(that.model.search.pageNo());
						}
					} else {
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
					Lobibox.notify("error",{msg:"修改失败"});
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
			});
		},
		/**
		 * 通知
		 */
		noticeForm: function () {
			var content = CKEDITOR.instances["content"].getData();
			this.model.notice.content(content);
			$("#content").text(content).val(content).html(content);
			var that = this,
				data = ko.mapping.toJS(this.model.notice);
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
			
			if($("#noticeForm").length>0){
				if(!$("#noticeForm").valid()){
					return;
				}
			}
			
			this.model.onFormSubmitting(true);
			
			var $ladda = $(".ladda-button"),isHasLadda = $ladda.length > 0 ? true : false,laddaObject;
			if(isHasLadda){
				laddaObject = $ladda.ladda();
				laddaObject.ladda("start");
			}
			
			service.notice(data).then(function (resJson) {
				if(resJson.status && (resJson.status===Ajax.CODE.SUCCESS_DATA || resJson.status===Ajax.CODE.SUCCESS_NODATA)) {
					Lobibox.notify("success",{msg:"通知发送成功"});
					that.model.onFormSubmitting(false);
				} else if(resJson.status && resJson.status === Ajax.CODE.SERVER_EXCEPTION  || resJson.status === Ajax.CODE.PARAM_ERROR  || resJson.status === Ajax.CODE.SERVICE_EXCEPTION) {
					Lobibox.notify("warning",{msg:resJson.desc});
					that.model.onFormSubmitting(false);
					return;
				} else {
					Lobibox.notify("error",{msg:"通知发送失败"});
					that.model.onFormSubmitting(false);
				}
				if(laddaObject){
					laddaObject.ladda("stop");
				}
				that.hideModal();
			}, function () {
				Lobibox.notify("error",{msg:"系统异常"});
				that.model.onFormSubmitting(false);
				that.hideModal();
				if(laddaObject){
					laddaObject.ladda("stop");
				}
			});
		},
		addValidate:function(){
			this.noticeValidate();
			$("#rechargeForm").find("[name='rechargePoint']").rules("add",{
				isNomalCurrencyofTwoDecimal:true,
				messages:{
					rechargePoint:{
						required:"充值金额必填",
						isNomalCurrencyofTwoDecimal:"请输入合法的充值金额"
					}
					
				}
			});
			$("#modifyParentIdForm").find("[name='parentId']").rules("add",{
				digits:true,
				messages:{
					rechargePoint:{
						required:"转移的用户ID",
						digits:"请输入合法的转移的用户ID"
					}
					
				}
			});
			var $form = $("#form"),that = this;
			
//			$form.find("[name='account']").rules("add",{
//				isAccount:true
//			});
			$form.find("[name='password']").rules("add",{
				isLettersOrNum:true
			});
//			$form.find("[name='account']").rules("add", {
//				remote: {
//					url: fullPath + "backstage/userInfo/isAccountExits",     //后台处理程序
//				    type: "post",               //数据发送方式
//				    dataType: "json",           //接受数据格式   
//				    data: {                     //要传递的数据
//				        account: function () {
//				        	return $.trim(that.model.formEntity.account())
//				        },
//				        id: function () {
//				        	return that.model.formEntity.id();
//				        }
//				    }
//				},
//				messages: {
//					remote: "该用户名已存在"
//				}
//			});
			
		},
		noticeValidate: function(){
			var $form = $( "#noticeForm" );
            var opts = {
            	errorElement: "span", 
            	submitHandler: function ( form ) {
            		//$( form ).find( ".btn-submit" ).click();
            	},
                highlight: function( element ) {
                    jQuery( element ).closest( '.form-group' )
                        .removeClass( 'has-success' )
                        .addClass( 'has-error' );
                },
                success: function( element ) {
                    jQuery( element ).closest( '.form-group' )
                        .removeClass( 'has-error' ).addClass("has-success");
                },
                rules:{
                	content:{
                		required:true
                	}
                },
            	messages:{
            		content:{
            			required:"内容必填"
            		}
            	},
            	ignore:""
            };
            	opts.errorPlacement = function( $error, $element ) {
            		$error.text( $error.text() );
            		$element.parent().append($error);
                }
            	$form.validate( opts );
		},
		afterUpload: function (resJson, type) {
			if(type==1){
				this.model.formEntity.userPhoto.push(resJson.data.paths[0]);
			}
		},
		removeImg : function(type,index){
			if(type==1){
				this.model.formEntity.userPhoto.splice(index, 1);
			}
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			
			var that = this;
			service.getUserLevels({}).then(function(resJson){
				if(resJson.status === Ajax.CODE.SUCCESS_DATA){
					that.model.levelOptions ( resJson.entity );
				} else if (resJson.status === Ajax.CODE.SUCCESS_NODATA) {
					Lobibox.notify("info",{msg:"暂无等级数据,请先添加等级数据"});
				} else {
					Lobibox.notify("error",{msg:"查询等级失败"});
				}
				that.list();
			},function(){
				$("body").uncover();
				Lobibox.notify("error",{msg:"系统异常"});
			});
//			this.list();
			this.initValidate();
			this.addValidate();
			ko.applyBindings(this);
			this.model.hasInited(true);
		},
		serachSort : function(sortName,event,$root){
			var $this = $(event.target);
			var sortType = "";
			if ($this[0].tagName.toLocaleUpperCase() == "TH"){
				sortType = this.getSortType($this);
			}else{
				sortType = this.getSortType($this.parents());
			}
			
			 
			if(sortName=="pointSort"){
				this.model.search.pointSort(sortType);
				this.model.search.xhibitPointSort("");
			}else if(sortName=="xhibitPointSort"){
				this.model.search.xhibitPointSort(sortType);
				this.model.search.pointSort("");
			}
			this.searchByCondition();
		},
		getSortType: function($this){
			var sortType = $this.attr("sort");
			if(!sortType){
//				$this.attr("sort","desc");
				return "desc";
			}
			if(sortType == "asc"){
//				$this.removeAttr("sort");
				return "";
			}
			
			if (sortType == "desc"){
//				$this.attr("sort","asc");
				return "asc";
			}
			
			return "";
		}
	});
	
	$(function(){
		CKEDITOR.replace('content',{
			customConfig:fullPath+"js/lib/ckeditor/config/normal.js"
		});
		viewModel.initViewModel();
	});
	
}());