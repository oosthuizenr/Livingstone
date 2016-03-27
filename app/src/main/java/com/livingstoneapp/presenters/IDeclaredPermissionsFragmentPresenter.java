package com.livingstoneapp.presenters;

import com.livingstoneapp.views.IDeclaredPermissionsFragmentView;

/**
 * Created by renier on 2/18/2016.
 */
public interface IDeclaredPermissionsFragmentPresenter {

    void setView(IDeclaredPermissionsFragmentView view);
    void clearView();
    void init(String packageName);
}
