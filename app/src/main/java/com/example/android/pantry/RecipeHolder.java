package com.example.android.pantry;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

/**
 * Created by elijahstaple on 8/10/15.
 */


public class RecipeHolder extends RecyclerView.ViewHolder
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

    public void bindRecipe(Recipe recipe, Context context) {
        Log.d("RecipeHolder", "Started bind");
        Picasso.with(context).load(recipe.getPicture()).fit().into(mImageView);
        Log.d("RecipeHolder", "Bound picture");
        mNameTextView.setText(recipe.getName());
        Log.d("RecipeHolder", "Bound name");
    }

    @Override
    public void onClick(View v) {
    }
}
