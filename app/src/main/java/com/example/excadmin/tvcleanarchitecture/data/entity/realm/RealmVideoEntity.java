package com.example.excadmin.tvcleanarchitecture.data.entity.realm;

import io.realm.RealmList;
import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * Created by excadmin on 2017/07/25.
 */

public class RealmVideoEntity extends RealmObject{

    @PrimaryKey
    private long id;

    private String category;

    private String title;

    private String description;

    private String bgImageUrl;

    private String cardImageUrl;

    private RealmList<RealmObject> videoUrls;

    private String studio;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getBgImageUrl() {
        return bgImageUrl;
    }

    public void setBgImageUrl(String bgImageUrl) {
        this.bgImageUrl = bgImageUrl;
    }

    public String getCardImageUrl() {
        return cardImageUrl;
    }

    public void setCardImageUrl(String cardImageUrl) {
        this.cardImageUrl = cardImageUrl;
    }

    public RealmList<RealmObject> getVideoUrls() {
        return videoUrls;
    }

    public void setVideoUrls(RealmList<RealmObject> videoUrls) {
        this.videoUrls = videoUrls;
    }

    public String getStudio() {
        return studio;
    }

    public void setStudio(String studio) {
        this.studio = studio;
    }
}
