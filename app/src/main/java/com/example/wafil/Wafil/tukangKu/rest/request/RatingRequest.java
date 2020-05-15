package com.example.wafil.Wafil.tukangKu.rest.request;

import com.google.gson.annotations.SerializedName;

public class RatingRequest  {
    @SerializedName("userId")
    private int userId;

    @SerializedName("tukangId")
    private int tukangId;

    @SerializedName("rating")
    private int rating;

    @SerializedName("review")
    private String review;

    public RatingRequest setUserId(int userId) {
        this.userId = userId;
        return this;
    }

    public RatingRequest setTukangId(int tukangId) {
        this.tukangId = tukangId;
        return this;
    }

    public RatingRequest setRating(int rating) {
        this.rating = rating;
        return this;
    }

    public RatingRequest setReview(String review) {
        this.review = review;
        return this;
    }
}
