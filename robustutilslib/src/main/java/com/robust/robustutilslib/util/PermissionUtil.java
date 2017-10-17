package com.robust.robustutilslib.util;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

/**
 * Created by chenhewen on 2017/9/26.
 */

public class PermissionUtil {

    public static boolean checkStoragePermission(Activity activity) {
        return ContextCompat.checkSelfPermission(activity,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
                == PackageManager.PERMISSION_GRANTED;
    }

    public static void requestStoragePermission(Activity activity, int requestCode) {
        // Here, thisActivity is the current activity
        if (!checkStoragePermission(activity)) {

            ActivityCompat.requestPermissions(activity,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                    requestCode);

        }
    }
}
