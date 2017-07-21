package com.example.excadmin.tvcleanarchitecture.domain.repository;

import com.example.excadmin.tvcleanarchitecture.domain.model.CategoryList;

import io.reactivex.Observable;

/**
 * Created by haradakazumi on 2017/07/15.
 */

public interface VideoRepository {

    Observable<CategoryList> videos();

}
