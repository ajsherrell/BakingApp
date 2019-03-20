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

import java.util.ArrayList;
import java.util.List;

// used resource: https://github.com/FasterXML/jackson

public class Bakery implements Parcelable {

    @JsonProperty("id")
    private int id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("ingredients")
    private List<Ingredients> ingredients;
    @JsonProperty("steps")
    private List<Steps> steps;
    @JsonProperty("servings")
    private int servings;
    @JsonProperty("image")
    private String image;


    public Bakery() {
        this.id = 0;
        this.name = "";
        this.ingredients = new ArrayList<>();
        this.steps = new ArrayList<>();
        this.servings = 0;
        this.image = "";
    }

    protected Bakery(Parcel in) {
        this.id = in.readInt();
        this.name = in.readString();
        this.ingredients = new ArrayList<>();
        in.readList(this.ingredients, Ingredients.class.getClassLoader());
        this.steps = new ArrayList<>();
        in.readList(this.steps, Steps.class.getClassLoader());
        this.servings = in.readInt();
        this.image = in.readString();
    }

    public static final Creator<Bakery> CREATOR = new Creator<Bakery>() {
        @Override
        public Bakery createFromParcel(Parcel in) {
            return new Bakery(in);
        }

        @Override
        public Bakery[] newArray(int size) {
            return new Bakery[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(this.id);
        parcel.writeString(this.name);
        parcel.writeList(this.ingredients);
        parcel.writeList(this.steps);
        parcel.writeInt(this.servings);
        parcel.writeString(this.image);
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public List<Ingredients> getIngredients() {
        return ingredients;
    }

    public List<Steps> getSteps() {
        return steps;
    }

    public int getServings() {
        return servings;
    }

    public String getImage() {
        return image;
    }

    @Override
    public String toString() {
        return "Bakery{" +
                "id=" + id + "," + "\n" +
                "name=" + name + "," + "\n" +
                "ingredients=" + ingredients + "," + "\n" +
                "steps=" + steps + "," + "\n" +
                "servings=" + servings +
                "}";
    }
}
