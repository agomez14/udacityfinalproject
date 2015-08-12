package com.example.android.pantry;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;

import com.example.android.pantry.welcome_screen.WelcomeSplash;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elijahstaple on 8/4/15.
 */
public class PantryActivity extends AppCompatActivity implements FetchFoodTask.Callbacks {//implements AdapterView.OnItemClickListener {

    private String selectedFood;
    private Intent intentAddFood;
    private int colorButton;
    private ArrayList<ImageItem> pictures;
    private ArrayList<String> ingredients = new ArrayList<String>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_container);
        if (ParseUser.getCurrentUser() == null) {
            Log.d("onCreate","Got to here!");
            Intent intent = new Intent(PantryActivity.this, WelcomeSplash.class);
            startActivity(intent);
            finish();
        } else {
            getSupportFragmentManager().beginTransaction()
                    .add(R.id.fragment_container, new PantryFragment(), "PantryFragment")
                    .commit();
        }
//        userPantryItems = ParseUser.getCurrentUser().getList("PantryIngredientFoodCodes");
//        gridView = (GridView) findViewById(R.id.listFoods);
//        recipeButton = (Button) findViewById(R.id.recipesButton);
//        doneButton = (Button) findViewById(R.id.done);
//        if (savedInstanceState != null) {
//            pictures = savedInstanceState.getParcelableArrayList("pictures");
//        } else {
//            pictures = getData();
//        }
//        gridAdapter = new GridViewAdapter(this, R.layout.grid_item, pictures);
//        if (userPantryItems != null) {
//            for (ingredientTuple s : userPantryItems) {
//                try {
//                    new FetchFoodTask(this, gridAdapter).execute(s.getId(),
//                            Integer.toString(2), s.getQuantity());
//                } catch (ParseException e) {
//                    e.printStackTrace();
//                }
//            }
//        }
//        gridView.setAdapter(gridAdapter);
//        gridView.setOnItemClickListener(this);
//        }
    }
//    public void onItemClick (AdapterView< ? > parent, View v,int position, long id){
//        if(colorButton > 0 && colorButton!=0){
//            doneButton.setVisibility(View.VISIBLE);
//            ImageItem item = (ImageItem) parent.getItemAtPosition(position);
//            ingredients.add(item.getTitle());
//
//        }
//        else {
////            Intent intent = new Intent(this, DetailsFragment.class);
//            ImageItem item = (ImageItem) parent.getItemAtPosition(position);
////            ByteArrayOutputStream stream = new ByteArrayOutputStream();
////            Bitmap bmp = item.getImage();
////            bmp.compress(Bitmap.CompressFormat.PNG, 0, stream);
////            byte[] bytes = stream.toByteArray();
////            intent.putExtra("image", bytes);
////            intent.putExtra("title", item.getTitle());
////            intent.putExtra("info", item.getDescription());
////            //intent.putExtra("image", item.getImage());
////
////            //Start details activity
////            Log.d("IntentStuff", "Starting detailActivity");
////            startActivity(intent);
//            DetailsFragment fragment = DetailsFragment.newInstance(item);
//            getSupportFragmentManager().beginTransaction().add(fragment, "DetailFragment").commit();
//                    //.show(fragment).commit();
//        }
//    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
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

    public void addFood(View v) {
        intentAddFood = new Intent(this, addItemActivity.class);
        startActivityForResult(intentAddFood, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            selectedFood = data.getStringExtra("food");
            int location = data.getIntExtra("pos", 0);
            String amount = data.getStringExtra("quantity");
            addFoodPic(location, selectedFood, amount);

            //addFoodPic(foodInfo,selectedFood);
        }
    }

    //    public ArrayList<ImageItem> getData() {
//        ArrayList<ImageItem> imageArray = new ArrayList<ImageItem>();
//        TypedArray imgs = getResources().obtainTypedArray(R.array.image_id);
//        String[] foodNames = new String[5];
//        foodNames[0] = "chicken";
//        foodNames[1] = "Pork";
//        foodNames[2] = "Beef";
//        foodNames[3] = "Turkey";
//        foodNames[4] = "fish";
//        for (int i = 0; i < imgs.length(); i++) {
//            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
//            imageArray.add(new ImageItem(bitmap, foodNames[i],""));
//        }
//        return imageArray;
//    }

    public void addFoodPic(int location, String selectedFood, String amount) {
        String position = Integer.toString(location);
        GridViewAdapter gridAdapter = ((PantryFragment) getSupportFragmentManager()
                .findFragmentByTag("PantryFragment"))
                .getAdapter();
        SearchFoodTask searchTask = new SearchFoodTask(this, gridAdapter);
        if (selectedFood.equals("Chicken")) {
            searchTask.execute(selectedFood, "0500", position, amount);
        } else if (selectedFood.equals("Pork")) {
            searchTask.execute(selectedFood, "1000", position, amount);
        } else if (selectedFood.equals("Beef")) {
            searchTask.execute(selectedFood, "1300", position, amount);
        } else if (selectedFood.equals("Turkey")) {
            searchTask.execute(selectedFood, "0500", position, amount);
        } else if (selectedFood.equals("Fish")) {
            searchTask.execute(selectedFood, "1500", position, amount);
        }
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        ArrayList<ImageItem> pics = pictures;
        savedInstanceState.putParcelableArrayList("pictures", pics);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }

    @Override
    public void fetchDone(Bitmap bitmap) {
        ((PantryFragment) getSupportFragmentManager().findFragmentByTag("PantryFragment"))
                .getAdapter()
                .add(new ImageItem(bitmap, "Image# ", "Foods"));
    }
}

