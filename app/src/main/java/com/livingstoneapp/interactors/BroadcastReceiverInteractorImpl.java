package com.livingstoneapp.interactors;

import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import java.util.ArrayList;

import rx.Observable;

import com.livingstoneapp.models.BroadcastReceiverInfo;

/**
 * Created by renier on 2/17/2016.
 */
public class BroadcastReceiverInteractorImpl implements IBroadcastReceiverInteractor {
    private Context mContext;

    public BroadcastReceiverInteractorImpl(Context context) {
        mContext = context;
    }


    @Override
    public Observable<ArrayList<BroadcastReceiverInfo>> getBroadcastReceivers(String packageName) {
        return Observable.defer(() -> Observable.create(subscriber -> {
            try {
                ArrayList<BroadcastReceiverInfo> toReturn = new ArrayList<>();

                PackageManager packageManager = mContext.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_RECEIVERS);

                if (packageInfo.receivers != null) {
                    for (ActivityInfo ai : packageInfo.receivers) {
                        toReturn.add(new BroadcastReceiverInfo(ai.name));
                    }
                }

                subscriber.onNext(toReturn);

                subscriber.onCompleted();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
        }));
    }
}
