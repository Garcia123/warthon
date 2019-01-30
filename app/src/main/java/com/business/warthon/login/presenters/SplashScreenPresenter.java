package com.business.warthon.login.presenters;

 import android.content.Context;
 import com.business.warthon.login.contracts.SplashScreenContract;

 public class SplashScreenPresenter implements SplashScreenContract.Presenter {

 	SplashScreenContract.Interactor interactor;
 	SplashScreenContract.View view;
 	Context context;

 	public SplashScreenPresenter() {
 		interactor = SplashScreenContract.newInteractor().setPresenter(this);
 	}

 	@Override
 	public SplashScreenContract.Presenter setView(SplashScreenContract.View view){
 		this.view = view;
 		return this;
 	}

 	@Override
 	public SplashScreenContract.Presenter setContext(Context context) {
 		this.context = context;
 		return this;
 	}

 	@Override
 	public Context getContext() {
 		return context;
 	}

 	@Override
 	public SplashScreenContract.View getView() {
 		return view;
 	}

	 @Override
	 public void solicitarSesion() {
		interactor.solicitarSesion();
	 }
 }
