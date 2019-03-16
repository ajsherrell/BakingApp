package com.ajsherrell.android.bakingapp;

import android.app.Application;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.VisibleForTesting;
import android.support.test.espresso.IdlingResource;

public class AppTest extends Application {

    @Nullable
    private SimpleIdlingResource simpleIdlingResource;

    @VisibleForTesting
    @NonNull
    private IdlingResource idlingResource() {
        if (simpleIdlingResource == null) {
            simpleIdlingResource = new SimpleIdlingResource();
        }
        return simpleIdlingResource;
    }

    public AppTest() {
        if (BuildConfig.DEBUG) {
            idlingResource();
        }
    }

    public void setIdleState(boolean state) {
        if (simpleIdlingResource != null) {
            simpleIdlingResource.setIdleState(state);
        }
    }

    @Nullable
    public IdlingResource getIdlingResource() {
        return simpleIdlingResource;
    }

}
