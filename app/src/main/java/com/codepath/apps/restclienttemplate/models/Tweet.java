package com.codepath.apps.restclienttemplate.models;

import android.util.Log;

import com.codepath.apps.restclienttemplate.TimeFormatter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.parceler.Parcel;

import java.util.ArrayList;
import java.util.List;

import static com.codepath.apps.restclienttemplate.TimelineActivity.TAG;

@Parcel
public class Tweet {
    public String body;
    public String createdAt;
    public  long id;
    public User user;
    //public Media media;
    public String mediaURL;

    //For parceler
    public Tweet(){}

    public static Tweet fromJson(JSONObject jsonObject) throws JSONException {
        Tweet tweet = new Tweet();
        tweet.body = jsonObject.getString("text");
        tweet.createdAt = jsonObject.getString("created_at");
        tweet.user = User.fromJson(jsonObject.getJSONObject("user"));
        tweet.id = jsonObject.getLong("id");
        //tweet.media = Media.fromJson(jsonObject.getJSONObject("entities"));

        tweet.mediaURL = getMediaURL( jsonObject.getJSONObject("entities") );

        return tweet;
    }

    public static List<Tweet> fromJsonArray(JSONArray jsonArray) throws JSONException {
        List<Tweet> tweets = new ArrayList<>();
        for (int i = 0; i < jsonArray.length(); i++)
            tweets.add(fromJson(jsonArray.getJSONObject(i)));

        return tweets;
    }
    public static String getMediaURL(JSONObject jsonObject){
        String result = "";
        try {
            JSONArray mediaArray = jsonObject.getJSONArray("media");
            JSONObject mediaObject = mediaArray.getJSONObject(0);
            result = mediaObject.getString("media_url_https");
            Log.i(TAG, "Media Found!");
        } catch (JSONException e) {
            Log.i(TAG, "No Media Found");
            e.printStackTrace();
        }
        return result;
    }

    public String getFormattedTimestamp(){
        return TimeFormatter.getTimeDifference(createdAt);
    }
}
