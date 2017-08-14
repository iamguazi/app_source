
$(function () {

	$.extend(true, window.service, {
		updateStatus: function (data,status){
			return Ajax.ajax({
				url: fullPath+"backstage/userFenxiaoLog/updateStatus?status="+status,
				contentType: "application/json; charset=utf-8",
		        type: "POST",
		        dataType: "json",
		        data: data
			});
		}
	});

});
 