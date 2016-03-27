package com.livingstoneapp.dagger;

import dagger.Component;
import com.livingstoneapp.MainApplication;
import com.livingstoneapp.activities.ActivityDetailActivity;
import com.livingstoneapp.activities.ContentProviderDetailActivity;
import com.livingstoneapp.activities.PackageListActivity;
import com.livingstoneapp.fragments.ActivitiesFragment;
import com.livingstoneapp.fragments.ApplicationInfoFragment;
import com.livingstoneapp.fragments.BroadcastReceiversFragment;
import com.livingstoneapp.fragments.ContentProvidersFragment;
import com.livingstoneapp.fragments.DeclaredPermissionsFragment;
import com.livingstoneapp.fragments.PackageDetailFragment;
import com.livingstoneapp.fragments.RequestedFeaturesFragment;
import com.livingstoneapp.fragments.RequestedPermissionsFragment;
import com.livingstoneapp.fragments.ServicesFragment;

/**
 * Created by renier on 1/12/2016.
 */
@Component(modules = { ApplicationModule.class, InteractorModule.class, PackageListActivityModule.class, PackageDetailActivityModule.class })
public interface Graph {
    void inject(PackageListActivity activity);
    void inject(PackageDetailFragment fragment);
    void inject(ApplicationInfoFragment fragment);
    void inject(ActivitiesFragment fragment);
    void inject(ActivityDetailActivity activity);
    void inject(DeclaredPermissionsFragment fragment);
    void inject(RequestedPermissionsFragment fragment);
    void inject(RequestedFeaturesFragment fragment);
    void inject(ServicesFragment fragment);
    void inject(ContentProvidersFragment fragment);
    void inject(ContentProviderDetailActivity activity);
    void inject(BroadcastReceiversFragment fragment);

    final class Initializer {
        public static Graph init(MainApplication application, boolean provideMocks) {
            return DaggerGraph
                    .builder()
                    .applicationModule(new ApplicationModule(application))
                    .interactorModule(new InteractorModule())
                    .packageListActivityModule(new PackageListActivityModule(provideMocks))
                    .packageDetailActivityModule(new PackageDetailActivityModule(provideMocks))
                    .build();
        }
    }
}
