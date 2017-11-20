package com.android.ruangguru.base.di.module;

import android.content.Context;
import android.content.ContextWrapper;

import com.android.ruangguru.base.di.component.ActivityContext;
import com.android.ruangguru.base.di.scope.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */
@Module
public class ActivityModule {

    private final ContextWrapper contextWrapper;


    public ActivityModule(ContextWrapper contextWrapper) {
        this.contextWrapper = contextWrapper;
    }

    @ApplicationScope
    @Provides
    @ActivityContext
    public Context provideContext(){
        return contextWrapper;
    }
}
