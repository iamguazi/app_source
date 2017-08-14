/**
 * Created by Administrator on 2015/6/11 0011.
 */

;( function ( $, window ) {


    var template = {
          pageDesc: '<div class="page-desc"><div class="desc-container">' +
                        '总共<i class="page-totalcount"></i>条记录,当前<i class="page-pageno"></i>/<i class="page-pagecount"></i>页，每页显示' +
                        '<select class="page-pagesize"></select>' +
                        '条记录' +
                    '</div></div>',
          pageUl: '<div class="page-wrapper">' +
                      '<div class="page-container">' +
                      '<ul class="page">' +
                          '<li class="page-first"><a href="javascript: void(0);"><i class="fa fa-angle-left" ></i><i class="fa fa-angle-left" ></i></a></li>' +
                          '<li class="page-prev"><a href="javascript: void(0);"><i class="fa fa-angle-left" ></i></a></li>' +
                          '<li>' +
                              '<div class="page-items-container" style="position: relative; width:0; height: 34px; overflow: hidden;">' +
                                  '<ul class="page page-items" style="display: inline; width: 99999px; position: absolute; margin: 0;"></ul>' +
                              '</div>' +
                          '</li>' +
                          '<li class="page-next"><a href="javascript: void(0);"><i class="fa fa-angle-right"></i></a></li>' +
                          '<li class="page-last"><a href="javascript: void(0);"><i class="fa fa-angle-right"></i><i class="fa fa-angle-right"></i></a></li>' +
                      '</ul>' +
                      '</div>' +
                  '</div>',
          pageGo: '<div class="page-pagego"><div class="input-group">' +
                      '<input type="text" class="form-control page-goinp" placeholder="页码" />' +
                      '<span class="input-group-btn">' +
                          '<button class="btn btn-default page-gobtn" type="button">GO</button>' +
                      '</span>' +
                  '</div></div>',
          pageLi: '<li><a href="javascript: void(0);"></a></li>'
    };

    var calculator = {
        marginWidth: 5,
        getPageLiWidth: function ( $li ) {
            return $li ? $li.outerWidth() + calculator.marginWidth : 0;
        }
    };


    var PageProxy = ( function () {
        var pageProxy = this;

        var page = null;

        var $obj = {
            $container         : null,      // 分页控件的父容器
            $page              : null,      // 分页控件
            $pageItemsContainer: null,      // 页码ul的父容器
            $pageItems         : null,      // 页码ul

            $pageDesc          : null,
            $totalCount        : null,
            $pageNo            : null,
            $pageCount         : null,
            $pageSize          : null,

            $pageGo            : null,
            $goInp             : null,
            $goBtn             : null,

            $widthWrapper      : null,
            $widthUl           : null
        };

        var options = {
            pageNo    : 1,      // 当前页码
            pageSize  : 10,     // 每页显示数量
            pageCount : 0,      // 总页数
            totalCount: 0,      // 总记录数
            showPages : 10,     // 显示的页码数量
            pageClass : "",     // 分页控件额外样式
            autoDisabled: true, // 自动禁用分页，直到调用 page.refreshPage 或者手动调用 page.setDisabled，通常在异步执行获取分页数据时需要设置页码为不可点击直到数据返回后调用 page.refreshPage 刷新分页数据
            showDesc  : true,
            sizes     : [10, 20, 50],
            showGo    : true,
            callback  : {
                click: null
            }
        };

        var store = {
            curPage  : 1,       // 当前页码
            prevPage : 1,       // 上一次的页码
            startPage: 1,       // 页码渲染起始位置
            renderedCount: 0,     // 已渲染的页码数量
            slideTime: 300,     // 动画时间
            disabled : false    // 当前分页控件是否可用
        };

        var item = {};          // 存放所有的页码对象

        var initUI = function () {
            var $page = $( template.pageUl );

            // 初始化分页模块UI
            $obj.$container.append( $page );
            $obj.$page = $page;
            $obj.$pageItems = $page.find( ".page-items" );
            $obj.$pageItemsContainer = $obj.$pageItems.parent();

            // 初始化分页描述模块UI
            var $pageDesc    = $( template.pageDesc );
            $obj.$pageDesc   = $pageDesc;
            $obj.$totalCount = $pageDesc.find( ".page-totalcount" );
            $obj.$pageNo     = $pageDesc.find( ".page-pageno" );
            $obj.$pageCount  = $pageDesc.find( ".page-pagecount" );
            $obj.$pageSize   = $pageDesc.find( ".page-pagesize" );

            $obj.$page.prepend( $pageDesc );
            if(!options.sizes.contain(options.pageSize)){
            	options.sizes.push(options.pageSize);
            	options.sizes.sort(function (a, b) {
            		return a - b;
            	})
            }
            $.each( options.sizes, function ( i, size ) {
                var $option = $( '<option value="SIZE">SIZE</option>'.replace( /SIZE/g, size ) );
                $obj.$pageSize.append( $option );
            } );

            if ( options.showDesc === true ) {
                $pageDesc.show();
            } else {
                $pageDesc.hide();
            }

            // 初始化页码跳转模块UI
            var $pageGo = $( template.pageGo );
            $obj.$pageGo = $pageGo;
            $obj.$goInp = $pageGo.find( ".page-goinp" );
            $obj.$goBtn = $pageGo.find( ".page-gobtn" );

            $obj.$page.find( ".page-container" ).append( $pageGo );
            if ( options.showGo === true ) {
                $pageGo.show();
            } else {
                $pageGo.hide();
            }

            // 初始化用于计算宽度的 $widthWrapper
            $obj.$widthWrapper = $( "<div class='page-wrapper' style='position: absolute; left: -99999px; top: -99999px; visibility: hidden;'></div>" );
            $obj.$widthUl = $( '<ul class="page page-items" style="position: absolute;"></ul>' );
            $obj.$widthWrapper.append( $obj.$widthUl ).appendTo( "body");

            if ( $.type( options.pageClass ) === "string" && options.pageClass !== "" ) {
                $page.addClass( options.pageClass );
                $obj.$widthWrapper.addClass( options.pageClass );
            }

            page.refreshPage();
            renderPageItemsLTR( Math.min(2 * options.showPages, options.pageCount) );
            locatePageNo( options.pageNo );
        };

        var initEvt = function () {
            // 委托绑定 pageNo 点击事件
            $obj.$pageItems.delegate( "li a", "click", function () {
                var $this = $( this),
                    pageNo = +$this.parent().attr( "pageNo" );
                if ( options.pageNo !== pageNo ) {
                    page.goPage( pageNo );
                }
            } );
            // 点击首页
            $obj.$page.find( ".page-first" )
                .on( "click", function () {
                    page.goPage( 1 );
                } );
            // 点击上一页
            $obj.$page.find( ".page-prev" )
                .on( "click", function () {
                    page.goPage( options.pageNo - 1 );
                } );
            // 点击下一页
            $obj.$page.find( ".page-next" )
                .on( "click", function () {
                    page.goPage( options.pageNo + 1 )
                } );
            // 点击尾页
            $obj.$page.find( ".page-last" )
                .on( "click", function () {
                    page.goPage( options.pageCount );
                } );

            // pageGo 只允许输入数值
            $obj.$goInp.on( "blur", function () {
                var $this = $( this),
                    value = $.trim( $this.val() );
                if ( /^\d+$/.test( value ) !== true ) {
                    alert( "请输入合法的页码!" );
                    $this.val( options.pageNo )
                        .focus()
                        .select();
                } else {
                   $this.val( value );
                }
            } );

            // 点击 GO 按钮事件
            $obj.$goBtn.on( "click", function () {
                var pageNo = $obj.$goInp.val();
                page.goPage( pageNo, true );
            } );

            // 改变 pageSize 事件
            $obj.$pageSize.on( "change", function () {
                var size = $( this ).val();
                if( size !== options.pageSize ) {
                    if ($.isFunction( options.callback.click ) ) {
                        options.callback.click.call( null, page, 1, size );
                    }
                }
            } );
        };

        var getNearestFloorItem = function ( pageNo ) {
            for ( var i = pageNo; i > 0; i-- ) {
                if ( item[i] ) {
                    return item[i];
                }
            }
            return null;
        };

        var addPageItem = function ( pageNo ) {
            if ( item[pageNo] ) {
                return item[pageNo];
            }
            var $item = $( template.pageLi );
            $item.attr( "pageNo", pageNo )
                .find( "a" )
                .text( pageNo );
            item[pageNo] = $item;
            store.renderedCount++;
            return $item;
        };

        /**
         * 传统的由左向右生成页码，该操作改变 store.startPage
         *
         * @param renderCount
         */
        var renderPageItemsLTR = function ( renderCount ) {
            var i = 0, l = 0,
                pageNo = store.startPage,
                $item,
                $referItem;
            // 判断页码是否已经渲染完成
            if ( store.renderedCount >= options.pageCount ) {
                return;
            }
            // 判断渲染的页码个数是否合法
            if ( store.startPage >= options.pageCount ) {
                return;
            }
            // 不允许超出总页数
            if ( store.startPage + renderCount > options.pageCount ) {
                renderCount = options.pageCount - store.startPage + 1;
            }
            $referItem = item[store.startPage - 1];
            for( i = 0, l = renderCount; i < l; i++ ) {
                pageNo = store.startPage + i;
                if ( item[pageNo] ) {
                    continue;
                }
                $item = addPageItem( pageNo );
                if ( $referItem ) {
                    $referItem.after( $item );
                    $referItem = $item;
                } else {
                    $obj.$pageItems.append( $item );
                }
            }
            store.startPage = pageNo + 1;
        };
        /**
         * 由中间向左右两个方向同时生成页码
         *
         * @param renderCount
         * @param curPage
         */
        var renderPageItemsCTA = function ( renderCount, curPage ) {
            var i = 0, l = 0,
                pageNo = store.startPage,
                $item,
                startPage,
                ceilCount = Math.ceil( renderCount / 2 );
            // 判断页码是否已经渲染完成
            if ( store.renderedCount >= options.pageCount ) {
                return;
            }
            // 计算页码渲染的起始位置和可渲染的页码数
            if ( curPage < ceilCount ) {
                startPage = 1;
                if ( curPage + renderCount > options.pageCount ) {
                    renderCount = options.pageCount;
                } else {
                    renderCount = curPage + renderCount;
                }
            } else if ( curPage + ceilCount > options.pageCount ) {
                if ( curPage < renderCount ) {
                    startPage = 1;
                    renderCount = options.pageCount;
                } else {
                    startPage = curPage - renderCount + 1;
                    renderCount = renderCount + options.pageCount - curPage;
                }
            } else {
                startPage = curPage - renderCount;
                renderCount *= 2;
            }

            for( i = 0, l = renderCount; i < l; i++ ) {
                pageNo = startPage + i;
                if ( item[pageNo] ) {
                    continue;
                }
                var $floorItem = getNearestFloorItem( pageNo );
                if ( $floorItem ) {
                	$floorItem.after( addPageItem( pageNo ) );
                } else {
                	$item = addPageItem( pageNo );
                	$obj.$pageItems.append( $item );                	
                }
            }
        };

        /**
         * 由右向左生成页码
         *
         * @param renderCount
         * @param curPage
         */
        var renderPageItemsRTL = function ( renderCount, curPage ) {
            var $item,
                startPage = curPage - renderCount,
                pageNo,
                i, l,
                $referItem;
            // 判断页码是否已经渲染完成
            if ( store.renderedCount >= options.pageCount ) {
                return;
            }
            // 判断渲染的页码个数是否合法
            if ( curPage < renderCount ) {
                return;
            }

            for ( i = renderCount - 1; i >= 0; i-- ) {
                pageNo = startPage + i;
                if ( item[pageNo] ) {
                    continue;
                }
                $referItem = item[pageNo + 1];
                $item = addPageItem( pageNo );
                $referItem.before( $item );
                $referItem = $item;
            }
        };

        /**
         * 定位到 pageNo 的位置上
         * 后续页码生成规则：
         *  1、从右向左滑动的操作执行完成后，当 pageNo + ceilShowPages + 1 < options.pageCount 在左边生成 options.showPages * 2 数量的页码
         *  2、从左向右滑动的操作执行完成后，当 options.showPages * 2 + store.startPage >= pageNo 执行右边生成 options.showPages 数量的页码
         *  3、任意定位到页码的操作，在定位前执行该页码处两端同时生成 options.showPages * 2 数量的页码，定位后不再执行任何操作
         *
         * @param pageNo
         */
        var locatePageNo = function ( pageNo ) {
            var $startItem = null,
                $targetItem = null,
                startPage,
                floorShowPages = Math.floor( options.showPages / 2),
                showPages = options.showPages;

            pageNo = Math.min( options.pageCount, Math.max( 1, pageNo ) );

            // 开始的 options.showPages 个页码
            if ( pageNo <= floorShowPages ) {
                startPage = 1;
            }
            // 最后的 options.showPages 个页码
            else if ( pageNo >= options.pageCount - floorShowPages ) {
                startPage = options.pageCount - showPages + 1;
            }
            // 其余位置页码
            else {
                startPage = pageNo - floorShowPages;
            }

            // 此时的页码可能已生成也可能未生成，需做不同处理
            $startItem = item[startPage];
            $targetItem = item[pageNo];

            if ( $startItem && $targetItem ) {

                // 从右向左渲染页码的必须在渲染后再定位到pageNo
                if ( options.pageNo > pageNo ) {
                    if ( pageNo + floorShowPages + 1 < options.pageCount ) {
                        renderPageItemsRTL( options.showPages * 2, pageNo );
                    }
                } else {
                    if ( options.showPages * 2 + store.startPage >= pageNo ) {
                        renderPageItemsLTR( options.showPages );
                    }
                }
                setTimeout( function () {

                    slidePagesWidth( startPage, options.showPages );
                    slideToPage( $startItem, function () {

                        store.prevPage = options.pageNo;
                        store.curPage = pageNo;
                        options.pageNo = pageNo;
                    } );
                }, 17 );

            } else {
                renderPageItemsCTA( options.showPages * 2, pageNo );
                //  重新选中页码对象
                $startItem = item[startPage];
                $targetItem = item[pageNo];
                setTimeout( function () {

                    slidePagesWidth( startPage, options.showPages );
                    slideToPage( $startItem, function () {

                        store.prevPage = options.pageNo;
                        store.curPage = pageNo;
                        options.pageNo = pageNo;
                    }  );
                }, 17 );
            }

            $targetItem.addClass( "active" )
                .siblings( ".active" )
                .removeClass( "active" );

        };

        /**
         * 活动到 $item 页码对应的位置上，通常$item为计算后需要显示的开始的页码对象而不是pageNo对应的定位页码对象
         *
         * @param $item
         * @param callback
         */
        var slideToPage = function ( $item, callback ) {
            var left = $item.position().left;
            $obj.$pageItems.animate( {
                left: 1 - ( left > 0 ? left : 0 )
            }, store.slideTime, function () {
                if ( $.isFunction( callback ) ) {
                    callback();
                }
            } );
        };

        /**
         * 自适应页码宽度
         * 由于页码数字位数不同，通常的页码对象宽度不一，在每次滑动页码后重新计算当前显示的页码对应的中宽度
         *
         * @param startPage
         * @param showPages
         */
        var slidePagesWidth = function ( startPage, showPages ) {
            var $item,
                totalWidth = 0,
                i = 0, l = 0,
                $ul = $obj.$widthUl;
            $ul.empty();
            for ( i = 0, l = Math.min(showPages, options.pageCount); i < l; i++ ) {
                $item = item[startPage + i];
                $ul.append( $item.clone() );
            }
            totalWidth = $ul.outerWidth() + 2;          // 宽度加 2px， 配合 slideToPage 中设置 $pageItems 的 left + 1 达到左右各 padding 一个像素的效果
            $ul.empty();
            $obj.$pageItemsContainer.animate( {
                width: totalWidth
            }, store.slideTime );
        };
        
        /**
         * 填充分页数据
         */
        var fillPageText = function () {
        	$obj.$totalCount.text( options.totalCount );
            $obj.$pageNo.text( options.pageNo );
            $obj.$pageCount.text( options.pageCount );
            $obj.$pageSize.val( options.pageSize );
            $obj.$goInp.val( options.pageNo );
        };


        var Page = function ( $container, opts ) {
            page = this;

            page.setOptions( $container, opts );
            initUI();
            initEvt();

            return page;
        };

        return $.extend( true, Page, {
            prototype: {
                /**
                 * 设置配置项
                 *
                 * @param $container
                 * @param opts
                 * @returns {*}
                 */
                setOptions: function ( $container, opts ) {
                    $obj.$container = $container;
                    $.extend( true, options, opts );
                    store.prevPage = options.pageNo;
                    store.curPage = options.pageNo;
                    if ( options.pageNo > options.pageCount ) {
                        options.pageNo = options.pageCount;
                    }

                    return page;
                },
                /**
                 * 跳转到页码 pageNo
                 *
                 * @param pageNo
                 * @param responseCallback 是否响应回调函数，默认 true
                 * @returns {*}
                 */
                goPage: function ( pageNo, responseCallback ) {
                    pageNo = pageNo = Math.min( options.pageCount, Math.max( 1, +pageNo ) );
                    if ( pageNo == options.pageNo ) {
                    	return page;
                    }
                    if ( $.type( responseCallback ) !== "boolean" ) {
                        responseCallback = true;
                    }
                    if ( $.type( pageNo ) === "number" && store.disabled === false ) {
                        locatePageNo( pageNo );
                        options.pageNo = pageNo;
                        // 如果设置了 options.autoDisabled = true 这在每次点击后会禁用当前分页控件，直到再次调用 page.refreshPage 或手动调用 page.setDisabled( false )
                        if ( options.autoDisabled === true ) {
                            page.setDisabled( true );
                        }
                        // 处理点击响应回调函数
                        if ( responseCallback && options.callback && $.isFunction( options.callback.click ) ) {
                            options.callback.click.call( null, page, pageNo, options.pageSize );
                        }
                    }
                    
                    return page;
                },
                /**
                 * 设置分页控件为不可用
                 *
                 * @param disabled
                 * @returns {*}
                 */
                setDisabled: function ( disabled ) {
                    store.disabled = !!disabled;

                    return page;
                },
                /**
                 * 刷新页码数据，通常跳转页码后由服务端返回分页数据，此时使用该函数刷新分页控件的数据并重置控件为可用状态
                 *
                 * @param pageData
                 * @returns {*|{}}
                 */
                refreshPage: function ( pageData ) {
                    pageData = pageData || {};
                    var sourcePage = {
                        pageNo      : $.isNumeric( pageData.pageNo ) ? pageData.pageNo : options.pageNo,
                        pageSize    : $.isNumeric( pageData.pageSize ) ? pageData.pageSize : options.pageSize,
                        pageCount   : $.isNumeric( pageData.pageCount ) ? pageData.pageCount : options.pageCount,
                        totalCount  : $.isNumeric( pageData.totalCount ) ? pageData.totalCount : options.totalCount
                    }, pageNo = sourcePage.pageNo;

                    if ( pageData.pageCount != options.pageCount ) {
                    	return page.reset( sourcePage );
                    }
                    
                    if ( sourcePage.pageNo > sourcePage.pageCount ) {
                        sourcePage.pageNo = sourcePage.pageCount;
                    } else if ( sourcePage.pageNo < 1 ) {
                        sourcePage.pageNo = 1;
                    }

                    if ( pageNo !== options.pageNo ) {
                        locatePageNo( pageNo );
                    }
                    $.extend( true, options, sourcePage );

                    fillPageText();

                    if ( options.autoDisabled === true ) {
                        page.setDisabled( false );
                    }

                    return page;
                },
                /**
                 * 重置分页脚本 
                 */
                reset: function (opts) {
                	item = [];
                	$obj.$pageItems.empty();
                	store.renderedCount = 0;
                	store.startPage = 1;
                	page.setOptions( $obj.$container, opts );
                	renderPageItemsLTR( Math.min(2 * options.showPages, options.pageCount) );
                    locatePageNo( options.pageNo );

                    fillPageText();
                    return page;
                }
            }
        } );
    } () );


    $.fn.page = function () {
        var $page = $( this ),
            page = $page.data( "page-page" );
        if ( !page ) {
            page = new PageProxy( $page, arguments[0] );
            $page.data( "page-page", page );
        } else {
            if ( arguments.length === 1 ) {
                var type = $.type( arguments[0] );
                if ( type === "boolean" ) {
                    page.setDisabled( arguments[0] );
                }  else if ( type === "number" || type === "string" ) {
                    page.goPage( +arguments[0], true );
                } else {
                    page.refreshPage( arguments[0] );
                }
            } else if ( arguments.length === 2 ) {
                page.goPage( arguments[0], arguments[1] );
            }
        }
        return $page;
    };



} ( jQuery, window ) );

