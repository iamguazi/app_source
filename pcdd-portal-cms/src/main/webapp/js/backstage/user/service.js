
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
		resetPassword: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/userInfo/resetPassword",
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
		modifyParentId: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/userInfo/modifyParentId",
				type: "POST",
				dataType: "json",
				data: data
			});
		},
		notice: function (data){
			return Ajax.ajax({
				url: fullPath+'backstage/noticeInfo/?act=SAVE',
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
				data: data,
				autoCloseCover:false
			});
		}
	});

});
 