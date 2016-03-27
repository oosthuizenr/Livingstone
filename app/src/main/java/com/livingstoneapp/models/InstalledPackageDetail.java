package com.livingstoneapp.models;

import android.graphics.drawable.Drawable;

import java.util.ArrayList;

/**
 * Created by renier on 2/2/2016.
 */
public class InstalledPackageDetail {
    private String mPackageName;
    private String mAppName;
    private Drawable mAppIcon;
    private int mVersionCode;
    private String mVersionName;
    private String mAPKPath;
    private String mDataPath;
    private String[] mPermissions;
    private String[] mRequestedFeatures;

    private ArrayList<String> mFlags;

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

    public ArrayList<String> getFlags() {
        return mFlags;
    }
    public void setFlags(ArrayList<String> mFlags) {
        this.mFlags = mFlags;
    }

}
