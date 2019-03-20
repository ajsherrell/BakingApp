package com.ajsherrell.android.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajsherrell.android.bakingapp.Constants;
import com.ajsherrell.android.bakingapp.GlideApp;
import com.ajsherrell.android.bakingapp.Models.Bakery;
import com.ajsherrell.android.bakingapp.R;
import com.ajsherrell.android.bakingapp.ViewHolders.BakeryViewHolder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.bitmap.CircleCrop;

import java.util.List;

public class BakeryAdapter extends RecyclerView.Adapter<BakeryViewHolder> {

    private static final String TAG = BakeryAdapter.class.getSimpleName();

    private Context context;
    private List<Bakery> bakery;
    private Constants.ClickListener.OnItemClickListener onItemClickListener;

    public BakeryAdapter(Context context, List<Bakery> bakery, Constants.ClickListener.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.bakery = bakery;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public BakeryViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.bakery_list, viewGroup, false);
        return new BakeryViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BakeryViewHolder bakeryViewHolder, final int i) {
        bakeryViewHolder.mBakeryNameTv.setText(bakery.get(i).getName());
        bakeryViewHolder.mServingsTv.setText(context.getString(R.string.serves) + bakery.get(i).getServings());

        if (bakery.get(i).getImage().isEmpty() || bakery.get(i).getImage() == null) {
            GlideApp.with(context)
                    .load(bakery.get(i).getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(new CircleCrop())
                    .placeholder(R.drawable.ic_action_name)
                    .error(R.drawable.ic_action_name)
                    .into(bakeryViewHolder.bakeryImage);
        } else {
            GlideApp.with(context)
                    .load(bakery.get(i).getImage())
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .transform(new CircleCrop())
                    .placeholder(R.drawable.ic_action_name)
                    .error(R.drawable.ic_action_name)
                    .into(bakeryViewHolder.bakeryImage);
        }

        bakeryViewHolder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (onItemClickListener != null) onItemClickListener.onItemClick(i);
                Log.d(TAG, "onClick: !!!!" + i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return bakery.size();
    }

}
