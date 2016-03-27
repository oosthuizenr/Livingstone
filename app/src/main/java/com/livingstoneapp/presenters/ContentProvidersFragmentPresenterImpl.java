package com.livingstoneapp.presenters;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import com.livingstoneapp.interactors.IContentProviderInteractor;
import com.livingstoneapp.models.ContentProviderInfo;
import com.livingstoneapp.views.IContentProvidersFragmentView;

/**
 * Created by Renier on 2016/02/16.
 */
public class ContentProvidersFragmentPresenterImpl implements IContentProvidersFragmentPresenter {

    private IContentProviderInteractor mInteractor;
    private IContentProvidersFragmentView mView;

    public ContentProvidersFragmentPresenterImpl(IContentProviderInteractor interactor) {
        mInteractor = interactor;
    }

    @Override
    public void setView(IContentProvidersFragmentView view) {
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

        mInteractor.getContentProviders(packageName)
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
    public void onContentProviderSelected(ContentProviderInfo provider) {
        mView.startContentProviderDetailActivity(provider);
    }
}
