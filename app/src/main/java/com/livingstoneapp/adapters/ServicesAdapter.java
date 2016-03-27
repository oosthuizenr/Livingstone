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
import com.livingstoneapp.models.ServiceInfoInternal;

/**
 * Created by renier on 2/17/2016.
 */
public class ServicesAdapter extends RecyclerView.Adapter<ServicesAdapter.ViewHolder> {

    private RecyclerViewOnClickListener<ServiceInfoInternal> mListener;


    protected List<ServiceInfoInternal> mData;

    public ServicesAdapter(List<ServiceInfoInternal> data) {
        mData = data;
    }

    public void setOnClickListener(RecyclerViewOnClickListener<ServiceInfoInternal> listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.services_adapter_layout, parent, false),
                        position -> {
                            if (mListener != null) {
                                mListener.onItemClick(mData.get(position));
                            }
                        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        ServiceInfoInternal si = mData.get(position);

        holder.tvName.setText(si.getName());
        holder.tvEnabled.setText(String.valueOf(si.isEnabled()));
        holder.tvExported.setText(String.valueOf(si.isExported()));

        if (si.getProcessName() != null && !si.getProcessName().isEmpty()) {
            holder.tvProcessNameHeader.setVisibility(View.VISIBLE);
            holder.tvProcessName.setVisibility(View.VISIBLE);

            holder.tvProcessName.setText(si.getProcessName());
        } else {
            holder.tvProcessNameHeader.setVisibility(View.GONE);
            holder.tvProcessName.setVisibility(View.GONE);
        }

        if (si.getPermission() != null && !si.getPermission().isEmpty()) {
            holder.tvPermissionHeader.setVisibility(View.VISIBLE);
            holder.tvPermission.setVisibility(View.VISIBLE);

            holder.tvPermission.setText(si.getPermission());
        } else {
            holder.tvPermissionHeader.setVisibility(View.GONE);
            holder.tvPermission.setVisibility(View.GONE);
        }

        if (!si.getFlags().isEmpty()) {
            holder.tvFlagsHeader.setVisibility(View.VISIBLE);
            holder.tvFlags.setVisibility(View.VISIBLE);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < si.getFlags().size(); i++) {
                if (i != 0)
                    sb.append("\n");

                sb.append(si.getFlags().get(i));
            }
            holder.tvFlags.setText(sb);
        } else {
            holder.tvFlagsHeader.setVisibility(View.GONE);
            holder.tvFlags.setVisibility(View.GONE);
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
        @Bind(R.id.tvEnabled)
        public TextView tvEnabled;
        @Bind(R.id.tvExported)
        public TextView tvExported;
        @Bind(R.id.tvProcessNameHeader)
        public TextView tvProcessNameHeader;
        @Bind(R.id.tvProcessName)
        public TextView tvProcessName;
        @Bind(R.id.tvPermissionHeader)
        public TextView tvPermissionHeader;
        @Bind(R.id.tvPermission)
        public TextView tvPermission;
        @Bind(R.id.tvFlagsHeader)
        public TextView tvFlagsHeader;
        @Bind(R.id.tvFlags)
        public TextView tvFlags;

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
