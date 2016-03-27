package com.livingstoneapp.dagger;


import dagger.Module;
import dagger.Provides;
import com.livingstoneapp.interactors.IActivityInteractor;
import com.livingstoneapp.interactors.IBroadcastReceiverInteractor;
import com.livingstoneapp.interactors.IContentProviderInteractor;
import com.livingstoneapp.interactors.IPackageInteractor;
import com.livingstoneapp.interactors.IPermissionsInteractor;
import com.livingstoneapp.interactors.IServiceInteractor;
import com.livingstoneapp.presenters.ActivitiesFragmentPresenterImpl;
import com.livingstoneapp.presenters.ActivityDetailActivityPresenterImpl;
import com.livingstoneapp.presenters.ApplicationInfoFragmentPresenterImpl;
import com.livingstoneapp.presenters.BroadcastReceiversFragmentPresenterImpl;
import com.livingstoneapp.presenters.ContentProviderDetailActivityPresenterImpl;
import com.livingstoneapp.presenters.ContentProvidersFragmentPresenterImpl;
import com.livingstoneapp.presenters.DeclaredPermissionsFragmentPresenterImpl;
import com.livingstoneapp.presenters.IActivitiesFragmentPresenter;
import com.livingstoneapp.presenters.IActivityDetailActivityPresenter;
import com.livingstoneapp.presenters.IApplicationInfoFragmentPresenter;
import com.livingstoneapp.presenters.IBroadcastReceiversFragmentPresenter;
import com.livingstoneapp.presenters.IContentProviderDetailActivityPresenter;
import com.livingstoneapp.presenters.IContentProvidersFragmentPresenter;
import com.livingstoneapp.presenters.IDeclaredPermissionsFragmentPresenter;
import com.livingstoneapp.presenters.IPackageDetailFragmentPresenter;
import com.livingstoneapp.presenters.IRequestedFeaturesFragmentPresenter;
import com.livingstoneapp.presenters.IRequestedPermissionsFragmentPresenter;
import com.livingstoneapp.presenters.IServicesFragmentPresenter;
import com.livingstoneapp.presenters.PackageDetailFragmentPresenterImpl;
import com.livingstoneapp.presenters.RequestedFeaturesFragmentPresenterImpl;
import com.livingstoneapp.presenters.RequestedPermissionsFragmentPresenterImpl;
import com.livingstoneapp.presenters.ServicesFragmentPresenterImpl;

/**
 * Created by renier on 2/2/2016.
 */
@Module
public class PackageDetailActivityModule {

    public PackageDetailActivityModule(boolean provideMocks) {

    }

    @Provides
    IPackageDetailFragmentPresenter providesPackageDetailFragmentPresenter(IPackageInteractor interactor) {
        return new PackageDetailFragmentPresenterImpl(interactor);
    }

    @Provides
    IApplicationInfoFragmentPresenter providesApplicationInfoFragmentPresenter(IPackageInteractor interactor) {
        return new ApplicationInfoFragmentPresenterImpl(interactor);
    }


    @Provides
    IActivitiesFragmentPresenter providesActivitiesFragmentPresenter(IActivityInteractor interactor) {
        return new ActivitiesFragmentPresenterImpl(interactor);
    }

    @Provides
    IActivityDetailActivityPresenter providesActivityDetailActivityPresenter() {
        return new ActivityDetailActivityPresenterImpl();
    }

    @Provides
    IDeclaredPermissionsFragmentPresenter providesDeclaredPermissionsFragmentPresenter(IPermissionsInteractor interactor) {
        return new DeclaredPermissionsFragmentPresenterImpl(interactor);
    }

    @Provides
    IRequestedPermissionsFragmentPresenter providesRequestedPermissionsFragmentPresenter(IPermissionsInteractor interactor) {
        return new RequestedPermissionsFragmentPresenterImpl(interactor);
    }

    @Provides
    IRequestedFeaturesFragmentPresenter providesRequestedFeaturesFragmentPresenter(IPackageInteractor interactor) {
        return new RequestedFeaturesFragmentPresenterImpl(interactor);
    }

    @Provides
    IServicesFragmentPresenter providesServicesFragmentPresenter(IServiceInteractor interactor) {
        return new ServicesFragmentPresenterImpl(interactor);
    }

    @Provides
    IContentProvidersFragmentPresenter providesContentProvidersFragmentPresenter(IContentProviderInteractor interactor) {
        return new ContentProvidersFragmentPresenterImpl(interactor);
    }

    @Provides
    IContentProviderDetailActivityPresenter providesContentProviderDetailActivityPresenter() {
        return new ContentProviderDetailActivityPresenterImpl();
    }

    @Provides
    IBroadcastReceiversFragmentPresenter providesBroadcastReceiversFragmentPresenter(IBroadcastReceiverInteractor interactor) {
        return new BroadcastReceiversFragmentPresenterImpl(interactor);
    }
}
