package com.example.excadmin.tvcleanarchitecture.data.entity.realm;

import io.realm.RealmObject;

/**
 * Created by excadmin on 2017/07/25.
 */

public class RealmStringEntity extends RealmObject {
    private String string;

    public String getString() {
        return string;
    }

    public void setString(String string) {
        this.string = string;
    }
}
