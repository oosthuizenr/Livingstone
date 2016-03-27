package com.livingstoneapp.presenters;

import com.livingstoneapp.models.ContentProviderInfo;
import com.livingstoneapp.views.IContentProviderDetailActivityView;

/**
 * Created by renier on 2/18/2016.
 */
public interface IContentProviderDetailActivityPresenter {
    void setView(IContentProviderDetailActivityView view);
    void clearView();
    void init(ContentProviderInfo cpi);
}
