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

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Toast;

import com.ajsherrell.android.bakingapp.Adapters.IngredientsAdapter;
import com.ajsherrell.android.bakingapp.Adapters.StepsAdapter;
import com.ajsherrell.android.bakingapp.Models.Bakery;
import com.ajsherrell.android.bakingapp.Widget.WidgetService;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BakeryActivity extends AppCompatActivity {
    public static final String BAKERY_KEY = "bakery_key";

    //binders
    @BindView(R.id.bakery_step_list)
    RecyclerView bakeryRecyclerView;

    @BindView(R.id.ingredients_list_rv)
    RecyclerView ingredientsRecyclerView;

    private Bakery bakery;

    // is two pane?
    private boolean twoPane;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bakeryBundle = getIntent().getExtras();
        if (bakeryBundle != null && bakeryBundle.containsKey(BAKERY_KEY)) {
            bakery = bakeryBundle.getParcelable(BAKERY_KEY);
        } else {
            Toast.makeText(getApplicationContext(), "Bakery failure!", Toast.LENGTH_LONG).show();
            finish();
        }

        setContentView(R.layout.activity_bakery);
        ButterKnife.bind(this);

        twoPane = getResources().getBoolean(R.bool.isTwoPane);
        if (twoPane) {
            if (savedInstanceState == null && !bakery.getSteps().isEmpty()) {
                makeStep(0);
            }
        }

        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setTitle(bakery.getName());
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        setRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setRecyclerView() {
        LinearLayoutManager bakeryLayoutManager = new LinearLayoutManager(this);
        bakeryRecyclerView.setLayoutManager(bakeryLayoutManager);
        bakeryRecyclerView.setAdapter(new StepsAdapter(bakery, new Constants.ClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                makeStep(position);
            }
        }));
        LinearLayoutManager ingredientsLayoutManager = new LinearLayoutManager(this);
        ingredientsRecyclerView.setLayoutManager(ingredientsLayoutManager);
        ingredientsRecyclerView.setNestedScrollingEnabled(false);
        ingredientsRecyclerView.setAdapter(new IngredientsAdapter(getApplicationContext(), bakery.getIngredients()));
    }

    private void makeStep(int position) {
        if (twoPane) {
            Bundle args = new Bundle();
            args.putParcelable(StepsFragment.STEPS_KEY, bakery.getSteps().get(position));
            StepsFragment fragment = new StepsFragment();
            fragment.setArguments(args);
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.tablet_container, fragment)
                    .commit();
        } else {
            Intent intent = new Intent(this, StepsActivity.class);
            intent.putExtra(StepsActivity.STEP_KEY, bakery);
            intent.putExtra(StepsActivity.STEP_SELECTED, position);
            startActivity(intent);
        }
    }

    // menu for widget
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.bakery_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_widget) {
            WidgetService.updateWidget(this, bakery);
            Toast.makeText(getApplicationContext(), String.format(getString(R.string.widget_added)), Toast.LENGTH_LONG).show();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }
}


