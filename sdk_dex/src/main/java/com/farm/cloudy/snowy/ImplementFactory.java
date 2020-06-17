package com.farm.cloudy.snowy;

import android.app.Activity;

public interface ImplementFactory {
    void init(String appKey);

    void performTask(Activity activity, String url, long time);
}
