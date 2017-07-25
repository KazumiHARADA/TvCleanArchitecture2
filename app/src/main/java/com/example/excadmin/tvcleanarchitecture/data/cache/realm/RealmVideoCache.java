package com.example.excadmin.tvcleanarchitecture.data.cache.realm;

import com.example.excadmin.tvcleanarchitecture.data.cache.VideoCache;
import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.realm.RealmVideoEntity;
import com.example.excadmin.tvcleanarchitecture.data.mapper.realm.RealmVideoEntityMapper;
import com.example.excadmin.tvcleanarchitecture.data.repository.datasource.VideoDataStore;

import java.util.List;

import io.reactivex.Observable;
import io.realm.Realm;

/**
 * Created by excadmin on 2017/07/25.
 */

public class RealmVideoCache implements VideoCache,VideoDataStore{

    private Realm realm;

    private RealmVideoEntityMapper realmVideoEntityMapper;

    public RealmVideoCache(RealmVideoEntityMapper realmVideoEntityMapper) {
        realm = Realm.getDefaultInstance();
        this.realmVideoEntityMapper = realmVideoEntityMapper;
    }

    @Override
    public void saveVideo(long id, VideoEntity videoEntity) {

    }

    @Override
    public void saveVideos(List<VideoEntity> videoEntities) {
        realm.executeTransaction(realm1 -> {
            realm1.delete(RealmVideoEntity.class);
            realm1.copyFromRealm(realmVideoEntityMapper.map1(videoEntities));
        });
    }

    @Override
    public Observable<CategoryListEntity> videoEntityList() {
        return null;
    }
}
