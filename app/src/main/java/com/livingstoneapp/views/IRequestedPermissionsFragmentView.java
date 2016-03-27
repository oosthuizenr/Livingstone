package com.livingstoneapp.views;

import java.util.ArrayList;

import com.livingstoneapp.models.RequestedPermission;


/**
 * Created by renier on 2/18/2016.
 */
public interface IRequestedPermissionsFragmentView {
    void showError(String message);
    void setModel(ArrayList<RequestedPermission> permissions);
    void showWaitDialog();
    void hideWaitDialog();
    void showDetailContainer();
    void hideDetailContainer();
    void showNoValuesText();
    void hideNoValuesText();
}
