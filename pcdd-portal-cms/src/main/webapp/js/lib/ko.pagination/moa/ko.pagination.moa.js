/**
 * @file: Javascript文件描述
 * @author: Administrator
 * @history: 2016/1/19
 */
;(function (ko, $) {

    var paginationExtender = "moa";

    (function (modularName, moaComponent) {
        if (ko.components.koPagination) {
            if (moaComponent.defaultSetting) {
                $.extend(true, ko.components.koPagination.defaultSetting, moaComponent.defaultSetting);
            }
            if (moaComponent.vmRender) {
                ko.components.koPagination.vmRender[modularName] = moaComponent.vmRender;
            }
            if (moaComponent.uiRender) {
                ko.components.koPagination.uiRender[modularName] = moaComponent.uiRender;
            }
            if (moaComponent.evtRender) {
                ko.components.koPagination.evtRender[modularName] = moaComponent.evtRender;
            }
        }
    }(paginationExtender, (function () {
        return {
            defaultSetting: {
                pageSizeArray: [10, 20, 50],
                vmRender: paginationExtender,
                uiRender: paginationExtender,
                evtRender: paginationExtender
            },
            vmRender: function (opts, uiModel, evtModel) {
                var optsMap = ko.toJS(opts),
                    templateHtml = "";

                function renderPageSizeInfo (pageSizeInfo, pageSizeArray, page) {
                    var $pageSizeInfo = $(pageSizeInfo),
                        $pageSize = $pageSizeInfo.find(".pagination-moa-pagesize");
                    $.each(pageSizeArray, function (i, pageSize) {
                        $pageSize.append('<option value="' + pageSize + '">' + pageSize + '</option>')
                    });
                    $pageSize.find("option[value='" + page.pageSize + "']").attr("selected", true);
                    return $pageSizeInfo[0].outerHTML.replace(/(PAGE_NO_BEGIN|PAGE_NO_END|TOTAL_RECORD_COUNT)/gi, function (matchName) {
                        switch (matchName) {
                            case "PAGE_NO_BEGIN": {
                                return Math.max(page.pageNo * page.pageSize + 1, 1);
                            }
                            case "PAGE_NO_END": {
                                return Math.min((page.pageNo + 1) * page.pageSize, page.totalRecordCount);
                            }
                            case "TOTAL_RECORD_COUNT": {
                                return page.totalRecordCount;
                            }
                        }
                    });
                }

                this.template = ko.computed(function () {
                    var page = {
                        pageNo: ko.unwrap(opts.pageNo) - 1,
                        pageSize: ko.unwrap(opts.pageSize),
                        totalPageCount: ko.unwrap(opts.totalPageCount),
                        totalRecordCount: ko.unwrap(opts.totalRecordCount)
                    }, itemHtmlNodes = [];
                    evtModel.unbindEvents($(templateHtml));

                    var pageSizeArray = ko.utils.arrayPushAll([], optsMap.pageSizeArray);
                    if (ko.utils.arrayIndexOf(optsMap.pageSizeArray, page.pageSize) == -1) {
                        pageSizeArray.push(page.pageSize);
                    }
                    itemHtmlNodes.push(renderPageSizeInfo(uiModel.getPageSizeInfo(), pageSizeArray.sort(function (v1, v2) {
                        return v1 > v2;
                    }), page));
                    itemHtmlNodes.push(uiModel.getPrevBtn(optsMap.text.prevBtnText, page.pageNo == 0));
                    itemHtmlNodes.push(uiModel.getNextBtn(optsMap.text.nextBtnText, page.pageNo == page.totalPageCount - 1));
                    itemHtmlNodes.push(uiModel.getForwardInfo(page.pageNo + 1));

                    var $template = $('<div></div>').html('<div class="pagination-moa-wrapper">' + itemHtmlNodes.join("") + '</div>');
                    evtModel.bindEvents($template, optsMap, page);
                    templateHtml = $template.html();
                    return templateHtml;
                }).extend({rateLimit: 50});
            },
            uiRender: (function () {
                function getBtn (btnText, isDisabled, className) {
                    return '<a href="javascript: void(0);" class="pagination-moa-btn ' + className + ' ' + (isDisabled ? 'disabled': '') + '">' + btnText + '</a>';
                }
                return {
                    getPrevBtn: function (prevBtnText, isDisabled) {
                        return getBtn(prevBtnText, isDisabled, "prev");
                    },
                    getNextBtn: function (nextBtnText, isDisabled) {
                        return getBtn(nextBtnText, isDisabled, "next");
                    },
                    getPageSizeInfo: function () {
                        return '<span class="pagination-moa-pagesizeinfo">顯示行數<select class="pagination-moa-pagesize"></select> PAGE_NO_BEGIN-PAGE_NO_END條/共TOTAL_RECORD_COUNT條</span>';
                    },
                    getForwardInfo: function (pageNo) {
                        return '前往<input type="text" class="pagination-moa-input" value="' + pageNo + '" />頁';
                    }
                };
            }()),
            evtRender:  {
                unbindEvents: function ($template) {
                    if ($template && $template.length) {
                        var $pageSize = $template.find(".pagination-moa-pagesize"),
                            $prevBrn = $template.find(".pagination-moa-btn.prev"),
                            $nextBrn = $template.find(".pagination-moa-btn.next"),
                            $input = $template.find(".pagination-moa-input");
                        $pageSize.off("change.moa");
                        $prevBrn.off("click.moa");
                        $nextBrn.off("click.moa");
                        $input.off("focus.moa blur.moa keyup.moa");
                    }
                },
                bindEvents: function ($template, optsMap, page) {
                    var $pageSize = $template.find(".pagination-moa-pagesize"),
                        $prevBtn = $template.find(".pagination-moa-btn.prev"),
                        $nextBtn = $template.find(".pagination-moa-btn.next"),
                        $input = $template.find(".pagination-moa-input"),
                        goPage = $.isFunction(optsMap.callback.click) ?
                            function (pageNo, pageSize) { optsMap.callback.click(pageNo + 1, pageSize); } : $.noop,
                        getElemId = function ($dom, prefix) {
                            var domId = $dom.attr("id");
                            if (!domId) {
                                domId = prefix + "-" + (new Date()).getTime();
                                $dom.attr("id", domId);
                            }
                            return domId;
                        },
                        bindEvent = function (callback, domId, page) {
                            (function (callback, domId, page) {
                                setTimeout(function () {
                                    callback(domId, page);
                                }, 50);
                            }(callback, domId, page));
                        },
                        pageSizeId = getElemId($pageSize, "moa-pagination-pageSize"),
                        prevBtnId = getElemId($prevBtn, "moa-pagination-prevBtn"),
                        nextBtnId = getElemId($nextBtn, "moa-pagination-nextBtn"),
                        inputId = getElemId($input, "moa-pagination-input");

                    bindEvent(function (pageSizeId, page) {
                        var $pageSize = $("#" + pageSizeId);
                        $pageSize.on("change.moa", function () {
                            var pageSize = +$(this).val();
                            goPage(0, pageSize);
                        });
                    }, pageSizeId, page);

                    bindEvent(function (prevBtnId, page) {
                        var $prevBtn = $("#" + prevBtnId);
                        $prevBtn.on("click.moa", function () {
                            if (page.pageNo > 0) {
                                goPage(page.pageNo - 1, page.pageSize);
                            }
                        });
                    }, prevBtnId, page);

                    bindEvent(function (nextBtnId, page) {
                        var $nextBtn = $("#" + nextBtnId);
                        $nextBtn.on("click.moa", function () {
                            if (page.pageNo < page.totalPageCount - 1) {
                                goPage(page.pageNo + 1, page.pageSize);
                            }
                        });
                    }, nextBtnId, page);

                    bindEvent(function (inputId, page) {
                        var $input = $("#" + inputId);
                        var pageNo = 0;
                        $input.on("focus.moa", function () {
                            pageNo = +$input.val();
                        }).on("blur.moa", function () {
                            var number = +$input.val() || pageNo;
                            $input.val(number--);
                            if (number != page.pageNo) {
                                goPage(number, page.pageSize);
                            }
                        }).on("keyup.moa", function (event) {
                        	if(event.keyCode==13){
                        		var number = +$input.val() || pageNo;
                        		$input.val(number--);
                        		if (number != page.pageNo) {
                        			goPage(number, page.pageSize);
                        		}
                        	}
                        });
                    }, inputId, page);
                }
            }
        };
    }())));
}(ko, jQuery));
