package com.example.excadmin.tvcleanarchitecture.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

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

    @SerializedName("background")
    public String bgImageUrl;

    @SerializedName("card")
    public String cardImageUrl;

    @SerializedName("sources")
    public List<String> videoUrl;

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

    public List<String> getVideoUrl() {
        return videoUrl;
    }

    public String getStudio() {
        return studio;
    }
}
