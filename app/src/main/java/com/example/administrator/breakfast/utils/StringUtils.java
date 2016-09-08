package com.example.administrator.breakfast.utils;

/**
 * Created by Jay on 2016/9/8.
 */

public class StringUtils {

    /**
     * Returns true if the string is null or 0-length.
     *
     * @param str the string to be examined
     * @return true if str is null or zero length
     */
    public static boolean isEmpty(CharSequence str) {
        if (str == null || str.length() == 0)
            return true;
        else
            return false;
    }
}
