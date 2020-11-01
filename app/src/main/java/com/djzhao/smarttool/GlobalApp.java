package com.djzhao.smarttool;

import android.content.Context;

import androidx.multidex.MultiDex;

import org.litepal.LitePalApplication;

public class GlobalApp extends LitePalApplication {
    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
    }
}
