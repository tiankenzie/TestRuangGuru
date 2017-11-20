package com.android.ruangguru.feature.games.di;

import com.android.ruangguru.base.domain.executor.PostExecutionThread;
import com.android.ruangguru.base.domain.executor.ThreadExecutor;
import com.android.ruangguru.base.network.ApiServices;
import com.android.ruangguru.feature.games.mapper.GameMapper;
import com.android.ruangguru.feature.games.presenter.GamePresenter;
import com.android.ruangguru.feature.games.source.GameDataSource;
import com.android.ruangguru.feature.games.usecase.GameUseCase;
import com.google.gson.Gson;

import dagger.Provides;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class GameModule {
    @GameScope
    @Provides
    GameUseCase gameUseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread, GameDataSource gameDataSource) {
        return new GameUseCase(threadExecutor, postExecutionThread, gameDataSource);
    }

    @GameScope
    @Provides
    GamePresenter gamePresenter() {
        return new GamePresenter();
    }

    @GameScope
    @Provides
    GameMapper gameMapper(Gson gson) {
        return new GameMapper(gson);
    }

    @GameScope
    @Provides
    GameDataSource gameDataSource(ApiServices apiServices, GameMapper gameMapper) {
        return new GameDataSource(apiServices, gameMapper);
    }
}
