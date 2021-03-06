package com.example.excadmin.tvcleanarchitecture.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by excadmin on 2017/07/18.
 */

public class VideoEntity {
    @SerializedName("id")
    private long id;

    @SerializedName("category")
    private String category;

    @SerializedName("title")
    private String title;

    @SerializedName("description")
    private String description;

    @SerializedName("background")
    private String bgImageUrl;

    @SerializedName("card")
    private String cardImageUrl;

    @SerializedName("sources")
    private List<String> videoUrl;

    @SerializedName("studio")
    private String studio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBgImageUrl() {
        return bgImageUrl;
    }

    public void setBgImageUrl(String bgImageUrl) {
        this.bgImageUrl = bgImageUrl;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public List<String> getVideoUrl() {
        return videoUrl;
    }

    public void setVideoUrls(List<String> videoUrl) {
        this.videoUrl = videoUrl;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
}
