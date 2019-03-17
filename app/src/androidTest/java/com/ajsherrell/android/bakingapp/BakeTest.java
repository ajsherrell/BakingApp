package com.ajsherrell.android.bakingapp;

import android.content.Context;
import android.support.test.espresso.intent.Intents;
import android.support.test.runner.AndroidJUnit4;

import com.ajsherrell.android.bakingapp.Models.Bakery;

import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.intent.Intents.intended;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasComponent;
import static android.support.test.espresso.intent.matcher.IntentMatchers.hasExtraWithKey;
import static android.support.test.espresso.matcher.ViewMatchers.isCompletelyDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.junit.Assert.assertNotNull;

@RunWith(AndroidJUnit4.class)
public class BakeTest extends IdlingResourceMenuActivityTest {

    @Test
    public void RvClickedIntentKey() {
        //has key?
        Intents.init();

        RecycleTest.BakeryRecycler(0);
        intended(hasExtraWithKey(BakeryActivity.BAKERY_KEY));
        Intents.release();
    }

    @Test
    public void RvClickedStepOpened() {
        RecycleTest.BakeryRecycler(0);

        onView(withId(R.id.ingredients_tv))
                .check(matches(isDisplayed()));

        onView(withId(R.id.bakery_step_list))
                .check(matches(isDisplayed()));
    }

    @Test
    public void RvClickedActivityFragmentOpened() {
        RecycleTest.BakeryRecycler(0);

        boolean twoPane = appTest.getResources().getBoolean(R.bool.isTwoPane);
        if (!twoPane) {
            Intents.init();
            RecycleTest.StepList(1);
            intended(hasComponent(StepsActivity.class.getName()));
            intended(hasExtraWithKey(StepsActivity.STEP_KEY));
            intended(hasExtraWithKey(StepsActivity.STEP_SELECTED));
            Intents.release();

            onView(withId(R.id.tablet_container))
                    .check(matches(isCompletelyDisplayed()));
        }
    }

    @Test
    public void WidgetButton() {
        appTest.getSharedPreferences(Constants.WidgetPref.PREFERENCE_NAME, Context.MODE_PRIVATE).edit()
                .clear()
                .commit();

        RecycleTest.BakeryRecycler(0);

        onView(withId(R.id.action_widget))
                .check(matches(isDisplayed()))
                .perform(click());

        Bakery bakery = Constants.WidgetPref.loadBake(appTest);
        assertNotNull(bakery);
    }

}