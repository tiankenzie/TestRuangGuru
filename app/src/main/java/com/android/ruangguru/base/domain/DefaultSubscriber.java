package com.android.ruangguru.base.domain;

import io.reactivex.observers.DisposableObserver;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class DefaultSubscriber<T> extends DisposableObserver<T> {
    @Override
    public void onNext(T t) {

    }

    @Override
    public void onError(Throwable e) {

    }

    @Override
    public void onComplete() {

    }
}
