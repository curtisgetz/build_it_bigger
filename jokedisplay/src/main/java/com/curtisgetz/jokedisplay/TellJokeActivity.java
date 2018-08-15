package com.curtisgetz.jokedisplay;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class TellJokeActivity extends AppCompatActivity {

    public final static String JOKE_EXTRA_KEY = "joke_extra";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tell_joke);

        TextView jokeDisplayTV = (TextView)findViewById(R.id.joke_display_tv);
        Intent intent = getIntent();

        if(intent == null){
            Toast.makeText(this, com.curtisgetz.jokedisplay.R.string.error_loading_joke, Toast.LENGTH_SHORT).show();
            finish();
        }else {
            String joke = intent.getStringExtra(JOKE_EXTRA_KEY);
            jokeDisplayTV.setText(joke);
        }

    }
}
