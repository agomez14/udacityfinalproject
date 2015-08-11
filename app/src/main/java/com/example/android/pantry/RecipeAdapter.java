package com.example.android.pantry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Created by elijahstaple on 8/10/15.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {
    private Context mContext;
    private List<Recipe> mRecipes;
    private LayoutInflater mInflater;

    public RecipeAdapter(Context context, List<Recipe> recipes, LayoutInflater inflater) {
        mContext = context;
        mRecipes = recipes;
        mInflater = inflater;
    }

    @Override
    public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Log.d("RecipeAdapter", "Creating view holder");
        View view = mInflater.inflate(R.layout.recipe_listview_fragment, parent, false);
        return new RecipeHolder(view);
    }

    @Override
    public void onBindViewHolder(RecipeHolder holder, int position) {
        Log.d("RecipeAdapter", "Calling bind");
        holder.bindRecipe(mRecipes.get(position), mContext);
        Log.d("RecipeAdapter", "After bind");
    }

    @Override
    public int getItemCount() {
        return mRecipes.size();
    }
}
