package com.livingstoneapp.presenters;

import com.livingstoneapp.models.InstalledPackageHeader;
import com.livingstoneapp.views.IPackageListView;

/**
 * Created by renier on 2/2/2016.
 */
public interface IPackageListActivityPresenter {
    void setView(IPackageListView view);
    void clearView();
    void setTwoPane();
    void init();
    void loadPackages(boolean refresh);
    void packageSelected(InstalledPackageHeader header);



}
