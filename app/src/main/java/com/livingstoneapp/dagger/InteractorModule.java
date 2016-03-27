package com.livingstoneapp.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.livingstoneapp.interactors.ActivityInteractorImpl;
import com.livingstoneapp.interactors.BroadcastReceiverInteractorImpl;
import com.livingstoneapp.interactors.ContentProviderInteractorImpl;
import com.livingstoneapp.interactors.IActivityInteractor;
import com.livingstoneapp.interactors.IBroadcastReceiverInteractor;
import com.livingstoneapp.interactors.IContentProviderInteractor;
import com.livingstoneapp.interactors.IPackageInteractor;
import com.livingstoneapp.interactors.IPermissionsInteractor;
import com.livingstoneapp.interactors.IServiceInteractor;
import com.livingstoneapp.interactors.PackageInteractorImpl;
import com.livingstoneapp.interactors.PermissionsInteractorImpl;
import com.livingstoneapp.interactors.ServiceInteractorImpl;

/**
 * Created by renier on 2/3/2016.
 */
@Module
public class InteractorModule {

    @Provides
    IPackageInteractor providePackageInteractor(Context context) {
        return new PackageInteractorImpl(context);
    }

    @Provides
    IActivityInteractor provideActivityInteractor(Context context) {
        return new ActivityInteractorImpl(context);
    }

    @Provides
    IPermissionsInteractor providePermissionsInteractor(Context context) {
        return new PermissionsInteractorImpl(context);
    }

    @Provides
    IServiceInteractor provideServiceInteractor(Context context) {
        return new ServiceInteractorImpl(context);
    }

    @Provides
    IContentProviderInteractor provideContentProvidersInteractor(Context context) {
        return new ContentProviderInteractorImpl(context);
    }

    @Provides
    IBroadcastReceiverInteractor proviceBroadcastReceiversInteractor(Context context) {
        return new BroadcastReceiverInteractorImpl(context);
    }
}
