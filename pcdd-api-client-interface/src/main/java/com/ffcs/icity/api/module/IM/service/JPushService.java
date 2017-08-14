package com.ffcs.icity.api.module.IM.service;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.device.DeviceClient;
import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;

@Service
public class JPushService {
	@Value("${pushAppKey}")
    private  String appKey;
    @Value("${pushApiPassword}")
    private  String apiPassword;
    
	public  void setTags(String registrationId,String alias,Set<String> tagsToAdd, Set<String> tagsToRemove){
		DeviceClient client = new DeviceClient(apiPassword,appKey, 3);
		
		try {
		  client.updateDeviceTagAlias(registrationId, alias,tagsToAdd 	,tagsToRemove );
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sentContentByTagAnd(Set<String> tags, String pushContent,boolean apnsFalg){
		JPushClient jPushClient=new JPushClient(apiPassword,appKey, 3);
		try {
			jPushClient.sendPush(buildPushObject_all_tagAnd_alertWithExtrasAndMessage(tags, pushContent,apnsFalg));
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	public void sentContentByAlias(String alias, String pushContent,boolean apnsFalg){
		JPushClient jPushClient=new JPushClient(apiPassword,appKey, 3);
		try {
			jPushClient.sendPush(buildPushObject_all_alias_alertWithExtrasAndMessage(alias, pushContent,apnsFalg));
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void sentContentByAll( String pushContent,boolean apnsFalg){
		JPushClient jPushClient=new JPushClient(apiPassword,appKey, 3);
		try {
			jPushClient.sendPush(buildPushObject_all_alertWithExtrasAndMessage( pushContent,apnsFalg));
		} catch (APIConnectionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (APIRequestException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
/**
 * 构建推送对象：平台是 iOS，推送目标是 "tag1", "tag_all" 的交集，推送内容同时包括通知与消息 - 通知信息是 ALERT，角标数字为 5，通知声音为 "happy"，并且附加字段 from = "JPush"；消息内容是 MSG_CONTENT。通知是 APNs 推送通道的，
 * 消息是 JPush 应用内消息通道的。APNs 的推送环境是“生产”（如果不显式设置的话，Library 会默认指定为开发）
 * @param pushContent
 * @return
 */
	
	public static PushPayload buildPushObject_all_tagAnd_alertWithExtrasAndMessage(Set<String> tags, String pushContent,boolean apnsFalg) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.tag_and(tags))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                        		.setAlert(pushContent)
                                .setBadge(1)
                                .build())
                        .build())
                 .setMessage(Message.content(pushContent))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(apnsFalg)
                         .build())
                 .build();
    }
	
	
	public static PushPayload buildPushObject_all_alias_alertWithExtrasAndMessage(String alias, String pushContent,boolean apnsFalg) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                        		.setAlert(pushContent)
                                .setBadge(1)
                                .build())
                        .build())
                 .setMessage(Message.content(pushContent))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(apnsFalg)
                         .build())
                 .build();
    }
	 
	public static PushPayload buildPushObject_all_alertWithExtrasAndMessage(  String pushContent,boolean apnsFalg) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.all())
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(IosNotification.newBuilder()
                        		.setAlert(pushContent)
                                .setBadge(1)
                                .build())
                        .build())
                 .setMessage(Message.content(pushContent))
                 .setOptions(Options.newBuilder()
                         .setApnsProduction(apnsFalg)
                         .build())
                 .build();
    }
	
	/**
	 * 构建推送对象：所有平台，推送目标是别名为 "alias1"，通知内容为 pushContent。
	 * @param pushContent
	 * @return
	 */
	public static PushPayload buildPushObject_all_alias_alert(String pushContent) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.all())
                .setAudience(Audience.alias("alias1"))
                .setNotification(Notification.alert(pushContent))
                .build();
    }
	/**
	 * 构建推送对象：平台是 Android，目标是 tag 为 "tag1" 的设备，内容是 Android 通知 ALERT，并且标题为 TITLE
	 * @return
	 */
	public static PushPayload buildPushObject_android_tag_alertWithTitle(String ALERT,String TITLE) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android())
                .setAudience(Audience.tag("tag1"))
                .setNotification(Notification.android(ALERT, TITLE, null))
                .build();
    }
	
	
	   public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String MSG_CONTENT) {
	        return PushPayload.newBuilder()
	                .setPlatform(Platform.android_ios())
	                .setAudience(Audience.newBuilder()
	                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
	                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2"))
	                        .build())
	                .setMessage(Message.newBuilder()
	                        .setMsgContent(MSG_CONTENT)
	                        .addExtra("from", "JPush")
	                        .build())
	                .build();
	    }
	public static void main(String[] args) {
		Set<String >tag=new HashSet<String>();
		tag.add("city7");
		
		Set<String >tagRemove=new HashSet<String>();
		tagRemove.add("city8");
//		setTags("03f8b76035b77255259d7591", "2b621af6cd9ecc076e85ec4c",tag,tagRemove);
	}
}
