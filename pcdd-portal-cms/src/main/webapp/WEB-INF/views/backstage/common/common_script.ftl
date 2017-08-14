<link href="<@full_path path='js/lib/Lobibox/Lobibox.min.css'/>" rel="stylesheet">
<link href="<@full_path path='js/lib/ladda/css/ladda-themeless.min.css'/>" rel="stylesheet">
<link href="<@full_path path='css/pace.css'/>" rel="stylesheet">
<script type="text/javascript" src="<@full_path path='js/lib/jquery-1.11.1.min.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/pace.min.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/Lobibox/lobibox.min.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/ladda/js/spin.min.js'/>"></script>
<script type="text/javascript" src="<@full_path path='js/lib/ladda/js/ladda.min.js'/>"></script>
<script type="text/javascript" src="<@full_path path='js/lib/ladda/js/ladda.jquery.min.js'/>"></script>
<script type="text/javascript" src="<@full_path path='js/lib/bootstrap-3.3.5/dist/js/bootstrap.min.js'/>"></script>

<script type="text/javascript" src="<@full_path path='js/lib/jquery.validate.min.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/jquery.validate.extend.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/json2.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/support.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/knockout-3.3.0.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/knockout.mapping.js'/>" ></script>

<!--<script type="text/javascript" src="<@full_path path='js/lib/ko.pagination/ko.pagination.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/ko.pagination/moa/ko.pagination.moa.js'/>" ></script>-->

<script type="text/javascript" src="<@full_path path='js/lib/ajaxfileupload.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/ajax/ajax.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/cover/jquery.cover.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/lib/laydate/laydate.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/common/common.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/common/service.js'/>" ></script>
<script type="text/javascript" src="<@full_path path='js/common/list.js'/>" ></script>
<script type="text/javascript">
	var fullPath = "<@full_path path='' />";
	var imgShowRoot = "${imgShowRoot!}";
	var defaultUserIcon = fullPath+"img/default_user_icon.png";
	Lobibox.notify.DEFAULTS = $.extend({}, Lobibox.notify.DEFAULTS, {
			position: "bottom right",
			delay:3000,
			title:"提示"
	});
	   
</script>