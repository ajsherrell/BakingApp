package com.ajsherrell.android.bakingapp.Models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

public class Bakery implements Parcelable {

    private int id;
    private String name;
    private List<Ingredients> ingredients;
    private List<Steps> steps;
    private int servings;

    public Bakery(int id, String name, List<Ingredients> ingredients, List<Steps> steps,
                  int servings) {
        this.id = id;
        this.name = name;
        this.ingredients = ingredients;
        this.steps = steps;
        this.servings = servings;
    }

    protected Bakery(Parcel in) {
        id = in.readInt();
        name = in.readString();
        ingredients = (List<Ingredients>) in.readArrayList((ClassLoader) ingredients);
        steps = (List<Steps>) in.readArrayList((ClassLoader) steps);
        servings = in.readInt();
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
        parcel.writeInt(id);
        parcel.writeString(name);
        parcel.writeList(ingredients);
        parcel.writeList(steps);
        parcel.writeInt(servings);
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
}
