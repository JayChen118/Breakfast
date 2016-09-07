package com.example.administrator.breakfast.data.model;

import android.os.Parcel;
import android.os.Parcelable;

import com.example.administrator.breakfast.utils.TimeUtils;

/**
 * Created by Jay on 2016/6/29.
 */
public class Food implements Parcelable {

    public static final String BREAKFAST = "breakfast";

    public static final String FRUIT = "fruit";

    public static final String SOUP = "soup";

    private final String type;

    private final String name;

    private int times;

    private long lastTime;

    public Food(String type, String name) {
        this.type = type;
        this.name = name;
    }

    protected Food(Parcel in) {
        type = in.readString();
        name = in.readString();
        times = in.readInt();
        lastTime = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeInt(times);
        dest.writeLong(lastTime);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Food> CREATOR = new Creator<Food>() {
        @Override
        public Food createFromParcel(Parcel in) {
            return new Food(in);
        }

        @Override
        public Food[] newArray(int size) {
            return new Food[size];
        }
    };

    public String getName() {
        return name;
    }

    public String getTimesText() {
        return String.valueOf(times);
    }

    public int getTimes() {
        return times;
    }

    public void setTimes(int times) {
        this.times = times;
    }

    public long getLastTime() {
        return lastTime;
    }

    public void setLastTime(long lastTime) {
        this.lastTime = lastTime;
    }

    public String getLastTimeText() {
        if (lastTime != 0) {
            int days = TimeUtils.passDays(lastTime);
            if (days == 0) {
                return "今天";
            } else {
                return String.format("%d 天前", TimeUtils.passDays(lastTime));
            }
        } else {
            return "";
        }
    }

    public void addTimes() {
        times++;
    }

    public String getType() {
        return type;
    }
}
