package com.business.warthon.dao;

import com.business.warthon.firebase.FireBaseFactory;

public abstract class DataFactory {

    public static final int FIREBASE = 1;

    public abstract DaoLogin getLoginDao();
    public abstract DaoCliente getClienteDao();

    public static DataFactory getFactory(int tipo) {
        switch (tipo) {
            case FIREBASE:
                return new FireBaseFactory();
            default:
                return null;
        }
    }
}
