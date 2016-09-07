package com.example.administrator.breakfast.presentation.display;

import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.DialogInterface;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import com.example.administrator.breakfast.R;
import com.example.administrator.breakfast.data.FoodRepositoryImpl;
import com.example.administrator.breakfast.data.model.Food;
import com.example.administrator.breakfast.data.model.FoodList;
import com.example.administrator.breakfast.presentation.base.BaseActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static com.example.administrator.breakfast.data.model.Food.BREAKFAST;
import static com.example.administrator.breakfast.data.model.Food.FRUIT;
import static com.example.administrator.breakfast.data.model.Food.SOUP;

public class MainActivity extends BaseActivity implements FoodDisplayContract.View {


    public static final Map<String, String> TITLE_MAP = new HashMap<>();

    static {
        TITLE_MAP.put(BREAKFAST, "早餐");
        TITLE_MAP.put(FRUIT, "水果");
        TITLE_MAP.put(SOUP, "炖汤");
    }

    private String currentType;

    private FoodDisplayContract.Presenter foodDisplayPresenter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Log.d("Jay", "onCreate");

        setContentView(R.layout.activity_main);

        foodDisplayPresenter = new FoodDisplayPresenter(new FoodRepositoryImpl());
        foodDisplayPresenter.attachView(this);

        foodDisplayPresenter.display();


//        if (PreferenceUtil.hasData()) {
//        resetFragment(BREAKFAST);
//        } else {
//            openDataInitialPage();
//        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        foodDisplayPresenter.detachView();
    }

    private void openDataInitialPage() {

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
                foodDisplayPresenter.change(BREAKFAST);
                break;
            }
            case R.id.fruit: {
                foodDisplayPresenter.change(FRUIT);
                break;
            }
            case R.id.soup: {
                foodDisplayPresenter.change(SOUP);
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
                    foodDisplayPresenter.addFood(new Food(currentType, editText.getText().toString()));
                }

            }
        }).create().show();
    }

    @Override
    public void showList(FoodList foodList) {

        currentType = foodList.getType();

        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(TITLE_MAP.get(foodList.getType()));
        }

        Bundle bundle = new Bundle();
        bundle.putString("type", foodList.getType());
        bundle.putParcelableArrayList("list", new ArrayList<Parcelable>(foodList.getFoods()));

        FoodFragment foodFragment = new FoodFragment();
        foodFragment.setFoodDisplayPresenter(foodDisplayPresenter);
        foodFragment.setArguments(bundle);

        FragmentManager manager = getFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.container, foodFragment);
        transaction.commit();
    }

    @Override
    public void showEmpty() {

    }
}
