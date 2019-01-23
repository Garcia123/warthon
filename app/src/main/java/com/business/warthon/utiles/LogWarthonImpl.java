package com.business.warthon.utiles;

import android.util.Log;

public class LogWarthonImpl implements LogWarthon {
    private String tag;

    public LogWarthonImpl(String tag) {
        this.tag = "warthon-proyecto"+ tag;
    }

    @Override
    public void verbose(String message) {
        Log.v(tag, message);
    }

    @Override
    public void debug(String message) {
        Log.d(tag, message);
    }

    @Override
    public void info(String message) {
        Log.i(tag, message);
    }

    @Override
    public void warn(String message) {
        Log.w(tag, message);
    }

    @Override
    public void error(String message) {
        Log.e(tag, message);
    }

    @Override
    public void verbose(String message, Throwable t) {
        Log.v(tag, message, t);
    }

    @Override
    public void debug(String message, Throwable t) {
        Log.d(tag, message, t);
    }

    @Override
    public void info(String message, Throwable t) {
        Log.i(tag, message, t);
    }

    @Override
    public void warn(String message, Throwable t) {
        Log.w(tag, message, t);
    }

    @Override
    public void error(String message, Throwable t) {
        Log.e(tag, message, t);
    }

    @Override
    public void info(String message, Object... args) {
        String smg = String.format(message, args);
        Log.i(tag, smg);
    }
}
