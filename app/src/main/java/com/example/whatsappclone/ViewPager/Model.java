package com.example.whatsappclone.ViewPager;

import androidx.fragment.app.Fragment;

import java.util.ArrayList;

public class Model {
    private Fragment fragment;
    private String title;

    public Model(Fragment fragment, String title) {
        this.fragment = fragment;
        this.title = title;
    }

    public Fragment getFragment() {
        return fragment;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
