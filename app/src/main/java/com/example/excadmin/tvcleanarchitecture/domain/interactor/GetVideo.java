package com.example.excadmin.tvcleanarchitecture.domain.interactor;

import com.example.excadmin.tvcleanarchitecture.domain.executor.PostExecutionThread;
import com.example.excadmin.tvcleanarchitecture.domain.executor.ThreadExecutor;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.domain.repository.VideoRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by excadmin on 2017/07/19.
 */

public class GetVideo extends UseCase<Video,Integer> {

    private final VideoRepository videoRepository;

    @Inject
    GetVideo(VideoRepository videoRepository, ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.videoRepository = videoRepository;
    }

    @Override
    Observable<Video> buildUseCaseObservable(Integer contentId) {
        return this.videoRepository.video(contentId);
    }
}
