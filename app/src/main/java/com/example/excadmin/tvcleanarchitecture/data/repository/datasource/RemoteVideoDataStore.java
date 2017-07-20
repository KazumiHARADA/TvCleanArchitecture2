package com.example.excadmin.tvcleanarchitecture.data.repository.datasource;

import com.example.excadmin.tvcleanarchitecture.data.api.RestApi;
import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by excadmin on 2017/07/18.
 */

public class RemoteVideoDataStore implements VideoDataStore{

    private final RestApi restApi;
    //private final UserCache userCache;

    RemoteVideoDataStore(RestApi restApi) {
        this.restApi = restApi;
        //this.userCache = userCache;
    }

    @Override
    public Observable<CategoryListEntity> videoEntityList() {
        return this.restApi.videoEntityList();
    }

    @Override
    public Observable<List<VideoEntity>> latestVideoEntityList(String category) {
        return this.restApi.latestVideoEntityList(category);
    }


    @Override
    public Observable<VideoEntity> videoEntityDetails(int videoId) {
        return null;
    }

}
