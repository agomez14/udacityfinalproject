package com.example.android.pantry;

import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import com.parse.ParseUser;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by elijahstaple on 8/4/15.
 */
public class MainActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    private String selectedFood;
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private Intent intentAddFood;
    private ArrayList<ImageItem> pictures;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Determine whether the current user is an anonymous user
//        if (ParseAnonymousUtils.isLinked(ParseUser.getCurrentUser())) {
//            // If user is anonymous, send the user to LoginSignupActivity.class
//            Intent intent = new Intent(MainActivity.this,
//                    LoginSignupActivity.class);
//            startActivity(intent);
//            finish();
//        } else {
            // If current user is NOT anonymous user
            // Get current user data from Parse.com
            ParseUser currentUser = ParseUser.getCurrentUser();
            if (currentUser != null) {
                // Send logged in users to Welcome.class
                Intent intent = new Intent(MainActivity.this, RecipeListActivity.class);
                startActivity(intent);
                finish();
            } else {
                // Send user to LoginSignupActivity.class
                Intent intent = new Intent(MainActivity.this,
                        LoginSignupActivity.class);
                startActivity(intent);
                finish();
            }
        gridView = (GridView) findViewById(R.id.listFoods);
        if (savedInstanceState != null) {
            pictures = savedInstanceState.getParcelableArrayList("pictures");
        } else {
            pictures = getData();
        }
        setContentView(R.layout.activity_main);
        gridAdapter = new GridViewAdapter(this, R.layout.grid_item, pictures);
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(this);
//        }
    }
    public void onItemClick (AdapterView< ? > parent, View v,int position, long id){
        Intent intent = new Intent(this,DetailsActivity.class);
        ImageItem item = (ImageItem) parent.getItemAtPosition(position);
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        Bitmap bmp = item.getImage();
        bmp.compress(Bitmap.CompressFormat.PNG, 100, stream);
        byte[] bytes = stream.toByteArray();
        intent.putExtra("image",bytes);
        intent.putExtra("title", item.getTitle());
        intent.putExtra("info",item.getDescription());
        //intent.putExtra("image", item.getImage());

        //Start details activity
        startActivity(intent);
    }

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
        intentAddFood = new Intent(this,addItemActivity.class);
        startActivityForResult(intentAddFood, 0);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 0 && resultCode == RESULT_OK && data != null) {
            selectedFood = data.getStringExtra("food");
            int location = data.getIntExtra("pos",0);
            String amount = data.getStringExtra("quantity");
            addFoodPic(location,selectedFood,amount);

            //addFoodPic(foodInfo,selectedFood);
        }


    }
    public ArrayList<ImageItem> getData() {
        ArrayList<ImageItem> imageArray = new ArrayList<ImageItem>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_id);
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageArray.add(new ImageItem(bitmap, "Image#" + i,""));
        }
        return imageArray;
    }
    public void addFoodPic(int location,String selectedFood,String amount){
        String position = Integer.toString(location);
        SearchFoodTask searchTask = new SearchFoodTask(this,gridAdapter);
        if(selectedFood.equals("Chicken")) {
            searchTask.execute(selectedFood,"0500",position,amount);
        }
        else if(selectedFood.equals("Pork")){
            searchTask.execute(selectedFood,"1000",position,amount);
        }
        else if(selectedFood.equals("Beef")){
            searchTask.execute(selectedFood,"1300",position,amount);
        }
        else if(selectedFood.equals("Turkey")){
            searchTask.execute(selectedFood,"0500",position, amount);
        }
        else if(selectedFood.equals("Fish")){
            searchTask.execute(selectedFood,"1500",position,amount);
        }
    }
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        ArrayList<ImageItem> pics = pictures;
        savedInstanceState.putParcelableArrayList("pictures",pics);
        // Always call the superclass so it can save the view hierarchy state
        super.onSaveInstanceState(savedInstanceState);
    }
}
