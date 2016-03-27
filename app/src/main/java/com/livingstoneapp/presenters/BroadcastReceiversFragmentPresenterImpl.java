package com.livingstoneapp.presenters;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.livingstoneapp.interactors.IBroadcastReceiverInteractor;
import com.livingstoneapp.models.BroadcastReceiverInfo;
import com.livingstoneapp.views.IBroadcastReceiversFragmentView;

/**
 * Created by Renier on 2016/02/16.
 */
public class BroadcastReceiversFragmentPresenterImpl implements IBroadcastReceiversFragmentPresenter {

    private IBroadcastReceiverInteractor mInteractor;
    private IBroadcastReceiversFragmentView mView;

    public BroadcastReceiversFragmentPresenterImpl(IBroadcastReceiverInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void setView(IBroadcastReceiversFragmentView view) {
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

        mInteractor.getBroadcastReceivers(packageName)
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

    @Override
    public void onBroadcastReceiverSelected(BroadcastReceiverInfo receiver) {
        mView.startBroadcastReceiverDetailActivity(receiver);
    }
}
