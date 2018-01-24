package com.qinlei.permissionutilsdemo;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by qinlei
 * Created on 2018/1/24
 * Created description :
 */

public class ExampleApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        if (LeakCanary.isInAnalyzerProcess(this)) {
            // This process is dedicated to LeakCanary for heap analysis.
            // You should not init your app in this process.
            return;
        }
        LeakCanary.install(this);
        // Normal app init code...
    }
}
