package com.livingstoneapp.presenters;

import com.livingstoneapp.models.BroadcastReceiverInfo;
import com.livingstoneapp.views.IBroadcastReceiversFragmentView;

/**
 * Created by Renier on 2016/02/16.
 */
public interface IBroadcastReceiversFragmentPresenter {
    void setView(IBroadcastReceiversFragmentView view);
    void clearView();
    void init(String packageName);
    void onBroadcastReceiverSelected(BroadcastReceiverInfo receiver);
}
