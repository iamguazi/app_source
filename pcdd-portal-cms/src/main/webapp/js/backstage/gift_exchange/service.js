
$(function () {

	$.extend(true, window.service, {
		updateStatus: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/exchangeGiftLog/updateStatus",
		        type: "POST",
		        dataType: "json",
		        data: data
			});
		}
	});

});
 