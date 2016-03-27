package com.livingstoneapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.DividerItemDecoration;
import com.livingstoneapp.R;
import com.livingstoneapp.helpers.RecyclerViewOnClickListener;
import com.livingstoneapp.helpers.RecyclerViewOnClickListenerInternal;
import com.livingstoneapp.models.ContentProviderInfo;
import com.livingstoneapp.models.PathPermission;
import com.livingstoneapp.models.URIPermissionPattern;
import com.livingstoneapp.views.LinearLayoutManager;

/**
 * Created by Renier on 2016/02/14.
 */
public class ContentProviderDetailAdapter extends RecyclerView.Adapter<ContentProviderDetailAdapter.ViewHolder> {
    public static final int GENERAL = 0;
    public static final int PATH_PERMISSIONS = 1;
    public static final int URI_PERMISSION_PATTERNS = 2;

    private RecyclerViewOnClickListener<Object> mListener;


    private Object[] mData;
    private int[] mDataSetTypes;

    public ContentProviderDetailAdapter(Object[] items, int[] dataSetTypes) {
        mData = items;
        mDataSetTypes = dataSetTypes;
    }

    public void setOnClickListener(RecyclerViewOnClickListener<Object> listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == GENERAL) {
            return new GeneralViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.content_provider_general_adapter_layout, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        } else if (viewType == PATH_PERMISSIONS) {
            return new PathPermissionViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.content_provider_path_permission_adapter_layout, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        } else if (viewType == URI_PERMISSION_PATTERNS) {
            return new URIPermissionPatternViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.content_provider_uri_permission_pattern_adapter_layout, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        } else {
            //This should never happen
            return new ViewHolder(
                    new View(parent.getContext()), null);
        }
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        if (holder.getItemViewType() == GENERAL) {
            ((GeneralViewHolder) holder).bind((ContentProviderInfo)mData[position]);
        } else if (holder.getItemViewType() == PATH_PERMISSIONS) {
            ((PathPermissionViewHolder) holder).bind((ArrayList<PathPermission>) mData[position]);
        } else if (holder.getItemViewType() == URI_PERMISSION_PATTERNS) {
            ((URIPermissionPatternViewHolder) holder).bind((ArrayList<URIPermissionPattern>) mData[position]);
        }
    }

    @Override
    public int getItemCount() {
        return mData.length;
    }

    @Override
    public int getItemViewType(int position) {
        return mDataSetTypes[position];
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        private RecyclerViewOnClickListenerInternal mListener;

        public ViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v);

            mListener = listener;
            v.setOnClickListener(v1 -> {
                if (mListener != null) {
                    mListener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    public static class PathPermissionViewHolder extends ViewHolder {

        @Bind(R.id.item_list)
        public RecyclerView recyclerView;

        private PathPermissionAdapter mAdapter;

        public PathPermissionViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v, listener);

            ButterKnife.bind(this, v);
        }

        public void bind(ArrayList<PathPermission> values) {
            mAdapter = new PathPermissionAdapter(values);

            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL_LIST));
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(mAdapter);
        }
    }

    public static class URIPermissionPatternViewHolder extends ViewHolder {

        @Bind(R.id.item_list)
        public RecyclerView recyclerView;

        private URIPermissionPatternsAdapter mAdapter;

        public URIPermissionPatternViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v, listener);

            ButterKnife.bind(this, v);
        }

        public void bind(ArrayList<URIPermissionPattern> values) {
            mAdapter = new URIPermissionPatternsAdapter(values);

            recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL_LIST));
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext()));
            recyclerView.setAdapter(mAdapter);
        }
    }


    public static class GeneralViewHolder extends ViewHolder {

        @Bind(R.id.tvName)
        public TextView tvName;
        @Bind(R.id.tvEnabled)
        public TextView tvEnabled;
        @Bind(R.id.tvExported)
        public TextView tvExported;
        @Bind(R.id.tvAuthority)
        public TextView tvAuthority;
        @Bind(R.id.tvReadPermission)
        public TextView tvReadPermission;
        @Bind(R.id.tvWritePermission)
        public TextView tvWritePermission;
        @Bind(R.id.tvProcessName)
        public TextView tvProcessName;
        @Bind(R.id.tvMultiProcess)
        public TextView tvMultiProcess;
        @Bind(R.id.tvSingleUser)
        public TextView tvSingleUser;
        @Bind(R.id.tvInitOrder)
        public TextView tvInitOrder;
        @Bind(R.id.tvIsSyncable)
        public TextView tvIsSyncable;
        @Bind(R.id.tvGrantURIPermission)
        public TextView tvGrantURIPermission;


        public GeneralViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v, listener);

            ButterKnife.bind(this, v);
        }

        public void bind(ContentProviderInfo info) {
            tvName.setText(info.getName());
            tvEnabled.setText(String.valueOf(info.isEnabled()));
            tvExported.setText(String.valueOf(info.isExported()));
            tvAuthority.setText(info.getAuthority());
            tvReadPermission.setText(info.getReadPermission());
            tvWritePermission.setText(info.getWritePermission());
            tvProcessName.setText(info.getProcessName());
            tvMultiProcess.setText(String.valueOf(info.isMultiProcess()));
            tvSingleUser.setText(String.valueOf(info.isSingleUser()));
            tvInitOrder.setText(String.valueOf(info.getInitOrder()));
            tvIsSyncable.setText(String.valueOf(info.isIsSyncable()));
            tvGrantURIPermission.setText(String.valueOf(info.isGrantURIPermissions()));
        }
    }
}
