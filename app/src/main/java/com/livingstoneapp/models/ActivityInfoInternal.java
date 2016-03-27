package com.livingstoneapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Renier on 2016/02/16.
 */
public class ActivityInfoInternal implements Parcelable, Comparable<ActivityInfoInternal> {
    private String mName;
    private boolean mEnabled;
    private boolean mExported;
    private String mProcessName;
    private ArrayList<String> mConfigChanges;
    private boolean mLauncher;
    private boolean mMain;
    private String mDocumentLaunchMode;
    private ArrayList<String> mFlags;
    private String mLaunchMode;
    private int mMaxRecents;
    private String mParentActivityName;
    private String mPermission;
    private String mPersitableMode;
    private String mScreenOrientation;
    private ArrayList<String> mSoftInputMode;
    private String mTargetActivity;
    private String mTaskAffinity;
    private ArrayList<String> mCategories;

    public ActivityInfoInternal(
            String mName,
            boolean mEnabled,
            boolean mExported,
            String mProcessName,
            ArrayList<String> mConfigChanges,
            boolean mLauncher,
            boolean mMain,
            String mDocumentLaunchMode,
            ArrayList<String> mFlags,
            String mLaunchMode,
            int mMaxRecents,
            String mParentActivityName,
            String mPermission,
            String mPersitableMode,
            String mScreenOrientation,
            ArrayList<String> mSoftInputMode,
            String mTargetActivity,
            String mTaskAffinity,
            ArrayList<String> mCategories) {
        this.mConfigChanges = mConfigChanges;
        this.mDocumentLaunchMode = mDocumentLaunchMode;
        this.mEnabled = mEnabled;
        this.mExported = mExported;
        this.mFlags = mFlags;
        this.mLauncher = mLauncher;
        this.mLaunchMode = mLaunchMode;
        this.mMain = mMain;
        this.mMaxRecents = mMaxRecents;
        this.mName = mName;
        this.mParentActivityName = mParentActivityName;
        this.mPermission = mPermission;
        this.mPersitableMode = mPersitableMode;
        this.mProcessName = mProcessName;
        this.mScreenOrientation = mScreenOrientation;
        this.mSoftInputMode = mSoftInputMode;
        this.mTargetActivity = mTargetActivity;
        this.mTaskAffinity = mTaskAffinity;
        this.mCategories = mCategories;
    }

    public ArrayList<String> getConfigChanges() {
        return mConfigChanges;
    }

    public String getDocumentLaunchMode() {
        return mDocumentLaunchMode;
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

    public boolean isLauncher() {
        return mLauncher;
    }

    public String getLaunchMode() {
        return mLaunchMode;
    }

    public boolean isMain() {
        return mMain;
    }

    public int getMaxRecents() {
        return mMaxRecents;
    }

    public String getName() {
        return mName;
    }

    public String getParentActivityName() {
        return mParentActivityName;
    }

    public String getPermission() {
        return mPermission;
    }

    public String getPersitableMode() {
        return mPersitableMode;
    }

    public String getProcessName() {
        return mProcessName;
    }

    public String getScreenOrientation() {
        return mScreenOrientation;
    }

    public ArrayList<String> getSoftInputMode() {
        return mSoftInputMode;
    }

    public String getTargetActivity() {
        return mTargetActivity;
    }

    public String getTaskAffinity() {
        return mTaskAffinity;
    }

    public ArrayList<String> getCategories() {
        return mCategories;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
        dest.writeByte(mEnabled ? (byte) 1 : (byte) 0);
        dest.writeByte(mExported ? (byte) 1 : (byte) 0);
        dest.writeString(this.mProcessName);
        dest.writeStringList(this.mConfigChanges);
        dest.writeByte(mLauncher ? (byte) 1 : (byte) 0);
        dest.writeByte(mMain ? (byte) 1 : (byte) 0);
        dest.writeString(this.mDocumentLaunchMode);
        dest.writeStringList(this.mFlags);
        dest.writeString(this.mLaunchMode);
        dest.writeInt(this.mMaxRecents);
        dest.writeString(this.mParentActivityName);
        dest.writeString(this.mPermission);
        dest.writeString(this.mPersitableMode);
        dest.writeString(this.mScreenOrientation);
        dest.writeStringList(this.mSoftInputMode);
        dest.writeString(this.mTargetActivity);
        dest.writeString(this.mTaskAffinity);
        dest.writeStringList(this.mCategories);
    }

    protected ActivityInfoInternal(Parcel in) {
        this.mName = in.readString();
        this.mEnabled = in.readByte() != 0;
        this.mExported = in.readByte() != 0;
        this.mProcessName = in.readString();
        this.mConfigChanges = in.createStringArrayList();
        this.mLauncher = in.readByte() != 0;
        this.mMain = in.readByte() != 0;
        this.mDocumentLaunchMode = in.readString();
        this.mFlags = in.createStringArrayList();
        this.mLaunchMode = in.readString();
        this.mMaxRecents = in.readInt();
        this.mParentActivityName = in.readString();
        this.mPermission = in.readString();
        this.mPersitableMode = in.readString();
        this.mScreenOrientation = in.readString();
        this.mSoftInputMode = in.createStringArrayList();
        this.mTargetActivity = in.readString();
        this.mTaskAffinity = in.readString();
        this.mCategories = in.createStringArrayList();
    }

    public static final Parcelable.Creator<ActivityInfoInternal> CREATOR = new Parcelable.Creator<ActivityInfoInternal>() {
        public ActivityInfoInternal createFromParcel(Parcel source) {
            return new ActivityInfoInternal(source);
        }

        public ActivityInfoInternal[] newArray(int size) {
            return new ActivityInfoInternal[size];
        }
    };

    @Override
    public int compareTo(ActivityInfoInternal another) {
        if (this.isLauncher() && this.isMain()) {
            return -1;
        } else if (another.isLauncher()) {
            return 1;
        } else if (this.isMain()) {
            return -1;
        } else if (another.isMain()) {
            return 1;
        } else {
            return this.getName().compareToIgnoreCase(another.getName());
        }
    }
}
