package com.joseonline.android.memgen;

import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class MemeTemplate {
    private static final String TAG = "MemeTemplate";

    private String imageUrl;
    private String thumbnailUrl;

    public MemeTemplate(String imageUrl, String thumbnailUrl) {
        this.imageUrl = imageUrl;
        this.thumbnailUrl = thumbnailUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getThumbnailUrl() {
        return thumbnailUrl;
    }

    /**
     * Returns a MemeTemplate from the Picasa Web JSON response
     */
    public static MemeTemplate fromJson(JSONObject json) {
        try {
            JSONObject content = (JSONObject) json.getJSONObject("media$group")
                    .getJSONArray("media$content").get(0);
            JSONObject thumbnail = (JSONObject) json.getJSONObject("media$group")
                    .getJSONArray("media$thumbnail").get(0);
            return new MemeTemplate(content.getString("url"), thumbnail.getString("url"));
        } catch (JSONException e) {
            Log.w("Unable to parse ImageResult from " + json.toString(), e);
        }
        return null;
    }

    /**
     * Returns Meme Templates from the Picasa Web JSON response
     */
    public static List<MemeTemplate> fromJson(JSONArray entries) {
        List<MemeTemplate> results = new ArrayList<>();

        for (int i=0; i < entries.length(); i++) {
            try {
                MemeTemplate memeTemplate = fromJson(entries.getJSONObject(i));
                if (memeTemplate != null) {
                    results.add(memeTemplate);
                }
            } catch (JSONException e) {
                Log.w(TAG, "Unable to get entry " + i + " from json Array: " + entries.toString(), e);
            }
        }

        return results;
    }
}
