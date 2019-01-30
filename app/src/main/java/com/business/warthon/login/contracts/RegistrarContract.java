package com.business.warthon.login.contracts;

import com.business.warthon.login.interactors.RegistrarInteractor;
import com.business.warthon.login.presenters.RegistrarPresenter;
import com.business.warthon.model.Usuario;
import com.business.warthon.utiles.PadreInteractor;
import com.business.warthon.utiles.PadrePresenter;
import com.business.warthon.utiles.PadreView;

public interface RegistrarContract {

 	static Presenter newPresenter(){return new RegistrarPresenter();}

 	static Interactor newInteractor(){return new RegistrarInteractor();}

 	interface View extends PadreView {
 	    void seRegistroUsuario(Usuario usuario);
 	}

 	interface Presenter extends PadrePresenter<Presenter, View> {
        void RegistrarUsuario(Usuario usuario);
 	}

 	interface Interactor extends PadreInteractor<Interactor, Presenter> {
 		void RegistrarUsuario(Usuario usuario);
 	}
 }
