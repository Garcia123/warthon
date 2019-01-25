package com.business.warthon.login;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.business.warthon.R;

public class LoginActivity extends AppCompatActivity {

    TextView txtLinkRegistrar;
    Button btnIngesar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.txtLinkRegistrar = findViewById(R.id.txtLinkRegistrar);
        this.txtLinkRegistrar.setOnClickListener(this::registrarUsurio);

        this.btnIngesar = findViewById(R.id.btnIngesar);
        this.btnIngesar.setOnClickListener(this::ingresarSistema);
    }

    private void ingresarSistema(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    private void registrarUsurio(View view) {
        Intent intent = new Intent(this, RegistrarActivity.class);
        startActivity(intent);
    }
}
