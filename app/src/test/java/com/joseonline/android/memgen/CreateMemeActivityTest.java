package com.joseonline.android.memgen;

import android.widget.EditText;
import android.widget.TextView;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;


@Config(constants = BuildConfig.class, sdk = 21)
@RunWith(RobolectricGradleTestRunner.class)
public class CreateMemeActivityTest {
    private CreateMemeActivity activity;
    private EditText etTopText;
    private EditText etBottomText;
    private TextView tvTopText;
    private TextView tvBottomText;


    @Before
    public void setup() throws Exception {
        activity = Robolectric.setupActivity(CreateMemeActivity.class);
        assertNotNull("CreateMemeActivity is not instantiated", activity);

        etTopText = (EditText) activity.findViewById(R.id.etTopText);
        etBottomText = (EditText) activity.findViewById(R.id.etBottomText);
        tvTopText = (TextView) activity.findViewById(R.id.tvTopText);
        tvBottomText = (TextView) activity.findViewById(R.id.tvBottomText);
    }

    @Test
    public void validateTextOnMemeImage() throws Exception {
        String topText = "Top Text Testing";
        String bottomText = "Bottom Text Testing";

        etTopText.setText(topText);
        etBottomText.setText(bottomText);

        assertTrue("Top Text TextView contains incorrect text",
                topText.equals(tvTopText.getText().toString()));
        assertTrue("Bottom Text TextView contains incorrect text",
                bottomText.equals(tvBottomText.getText().toString()));
    }

    @After
    public void tearDown() throws Exception {}
}
