package com.livingstoneapp.presenters;

import com.livingstoneapp.views.IPackageDetailFragmentView;

/**
 * Created by renier on 2/3/2016.
 */
public interface IPackageDetailFragmentPresenter {
    void setView(IPackageDetailFragmentView view);
    void clearView();
    void init(String packageName);
}
