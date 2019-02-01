package com.business.warthon.login.views;

import android.content.Intent;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.business.warthon.R;
import com.business.warthon.login.contracts.LoginContract;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, Validator.ValidationListener {

    ProgressBar progressBar;
    TextView txtLinkRegistrar;
    Button btnIngesar;

    @NotEmpty(messageResId = R.string.login_validate_password)
    EditText txtPassword;

    @Email(messageResId = R.string.login_validate_correo_es_correo)
    EditText txtCorreo;

    LoginContract.Presenter presenter;
    Validator validator;

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

        this.validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private LoginContract.Presenter instanciarPresenter() {
        return LoginContract.newPresenter()
                .setContext(this)
                .setView(this);
    }

    private void ingresarSistema(View view) {
        validator.validate();
    }

    private void registrarUsurio(View view) {
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
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

    @Override
    public void onValidationSucceeded() {
        mostrarProgresBar(true);
        presenter.loginCorreoPasswor(
                this.txtCorreo.getText().toString(),
                this.txtPassword.getText().toString()
        );
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
                break;
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    void mostrarProgresBar(boolean estado) {
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }
}
