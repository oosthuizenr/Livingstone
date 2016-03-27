package com.livingstoneapp.presenters;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.livingstoneapp.interactors.IActivityInteractor;
import com.livingstoneapp.models.ActivityInfoInternal;
import com.livingstoneapp.views.IActivitiesFragmentView;

/**
 * Created by Renier on 2016/02/16.
 */
public class ActivitiesFragmentPresenterImpl implements IActivitiesFragmentPresenter {

    private IActivityInteractor mInteractor;
    private IActivitiesFragmentView mView;

    public ActivitiesFragmentPresenterImpl(IActivityInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void setView(IActivitiesFragmentView view) {
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

        mInteractor.getActivities(packageName)
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
    public void onActivitySelected(ActivityInfoInternal activity) {
        mView.startActivityDetailActivity(activity);
    }
}
