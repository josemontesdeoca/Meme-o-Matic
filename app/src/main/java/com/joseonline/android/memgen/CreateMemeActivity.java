package com.joseonline.android.memgen;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.Menu;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class CreateMemeActivity extends ActionBarActivity {
    private EditText etTopText;
    private EditText etBottomText;
    private TextView tvTopText;
    private TextView tvBottomText;
    private ViewGroup rlMeme;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_meme);

        // Bind UI elements
        etTopText = (EditText) findViewById(R.id.etTopText);
        etBottomText = (EditText) findViewById(R.id.etBottomText);
        tvTopText = (TextView) findViewById(R.id.tvTopText);
        tvBottomText = (TextView) findViewById(R.id.tvBottomText);
        rlMeme = (ViewGroup) findViewById(R.id.rlMeme);

        etTopText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvTopText.setText(editable.toString());
            }
        });

        etBottomText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                tvBottomText.setText(editable.toString());
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_create_meme, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_share) {
            onShareMeme();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * Save meme from the ViewGroup as an Image using Android's MediaStore Content Provider
     *
     * @return The url where the image has been stored
     */
    private String saveMeme() {
        Bitmap memeBitmap = Bitmap.createBitmap(rlMeme.getWidth(), rlMeme.getHeight(),
                Bitmap.Config.ARGB_8888);
        Canvas c = new Canvas(memeBitmap);
        rlMeme.draw(c);
        return MediaStore.Images.Media.insertImage(getContentResolver(), memeBitmap, "Meme", "Meme");
    }

    /**
     * Share Meme via Android's ACTION_SEND implicit Intent
     */
    private void onShareMeme() {
        String memeUrl = saveMeme();

        if (memeUrl != null) {
            Intent shareIntent = new Intent(Intent.ACTION_SEND);
            shareIntent.setType("image/png");
            shareIntent.putExtra(Intent.EXTRA_STREAM, Uri.parse(memeUrl));
            startActivity(Intent.createChooser(shareIntent, getString(R.string.share_tag)));
        } else {
            Toast.makeText(this, "Oops! an error has occured while sharing your meme!", Toast.LENGTH_SHORT).show();
        }
    }
}
