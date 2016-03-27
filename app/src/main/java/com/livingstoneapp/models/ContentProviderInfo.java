package com.livingstoneapp.models;

import android.os.Parcel;
import android.os.Parcelable;

import java.util.ArrayList;

/**
 * Created by Renier on 2016/02/16.
 */
public class ContentProviderInfo implements Parcelable {
    private String mName;
    private boolean mEnabled;
    private boolean mExported;
    private String mAuthority;
    private String mReadPermission;
    private String mWritePermission;
    private String mProcessName;
    private boolean mMultiProcess;
    private boolean mSingleUser;
    private int mInitOrder;
    private boolean mIsSyncable;
    private boolean mGrantURIPermissions;
    private ArrayList<PathPermission> mPathPermissions;
    private ArrayList<URIPermissionPattern> mURIPermissionPatterns;

    public ContentProviderInfo(String name) {
        this.mName = name;
    }

    public ContentProviderInfo(
            String mName,
            boolean mEnabled,
            boolean mExported,
            String mAuthority,
            String mReadPermission,
            String mWritePermission,
            String mProcessName,
            boolean mMultiProcess,
            boolean mSingleUser,
            int mInitOrder,
            boolean mIsSyncable,
            boolean mGrantURIPermissions,
            ArrayList<PathPermission> mPathPermissions,
            ArrayList<URIPermissionPattern> mURIPermissionPatterns) {
        this.mAuthority = mAuthority;
        this.mEnabled = mEnabled;
        this.mExported = mExported;
        this.mGrantURIPermissions = mGrantURIPermissions;
        this.mInitOrder = mInitOrder;
        this.mIsSyncable = mIsSyncable;
        this.mMultiProcess = mMultiProcess;
        this.mName = mName;
        this.mPathPermissions = mPathPermissions;
        this.mProcessName = mProcessName;
        this.mReadPermission = mReadPermission;
        this.mSingleUser = mSingleUser;
        this.mURIPermissionPatterns = mURIPermissionPatterns;
        this.mWritePermission = mWritePermission;
    }

    public String getAuthority() {
        return mAuthority;
    }

    public boolean isEnabled() {
        return mEnabled;
    }

    public boolean isExported() {
        return mExported;
    }

    public boolean isGrantURIPermissions() {
        return mGrantURIPermissions;
    }

    public int getInitOrder() {
        return mInitOrder;
    }

    public boolean isIsSyncable() {
        return mIsSyncable;
    }

    public boolean isMultiProcess() {
        return mMultiProcess;
    }

    public String getName() {
        return mName;
    }

    public ArrayList<PathPermission> getPathPermissions() {
        return mPathPermissions;
    }

    public String getProcessName() {
        return mProcessName;
    }

    public String getReadPermission() {
        return mReadPermission;
    }

    public boolean isSingleUser() {
        return mSingleUser;
    }

    public ArrayList<URIPermissionPattern> getURIPermissionPatterns() {
        return mURIPermissionPatterns;
    }

    public String getWritePermission() {
        return mWritePermission;
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
        dest.writeString(this.mAuthority);
        dest.writeString(this.mReadPermission);
        dest.writeString(this.mWritePermission);
        dest.writeString(this.mProcessName);
        dest.writeByte(mMultiProcess ? (byte) 1 : (byte) 0);
        dest.writeByte(mSingleUser ? (byte) 1 : (byte) 0);
        dest.writeInt(this.mInitOrder);
        dest.writeByte(mIsSyncable ? (byte) 1 : (byte) 0);
        dest.writeByte(mGrantURIPermissions ? (byte) 1 : (byte) 0);
        dest.writeTypedList(mPathPermissions);
        dest.writeTypedList(mURIPermissionPatterns);
    }

    protected ContentProviderInfo(Parcel in) {
        this.mName = in.readString();
        this.mEnabled = in.readByte() != 0;
        this.mExported = in.readByte() != 0;
        this.mAuthority = in.readString();
        this.mReadPermission = in.readString();
        this.mWritePermission = in.readString();
        this.mProcessName = in.readString();
        this.mMultiProcess = in.readByte() != 0;
        this.mSingleUser = in.readByte() != 0;
        this.mInitOrder = in.readInt();
        this.mIsSyncable = in.readByte() != 0;
        this.mGrantURIPermissions = in.readByte() != 0;
        this.mPathPermissions = in.createTypedArrayList(PathPermission.CREATOR);
        this.mURIPermissionPatterns = in.createTypedArrayList(URIPermissionPattern.CREATOR);
    }

    public static final Parcelable.Creator<ContentProviderInfo> CREATOR = new Parcelable.Creator<ContentProviderInfo>() {
        public ContentProviderInfo createFromParcel(Parcel source) {
            return new ContentProviderInfo(source);
        }

        public ContentProviderInfo[] newArray(int size) {
            return new ContentProviderInfo[size];
        }
    };
}
