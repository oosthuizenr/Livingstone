package com.livingstoneapp.presenters;

import com.livingstoneapp.views.IRequestedPermissionsFragmentView;

/**
 * Created by renier on 2/18/2016.
 */
public interface IRequestedPermissionsFragmentPresenter {

    void setView(IRequestedPermissionsFragmentView view);
    void clearView();
    void init(String packageName);
}
