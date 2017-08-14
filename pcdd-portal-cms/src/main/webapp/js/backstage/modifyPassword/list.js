;(function () {
	
	var service = {
		resetPassword: function (data) {
			return $.ajax({
				url: fullPath + "backstage/manager/resetPassword",
				type: "post",
				dataType: "json",
				data: data
			})
		}
	};
	
	$.extend(true, viewModel, {
		model: {
			data: {
				oldPassword: "",
				newPassword: "",
				reNewPassword: ""
			}
		},
		initViewModel: function () {
			this.model = ko.mapping.fromJS(this.model);
			this.initValidate();
			ko.applyBindings(this);
			$("#form").find("[name='reNewPassword']").rules("add", {
				equalTo: "#newPassword",
				messages: {
					equalTo: "必须与新密码相同"
				}
			});
		},
		validateOption : function(){
			var that = this;
			return  {
			}
		},
		resetPassword: function () {
			var that = this, data = ko.mapping.toJS(this.model.data);
			if(!$("#form").valid()){
				return;
			}
			service.resetPassword(data).then(function (resJson) {
				if (resJson.status === 1) {
//					util.showSuccess("密码修改成功!");
					Lobibox.notify("success",{msg:"密码修改成功"});
//					that.showInfo("密码修改成功","alert alert-success");
					fillKOEntity(that.model.data, {});	
				} else {
					Lobibox.notify("error",{msg:resJson.desc});
//					that.showInfo(resJson.desc);
//					util.showWarning(resJson .desc);
				}
			});
		}
	});
	
	$(function(){
		viewModel.initViewModel();
	});
	
}());