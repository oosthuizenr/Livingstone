package com.livingstoneapp.models;

import java.util.ArrayList;

/**
 * Created by Renier on 2016/02/14.
 */
public class AppInfo {

    private ArrayList<String> mSignatures;
    private ArrayList<String> mFlags;
    private DirectoryInfo mDirectoryInfo;
    private GeneralInfo mGeneralInfo;

    public ArrayList<String> getSignatures() {
        return mSignatures;
    }

    public void setSignatures(ArrayList<String> mSignatures) {
        this.mSignatures = mSignatures;
    }

    public ArrayList<String> getFlags() {
        return mFlags;
    }
    public void setFlags(ArrayList<String> mFlags) {
        this.mFlags = mFlags;
    }

    public DirectoryInfo getDirectoryInfo() {
        return mDirectoryInfo;
    }

    public void setDirectoryInfo(DirectoryInfo mDirectoryInfo) {
        this.mDirectoryInfo = mDirectoryInfo;
    }

    public GeneralInfo getGeneralInfo() {
        return mGeneralInfo;
    }

    public void setGeneralInfo(GeneralInfo mGeneralInfo) {
        this.mGeneralInfo = mGeneralInfo;
    }
}
