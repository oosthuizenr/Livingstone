package com.livingstoneapp.presenters;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.livingstoneapp.interactors.IPermissionsInteractor;
import com.livingstoneapp.views.IRequestedPermissionsFragmentView;

/**
 * Created by renier on 2/18/2016.
 */
public class RequestedPermissionsFragmentPresenterImpl implements IRequestedPermissionsFragmentPresenter {
    private IPermissionsInteractor mInteractor;
    private IRequestedPermissionsFragmentView mView;

    public RequestedPermissionsFragmentPresenterImpl(IPermissionsInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void setView(IRequestedPermissionsFragmentView view) {
        mView = view;
    }

    @Override
    public void clearView() {
        mView = null;
    }

    @Override
    public void init(String packageName) {
        mView.hideNoValuesText();
        mView.hideDetailContainer();
        mView.showWaitDialog();

        mInteractor.getRequestedPermissions(packageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (mView != null) {
                        if (result.size() > 0) {
                            mView.setModel(result);
                            mView.showDetailContainer();
                            mView.hideWaitDialog();
                        } else {
                            mView.showNoValuesText();
                            mView.hideWaitDialog();
                        }
                    }
                }, error -> {
                    if (mView != null) {
                        mView.hideWaitDialog();
                        mView.showError(error.getMessage());
                    }
                });
    }
}
