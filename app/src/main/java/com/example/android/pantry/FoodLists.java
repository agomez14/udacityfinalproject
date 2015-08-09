package com.example.android.pantry;

import java.util.ArrayList;

/**
 * Created by barryjohnsonsmith on 7/31/15.
 */
public class FoodLists {

    public static Food retrieveFood(String foodType, String selectedItem){
        Food chosenFood = new Food("",0,0,0,0,"");
        ArrayList<Food> meats = new ArrayList<Food>();
        ArrayList<Food> vegetables =  new ArrayList<Food>();
        ArrayList<Food> fruit = new ArrayList<Food>();
        ArrayList<Food> sweets = new ArrayList<Food>();
        ArrayList<Food> grain = new ArrayList<Food>();
        ArrayList<Food> dairy = new ArrayList<Food>();
        meats.add(new Food("Chicken",260, 12.8,54.5,2.9,"Meat"));
        meats.add(new Food("Pork",127,3,24,3,"Meat"));
        meats.add(new Food("Turkey",29,1,5,0,"Meat"));
        meats.add(new Food("Beef",123,3,21,4,"Meat"));
        meats.add(new Food("Fish",70,3,15,0,"Meat"));
        if(foodType.equals("Meat")){
            for(Food f: meats){
                if(f.getName().equals(selectedItem)){
                    chosenFood = f;
                    break;
                }
            }
        }
        return chosenFood;
    }

}
