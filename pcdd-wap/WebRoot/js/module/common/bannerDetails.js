;( function () {
    "use strict";
    
	 $( function () {
	 	 var param_map=Result.getLocationParamObject();
 		Result.doResult( {
            url:  Interface.dong.bannerDetails  ,
            data: param_map,
            type: "POST"
        }, function ( data ) {
        	if(data.url==""||data.url==null){
        		$("#content").html(data.content);
        	}else{
        		window.location.href=data.url;
        	}
        	
        }, function ( desc ) {
            alert( "fail: " + desc );
        }, function ( err ) {
            alert( "error" + err );
        } );
        
        
	 });
	 
	
}( window ) );
