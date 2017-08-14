  ;(function () {
	  var menuTreeIdFrefix = "menu_";
		
		$.extend(true, viewModel, {
			model: {
				search: {
					managerName:""
				},
				formEntity: {
					id: "", 
					managerName:"",
	                managerPasswd:"",
	                managerType:"",
	                menuCode:"",
				    valid:"",
				    createTime:"",
				    bannerImgurl:[]
				},
				store:{
					accountNotice:true,
					accountInterval:"",
					widthdrawalsNotice:true,
					widthdrawalsInterval:"",
					exchangeNotice:true,
					exchangeInterval:"",
				},
				validator:{},
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
			initValidate: function () {
				var that = this; 
				that.model.validator = $("#form").validate({
					
				});
				
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
			},
			searchByCondition: function() {
				this.list();
			},
			beforeSubmit: function () {
				this.submitForm();
			},
			/**
			 * 初始化viewModel
			 */
			initViewModel: function () {
				this.model = ko.mapping.fromJS(this.model);
				//设置窗口大小
				var  winH = $(window).height(),topMenuH = ($("#topMenu").css("height") || "100px").replace("px","")-0; 
				$(".container").css("height",winH+"px");
				$("#bottomContainer").css("height",(winH-topMenuH)+"px");
				$("#right_iframe").css("height",(winH-topMenuH-3)+"px");
				
				$("#topMenu a[id!='company']").mouseover(function(event){
					event.stopPropagation();
					$(this).css({"font-size":"16px","font-weight":"bold"});
				}).mouseout(function(event){
					event.stopPropagation();
					$(this).css({"font-size":"","font-weight":""});
				});
				
				this.createLeftMenu();
				
				ko.applyBindings(this);
				
				//this.bindHideEvent();
			},
			createLeftMenu : function() {
				var that = this;
				service.getPermissions({}).then(
				function(resJson){
					if(resJson  && resJson.length>0){
						$("body").data("menu_data",resJson);
						var menu = that.getMenu(resJson,"super_account_info");
						if(menu){
							//定时获取用户线下转账信息
							that.getNotice();
						}
						
						var withdrawalsLogMenu = that.getMenu(resJson,"super_withdrawals_log");
						if (withdrawalsLogMenu){
							//定时获取提现信息
							that.getWithdrawalsLogNotice();
						}
						
						var exchangeMenu = that.getMenu(resJson,"super_exchange_gift_log");
						if (exchangeMenu){
							//定时获取兑换礼物信息
							that.getExchangeGiftLogNotice();
						}
						
						for(var i=0,len=resJson.length;i<len;i++){
							if(resJson[i].parentId==0){
								    var $cloneA = $("#templateA").clone().removeAttr("id").empty().css("display",""),$cloneUl=$("#templateUl").clone().removeAttr("id");
								
//									if(resJson[i].url && resJson[i].url!=""){
//										var $li="<li>"+
//								                    "<a data-label='Everyting' href='"+(fullPath+resJson[i].url)+"' target='right_iframe' ><i class='i'></i>"+resJson[i].name+"</a>"+
//						                        "</li>";
//										$cloneUl.append($li).css("display","");
//										$(".card-drop").append($cloneUl);
//									}
								    
								    var icon = (resJson[i].iconUrl && resJson[i].iconUrl != "null") ? resJson[i].iconUrl:"";
								    icon = that.checkIcon(icon);
								    if(resJson[i].url && resJson[i].url!=""){
								    	$cloneA.attr("id",menuTreeIdFrefix+resJson[i].id).attr("href",fullPath+resJson[i].url).attr("target","right_iframe").append(icon+"<i class='icon-briefcase'></i>"+resJson[i].name);
								    }else{
								    	$cloneA.attr("id",menuTreeIdFrefix+resJson[i].id).removeAttr("target").append(icon+"<i class='icon-briefcase'></i>"+resJson[i].name+"<i class='icon-chevron-up'><span class='glyphicon glyphicon-chevron-right'></span></i>");
								    }
								    
									$(".sidebar-nav").append($cloneA);
									
									var appenUlFlag = false;
									for(var j=0,len2=resJson.length;j<len2;j++){
										if(resJson[j].parentId==resJson[i].id){
											appenUlFlag = true;
											var leftIcon = "<span class='glyphicon glyphicon-play' style='position: absolute;color: #424242;z-index: 10;margin-top: .7rem;margin-left: 10px;'></span>";
											//<li ><a href="sign-in.html" target="right_iframe">Sign In</a></li>
											var $li="<li>"+
						                    		  "<a id='"+menuTreeIdFrefix+resJson[j].id+"' href='"+(fullPath+resJson[j].url)+"' target='right_iframe' >"+resJson[j].name+"</a>"+
				                                    "</li>";
											$cloneUl.append($li);
										}
									}
									if(appenUlFlag){
										$(".sidebar-nav").append($cloneUl);
									}
									
							}
						}		
						that.initLeftMenu();
					}else{
						Lobibox.notify("info","还未配置栏目信息");
					}
					
				},
				function(error){
					Lobibox.notify("error","获取栏目信息失败");
				});
			},
			initLeftMenu : function(){
				var that = this;
				var nav = $('.sidebar-nav'), navHeader = nav.find('.nav-header'),li = nav.find('ul>li');
				//设置左边下拉菜单
				li.on('click', function (e) {
					var $this = $(this);
					$("#right_iframe").attr("src",$this.find("a").attr("href"));
					e.preventDefault;
				});
				
				navHeader.on('click', function (e) {
					e.preventDefault;
					var $this = $(this);
					if ($this.is('.activeA')) {
						$this.removeClass('activeA');
						$this.next("ul").slideUp();
						$this.find(".glyphicon:last").removeAttr("class").addClass("glyphicon glyphicon-chevron-right");
					} else {
						$this.addClass('activeA');
						$this.next("ul").slideDown();
						$this.find(".glyphicon:last").removeAttr("class").addClass("glyphicon glyphicon-chevron-down");
					}
				});
				
				if(navHeader.length>1){
					if(navHeader.eq(1).next("ul").length>0){
						$("#right_iframe").attr("src",navHeader.eq(1).next("ul").find("a").eq(0).attr("href"));
						navHeader.eq(1).trigger("click");
					}else{
						$("#right_iframe").attr("src",navHeader.eq(1).attr("href"));
					}
				}
					
			},
			
			submitNotice:function (menuCode){
				$("#showNotice").modal("hide");
//				$("#"+menuCode).find(".badge").text("");
				this.goToCodeMenu(menuCode);
			},
			cancelNotice:function (noticeCode,intervalCode){
				var that = this;
				$("#showNotice").modal("hide");
				if (noticeCode && that.model.store[noticeCode]){
					that.model.store[noticeCode](false);
				}

				if (intervalCode && that.model.store[intervalCode]){
					clearInterval(that.model.store[intervalCode]());
				}
			},
			bindHideEvent : function(){
				var that = this;
				$('#showNotice').off('hide.bs.modal').on('hide.bs.modal', function (e) {
					 var noticeCode = $("body").data("noticeCode"),intervalCode = $("body").data("intervalCode");
					if (noticeCode && that.model.store[noticeCode]){
						that.model.store[noticeCode](false);
					}
//					if (intervalCode && that.model.store[intervalCode]){
//						clearInterval(that.model.store[intervalCode]());
//					}
				});
			},
			goToCodeMenu: function (menuCode){
//				$("#noticeDrop").hide();
				$("#"+menuCode).find(".badge").text("");
				var menuData = $("body").data("menu_data");
				if(!menuData || menuData.length <= 0){
					return ;
				}
				
				var menu = this.getMenu(menuData,menuCode);
				if (menu ){
					if(menu.parentId == 0){
						$("#"+menuTreeIdFrefix+menu.id).trigger("click");
					}else{
						var temParentMenus = [];
						var parentMenus = this.getParent(menuData,menu.parentId,temParentMenus);
						parentMenus.push(menu)
						this.clickParentMenus(parentMenus);
					}
				}
			  },
			 
			  getMenu:function (menuData,menuCode){
				for(var i = 0, len = menuData.length; i < len; i++){
					if (menuData[i].menuCode && menuData[i].menuCode == menuCode){
						return menuData[i];
					}
				}
				return ;
			  }
			  ,
			  getMenuById: function(menuData,id){
					for(var i = 0, len = menuData.length; i < len; i++){
						if (menuData[i].id && menuData[i].id == id){
							return menuData[i];
						}
					}
					return ;
			  }
			  
			  ,
			  getParent:function(menuData,parentId,parentMenus){
				 var menu = this.getMenuById(menuData,parentId);
				 if(menu){
					 parentMenus.push(menu);
					 if(menu.parentId != 0){
						 this.getParent(menuData,menu.parentId);
					 }
				 }
				 return parentMenus;
			  },
			clickParentMenus: function (parentMenus){
				  $.each(parentMenus,function(i,v){
					  if(v && v.id){
						  $("#"+menuTreeIdFrefix+v.id).not(".activeA").trigger("click");
					  }
				  })
			  },
			getNotice :  function(){
				var that = this;
				var accountInterval = setInterval(function(){
					  $.ajax({
						  url:fullPath+"backstage/userAccountLog/noticeNewUserAccountLog",
						  type:"POST",
						  dataType:"json",
						  success:function(resJson){
							  if(resJson && (resJson.status === Ajax.CODE.SUCCESS_DATA || resJson.status === Ajax.CODE.SUCCESS_NODATA)){
								 if(+resJson.entity > 0){
									 if (that.model.store.accountNotice()){
										 $("#contentMsg").text("您有新的收款信息需要处理!");
										 $("#showNotice").modal("show");
										 $("#dingdongPlayer")[0].play();
										 $("body").data("noticeCode","accountNotice");
										 $("body").data("intervalCode","accountInterval");
									 }
									 
									 $("#noticeDrop").show();
									 var userAccountLogId = "super_account_log_info";
										$("#"+userAccountLogId).show().find(".badge").text(resJson.entity);
										$("#submitNoticeButton").off("click").on("click",function(){
												that.submitNotice(userAccountLogId);
										});
										$("#cancelNoticeButton").off("click").on("click",function(){
											that.cancelNotice("accountNotice","accountInterval");
										});
										
								 }
							  }
						  }
					  });
					},60000);  
				that.model.store.accountInterval(accountInterval)
			},
			getWithdrawalsLogNotice :  function(){
				var that = this;
				setTimeout(function(){
				var widthdrawalsInterval = setInterval(function(){
						  $.ajax({
							  url:fullPath+"backstage/withdrawalsLogs/noticeWithdrawalsLog",
							  type:"POST",
							  dataType:"json",
							  success:function(resJson){
								  if(resJson && (resJson.status === Ajax.CODE.SUCCESS_DATA || resJson.status === Ajax.CODE.SUCCESS_NODATA)){
									 if(+resJson.entity > 0 ){
										 
										 if (that.model.store.widthdrawalsNotice()){
											 $("#contentMsg").text("您有新的提现信息需要处理!");
											 $("#showNotice").modal("show");
											 $("#dingdongPlayer")[0].play();
											 $("body").data("noticeCode","widthdrawalsNotice");
											 $("body").data("intervalCode","widthdrawalsInterval");
										 }
										 
										 $("#noticeDrop").show();
										 var widthdrawalsId = "super_withdrawals_log";
										 $("#"+widthdrawalsId).show().find(".badge").text(resJson.entity);
										 $("#submitNoticeButton").off("click").on("click",function(){
											that.submitNotice(widthdrawalsId);
										 });
										 $("#cancelNoticeButton").off("click").on("click",function(){
											that.cancelNotice("widthdrawalsNotice","widthdrawalsInterval");
										 });
										 
									 }
								  }
							  }
						  });
						},60000);
					that.model.store.widthdrawalsInterval(widthdrawalsInterval);
				},20000);
				  
		},
		getExchangeGiftLogNotice :  function(){
			var that = this;
			setTimeout(function(){
				var exchangeInterval = setInterval(function(){
					$.ajax({
						url:fullPath+"backstage/exchangeGiftLog/noticeExchangeGiftLog",
						type:"POST",
						dataType:"json",
						success:function(resJson){
							if(resJson && (resJson.status === Ajax.CODE.SUCCESS_DATA || resJson.status === Ajax.CODE.SUCCESS_NODATA)){
								if(+resJson.entity > 0 ){
									
									if(that.model.store.exchangeNotice()){
										$("#contentMsg").text("您有新的礼物兑换信息需要处理!");
										$("#showNotice").modal("show");
										$("#dingdongPlayer")[0].play();
										$("body").data("noticeCode","exchangeNotice");
										$("body").data("intervalCode","exchangeInterval");
									}
									
									$("#noticeDrop").show();
									var exchangeId = "super_exchange_gift_log";
									$("#"+exchangeId).show().find(".badge").text(resJson.entity);
									$("#submitNoticeButton").off("click").on("click",function(){
										that.submitNotice(exchangeId);
									});
									
									$("#cancelNoticeButton").off("click").on("click",function(){
										that.cancelNotice("exchangeNotice","exchangeInterval");
									});
									
								}
							}
						}
					});
				},60000);
				
				that.model.store.exchangeInterval(exchangeInterval);
			},40000);	  
		 },
		 checkIcon: function(icon){
			 if (icon){
				var urls = icon.split("src='");
				if(urls.length>=2){
					if(urls[1].indexOf("http") != 0 && urls[1].indexOf(fullPath) != 0){
						var path = urls[1],index = path.indexOf("/",1); 
						if(index){
							urls[1] = fullPath+path.substr(index+1,path.length);
							return  urls.join("src='");
						}
						
					}
				}
			 }
			 
			 return icon;
		 }
	});
		
		$(function(){
			viewModel.initViewModel();
		});
		
	}());