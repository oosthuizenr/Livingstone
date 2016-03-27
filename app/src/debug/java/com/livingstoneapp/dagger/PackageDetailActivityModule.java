package com.livingstoneapp.dagger;

import org.mockito.Mockito;

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
import com.livingstoneapp.presenters.IPackageDetailFragmentPresenter;
import com.livingstoneapp.presenters.IDeclaredPermissionsFragmentPresenter;
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
    private boolean mProvideMocks;

    public PackageDetailActivityModule(boolean provideMocks) {
        this.mProvideMocks = provideMocks;
    }

    @Provides
    IPackageDetailFragmentPresenter providesPackageDetailFragmentPresenter(IPackageInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(IPackageDetailFragmentPresenter.class);
        } else {
            return new PackageDetailFragmentPresenterImpl(interactor);
        }
    }

    @Provides
    IApplicationInfoFragmentPresenter providesApplicationInfoFragmentPresenter(IPackageInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(IApplicationInfoFragmentPresenter.class);
        } else {
            return new ApplicationInfoFragmentPresenterImpl(interactor);
        }
    }

    @Provides
    IActivitiesFragmentPresenter providesActivitiesFragmentPresenter(IActivityInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(IActivitiesFragmentPresenter.class);
        } else {
            return new ActivitiesFragmentPresenterImpl(interactor);
        }
    }

    @Provides
    IActivityDetailActivityPresenter providesActivityDetailActivityPresenter() {
        if (mProvideMocks) {
            return Mockito.mock(IActivityDetailActivityPresenter.class);
        } else {
            return new ActivityDetailActivityPresenterImpl();
        }
    }

    @Provides
    IDeclaredPermissionsFragmentPresenter providesDeclaredPermissionsFragmentPresenter(IPermissionsInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(IDeclaredPermissionsFragmentPresenter.class);
        } else {
            return new DeclaredPermissionsFragmentPresenterImpl(interactor);
        }
    }

    @Provides
    IRequestedPermissionsFragmentPresenter providesRequestedPermissionsFragmentPresenter(IPermissionsInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(IRequestedPermissionsFragmentPresenter.class);
        } else {
            return new RequestedPermissionsFragmentPresenterImpl(interactor);
        }
    }

    @Provides
    IRequestedFeaturesFragmentPresenter providesRequestedFeaturesFragmentPresenter(IPackageInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(IRequestedFeaturesFragmentPresenter.class);
        } else {
            return new RequestedFeaturesFragmentPresenterImpl(interactor);
        }
    }

    @Provides
    IServicesFragmentPresenter providesServicesFragmentPresenter(IServiceInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(IServicesFragmentPresenter.class);
        } else {
            return new ServicesFragmentPresenterImpl(interactor);
        }
    }

    @Provides
    IContentProvidersFragmentPresenter providesContentProvidersFragmentPresenter(IContentProviderInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(IContentProvidersFragmentPresenter.class);
        } else {
            return new ContentProvidersFragmentPresenterImpl(interactor);
        }
    }

    @Provides
    IContentProviderDetailActivityPresenter providesContentProviderDetailActivityPresenter() {
        if (mProvideMocks) {
            return Mockito.mock(IContentProviderDetailActivityPresenter.class);
        } else {
            return new ContentProviderDetailActivityPresenterImpl();
        }
    }

    @Provides
    IBroadcastReceiversFragmentPresenter providesBroadcastReceiversFragmentPresenter(IBroadcastReceiverInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(IBroadcastReceiversFragmentPresenter.class);
        } else {
            return new BroadcastReceiversFragmentPresenterImpl(interactor);
        }
    }
}
