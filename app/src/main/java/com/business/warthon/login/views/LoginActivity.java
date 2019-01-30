package com.business.warthon.login.views;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.business.warthon.R;
import com.business.warthon.login.contracts.LoginContract;

public class LoginActivity extends AppCompatActivity implements LoginContract.View {
    ProgressBar progressBar;
    EditText txtCorreo, txtPassword;
    TextView txtLinkRegistrar;
    Button btnIngesar;
    LoginContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.progressBar = findViewById(R.id.progressBar);

        this.txtCorreo = findViewById(R.id.txtRegistroCorreo);
        this.txtPassword = findViewById(R.id.txtRegistroPassword);

        this.txtLinkRegistrar = findViewById(R.id.txtLinkRegistrar);
        this.txtLinkRegistrar.setOnClickListener(this::registrarUsurio);

        this.btnIngesar = findViewById(R.id.btnIngesar);
        this.btnIngesar.setOnClickListener(this::ingresarSistema);

        this.presenter = this.instanciarPresenter();
    }

    private LoginContract.Presenter instanciarPresenter() {
        return LoginContract.newPresenter()
            .setContext(this)
            .setView(this);
    }

    private void ingresarSistema(View view) {
        mostrarProgresBar(true);
        presenter.loginCorreoPasswor(
            this.txtCorreo.getText().toString(),
            this.txtPassword.getText().toString()
        );
    }

    private void registrarUsurio(View view) {
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }

    @Override
    public void errorRespuesta(String mensaje) {

    }

    @Override
    public void respuestaLoginCorreoPasswor(boolean estado) {
        if (estado) {
            Intent intent = new Intent(this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
            startActivity(intent);
        }
        mostrarProgresBar(false);
    }

    void mostrarProgresBar(boolean estado){
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }
}
