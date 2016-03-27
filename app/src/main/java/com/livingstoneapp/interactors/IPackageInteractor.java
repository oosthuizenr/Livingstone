package com.livingstoneapp.interactors;

import java.util.ArrayList;

import rx.Observable;
import com.livingstoneapp.models.AppInfo;
import com.livingstoneapp.models.FeatureInfoInternal;
import com.livingstoneapp.models.InstalledPackageHeader;

/**
 * Created by renier on 2/2/2016.
 */
public interface IPackageInteractor {
    Observable<ArrayList<InstalledPackageHeader>> getInstalledPackages(boolean refresh);
    Observable<String> getAppNameFromPackage(String packageName);
    Observable<AppInfo> getApplicationInfo(String packageName);
    Observable<ArrayList<FeatureInfoInternal>> getRequestedFeatures(String packageName);

}
