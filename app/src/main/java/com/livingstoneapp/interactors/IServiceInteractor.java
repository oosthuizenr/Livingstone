package com.livingstoneapp.interactors;

import java.util.ArrayList;

import rx.Observable;
import com.livingstoneapp.models.ServiceInfoInternal;

/**
 * Created by renier on 2/22/2016.
 */
public interface IServiceInteractor {
    Observable<ArrayList<ServiceInfoInternal>> getServices(String packageName);
}
