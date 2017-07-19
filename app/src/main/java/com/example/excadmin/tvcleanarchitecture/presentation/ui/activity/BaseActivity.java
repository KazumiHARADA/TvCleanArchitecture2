package com.example.excadmin.tvcleanarchitecture.presentation.ui.activity;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;

import com.example.excadmin.tvcleanarchitecture.AndroidApplication;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.component.ApplicationComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.modules.ActivityModule;
import com.example.excadmin.tvcleanarchitecture.presentation.navigation.Navigator;

import javax.inject.Inject;

/**
 * Created by haradakazumi on 2017/07/15.
 */

public class BaseActivity extends Activity{

    @Inject
    Navigator navigator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.getApplicationComponent().inject(this);
    }

    /**
     * Adds a {@link Fragment} to this activity's layout.
     *
     * @param containerViewId The container view to where add the fragment.
     * @param fragment The fragment to be added.
     */
    protected void addFragment(int containerViewId, Fragment fragment) {
        final FragmentTransaction fragmentTransaction = this.getFragmentManager().beginTransaction();
        fragmentTransaction.add(containerViewId, fragment);
        fragmentTransaction.commit();
    }

    /**
     * Get the Main Application component for dependency injection.
     *
     */
    protected ApplicationComponent getApplicationComponent() {
        return ((AndroidApplication) getApplication()).getApplicationComponent();
    }

    /**
     * Get an Activity module for dependency injection.
     *
     */
    protected ActivityModule getActivityModule() {
        return new ActivityModule(this);
    }

}