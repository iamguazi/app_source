;(function () {
	//游戏类型		
	var gameTypeArray = [{id:"",name:"全部"},{id:1,name:"北京28"},{id:2,name:"加拿大28"}];	
	//修改时使用的游戏类型
	var gameTypeArrayOfForm = [{id:"",name:"请选择游戏类型"},{id:1,name:"北京28"},{id:2,name:"加拿大28"}];	
	
	var viewModel = {
		model: {
			dataList: [],			// 数据列表
			search: {				// 查询条件，更多条件可直接扩展
				pageNo: 1,
				pageSize: PAGE_SIZE,
			},
			formEntity: {},			// 新增/编辑实体
			detailEntity: {},		// 详情实体
			delId: [],				// 删除ID
			hasInited: false,		// 是否初始化完成
			onFormSubmitting: false,
	        checkItems: [],
	        validator:{},
	    	gameTypeArray:gameTypeArray, //游戏类型
	    	gameTypeArrayOfForm:gameTypeArrayOfForm,  //修改时使用的游戏类型
	        page: {
	        	totalCount: 0,
	        	pageCount: 0,
	        	recordMin: 0,
	        	recordMax: 0,
	        	defaultShowCount:5,  //默认显示多少个按钮
	        }
		},
		def: {
			imgExts: ["jpg", "jpeg", "png", "gif"],		// 支持的图片格式
			imgSize: 2 * 1024 * 1024					// 支持的图片尺寸
		},
		fmt: {
			/**
			 * 日期格式化
			 * @param date
			 * @param fmt
			 * @returns
			 */
			date: function ( date, fmt) {
				return date ? ( new Date(date) ).format( fmt || "yyyy-MM-dd hh:mm:ss" ) : "";
			},
			/**
			 * 头像img，返回img对象
			 * @param url
			 * @returns {String}
			 */
			img: function ( inUrl ) {
				var url = "";
				if(inUrl){
					if($.type(inUrl)=="array" ){
						if( inUrl.length > 0 ){
							url = inUrl[0];
						}
					}else{
						url = inUrl;
					}
					
					if(new RegExp("^((http:\/\/)|(https:\/\/))").test(url)){
						return "<img src='" +url + "' class='list_photo' />";
					}
					
					if(!new RegExp("^"+fullPath).test(url) && url.indexOf(imgShowRoot) == -1){
						return "<img src='" +imgShowRoot+url + "' class='list_photo' />";
					}else{
						return "<img src='" +url + "' class='list_photo' />";
					}
					
				}
				return url;
			},
			/**
			 * 头像img，返回多个img对象
			 * @param urls
			 * @returns {String}
			 */
			imgs: function (urls) {
				var result = "", that = this;
				if (urls) {
					$.each(urls.split(","), function (i, v) {
						result += that.img(v);
					});
				}
				return result;
			},
			/**
			 * 组装imgUrl
			 * @param url
			 * @returns
			 */
			imgUrl: function ( inUrl ) {
				var url = "";
				if(inUrl){
					
					if($.type(inUrl)=="array" ){
						if( inUrl.length > 0){
							url = inUrl[0];
						}
					}else{
						url = inUrl;
					}
					
					
					if(new RegExp("^((http:\/\/)|(https:\/\/))").test(url)){
						return url;
					}
					
					if(!new RegExp("^"+fullPath).test(url) && url.indexOf(imgShowRoot) == -1){
						return imgShowRoot + url;
					}else{
						return url;
					}
					
				}
				return url;
			},
			gameType:function(gameType){
				var gameTypeArray = viewModel.model.gameTypeArray.slice(0);
				for (var i = 0, len = gameTypeArray.length; i < len; i++){
					if(gameTypeArray[i].id() && gameType == gameTypeArray[i].id()){
						return gameTypeArray[i].name();
					}
				}
				return "";
			},
			img4Null: function(inUrl,defaultUrl){
				
				
				if(!inUrl){
					if(defaultUrl){
						return defaultUrl;
					}else{
						return fullPath+"img/no_picture.jpg";
					}
				}
				
				var url = "";
				if($.type(inUrl)=="array" ){
					if( inUrl.length > 0){
						url = inUrl[0];
					}
				}else{
					url = inUrl;
				}
				
				
				if(new RegExp("^((http:\/\/)|(https:\/\/))").test(url)){
					return url;
				}
				
				if(!new RegExp("^"+fullPath).test(url) && url.indexOf(imgShowRoot) == -1) {
					return imgShowRoot+url;
				}
				
				return url;
				
			},
			/**
			 * 添加至完整路径
			 * @param path
			 * @returns
			 */
			fullPath: function ( path ) {
				return fullPath + path;
			},
			/**
			 * 取对象值
			 * @param key
			 * @param obj
			 * @returns
			 */
			value: function ( key, obj ) {
				return obj[key];
			},
			/**
			 * 货币格式化
			 * @param money
			 * @param opt
			 * @returns
			 */
			currency: function ( money, opt ) {
				opt = $.extend(true, {
					places: 2,
					symbol: "￥",
					thousand: ",",
					decimal: "."
				}, opt);
		        var number = money,
		            negative = number < 0 ? "-" : "",
		            i = parseInt(number = Math.abs(+number || 0).toFixed(opt.places), 10) + "",
		            j = (j = i.length) > 3 ? j % 3 : 0;
		        return opt.symbol + negative + (j ? i.substr(0, j) + opt.thousand : "") + i.substr(j).replace(/(\d{3})(?=\d)/g, "$1" + opt.thousand) +
		        	(opt.places ? opt.decimal + Math.abs(number - i).toFixed(opt.places).slice(2) : "");
			},
			/**
			 * 图片放大
			 * @param src
			 */
			biggerShowImg  : function(inSrc){
				
				 var that = this,src = "";
				   var prevImg = new Image();
				   if(inSrc){
					   if($.type(inSrc)=="array"  ){
						   if(inSrc.length >0){
							   src = inSrc[0];
						   }
					   }else{
						   src = inSrc;
					   }
					   
					   if(!new RegExp("^"+fullPath).test(src) && src.indexOf(imgShowRoot) == -1 && !new RegExp("^((http:\/\/)|(https:\/\/))").test(src)){
						  src = imgShowRoot + src;
					   }
						prevImg.onload = function(){
							if($("#mask").length<1){
								$("body").append($('<div id="mask" class="mask"></div>'));
							}
						  $("#mask").show();
						  var $prevImg = $(prevImg),
							  winW = $(window).width(),
							  winH = $(window).height(),
							  imgW = parseInt(prevImg.width || "0"),
							  imgH = parseInt(prevImg.height || "0"),
							  padding = 50;
						  $prevImg.removeAttr("width")
								  .removeAttr("height")
								  .css({
										position: "fixed",
										top:0,
										left:0,
										width:(winW > imgW ? imgW : winW)+"px",
										height:(winH > imgH ?  imgH  : winH)+"px",
										marginLeft:   (winW > imgW ? (winW - imgW-14)/2 : 0),
										marginTop:  (winH > imgH ? (winH - imgH-14)/2 : 0),
										padding: "5px",
										border: "2px solid #ccc",
										"border-radius" : "5px",
										opacity : 0.2,
										zIndex : 10001,
										cursor: "pointer"
									}).appendTo("body")
									  .animate({
											opacity: 1
										}, 300)
									  .off("click")
									  .on("click", function(){
											$(this).animate({
												opacity: 0.2
											}, 300, function(){
												$(this).hide().remove();
												$("#mask").hide();
											});
										});
						       };
						       
						       prevImg.onerror = function(){
						    	   Lobibox.notify("warning",{msg:"加载图片失败......"});
						       }
						       
						       prevImg.src = src;
					}else{
						Lobibox.notify("warning",{msg:"无效地址"});
					}
			}
		},
		/**
		 * 初始化viewModel
		 */
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.list();
			this.initValidate();
			ko.applyBindings(this);
			this.model.hasInited(true);
			
		},
		/**
		 * 初始化表单验证
		 */
		validateOption: $.noop,
		
		/**
	     * 初始化 jquery.validate
	     */
		initValidate : function(){
	    	
	        if ( !$.fn.validate ) { return; }
	        
	        $.extend($.validator.messages, {
	            required    : "  为必填字段",
	            remote      : "  请修正字段",
	            email       : "  必须为合法的电子邮件",
	            url         : "  必须为合法的网址",
	            date        : "  必须为合法的日期格式",
	            dateIOS     : "  必须为合法的日期格式(ISO)",
	            number      : "  必须为合法的数字",
	            digits      : "  必须为整数",
	            creditcard  : "  必须为合法的信用卡号",
	            equalTo     : "  两次输入的不同",
	            accept      : "  只允许合法后缀名的字符串",
	            maxlength   : $.validator.format("  必须是一个 长度最多是 {0} 的字符串"),
	            minlength   : $.validator.format("  必须是一个 长度最少是 {0} 的字符串"),
	            rangelength : $.validator.format("  必须是一个长度介于 {0} 和 {1} 之间的字符串"),
	            range       : $.validator.format("  必须是一个介于 {0} 和 {1} 之间的值"),
	            max         : $.validator.format("  必须是一个最大为{0} 的值"),
	            min         : $.validator.format("  必须是一个最小为{0} 的值")
	        } );
	        var that = this;
	        $( ".sh-validate" ).each( function () {
	            var $form = $( this );
	            var $errorForm = $form.find( ".errorForm" );
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
	                }
	            };
	            if ( $errorForm.length ) {
	            	opts.errorLabelContainer = $errorForm;
	            } else {
	            	opts.errorPlacement = function( $error, $element ) {
	                    //error是错误提示元素span对象  element是触发错误的input对象  只触发一次
	                    //发现即使通过验证 本方法仍被触发
	                    //当通过验证时 error是一个内容为空的span元素
	            	    //var label = ( $element.parents( ".form-group" ).find( "label:first" ).text() || "" ).replace( /[\*|\:|\：]/g, " " );
	            		var $parent = $element.parent( ".input-group" );
	            		
	            		//$error.text( label + $error.text() );
	            		$error.text( $error.text() );
	            		if ( $parent && $parent.length ) {
	            			$error.insertAfter( $parent );        
	            		} else {
	            			$error.insertAfter( $element );            			
	            		}
	                }
	            }
	            
	            that.model.validator=$( this ).validate( $.extend(true,opts,that.validateOption() ));
	        } );
	    },
	    /**
	     * 查询
	     */
	    searchByCondition: function() {
			this.list(1);
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
					that.model.dataList(resJson.page.result);
					that.page(transformPage(resJson.page));
					that.model.checkItems([]);
					//that.initClickEvent();
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
		/**
		 * 查询数据列表首页
		 */
		search: function(){
			this.list(0);
		},
		/***
		 * 提示
		 * @param content
		 * @param classNames
		 */
		showInfo : function(content,classNames,timeClose){
			$('#alert').removeAttr("class");
			$("#alertContent").text(content);
			
			if(classNames){
				$("#alert").addClass(classNames);
			}else{
				$("#alert").addClass("alert alert-danger");
			}
			
			$('#alert').stop().show();
			
			//绑定定时器
			var fadeTimeOutId = setTimeout(function(){
				$('#alert').stop().fadeOut();
			},timeClose || 2000);
			
			
			$("#alert").off("click");
			
			$("#alert").on("click",function(){
				if(fadeTimeOutId){
					//清空定时器
					clearTimeout(fadeTimeOutId);
				}
				$('#alert').stop().fadeOut();
			});
			
			
			
		},
		/**
		 * 分页跳转
		 * @param pageNo
		 */
		goPage: function (root) {
			var pageNo = $("#goToPageNo").val(),pageCount = this.model.page.pageCount();
			if(!/^\d+$/.test(pageNo) || pageNo<1 || pageNo>pageCount){
				Lobibox.notify("warning",{msg:"输入正确页码"});
				return ;
			}
			
			if (pageNo >= 1 && pageNo <= this.model.page.pageCount()) {
				this.list(pageNo);
			}
		},
		
		/**首页按钮**/
		firstPage : function(){
			var that = this;
			$("#firstPage").off("click").on("click",function(){
				that.list(1);			
			});
		},
		
		/**尾页按钮**/
		lastPage : function(){
			var that =this;
			$("#lastPage").off("click").on("click",function(){
				that.list(that.model.page.pageCount());			
			});
		},
		
		/**上一页按钮**/
		prevPage : function(){
			var that = this;
			$("#prevPage").off("click").on("click",function(){
				if(that.model.search.pageNo()>1){
					that.list(that.model.search.pageNo()-1);
				}
			});
		},
		
		/**下一页按钮**/
		nextPage : function(){
			var that = this;
			$("#nextPage").off("click").on("click",function(){
				if( that.model.search.pageNo() < that.model.page.pageCount()){
					that.list(that.model.search.pageNo()+1);
				}
			});
		},
		
		/**点击的页数**/
		clickOnePage : function(){
			var that = this;
			$(".pagination li[common_page]").off("click").on("click",function(){
				$(".pagination li").removeClass("active");
				$(this).addClass("active");
				that.list($(this).attr("id").replace("page_no_","")-0);
			})
		},
		
		/**设置分页大小**/
		changePageSize : function(){
			var that = this;
			$("#changePageSize").off("change").on("change",function(){
				var pageSize = $("#changePageSize").val();
				that.model.search.pageSize(pageSize);
//				$("#changePageSize").find("option[val='"+pageSize+"']").attr("selected",true);
				that.list(1,pageSize);
			});
		},
		
		/****
		 * 创建分页按钮
		 */
		createPageLi : function(page){
			var pageCount = page.pageCount,pageNo = page.pageNo,pageSize = page.pageSize;
			
			var $liTemplate = $('<li common_page="true"><a href="javascript:void(0);">1</a></li>');
			var showCount = this.model.page.defaultShowCount();
			if(pageNo==1){
				//如果是第一页就删除有页码的li   (即初始化)
				$(".pagination li[common_page]").remove();
				//判断要显示的页码数量是否超过分页总页数
				for(var i=0,len=page.pageCount>showCount?showCount:pageCount;i<len;i++){
					if(i==len-1){
						$("#prevPage").after($liTemplate.clone().attr("id","page_no_"+(pageNo+len-i-1)).addClass("active").find("a").text(pageNo+len-i-1).end());
					}else{
						$("#prevPage").after($liTemplate.clone().attr("id","page_no_"+(pageNo+len-i-1)).find("a").text(pageNo+len-i-1).end());
					}
				}
				//禁用上一页
				$("#prevPage").addClass("disabled");
				if(pageNo==pageCount){
					//禁用下一页
					$("#nextPage").addClass("disabled");
				}else{
					$("#nextPage").removeClass("disabled");
				}
			}else{
				//启用上一页
				$("#prevPage").removeClass("disabled");
				if(pageNo==pageCount){
					//禁用下一页
					$("#nextPage").addClass("disabled");
				}else{
					//启用下一页 
					$("#nextPage").removeClass("disabled");
				}
				
				var $selectLi = $(".pagination li[common_page]");
				//移除选中样式 
				$selectLi.removeClass("active");
				//超过的元素删掉
				if(page.pageCount<showCount){
					$(".pagination li[common_page]:gt("+pageCount+")").remove();
				}
				
				var size = $selectLi.length;
				var $pageNo = $("#page_no_"+pageNo),pageNoStr = "#page_no_"+pageNo;
				
				//判断是否存在某一页
				if($pageNo.length>0){
					//选中样式
					$pageNo.addClass("active");
					
					//如果点击的是显示页码的最后一条页码  添加页码
					if($("li[common_page]").index($("#page_no_"+pageNo))==size-1){
						//判断能否添加页码默认个数个按钮
						if((pageNo+showCount-1)<=pageCount){
							$selectLi.not(pageNoStr).remove();
							
							for(var i=0,len=showCount-1;i<len;i++){
								$pageNo.after($liTemplate.clone().attr("id","page_no_"+(pageNo+len-i)).find("a").text(pageNo+len-i).end());
							}
						}else if(pageCount>pageNo){
							$selectLi.not(pageNoStr).remove();
							var nextCount = pageCount-pageNo,prevCount = showCount-nextCount-1;
							//添加下一页码按钮
							for(var i=0,len=nextCount;i<len;i++){
								$pageNo.after($liTemplate.clone().attr("id","page_no_"+(pageNo+len-i)).find("a").text(pageNo+len-i).end());
							}
							//添加上一页码按钮
							for(var i=0,len=prevCount;i<len;i++){
								$pageNo.before($liTemplate.clone().attr("id","page_no_"+(pageNo-len+i)).find("a").text(pageNo-len+i).end());
							}
						}
					}else if($("li[common_page]").index($("#page_no_"+pageNo))==0){
						//如果点击的是显示页码的第一条页码  添加前向页码
						$selectLi.not(pageNoStr).remove();
						
						if((pageNo - showCount) >= 0){
							//添加上一页码按钮
							for(var i=0,len=showCount-1;i<len;i++){
								$pageNo.before($liTemplate.clone().attr("id","page_no_"+(pageNo-len+i)).find("a").text(pageNo-len+i).end());
							}
						}else {
							var prevCount = pageNo,nextCount = pageCount>=showCount?showCount-prevCount:pageCount-prevCount;
							//添加下一页码按钮
							for(var i=0,len=nextCount;i<len;i++){
								$pageNo.after($liTemplate.clone().attr("id","page_no_"+(pageNo+len-i)).find("a").text(pageNo+len-i).end());
							}
							//添加上一页码按钮
							for(var i=0,len=prevCount-1;i<len;i++){
								$pageNo.before($liTemplate.clone().attr("id","page_no_"+(pageNo-len+i)).find("a").text(pageNo-len+i).end());
							}
						}
						
						
					}
					
				}else{
					
					var searchPageNoIndex = "next"; //prev next goPrev  goNext  查询页码所处位置
					//目前显示的第一个页码数
					var firstLiPageNo = $selectLi.first().attr("id").replace("page_no_","")-0;
					//目前显示的最后一个页码数
					var lastLiPageNo  = $selectLi.last().attr("id").replace("page_no_","")-0;
					
					if(pageNo <= firstLiPageNo-1 ){
						searchPageNoIndex = "prev";
					}
					
					if(pageNo >= lastLiPageNo+1 ){
						searchPageNoIndex = "next";
					}
					
					
					//如果点击的是显示页码的最后一条页码  添加页码
					if(searchPageNoIndex=="next"){
						//判断能否添加页码默认个数个按钮
						if((pageNo+showCount-1)<=pageCount){
							$selectLi.remove();
							//添加下一页码按钮
							for(var i=0,len=showCount;i<len;i++){
								$("#prevPage").after($liTemplate.clone().attr("id","page_no_"+(pageNo+len-i-1)).find("a").text(pageNo+len-i-1).end());
							}
						}else if(pageCount>pageNo){
							$selectLi.remove();
							var nextCount = pageCount-pageNo,prevCount = showCount-nextCount;
							//添加下一页码按钮
							for(var i=0,len=nextCount;i<len;i++){
								$("#prevPage").after($liTemplate.clone().attr("id","page_no_"+(pageNo+len-i)).find("a").text(pageNo+len-i).end());
							}
							//添加上一页码按钮
							for(var i=0,len=prevCount;i<len;i++){
								$("#prevPage").after($liTemplate.clone().attr("id","page_no_"+(pageNo-i)).find("a").text(pageNo-i).end());
							}
						}else{
							//添加上一页码按钮
							$selectLi.remove();
							for(var i=0,len=showCount;i<len;i++){
								$("#prevPage").after($liTemplate.clone().attr("id","page_no_"+(pageNo-i)).find("a").text(pageNo-i).end());
							}
						}
					}
					
					//如果点击的是显示页码的第一条页码  添加前向页码
					if(searchPageNoIndex=="prev"){
						$selectLi.remove();
						if((pageNo - showCount) >= 0){
							//添加上一页码按钮
							for(var i=0,len=showCount;i<len;i++){
								$("#prevPage").after($liTemplate.clone().attr("id","page_no_"+(pageNo-i)).find("a").text(pageNo-i).end());
							}
						}else {
							var prevCount = pageNo,nextCount = pageCount>=showCount?showCount-prevCount:pageCount-prevCount;
							//添加下一页码按钮
							for(var i=0,len=nextCount;i<len;i++){
								$("#prevPage").after($liTemplate.clone().attr("id","page_no_"+(pageNo+len-i)).find("a").text(pageNo+len-i).end());
							}
							//添加上一页码按钮
							for(var i=0,len=prevCount;i<len;i++){
								$("#prevPage").after($liTemplate.clone().attr("id","page_no_"+(pageNo-i)).find("a").text(pageNo-i).end());
							}
						}
					}
					//选中样式
					$("#page_no_"+pageNo).addClass("active");
				}
				
			}
			
		},
		
		/**
		 * 生成分页脚本
		 * @param page
		 */
		page: function (page) {
			var that = this;
			// 修订pageNo和pageSize
			this.model.search.pageNo(page.pageNo);
			this.model.search.pageSize(page.pageSize);
			this.model.page.totalCount(page.totalCount);
			this.model.page.pageCount(page.pageCount);
			
			//分页
			this.createPageLi(page);
			this.firstPage();
			this.lastPage();
			this.prevPage();
			this.nextPage();
			this.clickOnePage();
			this.changePageSize();
		},
		/**
		 * 清空查询内容
		 */
		clearSearch: function () {
			var pageNo = this.model.search.pageNo();
			var pageSize = this.model.search.pageSize();
			fillKOEntity( this.model.search, { pageNo: pageNo, pageSize: pageSize } );
		},
		/**
		 * 显示新增/编辑框
		 * @param data
		 */
		showForm: function (data){
			
		},
		/**
		 * 提交s表单信息成功后的回调
		 */
		formValidSuccess: function(){ return true;},
		/**
		 * 提交新增/编辑表单
		 */
		submitForm: function () {
			var that = this,
				data = ko.mapping.toJS(this.model.formEntity);
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
			
			if($("#form").length>0){
				if(!$("#form").valid()){
					return;
				}
			}
			
			if($.isFunction(this.formValidSuccess)){
				if(!this.formValidSuccess()){
					return ;
				}
			}
			
			this.model.onFormSubmitting(true);
			
			
			
			var $ladda = $(".ladda-button"),isHasLadda = $ladda.length > 0 ? true : false,laddaObject;
			if(isHasLadda){
				laddaObject = $ladda.ladda();
				laddaObject.ladda("start");
			}
			
			service.saveOrUpdate(data).then(function (resJson) {
				if(laddaObject){
					laddaObject.ladda("stop");
				}
				
				if(resJson.status && (resJson.status===Ajax.CODE.SUCCESS_DATA || resJson.status===Ajax.CODE.SUCCESS_NODATA)) {
					Lobibox.notify("success",{msg:"保存成功"});
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
					Lobibox.notify("error",{msg:resJson.desc});
					that.model.onFormSubmitting(false);
					return;
				} else {
					Lobibox.notify("error",{msg:"操作失败"});
					that.model.onFormSubmitting(false);
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
			});
		},
		isRefash:function(){
			return true;
		},
		saveAfter:function( data ,resJson){
			
		},
		updateAfter:function(data,resJson){
			
		},
		/**
		 * 显示详情
		 * @param data
		 */
		showDetail: function ( data ) {
			fillKOEntity( this.model.detailEntity, data || {} );
			$( "#detailDialog" ).fadeIn( 400 );
		},
		/**
		 * 显示删除确认框
		 * @param id
		 */
		showDelModal: function (id) {
			this.model.delId(id);
			$("#delModal").modal("show");

		},
		/**
		 * 删除对象
		 */
		delItem: function (id) {
			var that = this;
			this.hideModal();
			var ids = this.model.delId();
			if(id && typeof id == "number"){
				var ids = new Array();
				ids[0] = id;
				service.del(ids).then(function () {
					that.delItemAfter(ids);
					that.list(that.model.search.pageNo());
				});
				return;
			} 
			
			if(this.model.delId().length>0) {
				service.del(this.model.delId()).then(function () {
					that.delItemAfter(ids);
					that.list(that.model.search.pageNo());
				});
			} 
//			else {
//				var ids = new Array();
//				ids[0] = id;
//				service.del(ids).then(function () {
//					that.delItemAfter(ids);
//					that.list(that.model.search.pageNo());
//				});
//			}
		},
		delItemAfter:function(ids){
			
		},
		/**
		 * 图片放大
		 * @param src
		 */
		biggerShowImg  : function(src){
			 var that = this;
			   var prevImg = new Image();
			   if(src){
				   if(!new RegExp("^"+fullPath).test(src) && src.indexOf(imgShowRoot) == -1){
					  src = imgShowRoot + src;
				   }
					prevImg.onload = function(){
						if($("#mask").length<1){
							$("body").append($('<div id="mask" class="mask"></div>'));
						}
					  $("#mask").show();
					  var $prevImg = $(prevImg),
						  winW = $(window).width(),
						  winH = $(window).height(),
						  imgW = parseInt(prevImg.width || "0"),
						  imgH = parseInt(prevImg.height || "0"),
						  padding = 50;
					  $prevImg.removeAttr("width")
							  .removeAttr("height")
							  .css({
									position: "fixed",
									top:0,
									left:0,
									width:(winW > imgW ? imgW : winW)+"px",
									height:(winH > imgH ?  imgH  : winH)+"px",
									marginLeft:   (winW > imgW ? (winW - imgW-14)/2 : 0),
									marginTop:  (winH > imgH ? (winH - imgH-14)/2 : 0),
									padding: "5px",
									border: "2px solid #ccc",
									"border-radius" : "5px",
									opacity : 0.2,
									zIndex : 10001,
									cursor: "pointer"
								}).appendTo("body")
								  .animate({
										opacity: 1
									}, 300)
								  .off("click")
								  .on("click", function(){
										$(this).animate({
											opacity: 0.2
										}, 300, function(){
											$(this).hide().remove();
											$("#mask").hide();
										});
									});
					       };
					       
					       prevImg.onerror = function(){
					    	   Lobibox.notify('warning', {
				                    msg: '加载图片失败......'
				                    ,title:"提示"
				                });
					    	   //showMsg("加载图片失败......", "提示", "info",2000);
					       }
					       
					       prevImg.src = src;
				}else{
					Lobibox.notify('warning', {
	                    msg: '无效地址',title:"提示"
	                });
					//showMsg("无效地址", "提示", "info",2000);
				}
		},
		/**
		 * 图片上传
		 * @param fileInput
		 * @param bussinessDir
		 * @param type
		 * @returns {Boolean}
		 */
		upload: function ( fileInput, bussinessDir, type ) {
			var that = this;
			type = type || 1;
			if (!this.validateUpload(fileInput)) {
				return false;
			}
			$("body").cover("正在上传图片......");
			$.ajaxFileUpload({						// 异步保存图片   addForm.attr("action")
				url : fullPath + "backstage/multipart/upload",
				secureuri:false,
				fileElementId : fileInput.id,
				dataType : 'json',
				data : {
					bussinessDir: bussinessDir
				},
				success: function (resJson, status){
					if(resJson.status === Ajax.CODE.SUCCESS_DATA){
						if($.isFunction(that.afterUpload)){
							var imgShowRoot = resJson.data.imgShowRoot;
							for(var i=0,len=resJson.data.paths.length;i<len;i++){
								resJson.data.paths[i]=imgShowRoot+resJson.data.paths[i];
							}
							that.afterUpload(resJson, type, fileInput);
						}
						Lobibox.notify("success",{msg:"上传成功"});
					}else{
						Lobibox.notify("error",{msg:resJson.desc});
					}
					$("body").uncover();
				},
				error: function (data, status, e){
					Lobibox.notify("error",{msg:"上传异常"});
					$("body").uncover();
				}
			});
		},
		/**
		 * 验证图片
		 * @param file
		 * @returns {Boolean}
		 */
		validateUpload: function (file) {
			var ua = window.navigator.userAgent;
			var src = file.value;
			var ext = (src.substring(src.indexOf(".") + 1) || "").toLowerCase();
			var fileSize = 0;
			// 验证图片格式
			if (this.def.imgExts.contain(ext) !== true) {
				Lobibox.notify("info",{msg:"只允许 " + this.def.imgExts.join(",") + " 格式的图片!"});
				return false;
			}
			//验证图片大小
			if ( ua.indexOf("MSIE") >= 1 ) {
				var dynImg = document.getElementById("dynImg");
				dynImg.dynsrc = src;
				fileSize = dynImg.fileSize;
			} else if (file.files) {
				fileSize = file.files[0].size;
			}
			if (fileSize == -1) {
				Lobibox.notify("info",{msg:"请选择图片!"});
				return false;
			} else if (fileSize > this.def.imgSize) {
				Lobibox.notify("info",{msg:"图片不得大于" + this.def.imgSize + " Byte"});
				return false;
			}
			return true;
		},
		
		/**
		 * 图片上传成功回调函数
		 */
		afterUpload: $.noop,
		
		getCheckItemsIds: function () {
			var ids = [];
			$.each(this.model.checkItems(), function (index, item){
				ids.push(item.id);
			});
			return ids;
		},
		/**
		 * 初始化部分元素点击事件
		 */
		initClickEvent: function() {
			var that = this;
			$(".editor").click(function(e){
				//$(".editor").animate({right:0},800);
				e.stopPropagation();
			});
			if($(".e_head .close").length>0){
				var $closeObjects = $(".e_head .close");
				for(var i=0,len=$closeObjects.length;i<len;i++){
					var  isBindClick = $closeObjects.eq(i).attr("is_bind_click");
					if(isBindClick==null || isBindClick==undefined || isBindClick=="" ){
						$closeObjects.eq(i).click(function(){
							$(this).parent(".e_head").parent(".editor").animate({right:"-100%"},300);
							that.hideMask();
						});
					}
				}
			}
			
			$("#mask").click(function(){
				if($("#edit").length>0) {
					$("#edit").animate({right:"-100%"},500);
				} else {
					$(".editor").animate({right:"-100%"},500);
				}
				that.closeOtherForm();
				that.hideMask();
				$("#dialog").hide();
			});

		},
		closeOtherForm:function(){
			
		},
		hideModal : function(){
			$(".modal.fade").modal('hide');
		},
		/*弹出窗*/
		showpop: function(id,text,fun,isSingleDelete,btnName,className) {
			var that = this;
			if(isSingleDelete && isSingleDelete=="1"){
				$("#deleteIdsMsg").css("display","none");
			}else{
				$("#deleteIdsMsg").css("display","inline");
			}

			if(btnName &&  typeof btnName=="string" ){
				$("#deleteBtn").html(btnName);
				$("#deleteLabel").html(btnName);
			}else{
				$("#deleteBtn").html("删除");
				$("#deleteLabel").html("删除");
			}

			if(className &&  typeof className=="string" ){
				$("#"+id).find(".alert").removeAttr("class").addClass("alert alert-"+className);
				$("#deleteBtn").removeAttr("class").addClass("btn btn-"+className);
			}else{
				$("#"+id).find(".alert").removeAttr("class").addClass("alert alert-danger");
				$("#deleteBtn").removeAttr("class").addClass("btn btn-danger");
			}
			
			$("#deleteMsg").html(text);
			$("#deleteBtn").unbind();
			$("#deleteBtn").bind("click",fun);
			$("#"+id).modal("show");
		},
		closepop: function(id) {
			var that = this;
			$("#"+id).hide();
			that.hideMask();
		},
		showMssage: function(id,text){
			var that = this;
			$("#"+id).show();
			$("#text").html(text);
			that.showMask();
		},
		//兼容火狐、IE8
	    //显示遮罩层
	    showMask: function(){
	        $("#mask").css("height",$(document).height());
	        $("#mask").css("width",$(document).width());
	        $("#mask").show();
	    },
	    //隐藏遮罩层
		hideMask: function(){
	        $("#mask").hide();
	    },
		clickCheckbox: function(index,item) {
			var that = this;
			if(!($("#chk"+index).find(".chk_1").is(':checked'))
					&& !($("#chk"+index).find(".chk_1").attr("checked"))) {//未选中-->选中
				$("#chk"+index).addClass('db-checkbox-selected ');
				$("#chk"+index).find(".chk_1").attr("checked",true);
				$("h1 .caption-lbtns-box").animate({left:0},800);
				that.model.checkItems.push(item);
				that.model.delId.push(item.id);
			}else{ //选中-->未选中
				$("#chk"+index).removeClass('db-checkbox-selected ');
				$("[class=chk_all]:checkbox").removeAttr("checked");
				$("#check_all").removeClass('db-checkbox-selected ');
				$("#chk"+index).find(".chk_1").removeAttr("checked");
				that.model.checkItems.remove(item);
				that.model.delId.remove(item.id);

				var hasChecked = false;
				$(".chk_1").each(function() {
		            if($(this).is(':checked') || $(this).attr("checked")) {
		            	hasChecked = true;
		            }
		        });
				if(!hasChecked) {
					$("h1 .caption-lbtns-box").stop(true, true).animate({left:"-100%"},500);
				}
			}
		},
		clickCheckall: function() {
			var that = this;
			that.model.checkItems([]);
			that.model.delId([]);
			if(!$(".chk_all").is(':checked') &&
					!$(".chk_all").attr("checked")) {//未选中-->选中
				$("h1 .caption-lbtns-box").animate({left:0},800);
				$('.check_box').addClass('db-checkbox-selected ');
				$('.check_box_all').addClass('db-checkbox-selected ');
				$("[class=chk_1]:checkbox").attr("checked", true);
				$(".chk_all").attr("checked",true);

				var all = that.model.dataList();
				$(all).each(function(index) {
					that.model.checkItems.push(all[index]);
					that.model.delId.push(all[index].id);
				});

			}else{ //选中-->未选中
				$("h1 .caption-lbtns-box").stop(true, true).animate({left:"-100%"},500);
				$('.check_box').removeClass('db-checkbox-selected ');
				$('.check_box_all').removeClass('db-checkbox-selected ');
				$("[class=chk_1]:checkbox").removeAttr("checked");
				$(".chk_all").removeAttr("checked");
				that.model.checkItems([]);
				that.model.delId([]);
			}
		},
		//初始化页面控件
		initView: function() {
			var that = this;
			$("h1 .caption-lbtns-box").stop(true, true).animate({left:"-100%"},500);
			$('.check_box').removeClass('db-checkbox-selected ');
			$('.check_box_all').removeClass('db-checkbox-selected ');
			$(".chk_all").removeAttr("checked");
			that.model.onFormSubmitting(false);
			that.model.checkItems([]);
			that.model.delId([]);
		}

	};
	window.viewModel = viewModel;

}());