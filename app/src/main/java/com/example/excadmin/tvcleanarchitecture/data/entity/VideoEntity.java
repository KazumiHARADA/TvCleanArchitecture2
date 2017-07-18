package com.example.excadmin.tvcleanarchitecture.data.entity;

import com.google.gson.annotations.SerializedName;

/**
 * Created by excadmin on 2017/07/18.
 */

public class VideoEntity {
    @SerializedName("id")
    public long id;

    @SerializedName("category")
    public String category;

    @SerializedName("title")
    public String title;

    @SerializedName("description")
    public String description;

    @SerializedName("bgImageUrl")
    public String bgImageUrl;

    @SerializedName("cardImageUrl")
    public String cardImageUrl;

    @SerializedName("videoUrl")
    public String videoUrl;

    @SerializedName("studio")
    public String studio;

    public long getId() {
        return id;
    }

    public String getCategory() {
        return category;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getBgImageUrl() {
        return bgImageUrl;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public String getVideoUrl() {
        return videoUrl;
    }

    public String getStudio() {
        return studio;
    }
}
