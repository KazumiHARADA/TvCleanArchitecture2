package com.example.excadmin.tvcleanarchitecture.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by excadmin on 2017/07/18.
 */

public class CategoryVideoListEntity {

    @SerializedName("category")
    private String category;

    @SerializedName("videos")
    private List<VideoEntity> videos;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<VideoEntity> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoEntity> videos) {
        this.videos = videos;
    }
}
