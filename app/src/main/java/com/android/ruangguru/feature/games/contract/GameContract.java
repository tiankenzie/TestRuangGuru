package com.android.ruangguru.feature.games.contract;

import com.android.ruangguru.base.BaseContractView;
import com.android.ruangguru.feature.games.model.GameResponse;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public interface GameContract {
    interface View extends BaseContractView {

        int getLayoutId();

        void initial();

        void initInjector();

        void onClickToolbar();

        int getButtonIconToolbar();

        String getToolbarTitle();

        void showLoading();

        void hideLoading();

        void onRenderGame(GameResponse gameResponse);

        void onError(String error);

        void showButton();

        void hideButton();

        void onDestroy();
    }

    interface Presenter {

        void getGame(int amount, int category, String type);

        void unsubscribe();

    }
}
