package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.Action;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.SparseArrayObjectAdapter;

import com.example.excadmin.tvcleanarchitecture.R;
import com.example.excadmin.tvcleanarchitecture.domain.exception.DefaultErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.exception.ErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.DefaultObserver;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.GetVideoList;
import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryList;
import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryVideoList;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.presentation.exception.ErrorMessageFactory;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.LoadDataView;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.VideoDetailsActivity;

import java.util.List;

import javax.inject.Inject;

/**
 * Created by excadmin on 2017/07/19.
 */

public class VideoPresenter extends Presenter{

    private VideoPresenter.VideoView mVideoView;
    private final GetVideoList getVideoListUseCase;

    private String mSelectedCategory;

    private static final int NO_NOTIFICATION = -1;
    private static final int ACTION_WATCH_TRAILER = 1;
    private static final int ACTION_RENT = 2;
    private static final int ACTION_BUY = 3;

    @Inject
    public VideoPresenter(GetVideoList getLatestVideoListUseCase) {
        this.getVideoListUseCase = getLatestVideoListUseCase;
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
        mSelectedCategory = category;

        this.getVideoListUseCase.execute(new LatestVideoObserver(),null);
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

    public void removeNotification(Intent intent) {

        int notificationId = intent.getIntExtra(VideoDetailsActivity.NOTIFICATION_ID, NO_NOTIFICATION);

        if (notificationId != NO_NOTIFICATION) {
            NotificationManager notificationManager = (NotificationManager) this.mVideoView.context()
                    .getSystemService(Context.NOTIFICATION_SERVICE);
            notificationManager.cancel(notificationId);
        }
    }

    public SparseArrayObjectAdapter getActionAdapter() {
        SparseArrayObjectAdapter adapter = new SparseArrayObjectAdapter();

        adapter.set(ACTION_WATCH_TRAILER, new Action(ACTION_WATCH_TRAILER,this.mVideoView.context().getResources()
                .getString(R.string.watch_trailer_1),
                this.mVideoView.context().getResources().getString(R.string.watch_trailer_2)));
        adapter.set(ACTION_RENT, new Action(ACTION_RENT, this.mVideoView.context().getResources().getString(R.string.rent_1),
                this.mVideoView.context().getResources().getString(R.string.rent_2)));
        adapter.set(ACTION_BUY, new Action(ACTION_BUY,this.mVideoView.context().getResources().getString(R.string.buy_1),
                this.mVideoView.context().getResources().getString(R.string.buy_2)));
        return adapter;
    }

    public void onVideoClicked(android.support.v17.leanback.widget.Presenter.ViewHolder itemViewHolder, Object item,
                               RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (item instanceof Video) {
            Video video = (Video) item;
            ImageCardView imageCardView = ((ImageCardView) itemViewHolder.view);

            this.mVideoView.viewVideo(video, imageCardView);
        }
    }

    public void onWatchTrailerClicked(Action action, Video video) {
        if (action.getId() == ACTION_WATCH_TRAILER) {
            this.mVideoView.viewTrailer(video);
        } else {
            this.mVideoView.showError(action.toString());
        }
    }

    private final class LatestVideoObserver extends DefaultObserver<CategoryList> {
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
        public void onNext(CategoryList categoryList) {
            for (CategoryVideoList categoryVideoList : categoryList.getGooglevideos()) {
                if (categoryVideoList.getCategory().contains(mSelectedCategory)) {
                    VideoPresenter.this.hideViewLoading();
                    VideoPresenter.this.renderLatestVideoList(categoryVideoList.getVideos());
                    return;
                }
            }
            VideoPresenter.this.hideViewLoading();
            VideoPresenter.this.showViewRetry();
        }
    }


    public interface VideoView extends LoadDataView {
        void viewVideo(Video video, ImageCardView imageCardView);
        void viewTrailer(Video video);
        void renderLatestVideoList(List<Video> latestVideos);
    }
}
