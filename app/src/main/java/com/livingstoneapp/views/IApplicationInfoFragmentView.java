package com.livingstoneapp.views;

/**
 * Created by Renier on 2016/02/13.
 */
public interface IApplicationInfoFragmentView {
    void showError(String message);
    void setModel(Object[] items, int[] dataSetTypes);
    void showWaitDialog();
    void hideWaitDialog();
    void showDetailContainer();
    void hideDetailContainer();

}
