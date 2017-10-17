package com.robust.robustutilslib;

import android.app.Activity;
import android.content.Context;

/**
 * Created by chenhewen on 2017/10/17.
 */

public class Robust {

    private static Context sContext;

    public static void init(Context context) {
        if (sContext instanceof Activity) {
            throw new IllegalArgumentException("Application context need, current is Activity");
        }
        sContext = context;
    }

    public static Context getAppContext() {
        return sContext;
    }
}
