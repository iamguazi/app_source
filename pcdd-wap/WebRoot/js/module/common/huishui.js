;( function () {
    "use strict";
    
	 $( function () {
	 	 var param_map=Result.getLocationParamObject();
 		Result.doResult( {
            url:  Interface.dong.gethuishuiList  ,
            data: param_map,
            type: "POST"
        }, function ( data ) {
        	var room1=data.room1;
        	var $template = $( "#temp" ),
       			$head = $( "#head" ),
		        $list = $( "#list1" );
			    $list.empty();
			    $list.append($head.clone());
			    for( var i = 0, l =room1.length; i < l; i++ ) {
			        var tem=$template.clone().removeAttr( "id" );
			        room1[i].start=room1[i].start_point+"~"+room1[i].end_point;
			        room1[i].bili=accMul(room1[i].bili,100)
				    $list.append( Result.fillData(tem ,room1[i], "field" ) );
			                	
			     }
			    
			     room1=data.room2;
			    $list = $( "#list2" );
			    $list.empty();
			    $list.append($head.clone());
			    for( var i = 0, l =room1.length; i < l; i++ ) {
			        var tem=$template.clone().removeAttr( "id" );
			        room1[i].start=room1[i].start_point+"~"+room1[i].end_point;
			        room1[i].bili=accMul(room1[i].bili,100)
				    $list.append( Result.fillData(tem ,room1[i], "field" ) );
			                	
			     }
			    
			      room1=data.room3;
			    $list = $( "#list3" );
			    $list.empty();
			    $list.append($head.clone());
			    for( var i = 0, l =room1.length; i < l; i++ ) {
			        var tem=$template.clone().removeAttr( "id" );
			        room1[i].start=room1[i].start_point+"~"+room1[i].end_point;
			        room1[i].bili=accMul(room1[i].bili,100)
				    $list.append( Result.fillData(tem ,room1[i], "field" ) );
			                	
			     }
        }, function ( desc ) {
            alert( "fail: " + desc );
        }, function ( err ) {
            alert( "error" + err );
        } );
        
        
	 });
	 
	function accMul(arg1, arg2) {
		    var m = 0, s1 = arg1.toString(), s2 = arg2.toString();
		    try {
		        m += s1.split(".")[1].length;
		    }
		    catch (e) {
		    }
		    try {
		        m += s2.split(".")[1].length;
		    }
		    catch (e) {
		    }
		    var num=Number(s1.replace(".", "")) * Number(s2.replace(".", "")) / Math.pow(10, m);
		    return num.toFixed(0);
		}
}( window ) );
