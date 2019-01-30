package com.business.warthon.login.contracts;

import com.business.warthon.login.interactors.MenuPrincipalInteractor;
import com.business.warthon.login.presenters.MenuPrincipalPresenter;
import com.business.warthon.utiles.PadreInteractor;
import com.business.warthon.utiles.PadrePresenter;
import com.business.warthon.utiles.PadreView;

public interface MenuPrincipalContract {

    static Presenter newPresenter() {
        return new MenuPrincipalPresenter();
    }

    static Interactor newInteractor() {
        return new MenuPrincipalInteractor();
    }

    interface View extends PadreView {
        void respuestaCerrarSession();
    }

    interface Presenter extends PadrePresenter<Presenter, View> {
        void cerrarSesion();
    }

    interface Interactor extends PadreInteractor<Interactor, Presenter> {
        void cerrarSesion();
    }
}
