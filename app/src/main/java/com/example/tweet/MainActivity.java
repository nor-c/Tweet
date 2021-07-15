package com.example.tweet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import com.example.tweet.adapter.TweetAdapter;
import com.example.tweet.model.Tweet;
import com.example.tweet.swipers.TweetSwipe;

public class MainActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private TweetAdapter tweetAdapter;

    public MainActivity() {
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recView);
        LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),
                LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);

        //Do something here with the click
        //Maybe so all users who liked
        TweetAdapter.OnItemClickListener tweetListener = new TweetAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Tweet item) {
                //Do something here with the click
                //Maybe so all users who liked
            }
        };

        //TODO: Test data remove when database is in
        List<Tweet> tweetList = new ArrayList<>();

        tweetList.add(new Tweet("This is title 1", "1 This is some text about a tweet", 10));
        tweetList.add(new Tweet("This is title 2", "2 This is some text about a tweet", 100));
        tweetList.add(new Tweet("This is title 3", "3 This is some text about a tweet", 77));
        tweetList.add(new Tweet("This is title 4", "4 This is some text about a tweet", 15));
        tweetList.add(new Tweet("This is title 5", "5 This is some text about a tweet", 28));
        tweetList.add(new Tweet("This is title 6", "6 This is some text about a tweet", 66));

        tweetAdapter = new TweetAdapter(tweetList, tweetListener);
        recyclerView.setAdapter(tweetAdapter);

        setTrackerSwipe();
    }

    private void setTrackerSwipe() {
        ItemTouchHelper.SimpleCallback touchHelper = new TweetSwipe(0,
                ItemTouchHelper.LEFT, new TweetSwipe.TweetHelperListener() {
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction, int position) {
                //Do something here with the like swipe
                //update like count on item, save to database

                //TODO: Add a check if user already like this item

                tweetAdapter.getTweet(position).addLike();
                tweetAdapter.resortTweets();
                MediaPlayer.create(getApplicationContext(), R.raw.beep).start();
            }
        });

        new ItemTouchHelper(touchHelper).attachToRecyclerView(recyclerView);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.main, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case R.id.action_post:
                startActivity(new Intent(MainActivity.this, PostActivity.class));
                break;
            case R.id.action_reload:
                //TODO: reload the listview with database items
                break;

        }
        return super.onOptionsItemSelected(item);
    }

}
