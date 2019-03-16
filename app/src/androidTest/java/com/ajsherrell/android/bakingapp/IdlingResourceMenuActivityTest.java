package com.ajsherrell.android.bakingapp;

import android.support.test.espresso.IdlingRegistry;
import android.support.test.espresso.IdlingResource;
import android.support.test.rule.ActivityTestRule;

import org.junit.After;
import org.junit.Before;
import org.junit.Rule;

public class IdlingResourceMenuActivityTest {
    protected AppTest appTest;
    protected IdlingResource idlingResource;

    @Rule
    public ActivityTestRule<MainActivity> mActivityTextRule =
            new ActivityTestRule<>(MainActivity.class);

    @Before
    public void registerIdlingResource() {
        appTest = (AppTest) mActivityTextRule.getActivity().getApplicationContext();
        idlingResource = appTest.getIdlingResource();
        IdlingRegistry.getInstance().register(idlingResource);
    }

    @After
    public void unregisterIdlingResource() {
        if (idlingResource != null) {
            IdlingRegistry.getInstance().unregister(idlingResource);
        }
    }

}
