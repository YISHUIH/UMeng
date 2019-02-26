package com.jkwy.umeng.analytics;

import com.umeng.analytics.MobclickAgent;
import com.umeng.commonsdk.UMConfigure;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司
 * Author: 陈刘磊 1070379530@qq.com
 * Date: 2019/2/25 16:45
 * Description: 统计分析类
 */
public class UtilAnalytics {


    /**
     * 统计相关设置
     */
    public static void initAnalytics() {
        // 选用AUTO页面采集模式
        MobclickAgent.setPageCollectionMode(MobclickAgent.PageMode.AUTO);

        //// isEnable: false-关闭错误统计功能；true-打开错误统计功能（默认打开）
        MobclickAgent.setCatchUncaughtExceptions(true);

        // 支持在子进程中统计自定义事件
        UMConfigure.setProcessEvent(true);

        // 打开统计SDK调试模式
        UMConfigure.setLogEnabled(true);
    }


}
