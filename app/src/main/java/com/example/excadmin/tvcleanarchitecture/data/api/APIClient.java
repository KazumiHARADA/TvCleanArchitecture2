package com.example.excadmin.tvcleanarchitecture.data.api;

import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by excadmin on 2017/07/21.
 */

public interface APIClient {

    @GET("/android-tv/android_tv_videos_new.json")
    Call<CategoryListEntity> videoEntityList();
}
