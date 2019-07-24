package com.hwhl.shengxian.util;

import cn.jpush.api.JPushClient;
import cn.jpush.api.common.ClientConfig;
import cn.jpush.api.common.resp.APIConnectionException;
import cn.jpush.api.common.resp.APIRequestException;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.PushPayload;
import org.apache.log4j.Logger;

import java.util.Collection;
import java.util.List;

/**
 * 机关推送工具类
 * @author 陆晶晶
 */
public class JiGuangPushUtil {
    private static String APP_KEY="7d4f2755c22b2c7dfe3c0941";
    private static String MASTER_SECRET="aa437af9ff9a6ef57d7ebeb9";

    /**
     * 保存离线的时长。秒为单位。最多支持10天（864000秒）。 0 表示该消息不保存离线。即：用户在线马上发出，当前不在线用户将不会收到此消息。
     * 此参数不设置则表示默认，默认为保存1天的离线消息（86400秒)。
     */
    private final static long TIMETOLIVE = 60 * 60 * 24;

    private static JPushClient jPushClient = null;

    private static final Logger logger = Logger.getLogger(JiGuangPushUtil.class);


    /**
     * 极光 通知 全推
     * @param context
     * @return
     */
    public static boolean sendPushAll(String context) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(TIMETOLIVE);
        jPushClient = new JPushClient(MASTER_SECRET, APP_KEY,null, clientConfig);
        boolean flag = false;
        try {
            PushPayload payload = JPushUtil.buildPushObject_all_all_alert(context);
            System.out.println("服务器返回数据：" + payload.toString());

            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 通知 单推
     * @param alias
     * @param content
     * @return
     */
    public static boolean sendPushAll_alias(Collection<String> alias, String type, String content) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(TIMETOLIVE);
        jPushClient = new JPushClient(MASTER_SECRET, APP_KEY,null, clientConfig);
        boolean flag = false;
        try {
            PushPayload payload = JPushUtil.buildPushObject_all_Notification_alias_alert(alias, type, content);
            System.out.println("服务器返回数据：" + payload.toString());

            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static boolean senPushByRegesterId(List<String> regeSterIds, String msgContent) {
        jPushClient = new JPushClient(MASTER_SECRET, APP_KEY);
        boolean flag = false;
//       String content = "多个ID测试";
        try {
            PushPayload payload = JPushUtil.buildPushObject_all_all_regesterIds(regeSterIds, msgContent);
            System.out.println("服务器返回数据：" + payload.toString());
            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get result ----" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 极光 自定义消息 全推
     * @param context
     * @return
     */
    public static boolean sendPushAll_message(String context) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(TIMETOLIVE);
        jPushClient = new JPushClient(MASTER_SECRET, APP_KEY,null, clientConfig);
        boolean flag = false;
        try {
            PushPayload payload = JPushUtil.buildPushObject_message_all(context);
            System.out.println("服务器返回数据：" + payload.toString());

            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

    /**
     * 自定义消息 单推
     * @param alias
     * @param content
     * @return
     */
    public static boolean sendPushAll_message_alias(Collection<String> alias, String content) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        clientConfig.setTimeToLive(TIMETOLIVE);
        jPushClient = new JPushClient(MASTER_SECRET, APP_KEY,null, clientConfig);
        boolean flag = false;
        try {
            PushPayload payload = JPushUtil.buildPushObject_all_message_alias_alert(alias, content);
            System.out.println("服务器返回数据：" + payload.toString());

            PushResult result = jPushClient.sendPush(payload);
            if (null != result) {
                logger.info("Get resul ---" + result);
                flag = true;
            }
        } catch (APIConnectionException e) {
            logger.error("Connection error. Should retry later. ", e);
            flag = false;
        } catch (APIRequestException e) {
            logger.error("Error response from JPush server. Should review and fix it. ", e);
            logger.info("HTTP Status: " + e.getStatus());
            logger.info("Error Code: " + e.getErrorCode());
            logger.info("Error Message: " + e.getErrorMessage());
            logger.info("Msg ID: " + e.getMsgId());
            flag = false;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return flag;
    }

}
