package com.example.excadmin.tvcleanarchitecture.data.repository.datasource;

import com.example.excadmin.tvcleanarchitecture.data.api.APIClient;
import com.example.excadmin.tvcleanarchitecture.data.api.ServiceGenerator;
import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.util.ObservableGenerator;

import io.reactivex.Observable;
import retrofit2.Call;

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
        Call<CategoryListEntity> call = client.videoEntityList();
        ObservableGenerator<CategoryListEntity> generator = new ObservableGenerator<>();
        return generator.generate(call);
    }

}
