package com.robust.robustutilslib.util;

import android.content.Context;
import android.widget.Toast;

import com.robust.robustutilslib.R;
import com.robust.robustutilslib.Robust;

/**
 * 常用的Toast的工具类
 * Created by chenhewen on 2017/10/17.
 */

public class ToastUtil {

    /**
     * 如果网络没有连接弹出toast
     * @return true if toast
     */
    public static boolean toastIfNetworkNotAvailable() {
        Context appContext = Robust.getAppContext();
        boolean connected = NetworkUtil.isConnected();
        if (!connected) {
            Toast.makeText(appContext, R.string.robust_toast_net_work_disconnected, Toast.LENGTH_SHORT).show();
        }

        return !connected;
    }

    /**
     * 如果当前网络不是wifi，弹出toast
     * @return
     */
    public static boolean toastIfNetworkNotWifi() {
        Context appContext = Robust.getAppContext();
        NetworkUtil.NetworkType networkType = NetworkUtil.getNetworkType();
        boolean notWifi = networkType != NetworkUtil.NetworkType.NETWORK_WIFI;
        if (notWifi) {
            Toast.makeText(appContext, R.string.robust_toast_data_network_caution, Toast.LENGTH_SHORT).show();
        }

        return notWifi;
    }
}
