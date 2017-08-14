
$(function () {

	$.extend(true, window.service, {
		updateStatus: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/banner/updateStatus",
		        type: "POST",
		        dataType: "json",
		        data: data
			});
		}
	});

});
 