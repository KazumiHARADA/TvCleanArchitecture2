package com.example.excadmin.tvcleanarchitecture.data.repository.datasource;

import com.example.excadmin.tvcleanarchitecture.data.api.APIClient;
import com.example.excadmin.tvcleanarchitecture.data.api.ServiceGenerator;
import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;

import io.reactivex.Observable;

/**
 * Created by excadmin on 2017/07/18.
 */

public class RemoteVideoDataStore implements VideoDataStore{

    //private final RestApi restApi;
    //private final UserCache userCache;

    RemoteVideoDataStore() {
        //this.restApi = restApi;
        //this.userCache = userCache;
    }

    @Override
    public Observable<CategoryListEntity> videoEntityList() {
        APIClient client = ServiceGenerator.createService(APIClient.class);
        return client.videoEntityList();
    }

}
