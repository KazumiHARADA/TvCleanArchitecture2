package com.example.excadmin.tvcleanarchitecture.presentation.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import com.example.excadmin.tvcleanarchitecture.AndroidApplication;
import com.example.excadmin.tvcleanarchitecture.R;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.HasComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.component.DaggerFragmentComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.component.FragmentComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.modules.ActivityModule;

/**
 * Created by excadmin on 2017/07/21.
 */

public class ApiRequestsActivity extends LeanbackActivity implements HasComponent<FragmentComponent> {
    public static Intent getCallingIntent(Context context) {
        return new Intent(context, ApiRequestsActivity.class);
    }

    private FragmentComponent fragmentComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.initializeInjector();

        setContentView(R.layout.activity_api_requests);
        getWindow().setBackgroundDrawableResource(R.drawable.grid_bg);

    }

    private void initializeInjector() {
        this.fragmentComponent = DaggerFragmentComponent.builder()
                .applicationComponent (((AndroidApplication) getApplication()).getApplicationComponent())
                .activityModule(new ActivityModule(this))
                .build();
    }

    @Override
    public FragmentComponent getComponent() {
        return fragmentComponent;
    }
}
