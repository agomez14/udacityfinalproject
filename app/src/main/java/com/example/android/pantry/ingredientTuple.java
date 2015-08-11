package com.example.android.pantry;

import com.parse.ParseClassName;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

/**
 * Created by elijahstaple on 8/11/15.
 */

@ParseClassName("ingredientTuple")
public class ingredientTuple extends ParseObject {
    private String id;
    private String quantity;

    public ingredientTuple() {}

    public ingredientTuple(String x, String y) {
        id = x;
        quantity = y;
        this.put("FoodId", id);
        this.put("quantity", quantity);
        this.saveInBackground();
    }

    public String getId() throws ParseException {
        this.fetchIfNeeded();
        return this.getString("FoodId");
    }

    public String getQuantity() {
        return this.getString("quantity");
    }
}
