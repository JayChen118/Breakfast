package com.example.administrator.breakfast;

import android.app.Application;

import com.example.administrator.breakfast.utils.PreferenceUtil;

/**
 * Created by Jay on 2016/9/7.
 */

public class FoodApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceUtil.setup(this);
    }
}
