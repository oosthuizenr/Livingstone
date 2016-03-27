package com.livingstoneapp.views;

import java.util.ArrayList;

import com.livingstoneapp.models.FeatureInfoInternal;


/**
 * Created by renier on 2/18/2016.
 */
public interface IRequestedFeaturesFragmentView {
    void showError(String message);
    void setModel(ArrayList<FeatureInfoInternal> permissions);
    void showWaitDialog();
    void hideWaitDialog();
    void showDetailContainer();
    void hideDetailContainer();
    void showNoValuesText();
    void hideNoValuesText();
}
