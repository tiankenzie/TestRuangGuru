package com.android.ruangguru.base.network;

import com.android.ruangguru.base.domain.executor.PostExecutionThread;

import javax.inject.Inject;

import io.reactivex.Scheduler;
import io.reactivex.android.schedulers.AndroidSchedulers;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class UIThread implements PostExecutionThread{
    @Inject
    public UIThread() {
    }

    @Override
    public Scheduler getScheduler() {
        return AndroidSchedulers.mainThread();
    }

}
