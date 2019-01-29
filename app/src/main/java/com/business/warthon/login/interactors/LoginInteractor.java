package com.business.warthon.login.interactors;

 public class ClienteInteractor implements LoginContract.Interactor {
 	LoginContract.Presenter presenter;

 	@Override
 	public LoginContract.Interactor setPresenter(LoginContract.Presenter presenter) {
 		this.presenter = presenter;
 		return this;
 	}
 }
