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
package com.example.excadmin.tvcleanarchitecture.presentation.mapper;


import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;
import com.example.excadmin.tvcleanarchitecture.domain.dto.Video;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.PerActivity;

import javax.inject.Inject;

@PerActivity
public class VideoModelDataMapper {

  @Inject
  public VideoModelDataMapper() {}

  public Video transform(VideoEntity videoEntity) {
    Video video = null;
    if (videoEntity != null) {
      video = new Video(
              videoEntity.getId(),
              videoEntity.getCategory(),
              videoEntity.getTitle(),
              videoEntity.getDescription(),
              videoEntity.getVideoUrl(),
              videoEntity.getBgImageUrl(),
              videoEntity.getCardImageUrl(),
              videoEntity.getStudio()
      );
    }
    return video;
  }
}
