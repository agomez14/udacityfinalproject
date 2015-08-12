package com.example.android.pantry;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.example.android.pantry.RecipeListActivity.RecipeAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

/**
 * Created by barryjohnsonsmith on 8/8/15.
 */
public class SearchRecipesTask extends AsyncTask<ArrayList<String>, Void, ArrayList<Recipe>> {

    private final String LOG_TAG = SearchFoodTask.class.getSimpleName();
    private RecipeAdapter mAdapter;
    //private final Context mContext;

    public SearchRecipesTask(RecipeAdapter adapter) {
        //mContext = context;
        mAdapter = adapter;
    }

    private ArrayList<Recipe> getFoodDataFromJson(String recipeSearchJsonStr, int meal)
            throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String RESULTS_LIST = "results";
        final String INDGREDIENTS = "ingredients";
        final String TITLE = "title";
        final String RECIPE = "href";
        final String THUMB_N = "thumbnail";
        ArrayList<Recipe> recipeList = new ArrayList<Recipe>();
        JSONObject recipeJson = new JSONObject(recipeSearchJsonStr);
        JSONArray searchList = recipeJson.getJSONArray(RESULTS_LIST);
        for (int i = 0;i<searchList.length(); i++) {
            JSONObject j = searchList.getJSONObject(i);
            Log.d("RecipeTask", "Before while");
            String title = j.getString(TITLE);
            String ingredients = j.getString(INDGREDIENTS);
            String thumbNail = j.getString(THUMB_N);
            String recipe = j.getString(RECIPE);
            recipeList.add(new Recipe(title, ingredients, recipe, meal, thumbNail));
            i++;
        }
        return recipeList;
    }

    protected ArrayList<Recipe> doInBackground(ArrayList<String>... params) {
        HttpURLConnection urlConnection = null;
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        BufferedReader reader = null;
        String recipeSearchJsonStr = null;
        ArrayList<String> ingreds = params[0];
        if (params.length == 0) {
            return null;
        }
        try {
            final String FORECAST_BASE_URL = "http://www.recipepuppy.com/api/?";
            final String QUERY = "q";
            final String INGREDIENTS = "i";
            final String PAGES = "p";
            String recipes = "";
            for (String s : ingreds) {
                recipes = recipes + "," + s;
            }
            recipes = recipes.substring(1, recipes.length());
            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(INGREDIENTS, recipes)
                            //.appendQueryParameter(QUERY, params[3])
                    .appendQueryParameter(PAGES, "1")
                    .build();
            URL url = new URL(builtUri.toString());
            Log.v(LOG_TAG, "Built URI " + builtUri.toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            while ((line = reader.readLine()) != null) {
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }
            recipeSearchJsonStr = buffer.toString();
            Log.v(LOG_TAG, "Search string: " + recipeSearchJsonStr);
        } catch (IOException e)

        {
            Log.e(LOG_TAG, "Error ", e);
            return null;
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (final IOException e) {
                    Log.e(LOG_TAG, "Error closing stream", e);
                }
            }
        }
        try {
            results = (getFoodDataFromJson(recipeSearchJsonStr, 1));
            return results;
        } catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }

    @Override
    protected void onPostExecute(ArrayList<Recipe> result) {
        Log.d("recipeList", "Got to onPostExecute");
        Log.d("recipeList", result.toString());
        if (result != null) {
            mAdapter.setRecipes(result);
            mAdapter.notifyDataSetChanged();
        }
    }
}



