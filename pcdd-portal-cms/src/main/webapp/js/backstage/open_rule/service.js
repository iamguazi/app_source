
$(function () {

	$.extend(true, window.service, {
		getAreaType:function(data){
			return Ajax.ajax({
				url: fullPath+"backstage/gameArea/getAreaTypes",
		        type: "POST",
		        dataType: "json",
		        data: data,
		        async:false
			});
		}
	});

});
 