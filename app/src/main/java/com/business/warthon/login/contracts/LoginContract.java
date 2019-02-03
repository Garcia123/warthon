package com.business.warthon.login.contracts;

import com.business.warthon.login.interactors.LoginInteractor;
import com.business.warthon.login.presenters.LoginPresenter;
import com.business.warthon.utiles.PadreInteractor;
import com.business.warthon.utiles.PadrePresenter;
import com.business.warthon.utiles.PadreView;
import com.google.firebase.auth.AuthCredential;

public interface LoginContract {

 	static Presenter newPresenter(){return new LoginPresenter();}

 	static Interactor newInteractor(){return new LoginInteractor();}

 	interface View extends PadreView {
        void respuestaLoginCorreoPasswor(boolean estado);
 	}

 	interface Presenter extends PadrePresenter<Presenter, View> {
 	    void loginCorreoPasswor(String correo, String password);
        void loginConCredencial(AuthCredential credential);
 	}

 	interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void loginCorreoPasswor(String correo, String password);
        void loginConCredencial(AuthCredential credential);
 	}
 }
