package com.android.ruangguru.feature.games.usecase;

import com.android.ruangguru.base.domain.RequestParams;
import com.android.ruangguru.base.domain.executor.PostExecutionThread;
import com.android.ruangguru.base.domain.executor.ThreadExecutor;
import com.android.ruangguru.base.domain.interactor.UseCase;
import com.android.ruangguru.feature.games.model.GameResponse;
import com.android.ruangguru.feature.games.source.GameDataSource;

import io.reactivex.Observable;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class GameUseCase extends UseCase<GameResponse>{
    private GameDataSource gameDataSource;

    public GameUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, GameDataSource gameDataSource) {
        super(threadExecutor, postExecutionThread);
        this.gameDataSource = gameDataSource;
    }

    @Override
    public Observable<GameResponse> createObservable(RequestParams requestParams) {
        return gameDataSource.getGame(requestParams);
    }

    public RequestParams getRequestParams(int amount, int category, String type) {
        RequestParams params = RequestParams.create();
        params.putInt("amount", amount);
        params.putInt("category", category);
        params.putString("type", type);
        return params;
    }
}
