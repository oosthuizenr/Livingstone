package com.livingstoneapp.interactors;

import java.util.ArrayList;

import rx.Observable;

import com.livingstoneapp.models.BroadcastReceiverInfo;

/**
 * Created by renier on 2/17/2016.
 */
public interface IBroadcastReceiverInteractor {
    Observable<ArrayList<BroadcastReceiverInfo>> getBroadcastReceivers(String packageName);
}
