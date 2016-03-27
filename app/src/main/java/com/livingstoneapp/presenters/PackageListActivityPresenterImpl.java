package com.livingstoneapp.presenters;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.livingstoneapp.interactors.IPackageInteractor;
import com.livingstoneapp.models.InstalledPackageHeader;
import com.livingstoneapp.views.IPackageListView;

/**
 * Created by renier on 2/2/2016.
 */
public class PackageListActivityPresenterImpl implements IPackageListActivityPresenter {

    private IPackageListView mView;
    private boolean mIsTwoPane = false;
    private IPackageInteractor mPackageInteractor;


    @Inject
    public PackageListActivityPresenterImpl(IPackageInteractor interactor) {
        mPackageInteractor = interactor;
    }

    @Override
    public void setView(IPackageListView view) {
        mView = view;
    }

    @Override
    public void clearView() {
        mView = null;
    }

    @Override
    public void setTwoPane() {
        mIsTwoPane = true;
    }

    @Override
    public void init() {
        mView.init();
    }

    @Override
    public void loadPackages(boolean refresh) {
        if (mView != null) {
            mView.hideContentView();
            mView.showWaitDialog();

            mPackageInteractor.getInstalledPackages(refresh)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(
                            items -> {
                                if (mView != null) {
                                    if (items.size() > 0) {
                                        mView.setInstalledPackages(items);
                                        mView.hideWaitDialog();
                                        mView.showContentView();
                                    } else {
                                        mView.hideWaitDialog();
                                        mView.setError("No Packages");
                                    }
                                }
                            },
                            error -> {
                                if (mView != null) {
                                    mView.setError(error.getMessage());
                                    mView.hideWaitDialog();
                                }
                            });
        }
    }

    @Override
    public void packageSelected(InstalledPackageHeader header) {
        if (mView != null) {
            if (mIsTwoPane) {
                mView.setDetailFragment(header);
            } else {
                mView.startDetailActivity(header);
            }
        }
    }


}
