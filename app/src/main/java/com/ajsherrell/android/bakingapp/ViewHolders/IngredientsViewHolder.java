package com.ajsherrell.android.bakingapp.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ajsherrell.android.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IngredientsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.ingredients_tv)
    public TextView mIngredientsTv;

    public IngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
