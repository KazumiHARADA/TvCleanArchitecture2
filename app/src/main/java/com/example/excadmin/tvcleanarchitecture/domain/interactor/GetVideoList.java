package com.example.excadmin.tvcleanarchitecture.domain.interactor;

import com.example.excadmin.tvcleanarchitecture.domain.executor.PostExecutionThread;
import com.example.excadmin.tvcleanarchitecture.domain.executor.ThreadExecutor;
import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryList;
import com.example.excadmin.tvcleanarchitecture.domain.repository.VideoRepository;

import javax.inject.Inject;

import io.reactivex.Observable;

/**
 * Created by haradakazumi on 2017/07/15.
 */

public class GetVideoList extends UseCase<CategoryList,Void> {

    private final VideoRepository videoRepository;

    @Inject
    GetVideoList(VideoRepository videoRepository,ThreadExecutor threadExecutor, PostExecutionThread postExecutionThread) {
        super(threadExecutor, postExecutionThread);
        this.videoRepository = videoRepository;
    }

    @Override
    Observable<CategoryList> buildUseCaseObservable(Void aVoid) {
        return this.videoRepository.videos();
    }
}
