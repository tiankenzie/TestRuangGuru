package com.android.ruangguru.feature.games.source;

import com.android.ruangguru.base.domain.RequestParams;
import com.android.ruangguru.base.network.ApiServices;
import com.android.ruangguru.feature.games.mapper.GameMapper;
import com.android.ruangguru.feature.games.model.GameResponse;

import io.reactivex.Observable;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class GameDataSource {
    private GameMapper gameMapper;
    private ApiServices apiServices;

    public GameDataSource(ApiServices apiServices, GameMapper gameMapper) {
        this.apiServices = apiServices;
        this.gameMapper = gameMapper;
    }

    public Observable<GameResponse> getGame(RequestParams requestParams) {
        return apiServices.getGame(requestParams.getParameters()).map(gameMapper);
    }
}
