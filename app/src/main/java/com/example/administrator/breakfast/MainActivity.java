package com.example.administrator.breakfast;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MainActivity extends BaseActivity {

    public static final String BREAKFAST = "breakfast";

    public static final String FRUIT = "fruit";

    public static final String SOUP = "soup";


    public static final String[] DEFAULT_BREAKFAST_LIST = {"黑米粥", "豆浆+包点", "牛奶", "玉米粥", "小米粥"};

    public static final String[] DEFAULT_FRUIT_LIST = {"苹果", "火龙果", "梨", "桃子", "葡萄"};

    public static final String[] DEFAULT_SOUP_LIST = {"白萝卜炖排骨", "香菇乌鸡", "银耳莲子", "雪梨冰糖"};

    public static final Map<String, String[]> DEFAULT_MAP = new HashMap<>();

    public static final Map<String, String> TITLE_MAP = new HashMap<>();

    static {
        DEFAULT_MAP.put(BREAKFAST, DEFAULT_BREAKFAST_LIST);
        DEFAULT_MAP.put(FRUIT, DEFAULT_FRUIT_LIST);
        DEFAULT_MAP.put(SOUP, DEFAULT_SOUP_LIST);

        TITLE_MAP.put(BREAKFAST, "早餐");
        TITLE_MAP.put(FRUIT, "水果");
        TITLE_MAP.put(SOUP, "炖汤");

    }

    private String currentType;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Jay", "onCreate");

        setContentView(R.layout.activity_main);

        PreferenceUtil.setup(this);

//        if (PreferenceUtil.hasData()) {
        resetFragment(BREAKFAST);
//        } else {
//            openDataInitialPage();
//        }

    }

    private void openDataInitialPage() {

    }

    private List<Food> getDefaultFoodList(String type) {
        List<Food> result = new ArrayList<>();
        String[] list = DEFAULT_MAP.get(type);
        if (list != null) {
            for (String name : list) {
                Food food = new Food(name);
                result.add(food);
            }
        }
        return result;
    }

    private void resetFragment(String type) {

        currentType = type;

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(TITLE_MAP.get(type));
        }

        List<Food> list = getDefaultFoodList(type);

        List<Food> storeList = PreferenceUtil.getFoodList(type);
        if (!ListUtils.isEmpty(storeList)) {
            list = storeList;
        } else {
            PreferenceUtil.setFoodList(list, type);
        }

        Bundle bundle = new Bundle();
        bundle.putString("type", type);
        bundle.putParcelableArrayList("list", new ArrayList<Parcelable>(list));

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
                break;
            }
            case R.id.fruit: {
                resetFragment(FRUIT);
                break;
            }
            case R.id.soup: {
                resetFragment(SOUP);
                break;
            }
        }

        Log.d("Jay", "" + (item.getItemId() == R.id.fruit));
        return super.onOptionsItemSelected(item);
    }

    public void addItem(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("新增选项")
                .setView(R.layout.dialog_add_item).setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                AlertDialog alertDialog = (AlertDialog) dialog;
                EditText editText = (EditText) alertDialog.findViewById(R.id.item_text);

                if (editText != null && !TextUtils.isEmpty(editText.getText())) {
                    List<Food> storeList = PreferenceUtil.getFoodList(currentType);
                    if (!ListUtils.isEmpty(storeList)) {
                        storeList.add(new Food(editText.getText().toString()));
                        PreferenceUtil.setFoodList(storeList, currentType);
                    }
                }
                resetFragment(currentType);

            }
        }).create().show();
    }
}
