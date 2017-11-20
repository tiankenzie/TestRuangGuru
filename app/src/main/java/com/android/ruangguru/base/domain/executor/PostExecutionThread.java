package com.android.ruangguru.base.domain.executor;

import io.reactivex.Scheduler;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public interface PostExecutionThread {

    Scheduler getScheduler();
}
