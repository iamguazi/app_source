;( function () {
    "use strict";
    
	 $( function () {
	 	  var param_map=Result.getLocationParamObject();
	 	  if(param_map.room_id==1||param_map.room_id==4){
	 		 wapDetails('CHU_JI') 
	 	  }
	 	  
	 	  if(param_map.room_id==2||param_map.room_id==5){
	 		 wapDetails('ZHONG_JI') 
	 	  }
	 	  
	 	  if(param_map.room_id==3||param_map.room_id==6){
	 		 wapDetails('GAO_JI') 
	 	  }
        
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
        	$("#title").html(data.wap_title);
        }, function ( desc ) {
            alert( "fail: " + desc );
        }, function ( err ) {
            alert( "error" + err );
        } );
        
	}
}( window ) );
