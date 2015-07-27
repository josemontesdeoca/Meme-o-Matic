package com.joseonline.android.memgen;

import android.util.Log;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.apache.http.Header;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class PicasaWebClient {
    private static final String TAG = "PicasaWebClient";

    private static final String BASE_URL = "https://picasaweb.google.com";
    private static final String FEED_URL = BASE_URL + "/data/feed/base/user/112742940798685917586/albumid/6168211334555408817";
    private static final String FULL_URL = FEED_URL + "?authkey=Gv1sRgCPfyy-iH6b7jRQ&&kind=photo&alt=json&fields=entry(media:group(media:content,media:thumbnail))";

    public interface FetchCompleteCallback {
        void onFetchComplete(List<MemeTemplate> results);
    }

    private final AsyncHttpClient client = new AsyncHttpClient();

    /**
     * Retrieves the meme templates from the Picasa Album
     */
    public void fetchTemplates(final FetchCompleteCallback fetchCompleteCallback) {
       client.get(FULL_URL, new JsonHttpResponseHandler() {
           @Override
           public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
               try {
                   List<MemeTemplate> results = MemeTemplate.fromJson(response.getJSONObject("feed").getJSONArray("entry"));
                   fetchCompleteCallback.onFetchComplete(results);
               } catch (JSONException e) {
                   Log.w(TAG, "Unable to parse JSON response: " + response.toString());
               }
           }
       });
    }
}
