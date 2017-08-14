/**
 * Created by chennh on 2014/10/16 0016.
 */
;(function (window, $) {
    var T = function($table, s){
        return this.init($table, s);
    };

    $.extend(true, T, {

        prototype: {
            setting: {
                field: [],
                usePage: true,
                oddCls: "odd",
                selectedCls: "selected",
                multiple: false,
                replaceField: {
                    ROWNUM: function ($td, i, rowVal) {
                        return $td.html(i + 1);
                    }
                }
            },
            store: {

            },
            $obj: {
                $table: null
            },
            init: function ($table, s) {
                this.setSetting($table, s);
                return this;
            },
            setSetting: function ($table, s) {
                this.setting = $.extend(true, {}, this.setting, s);
                this.store = $.extend(true, {}, this.store);
                this.$obj = $.extend(true, {}, this.$obj, {
                    $table: $table
                });
                return this;
            },
            buildBlockTable: function (rowNum, colNum) {
                rowNum = !!+rowNum ? rowNum : 10;
                rowNum = Math.max(rowNum, 0);
                rowNum = Math.min(rowNum, 100);
                colNum = !!+colNum ? colNum : this.setting.field.length;
                colNum = Math.max(colNum, 0);
                colNum = Math.min(colNum, 20);

                var $table = this.$obj.$table,
                    $tbody = $table.children("tbody:first"),
                    $tr, $td, empty = "", self = this,
                    i, j;
                if($tbody.length === 0){
                    $tbody = $("<tbody></tbody>").appendTo($table);
                }else{
                    $tbody.empty();
                }

                for(i = 0; i < rowNum; i++){
                    $tr = $("<tr></tr>");
                    for(j = 0; j < colNum; j++){
                        $td = $("<td></td>");
                        $tr.append($td.html(empty));
                    }
                    $tbody.append($tr);
                }

                $tbody.children("tr").off("click")
                    .on("click", function () {
                        var $this = $(this);
                        $this.toggleClass(self.setting.selectedCls);
                        if(self.setting.multiple !== true){
                            $this.siblings().removeClass(self.setting.selectedCls);
                        }
                    }).filter(":odd").addClass(this.setting.oddCls);
                return this;
            },
            buildDataTable: function (dataList) {
                if(!$.isArray(dataList))    return this;

                var $table = this.$obj.$table,
                    $tbody = $table.children("tbody:first"),
                    replaceField = this.setting.replaceField,
                    field = this.setting.field, $tr, $td, empty = "",
                    i, j, rl, cl, tdText;

//                if($tbody.children("tr").length < dataList.length){
//                    // 如果返回的数据数量大于表格行数，则执行表格重绘
                    this.buildBlockTable(dataList.length, 0);
//                }

                for(i = 0, rl = dataList.length; i < rl; i++ ){
                    $tr = $tbody.children("tr").eq(i);
                    $tr.data("rowVal", dataList[i]);
                    for(j = 0, cl = field.length; j < cl; j++){
                        $td = $tr.children("td").eq(j);
                        tdText = dataList[i][field[j]];
                        if(typeof tdText !== "number" && !tdText){
                        	tdText = empty;
                        }
                        $td.attr("title", tdText).attr("fieldName", field[j]);
                        if($.isFunction(replaceField[field[j]])){
                            replaceField[field[j]].call(null, $td, i, dataList[i]);
                        }else{
                            $td.html(tdText);
                        }
                    }
                }

                return this;
            }
        }
    });

    $.extend(true, $, {
            table: function ($table, s) {
                $table.each(function () {
                    var $this = $(this),
                        t = $this.data("jquery_table_t");
                    if(undefined === t){
                        t = new T($this, s);
                        $this.data("jquery_table_t", t);
                    }
                });
                return $table.first().data("jquery_table_t");
            }
    });

}(window, jQuery));
