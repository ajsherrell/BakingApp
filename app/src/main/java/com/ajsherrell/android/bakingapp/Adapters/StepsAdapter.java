package com.ajsherrell.android.bakingapp.Adapters;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajsherrell.android.bakingapp.Constants;
import com.ajsherrell.android.bakingapp.Models.Bakery;
import com.ajsherrell.android.bakingapp.R;
import com.ajsherrell.android.bakingapp.ViewHolders.StepsViewHolder;


public class StepsAdapter extends RecyclerView.Adapter<StepsViewHolder> {

    private Bakery bakery;
    private Constants.ClickListener.OnItemClickListener onItemClickListener;

    public StepsAdapter(Bakery bakery, Constants.ClickListener.OnItemClickListener onItemClickListener) {
        this.bakery = bakery;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public StepsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.steps_list_item, viewGroup, false);
            return new StepsViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull StepsViewHolder viewHolder, final int i) {
            StepsViewHolder stepsViewHolder = (StepsViewHolder) viewHolder;
            stepsViewHolder.mStepsIdNumberTv.setText(String.valueOf(i+1) + ".");
            stepsViewHolder.mStepsShortDescriptionTv.setText(bakery.getSteps().get(i).getShortDescription());

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (onItemClickListener != null) onItemClickListener.onItemClick(i);
                }
            });
    }

    @Override
    public int getItemCount() {
        return bakery.getSteps().size();
    }

}
