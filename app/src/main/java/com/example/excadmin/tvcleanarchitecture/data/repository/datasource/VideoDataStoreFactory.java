package com.example.excadmin.tvcleanarchitecture.data.repository.datasource;

import android.content.Context;
import android.support.annotation.NonNull;

import javax.inject.Inject;

/**
 * Created by excadmin on 2017/07/18.
 */

public class VideoDataStoreFactory {

    private final Context context;

    @Inject
    VideoDataStoreFactory(@NonNull Context context) {
                       //UserEntityDataMapper userEntityDataMapper) {
        this.context = context;
    }

    public VideoDataStore create() {
        VideoDataStore userDataStore;

        userDataStore = new LocalVideoDataStore(context);

//        if (!this.userCache.isExpired() && this.userCache.isCached(userId)) {
//            userDataStore = new LocalVideoDataStore(this.userCache);
//        } else {
//            userDataStore = createCloudDataStore();
//        }

        return userDataStore;
    }

//    public VideoDataStore createRemoteDataStore() {
//        final UserEntityJsonMapper userEntityJsonMapper = new UserEntityJsonMapper();
//        final RestApi restApi = new RestApiImpl(this.context, userEntityJsonMapper);
//
//        return new CloudUserDataStore(restApi, this.userCache);
//    }
}

