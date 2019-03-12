package com.ajsherrell.android.bakingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.ajsherrell.android.bakingapp.Adapters.StepsAdapter;
import com.ajsherrell.android.bakingapp.Models.Bakery;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BakeryActivity extends AppCompatActivity {
    public static final String BAKERY_KEY = "bakery_key";

    //binders
    @BindView(R.id.bakery_step_list)
    RecyclerView recyclerView;

    // is two pane?
    private boolean twoPane;

    private Bakery bakery;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle bundle = getIntent().getExtras();
        if (bundle != null && bundle.containsKey(BAKERY_KEY)) {
            bakery = bundle.getParcelable(BAKERY_KEY);
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
        setRecyclerView();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    private void setRecyclerView() {
        recyclerView.setAdapter(new StepsAdapter(bakery, new Constants.ClickListener.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                makeStep(position);
            }
        }));
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

    // todo widget menu
}


