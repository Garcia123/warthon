package com.business.warthon.login.views;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.business.warthon.R;
import com.business.warthon.login.contracts.RegistrarContract;
import com.business.warthon.model.Usuario;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.Date;
import java.util.List;

public class RegistrarActivity extends AppCompatActivity implements Validator.ValidationListener, RegistrarContract.View {

    @Email(sequence = 1, messageResId = R.string.registro_correo_validar)
    EditText txtRegistroCorreo;

    @NotEmpty(sequence = 2, messageResId = R.string.registro_passwor_validar)
    EditText txtRegistroPassword;

    @NotEmpty(sequence = 3, messageResId = R.string.registro_nombres_validar)
    EditText txtRegistroNombres;

    @NotEmpty(sequence = 4, messageResId = R.string.registro_apellidos_validar)
    EditText txtRegistroApellidos;

    @Pattern(sequence = 5, regex = "^9[0-9]{8}", messageResId =R.string.registro_telefono_validar)
    EditText txtRegistroTelefono;

    @Pattern(sequence = 6, regex = "^[1-9]{8}$", messageResId =R.string.registro_dni_validar)
    EditText txtRegistroDni;

    Button registrar;
    ProgressBar progressBar;
    Validator validator;
    RegistrarContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        txtRegistroCorreo = findViewById(R.id.txtRegistroCorreo);
        txtRegistroPassword = findViewById(R.id.txtRegistroPassword);
        txtRegistroNombres = findViewById(R.id.txtRegistroNombres);
        txtRegistroApellidos = findViewById(R.id.txtRegistroApellidos);
        txtRegistroTelefono = findViewById(R.id.txtRegistroTelefono);
        txtRegistroDni = findViewById(R.id.txtRegistroDni);

        registrar = findViewById(R.id.btnRegistrar);
        registrar.setOnClickListener(this::onRegistrar);

        progressBar = findViewById(R.id.progressBar);

        validator = new Validator(this);
        validator.setValidationListener(this);

        presenter = this.instanciarPresenter();
    }

    private RegistrarContract.Presenter instanciarPresenter() {
        return RegistrarContract.newPresenter().setContext(this).setView(this);
    }

    private void onRegistrar(View view) {
        validator.validate();
    }

    private void mostrarProgresBar(boolean estado){
        progressBar.setVisibility(estado ? View.VISIBLE : View.GONE);
    }

    @Override
    public void onValidationSucceeded() {
        Usuario usuaio = new Usuario();
        usuaio.setCorreo(txtRegistroCorreo.getText().toString());
        usuaio.setPassword(txtRegistroPassword.getText().toString());
        usuaio.setNombres(txtRegistroNombres.getText().toString());
        usuaio.setApellidos(txtRegistroApellidos.getText().toString());
        usuaio.setTelefono(txtRegistroTelefono.getText().toString());
        usuaio.setDni(txtRegistroDni.getText().toString());
        usuaio.setTipoUsuario(Usuario.CLIENTE);
        usuaio.setFechaRegistro(new Date());
        presenter.RegistrarUsuario(usuaio);
        this.mostrarProgresBar(true);
    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void seRegistroUsuario(Usuario usuario) {
        this.mostrarProgresBar(false);
        Toast.makeText(this, getResources().getText(R.string.registro_usuario_creado), Toast.LENGTH_LONG).show();
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(intent);
    }

    @Override
    public void errorRespuesta(String mensaje) {
        this.mostrarProgresBar(false);
        Toast.makeText(this, mensaje, Toast.LENGTH_LONG).show();
    }
}
