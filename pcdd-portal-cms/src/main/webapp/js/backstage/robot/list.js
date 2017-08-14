;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				account:"",
				userType:"2",
				nickName:"",
				status:"",
				createTimeBegin:"",
				createTimeEnd:""
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
			    xhibitPoint:""
			},
			recharge:{
				nickName:"",
				account:"",
				userId:"",
				rechargePoint:""
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
				return "<img src='" +this.img4Null(url,defaultUserIcon)  + "' class='list_photo' />";
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
			data.userType = 2;
			data.bandType = 0;
			
			if(data.id){
				data.sex = data.sex+"";
				
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
		searchByCondition: function() {
			this.model.search.createTimeBegin($("#createTimeBegin").val());
			this.model.search.createTimeEnd($("#createTimeEnd").val());
			this.list(1);
		},
		beforeSubmit: function () {
			this.submitForm();
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
		addValidate:function(){
			var $form = $("#form"),that = this;
			$form.find("[name='account']").rules("add",{
				isAccount:true
			});
			$form.find("[name='password']").rules("add",{
				isLettersOrNum:true
			});
			$form.find("[name='account']").rules("add", {
				remote: {
					url: fullPath + "backstage/userInfo/isAccountExits",     //后台处理程序
				    type: "post",               //数据发送方式
				    dataType: "json",           //接受数据格式   
				    data: {                     //要传递的数据
				        account: function () {
				        	return $.trim(that.model.formEntity.account())
				        },
				        id: function () {
				        	return that.model.formEntity.id();
				        }
				    }
				},
				messages: {
					remote: "该用户名已存在"
				}
			});
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
		exportExcel: function (size) {
			var data =  ko.mapping.toJS(this.model.search);
			if (!size) {
				data.pageNo = 1;
				data.pageSize = 9999999;
			} else {
				data.pageSize = size;
			}
			var params = util.jsonToQueryString(data, "&");
			window.open(fullPath + "backstage/robot/exposeExcel?" + params, "_blank");
		},
		/**
		 * 验证图片
		 * @param file
		 * @returns {Boolean}
		 */
		validateXlsUpload: function (file) {
			var ua = window.navigator.userAgent;
			var src = file.value;
			var ext = (src.substring(src.indexOf(".") + 1) || "").toLowerCase();
			var fileSize = 0;
			
			if (["xls", "xlsx"].contain(ext) !== true) {
				Lobibox.notify("info",{msg:"文件格式错误,文件必须是.xls或.xlsx后缀的文件"});
				return false;
			}
			
			if ( ua.indexOf("MSIE") >= 1 ) {
				var dynImg = document.getElementById("dynImg");
				dynImg.dynsrc = src;
				fileSize = dynImg.fileSize;
			} else if (file.files) {
				fileSize = file.files[0].size;
			}
			if (fileSize == -1) {
				Lobibox.notify("info",{msg:"请选择要上传的文件!"});
				return false;
			} else if (fileSize > this.def.imgSize) {
				Lobibox.notify("info",{msg:"excel文件不得大于2M"});
				return false;
			}
			return true;
		},
		uploadXls: function ( fileInput, bussinessDir, type ) {
			var that = this;
			type = type || 1;
			if (!this.validateXlsUpload(fileInput)) {
				return false;
			}
			$("body").cover("正在导入......");
			$.ajaxFileUpload({						// 异步保存图片   addForm.attr("action")
				url : fullPath + "backstage/robot/importExcel",
				secureuri:false,
				fileElementId : fileInput.id,
				dataType : 'json',
				data : {
					bussinessDir: bussinessDir
				},
				success: function (resJson, status){
					if(resJson.status === Ajax.CODE.SUCCESS_NODATA){
						Lobibox.notify("success",{msg:"导入数据成功"});
						that.list(1);
					}else{
						if(resJson.desc){
							resJson.desc = resJson.desc.replace(/\r\n/g,"<br/>");
						}
						Lobibox.notify("error",{msg:resJson.desc,delay:false});
						$(".lobibox-notify.lobibox-notify-error").css({
							"max-height":"500px",
							"overflow-y":"auto"
						});
					}
					$("body").uncover();
				},
				error: function (data, status, e){
					Lobibox.notify("error",{msg:"上传异常",delay:false});
					$("body").uncover();
				}
			});
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			this.list();
			var that = this;
			service.getUserLevels({}).then(function(resJson){
				if(resJson.status === Ajax.CODE.SUCCESS_DATA){
					that.model.levelOptions ( resJson.entity );
				} else if (resJson.status === Ajax.CODE.SUCCESS_NODATA) {
					Lobibox.notify("info",{msg:"暂无等级数据,请先添加等级数据"});
				} else {
					Lobibox.notify("error",{msg:"查询等级失败"});
				}
			},function(){
				Lobibox.notify("error",{msg:"系统异常"});
			});
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