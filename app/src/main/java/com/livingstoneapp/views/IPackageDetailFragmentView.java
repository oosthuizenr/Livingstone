package com.livingstoneapp.views;

/**
 * Created by renier on 2/3/2016.
 */
public interface IPackageDetailFragmentView {

    void setTitle(String title);
    void showWaitDialog();
    void hideWaitDialog();
    void showDetailContainer();
    void hideDetailContainer();
    void initViews(String appName, String packageName);
    void showError(String message);
}
