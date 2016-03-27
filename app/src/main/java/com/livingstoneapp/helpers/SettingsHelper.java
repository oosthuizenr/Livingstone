package com.livingstoneapp.helpers;

import android.content.Context;
import android.content.SharedPreferences;

import rx.Observable;

/**
 * Created by renier on 2/2/2016.
 */
public class SettingsHelper {
    private static SharedPreferences prefs;
    private static final String SP_NAME = "SettingsHelper";
    private static final String SP_KEY_INCLUDE_UNINSTALLED_APPS = "IncludeUninstalledApps";
    private static final String SP_KEY_EXCLUDE_SYSTEM_APPS = "ExcludeSystemApps";

    public static void setIncludeUninstalledApps(Context context, boolean shouldInclude) {
        Observable.create(subscriber -> {
            checkPrefs(context);
            prefs.edit().putBoolean(SP_KEY_INCLUDE_UNINSTALLED_APPS, shouldInclude).apply();

            subscriber.onNext(shouldInclude);
            subscriber.onCompleted();
        })
                .subscribe();
    }

    public static void setIncludeSystemAppsApps(Context context, boolean shouldExclude) {
        Observable.create(subscriber -> {
            checkPrefs(context);
            prefs.edit().putBoolean(SP_KEY_EXCLUDE_SYSTEM_APPS, shouldExclude).apply();

            subscriber.onNext(shouldExclude);
            subscriber.onCompleted();
        })
                .subscribe();
    }

    public static boolean shouldIncludeUninstalledApps(Context context) {
        checkPrefs(context);
        return prefs.getBoolean(SP_KEY_INCLUDE_UNINSTALLED_APPS, false);
    }

    public static boolean shouldIncludeSystemApps(Context context) {
        checkPrefs(context);
        return prefs.getBoolean(SP_KEY_EXCLUDE_SYSTEM_APPS, true);
    }

    private static void checkPrefs(Context context) {
        if (prefs == null) {
            prefs = context.getSharedPreferences(SP_NAME, Context.MODE_PRIVATE);
        }
    }
}
