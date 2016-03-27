package com.livingstoneapp.interactors;

import java.util.ArrayList;

import rx.Observable;
import com.livingstoneapp.models.ActivityInfoInternal;

/**
 * Created by renier on 2/17/2016.
 */
public interface IActivityInteractor {
    Observable<ArrayList<ActivityInfoInternal>> getActivities(String packageName);
}
