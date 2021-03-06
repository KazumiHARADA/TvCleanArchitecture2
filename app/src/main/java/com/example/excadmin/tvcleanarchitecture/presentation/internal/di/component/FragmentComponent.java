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


import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.PerActivity;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.modules.ActivityModule;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.modules.FragmentModule;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.fragment.ApiRequestFragment;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.fragment.MainFragment;
import com.example.excadmin.tvcleanarchitecture.presentation.ui.fragment.VideoDetailsFragment;

import dagger.Component;

@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = {ActivityModule.class, FragmentModule.class})
public interface FragmentComponent extends ActivityComponent {
  void inject(MainFragment mainFragment);
  void inject(VideoDetailsFragment videoDetailsFragment);
  void inject(ApiRequestFragment apiRequestsFragment);
}
