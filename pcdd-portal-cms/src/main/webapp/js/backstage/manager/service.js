
$(function () {

	$.extend(true, window.service, {
		updateStatus: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/manager/updateStatus",
		        type: "POST",
		        dataType: "json",
		        data: data
			});
		},
		getAllPermissions: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/manager/getAllPermissions",
				type: "POST",
				dataType: "json",
				data: data
			});
		},
		getPermissions: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/manager/getPermissions",
				type: "POST",
				dataType: "json",
				data: data
			});
		}
	});

});
 