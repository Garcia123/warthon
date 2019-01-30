package com.business.warthon.login.interactors;

import com.business.warthon.dao.DataFactory;
import com.business.warthon.dao.DaoLogin;
import com.business.warthon.login.contracts.SplashScreenContract;

public class SplashScreenInteractor implements SplashScreenContract.Interactor {

	DataFactory _factory = DataFactory.getFactory(DataFactory.FIREBASE);
	DaoLogin _login = _factory.getLoginDao();
 	SplashScreenContract.Presenter presenter;

 	@Override
 	public SplashScreenContract.Interactor setPresenter(SplashScreenContract.Presenter presenter) {
 		this.presenter = presenter;
 		return this;
 	}

	@Override
	public void solicitarSesion() {
        _login.vificarSesion(presenter.getView()::existeSesion);
	}
}
