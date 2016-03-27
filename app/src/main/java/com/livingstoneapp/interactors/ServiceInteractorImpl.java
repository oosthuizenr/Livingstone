package com.livingstoneapp.interactors;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ServiceInfo;

import java.util.ArrayList;

import rx.Observable;

import com.livingstoneapp.models.ServiceInfoInternal;
import com.livingstoneapp.utils.Utils;

/**
 * Created by renier on 2/22/2016.
 */
public class ServiceInteractorImpl implements IServiceInteractor {
    private Context mContext;

    public ServiceInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public Observable<ArrayList<ServiceInfoInternal>> getServices(String packageName) {
        return Observable.defer(() -> Observable.create(subscriber -> {
            try {
                PackageManager packageManager = mContext.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_SERVICES);

                ArrayList<ServiceInfoInternal> toReturn = new ArrayList<>();

                if (packageInfo.services != null) {
                    for (ServiceInfo si : packageInfo.services) {
                        ArrayList<String> flags = new ArrayList<>();

                        if (Utils.hasFlag(si.flags, ServiceInfo.FLAG_ISOLATED_PROCESS))
                            flags.add("FLAG_ISOLATED_PROCESS");
                        if (Utils.hasFlag(si.flags, ServiceInfo.FLAG_SINGLE_USER))
                            flags.add("FLAG_SINGLE_USER");
                        if (Utils.hasFlag(si.flags, ServiceInfo.FLAG_STOP_WITH_TASK))
                            flags.add("FLAG_STOP_WITH_TASK");

                        toReturn.add(new ServiceInfoInternal(
                                        si.name,
                                        si.enabled,
                                        si.exported,
                                        si.processName,
                                        si.permission,
                                        flags
                                )
                        );
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
