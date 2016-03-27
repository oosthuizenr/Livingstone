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
import com.livingstoneapp.adapters.RequestedFeaturesAdapter;
import com.livingstoneapp.models.FeatureInfoInternal;
import com.livingstoneapp.presenters.IRequestedFeaturesFragmentPresenter;
import com.livingstoneapp.views.IRequestedFeaturesFragmentView;

/**
 * Created by renier on 2/18/2016.
 */
public class RequestedFeaturesFragment extends Fragment implements IRequestedFeaturesFragmentView {
    private static final String ARG_PACKAGE_NAME = "package_name_param";

    @Inject
    IRequestedFeaturesFragmentPresenter mPresenter;

    @Bind(R.id.item_list)
    RecyclerView recyclerView;

    @Bind(R.id.progress_bar)
    ProgressBar mProgressBar;

    @Bind(R.id.tvNoValues)
    TextView tvNoValues;

    private RequestedFeaturesAdapter mAdapter;

    public RequestedFeaturesFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param packageName The model that this fragment needs to display.
     * @return A new instance of fragment ApplicationInfoFragment.
     */
    public static RequestedFeaturesFragment newInstance(String packageName) {
        RequestedFeaturesFragment fragment = new RequestedFeaturesFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PACKAGE_NAME, packageName);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.fragment_requested_features, container, false);

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
    public void setModel(ArrayList<FeatureInfoInternal> features) {

        recyclerView.setHasFixedSize(true);

        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL_LIST));

        mAdapter = new RequestedFeaturesAdapter(features);
        recyclerView.setAdapter(mAdapter);
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
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideDetailContainer() {
        recyclerView.setVisibility(View.GONE);
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
