package com.hwhl.shengxian.util;

import cn.jpush.api.push.model.Message;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.audience.AudienceTarget;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import java.util.Collection;
import java.util.List;

/**
* @Author: ZZY
* 极光推送工具类
*/
public class JPushUtil {

    /*
       所有平台，所有设备，内容为 content 的通知
     */
    public static PushPayload buildPushObject_all_all_alert(String content) {

        return PushPayload.alertAll(content);
    }

    /*
         根据 设备终端ID 推送消息
         regesterIds   设备终端ID集合
           content      内容
         Audience设置为All（代表所有用户）。
     */

    public static PushPayload buildPushObject_all_all_regesterIds(List<String> regesterIds, String content) {

        return PushPayload.newBuilder().setPlatform(Platform.all()).setOptions(Options.newBuilder().setApnsProduction(Global.jiguan).build())
                .setAudience(Audience.registrationId(regesterIds))
                .setNotification(Notification.alert(content))
                .build();
    }

    /*

        所有平台，推送目标是别名为 "alias"，通知内容为 content
     */

    /**
     * 所有平台，推送目标是别名为 "alias"，通知内容为 content 发送通知
     * @param alias
     * @param content
     *  type  设置type APP对应点击后进入 不同的页面
     * @return
     */
    public static PushPayload buildPushObject_all_Notification_alias_alert(Collection<String> alias, String type, String content) {

        return PushPayload.newBuilder().setPlatform(Platform.all()).setOptions(Options.newBuilder().setApnsProduction(Global.jiguan).build()).setAudience(Audience.alias(alias))
                .setNotification(Notification.newBuilder()
                        .addPlatformNotification(AndroidNotification.newBuilder()
                                .addExtra("type", type)
                                .setAlert(content)
                                .build())
                        .addPlatformNotification(IosNotification.newBuilder()
                                .addExtra("type", type)
                                .setAlert(content)
                                .build())
                        .build()).build();
    }

    /**
     * 推送自定义消息
     * @param alias
     * @param content
     * @return
     */
    public static PushPayload buildPushObject_all_message_alias_alert(Collection<String> alias, String content) {
        return PushPayload.newBuilder().setPlatform(Platform.all()).setOptions(Options.newBuilder().setApnsProduction(Global.jiguan).build()).setAudience(Audience.alias(alias))
                .setMessage(Message.newBuilder()
                        .setMsgContent(content)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }


    public static PushPayload buildPushObject_ios_audienceMore_messageWithExtras(String MSG_CONTENT) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.newBuilder()
                        .addAudienceTarget(AudienceTarget.tag("tag1", "tag2"))
                        .addAudienceTarget(AudienceTarget.alias("alias1", "alias2", ""))
                        .build())
                .setMessage(Message.newBuilder()
                        .setMsgContent(MSG_CONTENT)
                        .addExtra("from", "JPush")
                        .build())
                .build();
    }

    public static PushPayload buildPushObject_message_all(String message) {
        return PushPayload.messageAll(message);
    }

}
