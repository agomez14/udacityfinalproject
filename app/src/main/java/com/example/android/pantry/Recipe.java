package com.example.android.pantry;

import android.graphics.drawable.Drawable;

/**
 * Created by elijahstaple on 8/3/15.
 */
public class Recipe {
    private String Name;
    private int Picture;
    private String Description;
    private int Meal;

    public Recipe(String name, String description, int meal) {
        Name = name;
        Picture = R.drawable.demopic;
        Description = description;
        Meal = meal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPicture() {
        return Picture;
    }

    public void setPicture(int picture) {
        Picture = picture;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public int getMeal() {
        return Meal;
    }

    public void setMeal(int meal) {
        Meal = meal;
    }
}
