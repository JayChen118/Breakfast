package com.example.administrator.breakfast.data;

import com.example.administrator.breakfast.data.model.Food;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.example.administrator.breakfast.data.model.Food.BREAKFAST;
import static com.example.administrator.breakfast.data.model.Food.FRUIT;
import static com.example.administrator.breakfast.data.model.Food.SOUP;

/**
 * Created by Jay on 2016/9/7.
 */

public interface FoodRepository {


    List<Food> getFoodList(String type);

    void setFoodList(List<Food> list, String type);

    List<Food> getDefaultFoodList(String type);
}
