package com.business.warthon.utiles;

public interface LogWarthon {
    static LogWarthon newIntance(String tag) {
        return new LogWarthonImpl(tag);
    }

    void verbose(String message);
    void debug(String message);
    void info(String message);
    void info(String message, Object... args);
    void warn(String message);
    void error(String message);

    void verbose(String message,Throwable t);
    void debug(String message,Throwable t);
    void info(String message,Throwable t);
    void warn(String message,Throwable t);
    void error(String message,Throwable t);
}
