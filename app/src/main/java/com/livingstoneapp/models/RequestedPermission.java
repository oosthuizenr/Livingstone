package com.livingstoneapp.models;

/**
 * Created by renier on 2/19/2016.
 */
public class RequestedPermission {
    private String mName;
    private boolean mGranted;

    public RequestedPermission(String mName, boolean mGranted) {
        this.mGranted = mGranted;
        this.mName = mName;
    }

    public boolean isGranted() {
        return mGranted;
    }

    public String getName() {
        return mName;
    }
}
