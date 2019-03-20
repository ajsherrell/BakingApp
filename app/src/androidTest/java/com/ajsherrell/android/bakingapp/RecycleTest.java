/*
 * Copyright 2019 AJ Sherrell
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
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
