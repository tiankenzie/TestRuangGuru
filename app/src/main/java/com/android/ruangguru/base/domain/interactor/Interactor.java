package com.android.ruangguru.base.domain.interactor;

import com.android.ruangguru.base.domain.RequestParams;

import io.reactivex.Observable;
import io.reactivex.observers.DisposableObserver;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public interface Interactor<T> {
    void execute(RequestParams requestParams, DisposableObserver<T> subscriber);

    Observable<T> getExecuteObservable(RequestParams requestParams);
}
