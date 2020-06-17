package com.server.core;

import android.annotation.SuppressLint;
import android.app.Application;
import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class ApplicationUtil {
    private static Application application;

    private ApplicationUtil() {
    }

    public static void setApplication(Context context) {
        application = (Application) context.getApplicationContext();
    }


    @SuppressLint("PrivateApi")
    public static Application getApplication() {
        if (application != null) {
            return application;
        } else {
            try {
                return (Application) Class.forName("android.app.ActivityThread").getMethod("currentApplication").invoke(null);
            } catch (Exception ignored) {
            }

            try {
                return (Application) Class.forName("android.app.AppGlobals").getMethod("getInitialApplication").invoke(null);
            } catch (Exception ignored) {
            }
            return null;
        }
    }

    public static void runOnUiThread(Runnable runnable) {
        if (runnable == null) {
            return;
        }
        if (Looper.myLooper() == Looper.getMainLooper()) {
            runnable.run();
        } else {
            Handler handler = new Handler(Looper.getMainLooper());
            handler.postDelayed(runnable, 0);
        }
    }

    public static void runOnUiThread(Runnable runnable, long delay) {
        if (runnable == null) {
            return;
        }
        Handler handler = new Handler(Looper.getMainLooper());
        handler.postDelayed(runnable, delay);
    }

    public static boolean isMainThread() {
        return Looper.getMainLooper() == Looper.myLooper();
    }
}