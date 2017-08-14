/**
 * Created by Administrator on 2015/6/19.
 */


;( function ( $, window ) {
    "use strict";
    
    var sequence = 1;

    function initPage () {
    	$("#pageNo, #pageSize").on("focus", function () {
    		$(this).data("pageNo", $(this).val());
    	}).blur(function () {
    		var $this = $(this);
    		var value = $this.val();
    		if (!/^\d+$/.test(value)) {
    			$this.val($this.data("pageNo"));
    		}
    	});
    }
    
    
   
    
    /**
     * 初始化file类型的文件自动赋予ID
     */
    function initFiles() {
    	$( "input:file" ).each( function () {
    		var $file = $( this );
    		if ( !$file.attr( "id" ) ) {
    			$file.attr( "id", "file_sequence_id_" + sequence++ );
    		}
    		if ( !$file.attr( "name" ) ) {
    			$file.attr( "name", "file_sequence_name_" + sequence++ );
    		}
    		if ( !$file.attr( "accept" ) ) {
    			$file.attr( "accept", ".jpg,.jpeg,.bmp,.png" );
    		}
    	} );
    }

    /**
     * 初始化插件
     */
    function initPlugins () {
    	initPage();
        //initValidate();
        initFiles();
    }

    
    function transformPage(page){
    	page = page || {};
    	return {
    		pageNo: page.pageNo || 1,
    		pageSize: page.pageSize || PAGE_SIZE,
    		pageCount: page.pageCount || page.totalPages || 0,
    		totalCount: page.totalCount || 0
    	}
    }
    
    function fillKOEntity ( modelEntity, newEntity ) {
    	if ( !modelEntity || !newEntity ) {
    		return;
    	}
    	for ( var name in modelEntity ) {
    		if ( $.isFunction( modelEntity[name] ) ) {
    			if ( newEntity[name] !== undefined ) {
    				modelEntity[name]( newEntity[name] );
    			} 
    			else {
    				modelEntity[name]( "" );
    			}
    		}
    	}
    }
    
    var util = {
    	showMsg: function (content, title, type, time) {
    		alert(content);
    	},
    	jsonToQueryString: function ( json, flag ) {
    		return json && ( function () {
    			var query = [];
    			for( var name in json ) {
    				if ( json[name] !== undefined && json[name] !== null && json[name] !== "" ) {
    					query.push( name + "=" + json[name] );    					
    				}
    			}
    			return query.join( flag || "&" );
    		} () ) || "";
    	},
    	stringToArray: function (data, property) {
    		if(data && data.id){
    			if(data && data[property]) {
    				if($.type(data[property]) === "string") {
    					data[property] = (data[property] || "") .split(",");	
    				}
    			}else {
    				data[property] = [];
    			}
    		}else{
    			data[property] = [];
    		}
    		
    		return data;
    	},
    	resetRemote: function ( $dom ) {
    		if ( $dom ) {
    			$dom.removeData("previousValue");
    		}
    	},
    	removeEmpty: function ( json ) {
    		return json && ( function () {
    			var ret = {};
    			for( var name in json ) {
    				if ( json[name] === undefined || json[name] === null || json[name] === "" ) {
    					
    				} else {
    					ret[name] = json[name];
    				}
    			}
    			return ret;
    		} () ) || {};
    	}
    };
    
    $( function () {

        initPlugins();
        
    } );
    
    window.util = util;
    window.PAGE_SIZE = 10;
    window.transformPage = transformPage;
    window.fillKOEntity = fillKOEntity;
    
} ( jQuery, window ) );
