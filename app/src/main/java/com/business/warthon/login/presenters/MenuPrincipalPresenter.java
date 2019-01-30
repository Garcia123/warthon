package com.business.warthon.login.presenters;

 import android.content.Context;
 import com.business.warthon.login.contracts.MenuPrincipalContract;

 public class MenuPrincipalPresenter implements MenuPrincipalContract.Presenter {

 	MenuPrincipalContract.Interactor interactor;
 	MenuPrincipalContract.View view;
 	Context context;

 	public MenuPrincipalPresenter() {
 		interactor = MenuPrincipalContract.newInteractor().setPresenter(this);
 	}

 	@Override
 	public MenuPrincipalContract.Presenter setView(MenuPrincipalContract.View view){
 		this.view = view;
 		return this;
 	}

 	@Override
 	public MenuPrincipalContract.Presenter setContext(Context context) {
 		this.context = context;
 		return this;
 	}

 	@Override
 	public Context getContext() {
 		return context;
 	}

 	@Override
 	public MenuPrincipalContract.View getView() {
 		return view;
 	}

	 @Override
	 public void cerrarSesion() {
		interactor.cerrarSesion();
	 }
 }
