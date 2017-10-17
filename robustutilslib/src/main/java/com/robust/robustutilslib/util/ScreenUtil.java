package com.robust.robustutilslib.util;

import android.content.Context;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;

import com.robust.robustutilslib.Robust;

/**
 * 有关手机屏幕的工具类
 * Created by honorchen on 2017/2/24.
 */

public class ScreenUtil {
    /**
     * dp to px
     */
    public static int dip2px(float dpValue) {
        Context context = Robust.getAppContext();
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * px to dp
     */
    public static int px2dip(float pxValue) {
        Context context = Robust.getAppContext();
        final float scale = context.getResources()
                .getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

    public static void setBrightness(Context context, Window window, float brightness) {
        int screenMode;
        int screenBrightness;
        try {
            /*
             * 获得当前屏幕亮度的模式
             * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
             * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
             */
            screenMode = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE);

            // 获得当前屏幕亮度值 0--255
            screenBrightness = Settings.System.getInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS);
            // 如果当前的屏幕亮度调节调节模式为自动调节，则改为手动调节屏幕亮度
            if (screenMode == Settings.System.SCREEN_BRIGHTNESS_MODE_AUTOMATIC) {
                setScreenMode(context, Settings.System.SCREEN_BRIGHTNESS_MODE_MANUAL);
            }

            // 设置屏幕亮度值为最大值255
            setScreenBrightness(context, window, brightness * 255.0F);

        } catch (Settings.SettingNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 设置当前屏幕亮度的模式
     * SCREEN_BRIGHTNESS_MODE_AUTOMATIC=1 为自动调节屏幕亮度
     * SCREEN_BRIGHTNESS_MODE_MANUAL=0 为手动调节屏幕亮度
     */
    private static void setScreenMode(Context context, int value) {
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS_MODE, value);
    }

    /**
     * 设置当前屏幕亮度值 0--255，并使之生效
     */
    private static void setScreenBrightness(Context context, Window window, float value) {
        WindowManager.LayoutParams mParams = window.getAttributes();
        float f = value / 255.0F;
        mParams.screenBrightness = f;
        window.setAttributes(mParams);

        // 保存设置的屏幕亮度值
        Settings.System.putInt(context.getContentResolver(), Settings.System.SCREEN_BRIGHTNESS, (int) value);
    }
}
