package com.example.excadmin.tvcleanarchitecture.data.mapper.realm;

import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.realm.RealmStringEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.realm.RealmVideoEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.realm.RealmList;
import io.realm.RealmObject;

/**
 * Created by excadmin on 2017/07/25.
 */

public class RealmVideoEntityMapper extends BaseRealmMapper<RealmVideoEntity,VideoEntity>{

    @Inject
    public RealmVideoEntityMapper() {}

    @Override
    public RealmVideoEntity map1(VideoEntity o2) {
        RealmVideoEntity entity = new RealmVideoEntity();
        entity.setId(o2.getId());
        entity.setTitle(o2.getTitle());
        entity.setBgImageUrl(o2.getBgImageUrl());
        entity.setCardImageUrl(o2.getCardImageUrl());
        entity.setCategory(o2.getCategory());
        entity.setDescription(o2.getDescription());
        entity.setStudio(o2.getStudio());

        RealmList<RealmObject> list = new RealmList<>();
        for (String videoUrl : o2.getVideoUrl()) {
            RealmStringEntity stringEntity = new RealmStringEntity();
            stringEntity.setString(videoUrl);
            list.add(stringEntity);
        }

        entity.setVideoUrls(list);
        return entity;
    }

    @Override
    public VideoEntity map2(RealmVideoEntity o1) {
        VideoEntity entity = new VideoEntity();

        entity.setId(o1.getId());
        entity.setTitle(o1.getTitle());
        entity.setBgImageUrl(o1.getBgImageUrl());
        entity.setCardImageUrl(o1.getCardImageUrl());
        entity.setCategory(o1.getCategory());
        entity.setDescription(o1.getDescription());
        entity.setStudio(o1.getStudio());

        List<String> list = new ArrayList<>();
        for (RealmObject videoUrl : o1.getVideoUrls()) {
            list.add(((RealmStringEntity)videoUrl).getString());
        }

        entity.setVideoUrls(list);
        return entity;
    }
}
