package com.livingstoneapp.models;

/**
 * Created by Renier on 2016/02/14.
 */
public class DirectoryInfo {
    private String mDataDir;
    private String mSourceDir;
    private String mNativeLibDir;
    private String mPublicSourceDir;
    private String[] mSplitPublicSourceDirs;
    private String[] mSplitSourceDirs;

    public DirectoryInfo(String mDataDir, String mSourceDir, String mNativeLibDir, String mPublicSourceDir, String[] mSplitPublicSourceDirs, String[] mSplitSourceDirs) {
        this.mDataDir = mDataDir;
        this.mSourceDir = mSourceDir;
        this.mNativeLibDir = mNativeLibDir;
        this.mPublicSourceDir = mPublicSourceDir;
        this.mSplitPublicSourceDirs = mSplitPublicSourceDirs;
        this.mSplitSourceDirs = mSplitSourceDirs;
    }

    public String getDataDir() {
        return mDataDir;
    }

    public void setDataDir(String mDataDir) {
        this.mDataDir = mDataDir;
    }

    public String getSourceDir() {
        return mSourceDir;
    }

    public void setSourceDir(String mSourceDir) {
        this.mSourceDir = mSourceDir;
    }

    public String getNativeLibDir() {
        return mNativeLibDir;
    }

    public void setNativeLibDir(String mNativeLibDir) {
        this.mNativeLibDir = mNativeLibDir;
    }

    public String getPublicSourceDir() {
        return mPublicSourceDir;
    }

    public void setPublicSourceDir(String mPublicSourceDir) {
        this.mPublicSourceDir = mPublicSourceDir;
    }

    public String[] getSplitPublicSourceDirs() {
        return mSplitPublicSourceDirs;
    }

    public void setSplitPublicSourceDirs(String[] mPublicSourceDirs) {
        this.mSplitPublicSourceDirs = mPublicSourceDirs;
    }

    public String[] getSplitSourceDirs() {
        return mSplitSourceDirs;
    }

    public void setSplitSourceDirs(String[] mSplitSourceDirs) {
        this.mSplitSourceDirs = mSplitSourceDirs;
    }
}
