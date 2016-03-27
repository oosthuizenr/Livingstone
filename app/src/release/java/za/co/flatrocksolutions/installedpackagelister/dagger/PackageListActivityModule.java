package com.livingstoneapp.dagger;

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
    public PackageListActivityModule(boolean provideMocks) {

    }

    @Provides
    IPackageListActivityPresenter providesMainActivityPresenter(IPackageInteractor interactor) {
        return new PackageListActivityPresenterImpl(interactor);
    }
}
