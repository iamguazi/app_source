
$(function () {

	$.extend(true, window.service, {
		getPointDescs: function (){
			return Ajax.ajax({
				url: fullPath+"backstage/pointChangeLog/getPointDescs",
		        type: "POST",
		        dataType: "json"
			});
		}
	});

});
 