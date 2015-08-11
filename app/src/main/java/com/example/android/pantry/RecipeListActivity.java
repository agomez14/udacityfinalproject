package com.example.android.pantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity {//implements ActionBar.TabListener {

    private ActionBar mActionBar;
    com.example.android.pantry.TabPagerAdapter mTabPagerAdapter;
    ViewPager mViewPager;
    private String[] tabs = { "Breakfast", "Lunch", "Dinner" };
    private List<Recipe> mRecipes = new ArrayList<>();
    private ArrayList<String> ingredients = new ArrayList<>();
    private RecyclerView mRecyclerView;
    private RecipeAdapter mAdapter;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_recipe_list, menu);
        return true;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_recipe_list);

        mRecyclerView = (RecyclerView) findViewById(R.id.recipe_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mAdapter = new RecipeAdapter(this, mRecipes, getLayoutInflater());
        mRecyclerView.setAdapter(mAdapter);

        ingredients = getIntent().getStringArrayListExtra("ingred");
        SearchRecipesTask recipesTask = new SearchRecipesTask(mAdapter, mRecipes);
        recipesTask.execute(ingredients);
//        for (int i = 0; i < 10; i++) {
//            mRecipes.add(new Recipe("Vegetable-Pasta Oven Omelet", "tomato, onions, red pepper, garlic, olive oil, zucchini, cream cheese, vermicelli, eggs, parmesan cheese, milk, italian seasoning, salt, black pepper","http://www.bigoven.com/43919-Roasted-Pepper-and-Bacon-Omelet-recipe.html", 3,"http://i.imgur.com/DvpvklR.png"));
//            mRecipes.add(new Recipe("Vegetable-Pasta Oven Omelet", "tomato, onions, red pepper, garlic, olive oil, zucchini, cream cheese, vermicelli, eggs, parmesan cheese, milk, italian seasoning, salt, black pepper","http://www.bigoven.com/43919-Roasted-Pepper-and-Bacon-Omelet-recipe.html", 2,"http://img.recipepuppy.com/560556.jpg"));
//            mRecipes.add(new Recipe("Vegetable-Pasta Oven Omelet", "tomato, onions, red pepper, garlic, olive oil, zucchini, cream cheese, vermicelli, eggs, parmesan cheese, milk, italian seasoning, salt, black pepper","http://www.bigoven.com/43919-Roasted-Pepper-and-Bacon-Omelet-recipe.html", 1,"http://img.recipepuppy.com/560556.jpg"));
//        }

//        mTabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
//        mViewPager = (ViewPager) findViewById(R.id.pager);
//        mViewPager.setOffscreenPageLimit(2);
//        mViewPager.setAdapter(mTabPagerAdapter);
//
//        mActionBar = getSupportActionBar();
//        mActionBar.setHomeButtonEnabled(false);
//        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
//
//
//        for (String tab_name : tabs) {
//            mActionBar.addTab(mActionBar.newTab().setText(tab_name)
//                    .setTabListener(this));
//        }
//
//        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
//
//            @Override
//            public void onPageSelected(int position) {
//                mActionBar.setSelectedNavigationItem(position);
//            }
//
//            @Override
//            public void onPageScrolled(int arg0, float arg1, int arg2) {
//            }
//
//            @Override
//            public void onPageScrollStateChanged(int arg0) {
//            }
//        });
    }

//    @Override
//    protected void onStart() {
//        super.onStart();
//    }
//
//    @Override
//    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
//        mViewPager.setCurrentItem(tab.getPosition());
//    }
//
//    @Override
//    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//    }
//
//    @Override
//    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {
//
//    }

//    public List<Recipe> getDinner() {
//        List<Recipe> recipes = new ArrayList<>();
//        for (Recipe r : mRecipes) {
//            if (r.getMeal() == 3) {
//                recipes.add(r);
//            }
//        }
//        return recipes;
//    }
//
//    public List<Recipe> getLunch() {
//        List<Recipe> recipes = new ArrayList<>();
//        for (Recipe r : mRecipes) {
//            if (r.getMeal() == 2) {
//                recipes.add(r);
//            }
//        }
//        return recipes;
//    }
//
//    public List<Recipe> getBreakfast() {
//        List<Recipe> recipes = new ArrayList<>();
//        for (Recipe r : mRecipes) {
//            if (r.getMeal() == 1) {
//                recipes.add(r);
//            }
//        }
//        return recipes;
//    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.action_settings:
                ParseUser.logOut();
                Intent intent = new Intent(RecipeListActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public List<Recipe> getRecipes() {
        return mRecipes;
    }

    public List<String> getIngredients() {
        return ingredients;
    }
}