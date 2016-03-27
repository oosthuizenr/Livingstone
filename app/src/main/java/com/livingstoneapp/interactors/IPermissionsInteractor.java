package com.livingstoneapp.interactors;

import java.util.ArrayList;

import rx.Observable;
import com.livingstoneapp.models.DeclaredPermission;
import com.livingstoneapp.models.RequestedPermission;

/**
 * Created by renier on 2/19/2016.
 */
public interface IPermissionsInteractor {
    Observable<ArrayList<DeclaredPermission>> getDeclaredPermissions(String packageName);
    Observable<ArrayList<RequestedPermission>> getRequestedPermissions(String packageName);
}
