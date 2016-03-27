package com.livingstoneapp.interactors;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.Signature;
import android.os.Build;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import rx.Observable;
import com.livingstoneapp.R;
import com.livingstoneapp.helpers.SettingsHelper;
import com.livingstoneapp.models.AppInfo;
import com.livingstoneapp.models.DirectoryInfo;
import com.livingstoneapp.models.FeatureInfoInternal;
import com.livingstoneapp.models.GeneralInfo;
import com.livingstoneapp.models.InstalledPackageHeader;
import com.livingstoneapp.utils.Utils;

/**
 * Created by renier on 2/2/2016.
 */
public class PackageInteractorImpl implements IPackageInteractor {
    private Context mContext;
    private ArrayList<InstalledPackageHeader> mApps;

    public PackageInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public Observable<ArrayList<InstalledPackageHeader>> getInstalledPackages(boolean refresh) {
        return Observable.defer(() -> {
            if (mApps != null && refresh != true) {
                return Observable.just(mApps);
            } else {
                return Observable.create(subscriber -> {
                    mApps = new ArrayList<>();

                    PackageManager packageManager = mContext.getPackageManager();

                    int flags = -1;

                    if (SettingsHelper.shouldIncludeUninstalledApps(mContext)) {
                        flags = PackageManager.GET_UNINSTALLED_PACKAGES;
                    }

                    boolean shouldIncludeSystemApps = SettingsHelper.shouldIncludeSystemApps(mContext);


                    List<ApplicationInfo> apps = packageManager.getInstalledApplications(flags);

                    for (int i = 0; i < apps.size(); i++) {
                        ApplicationInfo appInfo = apps.get(i);

                        if (!shouldIncludeSystemApps) {
                            if (Utils.hasFlag(appInfo.flags, ApplicationInfo.FLAG_SYSTEM)) {
                                continue;
                            }
                        }

                        InstalledPackageHeader header = new InstalledPackageHeader();

                        header.setPackageName(appInfo.packageName);
                        header.setAppName(appInfo.loadLabel(packageManager).toString());

                        try {
                            header.setAppIcon(appInfo.loadIcon(packageManager));
                        } catch (Exception e) {
                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                                header.setAppIcon(mContext.getDrawable(R.mipmap.default_icon));
                            } else {
                                header.setAppIcon(mContext.getResources().getDrawable(R.mipmap.default_icon));
                            }
                        }

                        mApps.add(header);
                    }

                    Collections.sort(mApps, new Comparator() {
                        @Override
                        public int compare(Object lhs, Object rhs) {
                            return ((InstalledPackageHeader) lhs).getAppName().compareToIgnoreCase(((InstalledPackageHeader) rhs).getAppName());
                        }
                    });

                    subscriber.onNext(mApps);

                    subscriber.onCompleted();
                });
            }
        });
    }

    @Override
    public Observable<String> getAppNameFromPackage(String packageName) {
        return Observable.defer(() -> Observable.create(subscriber -> {
            try {
                PackageManager packageManager = mContext.getPackageManager();
                ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, -1);
                subscriber.onNext(appInfo.loadLabel(packageManager).toString());
                subscriber.onCompleted();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                subscriber.onError(e);
            }

        }));
    }

    @Override
    public Observable<AppInfo> getApplicationInfo(String packageName) {
        return Observable.defer(() -> Observable.create(subscriber -> {
            try {
                PackageManager packageManager = mContext.getPackageManager();
                ApplicationInfo appInfo = packageManager.getApplicationInfo(packageName, -1);
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_META_DATA | PackageManager.GET_SIGNATURES);

                AppInfo toReturn = new AppInfo();

                toReturn.setFlags(parseAppFlags(appInfo.flags));

                DirectoryInfo di;

                if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP) {
                    di = new DirectoryInfo(
                            appInfo.dataDir,
                            appInfo.sourceDir,
                            appInfo.nativeLibraryDir,
                            appInfo.publicSourceDir,
                            null,
                            null
                    );
                } else {
                    di = new DirectoryInfo(
                            appInfo.dataDir,
                            appInfo.sourceDir,
                            appInfo.nativeLibraryDir,
                            appInfo.publicSourceDir,
                            appInfo.splitPublicSourceDirs,
                            appInfo.splitSourceDirs
                    );
                }
                toReturn.setDirectoryInfo(di);

                GeneralInfo gi = new GeneralInfo(
                        packageName,
                        packageInfo.versionCode,
                        packageInfo.versionName,
                        appInfo.uid,
                        appInfo.targetSdkVersion,
                        appInfo.processName,
                        appInfo.enabled,
                        appInfo.taskAffinity,
                        packageInfo.firstInstallTime,
                        packageInfo.lastUpdateTime,
                        packageInfo.sharedUserId,
                        packageInfo.sharedUserLabel
                );
                toReturn.setGeneralInfo(gi);

                ArrayList<String> signatures = new ArrayList<>();
                if (packageInfo.signatures != null) {
                    for (Signature sig : packageInfo.signatures) {
                        signatures.add(sig.toCharsString());
                    }
                }
                toReturn.setSignatures(signatures);

                subscriber.onNext(toReturn);

                subscriber.onCompleted();
            } catch (PackageManager.NameNotFoundException e) {
                e.printStackTrace();
                subscriber.onError(e);
            }
        }));
    }

    @Override
    public Observable<ArrayList<FeatureInfoInternal>> getRequestedFeatures(String packageName) {
        return Observable.defer(() -> Observable.create(subscriber -> {
            try {
                PackageManager packageManager = mContext.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_CONFIGURATIONS);

                ArrayList<FeatureInfoInternal> toReturn = new ArrayList<>();

                if (packageInfo.reqFeatures != null) {
                    for (android.content.pm.FeatureInfo fi : packageInfo.reqFeatures) {
                        toReturn.add(new FeatureInfoInternal(
                                        fi.name,
                                        Utils.hasFlag(fi.flags, android.content.pm.FeatureInfo.FLAG_REQUIRED),
                                        fi.getGlEsVersion()
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

    private ArrayList<String> parseAppFlags(int flags) {
        ArrayList<String> toReturn = new ArrayList<>();

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_ALLOW_BACKUP))
            toReturn.add("FLAG_ALLOW_BACKUP");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_ALLOW_CLEAR_USER_DATA))
            toReturn.add("FLAG_ALLOW_CLEAR_USER_DATA");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_ALLOW_TASK_REPARENTING))
            toReturn.add("FLAG_ALLOW_TASK_REPARENTING");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_DEBUGGABLE))
            toReturn.add("FLAG_DEBUGGABLE");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_EXTERNAL_STORAGE))
            toReturn.add("FLAG_EXTERNAL_STORAGE");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_EXTRACT_NATIVE_LIBS))
            toReturn.add("FLAG_EXTRACT_NATIVE_LIBS");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_FACTORY_TEST))
            toReturn.add("FLAG_FACTORY_TEST");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_FULL_BACKUP_ONLY))
            toReturn.add("FLAG_FULL_BACKUP_ONLY");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_HARDWARE_ACCELERATED))
            toReturn.add("FLAG_HARDWARE_ACCELERATED");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_HAS_CODE))
            toReturn.add("FLAG_HAS_CODE");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_INSTALLED))
            toReturn.add("FLAG_INSTALLED");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_IS_DATA_ONLY))
            toReturn.add("FLAG_IS_DATA_ONLY");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_IS_GAME))
            toReturn.add("FLAG_IS_GAME");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_KILL_AFTER_RESTORE))
            toReturn.add("FLAG_KILL_AFTER_RESTORE");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_LARGE_HEAP))
            toReturn.add("FLAG_LARGE_HEAP");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_MULTIARCH))
            toReturn.add("FLAG_MULTIARCH");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_PERSISTENT))
            toReturn.add("FLAG_PERSISTENT");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_RESIZEABLE_FOR_SCREENS))
            toReturn.add("FLAG_RESIZEABLE_FOR_SCREENS");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_RESTORE_ANY_VERSION))
            toReturn.add("FLAG_RESTORE_ANY_VERSION");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_STOPPED))
            toReturn.add("FLAG_STOPPED");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_SUPPORTS_LARGE_SCREENS))
            toReturn.add("FLAG_SUPPORTS_LARGE_SCREENS");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_SUPPORTS_NORMAL_SCREENS))
            toReturn.add("FLAG_SUPPORTS_NORMAL_SCREENS");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_SUPPORTS_RTL))
            toReturn.add("FLAG_SUPPORTS_RTL");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_SUPPORTS_SCREEN_DENSITIES))
            toReturn.add("FLAG_SUPPORTS_SCREEN_DENSITIES");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_SUPPORTS_SMALL_SCREENS))
            toReturn.add("FLAG_SUPPORTS_SMALL_SCREENS");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_SYSTEM))
            toReturn.add("FLAG_SYSTEM");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_TEST_ONLY))
            toReturn.add("FLAG_TEST_ONLY");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_UPDATED_SYSTEM_APP))
            toReturn.add("FLAG_UPDATED_SYSTEM_APP");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_USES_CLEARTEXT_TRAFFIC))
            toReturn.add("FLAG_USES_CLEARTEXT_TRAFFIC");

        if (Utils.hasFlag(flags, ApplicationInfo.FLAG_VM_SAFE_MODE))
            toReturn.add("FLAG_VM_SAFE_MODE");

        return toReturn;
    }
}
