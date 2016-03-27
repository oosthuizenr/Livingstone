package com.livingstoneapp.presenters;

import com.livingstoneapp.models.ActivityInfoInternal;
import com.livingstoneapp.views.IActivitiesFragmentView;

/**
 * Created by Renier on 2016/02/16.
 */
public interface IActivitiesFragmentPresenter {
    void setView(IActivitiesFragmentView view);
    void clearView();
    void init(String packageName);
    void onActivitySelected(ActivityInfoInternal activity);
}
