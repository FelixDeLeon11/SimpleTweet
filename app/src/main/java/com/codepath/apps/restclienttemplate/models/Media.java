package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import static com.codepath.apps.restclienttemplate.TimelineActivity.TAG;

public class Media {
    public JSONObject mediaObject;
    public JSONArray mediaArray;
    public String mediaURL;

    public static Media fromJson(JSONObject jsonObject) {
        Media media = new Media();
        media.mediaURL = "";
        try {
            media.mediaArray = jsonObject.getJSONArray("media");
            media.mediaObject = media.mediaArray.getJSONObject(0);
            media.mediaURL = media.mediaObject.getString("media_url_https");
            Log.i(TAG, "Media Found!");
        } catch (JSONException e) {
            Log.i(TAG, "No Media Found");
        }
        return media;
    }
}
