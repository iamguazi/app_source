
$(function () {

	$.extend(true, window.service, {
		getPermissions: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/manager/getPermissions",
		        type: "POST",
		        dataType: "json",
		        data: util.removeEmpty( data )
			});
		}
	});

});
 