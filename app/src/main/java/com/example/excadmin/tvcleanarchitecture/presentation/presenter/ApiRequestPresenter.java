package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import android.support.annotation.NonNull;
import android.util.Log;

import com.example.excadmin.tvcleanarchitecture.domain.interactor.DefaultObserver;
import com.example.excadmin.tvcleanarchitecture.domain.interactor.GetVideoList;
import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryList;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.LoadDataView;

import javax.inject.Inject;

/**
 * Created by excadmin on 2017/07/21.
 */

public class ApiRequestPresenter extends Presenter {

    private ApiRequestView mApiRequestView;
    private final GetVideoList getVideoListUseCase;

    @Inject
    public ApiRequestPresenter(GetVideoList getVideoListUseCase) {
        this.getVideoListUseCase = getVideoListUseCase;
    }

    public void setView(@NonNull ApiRequestView view) {
        this.mApiRequestView = view;
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

    public void onRequestClick(String key) {
        this.getVideoListUseCase.execute(new VideoListObserver(),null);
    }

    private final class VideoListObserver extends DefaultObserver<CategoryList> {

        @Override
        public void onComplete() {

        }

        @Override
        public void onError(Throwable e) {

        }

        @Override
        public void onNext(CategoryList categoryList) {
           Log.d("tes","tes");
        }
    }

    public interface ApiRequestView extends LoadDataView {
        void renderResult(String json);
    }

}
