package com.ajsherrell.android.bakingapp.ViewHolders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ajsherrell.android.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

// used resource: https://jakewharton.github.io/butterknife/

public class BakeryViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.bakery_name_tv)
    public TextView mBakeryNameTv;

    @BindView(R.id.servings_tv)
    public TextView mServingsTv;

    public BakeryViewHolder(View view) {
        super(view);
        ButterKnife.bind(this, view);
    }
}
