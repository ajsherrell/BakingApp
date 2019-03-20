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
package com.ajsherrell.android.bakingapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import com.fasterxml.jackson.annotation.JsonProperty;

// used resource: https://github.com/FasterXML/jackson

public class Ingredients implements Parcelable {

    @JsonProperty("quantity")
    private int quantity;
    @JsonProperty("measure")
    private String measure;
    @JsonProperty("ingredient")
    private String ingredient;

    public Ingredients() {
        this.quantity = 0;
        this.measure = "";
        this.ingredient = "";
    }

    protected Ingredients(Parcel in) {
        this.quantity = in.readInt();
        this.measure = in.readString();
        this.ingredient = in.readString();
    }

    public static final Creator<Ingredients> CREATOR = new Creator<Ingredients>() {
        @Override
        public Ingredients createFromParcel(Parcel in) {
            return new Ingredients(in);
        }

        @Override
        public Ingredients[] newArray(int size) {
            return new Ingredients[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.quantity);
        parcel.writeString(this.measure);
        parcel.writeString(this.ingredient);
    }

    public int getQuantity() {
        return quantity;
    }

    public String getMeasure() {
        return measure;
    }

    public String getIngredient() {
        return ingredient;
    }

    @Override
    public String toString() {
        return "Ingredients{" +
                "quantity=" + quantity + "," + "\n" +
                "measure=" + measure + "," + "\n" +
                "ingredient=" + ingredient +
                "}";
    }
}
