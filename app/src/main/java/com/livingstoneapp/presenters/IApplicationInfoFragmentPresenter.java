package com.livingstoneapp.presenters;

import com.livingstoneapp.views.IApplicationInfoFragmentView;

/**
 * Created by Renier on 2016/02/13.
 */
public interface IApplicationInfoFragmentPresenter {
    void setView(IApplicationInfoFragmentView view);
    void clearView();
    void init(String packageName);
}
