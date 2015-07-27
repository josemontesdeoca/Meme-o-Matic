package com.joseonline.android.memgen;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ImageAdapter extends ArrayAdapter<MemeTemplate> {

    public ImageAdapter(Context context, List<MemeTemplate> memeTemplates) {
        super(context, 0, memeTemplates);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        MemeTemplate memeTemplate = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_image, parent, false);
        }

        ImageView ivImage = (ImageView) convertView.findViewById(R.id.ivImage);

        if (!memeTemplate.getThumbnailUrl().isEmpty()) {
            Picasso.with(getContext()).load(memeTemplate.getThumbnailUrl()).into(ivImage);
        } else {
            Log.w("ImageAdapter", "Empty image URL");
        }

        return convertView;
    }
}
