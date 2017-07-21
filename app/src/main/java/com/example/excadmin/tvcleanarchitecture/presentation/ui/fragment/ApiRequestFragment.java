package com.example.excadmin.tvcleanarchitecture.presentation.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v17.leanback.widget.VerticalGridPresenter;
import android.view.View;

import com.example.excadmin.tvcleanarchitecture.R;
import com.example.excadmin.tvcleanarchitecture.presentation.presenter.ApiRequestPresenter;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.ApiRequestActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.SearchActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.viewpresenter.ApiPresenter;

import javax.inject.Inject;

/**
 * Created by excadmin on 2017/07/21.
 */

public class ApiRequestFragment extends android.support.v17.leanback.app.VerticalGridFragment implements ApiRequestPresenter.ApiRequestView{

    private static final int NUM_COLUMNS = 5;
    private final ArrayObjectAdapter mApiListAdapter =
            new ArrayObjectAdapter(new ApiPresenter());

    @Inject
    ApiRequestPresenter apiRequestPresenter;

    @Inject
    Context context;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((ApiRequestActivity) getActivity()).getComponent().inject(this);

        mApiListAdapter.add("login");
        mApiListAdapter.add("list");
        setAdapter(mApiListAdapter);

        setTitle(getString(R.string.vertical_grid_title));

        if (savedInstanceState == null) {
            prepareEntranceTransition();
        }
        setupFragment();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.apiRequestPresenter.setView(this);
        if (savedInstanceState == null) {
            this.apiRequestPresenter.initialize();
        }
    }

    private void setupFragment() {
        VerticalGridPresenter gridPresenter = new VerticalGridPresenter();
        gridPresenter.setNumberOfColumns(NUM_COLUMNS);
        setGridPresenter(gridPresenter);

        // After 500ms, start the animation to transition the cards into view.
        new Handler().postDelayed(this::startEntranceTransition, 500);

        setOnSearchClickedListener(view -> {
            Intent intent = new Intent(getActivity(), SearchActivity.class);
            startActivity(intent);
        });

        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showRetry() {

    }

    @Override
    public void hideRetry() {

    }

    @Override
    public void showError(String message) {

    }

    @Override
    public Context context() {
        return null;
    }

    @Override
    public void renderResult(String json) {

    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {

            apiRequestPresenter.onRequestClick((String) item);
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
        }
    }

}
