package com.example.excadmin.tvcleanarchitecture.data.entity.mapper;

import com.example.excadmin.tvcleanarchitecture.data.entity.CategoryListEntity;
import com.example.excadmin.tvcleanarchitecture.data.entity.VideoEntity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;

import javax.inject.Inject;

/**
 * Created by excadmin on 2017/07/18.
 */

public class VideoEntityJsonMapper {

    private final Gson gson;

    @Inject
    public VideoEntityJsonMapper() {
        this.gson = new Gson();
    }

    public VideoEntity transformVideoEntity(String userJsonResponse) throws JsonSyntaxException {
        final Type userEntityType = new TypeToken<VideoEntity>() {}.getType();
        return this.gson.fromJson(userJsonResponse, userEntityType);
    }

    public CategoryListEntity transformVideoEntityCollection(String videoListJsonResponse)
            throws JsonSyntaxException {

        final Type listOfUserEntityType = new TypeToken<CategoryListEntity>() {}.getType();
        return this.gson.fromJson(videoListJsonResponse, listOfUserEntityType);
    }

}
