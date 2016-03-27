package com.livingstoneapp;

import android.app.Application;
import android.content.Context;
import android.support.annotation.NonNull;

import com.livingstoneapp.dagger.Graph;

/**
 * Created by renier on 2/2/2016.
 */
public class MainApplication extends Application {
    private Graph mGraph;

    @Override
    public void onCreate() {
        super.onCreate();

        mGraph = Graph.Initializer.init(this, false);
    }

    public Graph getGraph() {
        return mGraph;
    }

    public void setMockMode(boolean provideMocks) {
        mGraph = Graph.Initializer.init(this, provideMocks);
    }

    public static MainApplication from(@NonNull Context context) {
        return (MainApplication) context.getApplicationContext();
    }
}
