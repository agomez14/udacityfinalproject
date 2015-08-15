package com.example.android.pantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;

import com.parse.ParseException;
import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by elijahstaple on 8/11/15.
 */
public class PantryFragment extends Fragment implements AdapterView.OnItemClickListener {

    private String selectedFood;
    private GridView gridView;
    private GridViewAdapter gridAdapter;
    private Intent intentAddFood;
    private int colorButton;
    private ArrayList<ImageItem> pictures;
    private ArrayList<String> ingredients =  new ArrayList<String>();
    private Button recipeButton;
    private Button doneButton;
    private List<ingredientTuple> userPantryItems = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.pantry_fragment, container, false);

        userPantryItems = ParseUser.getCurrentUser().getList("PantryIngredientFoodCodes");
        gridView = (GridView) v.findViewById(R.id.listFoods);
        recipeButton = (Button) v.findViewById(R.id.recipesButton);
        doneButton = (Button) v.findViewById(R.id.done);
       // recipeButton.setOnClickListener(new View.OnClickListener() {
          //  @Override
         //   public void onClick(View v) {
           //     changeColor(v);
            //}
       // });
        //doneButton.setOnClickListener(new View.OnClickListener() {
         //   @Override
          //  public void onClick(View v) {
           //     findRecipes(v);
           // }
       // });

        if (savedInstanceState != null) {
            pictures = savedInstanceState.getParcelableArrayList("pictures");
        }
        else {
            pictures = new ArrayList<ImageItem>();
        }
        gridAdapter = new GridViewAdapter(getActivity(), R.layout.grid_item, pictures);
        if (userPantryItems != null) {
            Log.d("PantryFragment", "Got items and it wasn't null at least");
            for (ingredientTuple s : userPantryItems) {
                try {
                    new FetchFoodTask(getActivity(),
                            gridAdapter,
                            ((FetchFoodTask.Callbacks) getActivity())).execute(s.getId(),
                            Integer.toString(2),
                            s.getQuantity());
                } catch (ParseException e) {
                    e.printStackTrace();
                    Log.e("PantryFragment", "Couldn't get user items");
                }
            }
        }
        gridView.setAdapter(gridAdapter);
        gridView.setOnItemClickListener(this);

        return v;
    }

    public void onItemClick (AdapterView< ? > parent, View v,int position, long id){
      /*  if(colorButton > 0 && colorButton!=0){
            doneButton.setVisibility(View.VISIBLE);
            ImageItem item = (ImageItem) parent.getItemAtPosition(position);
            ingredients.add(item.getTitle());
        }
        else {*/
        ImageItem item = (ImageItem) parent.getItemAtPosition(position);
        DetailsFragment fragment = DetailsFragment.newInstance(item);
        getFragmentManager().beginTransaction()
                .add(R.id.fragment_container, fragment)
                .hide(getFragmentManager().findFragmentByTag("PantryFragment"))
                .addToBackStack(null)
                .commit();
        }
   // }

   /* public ArrayList<ImageItem> getData() {
        ArrayList<ImageItem> imageArray = new ArrayList<ImageItem>();
        TypedArray imgs = getResources().obtainTypedArray(R.array.image_id);
        String[] foodNames = new String[5];
        foodNames[0] = "chicken";
        foodNames[1] = "Pork";
        foodNames[2] = "Beef";
        foodNames[3] = "Turkey";
        foodNames[4] = "fish";
        for (int i = 0; i < imgs.length(); i++) {
            Bitmap bitmap = BitmapFactory.decodeResource(getResources(), imgs.getResourceId(i, -1));
            imageArray.add(new ImageItem(bitmap, foodNames[i],""));
        }
        return imageArray;
    } */

    public GridViewAdapter getAdapter() {
        return gridAdapter;
    }

   /* public void changeColor(View v) {
        ColorDrawable buttonDrawable = (ColorDrawable) v.getBackground();
        if (colorButton == 0) {
            colorButton = buttonDrawable.getColor();
        }
        if (colorButton < 0) {
            v.setBackgroundColor(getResources().getColor(R.color.blue));
            colorButton = colorButton * -1;
        } else if (colorButton > 0) {
            v.setBackgroundColor(getResources().getColor(R.color.aqua));
            colorButton = colorButton * -1;
        }
    }*/

   /* public void findRecipes(View v) {
        Intent intent = new Intent(getActivity(), RecipeListActivity.class);
        ArrayList<String> ingred = new ArrayList<String>();
        for (String f : ingredients) {
            ingred.add(f);
        }
        intent.putExtra("ingred", ingred);
        colorButton = colorButton * -1;
        ingredients.clear();
        recipeButton.setBackgroundColor(getResources().getColor(R.color.aqua));
        doneButton.setVisibility(View.INVISIBLE);
        startActivity(intent);
    }
    */
}
