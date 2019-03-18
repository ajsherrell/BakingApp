package com.ajsherrell.android.bakingapp.Adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ajsherrell.android.bakingapp.Models.Ingredients;
import com.ajsherrell.android.bakingapp.R;
import com.ajsherrell.android.bakingapp.ViewHolders.IngredientsViewHolder;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsViewHolder> {

    private static final String TAG = IngredientsViewHolder.class.getSimpleName();
    private Context context;
    private List<Ingredients> ingredients;

    public IngredientsAdapter(Context context, List<Ingredients> ingredients) {
        this.context = context;
        this.ingredients = ingredients;
    }

    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.ingredients_list, viewGroup, false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder ingredientsViewHolder, final int i) {
        ingredientsViewHolder.mIngredientsTv.setText(ingredientsString(ingredientsViewHolder.getAdapterPosition()));
    }

    public String ingredientsString(int position) {
        Ingredients ingList = ingredients.get(position);
        String ingredientsString = ingList.getIngredient();
        String measure = ingList.getMeasure();
        int quantity = ingList.getQuantity();
        String measurement = trueMeasure(measure, quantity);
        String recipeIngredients = ingredientsString + " " + String.valueOf(measurement) + "\n";
        return recipeIngredients;
    }

    @Override
    public int getItemCount() {
        return ingredients == null ? 0 : ingredients.size();
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
                return measure + quantity;
        }
    }

}
