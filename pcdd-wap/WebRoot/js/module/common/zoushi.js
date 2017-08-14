;( function () {
    "use strict";
    
	 $( function () {
	 	 var param_map=Result.getLocationParamObject();
 		Result.doResult( {
            url:  Interface.dong.getOpenList  ,
            data: param_map,
            type: "POST"
        }, function ( data ) {
        	var data=data.open_time;
        	var $template = $( "#temp" ),
       			$head = $( "#head" ),
		        $list = $( "#list" );
			    $list.empty();
			    $list.append($head.clone());
			    for( var i = 0, l =data.length; i < l; i++ ) {
			        var tem=$template.clone().removeAttr( "id" );
			        
				    $list.append( Result.fillData(tem ,data[i], "field" ) );
			                	
			     }
			    
			    
        }, function ( desc ) {
            alert( "fail: " + desc );
        }, function ( err ) {
            alert( "error" + err );
        } );
        
        
	 });
	 
	 window. replace_type1 = function ( $field, game_result, rowVal ) {
		if(game_result>=14){
			$field.html("大");
			$field.addClass("font-color-3 bg5");
		}
	};
	
	 window. replace_type2 = function ( $field, game_result, rowVal ) {
		if(game_result<=13){
			$field.html("小");
			$field.addClass("font-color-3 bg5");
		}
	};
	
	 window. replace_type3 = function ( $field, game_result, rowVal ) {
		if(game_result%2!=0){
			$field.html("单");
			$field.addClass("font-color-3 bg6");
		}
	};
	
	 window. replace_type4 = function ( $field, game_result, rowVal ) {
		if(game_result%2==0){
			$field.html("双");
			$field.addClass("font-color-3 bg6");
		}
	};
	
	
	window. replace_type5 = function ( $field, game_result, rowVal ) {
		if(game_result%2!=0&&game_result>=14){
			$field.html("大单");
			$field.addClass("font-color-3 bg7");
		}
	};
	
	 window. replace_type6 = function ( $field, game_result, rowVal ) {
		if(game_result%2!=0&&game_result<=13){
			$field.html("小单");
			$field.addClass("font-color-3 bg7");
		}
	};
	
	window. replace_type7 = function ( $field, game_result, rowVal ) {
		if(game_result%2==0&&game_result>=14){
			$field.html("大双");
			$field.addClass("font-color-3 bg7");
		}
	};
	
	 window. replace_type8 = function ( $field, game_result, rowVal ) {
		if(game_result%2==0&&game_result<=13){
			$field.html("小双");
			$field.addClass("font-color-3 bg7");
		}
	};
	
}( window ) );
