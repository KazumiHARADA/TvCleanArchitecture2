package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import android.support.annotation.NonNull;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.view.View;

import com.example.excadmin.tvcleanarchitecture.R;
import com.example.excadmin.tvcleanarchitecture.domain.exception.DefaultErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.exception.ErrorBundle;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.DefaultObserver;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.GetVideoList;
import com.example.excadmin.tvcleanarchitecture.domain.dto.CategoryList;
import com.example.excadmin.tvcleanarchitecture.domain.dto.Video;
import com.example.excadmin.tvcleanarchitecture.presentation.exception.ErrorMessageFactory;
import com.example.excadmin.tvcleanarchitecture.presentation.mapper.VideoModelDataMapper;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.LoadDataView;

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
        this.getVideoList();
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

    private void renderVideoList(CategoryList categoryList) {
        this.mVideoListView.renderVideoList(categoryList);
    }

    public void onVideoClicked(android.support.v17.leanback.widget.Presenter.ViewHolder itemViewHolder, Object item,
                               RowPresenter.ViewHolder rowViewHolder, Row row) {
        if (item instanceof Video) {
            Video video = (Video) item;
            ImageCardView imageCardView = ((ImageCardView) itemViewHolder.view);

            this.mVideoListView.viewVideo(video, imageCardView);

        } else if (item instanceof String) {
            if (((String) item).contains(this.mVideoListView.context().getString(R.string.grid_view))) {
                this.mVideoListView.viewGrid();
            } else if (((String) item).contains(this.mVideoListView.context().getString(R.string.guidedstep_first_title))) {
                this.mVideoListView.viewGuide();
            } else if (((String) item).contains(this.mVideoListView.context().getString(R.string.error_fragment))) {
                this.mVideoListView.viewError();
            } else if (((String) item).contains(this.mVideoListView.context().getString(R.string.personal_settings))) {
                this.mVideoListView.viewSetting();
            } else if (((String) item).contains(this.mVideoListView.context().getString(R.string.api_requests))) {
                this.mVideoListView.viewApiRequests();
            } else {
                this.mVideoListView.viewError();
            }
        }
    }

    public void onSearchClicked(View view) {
        this.mVideoListView.viewSearch();
    }


    private void getVideoList() {
        this.getVideoListUseCase.execute(new VideoListObserver(), null);
    }

    private final class VideoListObserver extends DefaultObserver<CategoryList> {

        @Override
        public void onComplete() {
            VideoListPresenter.this.hideViewLoading();
        }

        @Override
        public void onError(Throwable e) {
            VideoListPresenter.this.hideViewLoading();
            VideoListPresenter.this.showErrorMessage(new DefaultErrorBundle((Exception) e));
            VideoListPresenter.this.showViewRetry();
        }

        @Override
        public void onNext(CategoryList categoryList) {
            VideoListPresenter.this.hideViewLoading();
            VideoListPresenter.this.renderVideoList(categoryList);
        }
    }

    public interface VideoListView extends LoadDataView {
        /**
         * Render a user list in the UI.
         *
         * @param videoModelCollection The collection of {@link Video} that will be shown.
         */
        void renderVideoList(CategoryList videoModelCollection);

        /**
         * View a {@link Video} profile/details.
         *
         * @param video The user that will be shown.
         */
        void viewVideo(Video video, ImageCardView imageCardView);

        void viewError();

        void viewSetting();

        void viewGuide();

        void viewGrid();

        void viewSearch();

        void viewApiRequests();

    }
}
