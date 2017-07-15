package com.example.excadmin.tvcleanarchitecture.domain.repository;

import com.example.excadmin.tvcleanarchitecture.domain.model.Video;

import java.util.List;

import io.reactivex.Observable;

/**
 * Created by haradakazumi on 2017/07/15.
 */

public interface VideoRepository {

    Observable<List<Video>> videos();

    Observable<Video> video(final int contentId);
}
