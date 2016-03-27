package com.livingstoneapp.presenters;

import com.livingstoneapp.views.IServicesFragmentView;

/**
 * Created by renier on 2/18/2016.
 */
public interface IServicesFragmentPresenter {

    void setView(IServicesFragmentView view);
    void clearView();
    void init(String packageName);
}
