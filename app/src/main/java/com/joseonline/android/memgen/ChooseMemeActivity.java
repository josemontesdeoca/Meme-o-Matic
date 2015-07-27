package com.joseonline.android.memgen;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ProgressBar;

import java.util.ArrayList;
import java.util.List;


public class ChooseMemeActivity extends ActionBarActivity {
    private GridView gvImages;
    private ProgressBar pbLoading;
    private ImageAdapter imageAdapter;
    private List<MemeTemplate> memeTemplates;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choose_meme);

        gvImages = (GridView) findViewById(R.id.gvImages);
        pbLoading = (ProgressBar) findViewById(R.id.pbLoading);

        memeTemplates = new ArrayList<>();
        imageAdapter = new ImageAdapter(this, memeTemplates);

        gvImages.setAdapter(imageAdapter);

        gvImages.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int pos, long rowId) {
                Intent intent = new Intent(ChooseMemeActivity.this, CreateMemeActivity.class);
                intent.putExtra("memeUrl", memeTemplates.get(pos).getImageUrl());
                startActivity(intent);
            }
        });

        PicasaWebClient picasaWebClient = new PicasaWebClient();

        picasaWebClient.fetchTemplates(new PicasaWebClient.FetchCompleteCallback() {
            @Override
            public void onFetchComplete(List<MemeTemplate> results) {
                pbLoading.setVisibility(View.INVISIBLE);

                memeTemplates.addAll(results);

                Log.d("ChooseMemeActivity", "Groups received: " + memeTemplates.size());

                imageAdapter.notifyDataSetChanged();
            }
        });
    }
}
