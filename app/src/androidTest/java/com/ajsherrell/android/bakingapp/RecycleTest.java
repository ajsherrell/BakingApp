package com.ajsherrell.android.bakingapp;

import android.support.test.espresso.contrib.RecyclerViewActions;


import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

public class RecycleTest {

    public static void BakeryRecycler(int position) {
        onView(withId(R.id.bakery_recycler_view))
                .perform(RecyclerViewActions.actionOnItemAtPosition(position, click()));
    }

    public static void StepList(int step) {
        onView(withId(R.id.bakery_step_list))
                .perform(RecyclerViewActions.actionOnItemAtPosition(step, click()));
    }

}
