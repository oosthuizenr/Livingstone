package com.livingstoneapp.interactors;

import java.util.ArrayList;

import rx.Observable;
import com.livingstoneapp.models.ContentProviderInfo;

/**
 * Created by renier on 2/17/2016.
 */
public interface IContentProviderInteractor {
    Observable<ArrayList<ContentProviderInfo>> getContentProviders(String packageName);
}
