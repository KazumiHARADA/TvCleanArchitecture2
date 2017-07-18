package com.example.excadmin.tvcleanarchitecture.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by excadmin on 2017/07/18.
 */

public class CategoryListEntity {

    @SerializedName("googlevideos")
    List<CategoryVideoListEntity> googlevideos;

    public List<CategoryVideoListEntity> getGooglevideos() {
        return googlevideos;
    }
}
