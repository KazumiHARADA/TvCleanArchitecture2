/*
 * Copyright (c) 2014 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.excadmin.tvcleanarchitecture.presentation.ui.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.support.v17.leanback.app.BackgroundManager;
import android.support.v17.leanback.app.BrowseFragment;
import android.support.v17.leanback.widget.ArrayObjectAdapter;
import android.support.v17.leanback.widget.HeaderItem;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v17.leanback.widget.ListRow;
import android.support.v17.leanback.widget.ListRowPresenter;
import android.support.v17.leanback.widget.OnItemViewClickedListener;
import android.support.v17.leanback.widget.OnItemViewSelectedListener;
import android.support.v17.leanback.widget.Presenter;
import android.support.v17.leanback.widget.PresenterSelector;
import android.support.v17.leanback.widget.Row;
import android.support.v17.leanback.widget.RowPresenter;
import android.support.v4.content.ContextCompat;
import android.util.DisplayMetrics;
import android.view.View;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.example.excadmin.tvcleanarchitecture.R;
import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryList;
import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryVideoList;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.presentation.presenter.VideoListPresenter;
import com.example.excadmin.tvcleanarchitecture.presentation.service.UpdateRecommendationsService;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.MainActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.viewpresenter.CardPresenter;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.viewpresenter.GridItemPresenter;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.viewpresenter.IconHeaderItemPresenter;

import java.util.HashMap;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;

import javax.inject.Inject;

/*
 * Main class to show BrowseFragment with header and rows of videos
 */
public class MainFragment extends BrowseFragment implements VideoListPresenter.VideoListView {
    public interface VideoListListener {
        void onVideoClicked(Video video, ImageCardView itemViewHolder);

        void onSettingClicked();

        void onGuideClicked();

        void onErrorClicked();

        void onGridClicked();

        void onSearchClicked();
    }

    private static final int BACKGROUND_UPDATE_DELAY = 300;
    private final Handler mHandler = new Handler();
    private ArrayObjectAdapter mCategoryRowAdapter;
    private Drawable mDefaultBackground;
    private DisplayMetrics mMetrics;
    private Timer mBackgroundTimer;
    private Uri mBackgroundURI;
    private BackgroundManager mBackgroundManager;

    @Inject
    VideoListPresenter videoListPresenter;

    @Inject
    Context context;

    private VideoListListener videoListListener;

    // Maps a Loader Id to its CursorObjectAdapter.
    private Map<Integer, ArrayObjectAdapter> mVideoArrayAdapters;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        // Create a list to contain all the CursorObjectAdapters.
        // Each adapter is used to render a specific row of videos in the MainFragment.
        mVideoArrayAdapters = new HashMap<>();

        // Start loading the categories from the database.
        //getLoaderManager().initLoader(CATEGORY_LOADER, null, this);

        if (getActivity() instanceof VideoListListener) {
            this.videoListListener = (VideoListListener) getActivity();
        }
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        // Final initialization, modifying UI elements.
        super.onActivityCreated(savedInstanceState);

        // Prepare the manager that maintains the same background image between activities.
        prepareBackgroundManager();

        setupUIElements();
        setupEventListeners();
        prepareEntranceTransition();

        // Map category results from the database to ListRow objects.
        // This Adapter is used to render the MainFragment sidebar labels.
        mCategoryRowAdapter = new ArrayObjectAdapter(new ListRowPresenter());
        setAdapter(mCategoryRowAdapter);

        updateRecommendations();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).getComponent().inject(this);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.videoListPresenter.setView(this);
        if (savedInstanceState == null) {
            this.videoListPresenter.initialize();
        }
    }

    @Override
    public void onDestroy() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
            mBackgroundTimer = null;
        }
        mBackgroundManager = null;
        super.onDestroy();
    }

    @Override
    public void onStop() {
        mBackgroundManager.release();
        super.onStop();
    }

    private void prepareBackgroundManager() {
        mBackgroundManager = BackgroundManager.getInstance(getActivity());
        mBackgroundManager.attach(getActivity().getWindow());
        mDefaultBackground = getResources().getDrawable(R.drawable.default_background, null);
        mMetrics = new DisplayMetrics();
        getActivity().getWindowManager().getDefaultDisplay().getMetrics(mMetrics);
    }

    private void setupUIElements() {
        setBadgeDrawable(
                getActivity().getResources().getDrawable(R.drawable.videos_by_google_banner, null));
        setTitle(getString(R.string.browse_title)); // Badge, when set, takes precedent over title
        setHeadersState(HEADERS_ENABLED);
        setHeadersTransitionOnBackEnabled(true);

        // Set fastLane (or headers) background color
        setBrandColor(ContextCompat.getColor(getActivity(), R.color.fastlane_background));

        // Set search icon color.
        setSearchAffordanceColor(ContextCompat.getColor(getActivity(), R.color.search_opaque));

        setHeaderPresenterSelector(new PresenterSelector() {
            @Override
            public Presenter getPresenter(Object o) {
                return new IconHeaderItemPresenter();
            }
        });
    }

    private void setupEventListeners() {
        setOnSearchClickedListener(view -> {
            this.videoListPresenter.onSearchClicked(view);
        });

        setOnItemViewClickedListener(new ItemViewClickedListener());
        setOnItemViewSelectedListener(new ItemViewSelectedListener());
    }

    private void updateBackground(String uri) {
        int width = mMetrics.widthPixels;
        int height = mMetrics.heightPixels;
        Glide.with(this)
                .load(uri)
                .asBitmap()
                .centerCrop()
                .error(mDefaultBackground)
                .into(new SimpleTarget<Bitmap>(width, height) {
                    @Override
                    public void onResourceReady(Bitmap resource, GlideAnimation<? super Bitmap>
                            glideAnimation) {
                        mBackgroundManager.setBitmap(resource);
                    }
                });
        mBackgroundTimer.cancel();
    }

    private void startBackgroundTimer() {
        if (null != mBackgroundTimer) {
            mBackgroundTimer.cancel();
        }
        mBackgroundTimer = new Timer();
        mBackgroundTimer.schedule(new UpdateBackgroundTask(), BACKGROUND_UPDATE_DELAY);
    }

    private void updateRecommendations() {
        Intent recommendationIntent = new Intent(getActivity(), UpdateRecommendationsService.class);
        getActivity().startService(recommendationIntent);
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
    public void renderVideoList(CategoryList videoModelCollection) {
        mCategoryRowAdapter.clear();

        for (CategoryVideoList categoryVideoList : videoModelCollection.getGooglevideos()) {

            String category = categoryVideoList.getCategory();
            HeaderItem header = new HeaderItem(category);


            int videoLoaderId = category.hashCode();
            ArrayObjectAdapter existingAdapter = mVideoArrayAdapters.get(videoLoaderId);

            if (existingAdapter == null) {
                ArrayObjectAdapter adapter = new ArrayObjectAdapter(new CardPresenter());
                adapter.addAll(0, categoryVideoList.getVideos());

                mVideoArrayAdapters.put(videoLoaderId, adapter);

                ListRow row = new ListRow(header, adapter);
                mCategoryRowAdapter.add(row);
            } else {
                ListRow row = new ListRow(header, existingAdapter);
                mCategoryRowAdapter.add(row);
            }
        }

        // Create a row for this special case with more samples.
        HeaderItem gridHeader = new HeaderItem(getString(R.string.more_samples));
        GridItemPresenter gridPresenter = new GridItemPresenter(this);
        ArrayObjectAdapter gridRowAdapter = new ArrayObjectAdapter(gridPresenter);
        gridRowAdapter.add(getString(R.string.grid_view));
        gridRowAdapter.add(getString(R.string.guidedstep_first_title));
        gridRowAdapter.add(getString(R.string.error_fragment));
        gridRowAdapter.add(getString(R.string.personal_settings));
        ListRow row = new ListRow(gridHeader, gridRowAdapter);
        mCategoryRowAdapter.add(row);

        startEntranceTransition();
    }

    @Override
    public void viewVideo(Video video, ImageCardView imageCardView) {
        videoListListener.onVideoClicked(video, imageCardView);
    }

    @Override
    public void viewError() {
        videoListListener.onErrorClicked();
    }

    @Override
    public void viewSetting() {
        videoListListener.onSettingClicked();
    }

    @Override
    public void viewGuide() {
        videoListListener.onGuideClicked();
    }

    @Override
    public void viewGrid() {
        videoListListener.onGridClicked();
    }

    @Override
    public void viewSearch() {
        videoListListener.onSearchClicked();
    }

    @Override
    public Context context() {
        return this.context;
    }

    private class UpdateBackgroundTask extends TimerTask {

        @Override
        public void run() {
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    if (mBackgroundURI != null) {
                        updateBackground(mBackgroundURI.toString());
                    }
                }
            });
        }
    }

    private final class ItemViewClickedListener implements OnItemViewClickedListener {
        @Override
        public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item,
                                  RowPresenter.ViewHolder rowViewHolder, Row row) {

            videoListPresenter.onVideoClicked(itemViewHolder, item, rowViewHolder, row);
        }
    }

    private final class ItemViewSelectedListener implements OnItemViewSelectedListener {
        @Override
        public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item,
                                   RowPresenter.ViewHolder rowViewHolder, Row row) {
            if (item instanceof Video) {
                mBackgroundURI = Uri.parse(((Video) item).bgImageUrl);
                startBackgroundTimer();
            }

        }
    }
}
