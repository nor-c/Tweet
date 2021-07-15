package com.example.tweet;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

import com.example.tweet.model.Tweet;

public class PostActivity extends AppCompatActivity {
    private EditText editPostTweetTitle, editPostTweetBody;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        editPostTweetTitle = findViewById(R.id.editPostTweetTitle);
        editPostTweetBody = findViewById(R.id.editPostTweetBody);

    }

    public void sendPost(View view) {
        Tweet tweet = new Tweet(editPostTweetTitle.getText().toString(), editPostTweetBody.getText().toString());
        //send to database

        //return to tweet list
        finish();
    }
}
