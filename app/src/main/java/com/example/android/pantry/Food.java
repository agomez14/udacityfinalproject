package com.example.android.pantry;

/**
 * Created by barryjohnsonsmith on 7/28/15.
 */
public class Food {
    private String name;
    private double calories;
    private double quantity;
    private double protein;
    private double fats;
    private String type;
    public Food(String name, double calories, double quantity, double protien, double fats, String type){
        this.name = name;
        this.calories = calories;
        this.quantity = quantity;
        this.protein = protien;
        this.fats = fats;
        this.type = type;
    }
    public String getName(){
        return name;
    }
    public double getCalories() {
        return calories;
    }
    public double getQuantity(){
        return quantity;
    }
    public double getProtein() {
        return protein;
    }
    public double getFats() {
        return fats;
    }
    public String getType() {
        return type;
    }
    public String toString() {
            return String.format("Name:%s%n Calories:%.2f%n Quantity:%.2f ounces %n Protein %.2f grams%n "
                            + "Sugar:%.2f grams %n Type:%s",
                    name, calories, quantity, protein, fats, type);

    }
}
