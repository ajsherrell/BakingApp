package com.ajsherrell.android.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajsherrell.android.bakingapp.Constants;
import com.ajsherrell.android.bakingapp.Models.Ingredients;
import com.ajsherrell.android.bakingapp.R;
import com.ajsherrell.android.bakingapp.ViewHolders.IngredientsViewHolder;

import java.util.Locale;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    private static final String TAG = IngredientsViewHolder.class.getSimpleName();
    private Context context;
    private Ingredients ingredients;
    private Constants.ClickListener.OnItemClickListener onItemClickListener;

    public IngredientsAdapter(Context context, Ingredients ingredients, Constants.ClickListener.OnItemClickListener onItemClickListener) {
        this.context = context;
        this.ingredients = ingredients;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ingredients_list_item, viewGroup, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder ingredientsViewHolder, final int i) {
        String measure = ingredients.getMeasure();
        int quantity = ingredients.getQuantity();
        String measurement = trueMeasure(measure, quantity);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(String.format(Locale.getDefault(), ingredients.getIngredient(), measurement));
        stringBuilder.append("\n");
        ingredientsViewHolder.mIngredientsTv.setText(stringBuilder);
    }

    @Override
    public int getItemCount() {
        return ingredients.hashCode();
    }

    private String trueMeasure(String measure, int quantity) {
        String measurement;

        switch (measure) {
            case "G":
                if (quantity > 1.0) {
                    measurement = "grams";
                } else {
                    measurement = "gram";
                }
                return measurement;
            case "UNIT":
                measurement = "";
                return measurement;
            case "TBLSP":
                if (quantity > 1.0) {
                    measurement = "tablespoons";
                } else {
                    measurement = "tablespoon";
                }
                return measurement;
            case "TSP":
                if (quantity > 1.0) {
                    measurement = "teaspoons";
                } else {
                    measurement = "teaspoon";
                }
                return measurement;
            case "CUP":
                if (quantity > 1.0) {
                    measurement = "cups";
                } else {
                    measurement = "cup";
                }
                return measurement;
            case "K":
                if (quantity > 1.0) {
                    measurement = "kilograms";
                } else {
                    measurement = "kilogram";
                }
                return measurement;
            case "OZ":
                if (quantity > 1.0) {
                    measurement = "ounces";
                } else {
                    measurement = "ounce";
                }
                return measurement;
            default:
                return measure;
        }
    }

}