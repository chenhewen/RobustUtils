package com.robust.robustutils;

import android.app.Activity;
import android.os.Bundle;

import com.robust.robustutilslib.RobustTool;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RobustTool.init("customTag", RobustTool.SHOW_TYPE_TOAST | RobustTool.SHOW_TYPE_LOG);
        RobustTool.log(getApplicationContext(), "hello from robust tool");
    }
}
