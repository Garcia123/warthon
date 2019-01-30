package com.business.warthon.login.interactors;

import com.business.warthon.login.contracts.LoginContract;

public class LoginInteractor implements LoginContract.Interactor {
    LoginContract.Presenter presenter;

    @Override
    public LoginContract.Interactor setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }
}
