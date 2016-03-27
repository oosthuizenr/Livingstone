package com.livingstoneapp.models;

import java.util.ArrayList;

/**
 * Created by renier on 2/18/2016.
 */
public class DeclaredPermission {

    private String mName;
    private String mLabel;
    private ArrayList<String> mProtectionLevel;
    private ArrayList<String> mFlags;

    public DeclaredPermission(String mName, String mLabel, ArrayList<String> mProtectionLevel, ArrayList<String> mFlags) {
        this.mLabel = mLabel;
        this.mName = mName;
        this.mProtectionLevel = mProtectionLevel;
        this.mFlags = mFlags;
    }

    public String getLabel() {
        return mLabel;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<String> getProtectionLevel() {
        return mProtectionLevel;
    }

    public ArrayList<String> getFlags() { return mFlags; }
}
