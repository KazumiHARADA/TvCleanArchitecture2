package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;

import com.example.excadmin.tvcleanarchitecture.domain.exception.DefaultErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.exception.ErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.DefaultObserver;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.GetLatestVideoList;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.GetVideo;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.presentation.exception.ErrorMessageFactory;
import com.example.excadmin.tvcleanarchitecture.presentation.mapper.VideoModelDataMapper;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.LoadDataView;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by excadmin on 2017/07/19.
 */

public class VideoPresenter extends Presenter{

    private VideoPresenter.VideoView mVideoView;
    private final GetVideo getVideoUseCase;
    private final GetLatestVideoList getLatestVideoListUseCase;
    private final VideoModelDataMapper videoModelDataMapper;

    @Inject
    public VideoPresenter(GetVideo getVideoUseCase,
                          GetLatestVideoList getLatestVideoListUseCase,
                              VideoModelDataMapper videoModelDataMapper) {
        this.getVideoUseCase = getVideoUseCase;
        this.getLatestVideoListUseCase = getLatestVideoListUseCase;
        this.videoModelDataMapper = videoModelDataMapper;
    }

    public void setView(@NonNull VideoPresenter.VideoView view) {
        this.mVideoView = view;
    }

    private void loadVideo() {
        this.hideViewRetry();
        this.showViewLoading();
        this.getVideo();
    }

    public void loadLatestVideos(String category) {
        this.hideViewRetry();
        this.showViewLoading();
        this.getLatestVideos(category);
    }

    private void showViewLoading() {
        this.mVideoView.showLoading();
    }

    private void hideViewLoading() {
        this.mVideoView.hideLoading();
    }

    private void showViewRetry() {
        this.mVideoView.showRetry();
    }

    private void hideViewRetry() {
        this.mVideoView.hideRetry();
    }

    private void showErrorMessage(ErrorBundle errorBundle) {
        String errorMessage = ErrorMessageFactory.create(this.mVideoView.context(),
                errorBundle.getException());
        this.mVideoView.showError(errorMessage);
    }

    private void getVideo() {

    }

    private void getLatestVideos(String category) {
        this.getLatestVideoListUseCase.execute(new LatestVideoObserver(),category);
    }

    private void renderLatestVideoList(List<Video> latestVideos) {
        this.mVideoView.renderLatestVideoList(latestVideos);
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

    }

    public void onVideoClicked(android.support.v17.leanback.widget.Presenter.ViewHolder itemViewHolder, Object item,
                               RowPresenter.ViewHolder rowViewHolder, Row row) {
        Video video = (Video) item;
        ImageCardView imageCardView = ((ImageCardView) itemViewHolder.view);

        this.mVideoView.viewVideo(video, imageCardView);
    }

    private final class LatestVideoObserver extends DefaultObserver<List<Video>> {
        @Override
        public void onComplete() {
            VideoPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            VideoPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(List<Video> latestVideos) {
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.renderLatestVideoList(latestVideos);
        }
    }


    public interface VideoView extends LoadDataView {
        void viewVideo(Video video, ImageCardView imageCardView);
        void renderVideo(Video video);
        void renderLatestVideoList(List<Video> latestVideos);
    }
}
