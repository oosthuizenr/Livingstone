package com.livingstoneapp.presenters;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.livingstoneapp.interactors.IPermissionsInteractor;
import com.livingstoneapp.views.IDeclaredPermissionsFragmentView;

/**
 * Created by renier on 2/18/2016.
 */
public class DeclaredPermissionsFragmentPresenterImpl implements IDeclaredPermissionsFragmentPresenter {
    private IPermissionsInteractor mInteractor;
    private IDeclaredPermissionsFragmentView mView;

    public DeclaredPermissionsFragmentPresenterImpl(IPermissionsInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void setView(IDeclaredPermissionsFragmentView view) {
        mView = view;
    }

    @Override
    public void clearView() {
        mView = null;
    }

    @Override
    public void init(String packageName) {
        mView.hideDetailContainer();
        mView.hideNoValuesText();
        mView.showWaitDialog();

        mInteractor.getDeclaredPermissions(packageName)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(result -> {
                    if (mView != null) {
                        if (result.size() > 0) {
                            mView.setModel(result);
                            mView.showDetailContainer();
                        } else {
                            mView.showNoValuesText();
                            mView.hideWaitDialog();
                        }

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
