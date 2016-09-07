package com.example.administrator.breakfast.presentation.display;

import com.example.administrator.breakfast.data.FoodRepository;
import com.example.administrator.breakfast.data.model.Food;
import com.example.administrator.breakfast.data.model.FoodList;
import com.example.administrator.breakfast.presentation.base.BasePresenter;
import com.example.administrator.breakfast.utils.ListUtils;
import com.example.administrator.breakfast.utils.TimeUtils;

import java.util.List;

/**
 * Created by Jay on 2016/9/7.
 */

public class FoodDisplayPresenter extends BasePresenter<FoodDisplayContract.View> implements FoodDisplayContract.Presenter {

    private FoodRepository foodRepository;

    public FoodDisplayPresenter(FoodRepository foodRepository) {
        this.foodRepository = foodRepository;
    }

    @Override
    public void addFood(Food food) {

        List<Food> storeList = foodRepository.getFoodList(food.getType());
        if (!ListUtils.isEmpty(storeList)) {
            storeList.add(food);
            foodRepository.setFoodList(storeList, food.getType());
        }
        getView().showList(new FoodList(food.getType(), storeList));
    }

    @Override
    public List<Food> clickFood(Food food, List<Food> list) {
        list.remove(food);
        food.addTimes();
        food.setLastTime(TimeUtils.getTime());
        list.add(list.size(), food);
        foodRepository.setFoodList(list, food.getType());
        return list;
    }

    @Override
    public void display() {

        change(Food.BREAKFAST);
    }

    @Override
    public void change(String type) {

        List<Food> list = foodRepository.getDefaultFoodList(type);

        List<Food> storeList = foodRepository.getFoodList(type);
        if (!ListUtils.isEmpty(storeList)) {
            list = storeList;
        } else {
            foodRepository.setFoodList(list, type);
        }

        getView().showList(new FoodList(type, list));
    }
}
