package com.business.warthon.login.interactors;

import com.business.warthon.dao.DataFactory;
import com.business.warthon.dao.DaoLogin;
import com.business.warthon.login.contracts.MenuPrincipalContract;

public class MenuPrincipalInteractor implements MenuPrincipalContract.Interactor {

    DataFactory _factory = DataFactory.getFactory(DataFactory.FIREBASE);
    DaoLogin _login = _factory.getLoginDao();
 	MenuPrincipalContract.Presenter presenter;

 	@Override
 	public MenuPrincipalContract.Interactor setPresenter(MenuPrincipalContract.Presenter presenter) {
 		this.presenter = presenter;
 		return this;
 	}

	@Override
	public void cerrarSesion() {
        _login.cerrarSecion((obj)-> {
            this.presenter.getView().respuestaCerrarSession();
        });
	}
}
