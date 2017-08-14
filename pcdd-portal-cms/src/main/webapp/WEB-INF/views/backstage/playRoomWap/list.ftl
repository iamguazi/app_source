<!DOCTYPE html>
<html style="width:100%;height:100%;">
	<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,maximum-scale=1,user-scalable=no">
    <meta content="telephone=no" name="format-detection">
    <title>直播</title>
    <link href="<@full_path path='js/lib/video_v5.8.8/video-js.min.css' />" rel="stylesheet">
    
    <script src="<@full_path path='js/lib/rtmp_sample_player_flowplayer/flowplayer-3.2.8.min.js' />" ></script>
    
    
  </head>
  <body style="width:100%;height:100%;margin:0;padding:0;" >
  	 <input type="hidden" id="fullPath"  value="<@full_path path='' />"  />
	 <!--<div  id="video_id_1" style="width:100%;height:100%;"></div>-->
	 <!--<a  
		 href="#"
		 style="display:block;width:100%;height:100%;"  
		 id="player"> 
	</a>-->
	
	<!--<video id="my-video" class="video-js vjs-default-skin" controls preload="auto" style="width:100%;height:100%;"
	 poster="https://img.alicdn.com/imgextra/i2/754328530/TB2FpxhkXXXXXa5XXXXXXXXXXXX_!!754328530.jpg" data-setup='{"controls": false, "autoplay": true}'>
	    <source src="rtmp://live.hkstv.hk.lxdns.com/live/hks" type="rtmp/flv">
	 </video>-->
	 
	<!--<video id="my-video" class="video-js vjs-default-skin" controls preload="auto" style="width:100%;height:100%;"
	 poster="https://img.alicdn.com/imgextra/i2/754328530/TB2FpxhkXXXXXa5XXXXXXXXXXXX_!!754328530.jpg" data-setup='{"controls": false, "autoplay": true}'>
	    <source src="http://pullhls99.a8.com/live/1474685601473216/playlist.m3u8" type="application/x-mpegURL">
	 </video>-->
 
        <video  src="${rtmp!}"  autoplay="autoplay">暂不支持视频播放</video>
 		<!--<div id="myElement">Loading the player...</div>-->
		<!--<div class="player">
            <div id="container" x5-video-player-type="h5" x5-video-player-fullscreen=true>  </div>
        </div>-->
  </body>
 <script src="<@full_path path='js/lib/video_v5.8.8/video.min.js' />"></script>
<script type="text/javascript" src="<@full_path path='js/lib/jquery-1.11.1.min.js'/>" ></script>
<script  src="<@full_path path='js/lib/ckplayer6.8_wap/ckplayer/ckplayer.js'/>"></script>
<script type="text/javascript">
/**
			var flashvars={
			        f:'${rtmp!}',
			        c:0,
			        p:1
			    };
			    var id = 'video_id_1';
			    CKobject.embed($("#fullPath").val()+'js/lib/ckplayer6.8/ckplayer/ckplayer.swf',id,'ckplayer_'+id,'100%','100%',false,flashvars,{});
**/

/**
var swf =$("#fullPath").val()+"js/lib/rtmp_sample_player_flowplayer/flowplayer-3.2.8.swf";
var swfRrtmp =$("#fullPath").val()+"js/lib/rtmp_sample_player_flowplayer/flowplayer.rtmp-3.2.8.swf";
flowplayer("player", swf,{ 
		clip: { 
		  url: 'hks',
		  provider: 'rtmp',
		  live: true, 
		},  
		plugins: {  
		   rtmp: {  
			 url: swfRrtmp,  
			 netConnectionUrl: 'rtmp://live.hkstv.hk.lxdns.com/live'
		   } 
	   } 
	});
	**/
	
	
	
</script>
<script src="<@full_path path='js/lib/jwplay6.6/jwplayer.js' />" ></script>	
<script >jwplayer.key="lsUqQ+PB1edH+bYoMb85Yr5CuPlXyhK/ngcyNQ==";</script>

<script >
/**
	jwplayer("container").setup({
        //flashplayer: "http://localhost:8082/"+$("#fullPath").val()+"js/lib/jwplay6.6/jwplayer.flash.swf",
                width: "50%",
                height: "50%",
                //stretching: "fill",
                androidhls:"true",
                aspectratio: "16:9",
                key:"lsUqQ+PB1edH+bYoMb85Yr5CuPlXyhK/ngcyNQ==",
                primary: "html5",
                controls:true,
                file:"http://pullhls99.a8.com/live/1474685601473216/playlist.m3u8",
    });
    **/
</script>
<script type="text/javascript">
    /**jwplayer("myElement").setup({
        flashplayer: "http://localhost:8082/"+$("#fullPath").val()+"js/lib/jwplay6.6/jwplayer.flash.swf",
                width: "50%",
                height: "50%",
                primary: "flash",
                controls:true,
                file:"${rtmp}"
    });**/
</script>
</html>
