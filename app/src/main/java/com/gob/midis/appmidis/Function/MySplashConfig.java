package com.gob.midis.appmidis.Function;

import android.app.Application;
import android.os.SystemClock;

public class MySplashConfig extends Application{
    @Override
    public void onCreate() {
        super.onCreate();
        SystemClock.sleep(3000);
    }
}
