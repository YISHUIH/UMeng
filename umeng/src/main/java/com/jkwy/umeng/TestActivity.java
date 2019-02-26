package com.jkwy.umeng;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.jkwy.umeng.push.UtilPush;

public class TestActivity extends AppCompatActivity {

    public static void start(Context context) {
        Intent starter = new Intent(context, TestActivity.class);
//        starter.putExtra();
        context.startActivity(starter);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);
        UtilPush.onAppStart(this);

    }
}
