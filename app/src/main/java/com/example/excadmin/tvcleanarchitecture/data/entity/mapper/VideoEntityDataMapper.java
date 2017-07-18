package com.example.excadmin.tvcleanarchitecture.data.entity.mapper;

import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by excadmin on 2017/07/18.
 */

public class VideoEntityDataMapper {

    @Inject VideoEntityDataMapper(){}

    public Video transform(VideoEntity videoEntity) {
        Video video = null;
        if (videoEntity != null) {
            video = new Video(
                    videoEntity.getId(),
                    videoEntity.getCategory(),
                    videoEntity.getTitle(),
                    videoEntity.getDescription(),
                    videoEntity.getVideoUrl(),
                    videoEntity.getBgImageUrl(),
                    videoEntity.getCardImageUrl(),
                    videoEntity.getStudio()
            );
        }
        return video;
    }

    public List<Video> transform(Collection<VideoEntity> videoEntityCollection) {
        final List<Video> videoList = new ArrayList<>();
        for (VideoEntity videoEntity : videoEntityCollection) {
            final Video user = transform(videoEntity);
            if (user != null) {
                videoList.add(user);
            }
        }
        return videoList;
    }


}
