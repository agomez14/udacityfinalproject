package com.example.android.pantry;

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
 * Created by barryjohnsonsmith on 8/13/15.
 */
public class FoodPicTask extends AsyncTask<String,Void,String> {
    private final String LOG_TAG = FoodPicTask.class.getSimpleName();
    private Callbacks mCallbacks;
    private static final String API_Key = "7eeac3f501dd3fe742a7";
    private static final String USER_VALUE = "Barryjohnsonsmith23";

    public FoodPicTask(Callbacks callbacks) {
        mCallbacks = callbacks;
    }
    private String getFoodPicFromJson(String SearchPicJsonStr)
            throws JSONException {
        // These are the names of the JSON objects that need to be extracted.
        final String HITS = "hit";
        JSONObject searchJson = new JSONObject(SearchPicJsonStr);
        JSONArray foodList = searchJson.getJSONArray(HITS);
        JSONObject foodPic = foodList.getJSONObject(0);
        String picLink = foodPic.getString("previewURL");
        return picLink;
    }
    @Override
    protected String doInBackground(String... params) {
        HttpURLConnection urlConnection = null;
        String results = "";
        BufferedReader reader = null;
        String searchPicJsonStr = null;
        if (params.length == 0) {
            return null;
        }
        try

        {
            final String FORECAST_BASE_URL = "https://pixabay.com/api/?";
            final String QUERY = "q";
            final String USER= "username";
            final String Key = "key";
            final String ORDER = "order";
            final String TYPE = "image_type";
            params[0] = params[0].replace(',','+');
            params[0] = params[0].replaceAll("\\s","");
            Uri builtUri = Uri.parse(FORECAST_BASE_URL).buildUpon()
                    .appendQueryParameter(USER,USER_VALUE)
                    .appendQueryParameter(Key, API_Key)
                    .appendQueryParameter(QUERY,"raw+chicken")
                    .appendQueryParameter(ORDER,"popular")
                    .appendQueryParameter(TYPE,"photo")
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
            searchPicJsonStr = buffer.toString();
            Log.v(LOG_TAG, "Search pic: " + searchPicJsonStr);
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
            results = getFoodPicFromJson(searchPicJsonStr);
            return results ;
        }
        catch (JSONException e) {
            Log.e(LOG_TAG, e.getMessage(), e);
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String result) {
        if (mCallbacks != null) {
            done(result);
        }
    }
    public interface Callbacks {
        void  fetchDone(String pic);
    }

    private void done(String pic) {
        mCallbacks.fetchDone(pic);
    }
}

