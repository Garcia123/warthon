package com.business.warthon.login.interactors;

import com.business.warthon.dao.DaoCliente;
import com.business.warthon.dao.DaoLogin;
import com.business.warthon.dao.DataFactory;
import com.business.warthon.login.contracts.RegistrarContract;
import com.business.warthon.model.Cliente;
import com.business.warthon.model.Usuario;

public class RegistrarInteractor implements RegistrarContract.Interactor {
    DataFactory _factory = DataFactory.getFactory(DataFactory.FIREBASE);
    DaoLogin _daoLogin = _factory.getLoginDao();
    DaoCliente _daoCliente = _factory.getClienteDao();
 	RegistrarContract.Presenter presenter;

 	@Override
 	public RegistrarContract.Interactor setPresenter(RegistrarContract.Presenter presenter) {
 		this.presenter = presenter;
 		return this;
 	}

	@Override
	public void RegistrarUsuario(Usuario usuario) {
        _daoLogin.registrarUsuario(presenter.getContext(), usuario.getCorreo(), usuario.getPassword(), user -> {
            _daoCliente.crearCliente((Cliente) user, cliente -> {

            },presenter.getView()::errorRespuesta);
        }, presenter.getView()::errorRespuesta);
	}
}
