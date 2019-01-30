package com.business.warthon.firebase;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.NonNull;

import com.business.warthon.dao.DaoLogin;
import com.business.warthon.model.Cliente;
import com.business.warthon.model.Usuario;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class FireBaseLogin implements DaoLogin {

    @Override
    public void vificarSesion(RespuestaSucces<Boolean> respuesta) {
        respuesta.onRespuestaSucces(FirebaseAuth.getInstance().getCurrentUser() != null);
    }

    @Override
    public void iniciarSesionCorreoPassword(Context context, String correo, String password, RespuestaSucces<Boolean> callback) {

        FirebaseAuth.getInstance().signInWithEmailAndPassword(correo,password)
            .addOnCompleteListener((Activity)context, new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    callback.onRespuestaSucces(task.isSuccessful());
                }
            });
    }

    @Override
    public void cerrarSecion(RespuestaSucces<?> callback) {
        FirebaseAuth.getInstance().signOut();
        callback.onRespuestaSucces(null);
    }

    @Override
    public void registrarUsuario(Context context, String correo, String password, RespuestaSucces<Usuario> callBack, RespuestaError error) {
        FirebaseAuth.getInstance().createUserWithEmailAndPassword(correo,password)
            .addOnCompleteListener((Activity)context,new OnCompleteListener<AuthResult>(){
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()){
                        Usuario user = new Cliente();
                        user.setUid(FirebaseAuth.getInstance().getCurrentUser().getUid());
                        callBack.onRespuestaSucces(user);
                    }else {
                        error.onRespuestaError(task.getException().getMessage());
                    }
                }
            });
    }
}














