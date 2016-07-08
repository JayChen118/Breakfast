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

    static String KEY_BREAKFAST_LIST = "breakfast_list";

    private static SharedPreferences preferences;

    public static void setup(Context context) {
        preferences = context.getSharedPreferences("breakfast", Context.MODE_PRIVATE);
    }

    public static List<Food> getBreakfastList(){

        String string = preferences.getString(KEY_BREAKFAST_LIST, null);

        if (!TextUtils.isEmpty(string)) {
            TypeToken<List<Food>> token = new TypeToken<List<Food>>() {
            };
            return GsonUtil.fromJson(string, token.getType());
        }
        return null;
    }

    public static void setBreakfastList(List<Food> list) {
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(KEY_BREAKFAST_LIST, GsonUtil.toJson(list));
        editor.apply();
    }
}
