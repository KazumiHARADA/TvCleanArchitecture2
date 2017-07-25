package com.example.excadmin.tvcleanarchitecture.data.mapper;

import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryVideoListEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;
import com.example.excadmin.tvcleanarchitecture.domain.dto.CategoryList;
import com.example.excadmin.tvcleanarchitecture.domain.dto.CategoryVideoList;
import com.example.excadmin.tvcleanarchitecture.domain.dto.Video;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by excadmin on 2017/07/18.
 */

public class VideoEntityDtoMapper {

    @Inject
    VideoEntityDtoMapper(){}

    public CategoryList transform(CategoryListEntity categoryListEntity) {
        CategoryList categoryList = null;
        if (categoryListEntity != null) {
            categoryList = new CategoryList(
                    transformCategoryVideoListEntity(categoryListEntity.getGooglevideos())
            );
        }
        return categoryList;
    }

    public List<CategoryVideoList> transformCategoryVideoListEntity(List<CategoryVideoListEntity> categoryListEntities) {
        final List<CategoryVideoList> categoryVideoListSummary = new ArrayList<>();
        for (CategoryVideoListEntity videoEntity : categoryListEntities) {
            final CategoryVideoList categoryVideo = transform(videoEntity);
            if (categoryVideo != null) {
                categoryVideoListSummary.add(categoryVideo);
            }
        }
        return categoryVideoListSummary;
    }

    public CategoryVideoList transform(CategoryVideoListEntity categoryVideoListEntity) {
        CategoryVideoList categoryVideoList = null;
        if (categoryVideoListEntity != null) {
            categoryVideoList = new CategoryVideoList(
                    categoryVideoListEntity.getCategory(),
                    transform(categoryVideoListEntity.getVideos())
            );
        }
        return categoryVideoList;
    }

    public List<Video> transform(List<VideoEntity> videoEntities) {
        final List<Video> videoList = new ArrayList<>();
        for (VideoEntity videoEntity : videoEntities) {
            final Video user = transform(videoEntity);
            if (user != null) {
                videoList.add(user);
            }
        }
        return videoList;
    }

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
}
