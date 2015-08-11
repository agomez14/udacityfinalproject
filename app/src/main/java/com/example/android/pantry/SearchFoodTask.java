package com.example.android.pantry;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;

import com.parse.ParseUser;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by barryjohnsonsmith on 8/6/15.
 */
public class SearchFoodTask extends AsyncTask<String ,Void, String[]> {
    private final String LOG_TAG = SearchFoodTask.class.getSimpleName();
    private GridViewAdapter gridAdapter;
    private final Context mContext;

    public SearchFoodTask(Context context, GridViewAdapter gridAdapter) {
        mContext = context;
        this.gridAdapter = gridAdapter;
    }
    private String getFoodDataFromJson(String foodSearchJsonStr)
            throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String LIST = "list";
        final String ITEM = "item";
        final String DATABASE_NUM ="ndbno";
        JSONObject searchJson = new JSONObject(foodSearchJsonStr);
        JSONObject foodList = searchJson.getJSONObject(LIST);
        JSONArray itemList = foodList.getJSONArray(ITEM);
        JSONObject foodObj = itemList.getJSONObject(0);
        String foodCode = foodObj.getString(DATABASE_NUM);
        return foodCode;
    }
    @Override
    protected String[] doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        String[] results = new String[3];
        BufferedReader reader = null;
        String foodSearchJsonStr = null;
        String format = "json";
        String key ="OduqoSRTfVbJenKSXdOTyWl6OlMbRz7jxWW6vbQg";
        if (params.length == 0) {
            return null;
        }
        try

        {
            // http://api.nal.usda.gov/ndb/search/?format=json&q=cheese&sort=n&max=25&offset=0&api_key=DEMO_KEY);
            final String FORECAST_BASE_URL = " http://api.nal.usda.gov/ndb/search/?";
            final String QUERY = "q";
            final String FG_PARAM = "fg";
            final String FORMAT_PARAM = "format";
            final String KEY_PARAM = "api_key";
            final String SORT_PARAM = "sort";
            final String MAX = "max";
            final String OFFSET = "offset";
            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(FORMAT_PARAM,format)
                    .appendQueryParameter(QUERY, params[0])
                    .appendQueryParameter(FG_PARAM,params[1])
                    .appendQueryParameter(SORT_PARAM,"r")
                    .appendQueryParameter(MAX,"25")
                    .appendQueryParameter(OFFSET,"0")
                    .appendQueryParameter(KEY_PARAM, key)
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
            foodSearchJsonStr = buffer.toString();
            Log.v(LOG_TAG, "Search string: " + foodSearchJsonStr);
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
            results[0] = getFoodDataFromJson(foodSearchJsonStr);
            results[1] = params[2];
            results[2] = params[3];
            return results ;
        }
        catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String[] result) {
        if (result != null) {
            ParseUser currentUser = ParseUser.getCurrentUser();
            currentUser.add("PantryIngredientFoodCodes", new ingredientTuple(result[0], result[2]));
            currentUser.saveInBackground();
            FetchFoodTask fetchFood = new FetchFoodTask(mContext,gridAdapter);
            fetchFood.execute(result[0],result[1],result[2]);
        }
    }
}

