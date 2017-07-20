package com.example.excadmin.tvcleanarchitecture.data.api;

import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by excadmin on 2017/07/18.
 */

public interface RestApi {
    String API_BASE_URL =
            "https://storage.googleapis.com/android-tv/";

    /**
     * Api url for getting all users
     */
    String API_URL_GET_VIDEO_LIST = API_BASE_URL + "android_tv_videos_new.json";


    Observable<CategoryListEntity> videoEntityList();

    Observable<List<VideoEntity>> latestVideoEntityList(String category);
}
