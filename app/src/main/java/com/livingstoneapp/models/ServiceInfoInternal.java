package com.livingstoneapp.models;

import java.util.ArrayList;

/**
 * Created by renier on 2/22/2016.
 */
public class ServiceInfoInternal {
    private String mName;
    private boolean mEnabled;
    private boolean mExported;
    private String mProcessName;
    private String mPermission;
    private ArrayList<String> mFlags;

    public ServiceInfoInternal(String mName, boolean mEnabled, boolean mExported, String mProcessName, String mPermission, ArrayList<String> mFlags) {
        this.mName = mName;
        this.mEnabled = mEnabled;
        this.mExported = mExported;
        this.mProcessName = mProcessName;
        this.mPermission = mPermission;
        this.mFlags = mFlags;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public boolean isExported() {
        return mExported;
    }

    public ArrayList<String> getFlags() {
        return mFlags;
    }

    public String getName() {
        return mName;
    }

    public String getPermission() {
        return mPermission;
    }

    public String getProcessName() {
        return mProcessName;
    }
}
