package com.livingstoneapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by renier on 2/22/2016.
 */
public class PathPermission implements Parcelable {

    private String mPath;
    private String mType;
    private String mWritePermission;
    private String mReadPermission;

    public PathPermission(String mPath, String mReadPermission, String mWritePermission, String mType) {
        this.mPath = mPath;
        this.mReadPermission = mReadPermission;
        this.mType = mType;
        this.mWritePermission = mWritePermission;
    }

    public String getPath() {
        return mPath;
    }

    public String getReadPermission() {
        return mReadPermission;
    }

    public String getType() {
        return mType;
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
        dest.writeString(this.mPath);
        dest.writeString(this.mType);
        dest.writeString(this.mWritePermission);
        dest.writeString(this.mReadPermission);
    }

    protected PathPermission(Parcel in) {
        this.mPath = in.readString();
        this.mType = in.readString();
        this.mWritePermission = in.readString();
        this.mReadPermission = in.readString();
    }

    public static final Parcelable.Creator<PathPermission> CREATOR = new Parcelable.Creator<PathPermission>() {
        public PathPermission createFromParcel(Parcel source) {
            return new PathPermission(source);
        }

        public PathPermission[] newArray(int size) {
            return new PathPermission[size];
        }
    };
}
