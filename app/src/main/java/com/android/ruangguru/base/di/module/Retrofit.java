package com.android.ruangguru.base.di.module;

import com.android.ruangguru.base.di.component.RetrofitQualifier;
import com.android.ruangguru.base.di.scope.ApplicationScope;
import com.android.ruangguru.base.network.ApiServices;
import com.android.ruangguru.base.network.ResponseConverter;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dagger.Module;
import dagger.Provides;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */
@Module
public class Retrofit {

    @ApplicationScope
    @Provides
    public retrofit2.Retrofit.Builder provideRetrofitBuilder(ResponseConverter stringResponseConverter){
        return new retrofit2.Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(stringResponseConverter)
                .addConverterFactory(GsonConverterFactory.create());
    }

    @ApplicationScope
    @Provides
    ResponseConverter provideStringResponseConverter(){
        return new ResponseConverter();
    }

    @ApplicationScope
    @Provides
    ApiServices apiService(@RetrofitQualifier retrofit2.Retrofit retrofit){
        return retrofit.create(ApiServices.class);
    }

    @ApplicationScope
    @Provides
    public Gson provideGson() {
        return new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setPrettyPrinting()
                .serializeNulls()
                .create();
    }

}
