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
        String recipeIngredients = ingredientsString + " " + measurement + "\n";
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
                    measurement = "grams" + " " + String.valueOf(quantity);
                } else {
                    measurement = "gram" + " " + String.valueOf(quantity);
                }
                return measurement;
            case "UNIT":
                if (quantity > 1) {
                    measurement = "units" + " " + String.valueOf(quantity);
                } else {
                    measurement = "unit" + " " + String.valueOf(quantity);
                }
                return measurement;
            case "TBLSP":
                if (quantity > 1.0) {
                    measurement = "tablespoons" + " " + String.valueOf(quantity);
                } else {
                    measurement = "tablespoon" + " " + String.valueOf(quantity);
                }
                return measurement;
            case "TSP":
                if (quantity > 1.0) {
                    measurement = "teaspoons" + " " + String.valueOf(quantity);
                } else {
                    measurement = "teaspoon" + " " + String.valueOf(quantity);
                }
                return measurement;
            case "CUP":
                if (quantity > 1.0) {
                    measurement = "cups" + " " + String.valueOf(quantity);
                } else {
                    measurement = "cup" + " " + String.valueOf(quantity);
                }
                return measurement;
            case "K":
                if (quantity > 1.0) {
                    measurement = "kilograms" + " " + String.valueOf(quantity);
                } else {
                    measurement = "kilogram" + " " + String.valueOf(quantity);
                }
                return measurement;
            case "OZ":
                if (quantity > 1.0) {
                    measurement = "ounces" + " " + String.valueOf(quantity);
                } else {
                    measurement = "ounce" + " " + String.valueOf(quantity);
                }
                return measurement;
            default:
                return measure + quantity;
        }
    }

}
