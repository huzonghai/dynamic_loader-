package com.farm.cloudy.snowy;

import android.util.Log;

public class Logger {
    public Logger() {
    }

    private static final String TAG = "walk";
    private static boolean ENABLE = true;


    public static void d(String message, Throwable throwable) {
        if (ENABLE) {
            if (throwable == null) {
                Log.d(TAG, message);
            } else {
                Log.d(TAG, message, throwable);
            }
        }
    }

    public static void i(String message) {
        if (ENABLE) {
            Log.i(TAG, message);
        }
    }

    public static void e(String message, Throwable throwable) {
        if (ENABLE) {
            if (throwable == null) {
                Log.e(TAG, message);
            } else {
                Log.e(TAG, message, throwable);
            }
        }
    }

    public static void w(String message, Throwable throwable) {
        if (ENABLE) {
            if (throwable == null) {
                Log.w(TAG, message);
            } else {
                Log.w(TAG, message, throwable);
            }
        }
    }

    public static void w(String message) {
        if (ENABLE) {
            if (message != null) {
                Log.w(TAG, message);
            }
        }
    }

    public static void e(String message) {
        if (ENABLE) {
            if (message != null) {
                Log.e(TAG, message);
            }
        }
    }

    public static void d(String message) {
        if (ENABLE) {
            if (message != null) {
                Log.d(TAG, message);
            }
        }
    }
}
