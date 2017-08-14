
$(function () {

	$.extend(true, window.service, {
		openGame: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/gameOpenLog/openGame",
		        type: "POST",
		        dataType: "json",
		        data: data
			});
		}
	});

});
 