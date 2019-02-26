package com.example.umdemo;

import android.app.Application;

import com.jkwy.umeng.UtilUMeng;

/**
 * Copyright , 2015-2019, 健康无忧网络科技有限公司
 * Author: 陈刘磊 1070379530@qq.com
 * Date: 2019/2/25 11:51
 * Description: MyApplication 进行初始化工作
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化友盟
        UtilUMeng.umConfigure(this,"ae3a0d986d9d2cb80c05eca40c9b2455");
    }
}
