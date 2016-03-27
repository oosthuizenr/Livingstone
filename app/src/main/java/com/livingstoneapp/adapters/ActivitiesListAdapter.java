package com.livingstoneapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.R;
import com.livingstoneapp.helpers.RecyclerViewOnClickListener;
import com.livingstoneapp.helpers.RecyclerViewOnClickListenerInternal;
import com.livingstoneapp.models.ActivityInfoInternal;

/**
 * Created by renier on 2/17/2016.
 */
public class ActivitiesListAdapter extends RecyclerView.Adapter<ActivitiesListAdapter.ViewHolder> {

    private RecyclerViewOnClickListener<ActivityInfoInternal> mListener;


    protected List<ActivityInfoInternal> mData;

    public ActivitiesListAdapter(List<ActivityInfoInternal> data) {
        mData = data;
    }

    public void setOnClickListener(RecyclerViewOnClickListener<ActivityInfoInternal> listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.app_info_activities_adapter_layout, parent, false),
                        position -> {
                            if (mListener != null) {
                                mListener.onItemClick(mData.get(position));
                            }
                        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getName());

        if (mData.get(position).isMain() || mData.get(position).isLauncher()) {
            holder.llContainer.setVisibility(View.VISIBLE);
        } else {
            holder.llContainer.setVisibility(View.GONE);
        }
        holder.tvMain.setVisibility(mData.get(position).isMain() ? View.VISIBLE : View.GONE);
        holder.tvLauncher.setVisibility(mData.get(position).isLauncher() ? View.VISIBLE : View.GONE);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerViewOnClickListenerInternal mListener;

        @Bind(R.id.tvName)
        public TextView tvName;
        @Bind(R.id.tvMain)
        public TextView tvMain;
        @Bind(R.id.tvLauncher)
        public TextView tvLauncher;
        @Bind(R.id.llContainer)
        public LinearLayout llContainer;

        public ViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v);

            ButterKnife.bind(this, v);

            mListener = listener;

            v.setOnClickListener(v1 -> {
                if (mListener != null) {
                    mListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }
}
