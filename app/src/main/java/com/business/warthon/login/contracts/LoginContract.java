package com.business.warthon.login.contracts;

import com.business.warthon.utiles.PadreInteractor;
import com.business.warthon.utiles.PadrePresenter;
import com.business.warthon.utiles.PadreView;

public interface LoginContract {

 	static Presenter newPresenter(){return null;}

 	static Interactor newInteractor(){return null;}

 	interface View extends PadreView {
 	}

 	interface Presenter extends PadrePresenter<Presenter, View> {
 	}

 	interface Interactor extends PadreInteractor<Interactor, Presenter> {
 	}
 }
