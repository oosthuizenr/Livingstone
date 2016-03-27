package com.livingstoneapp.presenters;

import com.livingstoneapp.models.ContentProviderInfo;
import com.livingstoneapp.views.IContentProvidersFragmentView;

/**
 * Created by Renier on 2016/02/16.
 */
public interface IContentProvidersFragmentPresenter {
    void setView(IContentProvidersFragmentView view);
    void clearView();
    void init(String packageName);
    void onContentProviderSelected(ContentProviderInfo provider);
}
