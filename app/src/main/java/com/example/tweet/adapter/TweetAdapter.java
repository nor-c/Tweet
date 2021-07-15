package com.example.tweet.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Collections;
import java.util.List;
import com.example.tweet.R;
import com.example.tweet.model.Tweet;

public class TweetAdapter extends RecyclerView.Adapter<TweetAdapter.ViewHolder> {
    private final OnItemClickListener listener;
    private List<Tweet> tweets;
    public TweetAdapter(List<Tweet> tweets, OnItemClickListener listener) {
        Collections.sort(tweets);
        this.tweets = tweets;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TweetAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.tweet_item, parent, false);

        return new TweetAdapter.ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.bind(tweets.get(position), listener);

    }

    @Override
    public int getItemCount() {
        return tweets.size();
    }

    public Tweet getTweet(int position) {
        return tweets.get(position);
    }

    public Tweet addTweet(Tweet tweet) {
        tweets.add(tweet);
        Collections.sort(tweets);
        notifyDataSetChanged();

        return tweet;
    }

    public Tweet removeTweet(int position) {
        return removeTweet(tweets.get(position));
    }

    public Tweet removeTweet(Tweet tweet) {
        tweets.remove(tweet);
        Collections.sort(tweets);
        notifyDataSetChanged();
        return tweet;
    }

    public void resortTweets() {
        Collections.sort(tweets);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        //TextView fields here
        TextView tweetTitle, tweetBody, tweetLikes;
        View itemView;
        public TableLayout viewForeground, viewBackground;

        public ViewHolder(View view) {
            super(view);
            this.itemView = view;
            //init view here
            tweetTitle = view.findViewById(R.id.tweetTitle);
            tweetBody = view.findViewById(R.id.tweetBody);
            tweetLikes = view.findViewById(R.id.tweetLikes);
            viewForeground = view.findViewById(R.id.foreground_view);
            viewBackground = view.findViewById(R.id.background_view);
        }

        public void bind(final Tweet tweet, final OnItemClickListener listener){

            tweetTitle.setText(tweet.getTweetTitle());
            tweetBody.setText(tweet.getTweetTitle());
            tweetLikes.setText("Likes " + tweet.getTweetLikes());
            //set data here
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(tweet);
                }
            });
        }
    }

    public interface OnItemClickListener {
        void onItemClick(Tweet item);
    }
}