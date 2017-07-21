package com.example.excadmin.tvcleanarchitecture.data.repository;

import com.example.excadmin.tvcleanarchitecture.data.entity.mapper.VideoEntityDataMapper;
import com.example.excadmin.tvcleanarchitecture.data.repository.datasource.VideoDataStore;
import com.example.excadmin.tvcleanarchitecture.data.repository.datasource.VideoDataStoreFactory;
import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryList;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.domain.repository.VideoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by excadmin on 2017/07/18.
 */

public class VideoDataRepository implements VideoRepository {

    private final VideoDataStoreFactory dataStoreFactory;
    private final VideoEntityDataMapper videoEntityDataMapper;

    @Inject
    VideoDataRepository(VideoDataStoreFactory dataStoreFactory,
                        VideoEntityDataMapper videoEntityDataMapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.videoEntityDataMapper = videoEntityDataMapper;
    }

    @Override
    public Observable<CategoryList> videos() {
        final VideoDataStore videoDataStore = this.dataStoreFactory.create();
        return videoDataStore.videoEntityList().map(this.videoEntityDataMapper::transform);
    }

    @Override
    public Observable<CategoryList> videosService() {
        final VideoDataStore videoDataStore = this.dataStoreFactory.create();
        return videoDataStore.videoEntityServiceList().map(this.videoEntityDataMapper::transform);
    }

    @Override
    public Observable<List<Video>> latestVideos(String category) {
        final VideoDataStore videoDataStore = this.dataStoreFactory.create();
        return videoDataStore.latestVideoEntityList(category).map(this.videoEntityDataMapper::transform);
    }

    @Override
    public Observable<Video> video(int contentId) {
        return null;
    }
}
