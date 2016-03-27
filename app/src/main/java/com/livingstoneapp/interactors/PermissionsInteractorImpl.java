package com.livingstoneapp.interactors;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Build;

import java.util.ArrayList;

import rx.Observable;
import com.livingstoneapp.models.DeclaredPermission;
import com.livingstoneapp.models.RequestedPermission;
import com.livingstoneapp.utils.Utils;

/**
 * Created by renier on 2/19/2016.
 */
public class PermissionsInteractorImpl implements IPermissionsInteractor {
    private Context mContext;

    public PermissionsInteractorImpl(Context context) {
        mContext = context;
    }

    @Override
    public Observable<ArrayList<DeclaredPermission>> getDeclaredPermissions(String packageName) {
        return Observable.defer(() -> Observable.create(subscriber -> {
            try {
                ArrayList<DeclaredPermission> toReturn = new ArrayList<>();

                PackageManager packageManager = mContext.getPackageManager();
                PackageInfo pi = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

                if (pi.permissions != null) {
                    for (PermissionInfo info : pi.permissions) {
                        ArrayList<String> protectionLevels = new ArrayList<>();
                        ArrayList<String> flags = new ArrayList<>();

                        //Protection Levels
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_DANGEROUS))
                            protectionLevels.add("PROTECTION_DANGEROUS");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_FLAG_APPOP))
                            protectionLevels.add("PROTECTION_FLAG_APPOP");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_FLAG_DEVELOPMENT))
                            protectionLevels.add("PROTECTION_FLAG_DEVELOPMENT");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_FLAG_INSTALLER))
                            protectionLevels.add("PROTECTION_FLAG_INSTALLER");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_FLAG_PRE23))
                            protectionLevels.add("PROTECTION_FLAG_PRE23");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_FLAG_PREINSTALLED))
                            protectionLevels.add("PROTECTION_FLAG_PREINSTALLED");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_FLAG_PRIVILEGED))
                            protectionLevels.add("PROTECTION_FLAG_PRIVILEGED");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_FLAG_VERIFIER))
                            protectionLevels.add("PROTECTION_FLAG_VERIFIER");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_NORMAL))
                            protectionLevels.add("PROTECTION_NORMAL");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_SIGNATURE))
                            protectionLevels.add("PROTECTION_SIGNATURE");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_FLAG_SYSTEM))
                            protectionLevels.add("PROTECTION_FLAG_SYSTEM");
                        if (Utils.hasFlag(info.protectionLevel, PermissionInfo.PROTECTION_SIGNATURE_OR_SYSTEM))
                            protectionLevels.add("PROTECTION_SIGNATURE_OR_SYSTEM");

                        //Flags
                        if (Utils.hasFlag(info.flags, PermissionInfo.FLAG_COSTS_MONEY))
                            flags.add("FLAG_COSTS_MONEY");
                        //hidden - http://developer.android.com/intl/ko/reference/android/R.attr.html#permissionFlags
                        if (Utils.hasFlag(info.flags, 0x2))
                            flags.add("hidden");

                        toReturn.add(new DeclaredPermission(
                                info.name.startsWith(packageName) == true ? info.name.replace(packageName, "") : info.name,
                                String.valueOf(info.loadLabel(packageManager)),
                                protectionLevels,
                                flags));
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

    @Override
    public Observable<ArrayList<RequestedPermission>> getRequestedPermissions(String packageName) {
        return Observable.defer(() -> Observable.create(subscriber -> {
            try {
                ArrayList<RequestedPermission> toReturn = new ArrayList<>();

                PackageManager packageManager = mContext.getPackageManager();
                PackageInfo pi = packageManager.getPackageInfo(packageName, PackageManager.GET_PERMISSIONS);

                if (pi.requestedPermissions != null) {
                    for (int i = 0; i < pi.requestedPermissions.length; i++) {
                        boolean granted = true;
                        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
                            switch (pi.requestedPermissionsFlags[i]) {
                                case 1:
                                    //The permission has been denied
                                    granted = false;
                                    break;
                            }
                        }

                        toReturn.add(new RequestedPermission(pi.requestedPermissions[i], granted));
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
