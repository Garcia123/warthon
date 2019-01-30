package com.business.warthon.dao;

import com.business.warthon.model.Cliente;

public interface DaoCliente extends InterfaceGeneral {
    void crearCliente(Cliente cliente, RespuestaSucces<Cliente> callBack, RespuestaError error);
}
