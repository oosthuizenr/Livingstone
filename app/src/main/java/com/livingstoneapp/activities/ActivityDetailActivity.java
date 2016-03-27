package com.livingstoneapp.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.MainApplication;
import com.livingstoneapp.R;
import com.livingstoneapp.adapters.ActivityDetailAdapter;
import com.livingstoneapp.models.ActivityInfoInternal;
import com.livingstoneapp.presenters.IActivityDetailActivityPresenter;
import com.livingstoneapp.views.IActivityDetailActivityView;

public class ActivityDetailActivity extends AppCompatActivity implements IActivityDetailActivityView {
    private static final String ARG_ACTIVITY = "ActivityArg";
    public static Intent getLaunchIntent(Context context, ActivityInfoInternal activity) {
        return new Intent(context, ActivityDetailActivity.class).putExtra(ARG_ACTIVITY, activity);
    }

    @Inject
    IActivityDetailActivityPresenter mPresenter;

    @Bind(R.id.item_list)
    RecyclerView recyclerView;

    private ActivityDetailAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_activity_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_activity_package_list));
        setSupportActionBar(toolbar);
        if(getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

        MainApplication.from(this).getGraph().inject(this);

        ButterKnife.bind(this);

        mPresenter.setView(this);

        mPresenter.init(getIntent().getParcelableExtra(ARG_ACTIVITY));
    }

    @Override
    protected void onPause() {
        mPresenter.clearView();
        super.onPause();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPresenter.setView(this);
    }

    @Override
    public void setTitle(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void setModel(Object[] items, int[] dataSetTypes) {
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        mAdapter = new ActivityDetailAdapter(items, dataSetTypes);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
