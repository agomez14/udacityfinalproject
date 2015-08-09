package com.example.android.pantry;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import java.util.ArrayList;


public class addItemActivity extends Activity implements AdapterView.OnItemSelectedListener {
    Spinner spinner;
    Spinner spinner2;
    ArrayList<ArrayAdapter> adapterList;
    EditText quantity;
    Intent foodIntent;
    int location;
    String selectedFood;
    Button doneButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        spinner = (Spinner) findViewById(R.id.types);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.mainTypes, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(this);
        spinner2 = (Spinner) findViewById(R.id.subgenres);
        spinner2.setOnItemSelectedListener(this);
        doneButton = (Button) findViewById(R.id.done);
        doneButton.setVisibility(View.GONE);
        quantity = (EditText) findViewById(R.id.qunatity);
        quantity.setVisibility(View.GONE);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_add_item, menu);
        return true;
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
        adapterList = new ArrayList<ArrayAdapter>();
        adapterList = AdapterList.getArrayList(this);
        String selectedFoodType = spinner.getSelectedItem().toString();
        if (parent.getId() == (R.id.types)) {
            spinner2.setAdapter(adapterList.get(spinner.getSelectedItemPosition()));

        }
        if (parent.getId() == R.id.subgenres) {
            selectedFood = spinner2.getSelectedItem().toString();
            if(spinner2.getSelectedItemPosition()!= 0) {
                location = spinner2.getSelectedItemPosition();
                quantity.setVisibility(View.VISIBLE);
                doneButton.setVisibility(View.VISIBLE);
            }

        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    public void  sendFoodToList(View v) {
           // newFood = FoodLists.retrieveFood(selectedFoodType, selectedFood);
            //foodInfo = newFood.toString();
            String amount = quantity.getText().toString();
            String foodName = selectedFood;
            location = location-1;
            foodIntent = new Intent();
            foodIntent.putExtra("food", foodName);
            foodIntent.putExtra("pos",location);
            foodIntent.putExtra("quantity",amount);
            //foodIntent.putExtra("info", foodInfo);
            setResult(RESULT_OK, foodIntent);
            finish();



    }
}
