package com.example.excadmin.tvcleanarchitecture.data.repository.datasource;

import android.util.Log;

import com.example.excadmin.tvcleanarchitecture.data.api.APIClient;
import com.example.excadmin.tvcleanarchitecture.data.api.ServiceGenerator;
import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.mapper.realm.RealmVideoEntityMapper;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by excadmin on 2017/07/18.
 */

public class RemoteVideoDataStore implements VideoDataStore{

    //private final RestApi restApi;
    //private final UserCache userCache;

    private final RealmVideoEntityMapper realmMapper;

    @Inject
    RemoteVideoDataStore(RealmVideoEntityMapper realmMapper) {
        this.realmMapper = realmMapper;
    }

    @Override
    public Observable<CategoryListEntity> videoEntityList() {
        APIClient client = ServiceGenerator.createService(APIClient.class);
        return client.videoEntityList().doOnNext(categoryListEntity -> {

            Log.d("test","tet");
        });
    }

}
