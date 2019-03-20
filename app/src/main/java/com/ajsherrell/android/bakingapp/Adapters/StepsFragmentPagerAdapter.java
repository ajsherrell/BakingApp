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
package com.ajsherrell.android.bakingapp.Adapters;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

import com.ajsherrell.android.bakingapp.Models.Steps;
import com.ajsherrell.android.bakingapp.StepsFragment;

import java.util.List;

public class StepsFragmentPagerAdapter extends FragmentPagerAdapter {

    private Context context;
    private List<Steps> steps;

    public StepsFragmentPagerAdapter(Context context, List<Steps> steps, FragmentManager fm) {
        super(fm);
        this.context = context;
        this.steps = steps;
    }

    @Override
    public Fragment getItem(int i) {
        Bundle args = new Bundle();
        args.putParcelable(StepsFragment.STEPS_KEY, steps.get(i));
        StepsFragment fragment = new StepsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public int getCount() {
        return steps.size();
    }
}
