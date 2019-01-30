package com.business.warthon.dao;

public interface InterfaceGeneral {
    @FunctionalInterface
    interface RespuestaSucces<T> {
        void onRespuestaSucces(T t);
    }

    @FunctionalInterface
    interface RespuestaError {
        void onRespuestaError(String mensaje);
    }

}
