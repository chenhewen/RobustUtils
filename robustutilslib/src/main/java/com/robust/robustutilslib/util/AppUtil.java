package com.robust.robustutilslib.util;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import com.robust.robustutilslib.Robust;
import com.robust.robustutilslib.RobustGlobal;

/**
 * 有关应用的工具类
 * Created by chenhewen on 2017/7/15.
 */

public class AppUtil {

    public static Intent getOpenIntent(Uri uri) {
        Context context = Robust.getAppContext();
        Intent intent = new Intent(Intent.ACTION_VIEW);
        if (!(context instanceof Activity)) {
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        }
        intent.setData(uri);
        return intent;
    }

    public static void openInExplorer(String url) {
        Context context = Robust.getAppContext();
        Uri uri = Uri.parse(url);
        Intent intent = getOpenIntent(uri);
        context.startActivity(intent);
    }

    public static boolean isAppInstall(String packageName) {
        Context context = Robust.getAppContext();
        ApplicationInfo applicationInfo = null;
        PackageManager pm = context.getPackageManager();
        try {
            applicationInfo = pm.getApplicationInfo(packageName, 0);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        return applicationInfo != null;
    }

    public static boolean isGooglePlayInstall() {
        return isAppInstall(RobustGlobal.PackageName.GP_PACKAGE_NAME);
    }

    public static void openInGp(String pkgName) {

        String gpPkgName = RobustGlobal.PackageName.GP_PACKAGE_NAME;

        Context context = Robust.getAppContext();
        Uri uri = Uri.parse("market://details?id=" + pkgName);
        Intent intent = getOpenIntent(uri);
        if (isAppInstall(gpPkgName)) {
            intent.setPackage(gpPkgName);
        }
        context.startActivity(intent);
    }

    public static void openInGooglePlus(String url) {

        String googlePlusPkgName = RobustGlobal.PackageName.GOOGLE_PLUS_PACKAGE_NAME;

        Context context = Robust.getAppContext();
        Uri uri = Uri.parse(url);
        Intent intent = getOpenIntent(uri);
        if (isAppInstall(googlePlusPkgName)) {
            intent.setPackage(googlePlusPkgName);
        }
        context.startActivity(intent);
    }

}
