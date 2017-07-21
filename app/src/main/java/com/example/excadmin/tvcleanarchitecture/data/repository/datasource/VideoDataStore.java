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
package com.example.excadmin.tvcleanarchitecture.data.repository.datasource;

import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;

import java.util.List;

import io.reactivex.Observable;

/**
 * Interface that represents a data store from where data is retrieved.
 */
public interface VideoDataStore {
  /**
   * Get an {@link Observable} which will emit a List of {@link VideoEntity}.
   */
  Observable<CategoryListEntity> videoEntityList();

  /**
   * Get an {@link Observable} which will emit a List of {@link VideoEntity}.
   */
  Observable<CategoryListEntity> videoEntityServiceList();

  /**
   * Get an {@link Observable} which will emit a List of {@link VideoEntity}.
   */
  Observable<List<VideoEntity>> latestVideoEntityList(String category);

  /**
   * Get an {@link Observable} which will emit a {@link VideoEntity} by its id.
   *
   * @param videoId The id to retrieve user data.
   */
  Observable<VideoEntity> videoEntityDetails(final int videoId);
}
