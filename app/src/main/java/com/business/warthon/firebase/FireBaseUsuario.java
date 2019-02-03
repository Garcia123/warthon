package com.business.warthon.firebase;

import android.content.Context;

import com.business.warthon.dao.DaoUsuario;
import com.business.warthon.model.Usuario;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseUsuario implements DaoUsuario {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void registrarUsuario(Context context, Usuario usuario, RespuestaSucces<Usuario> callBack, RespuestaError error) {
        db.collection(Constantes.Uri.USUARIO).document(usuario.getUid()).set(usuario)
            .addOnSuccessListener(aViod -> callBack.onRespuestaSucces(usuario))
            .addOnFailureListener(ex -> error.onRespuestaError(ex.getMessage()));
    }
}
