package com.example.excadmin.tvcleanarchitecture.presentation.presenter;

import com.example.excadmin.tvcleanarchitecture.presentation.ui.LoadDataView;

import javax.inject.Inject;

/**
 * Created by excadmin on 2017/07/21.
 */

public class ApiRequestPresenter extends Presenter {

    @Inject
    public ApiRequestPresenter() {

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

    }

    public interface ApiRequestView extends LoadDataView {
        void renderResult(String json);
    }

}
