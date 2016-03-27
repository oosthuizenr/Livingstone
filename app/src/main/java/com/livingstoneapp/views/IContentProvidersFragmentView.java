package com.livingstoneapp.views;

import java.util.ArrayList;

import com.livingstoneapp.models.ContentProviderInfo;

/**
 * Created by Renier on 2016/02/16.
 */
public interface IContentProvidersFragmentView {
    void showError(String message);
    void setModel(ArrayList<ContentProviderInfo> contentProviders);
    void showWaitDialog();
    void hideWaitDialog();
    void showDetailContainer();
    void hideDetailContainer();
    void startContentProviderDetailActivity(ContentProviderInfo provider);
    void showNoValuesText();
    void hideNoValuesText();
}
