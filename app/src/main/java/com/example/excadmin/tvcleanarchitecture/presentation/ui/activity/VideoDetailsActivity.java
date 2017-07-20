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

package com.example.excadmin.tvcleanarchitecture.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v17.leanback.widget.ImageCardView;

import com.example.excadmin.tvcleanarchitecture.AndroidApplication;
import com.example.excadmin.tvcleanarchitecture.R;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.HasComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.component.DaggerVideoComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.component.VideoComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.modules.ActivityModule;
import com.example.excadmin.tvcleanarchitecture.presentation.navigation.Navigator;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.fragment.VideoDetailsFragment;

/*
 * Details activity class that loads VideoDetailsFragment class
 */
public class VideoDetailsActivity extends LeanbackActivity implements HasComponent<VideoComponent>,VideoDetailsFragment.VideoDetailsListener {

    public static Intent getCallingIntent(Context context) {
        return new Intent(context, VideoDetailsActivity.class);
    }

    Navigator navigator;

    private VideoComponent videoComponent;

    public static final String SHARED_ELEMENT_NAME = "hero";
    public static final String VIDEO = "Video";
    public static final String NOTIFICATION_ID = "NotificationId";

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //FIXME use dagger2
        navigator = new Navigator();

        this.initializeInjector();

        setContentView(R.layout.fragment_details);
    }

    private void initializeInjector() {
        this.videoComponent = DaggerVideoComponent.builder()
                .applicationComponent (((AndroidApplication) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    public VideoComponent getComponent() {
        return videoComponent;
    }

    @Override
    public void onVideoClicked(Video video, ImageCardView itemViewHolder) {
        navigator.navigateToVideoDetail(this,video,itemViewHolder);
    }
}
