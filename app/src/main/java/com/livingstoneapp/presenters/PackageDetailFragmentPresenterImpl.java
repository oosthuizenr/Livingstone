package com.livingstoneapp.presenters;

import javax.inject.Inject;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.livingstoneapp.interactors.IPackageInteractor;
import com.livingstoneapp.views.IPackageDetailFragmentView;

/**
 * Created by renier on 2/3/2016.
 */
public class PackageDetailFragmentPresenterImpl implements IPackageDetailFragmentPresenter {

    private IPackageInteractor mPackageInteractor;
    private IPackageDetailFragmentView mView;


    @Inject
    public PackageDetailFragmentPresenterImpl(IPackageInteractor interactor) {
        mPackageInteractor = interactor;
    }

    @Override
    public void setView(IPackageDetailFragmentView view) {
        mView = view;
    }

    @Override
    public void clearView() {
        mView = null;
    }

    @Override
    public void init(String packageName) {
        if (mView != null) {
            mView.hideDetailContainer();
            mView.showWaitDialog();

            mPackageInteractor.getAppNameFromPackage(packageName)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(appName -> {
                        if (mView != null) {
                            mView.initViews(appName, packageName);
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
}
