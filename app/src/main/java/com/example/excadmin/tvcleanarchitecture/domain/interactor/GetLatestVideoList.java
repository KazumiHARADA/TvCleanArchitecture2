package com.example.excadmin.tvcleanarchitecture.domain.interactor;

import com.example.excadmin.tvcleanarchitecture.domain.executor.PostExecutionThread;
import com.example.excadmin.tvcleanarchitecture.domain.executor.ThreadExecutor;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.domain.repository.VideoRepository;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by excadmin on 2017/07/19.
 */

public class GetLatestVideoList extends UseCase<List<Video>,String> {

    private final VideoRepository videoRepository;

    @Inject
    GetLatestVideoList(VideoRepository videoRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.videoRepository = videoRepository;
    }

    @Override
    Observable<List<Video>> buildUseCaseObservable(String category) {
        return this.videoRepository.latestVideos(category);
    }
}

