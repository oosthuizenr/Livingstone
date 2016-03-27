package com.livingstoneapp.presenters;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.livingstoneapp.adapters.AppInfoAdapter;
import com.livingstoneapp.interactors.IPackageInteractor;
import com.livingstoneapp.views.IApplicationInfoFragmentView;

/**
 * Created by Renier on 2016/02/13.
 */
public class ApplicationInfoFragmentPresenterImpl implements IApplicationInfoFragmentPresenter {

    private IPackageInteractor mInteractor;
    private IApplicationInfoFragmentView mView;

    @Inject
    public ApplicationInfoFragmentPresenterImpl(IPackageInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void setView(IApplicationInfoFragmentView view) {
        mView = view;
    }

    @Override
    public void clearView() {
        mView = null;
    }

    @Override
    public void init(String packageName) {
        mView.hideDetailContainer();
        mView.showWaitDialog();

        mInteractor.getApplicationInfo(packageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(appInfo -> {
                    if (mView != null) {
                        Object[] items = new Object[4];
                        items[0] = appInfo.getGeneralInfo();
                        items[1] = appInfo.getDirectoryInfo();
                        items[2] = appInfo.getFlags();
                        items[3] = appInfo.getSignatures();

                        int[] types = new int[4];
                        types[0] = AppInfoAdapter.GENERAL;
                        types[1] = AppInfoAdapter.DIRECTORIES;
                        types[2] = AppInfoAdapter.FLAGS;
                        types[3] = AppInfoAdapter.SIGNATURES;

                        mView.setModel(items, types);
                        mView.showDetailContainer();
                        mView.hideWaitDialog();
                    }
                }, error -> {
                    if (mView != null) {
                        mView.hideWaitDialog();
                        mView.showError(error.getMessage());
                    }
                });
    }
}
