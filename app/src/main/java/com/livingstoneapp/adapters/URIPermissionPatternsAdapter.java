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
import com.livingstoneapp.models.URIPermissionPattern;

/**
 * Created by renier on 2/17/2016.
 */
public class URIPermissionPatternsAdapter extends RecyclerView.Adapter<URIPermissionPatternsAdapter.ViewHolder> {

    private RecyclerViewOnClickListener<URIPermissionPattern> mListener;


    protected List<URIPermissionPattern> mData;

    public URIPermissionPatternsAdapter(List<URIPermissionPattern> data) {
        mData = data;
    }

    public void setOnClickListener(RecyclerViewOnClickListener<URIPermissionPattern> listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.content_provider_uri_permission_pattern_item_adapter_layout, parent, false),
                        position -> {
                            if (mListener != null) {
                                mListener.onItemClick(mData.get(position));
                            }
                        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        URIPermissionPattern upp = mData.get(position);

        holder.tvPath.setText(upp.getPath());
        holder.tvType.setText(upp.getType());
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
