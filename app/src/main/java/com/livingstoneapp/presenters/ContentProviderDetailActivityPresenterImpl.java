package com.livingstoneapp.presenters;


import com.livingstoneapp.adapters.ContentProviderDetailAdapter;
import com.livingstoneapp.models.ContentProviderInfo;
import com.livingstoneapp.views.IContentProviderDetailActivityView;

/**
 * Created by renier on 2/18/2016.
 */
public class ContentProviderDetailActivityPresenterImpl implements IContentProviderDetailActivityPresenter {
    private IContentProviderDetailActivityView mView;

    @Override
    public void setView(IContentProviderDetailActivityView view) {
        mView = view;
    }

    @Override
    public void clearView() {
        mView = null;
    }

    @Override
    public void init(ContentProviderInfo cpi) {
        if (mView != null) {

            mView.setTitle(cpi.getName());

            Object[] items = new Object[3];
            items[0] = cpi;
            items[1] = cpi.getPathPermissions();
            items[2] = cpi.getURIPermissionPatterns();

            int[] types = new int[3];
            types[0] = ContentProviderDetailAdapter.GENERAL;
            types[1] = ContentProviderDetailAdapter.PATH_PERMISSIONS;
            types[2] = ContentProviderDetailAdapter.URI_PERMISSION_PATTERNS;

            mView.setModel(items, types);
        }
    }
}
