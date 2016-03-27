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
import com.livingstoneapp.models.BroadcastReceiverInfo;

/**
 * Created by renier on 2/17/2016.
 */
public class BroadcastReceiversListAdapter extends RecyclerView.Adapter<BroadcastReceiversListAdapter.ViewHolder> {

    private RecyclerViewOnClickListener<BroadcastReceiverInfo> mListener;


    protected List<BroadcastReceiverInfo> mData;

    public BroadcastReceiversListAdapter(List<BroadcastReceiverInfo> data) {
        mData = data;
    }

    public void setOnClickListener(RecyclerViewOnClickListener<BroadcastReceiverInfo> listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.app_info_broadcast_receivers_adapter_layout, parent, false),
                        position -> {
                            if (mListener != null) {
                                mListener.onItemClick(mData.get(position));
                            }
                        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvName.setText(mData.get(position).getName());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerViewOnClickListenerInternal mListener;

        @Bind(R.id.tvName)
        public TextView tvName;

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
