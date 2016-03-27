package com.livingstoneapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.R;
import com.livingstoneapp.helpers.RecyclerViewOnClickListener;
import com.livingstoneapp.helpers.RecyclerViewOnClickListenerInternal;
import com.livingstoneapp.models.FeatureInfoInternal;

/**
 * Created by renier on 2/17/2016.
 */
public class RequestedFeaturesAdapter extends RecyclerView.Adapter<RequestedFeaturesAdapter.ViewHolder> {

    private RecyclerViewOnClickListener<FeatureInfoInternal> mListener;


    protected List<FeatureInfoInternal> mData;

    public RequestedFeaturesAdapter(List<FeatureInfoInternal> data) {
        mData = data;
    }

    public void setOnClickListener(RecyclerViewOnClickListener<FeatureInfoInternal> listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.requested_features_adapter_layout, parent, false),
                        position -> {
                            if (mListener != null) {
                                mListener.onItemClick(mData.get(position));
                            }
                        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (mData.get(position).getName() != null) {
            holder.tvName.setVisibility(View.VISIBLE);
            holder.tvName.setText(mData.get(position).getName());
        } else {
            holder.tvName.setVisibility(View.GONE);
        }


        if (!mData.get(position).getGLESVersion().equalsIgnoreCase(holder.tvGLESVersion.getContext().getString(R.string.requested_features_empty_gles))) {
            holder.tvGLESVersion.setVisibility(View.VISIBLE);
            holder.tvGLESVersion.setText(holder.tvGLESVersion.getContext().getString(R.string.requested_features_gles_version_prefix) + " " + mData.get(position).getGLESVersion());
        } else {
            holder.tvGLESVersion.setVisibility(View.GONE);
        }

        if (mData.get(position).isRequired()) {
            holder.tvRequiredHeader.setVisibility(View.VISIBLE);
            holder.tvNotRequiredHeader.setVisibility(View.GONE);
        } else {
            holder.tvRequiredHeader.setVisibility(View.GONE);
            holder.tvNotRequiredHeader.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerViewOnClickListenerInternal mListener;

        @Bind(R.id.tvName)
        public TextView tvName;
        @Bind(R.id.tvGLESVersion)
        public TextView tvGLESVersion;
        @Bind(R.id.tvRequiredHeader)
        public TextView tvRequiredHeader;
        @Bind(R.id.tvNotRequiredHeader)
        public TextView tvNotRequiredHeader;

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
