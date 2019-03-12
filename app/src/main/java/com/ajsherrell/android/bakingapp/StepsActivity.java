package com.ajsherrell.android.bakingapp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.ajsherrell.android.bakingapp.Adapters.StepsFragmentPagerAdapter;
import com.ajsherrell.android.bakingapp.Models.Bakery;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsActivity extends AppCompatActivity {

    public static final String STEP_KEY = "step_key";
    public static final String STEP_SELECTED = "step_selected";

    private Bakery bakery;
    private int stepSelected;

    @BindView(R.id.bakery_viewpager)
    ViewPager bakeryViewpager;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_steps);
        ButterKnife.bind(this);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(STEP_KEY) && bundle.containsKey(STEP_SELECTED)) {
            bakery = bundle.getParcelable(STEP_KEY);
            stepSelected = bundle.getInt(STEP_SELECTED);
        } else {
            Toast.makeText(getApplicationContext(), "Bakery failure!", Toast.LENGTH_LONG).show();
            finish();
        }

        final ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(bakery.getName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        StepsFragmentPagerAdapter adapter = new StepsFragmentPagerAdapter(getApplicationContext(), bakery.getSteps(), getSupportFragmentManager());
        bakeryViewpager.setAdapter(adapter);
        bakeryViewpager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                if (actionBar != null) {
                    actionBar.setTitle(bakery.getSteps().get(i).getShortDescription());
                }
            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
        bakeryViewpager.setCurrentItem(stepSelected);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
