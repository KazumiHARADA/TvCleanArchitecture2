package com.example.excadmin.tvcleanarchitecture.data.cache;

import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;

import java.util.List;

/**
 * Created by excadmin on 2017/07/25.
 */

public interface VideoCache {
    void saveVideo(long id, VideoEntity videoEntity);

    void saveVideos(List<VideoEntity> videoEntities);
}
