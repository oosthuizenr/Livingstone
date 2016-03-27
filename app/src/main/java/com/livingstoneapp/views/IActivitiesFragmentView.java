package com.livingstoneapp.views;

import java.util.ArrayList;

import com.livingstoneapp.models.ActivityInfoInternal;

/**
 * Created by Renier on 2016/02/16.
 */
public interface IActivitiesFragmentView {
    void showError(String message);
    void setModel(ArrayList<ActivityInfoInternal> activities);
    void showWaitDialog();
    void hideWaitDialog();
    void showDetailContainer();
    void hideDetailContainer();
    void startActivityDetailActivity(ActivityInfoInternal activity);
    void showNoValuesText();
    void hideNoValuesText();
}
