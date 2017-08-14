;(function () {
	
	
	$.extend(true, viewModel, {
		model: {
			search: {
				id:"",
				name:"",
				status:""
			},
			formEntity: {
				id: "", 
				name:"",
                passwd:"",
                type:"",
			    status:"",
			    menuIds:"",
			    parentId:""
			},
			currManagerType:"",
			permissions:"",
			treeId:"permissions"
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
					name : {
						required : true,
						isLettersOrNum : true,
						remote : {
							url : fullPath+"backstage/manager/checkIsExist",
							type : "post",
							dataType : "json",
							// async:false,
							data : {
								id : function() {
									return that.model.formEntity.id();
								},
								managerName: function() {
									return that.model.formEntity.name();
								},
							}
						}
					},
				},
				messages : {
					name : {
						required : "管理员名称必填",
						isLettersOrNum : "只能包含字符、数字。",
						remote : "管理员名称已存在"
					},
				}
			}
		},
		fmt  : {
			status : function(status){
				if(status=="1"){
					return "<span style='color:#5cb85c;font-weight:bold;' >启用</span>";
				}
				if(status=="0"){
					return "<span style='color:#d9534f;font-weight:bold; '>禁用</span>";
				}
				return "";
			},
			type : function(type){
				if(type=="super"){
					return "超级管理员";
				}
				if(type=="normal"){
					return "管理员";
				}
				return "";
			}
		},
		/**
		 * 清空查询内容
		 */
		clearSearch: function () {
			var pageNo = this.model.search.pageNo();
			var pageSize = this.model.search.pageSize();
			fillKOEntity( this.model.search, { pageNo: pageNo, pageSize: pageSize,name:""} );
		},
		/**
		 * 显示新增/编辑框
		 * @param data
		 */
		showForm: function (data){
			$("body").cover("正在加载数据......");
			
			var that = this;
			var $form = $("#form");
			that.model.validator.resetForm();
			util.resetRemote($form.find("[name='name']"));
			$form.find(".form-group").removeClass("has-error has-success");
			
			var treeId = this.model.treeId();
			this.initTree(treeId, this.setting);
			
			if(data.id) {
				$("#editorLabel").html("编辑");
				this.forEachPromissions(data.menuIds);
			} else {
				$("#editorLabel").html("新增");
			}
			
			fillKOEntity( this.model.formEntity, data);
			$("#editor").modal("show");
			
			$("body").uncover();
		},
		searchByCondition: function() {
			this.list(1);
		},
		beforeSubmit: function () {
			if(!this.isLessSelectOne()) {
				this.showInfo("请至少选择一个权限");
				return ;
			}
			this.model.formEntity.menuIds(this.getSelectMenuIds());
			this.submitForm();
		},
		
		getCheckNodes : function(){
			var treeObj = $.fn.zTree.getZTreeObj(this.model.treeId());
			return treeObj.getCheckedNodes(true);
		},
		isLessSelectOne : function(){
			var nodes = this.getCheckNodes();
			if(nodes.length<=0){
				return false;
			}
			return true;
		},
		getSelectMenuIds : function(){
			var menuIds = "",nodes= this.getCheckNodes();
			for(var i=0,len=nodes.length;i<len;i++){
				if(i==0){
					menuIds +=nodes[i].id;
				}else{
					menuIds +=","+nodes[i].id;
				}
			}
			return menuIds;
		},
		getPermissions : function(){
			var that = this;
			service.getPermissions().then(function(resJson){
				if(resJson){
					that.model.permissions(resJson);
				}else{
					that.showInfo("获取权限失败");
				}
			});
		},
		setting: {
			view: {
                showLine: false,
                //showIcon: false,
				dblClickExpand: false,
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "ps" }
			},
			data : {
				key:{
					url: "xUrl"
				},
				simpleData:{
					enable:true,
					idKey:"id",
			        pIdKey:"parentId"
				}
			}
		},
		
		initTree: function (id,setting) {
			var that = this;
			$.fn.zTree.init($("#"+id), setting, that.model.permissions());
		},
		
		forEachPromissions: function(menuIds){
			if(menuIds!="" && menuIds!=undefined){
				menuIds = menuIds.split(",");
				
				var treeObj = $.fn.zTree.getZTreeObj(this.model.treeId());
				var parentNodes = treeObj.getNodes();
				for(var i=0,len=menuIds.length;i<len;i++){
					if(parentNodes!=null && parentNodes!=undefined){
							this.selectPromissions(menuIds[i],parentNodes,treeObj);
					}
				}
			}
		},
		selectPromissions:function(menuId,parentNodes,treeObj){
			var type = typeof parentNodes;
			for(var i=0,len=parentNodes.length;i<len;i++){
				var isParent = parentNodes[i].isParent;
				if(isParent){
					this.selectPromissions(menuId,parentNodes[i].children,treeObj);
				}else if(parentNodes[i].id==menuId){
					treeObj.checkNode(parentNodes[i], true, true);
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
			
			var msg = status==1?"启用":"禁用";
			var that = this;
			service.updateStatus(data).then(function(data){
				if(data){
					that.showInfo(msg+"成功","alert alert-success");
					that.list(that.model.search.pageNo());
				}else{
					that.showInfo(msg+"失败");
				}
			},function(){
				that.showInfo(msg+"异常");
			});
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.model.currManagerType($("#managerType").val());
			this.getPermissions();
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