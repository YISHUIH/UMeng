package com.jkwy.umeng.push;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.jkwy.umeng.R;
import com.umeng.message.IUmengRegisterCallback;
import com.umeng.message.PushAgent;
import com.umeng.message.UTrack;
import com.umeng.message.UmengMessageHandler;
import com.umeng.message.entity.UMessage;
import com.umeng.socialize.UMShareAPI;

import static android.os.Looper.getMainLooper;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司
 * Author: 陈刘磊 1070379530@qq.com
 * Date: 2019/2/25 16:58
 * Description: 推送的相关方法
 */
public class UtilPush {
    private static final String TAG="UtilPush";
    public static void init(Context context){
        //获取消息推送代理示例
        PushAgent mPushAgent = PushAgent.getInstance(context);
        //注册推送服务，每次调用register方法都会回调该接口
        mPushAgent.register(new IUmengRegisterCallback() {
            @Override
            public void onSuccess(String deviceToken) {
                //注册成功会返回deviceToken deviceToken是推送消息的唯一标志
                Log.i(TAG, "注册成功：deviceToken：-------->  " + deviceToken);
            }

            @Override
            public void onFailure(String s, String s1) {
                Log.e(TAG, "注册失败：-------->  " + "s:" + s + ",s1:" + s1);
            }
        });

        umMessageHandler(context);
    }


    /**
     * 在所有的Activity 的onCreate 方法或在应用的BaseActivity的onCreate方法中添加。
     *
     * 注：该方法是【友盟+】Push后台进行日活统计及多维度推送的必调用方法，请务必调用！
     * @param context
     */
    public static void onAppStart(Context context){
        PushAgent.getInstance(context).onAppStart();

    }


    /**
     *UmengMessageHandler设置
     */

    public static void umMessageHandler(Context context){
        //UmengMessageHandler类负责处理消息，包括通知和自定义消息
        UmengMessageHandler umengMessageHandler=new UmengMessageHandler(){

            /**
             * getNotification负责定义通知栏样式
             * 若SDK默认的消息展示样式不符合开发者的需求，可通过覆盖该方法来自定义通知栏展示样式
             * @param context
             * @param uMessage
             * @return
             */
            @Override
            public Notification getNotification(Context context, UMessage uMessage) {
                switch (uMessage.builder_id) {
                    case 1:
                        Notification.Builder builder = new Notification.Builder(context);
                        RemoteViews myNotificationView = new RemoteViews(context.getPackageName(),
                                R.layout.notification_view);
                        uMessage.title="修改修改";
                        myNotificationView.setTextViewText(R.id.notification_title, uMessage.title);
                        myNotificationView.setTextViewText(R.id.notification_text, uMessage.text);
                        myNotificationView.setImageViewBitmap(R.id.notification_large_icon,
                                getLargeIcon(context, uMessage));
                        myNotificationView.setImageViewResource(R.id.notification_small_icon,
                                getSmallIconId(context, uMessage));
                        builder.setContent(myNotificationView)
                                .setSmallIcon(getSmallIconId(context, uMessage))
                                .setTicker(uMessage.ticker)
                                .setAutoCancel(true);
                        return builder.getNotification();
                    default:
                        //默认为0，若填写的builder_id并不存在，也使用默认。
                        return super.getNotification(context, uMessage);
                }
            }


            /**
             * 自定义消息，是指发送后不会在系统通知栏展现
             * @param context
             * @param msg
             */
            @Override
            public void dealWithCustomMessage(final Context context, final UMessage msg) {
                new Handler(getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        // 对于自定义消息，PushSDK默认只统计送达。若开发者需要统计点击和忽略，则需手动调用统计方法。
                        boolean isClickOrDismissed = true;
                        if(isClickOrDismissed) {
                            //自定义消息的点击统计
                            UTrack.getInstance(context).trackMsgClick(msg);
                        } else {
                            //自定义消息的忽略统计
                            UTrack.getInstance(context).trackMsgDismissed(msg);
                        }
                        Toast.makeText(context, msg.custom, Toast.LENGTH_LONG).show();
                    }
                });
            }
        };

        PushAgent.getInstance(context).setMessageHandler(umengMessageHandler);
    }



}
