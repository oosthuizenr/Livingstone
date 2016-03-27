package com.livingstoneapp.models;

import android.graphics.drawable.Drawable;

/**
 * Created by renier on 2/2/2016.
 */
public class InstalledPackageHeader {
    private String mPackageName;
    private String mAppName;
    private Drawable mAppIcon;

    public InstalledPackageHeader() {

    }

    public InstalledPackageHeader(String packageName, String appName, Drawable appIcon) {
        this.mPackageName = packageName;
        this.mAppName = appName;
        this.mAppIcon = appIcon;
    }

    public Drawable getAppIcon() {
        return mAppIcon;
    }
    public void setAppIcon(Drawable mAppIcon) {
        this.mAppIcon = mAppIcon;
    }

    public String getAppName() {
        return mAppName;
    }
    public void setAppName(String mAppName) {
        this.mAppName = mAppName;
    }

    public String getPackageName() {
        return mPackageName;
    }
    public void setPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }
}
