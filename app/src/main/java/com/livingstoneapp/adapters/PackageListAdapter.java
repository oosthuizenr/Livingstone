package com.livingstoneapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.R;
import com.livingstoneapp.helpers.RecyclerViewOnClickListener;
import com.livingstoneapp.helpers.RecyclerViewOnClickListenerInternal;
import com.livingstoneapp.models.InstalledPackageHeader;

/**
 * Created by renier on 2/2/2016.
 */
public class PackageListAdapter extends RecyclerView.Adapter<PackageListAdapter.ViewHolder>{

    private RecyclerViewOnClickListener<InstalledPackageHeader> mListener;


    protected List<InstalledPackageHeader> mData;

    public PackageListAdapter() {
        mData = new ArrayList<>();
    }

    public void setOnClickListener(RecyclerViewOnClickListener<InstalledPackageHeader> listener) {
        this.mListener = listener;
    }

    public void addItemToList(InstalledPackageHeader item) {
        mData.add(item);
        notifyItemInserted(mData.size() - 1);
        notifyItemRangeInserted(mData.size() - 1, 1);
    }

    public void setData(List<InstalledPackageHeader> items) {
        mData = items;
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return
                new ViewHolder(
                        LayoutInflater.from(parent.getContext())
                                .inflate(R.layout.package_list_item_content, parent, false),
                        position -> {
                            if (mListener != null) {
                                mListener.onItemClick(mData.get(position));
                            }
                        });
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.tvAppName.setText(mData.get(position).getAppName());
        holder.ivIcon.setImageDrawable(mData.get(position).getAppIcon());
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerViewOnClickListenerInternal mListener;

        @Bind(R.id.ivIcon)
        public ImageView ivIcon;
        @Bind(R.id.tvAppName)
        public TextView tvAppName;

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
