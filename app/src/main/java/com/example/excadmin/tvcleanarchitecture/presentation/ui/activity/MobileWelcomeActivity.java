package com.example.excadmin.tvcleanarchitecture.presentation.ui.activity;

import android.os.Bundle;

import com.example.excadmin.tvcleanarchitecture.R;

public class MobileWelcomeActivity extends LeanbackActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mobile_welcome);
    }

    @Override
    boolean isSearchEnabled() {
        return false;
    }
}
