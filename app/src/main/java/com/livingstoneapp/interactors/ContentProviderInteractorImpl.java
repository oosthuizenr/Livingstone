package com.livingstoneapp.interactors;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.ProviderInfo;
import android.os.PatternMatcher;

import java.util.ArrayList;

import rx.Observable;

import com.livingstoneapp.models.ContentProviderInfo;
import com.livingstoneapp.models.PathPermission;
import com.livingstoneapp.models.URIPermissionPattern;
import com.livingstoneapp.utils.Utils;

/**
 * Created by renier on 2/17/2016.
 */
public class ContentProviderInteractorImpl implements IContentProviderInteractor {
    private Context mContext;

    public ContentProviderInteractorImpl(Context context) {
        mContext = context;
    }


    @Override
    public Observable<ArrayList<ContentProviderInfo>> getContentProviders(String packageName) {
        return Observable.defer(() -> Observable.create(subscriber -> {
            try {
                ArrayList<ContentProviderInfo> toReturn = new ArrayList<>();

                PackageManager packageManager = mContext.getPackageManager();
                PackageInfo packageInfo = packageManager.getPackageInfo(packageName, PackageManager.GET_PROVIDERS);

                if (packageInfo.providers != null) {
                    for (ProviderInfo pi : packageInfo.providers) {

                        ArrayList<PathPermission> pathPermissions = new ArrayList<>();
                        ArrayList<URIPermissionPattern> uriPermissionPatterns = new ArrayList<>();

                        if (pi.pathPermissions != null) {
                            for (android.content.pm.PathPermission pp : pi.pathPermissions) {
                                String type = "";

                                switch (pp.getType()) {
                                    case android.content.pm.PathPermission.PATTERN_LITERAL:
                                        type = "PATTERN_LITERAL";
                                    case android.content.pm.PathPermission.PATTERN_PREFIX:
                                        type = "PATTERN_PREFIX";
                                    case android.content.pm.PathPermission.PATTERN_SIMPLE_GLOB:
                                        type = "PATTERN_SIMPLE_GLOB";
                                }

                                pathPermissions.add(new PathPermission(
                                    pp.getPath(),
                                        pp.getReadPermission(),
                                        pp.getWritePermission(),
                                        type
                                ));
                            }
                        }

                        if (pi.uriPermissionPatterns != null) {
                            for (PatternMatcher pm : pi.uriPermissionPatterns) {
                                String type = "";

                                switch (pm.getType()) {
                                    case android.content.pm.PathPermission.PATTERN_LITERAL:
                                        type = "PATTERN_LITERAL";
                                    case android.content.pm.PathPermission.PATTERN_PREFIX:
                                        type = "PATTERN_PREFIX";
                                    case android.content.pm.PathPermission.PATTERN_SIMPLE_GLOB:
                                        type = "PATTERN_SIMPLE_GLOB";
                                }

                                uriPermissionPatterns.add(new URIPermissionPattern(pm.getPath(), type));
                            }
                        }

                        toReturn.add(new ContentProviderInfo(pi.name,
                                pi.enabled,
                                pi.exported,
                                pi.authority,
                                pi.readPermission,
                                pi.writePermission,
                                pi.processName,
                                pi.multiprocess,
                                Utils.hasFlag(pi.flags, ProviderInfo.FLAG_SINGLE_USER),
                                pi.initOrder,
                                pi.isSyncable,
                                pi.grantUriPermissions,
                                pathPermissions,
                                uriPermissionPatterns));
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
