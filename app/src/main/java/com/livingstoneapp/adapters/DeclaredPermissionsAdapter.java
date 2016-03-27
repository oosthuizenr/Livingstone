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
import com.livingstoneapp.models.DeclaredPermission;

/**
 * Created by renier on 2/17/2016.
 */
public class DeclaredPermissionsAdapter extends RecyclerView.Adapter<DeclaredPermissionsAdapter.ViewHolder> {

    private RecyclerViewOnClickListener<DeclaredPermission> mListener;


    protected List<DeclaredPermission> mData;

    public DeclaredPermissionsAdapter(List<DeclaredPermission> data) {
        mData = data;
    }

    public void setOnClickListener(RecyclerViewOnClickListener<DeclaredPermission> listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.declared_permissions_adapter_layout, parent, false),
                        position -> {
                            if (mListener != null) {
                                mListener.onItemClick(mData.get(position));
                            }
                        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getName());
        holder.tvLabel.setText(mData.get(position).getLabel());

        if (mData.get(position).getProtectionLevel().size() > 0) {
            holder.tvProtectionLevelHeader.setVisibility(View.VISIBLE);
            holder.tvProtectionLevel.setVisibility(View.VISIBLE);

            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mData.get(position).getProtectionLevel().size(); i++) {
                if (i != 0)
                    sb.append("\n");

                sb.append(mData.get(position).getProtectionLevel().get(i));
            }
            holder.tvProtectionLevel.setText(sb);
        } else {
            holder.tvProtectionLevelHeader.setVisibility(View.GONE);
            holder.tvProtectionLevel.setVisibility(View.GONE);
        }

        if (mData.get(position).getFlags().size() > 0) {
            holder.tvFlagsHeader.setVisibility(View.VISIBLE);
            holder.tvFlags.setVisibility(View.VISIBLE);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < mData.get(position).getFlags().size(); i++) {
                if (i != 0)
                    sb.append("\n");

                sb.append(mData.get(position).getFlags().get(i));
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
        @Bind(R.id.tvLabel)
        public TextView tvLabel;
        @Bind(R.id.tvProtectionLevel)
        public TextView tvProtectionLevel;
        @Bind(R.id.tvProtectionLevelHeader)
        public TextView tvProtectionLevelHeader;
        @Bind(R.id.tvFlags)
        public TextView tvFlags;
        @Bind(R.id.tvFlagsHeader)
        public TextView tvFlagsHeader;

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
