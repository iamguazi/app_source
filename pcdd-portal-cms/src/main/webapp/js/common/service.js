$(function () {

	var service = {
		list: function (data){
			return Ajax.ajax({
				url: $("#queryUrl").val(),
		        type: "POST",
		        dataType: "json",
		        coverTitle:"正在加载中,请稍后......",
		        autoCloseCover:false,
		        data: util.removeEmpty( data ),
		        showError:false
			});
		},
		saveOrUpdate: function (data) {
			return Ajax.ajax({
				url: $("#saveUrl").val(),
		        type: "POST",
		        dataType: "json",
		        coverTitle:"正在提交数据中......",
		        data: data,
		        showError:false
			});
		},
		del: function (ids) {
			var idsStr = "";
			for(var i=0,len=ids.length;i<len;i++){
				idsStr+="&ids="+ids[i];
			}
			idsStr.replace("&","");
			return Ajax.ajax({
				url: $("#deleteUrl").val(),
		        type: "POST",
		        dataType: "json",
		        coverTitle:"删除数据中......",
		        data:idsStr
			});
		}
	};
	
	window.service = service;
}());
 