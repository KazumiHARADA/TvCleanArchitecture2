package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import android.support.annotation.NonNull;

import com.example.excadmin.tvcleanarchitecture.domain.exception.DefaultErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.exception.ErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.DefaultObserver;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.GetVideoList;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.presentation.exception.ErrorMessageFactory;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.VideoListView;

import java.util.Collection;
import java.util.List;

/**
 * Created by haradakazumi on 2017/07/15.
 */

public class VideoListPresenter extends Presenter {

    private VideoListView mVideoListView;

    private final GetVideoList getVideoListUseCase;

    public VideoListPresenter(GetVideoList videoList) {
        this.getVideoListUseCase = videoList;
    }

    public void setView(@NonNull VideoListView view) {
        mVideoListView = view;
    }

    @Override
    public void initialize() {

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

    private void loadVideoList(){
        this.hideViewRetry();
        this.showViewLoading();
        this.getUserList();
    }

    public void onVideoClicked(Video video) {
        //this.viewListView.viewUser(userModel);
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

    private void showUsersCollectionInView(Collection<Video> videosCollection) {
        this.mVideoListView.renderVideoList(videosCollection);
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
            VideoListPresenter.this.showUsersCollectionInView(users);
        }
    }
}
