package com.example.excadmin.tvcleanarchitecture.presentation.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;

import com.example.excadmin.tvcleanarchitecture.AndroidApplication;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.component.ApplicationComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.modules.ActivityModule;
import com.example.excadmin.tvcleanarchitecture.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * This parent class contains common methods that run in every activity such as search.
 */
public abstract class LeanbackActivity extends FragmentActivity {

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

    /*
     * 遷移しない時もtrueを返していいのか？
     */
    @Override
    public boolean onSearchRequested() {
        if (isSearchEnabled()) {
            startActivity(new Intent(this, SearchActivity.class));
        }
        return true;
    }

    abstract boolean isSearchEnabled();
}
