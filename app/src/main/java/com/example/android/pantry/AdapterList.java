package com.example.android.pantry;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by barryjohnsonsmith on 7/29/15.
 */
public class AdapterList {
    static ArrayAdapter<CharSequence> adapter2;
    static ArrayAdapter<CharSequence> adapter3;
    static ArrayAdapter<CharSequence> adapter4;
    static ArrayAdapter<CharSequence> adapter5;
    static ArrayAdapter<CharSequence> adapter6;
    static ArrayAdapter<CharSequence> adapter7;
    static ArrayList<ArrayAdapter> adapterList;
    public static ArrayList<ArrayAdapter> getArrayList(Context context) {
        adapterList = new ArrayList<ArrayAdapter>();
        adapter2 = ArrayAdapter.createFromResource(context,
                R.array.meatTypes, android.R.layout.simple_spinner_item);
        adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter3 = ArrayAdapter.createFromResource(context,
                R.array.vegetables, android.R.layout.simple_spinner_item);
        adapter3.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter4 = ArrayAdapter.createFromResource(context,
                R.array.grains, android.R.layout.simple_spinner_item);
        adapter4.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter5 = ArrayAdapter.createFromResource(context,
                R.array.fruits, android.R.layout.simple_spinner_item);
        adapter5.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter6 = ArrayAdapter.createFromResource(context,
                R.array.sweets, android.R.layout.simple_spinner_item);
        adapter6.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapter7 = ArrayAdapter.createFromResource(context,
                R.array.dairy, android.R.layout.simple_spinner_item);
        adapter7.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        adapterList.add(adapter2);
        adapterList.add(adapter3);
        adapterList.add(adapter4);
        adapterList.add(adapter5);
        adapterList.add(adapter6);
        adapterList.add(adapter7);
        return adapterList;

    }

}
