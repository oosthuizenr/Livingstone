package com.livingstoneapp.dagger;

import org.mockito.Mockito;

import dagger.Module;
import dagger.Provides;
import com.livingstoneapp.interactors.IPackageInteractor;
import com.livingstoneapp.presenters.IPackageListActivityPresenter;
import com.livingstoneapp.presenters.PackageListActivityPresenterImpl;

/**
 * Created by renier on 2/2/2016.
 */
@Module
public class PackageListActivityModule {
    private boolean mProvideMocks;

    public PackageListActivityModule(boolean provideMocks) {
        this.mProvideMocks = provideMocks;
    }

    @Provides
    IPackageListActivityPresenter providesMainActivityPresenter(IPackageInteractor interactor) {
        if (mProvideMocks) {
            return Mockito.mock(PackageListActivityPresenterImpl.class);
        } else {
            return new PackageListActivityPresenterImpl(interactor);
        }
    }
}
