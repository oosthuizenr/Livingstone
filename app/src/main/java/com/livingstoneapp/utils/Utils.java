package com.livingstoneapp.utils;

/**
 * Created by Renier on 2016/02/13.
 */
public class Utils {
    public static boolean hasFlag(
            int flags,
            int flagToCheck
    ) {
        return ((flags & flagToCheck) == flagToCheck);
    }
}
