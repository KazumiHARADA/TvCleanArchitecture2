package com.example.excadmin.tvcleanarchitecture.data.repository;

import com.example.excadmin.tvcleanarchitecture.data.mapper.VideoEntityDtoMapper;
import com.example.excadmin.tvcleanarchitecture.data.repository.datasource.VideoDataStore;
import com.example.excadmin.tvcleanarchitecture.data.repository.datasource.VideoDataStoreFactory;
import com.example.excadmin.tvcleanarchitecture.domain.dto.CategoryList;
import com.example.excadmin.tvcleanarchitecture.domain.repository.VideoRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by excadmin on 2017/07/18.
 */

public class VideoDataRepository implements VideoRepository {

    private final VideoDataStoreFactory dataStoreFactory;
    private final VideoEntityDtoMapper videoEntityDtoMapper;

    @Inject
    VideoDataRepository(VideoDataStoreFactory dataStoreFactory,
                        VideoEntityDtoMapper videoEntityDtoMapper) {
        this.dataStoreFactory = dataStoreFactory;
        this.videoEntityDtoMapper = videoEntityDtoMapper;
    }

    @Override
    public Observable<CategoryList> videos() {
        final VideoDataStore videoDataStore = this.dataStoreFactory.create();
        return videoDataStore.videoEntityList().map(this.videoEntityDtoMapper::transform);
    }
}
