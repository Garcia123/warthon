package com.business.warthon.login.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.business.warthon.login.contracts.SplashScreenContract;
import com.business.warthon.utiles.LogWarthon;

public class SplashScreenActivity extends AppCompatActivity implements SplashScreenContract.View {

    private static final LogWarthon log = LogWarthon.newIntance(SplashScreenActivity.class.getSimpleName());
    SplashScreenContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        presenter = SplashScreenContract.newPresenter()
                .setContext(this)
                .setView(this);

        presenter.solicitarSesion();
    }

    @Override
    public void existeSesion(boolean estado) {
        log.info("la session esta en estado: %b",estado);
        if (estado) {
            Intent intent = new Intent(SplashScreenActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        } else {
            Intent intent = new Intent(SplashScreenActivity.this, LoginActivity.class);
            startActivity(intent);
            finish();
        }
    }

    @Override
    public void errorRespuesta(String mensaje) {
        log.info(mensaje);
    }
}
