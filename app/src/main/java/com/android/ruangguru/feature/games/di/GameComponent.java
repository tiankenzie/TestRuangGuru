package com.android.ruangguru.feature.games.di;

import com.android.ruangguru.base.di.component.ApplicationComponent;
import com.android.ruangguru.feature.games.presenter.GamePresenter;
import com.android.ruangguru.feature.games.view.GameActivity;

import dagger.Component;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

@GameScope
@Component(modules = GameModule.class, dependencies = ApplicationComponent.class)
public interface GameComponent {

    void inject(GameActivity gameActivity);

    void inject(GamePresenter gamePresenter);
}