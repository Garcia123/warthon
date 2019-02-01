package com.business.warthon.login.interactors;

import com.business.warthon.dao.DataFactory;
import com.business.warthon.dao.DaoLogin;
import com.business.warthon.login.contracts.LoginContract;

public class LoginInteractor implements LoginContract.Interactor {
    DataFactory _factory = DataFactory.getFactory(DataFactory.FIREBASE);
    DaoLogin _login = _factory.getLoginDao();
    LoginContract.Presenter presenter;

    @Override
    public LoginContract.Interactor setPresenter(LoginContract.Presenter presenter) {
        this.presenter = presenter;
        return this;
    }

    @Override
    public void loginCorreoPasswor(String correo, String password) {
        _login.iniciarSesionCorreoPassword(presenter.getContext(), correo, password,
            presenter.getView()::respuestaLoginCorreoPasswor,
            presenter.getView()::errorRespuesta
        );
    }
}
