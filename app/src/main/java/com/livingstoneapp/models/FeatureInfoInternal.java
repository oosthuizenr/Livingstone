package com.livingstoneapp.models;

/**
 * Created by renier on 2/22/2016.
 */
public class FeatureInfoInternal {
    private String mName;
    private boolean mRequired;
    private String mGLESVersion;

    public FeatureInfoInternal(String mName, boolean mRequired, String mGLESVersion) {

        this.mName = mName;
        this.mRequired = mRequired;
        this.mGLESVersion = mGLESVersion;
    }

    public String getName() {
        return mName;
    }

    public boolean isRequired() {
        return mRequired;
    }

    public String getGLESVersion() {
        return mGLESVersion;
    }
}
