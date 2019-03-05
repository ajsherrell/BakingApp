package com.ajsherrell.android.bakingapp.Utils;

// used resource: https://code.tutsplus.com/tutorials/getting-started-with-retrofit-2--cms-27792

public interface NetworkApiCallback<T> {
    void onResponse(T result);
    void onCancel();
}
