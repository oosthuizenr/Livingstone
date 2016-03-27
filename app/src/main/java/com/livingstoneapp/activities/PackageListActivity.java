package com.livingstoneapp.activities;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.DividerItemDecoration;
import com.livingstoneapp.MainApplication;
import com.livingstoneapp.R;
import com.livingstoneapp.adapters.PackageListAdapter;
import com.livingstoneapp.fragments.PackageDetailFragment;
import com.livingstoneapp.helpers.RecyclerViewOnClickListener;
import com.livingstoneapp.helpers.SettingsHelper;
import com.livingstoneapp.models.InstalledPackageHeader;
import com.livingstoneapp.presenters.IPackageListActivityPresenter;
import com.livingstoneapp.views.IPackageListView;

public class PackageListActivity extends AppCompatActivity implements IPackageListView {

    @Inject
    IPackageListActivityPresenter mPresenter;

    @Bind(R.id.item_list)
    RecyclerView rvPackages;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    private PackageListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_package_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.title_activity_package_list));
        setSupportActionBar(toolbar);

        MainApplication.from(this).getGraph().inject(this);

        ButterKnife.bind(this);

        mPresenter.setView(this);

        if (findViewById(R.id.item_detail_container) != null) {
            mPresenter.setTwoPane();
        }

        mPresenter.init();
    }

    @Override
    public void init() {
        rvPackages.setHasFixedSize(true);

        rvPackages.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL_LIST));

        mAdapter = new PackageListAdapter();
        rvPackages.setAdapter(mAdapter);

        mAdapter.setOnClickListener(onItemClickListener);
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
        mPresenter.loadPackages(false);
    }

    @Override
    public void setInstalledPackages(List<InstalledPackageHeader> items) {
        mAdapter.setData(items);
    }

    @Override
    public void setError(String message) {
        Snackbar.make(rvPackages, message, Snackbar.LENGTH_LONG).show();
    }

    private RecyclerViewOnClickListener<InstalledPackageHeader> onItemClickListener = item -> mPresenter.packageSelected(item);

    @Override
    public void startDetailActivity(InstalledPackageHeader item) {
        Intent intent = new Intent(getApplicationContext(), PackageDetailActivity.class);
        intent.putExtra(PackageDetailActivity.ARG_PACKAGE_NAME, item.getPackageName());
        PackageListActivity.this.startActivity(intent);
    }

    @Override
    public void setDetailFragment(InstalledPackageHeader item) {
        Bundle arguments = new Bundle();
        arguments.putString(PackageDetailFragment.ARG_PACKAGE_NAME, item.getPackageName());
        arguments.putBoolean(PackageDetailFragment.ARG_2_PANE, true);
        PackageDetailFragment fragment = new PackageDetailFragment();
        fragment.setArguments(arguments);
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.item_detail_container, fragment)
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_package_list, menu);

        menu.getItem(0).setChecked(SettingsHelper.shouldIncludeSystemApps(this));
        menu.getItem(1).setChecked(SettingsHelper.shouldIncludeUninstalledApps(this));

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_include_uninstalled_aps) {
            item.setChecked(item.isChecked() == true ? false : true);

            SettingsHelper.setIncludeUninstalledApps(this, item.isChecked());

            mPresenter.loadPackages(true);

            return true;
        } else if (id == R.id.action_include_system_apps) {
            item.setChecked(item.isChecked() == true ? false : true);

            SettingsHelper.setIncludeSystemAppsApps(this, item.isChecked());

            mPresenter.loadPackages(true);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showWaitDialog() {
        mProgressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideWaitDialog() {
        mProgressBar.setVisibility(View.GONE);
    }

    @Override
    public void showContentView() {
        rvPackages.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideContentView() {
        rvPackages.setVisibility(View.GONE);
    }
}
