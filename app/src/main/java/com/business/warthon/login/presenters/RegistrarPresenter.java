package com.business.warthon.login.presenters;

import android.content.Context;
import com.business.warthon.login.contracts.RegistrarContract;

public class RegistrarPresenter implements RegistrarContract.Presenter {

    RegistrarContract.Interactor interactor;
    RegistrarContract.View view;
    Context context;

    public RegistrarPresenter() {
        interactor = RegistrarContract.newInteractor().setPresenter(this);
    }

    @Override
    public RegistrarContract.Presenter setView(RegistrarContract.View view) {
        this.view = view;
        return this;
    }

    @Override
    public RegistrarContract.Presenter setContext(Context context) {
        this.context = context;
        return this;
    }

    @Override
    public Context getContext() {
        return context;
    }

    @Override
    public RegistrarContract.View getView() {
        return view;
    }
}
