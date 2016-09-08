package com.example.administrator.breakfast.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.administrator.breakfast.data.store.StringStore;

/**
 * Created by Jay on 2016/6/28.
 */
public class PreferenceUtil implements StringStore {

    private static final String DATA_HAS_SET = "data_has_set";

    private static SharedPreferences preferences;

    public static void setup(Context context) {
        preferences = context.getSharedPreferences("breakfast", Context.MODE_PRIVATE);
    }

    public String getString(String key) {
        return preferences.getString(key, null);
    }


    public void setString(String key, String string) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, string);
        editor.apply();
    }

    public static boolean hasData() {
        return preferences.getBoolean(DATA_HAS_SET, false);
    }

    public static void setDataSettingFlag() {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(DATA_HAS_SET, true);
        editor.apply();
    }
}
