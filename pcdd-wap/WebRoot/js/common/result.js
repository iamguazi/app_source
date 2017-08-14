/**
 * Created by chennh on 2015/4/21 0021.
 */
;( function ( $, window ) {
    "use strict";

    var ResultProxy = ( function () {

        var pages = {},

            opt = {

            getFieldValue: function ( $field ) {
                var tagName = ( $field[0].tagName || "" ).toLowerCase();
                if ( "input select textarea".indexOf( tagName ) > -1 ) {
                    return $field.val();
                } else {
                    return $field.html();
                }
            },

            page: {
                showPage: function ( $page, callback ) {
                    $page.css( {
                        left: "100%",
                        display: "block"
                    } ).animate( {
                        left: 0
                    }, function () {
                        if($.isFunction( callback ) ) {
                            callback();
                        }
                    } );
                },

                hidePage: function ( $page, callback ) {
                    $page.animate( {
                        left: "100%"
                    }, function (){
                        $page.hide();
                        if($.isFunction( callback ) ) {
                            callback();
                        }
                    } );
                }

            },

            validate: {
                methods: {
                    required: function ( value, element ) {
                        var val;
                        if ( element.nodeName.toLowerCase() === "select" ) {
                            val = $( element ).val();
                            return val && val.length > 0;
                        }
                        if ( opt.validate.checkable( element ) ) {
                            return opt.validate.getLength(value, element) > 0;
                        }
                        return $.trim( value ).length > 0;
                    },
                    email: function ( value, element ) {
                        return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(value);
                    },
                    url: function ( value, element ) {
                        return /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
                    },
                    mobile: function ( value, element ) {
                        return /^(((13[0-9]{1})|(14[0-9]{1})|(15[0-9]{1})|(17[0-9]{1})|(18[0-9]{1}))+\d{8})$/.test( value );
                    },
                    date: function ( value, element ) {
                        return !/Invalid|NaN/.test( new Date( value ).toString() );
                    },
                    dateISO: function ( value, element ) {
                        return /^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/.test( value );
                    },
                    number: function ( value, element ) {
                        return /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test( value );
                    },
                    digits: function ( value, element ) {
                        return /^\d+$/.test( value );
                    },
                    creditcard: function( value, element ) {
                        // accept only spaces, digits and dashes
                        if ( /[^0-9 \-]+/.test(value) ) {
                            return false;
                        }
                        var nCheck = 0,
                            nDigit = 0,
                            bEven = false;

                        value = value.replace(/\D/g, "");

                        for (var n = value.length - 1; n >= 0; n--) {
                            var cDigit = value.charAt( n );
                            nDigit = parseInt( cDigit, 10 );
                            if ( bEven ) {
                                if ( ( nDigit *= 2 ) > 9 ) {
                                    nDigit -= 9;
                                }
                            }
                            nCheck += nDigit;
                            bEven = !bEven;
                        }

                        return ( nCheck % 10 ) === 0;
                    },
                    minLength: function( value, element, param ) {
                        var length = $.isArray( value ) ? value.length : opt.validate.getLength( $.trim( value ), element );
                        return length >= param;
                    },

                    // http://docs.jquery.com/Plugins/Validation/Methods/maxlength
                    maxLength: function( value, element, param ) {
                        var length = $.isArray( value ) ? value.length : opt.validate.getLength( $.trim( value ), element );
                        return length <= param;
                    },

                    // http://docs.jquery.com/Plugins/Validation/Methods/rangelength
                    rangeLength: function( value, element, param ) {
                        var length = $.isArray( value ) ? value.length : opt.validate.getLength( $.trim( value ), element );
                        return ( length >= param[0] && length <= param[1] );
                    },

                    // http://docs.jquery.com/Plugins/Validation/Methods/min
                    min: function( value, element, param ) {
                        return value >= param;
                    },

                    // http://docs.jquery.com/Plugins/Validation/Methods/max
                    max: function( value, element, param ) {
                        return value <= param;
                    },

                    // http://docs.jquery.com/Plugins/Validation/Methods/range
                    range: function( value, element, param ) {
                        return ( value >= param[0] && value <= param[1] );
                    },

                    // http://docs.jquery.com/Plugins/Validation/Methods/equalTo
                    equalTo: function( value, element, param ) {
                        var $target = $( param );
                        return value === $target.val();
                    }
                },
                messages: {
                    required		: "请完善信息.",
                    email			: "请输入正确的邮箱地址.",
                    url				: "请输入合法的网址.",
                    mobile			: "请输入正确的11位手机号", //请输入正确的手机号码.
                    date			: "请输入合法的日期.",
                    dateISO			: "请输入合法的日期 (ISO).",
                    number			: "请输入合法的数字.",
                    digits			: "只能输入整数.",
                    creditcard		: "请输入合法的信用卡号.",
                    equalTo			: "请再次输入相同的值.",
                    accept			: "请输入拥有合法后缀名的字符串.",
                    maxLength		: "请输入一个 长度最多是 {0} 的字符串.",
                    minLength		: "请输入一个 长度最少是 {0} 的字符串.",
                    rangeLength		: "请输入 一个长度介于 {0} 和 {1} 之间的字符串.",
                    range			: "请输入一个介于 {0} 和 {1} 之间的值.",
                    max				: "请输入一个最大为{0} 的值.",
                    min				: "请输入一个最小为{0} 的值."
                },
                checkable: function( element ) {
                    return ( /radio|checkbox/i ).test( element.type );
                },

                getLength: function( value, element ) {
                    switch( element.nodeName.toLowerCase() ) {
                        case "select":
                            return $( "option:selected", element ).length;
                        case "input":
                            if ( opt.validate.checkable( element) ) {
                                return $( element ).parents( "form" ).findByName( element.name ).filter( ":checked" ).length;
                            }
                    }
                    return value.length;
                },
                format: function ( source, params ) {
                    if ( arguments.length === 1 ) {
                        return function() {
                            var args = $.makeArray( arguments );
                            args.unshift( source );
                            return opt.validate.format.apply( this, args );
                        };
                    }
                    if ( arguments.length > 2 && params.constructor !== Array  ) {
                        params = $.makeArray( arguments ).slice( 1 );
                    }
                    if ( params.constructor !== Array ) {
                        params = [ params ];
                    }
                    $.each( params, function( i, n ) {
                        source = source.replace( new RegExp( "\\{" + i + "\\}", "g" ), function() {
                            return n;
                        } );
                    } );
                    return source;
                },
                showErrorMsg: function ( source, params ) {
                    alert( opt.validate.format( source, params ) );
                },
                getParams: function ( vldMsg ) {
                    var ret = [],
                        params;
                    if ( vldMsg && vldMsg.indexOf( ":" ) > -1 ) {
                        params = vldMsg.split( ":" );
                        params.shift();
                        ret = params.filter( function ( value, i, arr ) {
                            return !!value;
                        } );
                    }
                    return ret;
                },
                validateForm: function ( $form ) {
                    var bIsValid = true;
                    if( $form && $form.length && $form.length > 0 ) {
                        $form.find( "[resultVld]" ).each( function () {
                            if ( opt.validate.validateElement( this ) !== true ) {
                                bIsValid = false;
                                return false;
                            }
                        } );
                    }
                    return bIsValid;
                },
                validateElement: function ( element ) {
                    var $field = $( element ),
                        resultVld = $.trim( $field.attr( "resultVld" ) ),
                        vlds = null,
                        i, l,
                        vldName,
                        params = null;
                    if ( resultVld !== "" ) {
                        vlds = resultVld.split( "||" );
                        for ( i = 0, l = vlds.length; i < l; i++ ) {
                            if ( /\:/.test( vlds[i] ) ) {
                                vldName = $.trim( vlds[i].split( ":" )[0] );
                            } else {
                                vldName = $.trim( vlds[i] );
                            }
                            if ( $.isFunction( opt.validate.methods[vldName] ) ) {
                                params = opt.validate.getParams( vlds[i] );
                                if ( opt.validate.methods[vldName]( opt.getFieldValue( $field ), element, params ) !== true ) {
                                    if ( !$field.hasClass( "result-invalid" ) ) {
                                        $field.addClass( "result-invalid" );
                                    }
                                    opt.validate.showErrorMsg( opt.validate.messages[vldName] , params );
                                    $field.focus();
                                    return false;
                                } else {
                                    $field.removeClass( "result-invalid" );
                                }
                            }
                        }
                        return true;
                    }
                },
                resetForm: function ( $form ) {
                    $form.find( "[resultVld]" ).removeClass( "result-invalid" );
                }
            }
        };


        return {

            /**
             * json化$form表单数据
             * @param $form jQuery对象
             * @param excludeBlank 是否排除值为空的字段
             * @returns {{}}
             */
            getFormData: function ( $form, excludeBlank ) {
                var ret = {},
                    serializeArray = $form.serializeArray(),
                    name,
                    value,
                    i = 0,
                    field,
                    l = serializeArray && serializeArray.length || 0;
                if( $.type( excludeBlank ) !== "boolean" ) {
                    excludeBlank = false;
                }
                if ( l > 0 ) {
                    for ( ; i < l; i++ ) {
                        field = serializeArray[i];
                        name = field["name"];
                        value = $.trim( field["value"] || "" );
                        if ( value === "" && excludeBlank === true ) {
                            continue;
                        }
                        ret[name] = value;
                    }
                }
                return ret;
            },
            /**
             * json字符串转json对象
             */
            jsonEval : ( function () {
                return String.prototype.parseJSON ?
                    function ( data ) {
                        return String.prototype.parseJSON.call( data );
                    } :
                    window.JSON ?
                        function ( data ) {
                            return JSON.parse( data );
                        } :
                        function ( data ) {
                            return eval( "(" + data + ")" );
                        }
            } () ),
            /**
             * 指定作用域和参数执行函数
             * @param func 待执行的函数
             * @param scope 指定的作用域
             */
            applyFunction: function ( func, scope ) {
                var args = [];
                Array.prototype.unshift.apply( args, arguments );
                if ( $.isFunction( func ) ) {
                    if ($.type( scope ) !== "object" ) {
                        scope = null;
                    }
                    Array.prototype.splice.call( args, 0, 2 );
                    func.apply( scope, args );
                }
            },
            /**
             * 执行ajax
             * @param opt
             */
            doAjax: function ( opt ) {
                opt = $.extend( true, {}, ResultProxy.defaults.ajax.setting, opt );
                if ( $.support && $.support.cors !== undefined ) {
                    $.support.cors = true;
                }
                $.ajax( opt );
            },
            /**
             * 执行特定返回值判定的ajax
             * @param opt
             * @param successCallback 成功回掉函数
             * @param failCallback 失败回掉函数
             * @param errorCallback 异常回掉函数
             */
            doResult: function ( opt, successCallback, failCallback, errorCallback ) {
                opt = $.extend( true, {}, ResultProxy.defaults.ajax.setting, {
                    success: function ( response ) {
                        try {
                            var resJson = ResultProxy.jsonEval( response),
                                status = resJson[opt.result.status],
                                data = resJson[opt.result.data],
                                count = resJson[opt.result.count],
                                desc = resJson[opt.result.desc];
                            if ( status === opt.result.success ) {
                                ResultProxy.applyFunction( successCallback, null, data, count);
                            } else if ( status === opt.result.fail ) {
                                ResultProxy.applyFunction( failCallback, null, desc );
                            } else {
                                ResultProxy.applyFunction( failCallback, null, "错误的返回码: " + status );
                            }
                        } catch ( e ) {
                            ResultProxy.applyFunction( errorCallback, null, e );
                        }
                        if( opt.$mask && $.fn.unmask ) {
                            opt.$mask.unmask();
                        }
                    },
                    error: function ( err ) {
                        ResultProxy.applyFunction( errorCallback, null, err );
                        if( opt.$mask && $.fn.unmask ) {
                            opt.$mask.unmask();
                        }
                    }
                }, opt );

                // stringify data on post if data is json
                if ( opt.result.stringifyOnPost === true && ( opt.type || "" ).toLowerCase() === "post" && $.type( opt.data ) === "object" ) {
                    opt.data = JSON.stringify( opt.data );
                }

                if( opt.$mask && $.fn.mask ) {
                    opt.$mask.mask();
                }
                if ( $.support && $.support.cors !== undefined ) {
                    $.support.cors = true;
                }
                $.ajax( opt );
            },
            /**
             * 填充数据
             * @param $template 模版jQuery对象
             * @param rowVal 数据对象
             * @param attrName 属性选择器
             */
            fillData: function ( $template, rowVal, attrName ) {
                var fieldName,
                    fieldValue,
                    $field = null,
                    replaceFunc,
                    type,
                    tagName,
                    /**
                     * 填充值，如果fieldValue不为 number或string 类型则抛出一个 TypeError异常
                     * @param $field
                     * @param fieldName string
                     * @param fieldValue string | number
                     */
                    fillValue = function ( $field, fieldName, fieldValue ) {
                        var type = $.type( fieldValue),
                            tagName = $field[0].tagName.toLowerCase();
                        if( fieldValue === null || fieldValue === undefined || fieldValue !== fieldValue ) {
                            return ;
                        }
                        if( type === "string" || type === "number" ) {
                            // 确保正确的赋值
                            if( ["input", "select", "textarea"].indexOf( tagName ) > -1 ) {
                                $field.val( fieldValue );
                            } else {
                                fieldValue += "";
                                $field.html( fieldValue );
                            }
                        } else {
                            throw new TypeError( "rowVal[" + fieldName + "] 必须为string或number，实际为: " + type );
                        }
                    };
                if( $template && $.type( $template ) === "object" && $template.length > 0 &&
                    rowVal && $.type( rowVal ) === "object" ) {
                    if($.type( attrName ) !== "string" ) {
                        attrName = "field";
                    }
                    $template.find( "[ATTR_NAME]".replace( /ATTR_NAME/, attrName ) ).each( function(){
                        $field = $( this );
                        fieldName = $field.attr( attrName);
                        // 驼峰转下划线
                        if( fieldName ) {
                            fieldName = fieldName.replace( /[A-Z]+/g, function ( letter ) {
                                return "_" + letter.toLowerCase();
                            } );
                        }
                        replaceFunc = $field.attr( "replaceFunc" );
                        fieldValue = rowVal[fieldName];
                        if( replaceFunc !== undefined && replaceFunc !== "" ) {
                            if( $.isFunction( window[replaceFunc] ) ) {
                                window[replaceFunc].call( null, $field, fieldValue, rowVal );
                            } else {
                                try {
                                    // 用rowVal对象中的值(string|number，否则抛出TypeError)填充replaceFunc的参数，参数格式必须为大写字母加下划线
                                    replaceFunc = replaceFunc.replace( /[A-Z_]+/g, function ( word ) {
                                        var name = word.toLowerCase(),
                                            type;
                                        if ( rowVal[name] !== undefined && rowVal[name] !== null ) {
                                            type = $.type( rowVal[name] );
                                            if( type === "string" ) {
                                                return "'" + rowVal[name] + "'";
                                            } else if ( type === "number" ) {
                                                return rowVal[name];
                                            } else {
                                                throw new TypeError( "rowVal[" + name + "] 必须为string或number，实际为: " + type );
                                            }
                                        }
                                        return word;
                                    } );
                                    fieldValue = eval( replaceFunc );
                                    fillValue( $field, fieldName, fieldValue );
                                } catch ( e ) {
                                    throw new EvalError( "运行表达式错误: " + replaceFunc + ", e: " + e.message );
                                }
                            }
                        } else {
                            fillValue( $field, fieldName, fieldValue );
                        }
                    } );
                }
                return $template;
            },
            /**
             * 日期格式化
             * @param datetime number | Date | 特殊的Object
             * @param format 如: yyyy-MM-dd hh:mm:ss
             * @returns {*}
             */
            formatDate: function ( datetime, format ) {
                var type = $.type( datetime );
                if( type === "object" && type.time ) {
                    datetime = new Date( datetime.time );
                } else if ( type === "number" ) {
                    datetime = new Date( datetime );
                } else if( type !== "date" ) {
                    throw new TypeError( "日期格式化错误，datetime类型不匹配" );
                }
                if($.type( format ) !== "string" ) {
                    format = "yyyy-MM-dd hh:mm:ss";
                }
                return datetime.format( format );
            },
            /**
             * 统一alert
             * @param msg
             */
            alert: function ( msg ) {
                window.alert( msg );
                return ResultProxy;
            },
            /**
             * 统一confirm
             * @param msg
             * @param title
             * @param okCallback
             * @param cancelCallback
             */
            confirm: function ( msg, title, okCallback, cancelCallback ) {
                var result = window.confirm( msg );
                if( result === true ) {
                    if( $.isFunction( okCallback ) ) {
                        okCallback.call( null );
                    }
                } else {
                    if( $.isFunction( cancelCallback ) ) {
                        cancelCallback.call( null );
                    }
                }
                return ResultProxy;
            },
            /**
             * 序列化给定的参数并跳转到地址targetLocation
             * @param targetLocation
             * @param paramObject
             * @param removeEmpty
             */
            forward: function ( targetLocation, paramObject, removeEmpty ) {
                var type = $.type( targetLocation ),
                    factorIndex = -1,
                    name,
                    paramStr = "";
                if ( type !== "string" ) {
                    throw new TypeError( "targetLocation 必须是string类型，实际上是 " + type );
                }
                removeEmpty = !!removeEmpty;
                factorIndex = targetLocation.indexOf( "?" );
                type = $.type( paramObject );
                if ( type === "object" ) {
                    for( name in paramObject ) {
                        if ( paramObject.hasOwnProperty( name ) ) {
                            if ( paramObject[name] == null ) {
                                if ( !removeEmpty ) {
                                    paramStr += "&" + name + "=";
                                }
                            } else {
                                paramStr += "&" + name + "=" + paramObject[name];
                            }
                        }
                    }
                } else {
                    paramStr = "&" + paramObject;
                }
                if ( paramStr !== "" ) {
                    if ( factorIndex === -1 ) {
                        targetLocation = targetLocation + "?" + paramStr.substring( 1 );
                    } else if ( factorIndex === targetLocation.length - 1 ) {
                        targetLocation = targetLocation + paramStr.substring( 1 );
                    } else {
                        targetLocation = targetLocation + paramStr;
                    }
                }
                window.location.href = encodeURI( targetLocation );
            },
            /**
             * 获取地址栏参数对象
             * @returns {{}}
             */
            getLocationParamObject: function () {
                var ret = {},
                    href = window.location.href,
                    arr,
                    entityArr,
                    search,
                    i, l;
                if ( href !== "" && href.indexOf( "?" ) > -1 ) {
                    arr = href.split( "?" );
                    arr.shift();
                    search = arr.join( "?" );
                    arr = search.split( "&" );
                    for ( i = 0, l = arr.length; i < l; i++ ) {
                        entityArr = arr[i].split( "=" );
                        ret[entityArr[0]] = entityArr[1] === undefined ? "" : decodeURI( entityArr[1] );
                    }
                }
                return ret;
            },
            /**
             * 根据参数名从地址栏中获取值
             * @param name
             * @returns {string}
             */
            getLocationParamByName: function ( name ) {
                var search = window.location.search,
                    ret = "",
                    reg = null,
                    m;
                if ( search ) {
                    reg = new RegExp( "(^|&)" + name + "=([^&]*)(&|$)" );
                    m = search.substr( 1 ).match( reg );
                    if ( m !== null || m !== undefined || m.length > 0 ) {
                        ret = m[2];
                    }
                }
                return ret;
            },
            /**
             * form表单验证，循环遍历包含属性[resultVld]的对象
             * 验证写法：resultVld="required || minLength: 5"
             * @param $form
             * @returns
             */
            validateForm: function ( $form ) {
                return opt.validate.validateForm( $form );
            },
            /**
             * 重置表单验证
             * @param $form
             * @returns
             */
            resetForm: function ( $form ) {
                opt.validate.resetForm( $form );
            },
            /**
             * 验证单个元素
             * 验证写法：resultVld="required || minLength: 5"
             * @param $element
             * @returns
             */
            validateElement: function( $element ) {
                if ( $element && $element.length ) {
                    return opt.validate.validateElement( $element[0] );
                } else {
                    return opt.validate.validateElement( $( $element )[0] );
                }
            },
            /**
             * 资源字符串替换
             * 如： replaceSource( "http://www.baidu.com?id={ID}", {ID: 1} ) => http://www.baidu.com?id=1
             * @param source
             * @param replaceObj
             * @returns {*}
             */
            replaceSource: function ( source, replaceObj ) {
                if( !source || !replaceObj ) return source;
                var reg = /(\{(.*?)\})/,
                    matches;
                while( matches = source.match( reg ) ){
                    source = source.replace( matches[1], replaceObj[matches[2]] );
                }
                return source;
            },
            /**
             * 字符串转xml
             * @param txt
             * @returns {*}
             */
            txt2Xml: function ( txt ) {
                if( txt && $.type( txt ) === "string" ){
                    try{
                        txt = txt.replace( /<\!\[CDATA\[/g, "" ).replace( /\]\]>/g, "" );
                        return new DOMParser().parseFromString( txt, "text/xml" );
                    }catch( e ){
                        var xmlDom = new ActiveXObject( "Microsoft.XMLDOM" );
                        xmlDom.loadXML( txt );
                        return xmlDom;
                    }
                }
                return null;
            },
            /**
             * json对象转xml
             * @param jsonObj
             * @param rootNode
             * @returns {*}
             */
            json2Xml: function ( jsonObj, rootNode ) {
                if( $.type( jsonObj ) !== 'object') {
                    return;
                }
                var xmldoc = [];
                xmldoc.push( '' );
                if( rootNode ) {
                    xmldoc.push( '<' + rootNode + '>' );
                }
                function toXml( jsonObj ) {
                    for ( var m in jsonObj ) {
                        if ( $.isArray( jsonObj[m] ) ) {
                            xmldoc.push( '<' + m + '>' );
                            for ( var i = 0, len = jsonObj[m].length; i < len; i++ ) {
                                toXml( jsonObj[m][i] );
                            }
                            xmldoc.push( '' );
                        } else if ($.type( jsonObj[m] ) === "object" ) {
                            xmldoc.push( '<' + m + '>' );
                            toXml( jsonObj[m] );
                            xmldoc.push( '</' + m + ">" );
                        } else {
                            xmldoc.push( '<' + m + '>' + jsonObj[m] + '</' + m + ">" );
                        }
                    }
                }
                toXml( jsonObj );
                if( rootNode ) {
                    xmldoc.push( '</' + rootNode + ">" );
                }
                return Result.txt2Xml( xmldoc.join( '' ) );
            },
            /**
             * xml转json对象
             * @param xml
             * @returns {{}}
             */
            xml2JSON: function ( xml ) {
                var getNodeName = function ( name ) {
                        return name && $.trim( name.replace(/^#/, "") ) || name;
                    },
                    getNodeValue = function ( value ) {
                        return $.trim( value );
                    },
                    nodeName;
                if ($.type( xml ) === "string" ) {
                    xml = Result.txt2Xml( xml );
                }

                // Create the return object
                var obj = {};

                if ( xml.nodeType == 1 ) { // element
                    // do attributes
                    if ( xml.attributes.length > 0 ) {
                        obj["@attributes"] = {};
                        for ( var j = 0; j < xml.attributes.length; j++ ) {
                            var attribute = xml.attributes.item( j );
                            nodeName = getNodeName( attribute.nodeName );
                            obj["@attributes"][nodeName] = getNodeValue( attribute.nodeValue );
                        }
                    }
                } else if ( xml.nodeType == 3 ) { // text
                    obj = getNodeValue( xml.nodeValue );
                }

                // do children
                if ( xml.hasChildNodes() ) {
                    for ( var i = 0; i < xml.childNodes.length; i++ ) {
                        var item = xml.childNodes.item( i );
                        nodeName = getNodeName( item.nodeName );
                        if ( typeof ( obj[nodeName] ) == "undefined" ) {
                            obj[nodeName] = Result.xml2JSON( item );
                        } else {
                            if ( $.isArray( obj[nodeName] ) !== "array" ) {
                                var old = obj[nodeName];
                                obj[nodeName] = [];
                                obj[nodeName].push( old );
                            }
                            obj[nodeName].push( Result.xml2JSON( item ) );
                        }
                    }
                }
                return obj;
            },
            /**
             * 单页面应用使用iframe载入其他页面
             * @param url
             * @param name 唯一标识一个页面
             * @param callback 载入动画结束后的回调函数，function ( $page ) {}
             * @param forceReload 强制重新载入页面
             */
            loadPage: function ( url, name, callback, forceReload ) {
                forceReload = $.type( forceReload ) === "boolean" ? forceReload : false;
                if ( !pages[name] || forceReload === true ) {
                    if ( pages[name] && forceReload === true ) {
                        pages[name].remove();
                        delete pages[name];
                    }
                    var $page = $( "<iframe class='result-page'></iframe>" ).appendTo( "body" );
                    pages[name] = $page;
                    $page.attr( "src", url );
                }
                opt.page.showPage( pages[name], function (){
                    if ($.isFunction( callback ) ) {
                        callback( pages[name] );
                    }
                } );
            },
            /**
             * 配合loadPage使用的返回上一页
             * @param name 页面的唯一标识
             * @param callback 返回动画结束后的回调函数， function ( parentWindow ) {}
             * @param isDestory 是否销毁当前name对应的页面，默认false
             * @param isParent 子页面中调用请设置为true，默认true
             */
            backPage: function ( name, callback, isDestory, isParent ) {
                isDestory = $.type( isDestory ) === "boolean" ? isDestory : false;
                isParent = $.type( isParent ) === "boolean" ? isDestory : true;
                if ( isParent === true ) {
                    if ( window.parent.Result && window.parent.Result.backPage ) {
                        window.parent.Result.backPage( name, callback, isDestory, false );
                    }
                } else {
                    if ( pages[name] ) {
                        opt.page.hidePage( pages[name], function () {
                            if ( isDestory === true ) {
                                pages[name].remove();
                                delete pages[name];
                            }
                            if ($.isFunction( callback ) ) {
                                callback.call( null, window );
                            }
                        } );
                    }
                }
            }
        };
    } () );


    $( function () {

        $.extend( true, ResultProxy, {
            /**
             * 请求成功的result_code编码
             */
            SUCCESS: "0",
            /**
             * 请求失败的result_code编码
             */
            FAIL: "1",

            defaults: {
                ajax: {
                    setting: {
                        type: "POST",
                        async: true,
                        contentType: "application/x-www-form-urlencoded",
                        data: null,
                        url: null,
                        dataType: "text",
                        success: null,
                        error: null,
                        $mask: $( "body" ),
                        result: {
                            status: "result_code",
                            data: "data",
                            desc: "result_desc",
                            count: "result_count",
                            success: "0",
                            fail: "1",
                            stringifyOnPost: true
                        }
                    }
                }
            }
        } );

    } );


    // Date 扩展
    if( !Date.prototype.format ) {
        // 日期格式化,yyyy-MM-dd hh:mm:ss
        Date.prototype.format = function ( format ) {
            var o = {
                "M+" : this.getMonth() + 1, 						// month
                "d+" : this.getDate(), 								// day
                "h+" : this.getHours(), 							// hour
                "m+" : this.getMinutes(), 							// minute
                "s+" : this.getSeconds(), 							// second
                "q+" : Math.floor( ( this.getMonth() + 3 )/3 ),		// quarter
                "S" : this.getMilliseconds() 						// millisecond
            }
            if( /(y+)/.test( format ) ) {
                format = format.replace( RegExp.$1, ( this.getFullYear() + "" ).substr( 4 - RegExp.$1.length ) );
            }

            for( var k in o ) {
                if( new RegExp( "(" + k + ")" ).test( format ) ) {
                    format = format.replace( RegExp.$1, RegExp.$1.length == 1 ? o[k] : ( "00"+ o[k] ).substr( ( "" + o[k] ).length ) );
                }
            }
            return format;
        }
    }

    window["Result"] = ResultProxy;

} ( jQuery, window ) );
