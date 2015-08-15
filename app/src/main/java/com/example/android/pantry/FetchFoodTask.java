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

/**
 * Created by barryjohnsonsmith on 8/5/15.
 */
public class FetchFoodTask extends AsyncTask<String ,Void, String[]> implements FoodPicTask.Callbacks {
    private final String LOG_TAG = FetchFoodTask.class.getSimpleName();
    private GridViewAdapter gridAdapter;
    private final Context mContext;
    private Callbacks mCallbacks;
    private String pic;

    public FetchFoodTask(Context context, GridViewAdapter gridAdapter) {
        mContext = context;
        //mCallbacks = (Callbacks) context;
        this.gridAdapter = gridAdapter;
    }

    public FetchFoodTask(Context context, GridViewAdapter gridAdapter, Callbacks callbacks) {
        mContext = context;
        mCallbacks = callbacks;
        this.gridAdapter = gridAdapter;
    }

    private String[] getFoodDataFromJson(String foodJsonStr,String amount)
                   throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String REPORT = "report";
        final String FOOD = "food";
        final String NAME = "name";
        final String NUTRIENTS = "nutrients";
        final String FOODGROUP = "fg";
        final String VALUE = "value";
        JSONObject foodJson = new JSONObject(foodJsonStr);
        JSONObject report = foodJson.getJSONObject(REPORT);
        JSONObject foodObj =  report.getJSONObject(FOOD);
        JSONArray nutrientObject = foodObj.getJSONArray(NUTRIENTS);
        JSONObject calories = nutrientObject.getJSONObject(1);
        JSONObject protien = nutrientObject.getJSONObject(3);
        JSONObject fats = nutrientObject.getJSONObject(4);
        JSONObject carb = nutrientObject.getJSONObject(6);
        JSONObject sugar  = nutrientObject.getJSONObject(8);
        JSONObject sodium = nutrientObject.getJSONObject(20);
        String nameValue = foodObj.getString(NAME);
        String foodGroup = foodObj.getString(FOODGROUP);
        double caloriesValue = calories.getDouble(VALUE);
        double protienValue = protien.getDouble(VALUE);
        double fatsValue = fats.getDouble(VALUE);
      //  double carbValue = carb.getDouble(VALUE);
       // double sugarValue = sugar.getDouble(VALUE);
        //double sodiumValue = sodium.getDouble(VALUE);
        double quantity = Double.parseDouble(amount);
        Food newFood = new Food(nameValue,caloriesValue,quantity,protienValue,fatsValue,foodGroup);
        FoodPicTask getPic = new FoodPicTask(this);
        getPic.execute(nameValue);
        String[] info = new String[2];
        info[0] = newFood.toString();
        info[1] = nameValue;

        return info;
    }

    @Override
    protected String[] doInBackground(String... params) {
        String[] results = new String[2];
        HttpURLConnection urlConnection = null;
        BufferedReader reader = null;
        String foodJsonStr = null;
        String format = "json";
        String key ="OduqoSRTfVbJenKSXdOTyWl6OlMbRz7jxWW6vbQg";
        String type ="f";
        if (params.length == 0) {
            return null;
        }
        try

        {
           // URL url = new URL("http://api.nal.usda.gov/ndb/reports/?ndbno=01009&type=b&format=fjson&api_key=OduqoSRTfVbJenKSXdOTyWl6OlMbRz7jxWW6vbQg");
            final String FORECAST_BASE_URL = "http://api.nal.usda.gov/ndb/reports/?";
            final String NDB_PARAM = "ndbno";
            final String TYPE_PARAM = "type";
            final String FORMAT_PARAM = "format";
            final String KEY_PARAM = "api_key";
            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(NDB_PARAM, params[0])
                    .appendQueryParameter(TYPE_PARAM,type)
                    .appendQueryParameter(FORMAT_PARAM,format)
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
            foodJsonStr = buffer.toString();
            Log.v(LOG_TAG, "Forecast string: " + foodJsonStr);
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
            String[] foodsInfo = new String[2];
            foodsInfo = getFoodDataFromJson(foodJsonStr,params[1]);
            results[0] = foodsInfo[0];
            results[1] = foodsInfo[1];
            return results;
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
            String info = result[0];
            String name = result[1];
//            gridAdapter.add(new ImageItem(bitmap, "Im
// age# ", result[0]));
            if (mCallbacks != null) {
                done(pic,info,name);
            }
        }
    }

    public interface Callbacks {
        void fetchDone(String pic,String info,String name);
    }

    private void done(String pic,String info,String name) {
        mCallbacks.fetchDone(pic,info,name);
    }
    @Override
    public void fetchDone(String picture) {
        pic = picture;
    }
}
