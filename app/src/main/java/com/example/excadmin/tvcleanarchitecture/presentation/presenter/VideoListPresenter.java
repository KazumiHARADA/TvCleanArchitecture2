package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.excadmin.tvcleanarchitecture.domain.exception.DefaultErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.exception.ErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.DefaultObserver;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.GetVideoList;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.presentation.exception.ErrorMessageFactory;
import com.example.excadmin.tvcleanarchitecture.presentation.mapper.VideoModelDataMapper;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.VideoListView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by haradakazumi on 2017/07/15.
 */

public class VideoListPresenter extends Presenter {

    private VideoListView mVideoListView;
    private final GetVideoList getVideoListUseCase;
    private final VideoModelDataMapper videoModelDataMapper;

    @Inject
    public VideoListPresenter(GetVideoList getVideoListUseCase,
                              VideoModelDataMapper videoModelDataMapper) {
        this.getVideoListUseCase = getVideoListUseCase;
        this.videoModelDataMapper = videoModelDataMapper;
    }

    public void setView(@NonNull VideoListView view) {
        this.mVideoListView = view;
    }

    @Override
    public void initialize() {
        this.loadUserList();
    }

    @Override
    public void resume() {

    }

    @Override
    public void pause() {

    }

    @Override
    public void destroy() {
        this.getVideoListUseCase.dispose();
        this.mVideoListView = null;
    }

    private void loadUserList() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList();
    }

    private void showViewLoading() {
        this.mVideoListView.showLoading();
    }

    private void hideViewLoading() {
        this.mVideoListView.hideLoading();
    }

    private void showViewRetry() {
        this.mVideoListView.showRetry();
    }

    private void hideViewRetry() {
        this.mVideoListView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.mVideoListView.context(),
                errorBundle.getException());
        this.mVideoListView.showError(errorMessage);
    }


    private void getUserList() {
        this.getVideoListUseCase.execute(new VideoListObserver(), null);
    }

    private final class VideoListObserver extends DefaultObserver<List<Video>> {

        @Override public void onComplete() {
            VideoListPresenter.this.hideViewLoading();
        }

        @Override public void onError(Throwable e) {
            VideoListPresenter.this.hideViewLoading();
            VideoListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            VideoListPresenter.this.showViewRetry();
        }

        @Override public void onNext(List<Video> users) {
            //VideoListPresenter.this.(users);
            Log.d("kita","kita");
        }
    }
}
