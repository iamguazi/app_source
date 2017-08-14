;( function () {
    "use strict";

    $(function () {
        var param_map = Result.getLocationParamObject();
        Result.doResult({
            url: Interface.dong.noticeDetails,
            data: param_map,
            type: "POST"
        }, function (data) {
            $("#title").html(data.title);
            $("#createTime").html(Result.formatDate(data.create_time, "yyyy-MM-dd hh:mm"));
            $("#content").html(data.content);
        }, function (desc) {
            alert("fail: " + desc);
        }, function (err) {
            $("#title").html(Interface.dong.noticeDetails);
            alert("error" + err);
        });


    });


}(window) );
