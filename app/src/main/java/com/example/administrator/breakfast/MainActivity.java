package com.example.administrator.breakfast;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    public static final String BREAKFAST = "breakfast";

    public static final String SOUP = "soup";

    public static final String[] DEFAULT_BREAKFAST_LIST = {"黑米粥", "豆浆+包点", "牛奶", "玉米粥", "小米粥"};

    List<Food> list = getDefaultBreakfastList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Jay", "onCreate");

        setContentView(R.layout.activity_main);

        FrameLayout layout = (FrameLayout) findViewById(R.id.container);

        PreferenceUtil.setup(this);

        List<Food> storeList = PreferenceUtil.getBreakfastList();
        if (!ListUtils.isEmpty(storeList) && storeList.size() == list.size()) {
            list = storeList;
        } else {
            PreferenceUtil.setBreakfastList(list);
        }

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", new ArrayList<Parcelable>(list));
        FoodFragment foodFragment = new FoodFragment();
        foodFragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.add(R.id.container, foodFragment);
        transaction.commit();

    }

    private List<Food> getDefaultBreakfastList(){
        List<Food> result = new ArrayList<>();
        for (String name : DEFAULT_BREAKFAST_LIST) {
            Food food = new Food(name);
            result.add(food);
        }
        return result;
    }

}
