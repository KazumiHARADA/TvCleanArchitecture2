/**
 * Copyright (C) 2015 Fernando Cejas Open Source Project
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
package com.example.excadmin.tvcleanarchitecture.presentation.internal.di.component;

import android.content.Context;

import com.example.excadmin.tvcleanarchitecture.domain.executor.PostExecutionThread;
import com.example.excadmin.tvcleanarchitecture.domain.executor.ThreadExecutor;
import com.example.excadmin.tvcleanarchitecture.domain.repository.VideoRepository;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.modules.ApplicationModule;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.BaseActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.activity.MainActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * A component whose lifetime is the life of the application.
 */
@Singleton // Constraints this component to one-per-application or unscoped bindings.
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {
  void inject(BaseActivity baseActivity);
  void inject(MainActivity mainActivity);

  //Exposed to sub-graphs.
  Context context();
  ThreadExecutor threadExecutor();
  PostExecutionThread postExecutionThread();
  VideoRepository videoRepository();
}
