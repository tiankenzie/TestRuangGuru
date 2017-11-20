package com.android.ruangguru.base.domain.interactor;

import com.android.ruangguru.base.domain.RequestParams;
import com.android.ruangguru.base.domain.executor.PostExecutionThread;
import com.android.ruangguru.base.domain.executor.ThreadExecutor;

import io.reactivex.Observable;
import io.reactivex.disposables.Disposable;
import io.reactivex.disposables.Disposables;
import io.reactivex.observers.DisposableObserver;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public abstract class UseCase<T> implements Interactor<T> {
    protected ThreadExecutor threadExecutor;
    protected PostExecutionThread postExecutionThread;
    protected Disposable subscription = Disposables.empty();

    public UseCase(ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        this.threadExecutor = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }

    public UseCase() {
        this(null, null);
    }

    public abstract Observable<T> createObservable(RequestParams requestParams);

    public void execute(RequestParams requestParams, DisposableObserver<T> subscriber) {
        this.subscription = createObservable(requestParams)
                .subscribeOn(Schedulers.from(threadExecutor))
                .observeOn(postExecutionThread.getScheduler())
                .subscribeWith(subscriber);
    }

    public void execute(DisposableObserver<T> subscriber) {
        execute(null, subscriber);
    }

    public void unsubscribe() {
        if (!this.subscription.isDisposed()) {
            this.subscription.dispose();
        }
    }

    @Override
    public Observable<T> getExecuteObservable(RequestParams requestParams) {
        return createObservable(requestParams);
    }
}
