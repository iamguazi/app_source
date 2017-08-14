
$(function () {

	$.extend(true, window.service, {
		updateStatus: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/roomInfo/updateStatus",
		        type: "POST",
		        dataType: "json",
		        data: data
			});
		},
		getPayListConfig: function (data){
			return Ajax.ajax({
				url: fullPath+"backstage/payListConfig/getPayListConfig",
				type: "POST",
				dataType: "json",
				data: data,
				autoCloseCover:false
			});
		},
		getAreaType:function(data){
			return Ajax.ajax({
				url: fullPath+"backstage/gameArea/getAreaTypes",
		        type: "POST",
		        dataType: "json",
		        data: data,
		        async:false
			});
		}
	});

});
 