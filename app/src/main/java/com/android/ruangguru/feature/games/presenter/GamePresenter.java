package com.android.ruangguru.feature.games.presenter;

import com.android.ruangguru.base.BasePresenter;
import com.android.ruangguru.base.domain.DefaultSubscriber;
import com.android.ruangguru.feature.games.contract.GameContract;
import com.android.ruangguru.feature.games.model.GameResponse;
import com.android.ruangguru.feature.games.usecase.GameUseCase;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class GamePresenter extends BasePresenter<GameContract.View> implements GameContract.Presenter {


    @Inject
    GameUseCase gameUseCase;

    @Override
    public void getGame(int amount, int category, String type) {
        gameUseCase.execute(
                gameUseCase.getRequestParams(amount, category, type),
                new DefaultSubscriber<GameResponse>(){
                    @Override
                    protected void onStart() {
                        getView().showLoading();
                    }

                    @Override
                    public void onNext(@NonNull GameResponse gameResponse) {
                        getView().onRenderGame(gameResponse);
                    }

                    @Override
                    public void onComplete() {
                        getView().hideLoading();
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        try {
                            getView().onError(e.getMessage());
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }

                    }
                }
        );
    }

    @Override
    public void unsubscribe() {
        gameUseCase.unsubscribe();
    }
}
