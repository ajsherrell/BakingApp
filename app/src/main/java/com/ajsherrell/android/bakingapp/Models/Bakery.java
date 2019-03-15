package com.ajsherrell.android.bakingapp.Models;

import android.os.Parcel;
import android.os.Parcelable;
import android.widget.TextView;

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
    private String image = NO_IMAGE_PROVIDED;

    private static final String NO_IMAGE_PROVIDED = null;

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

    public boolean hasImage() {
        return image != NO_IMAGE_PROVIDED;
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
