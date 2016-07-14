package com.example.administrator.breakfast;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;

import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by Jay on 2016/6/28.
 */
public class PreferenceUtil {

    private static final String DATA_HAS_SET = "data_has_set";
    static String KEY_BREAKFAST_LIST = "breakfast_list";

    static String LIST_SUFFIX = "_list";

    private static SharedPreferences preferences;

    public static void setup(Context context) {
        preferences = context.getSharedPreferences("breakfast", Context.MODE_PRIVATE);
    }

    public static List<Food> getBreakfastList() {

        String string = preferences.getString(KEY_BREAKFAST_LIST, null);

        if (!TextUtils.isEmpty(string)) {
            TypeToken<List<Food>> token = new TypeToken<List<Food>>() {
            };
            return GsonUtil.fromJson(string, token.getType());
        }
        return null;
    }

    public static List<Food> getFoodList(String type) {

        String string = preferences.getString(type + LIST_SUFFIX, null);

        if (!TextUtils.isEmpty(string)) {
            TypeToken<List<Food>> token = new TypeToken<List<Food>>() {
            };
            return GsonUtil.fromJson(string, token.getType());
        }
        return null;
    }

    public static void setFoodList(List<Food> list, String type) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(type + LIST_SUFFIX, GsonUtil.toJson(list));
        editor.apply();
    }

    public static void setBreakfastList(List<Food> list) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_BREAKFAST_LIST, GsonUtil.toJson(list));
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
