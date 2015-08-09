package com.example.android.pantry;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

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
public class SearchRecipesTask extends AsyncTask<String ,Void, ArrayList<Recipe>> {

    private final String LOG_TAG = SearchFoodTask.class.getSimpleName();
    private GridViewAdapter gridAdapter;
    private final Context mContext;

    public SearchRecipesTask(Context context, GridViewAdapter gridAdapter) {
        mContext = context;
        this.gridAdapter = gridAdapter;
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
        for (int i = 0; i < searchList.length(); i++) {
            JSONObject j = searchList.getJSONObject(i);
            String title = j.getString(TITLE);
            String ingredients = j.getString(INDGREDIENTS);
            String thumbNail = j.getString(THUMB_N);
            String recipe = j.getString(RECIPE);
            recipeList.add(new Recipe(title, ingredients, recipe, meal, thumbNail));
        }
        return recipeList;
    }
    protected ArrayList<Recipe> doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        ArrayList<Recipe> results = new ArrayList<Recipe>();
        BufferedReader reader = null;
        String recipeSearchJsonStr = null;
        String format = "json";
        String key ="OduqoSRTfVbJenKSXdOTyWl6OlMbRz7jxWW6vbQg";
        if (params.length == 0) {
            return null;
        }
        try {
            final String FORECAST_BASE_URL = "http://www.recipepuppy.com/api/?";
            final String QUERY = "q";
            final String INGREDIENTS = "i";
            final String PAGES = "p";
            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(INGREDIENTS,params[0])
                    //.appendQueryParameter(QUERY, params[3])
                    //.appendQueryParameter(FG_PARAM,params[1])
                    .appendQueryParameter(PAGES,params[2])
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
        }
        finally
        {
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
            results=(getFoodDataFromJson(recipeSearchJsonStr, Integer.parseInt(params[1])));
            return results ;
        }
        catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(ArrayList<Recipe> result) {
        if (result != null) {
            FetchFoodTask fetchFood = new FetchFoodTask(mContext,gridAdapter);
        }
    }
}


