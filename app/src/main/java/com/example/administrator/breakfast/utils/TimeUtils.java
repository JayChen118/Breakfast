package com.example.administrator.breakfast.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

/**
 * Created by Jay on 2016/6/29.
 */
public class TimeUtils {

    private static final DateFormat DATE_FORMAT = new SimpleDateFormat("yyyy/MM/dd", Locale.SIMPLIFIED_CHINESE);

    private static final DateFormat HOUR_MINUTE_FORMAT = new SimpleDateFormat("HH:mm", Locale.SIMPLIFIED_CHINESE);

    private static final DateFormat NORMAL_FORMAT = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.SIMPLIFIED_CHINESE);

    public static int passDays(long time) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(time);

        Calendar now = Calendar.getInstance();

        return now.get(Calendar.DAY_OF_YEAR) - calendar.get(Calendar.DAY_OF_YEAR);
    }

    public static String getTimeDisplayString(long time) {
        return getTimeDisplayString(new Date(time));
    }

    public static String getTimeDisplayString(Date time) {

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(time);

        Calendar todayCalendar = Calendar.getInstance();

        if (isSameDay(calendar, todayCalendar)) {
            // 同一年同一日
            return HOUR_MINUTE_FORMAT.format(time);
        }

        calendar.add(Calendar.DAY_OF_YEAR, 1);

        if (isSameDay(calendar, todayCalendar)) {
            //加了一天后，是同一年同一日
            return "昨天";
        }

        return DATE_FORMAT.format(time);
    }

    private static boolean isSameDay(Calendar calendar1, Calendar calendar2) {
        return calendar1.get(Calendar.YEAR) == calendar2.get(Calendar.YEAR)
                && calendar1.get(Calendar.DAY_OF_YEAR) == calendar2.get(Calendar.DAY_OF_YEAR);
    }

    public static long getTime() {
        return new Date().getTime();
    }
}
