package com.android.ruangguru.base.network;

import java.util.Map;

import io.reactivex.Observable;
import retrofit2.Response;
import retrofit2.http.GET;
import retrofit2.http.QueryMap;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public interface ApiServices {

    @GET(ApiBaseUrl.ENDPOINT)
    Observable<Response<String>> getGame(@QueryMap()Map<String, Object> params);
}
