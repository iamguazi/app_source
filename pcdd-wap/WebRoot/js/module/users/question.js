;( function () {
	"use strict";
    var param_map = Result.getLocationParamObject();
    var user_id = param_map.user_id;
    var city_id = param_map.city_id;
    
	$( function () {
		loadList();
	}); 
	
	window.loadList = function(){
		var post_params = {
			"user_id" : user_id,
		};
		var $template = $( "#temp" ),
       		$list = $( "#list");
		
		Result.doResult( {
	    	url:  Interface.dong.getQuestList,
	       	type: "POST",
	       	async: false,
	       	data : post_params
	   	}, function ( data, count) {
	        $list.empty();
	        if( $.isArray( data ) ) {
        		for( var i = 0, l = data.length; i < l; i++ ) {
        			var obj = $template.clone().removeAttr( "id" );
        			 
        			//obj.attr('info_id', data[i].id);
                	$list.append( Result.fillData( obj, data[i], "field" ));
                }
	       	}
	         
	        
	        
	   	}, function ( desc ) {
	        Result.alert( "对不起:" + desc );
	    }, function ( err ) {
	        Result.alert( "错误:" + JSON.stringify(err));
	    } );
	}
	
	
 
	
	window.clickTitle = function(obj){
		 if($(obj).parent().find("dd:first").hasClass("am-in")){
			 $(obj).parent().find("dd:first").removeClass("am-in");
		 }else{
			 $(obj).parent().find("dd:first").addClass("am-in");
		 }
	};
	
	window.replaceId = function($field, id, rowVal){
		$field.attr("address_id", id);
		$field.attr("data-id", id);
	};
	
	
	
	window.toIndex = function(){
		Result.forward("myIndex.html", param_map);
	};
	

}( window ) );
