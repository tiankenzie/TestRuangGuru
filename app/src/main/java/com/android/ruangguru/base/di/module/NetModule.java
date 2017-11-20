package com.android.ruangguru.base.di.module;

import com.android.ruangguru.base.di.component.RetrofitQualifier;
import com.android.ruangguru.base.di.scope.ApplicationScope;
import com.android.ruangguru.base.network.ApiBaseUrl;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */
@Module (includes = {OkkHttpClient.class, Retrofit.class})
public class NetModule {
    @RetrofitQualifier
    @ApplicationScope
    @Provides
    public retrofit2.Retrofit provideRetrofit(retrofit2.Retrofit.Builder retrofitBuilder, OkHttpClient httpClient){
        return retrofitBuilder.baseUrl(ApiBaseUrl.BASE_URL).client(httpClient).build();
    }
}
