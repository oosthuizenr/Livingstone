package com.livingstoneapp.models;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Renier on 2016/02/16.
 */
public class BroadcastReceiverInfo implements Parcelable, Comparable<BroadcastReceiverInfo> {
    private String mName;

    public BroadcastReceiverInfo(
            String mName) {
        this.mName = mName;
    }


    public String getName() {
        return mName;
    }


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.mName);
    }

    protected BroadcastReceiverInfo(Parcel in) {
        this.mName = in.readString();
    }

    public static final Creator<BroadcastReceiverInfo> CREATOR = new Creator<BroadcastReceiverInfo>() {
        public BroadcastReceiverInfo createFromParcel(Parcel source) {
            return new BroadcastReceiverInfo(source);
        }

        public BroadcastReceiverInfo[] newArray(int size) {
            return new BroadcastReceiverInfo[size];
        }
    };

    @Override
    public int compareTo(BroadcastReceiverInfo another) {
        return this.getName().compareToIgnoreCase(another.getName());
    }
}
