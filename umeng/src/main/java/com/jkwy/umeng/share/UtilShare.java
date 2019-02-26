package com.jkwy.umeng.share;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司
 * Author: 陈刘磊 1070379530@qq.com
 * Date: 2019/2/26 13:56
 * Description: 分享的相关方法
 */
public class UtilShare {

    public static void initShare() {
        PlatformConfig.setWeixin("wxdc1e388c3822c80b", "3baf1193c85774b3fd9d18447d76cab0");
        //豆瓣RENREN平台目前只能在服务器端配置
        PlatformConfig.setSinaWeibo("3921700954", "04b48b094faeb16683c32669824ebdad", "http://sns.whalecloud.com");



    }

    public static void showShare(final Activity context) {
        UMShareListener shareListener = new UMShareListener() {
            /**
             * @descrption 分享开始的回调
             * @param platform 平台类型
             */
            @Override
            public void onStart(SHARE_MEDIA platform) {

            }

            /**
             * @descrption 分享成功的回调
             * @param platform 平台类型
             */
            @Override
            public void onResult(SHARE_MEDIA platform) {
                Toast.makeText(context, "成功了", Toast.LENGTH_LONG).show();
            }

            /**
             * @descrption 分享失败的回调
             * @param platform 平台类型
             * @param t 错误原因
             */
            @Override
            public void onError(SHARE_MEDIA platform, Throwable t) {
                Toast.makeText(context, "失败" + t.getMessage(), Toast.LENGTH_LONG).show();
            }

            /**
             * @descrption 分享取消的回调
             * @param platform 平台类型
             */
            @Override
            public void onCancel(SHARE_MEDIA platform) {
                Toast.makeText(context, "取消了", Toast.LENGTH_LONG).show();

            }
        };
        new ShareAction(context).withText("hello")
                .setDisplayList(SHARE_MEDIA.SINA, SHARE_MEDIA.WEIXIN_CIRCLE, SHARE_MEDIA.WEIXIN)
                .setCallback(shareListener).open();


    }

    /**
     *
     * @param context
     * @param requestCode
     * @param resultCode
     * @param data
     */
    public static void get(Context context, int requestCode, int resultCode, Intent data){
        UMShareAPI.get(context).onActivityResult(requestCode,resultCode,data);

    }
}
