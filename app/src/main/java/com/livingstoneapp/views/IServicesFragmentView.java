package com.livingstoneapp.views;

import java.util.ArrayList;

import com.livingstoneapp.models.ServiceInfoInternal;


/**
 * Created by renier on 2/18/2016.
 */
public interface IServicesFragmentView {
    void showError(String message);
    void setModel(ArrayList<ServiceInfoInternal> services);
    void showWaitDialog();
    void hideWaitDialog();
    void showDetailContainer();
    void hideDetailContainer();
    void showNoValuesText();
    void hideNoValuesText();
}
