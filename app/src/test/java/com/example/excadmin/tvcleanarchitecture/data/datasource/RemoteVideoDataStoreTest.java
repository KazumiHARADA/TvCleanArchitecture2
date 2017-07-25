package com.example.excadmin.tvcleanarchitecture.data.datasource;

import android.util.Log;

import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.repository.datasource.RemoteVideoDataStore;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by excadmin on 2017/07/25.
 */

@RunWith(MockitoJUnitRunner.class)
public class RemoteVideoDataStoreTest {

    private RemoteVideoDataStore videoDataStore;

    @Before
    public void setUp() {
        videoDataStore = new RemoteVideoDataStore();
    }

    @Test
    public void testGetEntityListFromApi() {
        Observable<CategoryListEntity> category = videoDataStore.videoEntityList();
        category.subscribe(new Consumer<CategoryListEntity>() {
            @Override
            public void accept(@NonNull CategoryListEntity categoryListEntity) throws Exception {
                Log.d("tag" ,categoryListEntity.toString());
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(@NonNull Throwable throwable) throws Exception {

            }
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
