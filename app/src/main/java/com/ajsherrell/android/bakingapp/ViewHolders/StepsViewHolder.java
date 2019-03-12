package com.ajsherrell.android.bakingapp.ViewHolders;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.ajsherrell.android.bakingapp.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class StepsViewHolder extends RecyclerView.ViewHolder {

    @BindView(R.id.steps_id_number_tv)
    public TextView mStepsIdNumberTv;

    @BindView(R.id.steps_short_description_tv)
    public TextView mStepsShortDescriptionTv;

    public StepsViewHolder(@NonNull View itemView) {
        super(itemView);
        ButterKnife.bind(this, itemView);
    }
}
