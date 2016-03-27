package com.livingstoneapp.models;

/**
 * Created by Renier on 2016/02/14.
 */
public class GeneralInfo {
    private String mPackageName;
    private int mVersionCode;
    private String mVersionName;
    private int mUID;
    private int mTargetSDK;
    private String mProcessName;
    private boolean mEnabled;
    private String mTaskAffinity;
    private long mFirstInstallTime;
    private long mLastUpdateTime;
    private String mSharedUID;
    private int mSharedUserLabel;

    public GeneralInfo(String mPackageName, int mVersionCode, String mVersionName, int mUID, int mTargetSDK, String mProcessName, boolean mEnabled, String mTaskAffinity, long mFirstInstallTime, long mLastUpdateTime, String mSharedUID, int mSharedUserLabel) {
        this.mPackageName = mPackageName;
        this.mVersionCode = mVersionCode;
        this.mVersionName = mVersionName;
        this.mUID = mUID;
        this.mTargetSDK = mTargetSDK;
        this.mProcessName = mProcessName;
        this.mEnabled = mEnabled;
        this.mTaskAffinity = mTaskAffinity;
        this.mFirstInstallTime = mFirstInstallTime;
        this.mLastUpdateTime = mLastUpdateTime;
        this.mSharedUID = mSharedUID;
        this.mSharedUserLabel = mSharedUserLabel;
    }

    public String getPackageName() {
        return mPackageName;
    }

    public void setPackageName(String mPackageName) {
        this.mPackageName = mPackageName;
    }

    public int getVersionCode() {
        return mVersionCode;
    }

    public void setVersionCode(int mVersionCode) {
        this.mVersionCode = mVersionCode;
    }

    public String getVersionName() {
        return mVersionName;
    }

    public void setVersionName(String mVersionName) {
        this.mVersionName = mVersionName;
    }

    public int getUID() {
        return mUID;
    }

    public void setUID(int mUID) {
        this.mUID = mUID;
    }

    public int getTargetSDK() {
        return mTargetSDK;
    }

    public void setTargetSDK(int mTargetSDK) {
        this.mTargetSDK = mTargetSDK;
    }

    public String getProcessName() {
        return mProcessName;
    }

    public void setProcessName(String mProcessName) {
        this.mProcessName = mProcessName;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public void setEnabled(boolean mEnabled) {
        this.mEnabled = mEnabled;
    }

    public String getTaskAffinity() {
        return mTaskAffinity;
    }

    public void setTaskAffinity(String mTaskAffinity) {
        this.mTaskAffinity = mTaskAffinity;
    }

    public long getFirstInstallTime() {
        return mFirstInstallTime;
    }

    public void setFirstInstallTime(long mFirstInstallTime) {
        this.mFirstInstallTime = mFirstInstallTime;
    }

    public long getLastUpdateTime() {
        return mLastUpdateTime;
    }

    public void setLastUpdateTime(long mLastUpdateTime) {
        this.mLastUpdateTime = mLastUpdateTime;
    }

    public String getSharedUID() {
        return mSharedUID;
    }

    public void setSharedUID(String mSharedUID) {
        this.mSharedUID = mSharedUID;
    }

    public int getSharedUserLabel() {
        return mSharedUserLabel;
    }

    public void setSharedUserLabel(int mSharedUserLabel) {
        this.mSharedUserLabel = mSharedUserLabel;
    }
}
