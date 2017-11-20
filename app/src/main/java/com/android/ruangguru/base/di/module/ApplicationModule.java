package com.android.ruangguru.base.di.module;

import android.content.Context;

import com.android.ruangguru.base.di.component.ApplicationContext;
import com.android.ruangguru.base.di.scope.ApplicationScope;
import com.android.ruangguru.base.domain.executor.PostExecutionThread;
import com.android.ruangguru.base.domain.executor.ThreadExecutor;
import com.android.ruangguru.base.network.JobExecutor;
import com.android.ruangguru.base.network.UIThread;

import dagger.Module;
import dagger.Provides;

/**
 * Created by vpdigital on 11/20/17.
 */
@Module(includes = {ActivityModule.class, NetModule.class})
public class ApplicationModule {

    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @ApplicationScope
    @Provides
    @ApplicationContext
    public Context provideContext(){
        return context.getApplicationContext();
    }

    @ApplicationScope
    @Provides
    public ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @ApplicationScope
    @Provides
    public PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

}
