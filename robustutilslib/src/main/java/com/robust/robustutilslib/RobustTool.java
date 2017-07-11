package com.robust.robustutilslib;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;
import android.widget.Toast;

/**
 * RobustTool
 * Created by chenhewen on 2017/6/19.
 */

public class RobustTool {

    private static String sTag = "Robust";

    public static final int SHOW_TYPE_LOG = 1;
    public static final int SHOW_TYPE_TOAST = 1 << 1;

    private static Context sAppContext;

    private static int sType = SHOW_TYPE_LOG;
    private static long sMainThreadId;

    private static Handler sHandler = new Handler(Looper.getMainLooper());

    public static void init(Context appContext, String tag, int type) {
        sAppContext = appContext;
        sTag = tag;
        sType = type;
        sMainThreadId = Thread.currentThread().getId();
    }

    public static void log(final String log) {
        if ((sType & SHOW_TYPE_LOG) != 0) {
            Log.i(sTag, log);
        }
        if ((sType & SHOW_TYPE_TOAST) != 0) {
            long curThreadId = Thread.currentThread().getId();
            if (curThreadId == sMainThreadId) {
                Toast.makeText(sAppContext, log, Toast.LENGTH_SHORT).show();
            } else {
                sHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(sAppContext, log, Toast.LENGTH_SHORT).show();
                    }
                });
            }
        }
    }


}
