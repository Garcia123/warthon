package com.business.warthon.login.interactors;

import com.business.warthon.dao.DaoLogin;
import com.business.warthon.dao.DaoUsuario;
import com.business.warthon.dao.DataFactory;
import com.business.warthon.login.contracts.RegistrarContract;
import com.business.warthon.model.Usuario;

public class RegistrarInteractor implements RegistrarContract.Interactor {
    DataFactory _factory = DataFactory.getFactory(DataFactory.FIREBASE);
    DaoLogin _daoLogin = _factory.getLoginDao();
    DaoUsuario _daoUsuario = _factory.getUsurarioDao();
 	RegistrarContract.Presenter presenter;

 	@Override
 	public RegistrarContract.Interactor setPresenter(RegistrarContract.Presenter presenter) {
 		this.presenter = presenter;
 		return this;
 	}

	@Override
	public void RegistrarUsuario(Usuario usuario) {
        _daoLogin.registrarUsuario(presenter.getContext(), usuario.getCorreo(), usuario.getPassword(), user -> {
            usuario.setUid(user.getUid());
            _daoUsuario.registrarUsuario(
                this.presenter.getContext(),
                usuario, this.presenter.getView()::seRegistroUsuario,
                presenter.getView()::errorRespuesta
            );
        }, presenter.getView()::errorRespuesta);
	}
}
