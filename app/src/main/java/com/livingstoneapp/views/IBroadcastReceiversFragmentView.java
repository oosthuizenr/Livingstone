package com.livingstoneapp.views;

import java.util.ArrayList;

import com.livingstoneapp.models.BroadcastReceiverInfo;

/**
 * Created by Renier on 2016/02/16.
 */
public interface IBroadcastReceiversFragmentView {
    void showError(String message);
    void setModel(ArrayList<BroadcastReceiverInfo> receivers);
    void showWaitDialog();
    void hideWaitDialog();
    void showDetailContainer();
    void hideDetailContainer();
    void startBroadcastReceiverDetailActivity(BroadcastReceiverInfo receiver);
    void showNoValuesText();
    void hideNoValuesText();
}
