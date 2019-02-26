package com.example.umdemo;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.jkwy.umeng.push.UtilPush;
import com.jkwy.umeng.share.UtilShare;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        UtilPush.onAppStart(this);
        setContentView(R.layout.activity_main);
        TextView tv = findViewById(R.id.tv);
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

//                try {
//                    openFileInput("dd");
//                } catch (FileNotFoundException e) {
//                    e.printStackTrace();
//                    UtilUMeng.reportError(MainActivity.this,e.getMessage());
//                }
//
//                TestActivity.start(MainActivity.this);
                UtilShare.showShare(MainActivity.this);

            }
        });

    }


}
