/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
 * <p>
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * <p>
 * http://www.apache.org/licenses/LICENSE-2.0
 * <p>
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.excadmin.tvcleanarchitecture.presentation.navigation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.widget.ImageCardView;
import android.support.v4.app.ActivityOptionsCompat;

import com.example.excadmin.tvcleanarchitecture.R;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.GuidedStepActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.PlaybackOverlayActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.SearchActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.SettingsActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.VerticalGridActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.VideoDetailsActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.fragment.BrowseErrorFragment;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Class used to navigate through the application.
 */
@Singleton
public class Navigator {

    @Inject
    public Navigator() {
        //empty
    }

    public void navigateToVideoDetail(Activity activity, Video video, ImageCardView imageCardView) {
        if (activity != null) {
            Intent intentToLaunch = VideoDetailsActivity.getCallingIntent(activity);
            intentToLaunch.putExtra(VideoDetailsActivity.VIDEO, video);

            Bundle bundle = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    activity,
                    imageCardView.getMainImageView(),
                    VideoDetailsActivity.SHARED_ELEMENT_NAME).toBundle();
            activity.startActivity(intentToLaunch, bundle);
        }
    }

    public void navigateToGrid(Activity activity) {
        Intent intentToLaunch = VerticalGridActivity.getCallingIntent(activity);

        Bundle bundle =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity)
                        .toBundle();
        activity.startActivity(intentToLaunch, bundle);
    }

    public void navigateToGuide(Activity activity) {
        Intent intentToLaunch = GuidedStepActivity.getCallingIntent(activity);

        Bundle bundle =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity)
                        .toBundle();
        activity.startActivity(intentToLaunch, bundle);
    }


    // TODO ここだけFragment大丈夫かな？
    public void navigateToError(Activity activity) {
        BrowseErrorFragment errorFragment = new BrowseErrorFragment();
        activity.getFragmentManager().beginTransaction().replace(R.id.main_frame, errorFragment)
                .addToBackStack(null).commit();
    }


    public void navigateToSetting(Activity activity) {
        Intent intentToLaunch = SettingsActivity.getCallingIntent(activity);

        Bundle bundle =
                ActivityOptionsCompat.makeSceneTransitionAnimation(activity)
                        .toBundle();
        activity.startActivity(intentToLaunch, bundle);
    }

    public void navigateToSearch(Context context) {
        Intent intentToLaunch = SearchActivity.getCallingIntent(context);
        context.startActivity(intentToLaunch);
    }

    public void navigateToPlayback(Context context,Video video) {
        Intent intent = PlaybackOverlayActivity.getCallingIntent(context);
        intent.putExtra(VideoDetailsActivity.VIDEO, video);
        context.startActivity(intent);
    }

}
