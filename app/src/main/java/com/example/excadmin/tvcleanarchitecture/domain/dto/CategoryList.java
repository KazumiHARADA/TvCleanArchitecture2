package com.example.excadmin.tvcleanarchitecture.domain.dto;

import java.util.List;

/**
 * Created by excadmin on 2017/07/19.
 */

public class CategoryList {

    List<CategoryVideoList> googlevideos;

    public CategoryList(List<CategoryVideoList> googlevideos) {
        this.googlevideos = googlevideos;
    }

    public List<CategoryVideoList> getGooglevideos() {
        return googlevideos;
    }
}
