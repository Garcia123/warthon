package com.business.warthon.firebase;

import com.business.warthon.dao.DaoCliente;
import com.business.warthon.model.Cliente;
import com.google.firebase.firestore.FirebaseFirestore;

public class FireBaseCliente implements DaoCliente {

    FirebaseFirestore db = FirebaseFirestore.getInstance();

    @Override
    public void crearCliente(Cliente cliente, RespuestaSucces<Cliente> callBack, RespuestaError error) {
        db.collection(Constantes.Uri.CLIENTE).document(cliente.getUid()).set(cliente)
            .addOnSuccessListener(aViod -> callBack.onRespuestaSucces(cliente))
            .addOnFailureListener(ex -> error.onRespuestaError(ex.getMessage()));
    }
}
