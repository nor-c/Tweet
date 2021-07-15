package com.example.tweet.model;

import java.util.Objects;

public class Tweet implements Comparable<Tweet>{
    private String tweetTitle;//the title is unique so we use as the id
    private String tweetBody;
    private int tweetLikes;

    public Tweet(String tweetTitle, String tweetBody) {
        this(tweetTitle, tweetBody, 0);
    }

    public Tweet(String tweetTitle, String tweetBody, int tweetLikes) {
        this.tweetTitle = tweetTitle;
        this.tweetBody = tweetBody;
        this.tweetLikes = tweetLikes;
    }

    public String getTweetTitle() {
        return tweetTitle;
    }

    public void setTweetTitle(String tweetTitle) {
        this.tweetTitle = tweetTitle;
    }

    public String getTweetBody() {
        return tweetBody;
    }

    public void setTweetBody(String tweetBody) {
        this.tweetBody = tweetBody;
    }

    public int getTweetLikes() {
        return tweetLikes;
    }

    public void setTweetLikes(int tweetLikes) {
        this.tweetLikes = tweetLikes;
    }

    public int addLike() {
        return tweetLikes++;
    }

    @Override
    public int compareTo(Tweet tweet) {
        return tweet.tweetLikes - tweetLikes;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Tweet tweet = (Tweet) o;

        return Objects.equals(tweetTitle, tweet.tweetTitle);
    }

    @Override
    public int hashCode() {
        return tweetTitle != null ? tweetTitle.hashCode() : 0;
    }
}
