package com.business.warthon.login.presenters;

 import android.content.Context;
 import com.business.warthon.login.contracts.LoginContract;

 public class LoginPresenter implements LoginContract.Presenter {

 	LoginContract.Interactor interactor;
 	LoginContract.View view;
 	Context context;

 	public LoginPresenter() {
 		interactor = LoginContract.newInteractor().setPresenter(this);
 	}

 	@Override
 	public LoginContract.Presenter setView(LoginContract.View view){
 		this.view = view;
 		return this;
 	}

 	@Override
 	public LoginContract.Presenter setContext(Context context) {
 		this.context = context;
 		return this;
 	}

 	@Override
 	public Context getContext() {
 		return context;
 	}

 	@Override
 	public LoginContract.View getView() {
 		return view;
 	}

	 @Override
	 public void loginCorreoPasswor(String correo, String password) {
		interactor.loginCorreoPasswor(correo,password);
	 }
 }
