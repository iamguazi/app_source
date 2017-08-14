;( function () {
    "use strict";
    
	 $( function () {
	 	 
        
	 });
	 
	window.wapDetails=function(key){
		var param_map={};
		param_map.wap_key=key;
 		Result.doResult( {
            url:  Interface.dong.wapDetails  ,
            data: param_map,
            type: "POST"
        }, function ( data ) {
        	$("#content").html(data.wap_content);
        }, function ( desc ) {
            alert( "fail: " + desc );
        }, function ( err ) {
            alert( "error" + err );
        } );
        
	}
	
	
	
	

}( window ) );
