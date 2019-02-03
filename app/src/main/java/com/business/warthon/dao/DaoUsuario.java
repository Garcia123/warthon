package com.business.warthon.dao;

import android.content.Context;

import com.business.warthon.model.Usuario;

public interface DaoUsuario extends InterfaceGeneral{

     void registrarUsuario(Context context, Usuario usuario, RespuestaSucces<Usuario> callBack, RespuestaError error);

}
