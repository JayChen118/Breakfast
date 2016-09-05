package com.example.administrator.breakfast.utils;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;

import java.lang.reflect.Type;

/**
 * Created by Jay on 2015/12/9.
 */
public class GsonUtil {

    final static Gson gson = new Gson();

    public static <T> T fromJson(String json, Type typeOfT) {
        try {
            return gson.fromJson(json, typeOfT);
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String toJson(Object object) {
        return gson.toJson(object);
    }
}
