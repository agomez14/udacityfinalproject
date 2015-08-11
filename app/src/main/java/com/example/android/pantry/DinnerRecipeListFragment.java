package com.example.android.pantry;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;


/**
 * A placeholder fragment containing a simple view.
 */
public class DinnerRecipeListFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private List<Recipe> mRecipes;
    private RecipeAdapter mAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mRecipes = ((RecipeListActivity) getActivity()).getDinner();
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_recipe_list, container, false);

        mRecyclerView = (RecyclerView) v.findViewById(R.id.recipe_recycler_view);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(new RecipeAdapter());

        return v;
    }

    private class RecipeHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener {

        private ImageView mImageView;
        private TextView mNameTextView;

        public RecipeHolder(View itemView) {
            super(itemView);
            itemView.setOnClickListener(this);

            mImageView = (ImageView) itemView.findViewById(
                    R.id.imageView);
            mNameTextView = (TextView) itemView.findViewById(
                    R.id.textView);
        }

        public void bindRecipe(Recipe recipe) {
            Picasso.with(getActivity()).load(recipe.getPicture()).into(mImageView);
            mNameTextView.setText(recipe.getName());
        }

        @Override
        public void onClick(View v) {
        }
    }

    private class RecipeAdapter extends RecyclerView.Adapter<RecipeHolder> {

        @Override
        public RecipeHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            LayoutInflater layoutInflater = LayoutInflater
                    .from(getActivity());
            View view = layoutInflater.inflate(
                    R.layout.recipe_listview_fragment, parent, false);
            return new RecipeHolder(view);
        }

        @Override
        public void onBindViewHolder(RecipeHolder holder, int position) {
            holder.bindRecipe(mRecipes.get(position));
        }

        @Override
        public int getItemCount() {
            return mRecipes.size();
        }
    }
}
