package com.livingstoneapp.fragments;

import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.util.ArrayList;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.DividerItemDecoration;
import com.livingstoneapp.MainApplication;
import com.livingstoneapp.R;
import com.livingstoneapp.activities.ActivityDetailActivity;
import com.livingstoneapp.adapters.ActivitiesListAdapter;
import com.livingstoneapp.helpers.RecyclerViewOnClickListener;
import com.livingstoneapp.models.ActivityInfoInternal;
import com.livingstoneapp.presenters.IActivitiesFragmentPresenter;
import com.livingstoneapp.views.IActivitiesFragmentView;


public class ActivitiesFragment extends Fragment implements IActivitiesFragmentView {

    private static final String ARG_PACKAGE_NAME = "package_name_param";

    @Inject
    IActivitiesFragmentPresenter mPresenter;

    @Bind(R.id.item_list)
    RecyclerView recyclerView;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Bind(R.id.tvNoValues)
    TextView tvNoValues;

    private ActivitiesListAdapter mAdapter;

    public ActivitiesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param packageName The app package name.
     * @return A new instance of fragment ActivitiesFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ActivitiesFragment newInstance(String packageName) {
        ActivitiesFragment fragment = new ActivitiesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PACKAGE_NAME, packageName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_activities, container, false);

        MainApplication.from(getActivity()).getGraph().inject(this);

        ButterKnife.bind(this, rootView);

        if (getArguments() != null) {
            mPresenter.setView(this);
            mPresenter.init(getArguments().getString(ARG_PACKAGE_NAME));
        }

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
    public void showError(String message) {
        Snackbar.make(recyclerView, message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void setModel(ArrayList<ActivityInfoInternal> activities) {

        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mAdapter = new ActivitiesListAdapter(activities);
        recyclerView.setAdapter(mAdapter);

        mAdapter.setOnClickListener(onItemClickListener);
    }

    private RecyclerViewOnClickListener<ActivityInfoInternal> onItemClickListener = new RecyclerViewOnClickListener<ActivityInfoInternal>() {
        @Override
        public void onItemClick(ActivityInfoInternal item) {
            mPresenter.onActivitySelected(item);
        }
    };


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
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDetailContainer() {
        recyclerView.setVisibility(View.GONE);
    }

    @Override
    public void startActivityDetailActivity(ActivityInfoInternal activity) {
        getContext().startActivity(ActivityDetailActivity.getLaunchIntent(getContext(), activity));
    }

    @Override
    public void showNoValuesText() {
        tvNoValues.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideNoValuesText() {
        tvNoValues.setVisibility(View.GONE);
    }
}
