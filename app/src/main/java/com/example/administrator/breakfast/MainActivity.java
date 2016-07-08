package com.example.administrator.breakfast;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends AppCompatActivity {

    public static final String BREAKFAST = "breakfast";

    public static final String SOUP = "soup";

    public static final String FRUIT = "fruit";


    public static final String[] DEFAULT_BREAKFAST_LIST = {"黑米粥", "豆浆+包点", "牛奶", "玉米粥", "小米粥"};

    public static final String[] DEFAULT_FRUIT_LIST = {"苹果", "火龙果", "梨", "桃子", "葡萄"};

    public static final Map<String, String[]> DEFAULT_MAP = new HashMap<>();

    static {
        DEFAULT_MAP.put(BREAKFAST, DEFAULT_BREAKFAST_LIST);
        DEFAULT_MAP.put(FRUIT, DEFAULT_FRUIT_LIST);
    }

    List<Food> list = getDefaultFoodList();

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

    private List<Food> getDefaultFoodList() {
        List<Food> result = new ArrayList<>();
        for (String name : DEFAULT_BREAKFAST_LIST) {
            Food food = new Food(name);
            result.add(food);
        }
        return result;
    }

    private void resetFragment(String type) {

        Bundle bundle = new Bundle();
        bundle.putParcelableArrayList("list", new ArrayList<Parcelable>());
        FoodFragment foodFragment = new FoodFragment();
        foodFragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, foodFragment);
        transaction.commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.breakfast: {
                resetFragment(BREAKFAST);
            }
            case R.id.fruit: {
                resetFragment(FRUIT);
            }
        }

        Log.d("Jay", "" + (item.getItemId() == R.id.fruit));
        return super.onOptionsItemSelected(item);
    }
}
