package com.example.excadmin.tvcleanarchitecture.domain.repository;

import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryList;
import com.example.excadmin.tvcleanarchitecture.domain.model.Video;

import io.reactivex.Observable;

/**
 * Created by haradakazumi on 2017/07/15.
 */

public interface VideoRepository {

    Observable<CategoryList> videos();

    Observable<Video> video(final int contentId);
}
