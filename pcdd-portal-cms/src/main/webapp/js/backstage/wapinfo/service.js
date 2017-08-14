
$(function () {

	$.extend(true, window.service, {
		getWapInfoByKey: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/wapInfo/getWapInfoByKey",
		        type: "POST",
		        dataType: "json",
		        data: util.removeEmpty( data )
			});
		}
	});

});
 