package com.livingstoneapp.dagger;

import android.content.Context;

import dagger.Module;
import dagger.Provides;
import com.livingstoneapp.MainApplication;

/**
 * Created by renier on 1/12/2016.
 */
@Module
public class ApplicationModule {
    private static MainApplication sApplication;

    public ApplicationModule(MainApplication application) {
        sApplication = application;
    }

    @Provides
    MainApplication providesApplication() {
        return sApplication;
    }

    @Provides
    Context provideApplicationContext() {
        return sApplication.getApplicationContext();
    }
}
