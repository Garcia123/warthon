package com.business.warthon.login.views;

import android.content.Intent;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.business.warthon.R;
import com.business.warthon.login.contracts.LoginContract;
import com.google.android.gms.auth.api.Auth;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.auth.api.signin.GoogleSignInResult;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.GoogleAuthProvider;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;

import java.util.List;

public class LoginActivity extends AppCompatActivity implements LoginContract.View, Validator.ValidationListener{

    private static final int RC_SIGN_IN = 777;
    ProgressBar progressBar;
    TextView txtLinkRegistrar;
    Button btnIngesar;
    RelativeLayout btnGoogle;
    @NotEmpty(messageResId = R.string.login_validate_password)
    EditText txtPassword;

    @Email(messageResId = R.string.login_validate_correo_es_correo)
    EditText txtCorreo;

    LoginContract.Presenter presenter;
    Validator validator;

    private GoogleSignInClient mGoogleSignInClient;

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

        this.btnGoogle = findViewById(R.id.btnGoogle);
        this.btnGoogle.setOnClickListener(this::onBtnLoginGoogle);
        this.presenter = this.instanciarPresenter();

        this.validator = new Validator(this);
        validator.setValidationListener(this);

        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.default_web_client_id2))
                .requestEmail()
                .build();

        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
    }

    private void onBtnLoginGoogle(View view) {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
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

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_SIGN_IN) {
            handlerGoogleSignInApi(data);
        }
    }

    private void handlerGoogleSignInApi(Intent data) {
        Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
        try {
            // Google Sign In was successful, authenticate with Firebase
            GoogleSignInAccount account = task.getResult(ApiException.class);
            authWithGoogle(account);
        } catch (ApiException e) {
            errorRespuesta(e.getMessage());
        }
    }

    private void authWithGoogle(GoogleSignInAccount acct) {
        AuthCredential credential = GoogleAuthProvider.getCredential(acct.getIdToken(), null);
        presenter.loginConCredencial(credential);
        mostrarProgresBar(true);
    }

}
