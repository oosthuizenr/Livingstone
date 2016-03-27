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
import com.livingstoneapp.models.PathPermission;

/**
 * Created by renier on 2/17/2016.
 */
public class PathPermissionAdapter extends RecyclerView.Adapter<PathPermissionAdapter.ViewHolder> {

    private RecyclerViewOnClickListener<PathPermission> mListener;


    protected List<PathPermission> mData;

    public PathPermissionAdapter(List<PathPermission> data) {
        mData = data;
    }

    public void setOnClickListener(RecyclerViewOnClickListener<PathPermission> listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.content_provider_path_permission_item_adapter_layout, parent, false),
                        position -> {
                            if (mListener != null) {
                                mListener.onItemClick(mData.get(position));
                            }
                        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        PathPermission pp = mData.get(position);

        holder.tvPath.setText(pp.getPath());
        holder.tvType.setText(pp.getType());
        holder.tvReadPermission.setText(pp.getReadPermission());
        holder.tvWritePermission.setText(pp.getWritePermission());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerViewOnClickListenerInternal mListener;

        @Bind(R.id.tvPath)
        public TextView tvPath;
        @Bind(R.id.tvType)
        public TextView tvType;
        @Bind(R.id.tvReadPermission)
        public TextView tvReadPermission;
        @Bind(R.id.tvWritePermission)
        public TextView tvWritePermission;

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
