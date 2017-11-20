package com.android.ruangguru.base;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public abstract class BasePresenter<T extends BaseContractView> {

    private T view;

    public T getView() {
        return view;
    }

    public void setView(T view) {
        this.view = view;
    }
}
