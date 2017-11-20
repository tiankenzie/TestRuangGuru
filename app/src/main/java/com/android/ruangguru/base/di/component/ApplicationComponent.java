package com.android.ruangguru.base.di.component;

import android.app.Activity;
import android.content.Context;

import com.android.ruangguru.base.di.module.ApplicationModule;
import com.android.ruangguru.base.di.scope.ApplicationScope;
import com.android.ruangguru.base.domain.executor.PostExecutionThread;
import com.android.ruangguru.base.domain.executor.ThreadExecutor;
import com.android.ruangguru.base.network.ApiServices;
import com.google.gson.Gson;

import dagger.Component;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */
@ApplicationScope
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
    void inject(Activity activity);

    @ApplicationContext
    Context context();

    @ActivityContext
    Context contextActivity();

    @RetrofitQualifier
    retrofit2.Retrofit serviceRetrofit();

    ApiServices apiService();

    Gson gson();

    retrofit2.Retrofit.Builder retrofitBuilder();

    ThreadExecutor threadExecutor();

    PostExecutionThread postExecutionThread();
}
