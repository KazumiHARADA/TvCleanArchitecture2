package com.example.excadmin.tvcleanarchitecture.presentation.ui;

import android.support.v17.leanback.widget.ImageCardView;

import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryList;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;

/**
 * Created by haradakazumi on 2017/07/15.
 */

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
}
