package com.livingstoneapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by renier on 2/22/2016.
 */
public class URIPermissionPattern implements Parcelable {
    private String mPath;
    private String mType;

    public URIPermissionPattern(String mPath, String mType) {
        this.mPath = mPath;
        this.mType = mType;
    }

    public String getPath() {
        return mPath;
    }

    public String getType() {
        return mType;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mPath);
        dest.writeString(this.mType);
    }

    protected URIPermissionPattern(Parcel in) {
        this.mPath = in.readString();
        this.mType = in.readString();
    }

    public static final Parcelable.Creator<URIPermissionPattern> CREATOR = new Parcelable.Creator<URIPermissionPattern>() {
        public URIPermissionPattern createFromParcel(Parcel source) {
            return new URIPermissionPattern(source);
        }

        public URIPermissionPattern[] newArray(int size) {
            return new URIPermissionPattern[size];
        }
    };
}
