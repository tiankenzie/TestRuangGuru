package com.android.ruangguru.base.domain;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Prabowo Agustian on 11/20/17.
 */

public class RequestParams {
    public static final RequestParams EMPTY = RequestParams.create();

    private final Map<String, Object> parameters = new HashMap<>();

    private RequestParams() {
    }

    public static RequestParams create() {
        return new RequestParams();
    }

    public void putInt(String key, int value) {
        parameters.put(key, value);
    }

    public void putString(String key, String value) {
        parameters.put(key, value);
    }

    public void putBoolean(String key, boolean value) {
        parameters.put(key, value);
    }

    public void putLong(String key, long value) {
        parameters.put(key, value);
    }

    public void putObject(String key, Object object) {
        parameters.put(key, object);
    }

    public int getInt(String key, int defaultValue) {
        final Object object = parameters.get(key);
        if (object == null) {
            return defaultValue;
        }
        try {
            return (int) object;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    public String getString(String key, String defaultValue) {
        final Object object = parameters.get(key);
        if (object == null) {
            return defaultValue;
        }
        try {
            return (String) object;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    public boolean getBoolean(String key, boolean defaultValue) {
        final Object object = parameters.get(key);
        if (object == null) {
            return defaultValue;
        }
        try {
            return (Boolean) object;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    public long getLong(String key, long defaultValue){
        final Object object = parameters.get(key);
        if (object == null) {
            return defaultValue;
        }
        try {
            return (Long) object;
        } catch (ClassCastException e) {
            return defaultValue;
        }
    }

    public Object getObject(String key) {
        return parameters.get(key);
    }

    public Map<String, Object> getParameters() {
        return parameters;
    }
}
