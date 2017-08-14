<!DOCTYPE html>
<html>
	<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1" />
    
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 上述3个meta标签*必须*放在最前面，任何其他内容都*必须*跟随其后！ -->
    <title>主页</title>

    <!-- Bootstrap -->
    <link href="<@full_path path='js/lib/bootstrap-3.3.5/dist/css/bootstrap.min.css'/>" rel="stylesheet">
    <link href="<@full_path path='css/main.css'/>" rel="stylesheet">
    <link href="<@full_path path='js/lib/cover/jquery.cover.css'/>" rel="stylesheet">
	<style>
		.rtmp {
		  border: 1px solid #ccc;
		  margin:20px 0;
		}
		
		.rtmp>div{
	      border: 2px solid #d9e2e6;
		}
		
		.rtmp .top {
			line-height:30px;
			padding-left:10px;
			padding-right:10px;
		}
		.rtmp .content {
			height:480px;
			width:100%;
			background-color:#000;
		}
		.rtmp .bottom {
			line-height:30px;
			width:100%;
			margin-bottom:10px;
			padding-left:10px;
			padding-right:10px;
		}
		.rtmp .textLeft {
			text-align:left;
		}
		.rtmp .textRight {
			text-align:right;
		}
		.rtmp .col-md-3,.rtmp .col-sm-3,.rtmp .col-xs-3,.rtmp .col-lg-3 {
			font-size:12px;
			padding:0;
			min-width:350px;
		}
		.search {
			padding: 1em;
		}
		.search>div {
			border: 0;
		}
		
		.rtmp .roomNo{
		  color: #fe990b;
		}
		
		.rtmp .reportCount {
			color: #768fe2;
		}
	</style>
	<#include "/views/backstage/common/common_script.ftl"/>
    
    
    
  </head>
  <body>
  	<input type="hidden" id="queryUrl"  value="<@full_path path='backstage/liveRoom/?act=QUERY' />"  />
  	<input type="hidden" id="deleteUrl"  value="<@full_path path='backstage/liveRoom/?act=DELETE' />"  />
  	<input type="hidden" id="saveUrl"  value="<@full_path path='backstage/liveRoom/?act=SAVE' />"  />
  	<input type="hidden" id="managerType"  value="${Session.manager.managerType!}"  />
  	<input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
       <!--提示框 -->
       <#include "/views/backstage/common/common_alert.ftl"/>
       
       <div id="mask" class="mask"></div>
       <div class="container" style="width:100%;">
       		
       	<!--二次确认框  提示框 -->
       	<#include "/views/backstage/common/common_confirm.ftl"/>
       	
       	<!--新增或修改-->	
		<#include "/views/backstage/liveroom/form.ftl"/>			
								
		 <div class="row">
				<!--栏目路径-->
						<div class="header">
	            <h1 class="page-title">监控</h1>
	          </div>
	          <ul class="breadcrumb">
	            <li><a href="javascript:void(0);">视频管理</a> <span class="divider">/</span></li>
	            <li class="active">监控</li>
            </ul>
		  </div>		  
			
		  <div class="row search rtmp" >
		    <div class="col-sm-4 col-md-6" >
		    	<a href="javascript:void(0);" sort="asc" data-bind="click: function($root, event){serachSort('reportCount',event,$root)}">举报次数</a><span class="glyphicon glyphicon-arrow-down"></span> &nbsp;&nbsp;&nbsp;
		    	<a href="javascript:void(0);" sort="asc" data-bind="click: function($root, event){serachSort('watchCount',event,$root)}" >观看人数</a><span class="glyphicon glyphicon-arrow-down"></span> &nbsp;&nbsp;&nbsp;
		    	<a href="javascript:void(0);" data-bind="click: $root.searchByCondition.bind($root)">刷新</a>
		    </div>
		    <div class="col-sm-8 col-md-6" >	
		  		<!--查询-->
		  		<div class="pull-right" >
		  			ID:			  	 
			  		<input type="text" class="form-control"  placeholder="搜索ID" data-bind="textinput:model.search.id">
			  		&nbsp;&nbsp;&nbsp;&nbsp;
				  	 <input type="text" class="form-control"  placeholder="搜索主播名称" data-bind="textinput:model.search.owerName">
				  	 <button type="button" class="btn btn-default btn-sm" data-bind="click: $root.searchByCondition.bind($root)">
		               <span class="glyphicon glyphicon-search"></span> Search
		             </button>
				</div>
			</div>
		  </div>

		  
		  
			<!--列表数据s-->
			<div class="row rtmp" style="padding:10px" id="rtmpContainer"  data-bind="visible: model.dataList().length">
				<div class="col-md-3 col-sm-3 col-xs-3 col-lg-3" style="display:none;">
					<div class="top">
					  <div class="row">
						<div class="col-md-6 col-sm-6 col-xs-6 col-lg-6 textLeft" >房间号:<span style="color:#fe990b;">1234</span></div>
						<div class="col-md-6 col-sm-6 col-xs-6 col-lg-6 textRight">举报次数:<span style="color:#768fe2;">0</span></div>
					  </div>
					</div>
					<div class="content" id="video1">这是视频</div>
					<div class="bottom">
					  <div class="row">
						<div class="col-md-6 col-sm-6 col-xs-6 col-lg-6 textLeft" >主播:<span>jack</span></div>
						<div class="col-md-6 col-sm-6 col-xs-6 col-lg-6 textRight" >观看人数:<span>0</span></div>
					  </div>
					  <div class="row" >
						<div class="col-md-6 col-sm-6 col-xs-6 col-lg-6 textLeft" >开播时间:<span>0:0:26</span></div>
						<div class="col-md-6 col-sm-6 col-xs-6 col-lg-6 textRight">
							<button type="button" class="btn btn-default btn-sm "  >
		               			关闭房间
		             		</button>
						</div>
					  </div>
					</div>
			    </div>
			</div>
			
			
			<div class="row rtmp" style="text-align:center;width:40;line-height:40px;" data-bind="visible: !model.dataList().length">
				暂无数据
			</div>
			<!--列表数据e-->
			<!--分页-->
		    <#include "/views/backstage/common/page.ftl"/>
</div>


<script  src="<@full_path path='js/lib/ckplayer6.8/ckplayer/ckplayer.js'/>"></script>
<script src="<@full_path path='js/backstage/liveroom/service.js'/>" ></script>	
<script  src="<@full_path path='js/backstage/liveroom/list.js'/>" ></script>
<script type="text/javascript">
/**
    var flashvars={
        f:'rtmp://live.hkstv.hk.lxdns.com/live/hks',
        c:0
    };
    var video=['http://localhost/live.m3u8'];
    CKobject.embed($("#fullPath").val()+'js/lib/ckplayer6.8/ckplayer/ckplayer.swf','video1','ckplayer_a1','100%','100%',false,flashvars,video);
    
    
     var flashvars2={
        f:'rtmp://live.hkstv.hk.lxdns.com/live/hks',
        c:0
        
    };

    CKobject.embed($("#fullPath").val()+'js/lib/ckplayer6.8/ckplayer/ckplayer.swf','video2','ckplayer_a1','100%','100%',false,flashvars2,video);
	**/
</script>	
  </body>
</html>
