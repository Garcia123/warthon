package com.business.warthon.firebase;

import com.business.warthon.dao.DaoCliente;
import com.business.warthon.dao.DataFactory;
import com.business.warthon.dao.DaoLogin;

public class FireBaseFactory extends DataFactory {
    @Override
    public DaoLogin getLoginDao() {
        return new FireBaseLogin();
    }

    @Override
    public DaoCliente getClienteDao() {
        return new FireBaseCliente();
    }
}
