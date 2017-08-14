
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
		getAreaType:function(data){
			return Ajax.ajax({
				url: fullPath+"backstage/gameArea/getAreaTypes",
		        type: "POST",
		        dataType: "json",
		        data: data,
		        autoCloseCover:false
			});
		},
		getAllRoomInfo:function(data){
			return Ajax.ajax({
				url: fullPath+"backstage/roomInfo/getAllRoomInfo",
				type: "POST",
				dataType: "json",
				data: data,
				autoCloseCover:false
			});
		}
	});

});
 