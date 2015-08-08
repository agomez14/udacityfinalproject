package com.example.android.pantry;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.Window;

import com.parse.ParseUser;

import java.util.ArrayList;
import java.util.List;

public class RecipeListActivity extends AppCompatActivity implements ActionBar.TabListener {

    private ActionBar mActionBar;
    com.example.android.pantry.TabPagerAdapter mTabPagerAdapter;
    ViewPager mViewPager;
    private String[] tabs = { "Breakfast", "Lunch", "Dinner" };
    private List<Recipe> mRecipes = new ArrayList<>();

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
        setContentView(R.layout.activity_recipe_list);

        for (int i = 0; i < 25; i++) {
            mRecipes.add(new Recipe("Food and stuff", "Ingredients and shit till it's good.", 3));
            mRecipes.add(new Recipe("Lunch and shit", "Ingredients and shit till it's good.", 2));
            mRecipes.add(new Recipe("Bacon and Eggs", "Ingredients and shit till it's good.", 1));
        }

        mTabPagerAdapter = new TabPagerAdapter(getSupportFragmentManager());
        mViewPager = (ViewPager) findViewById(R.id.pager);
        mViewPager.setOffscreenPageLimit(2);
        mViewPager.setAdapter(mTabPagerAdapter);

        mActionBar = getSupportActionBar();
        mActionBar.setHomeButtonEnabled(false);
        mActionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);


        for (String tab_name : tabs) {
            mActionBar.addTab(mActionBar.newTab().setText(tab_name)
                    .setTabListener(this));
        }

        mViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {

            @Override
            public void onPageSelected(int position) {
                mActionBar.setSelectedNavigationItem(position);
            }

            @Override
            public void onPageScrolled(int arg0, float arg1, int arg2) {
            }

            @Override
            public void onPageScrollStateChanged(int arg0) {
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction ft) {
        mViewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction ft) {

    }

    public List<Recipe> getDinner() {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe r : mRecipes) {
            if (r.getMeal() == 3) {
                recipes.add(r);
            }
        }
        return recipes;
    }

    public List<Recipe> getLunch() {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe r : mRecipes) {
            if (r.getMeal() == 2) {
                recipes.add(r);
            }
        }
        return recipes;
    }

    public List<Recipe> getBreakfast() {
        List<Recipe> recipes = new ArrayList<>();
        for (Recipe r : mRecipes) {
            if (r.getMeal() == 1) {
                recipes.add(r);
            }
        }
        return recipes;
    }

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
}