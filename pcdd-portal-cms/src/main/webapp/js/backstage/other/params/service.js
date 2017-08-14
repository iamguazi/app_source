
$(function () {

	$.extend(true, window.service, {
		updateStatus: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/giftInfo/updateStatus",
		        type: "POST",
		        dataType: "json",
		        data: data
			});
		}
	});

});
 