package com.livingstoneapp.presenters;


import com.livingstoneapp.adapters.ActivityDetailAdapter;
import com.livingstoneapp.models.ActivityInfoInternal;
import com.livingstoneapp.views.IActivityDetailActivityView;

/**
 * Created by renier on 2/18/2016.
 */
public class ActivityDetailActivityPresenterImpl implements IActivityDetailActivityPresenter {
    private IActivityDetailActivityView mView;

    @Override
    public void setView(IActivityDetailActivityView view) {
        mView = view;
    }

    @Override
    public void clearView() {
        mView = null;
    }

    @Override
    public void init(ActivityInfoInternal ai) {
        if (mView != null) {
            mView.setTitle(ai.getName());

            int arraySize = 1;
            int currentIndex = 1;

            if (ai.getConfigChanges().size() > 0)
                arraySize++;

            if (ai.getCategories().size() > 0)
                arraySize++;

            if (ai.getFlags().size() > 0)
                arraySize++;

            if (ai.getSoftInputMode().size() > 0)
                arraySize++;

            Object[] items = new Object[arraySize];
            int[] types = new int[arraySize];

            items[0] = ai;
            types[0] = ActivityDetailAdapter.GENERAL;

            if (ai.getConfigChanges().size() > 0) {
                items[currentIndex] = ai.getConfigChanges();
                types[currentIndex] = ActivityDetailAdapter.CONFIG_CHANGES;
                currentIndex++;
            }

            if (ai.getCategories().size() > 0) {
                items[currentIndex] = ai.getCategories();
                types[currentIndex] = ActivityDetailAdapter.CATEGORIES;
                currentIndex++;
            }

            if (ai.getFlags().size() > 0) {
                items[currentIndex] = ai.getFlags();
                types[currentIndex] = ActivityDetailAdapter.FLAGS;
                currentIndex++;
            }

            if (ai.getSoftInputMode().size() > 0) {
                items[currentIndex] = ai.getSoftInputMode();
                types[currentIndex] = ActivityDetailAdapter.SOFT_INPUT_MODE;
                currentIndex++;
            }


            /*Object[] items = new Object[4];
            items[0] = ai;
            items[1] = ai.getConfigChanges();
            items[2] = ai.getCategories();
            items[3] = ai.getSoftInputMode();*/

            /*int[] types = new int[4];
            types[0] = ActivityDetailAdapter.GENERAL;
            types[1] = ActivityDetailAdapter.CONFIG_CHANGES;
            types[2] = ActivityDetailAdapter.CATEGORIES;
            types[3] = ActivityDetailAdapter.SOFT_INPUT_MODE;*/

            mView.setModel(items, types);
        }
    }
}
