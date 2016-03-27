package com.livingstoneapp.views;

import java.util.List;

import com.livingstoneapp.models.InstalledPackageHeader;

/**
 * Created by renier on 2/2/2016.
 */
public interface IPackageListView {
    void init();
    void setInstalledPackages(List<InstalledPackageHeader> items);
    void setError(String message);
    void startDetailActivity(InstalledPackageHeader item);
    void setDetailFragment(InstalledPackageHeader item);
    void showWaitDialog();
    void hideWaitDialog();
    void showContentView();
    void hideContentView();
}
