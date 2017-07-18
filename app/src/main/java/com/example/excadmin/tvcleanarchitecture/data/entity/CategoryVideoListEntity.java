package com.example.excadmin.tvcleanarchitecture.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by excadmin on 2017/07/18.
 */

public class CategoryVideoListEntity {

    @SerializedName("category")
    String category;

    @SerializedName("videos")
    List<VideoEntity> videos;

    public String getCategory() {
        return category;
    }

    public List<VideoEntity> getVideos() {
        return videos;
    }
}
