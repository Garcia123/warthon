package com.business.warthon.dao;

import android.content.Context;

import com.business.warthon.model.Usuario;

public interface DaoLogin extends InterfaceGeneral {
    void vificarSesion(RespuestaSucces<Boolean> respuesta);

    void iniciarSesionCorreoPassword(Context context, String correo, String password, RespuestaSucces<Boolean> callback, RespuestaError error);

    void cerrarSecion(RespuestaSucces<?> callback);

    void registrarUsuario(Context context, String correo, String password, RespuestaSucces<Usuario> callBack, RespuestaError error);
}
