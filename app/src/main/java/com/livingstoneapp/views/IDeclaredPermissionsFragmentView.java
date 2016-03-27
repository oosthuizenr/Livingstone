package com.livingstoneapp.views;

import java.util.ArrayList;

import com.livingstoneapp.models.DeclaredPermission;


/**
 * Created by renier on 2/18/2016.
 */
public interface IDeclaredPermissionsFragmentView {
    void showError(String message);
    void setModel(ArrayList<DeclaredPermission> permissions);
    void showWaitDialog();
    void hideWaitDialog();
    void showDetailContainer();
    void hideDetailContainer();
    void showNoValuesText();
    void hideNoValuesText();
}
