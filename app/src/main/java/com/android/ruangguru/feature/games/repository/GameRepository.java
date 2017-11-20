package com.android.ruangguru.feature.games.repository;

import com.android.ruangguru.base.domain.RequestParams;
import com.android.ruangguru.feature.games.model.GameResponse;

import io.reactivex.Observable;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public interface GameRepository {
    Observable<GameResponse> getGame(RequestParams requestParams);
}
