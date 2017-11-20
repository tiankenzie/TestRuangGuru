package com.android.ruangguru.base.di.module;

import android.content.Context;

import com.android.ruangguru.BuildConfig;
import com.android.ruangguru.base.di.component.ActivityContext;
import com.android.ruangguru.base.di.scope.ApplicationScope;

import java.io.IOException;

import dagger.Module;
import dagger.Provides;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */
@Module
public class Interceptor {
    @ApplicationScope
    @Provides
    public HttpLoggingInterceptor provideHttpLoggingInterceptor() {
        HttpLoggingInterceptor logging = new HttpLoggingInterceptor();
        if (BuildConfig.DEBUG) {
            logging.setLevel(HttpLoggingInterceptor.Level.BODY);
        } else {
            logging.setLevel(HttpLoggingInterceptor.Level.NONE);
        }
        return logging;
    }

    @ApplicationScope
    @Provides
    public okhttp3.Interceptor provideInterceptor(@ActivityContext final Context context) {
        return new okhttp3.Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Request.Builder reqBuilder = request.newBuilder();
                Request req = reqBuilder.build();
                return chain.proceed(req);
            }
        };
    }

}
