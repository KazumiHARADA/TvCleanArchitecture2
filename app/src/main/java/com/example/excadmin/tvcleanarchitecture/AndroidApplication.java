package com.example.excadmin.tvcleanarchitecture;

import android.app.Application;

import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.component.ApplicationComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.component.DaggerApplicationComponent;
import com.example.excadmin.tvcleanarchitecture.presentation.internal.di.modules.ApplicationModule;

/**
 * Created by haradakazumi on 2017/07/15.
 */

public class AndroidApplication extends Application{

    private ApplicationComponent applicationComponent;

    @Override public void onCreate() {
        super.onCreate();
        this.initializeInjector();
    }

    private void initializeInjector() {
        this.applicationComponent = DaggerApplicationComponent.builder()
                .applicationModule(new ApplicationModule(this))
                .build();
    }

    public ApplicationComponent getApplicationComponent() {
        return this.applicationComponent;
    }


}
