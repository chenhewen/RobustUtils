package com.robust.robustutilslib.util;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;

import com.robust.robustutilslib.Robust;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * 图片相关的工具类
 * Created by chenhewen on 2017/9/20.
 */

public class ImageUtil {

    public static void setWallpaper(Bitmap wallpaper) {
        Context appContext = Robust.getAppContext();
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(appContext);
        try {
            wallpaperManager.setBitmap(wallpaper);
        } catch (IOException e) {
            RobustLog.log(e.toString());
        }
    }

    public static Bitmap getWallpaper() {
        Context appContext = Robust.getAppContext();
        WallpaperManager wallpaperManager = WallpaperManager.getInstance(appContext);
        return ((BitmapDrawable) wallpaperManager.getDrawable()).getBitmap();
    }

    public static void save(Bitmap bitmap, File downloadImageDir) {
        String name = String.valueOf(System.currentTimeMillis()) + ".jpg";
        File file = new File(downloadImageDir, name);
        FileOutputStream fileOutputStream = null;
        try {
            fileOutputStream = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fileOutputStream);
        } catch (FileNotFoundException e) {
            RobustLog.log(e.toString());
        } finally {
            if (fileOutputStream != null) {
                try {
                    fileOutputStream.close();
                } catch (IOException e) {
                    RobustLog.log(e.toString());
                }
                fileOutputStream = null;
            }
        }
    }
}
