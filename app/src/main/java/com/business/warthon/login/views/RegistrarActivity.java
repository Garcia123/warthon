package com.business.warthon.login.views;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.business.warthon.R;
import com.mobsandgeeks.saripaar.ValidationError;
import com.mobsandgeeks.saripaar.Validator;
import com.mobsandgeeks.saripaar.annotation.Email;
import com.mobsandgeeks.saripaar.annotation.NotEmpty;
import com.mobsandgeeks.saripaar.annotation.Pattern;

import java.util.List;

public class RegistrarActivity extends AppCompatActivity implements Validator.ValidationListener{

    @Email(sequence = 1, messageResId = R.string.registro_correo_validar)
    EditText txtRegistroCorreo;

    @NotEmpty(sequence = 2, messageResId = R.string.registro_passwor_validar)
    EditText txtRegistroPassword;

    @NotEmpty(sequence = 3, messageResId = R.string.registro_nombres_validar)
    EditText txtRegistroNombres;

    @NotEmpty(sequence = 4, messageResId = R.string.registro_apellidos_validar)
    EditText txtRegistroApellidos;

    @Pattern(sequence = 5, regex = "^9[0-9]{9}$", messageResId =R.string.registro_telefono_validar)
    EditText txtRegistroTelefono;

    @Pattern(sequence = 6, regex = "^[1-9][0-9]{8}$", messageResId =R.string.registro_dni_validar)
    EditText txtRegistroDni;

    Button registrar;

    Validator validator;

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

        validator = new Validator(this);
        validator.setValidationListener(this);
    }

    private void onRegistrar(View view) {
        validator.validate();
    }

    @Override
    public void onValidationSucceeded() {

    }

    @Override
    public void onValidationFailed(List<ValidationError> errors) {
        for (ValidationError error : errors) {
            View view = error.getView();
            String message = error.getCollatedErrorMessage(this);
            // Display error messages ;)
            if (view instanceof EditText) {
                ((EditText) view).setError(message);
            } else {
                Toast.makeText(this, message, Toast.LENGTH_LONG).show();
            }
        }
    }

}
