package com.jkwy.umeng;

import android.content.Context;

import com.jkwy.umeng.analytics.UtilAnalytics;
import com.jkwy.umeng.push.UtilPush;
import com.jkwy.umeng.share.UtilShare;
import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司
 * Author: 陈刘磊 1070379530@qq.com
 * Date: 2019/2/25 11:36
 * Description: TODO 请输入此类的功能
 */
public class UtilUMeng {

    private static final String TAG = "UtilUMeng";

    /**
     * @param context 上下文，不能为空
     */
    public static void umConfigure(Context context,String pushSecret) {
        /**
         * 注意：如果您已经在AndroidManifest.xml中配置过appkey和channel值，可以调用此版本初始化函数。
         * deviceType    设备类型，UMConfigure.DEVICE_TYPE_PHONE为手机、UMConfigure.DEVICE_TYPE_BOX为盒子，默认为手机
         * pushSecret    Push推送业务的secret ,需要集成Push功能时必须传入Push的secret，否则传空。
         */
//        UMConfigure.init(context, UMConfigure.DEVICE_TYPE_PHONE, "c8b9869fcfb709118871797e38e162c4");

        UMConfigure.init(context,UMConfigure.DEVICE_TYPE_PHONE,pushSecret);

        //secretkey     设置接口，防止AppKey被盗用，secretkey需要网站申请。申请方法见AppKey保护策略(Secret)介绍。
        //context       当前宿主进程的ApplicationContext上下文。
        //secretkey     需要在【友盟+】网站申请。
//        MobclickAgent.setSecret(context, "s10bacedtyz");
        //统计相关设置
        UtilAnalytics.initAnalytics();


        //推送相关
        UtilPush.init(context);

        //分享相关
        UtilShare.initShare();

        /**
         * 设置组件化的Log开关
         * 参数: boolean 默认为false，如需查看LOG设置为true
         */
        UMConfigure.setLogEnabled(true);
    }


    /**
     * 自己捕获了错误，需要手动上传到【友盟+】服务器
     *
     * @param context
     * @param error
     */
    public static void reportError(Context context, String error) {
        MobclickAgent.reportError(context, error);
    }

    public static void reportError(Context context, Throwable e) {
        MobclickAgent.reportError(context, e);
    }


    public static void onResume(Context context) {
        MobclickAgent.onResume(context);
    }

    public static void onPaused(Context context) {
        MobclickAgent.onResume(context);
    }
}
