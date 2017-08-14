;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				gameType:"",
				openTimeBegin:"",
				openTimeEnd:"",
				isGive:""
			},
			formEntity: {
				id:"",
				userId:"",
			    gameType:"",
			    openTime:"",
			    gameNum:"",
			    gameResult:"",
			    gameResultDesc:"",
			    gameResultDesc1:"",
			    gameResultDesc2:"",
			    gameResultDesc3:"",
			    gameResultDesc4:"",
			    resultType:"",
			    xhibitPoint:"",
			    totalPoint:"",
			    isGive:"",
			    givePoint:"",
			    isBaozi:"",
			    color:"",
			    source:""
			},
			recharge:{
				nickName:"",
				account:"",
				userId:"",
				rechargePoint:""
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
			return  {
				rules:{
					gameResult:{
						required:true,
						digits:true
					},
					gameResultDesc1:{
						required:true,
						digits:true
					},
					gameResultDesc2:{
						required:true,
						digits:true
					},
					gameResultDesc3:{
						required:true,
						digits:true
					}
//					,totalPoint:{
//						required:true,
//						isCurrencyofTwoDecimal:true
//					}
				},
				messages:{
					gameResult:{
						required:"开奖号码(数字)必填",
						digits:"输入合法开奖号码(数字)"
					},
					gameResultDesc1:{
						required:"因子必填",
						digits:"输入合法因子"
					},
					gameResultDesc2:{
						required:"因子必填",
						digits:"输入合法因子"
					},
					gameResultDesc3:{
						required:"因子必填",
						digits:"输入合法因子"
					},
					gameResultDesc4:{
						required:"结果必填",
						digits:"输入合法结果"
					}
//				    ,totalPoint:{
//						required:"下注总金额必填",
//						isCurrencyofTwoDecimal:"输入合法下注总金额"
//					}
				}
				
			};
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
				if(url){
					return "<img src='" +imgShowRoot+url + "' class='list_photo' />";
				}else{
					return "<img src='" + defaultUserIcon + "' class='list_photo' />";
				}
			},
			resultType:function(resultType){
				if(resultType==1){
					
				}
				
				if(resultType==2){
					
				}
				
				return "";
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
			},
			isGive: function(isGive){
				if(isGive == "1" ){
					return "是";
				}
				if(isGive == "0" ){
					return "否";
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
			
			var that = this;
			var $form = $("#form"),validator = $form.data( "validator" );
			if (validator){
				validator.resetForm();
			}
			util.resetRemote($form.find("[name='gameNum']"));
			util.resetRemote($form.find("[name='gameType']"));
			$form.find(".form-group").removeClass("has-error has-success");
			 data = data || {source:2,userId:0,xhibitPoint:0,isGive:0,totalPoint:0}
			fillKOEntity( this.model.formEntity, data );
			var gameResultDesc = data.gameResultDesc;
			if (gameResultDesc){
				var gameResultDescArr = gameResultDesc.replace(new RegExp("\\+","g"),",").replace(new RegExp("=","g"),",").split(",");
				this.model.formEntity.gameResultDesc1(gameResultDescArr[0]);
				this.model.formEntity.gameResultDesc2(gameResultDescArr[1]);
				this.model.formEntity.gameResultDesc3(gameResultDescArr[2]);
				this.model.formEntity.gameResultDesc4(gameResultDescArr[3]);
			}
			if(data.openTime){
				this.model.formEntity.openTime(this.fmt.date(data.openTime));
			}
			this.bindOrRemoveRemoteCheck();
//			this.model.formEntity.source("2");
//			this.model.formEntity.userId(0);
//			this.model.formEntity.xhibitPoint(0);
			
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		changeGameType:function(){
			this.bindOrRemoveRemoteCheck();
			var $form = $("#form"),validator = $form.data( "validator" );
			if (validator){
				var $gameNum = $form.find("[name='gameNum']"),$gameType = $form.find("[name='gameType']");
				util.resetRemote($gameNum);
				util.resetRemote($gameType);
				validator.element($gameNum);
			}
		},
		bindOrRemoveRemoteCheck: function(){
			var $form = $("#form"),gameType = $("#gameType").val(),$gameNun = $("#gameNum"),that = this;
			
			var rules = $gameNun.rules();
			if (!$.isEmptyObject(rules)){
				$gameNun.rules("remove");
			}
			
			if(gameType){
				$gameNun.rules("add",{
					required:true,
					digits:true,
					remote:{
						url: fullPath + "backstage/gameOpenLog/checkGameNum",
						type: "post",
						dataType: "json",           //接受数据格式   
					    data: {                     //要传递的数据
					    	gameNum: function(){
					    		return $.trim($("#gameNum").val());
					    	},
					        gameType: function () {
					        	return $("#gameType").val();
					        },
					        id: function () {
					        	return that.model.formEntity.id();
					        }
					    }
					},
					messages:{
						required:"期数必填",
						digits:"输入合法期数",
						remote:"期数已存在"
					}
				});
				
			}else{
				$gameNun.rules("add",{
					required:true,
					digits:true,
					messages:{
						required:"期数必填",
						digits:"输入合法期数"
					}
				});
			}
		},
		/**
		 * 显示详情
		 * @param data
		 */
		showDetail: function (data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			
			fillKOEntity( this.model.formEntity, data || {});
			
			$("#detail").modal("show");
			
			$("body").uncover();
		},
		openGame: function(id){
			var that = this;
			service.openGame({id:id}).then(function(resJson){
				if(resJson.status && (resJson.status===Ajax.CODE.SUCCESS_DATA || resJson.status===Ajax.CODE.SUCCESS_NODATA)){
					Lobibox.notify("success",{msg:"开奖成功"});
					that.list(that.model.search.pageNo());
				}else if(resJson.status && resJson.status === Ajax.CODE.SERVER_EXCEPTION  || resJson.status === Ajax.CODE.PARAM_ERROR  || resJson.status === Ajax.CODE.SERVICE_EXCEPTION){
					that.showInfo(resJson.desc);
					Lobibox.notify("error",{msg:resJson.desc});
				}else{
					Lobibox.notify("error",{msg:"开奖失败"});
				}
			},function(){
				Lobibox.notify("error",{msg:"系统异常"});
			});
		},
		searchByCondition: function() {
			this.model.search.openTimeBegin($("#createTimeBegin").val());
			this.model.search.openTimeEnd($("#createTimeEnd").val());
			this.list(1);
		},
		beforeSubmit: function () {
			var gameResultDesc = this.model.formEntity.gameResultDesc1() +"+"+
			this.model.formEntity.gameResultDesc2()+"+"+
			this.model.formEntity.gameResultDesc3()+"="+
			this.model.formEntity.gameResultDesc4();
			this.model.formEntity.gameResultDesc(gameResultDesc);
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