package com.example.excadmin.tvcleanarchitecture.data.api;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryVideoListEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.mapper.VideoEntityJsonMapper;
import com.example.excadmin.tvcleanarchitecture.data.exception.NetworkConnectionException;

import java.net.MalformedURLException;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * Created by excadmin on 2017/07/18.
 */

public class RestApiImpl implements RestApi {

    private final Context context;
    private final VideoEntityJsonMapper videoEntityJsonMapper;

    public RestApiImpl(Context context, VideoEntityJsonMapper videoEntityJsonMapper) {
        if (context == null || videoEntityJsonMapper == null) {
            throw new IllegalArgumentException("The constructor parameters cannot be null!!!");
        }
        this.context = context.getApplicationContext();
        this.videoEntityJsonMapper = videoEntityJsonMapper;
    }

    @Override
    public Observable<CategoryListEntity> videoEntityList() {
        Single<CategoryListEntity> single = Single.create(singleSubscribeOn -> {
            if (isThereInternetConnection()) {
                String responseVideoList = getUserEntitiesFromApi();
                if (responseVideoList != null) {
                    singleSubscribeOn.onSuccess(videoEntityJsonMapper.transformVideoEntityCollection(responseVideoList));
                } else {
                    singleSubscribeOn.onError(new NetworkConnectionException());
                }
            } else {
                singleSubscribeOn.onError(new NetworkConnectionException());
            }
        });
        return single.toObservable();
    }

    @Override
    public Observable<List<VideoEntity>> latestVideoEntityList(String category) {
        Single<List<VideoEntity>> single = Single.create(singleSubscribeOn -> {
            if (isThereInternetConnection()) {
                String responseVideoList = getUserEntitiesFromApi();
                if (responseVideoList != null) {

                    CategoryListEntity categoryList = videoEntityJsonMapper.transformVideoEntityCollection(responseVideoList);

                    for (CategoryVideoListEntity categoryVideoListEntity : categoryList.getGooglevideos()) {
                        if (categoryVideoListEntity.getCategory().contains(category)) {
                            singleSubscribeOn.onSuccess(categoryVideoListEntity.getVideos());
                            return;
                        }
                    }
                    singleSubscribeOn.onError(new NetworkConnectionException());

                } else {
                    singleSubscribeOn.onError(new NetworkConnectionException());
                }
            } else {
                singleSubscribeOn.onError(new NetworkConnectionException());
            }
        });
        return single.toObservable();
    }

    private String getUserEntitiesFromApi() throws MalformedURLException {
        return ApiConnection.createGET(API_URL_GET_VIDEO_LIST).requestSyncCall();
    }

    private boolean isThereInternetConnection() {
        boolean isConnected;

        ConnectivityManager connectivityManager =
                (ConnectivityManager) this.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
        isConnected = (networkInfo != null && networkInfo.isConnectedOrConnecting());

        return isConnected;
    }
}
