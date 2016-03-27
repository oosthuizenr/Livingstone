package com.livingstoneapp.presenters;

import com.livingstoneapp.views.IRequestedFeaturesFragmentView;

/**
 * Created by renier on 2/18/2016.
 */
public interface IRequestedFeaturesFragmentPresenter {

    void setView(IRequestedFeaturesFragmentView view);
    void clearView();
    void init(String packageName);
}
