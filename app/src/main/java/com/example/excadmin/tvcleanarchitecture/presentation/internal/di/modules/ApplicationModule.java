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
package com.example.excadmin.tvcleanarchitecture.presentation.internal.di.modules;

import android.content.Context;

import com.example.excadmin.tvcleanarchitecture.AndroidApplication;
import com.example.excadmin.tvcleanarchitecture.UIThread;
import com.example.excadmin.tvcleanarchitecture.data.executor.JobExecutor;
import com.example.excadmin.tvcleanarchitecture.data.repository.VideoDataRepository;
import com.example.excadmin.tvcleanarchitecture.domain.executor.PostExecutionThread;
import com.example.excadmin.tvcleanarchitecture.domain.executor.ThreadExecutor;
import com.example.excadmin.tvcleanarchitecture.domain.repository.VideoRepository;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Dagger module that provides objects which will live during the application lifecycle.
 */
@Module
public class ApplicationModule {
    private final AndroidApplication application;

    public ApplicationModule(AndroidApplication application) {
        this.application = application;
    }

    @Provides
    @Singleton
    Context provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    ThreadExecutor provideThreadExecutor(JobExecutor jobExecutor) {
        return jobExecutor;
    }

    @Provides
    @Singleton
    PostExecutionThread providePostExecutionThread(UIThread uiThread) {
        return uiThread;
    }

//  @Provides
//  @Singleton UserCache provideUserCache(UserCacheImpl userCache) {
//    return userCache;
//  }

    @Provides
    @Singleton
    VideoRepository provideVideoRepository(VideoDataRepository videoDataRepository) {
        return videoDataRepository;
    }
}
