### 工程的gralde添加了友盟地址

```gralde
// Top-level build file where you can add configuration options common to all sub-projects/modules.

buildscript {
    repositories {
        google()
        jcenter()
        //友盟
        maven { url 'https://dl.bintray.com/umsdk/release' }
        
    }
    dependencies {
        classpath 'com.android.tools.build:gradle:3.3.0'
        
        // NOTE: Do not place your application dependencies here; they belong
        // in the individual module build.gradle files
    }
}

allprojects {
    repositories {
        google()
        jcenter()
         //友盟
        maven { url 'https://dl.bintray.com/umsdk/release' }
        
    }
}

task clean(type: Delete) {
    delete rootProject.buildDir
}

```

### Library部分

 - 需要添加依赖
```gralde
   //PushSDK必须依赖基础组件库，所以需要加入对应依赖
    implementation  'com.umeng.umsdk:common:2.0.0'
    //友盟统计
    implementation  'com.umeng.umsdk:analytics:8.0.0'
    //PushSDK必须依赖utdid库，所以需要加入对应依赖
    implementation 'com.umeng.umsdk:utdid:1.1.5.3'
    //PushSDK
    implementation 'com.umeng.umsdk:push:5.0.2'
    //分享核心库
    implementation  'com.umeng.umsdk:share-core:6.9.4'
    implementation  'com.umeng.umsdk:share-board:6.9.4'
    //分享平台模块
//    implementation  'com.umeng.umsdk:share-wx:6.9.4' 微信平台需要在应用程序中添加
    implementation  'com.umeng.umsdk:share-sina:6.9.4'
```
- 目录结构
![结构图](https://img-blog.csdnimg.cn/20190226171346895.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2FuZHJvaWRfaGw=,size_16,color_FFFFFF,t_70)
UTilAnalytics、utilPush、UtilShare分别提供了统计，推送、分享的方法。
UtilUMeng提供了初始化的方法

### app部分
- 添加依赖

```gradle
 	implementation project(':umeng')
    //微信分享
    implementation  'com.umeng.umsdk:share-wx:6.9.4'
```
添加微信的依赖是为了WXEntryActivity
- 结构图
![app结构](https://img-blog.csdnimg.cn/20190226171727794.png?x-oss-process=image/watermark,type_ZmFuZ3poZW5naGVpdGk,shadow_10,text_aHR0cHM6Ly9ibG9nLmNzZG4ubmV0L2FuZHJvaWRfaGw=,size_16,color_FFFFFF,t_70)
- 在MyApplication中调用初始化

```java
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化友盟
        UtilUMeng.umConfigure(this,"ae3a0d986d9d2cb80c05eca40c9b2455");
    }
}
```
- AndroidManifest.xml文件配置

```xml
<activity
            android:name=".wxapi.WXEntryActivity"
            android:configChanges="keyboardHidden|orientation|screenSize"
            android:exported="true"
            android:theme="@android:style/Theme.Translucent.NoTitleBar" />


        <!--友盟的appkey和channel-->
        <meta-data
            android:name="UMENG_APPKEY"
            android:value="5c749ecff1f556c9cd000758" />
        <meta-data
            android:name="UMENG_CHANNEL"
            android:value="Channel ID" />
```

使用的时候，在友盟平台创建的包名和应用包名一致。在应用中添加微信依赖，配置AppKey等。
分享的回调，通知的回调，自定义可在umeng中进行修改。