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
package com.example.excadmin.tvcleanarchitecture.data.repository.datasource;

import android.content.Context;
import android.database.Cursor;
import android.util.Log;

import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.Single;

/**
 * {@link VideoDataStore} implementation based on file system data store.
 */
class LocalVideoDataStore implements VideoDataStore {

    private final Context context;

    @Inject
    public LocalVideoDataStore(Context context) {
        this.context = context;
    }

    //private final UserCache userCache;
    @Override
    public Observable<List<VideoEntity>> videoEntityList() {
        Single<List<VideoEntity>> videoListSingle = Single.create(singleOnSubscribe -> {

            VideoDbHelper openHelper = new VideoDbHelper(context);
            Cursor cursor = openHelper.getReadableDatabase().query(
                    VideoContract.VideoEntry.TABLE_NAME,
                    new String[]{"DISTINCT " + VideoContract.VideoEntry.COLUMN_CATEGORY},
                    null,
                    null,
                    null,
                    null,
                    null
            );

            try {
                while (!cursor.isAfterLast()) {
                    int categoryIndex =
                            cursor.getColumnIndex(VideoContract.VideoEntry.COLUMN_CATEGORY);
                    String category = cursor.getString(categoryIndex);

                    Log.d("test",category);
                    singleOnSubscribe.onSuccess(new ArrayList<>());
                }
            } catch (NullPointerException e) {
                singleOnSubscribe.onError(e);
            }
        });
        return videoListSingle.toObservable();
    }

    @Override
    public Observable<VideoEntity> videoEntityDetails(int videoId) {
        return null;
    }
}
