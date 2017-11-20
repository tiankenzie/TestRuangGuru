package com.android.ruangguru.base.di.module;

import com.android.ruangguru.base.di.scope.ApplicationScope;

import java.util.concurrent.TimeUnit;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

@Module(includes = {Interceptor.class})
public class OkkHttpClient {
    @ApplicationScope
    @Provides
    public OkHttpClient provideOkHttpClient(HttpLoggingInterceptor loggingInterceptor,
                                            okhttp3.Interceptor interceptor) {
        return new OkHttpClient.Builder()
                .addInterceptor(loggingInterceptor)
                .connectTimeout(15, TimeUnit.SECONDS)
                .readTimeout(15, TimeUnit.SECONDS)
                .addInterceptor(interceptor)
                .build();
    }
}
