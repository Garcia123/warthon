package com.business.warthon.login.contracts;

import com.business.warthon.login.interactors.SplashScreenInteractor;
import com.business.warthon.login.presenters.SplashScreenPresenter;
import com.business.warthon.utiles.PadreInteractor;
import com.business.warthon.utiles.PadrePresenter;
import com.business.warthon.utiles.PadreView;

public interface SplashScreenContract {

 	static Presenter newPresenter(){return new SplashScreenPresenter();}

 	static Interactor newInteractor(){return new SplashScreenInteractor();}

 	interface View extends PadreView {
 		void existeSesion(boolean estado);
 	}

 	interface Presenter extends PadrePresenter<Presenter, View> {
		void solicitarSesion();
 	}

 	interface Interactor extends PadreInteractor<Interactor, Presenter> {
 		void solicitarSesion();
 	}
 }
