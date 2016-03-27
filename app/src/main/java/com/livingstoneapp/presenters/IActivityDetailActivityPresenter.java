package com.livingstoneapp.presenters;

import com.livingstoneapp.models.ActivityInfoInternal;
import com.livingstoneapp.views.IActivityDetailActivityView;

/**
 * Created by renier on 2/18/2016.
 */
public interface IActivityDetailActivityPresenter {
    void setView(IActivityDetailActivityView view);
    void clearView();
    void init(ActivityInfoInternal ai);
}
