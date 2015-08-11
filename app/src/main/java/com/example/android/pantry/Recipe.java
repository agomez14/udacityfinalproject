package com.example.android.pantry;

/**
 * Created by elijahstaple on 8/3/15.
 */
public class Recipe {
    private String Name;
    private String Picture;
    private String Description;
    private String Recipe;
    private int Meal;

    public Recipe(String name, String description,String recipe, int meal,String pic) {
        Name = name;
        Picture = pic;
        Description = description;
        Recipe = recipe;
        Meal = meal;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPicture() {
        return Picture;
    }

    public void setPicture(String picture) {
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
    public String getRecipe () {
        return Recipe;
    }

    public void setRecipe(String recipe) {
        Recipe = recipe;
    }
}

