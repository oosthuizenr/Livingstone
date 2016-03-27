package com.livingstoneapp.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.MainApplication;
import com.livingstoneapp.R;
import com.livingstoneapp.adapters.PackageDetailViewPagerAdapter;
import com.livingstoneapp.presenters.IPackageDetailFragmentPresenter;
import com.livingstoneapp.views.IPackageDetailFragmentView;

/**
 * Created by renier on 2/2/2016.
 */
public class PackageDetailFragment extends Fragment implements IPackageDetailFragmentView {

    public static String ARG_PACKAGE_NAME = "PackageName";
    public static String ARG_2_PANE = "2Pane";

    @Inject
    IPackageDetailFragmentPresenter mPresenter;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Bind(R.id.tabs)
    TabLayout mTabLayout;

    @Bind(R.id.viewpager)
    ViewPager mViewPager;

    private PackageDetailViewPagerAdapter mViewPagerAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_package_detail, container, false);



        String packageName = getArguments().getString(ARG_PACKAGE_NAME);

        MainApplication.from(getActivity()).getGraph().inject(this);

        ButterKnife.bind(this, rootView);

        mPresenter.setView(this);

        mPresenter.init(packageName);

        return rootView;
    }

    @Override
    public void onPause() {
        mPresenter.clearView();
        super.onPause();
    }

    @Override
    public void onResume() {
        super.onResume();
        mPresenter.setView(this);
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
    public void showDetailContainer() {
        mTabLayout.setVisibility(View.VISIBLE);
        mViewPager.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDetailContainer() {
        mTabLayout.setVisibility(View.GONE);
        mViewPager.setVisibility(View.GONE);
    }

    @Override
    public void setTitle(String title) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(title);
    }

    @Override
    public void initViews(String appName, String packageName) {
        ((AppCompatActivity) getActivity()).getSupportActionBar().setTitle(appName);

        mViewPagerAdapter = new PackageDetailViewPagerAdapter(this.getChildFragmentManager());
        mViewPagerAdapter.addFragment(ApplicationInfoFragment.newInstance(packageName), getString(R.string.tab_header_application_info));
        mViewPagerAdapter.addFragment(ActivitiesFragment.newInstance(packageName), getString(R.string.tab_header_activities));
        mViewPagerAdapter.addFragment(ServicesFragment.newInstance(packageName), getString(R.string.tab_header_services));
        mViewPagerAdapter.addFragment(ContentProvidersFragment.newInstance(packageName), getString(R.string.tab_header_content_providers));
        mViewPagerAdapter.addFragment(BroadcastReceiversFragment.newInstance(packageName), getString(R.string.tab_header_broadcast_receivers));
        mViewPagerAdapter.addFragment(RequestedPermissionsFragment.newInstance(packageName), getString(R.string.tab_header_requested_permissions));
        mViewPagerAdapter.addFragment(DeclaredPermissionsFragment.newInstance(packageName), getString(R.string.tab_header_declared_permissions));
        mViewPagerAdapter.addFragment(RequestedFeaturesFragment.newInstance(packageName), getString(R.string.tab_header_requested_features));
        mViewPager.setAdapter(mViewPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void showError(String message) {

    }
}
