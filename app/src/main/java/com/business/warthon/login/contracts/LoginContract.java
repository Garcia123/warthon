package com.business.warthon.login.contracts;

import com.business.warthon.login.interactors.LoginInteractor;
import com.business.warthon.login.presenters.LoginPresenter;
import com.business.warthon.utiles.PadreInteractor;
import com.business.warthon.utiles.PadrePresenter;
import com.business.warthon.utiles.PadreView;

public interface LoginContract {

 	static Presenter newPresenter(){return new LoginPresenter();}

 	static Interactor newInteractor(){return new LoginInteractor();}

 	interface View extends PadreView {
 	}

 	interface Presenter extends PadrePresenter<Presenter, View> {
 	}

 	interface Interactor extends PadreInteractor<Interactor, Presenter> {
 	}
 }
