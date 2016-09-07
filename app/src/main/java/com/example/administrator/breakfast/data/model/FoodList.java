package com.example.administrator.breakfast.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.List;

/**
 * Created by Jay on 2016/9/7.
 */

public class FoodList implements Parcelable {

    private String type;
    private List<Food> foods;

    public FoodList(String type, List<Food> foods) {
        this.type = type;
        this.foods = foods;
    }

    protected FoodList(Parcel in) {
        type = in.readString();
        foods = in.createTypedArrayList(Food.CREATOR);
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(type);
        dest.writeTypedList(foods);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<FoodList> CREATOR = new Creator<FoodList>() {
        @Override
        public FoodList createFromParcel(Parcel in) {
            return new FoodList(in);
        }

        @Override
        public FoodList[] newArray(int size) {
            return new FoodList[size];
        }
    };

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<Food> getFoods() {
        return foods;
    }

    public void setFoods(List<Food> foods) {
        this.foods = foods;
    }
}
