package com.robust.robustutilslib;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

/**
 * Created by chenhewen on 2017/6/19.
 */

public class RobustTool {

    private static String sTag = "Robust";

    public static final int SHOW_TYPE_LOG = 1;
    public static final int SHOW_TYPE_TOAST = 1 << 1;

    private static int sType = SHOW_TYPE_LOG;

    public static void init(String tag, int type) {
        sTag = tag;
        sType = type;
    }

    public static void Log(Context context, String log) {
        if ((sType & SHOW_TYPE_LOG) != 0) {
            Log.i(sTag, log);
        }
        if ((sType & SHOW_TYPE_TOAST) != 0) {
            Toast.makeText(context, log, Toast.LENGTH_SHORT).show();
        }
    }


}
