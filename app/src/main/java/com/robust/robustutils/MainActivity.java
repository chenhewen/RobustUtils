package com.robust.robustutils;

import android.app.Activity;
import android.os.Bundle;

import com.robust.robustutilslib.Robust;
import com.robust.robustutilslib.util.RobustLog;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Robust.init(getApplicationContext());
        RobustLog.init("customTag", RobustLog.SHOW_TYPE_TOAST | RobustLog.SHOW_TYPE_LOG);
        RobustLog.log("hello from robust tool");
    }
}
