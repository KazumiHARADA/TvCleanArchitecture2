package com.example.excadmin.tvcleanarchitecture.domain.dto;

import java.util.List;

/**
 * Created by excadmin on 2017/07/19.
 */

public class CategoryVideoList {

    String category;
    List<Video> videos;

    public CategoryVideoList(String category, List<Video> videos) {
        this.category = category;
        this.videos = videos;
    }

    public String getCategory() {
        return category;
    }

    public List<Video> getVideos() {
        return videos;
    }

}
