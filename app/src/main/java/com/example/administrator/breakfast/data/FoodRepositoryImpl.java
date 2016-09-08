package com.example.administrator.breakfast.data;

import com.example.administrator.breakfast.data.model.Food;
import com.example.administrator.breakfast.data.store.StringStore;
import com.example.administrator.breakfast.utils.GsonUtil;
import com.example.administrator.breakfast.utils.StringUtils;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.administrator.breakfast.data.model.Food.BREAKFAST;
import static com.example.administrator.breakfast.data.model.Food.FRUIT;
import static com.example.administrator.breakfast.data.model.Food.SOUP;

/**
 * Created by Jay on 2016/9/7.
 */

public class FoodRepositoryImpl implements FoodRepository {


    public static final String[] DEFAULT_BREAKFAST_LIST = {"黑米粥", "豆浆+包点", "牛奶", "玉米粥", "小米粥"};

    public static final String[] DEFAULT_FRUIT_LIST = {"苹果", "火龙果", "梨", "桃子", "葡萄"};

    public static final String[] DEFAULT_SOUP_LIST = {"白萝卜炖排骨", "香菇乌鸡", "银耳莲子", "雪梨冰糖"};

    public static final Map<String, String[]> DEFAULT_MAP = new HashMap<>();

    static {
        DEFAULT_MAP.put(BREAKFAST, DEFAULT_BREAKFAST_LIST);
        DEFAULT_MAP.put(FRUIT, DEFAULT_FRUIT_LIST);
        DEFAULT_MAP.put(SOUP, DEFAULT_SOUP_LIST);
    }

    private StringStore store;

    public FoodRepositoryImpl(StringStore store) {
        this.store = store;
    }

    @Override
    public List<Food> getFoodList(String type) {

        String string = store.getString(type + LIST_SUFFIX);

        if (!StringUtils.isEmpty(string)) {
            TypeToken<List<Food>> token = new TypeToken<List<Food>>() {
            };
            return GsonUtil.fromJson(string, token.getType());
        }
        return null;
    }

    @Override
    public void setFoodList(List<Food> list, String type) {
        store.setString(type + LIST_SUFFIX, GsonUtil.toJson(list));
    }

    @Override
    public List<Food> getDefaultFoodList(String type) {
        List<Food> result = new ArrayList<>();
        String[] list = DEFAULT_MAP.get(type);
        if (list != null) {
            for (String name : list) {
                Food food = new Food(type, name);
                result.add(food);
            }
        }
        return result;
    }
}
