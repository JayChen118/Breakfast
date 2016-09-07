package com.example.administrator.breakfast.presentation.display;

import com.example.administrator.breakfast.data.model.Food;
import com.example.administrator.breakfast.data.model.FoodList;
import com.example.administrator.breakfast.presentation.base.MvpPresenter;
import com.example.administrator.breakfast.presentation.base.MvpView;

import java.util.List;

/**
 * Created by Jay on 2016/9/5.
 */

public interface FoodDisplayContract {

    interface View extends MvpView {
        void showList(FoodList foodList);

        void showEmpty();
    }

    interface Presenter extends MvpPresenter<View> {
        void addFood(Food food);

        List<Food> clickFood(Food food, List<Food> list);

        void display();

        void change(String type);
    }

}
