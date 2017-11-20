package com.android.ruangguru.feature.games.mapper;

import com.android.ruangguru.feature.games.model.GameResponse;
import com.google.gson.Gson;

import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import retrofit2.Response;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class GameMapper implements Function<Response<String>, GameResponse> {

    private Gson gson;

    public GameMapper(Gson gson) {
        this.gson = gson;
    }

    @Override
    public GameResponse apply(@NonNull Response<String> stringResponse) throws Exception {
        GameResponse response = new GameResponse();
        if (stringResponse.isSuccessful()) {
            response = gson.fromJson(stringResponse.body(), GameResponse.class);
        } else {
            response.setResults(null);
        }

        return response;
    }
}
