package com.android.ruangguru.base;

import android.support.multidex.MultiDexApplication;

import com.android.ruangguru.base.di.component.ApplicationComponent;
import com.android.ruangguru.base.di.module.ActivityModule;
import com.android.ruangguru.base.di.module.ApplicationModule;
import com.android.ruangguru.base.di.module.NetModule;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

class MyApplication extends MultiDexApplication{

    private DaggerApplicationComponent.Builder dagger;
    private ApplicationComponent component;

    @Override
    public void onCreate() {
        super.onCreate();
        setupDI();
    }

    private void setupDI() {
        dagger = DaggerApplicationComponent.builder()
                .appModule(new ApplicationModule(this))
                .netModule(new NetModule());
    }

    public ApplicationComponent getApplicationComponent(ActivityModule activityModule) {
        return component = dagger.activityModule(activityModule)
                .build();
    }


}
