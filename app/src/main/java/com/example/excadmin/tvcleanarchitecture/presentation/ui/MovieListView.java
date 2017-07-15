package com.example.excadmin.tvcleanarchitecture.presentation.ui;

import java.util.Collection;
import com.example.excadmin.tvcleanarchitecture.presentation.model.Video;

/**
 * Created by haradakazumi on 2017/07/15.
 */

public interface MovieListView extends LoadDataView {
    /**
     * Render a user list in the UI.
     *
     * @param videoModelCollection The collection of {@link Video} that will be shown.
     */
    void renderMovieList(Collection<Video> videoModelCollection);

    /**
     * View a {@link Video} profile/details.
     *
     * @param video The user that will be shown.
     */
    void viewMovie(Video video);
}
