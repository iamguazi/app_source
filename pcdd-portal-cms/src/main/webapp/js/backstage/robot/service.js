
$(function () {

	$.extend(true, window.service, {
		updateStatus: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/userInfo/updateStatus",
		        type: "POST",
		        dataType: "json",
		        data: data
			});
		},
		rechargePoint: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/userInfo/rechargePoint",
				type: "POST",
				dataType: "json",
				data: data
			});
		},
		getUserLevels: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/userLevel/getUserLevels",
				type: "POST",
				dataType: "json",
				data: data
			});
		},
		resetPassword: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/userInfo/resetPassword",
				type: "POST",
				dataType: "json",
				data: data
			});
		}
	});

});
 