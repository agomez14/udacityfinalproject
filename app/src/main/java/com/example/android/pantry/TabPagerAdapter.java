// Copyright 2004-present Facebook. All Rights Reserved.

package com.example.android.pantry;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by emstaple on 7/16/15.
 */
public class  TabPagerAdapter extends FragmentPagerAdapter {

    public TabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int index) {

        switch (index) {
            case 0:
                return new BreakfastRecipeListFragment();
            case 1:
                return new LunchRecipeListFragment();
            case 2:
                return new DinnerRecipeListFragment();
        }

        return null;
    }

    @Override
    public int getCount() {
        return 3;
    }
}
