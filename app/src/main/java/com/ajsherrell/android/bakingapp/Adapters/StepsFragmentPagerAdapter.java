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
