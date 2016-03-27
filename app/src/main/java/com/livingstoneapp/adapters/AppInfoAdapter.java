package com.livingstoneapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.R;
import com.livingstoneapp.helpers.RecyclerViewOnClickListener;
import com.livingstoneapp.helpers.RecyclerViewOnClickListenerInternal;
import com.livingstoneapp.models.DirectoryInfo;
import com.livingstoneapp.models.GeneralInfo;

/**
 * Created by Renier on 2016/02/14.
 */
public class AppInfoAdapter extends RecyclerView.Adapter<AppInfoAdapter.ViewHolder> {
    public static final int GENERAL = 0;
    public static final int DIRECTORIES = 1;
    public static final int FLAGS = 2;
    public static final int SIGNATURES = 3;

    private RecyclerViewOnClickListener<Object> mListener;


    private Object[] mData;
    private int[] mDataSetTypes;

    public AppInfoAdapter(Object[] items, int[] dataSetTypes) {
        mData = items;
        mDataSetTypes = dataSetTypes;
    }

    public void setOnClickListener(RecyclerViewOnClickListener<Object> listener) {
        this.mListener = listener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == FLAGS) {
            return new FlagsViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.app_info_flags_layout, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        } else if (viewType == DIRECTORIES) {
            return new DirectoriesViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.app_info_directories_layout, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        } else if (viewType == GENERAL) {
            return new GeneralViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.app_info_general_layout, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        } else if (viewType == SIGNATURES) {
            return new SignaturesViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.app_info_signatures_layout, parent, false),
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
        if (holder.getItemViewType() == FLAGS) {
            ((FlagsViewHolder) holder).bind((ArrayList<String>) mData[position]);
        } else if (holder.getItemViewType() == DIRECTORIES) {
            ((DirectoriesViewHolder) holder).bind((DirectoryInfo)mData[position]);
        } else if (holder.getItemViewType() == GENERAL) {
            ((GeneralViewHolder) holder).bind((GeneralInfo)mData[position]);
        } else if (holder.getItemViewType() == SIGNATURES) {
            ((SignaturesViewHolder) holder).bind((ArrayList<String>) mData[position]);
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

    public static class SignaturesViewHolder extends ViewHolder {

        @Bind(R.id.info_text)
        public TextView info_text;

        public SignaturesViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v, listener);

            ButterKnife.bind(this, v);
        }

        public void bind(ArrayList<String> values) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < values.size(); i++) {
                if (i != 0) {
                    sb.append("\n");
                }

                sb.append(values.get(i));
            }

            info_text.setText(sb);
        }
    }

    public static class FlagsViewHolder extends ViewHolder {

        @Bind(R.id.info_text)
        public TextView info_text;

        public FlagsViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v, listener);

            ButterKnife.bind(this, v);
        }

        public void bind(ArrayList<String> values) {
            StringBuilder sb = new StringBuilder();

            for (int i = 0; i < values.size(); i++) {
                if (i != 0) {
                    sb.append("\n");
                }

                sb.append(values.get(i));
            }

            info_text.setText(sb);
        }
    }

    public static class DirectoriesViewHolder extends ViewHolder {
        @Bind(R.id.tvDataDir)
        public TextView tvDataDir;

        @Bind(R.id.tvSourceDir)
        public TextView tvSourceDir;

        @Bind(R.id.tvNativeLibDir)
        public TextView tvNativeLibDir;

        @Bind(R.id.tvPublicSourceDir)
        public TextView tvPublicSourceDir;

        @Bind(R.id.tvSplitPublicSourceDirs)
        public TextView tvSplitPublicSourceDirs;

        @Bind(R.id.tvSplitSourceDirs)
        public TextView tvSplitSourceDirs;

        @Bind(R.id.tvDataDirHeader)
        public TextView tvDataDirHeader;

        @Bind(R.id.tvSourceDirHeader)
        public TextView tvSourceDirHeader;

        @Bind(R.id.tvNativeLibDirHeader)
        public TextView tvNativeLibDirHeader;

        @Bind(R.id.tvPublicSourceDirHeader)
        public TextView tvPublicSourceDirHeader;

        @Bind(R.id.tvSplitPublicSourceDirsHeader)
        public TextView tvSplitPublicSourceDirsHeader;

        @Bind(R.id.tvSplitSourceDirsHeader)
        public TextView tvSplitSourceDirsHeader;


        public DirectoriesViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v, listener);

            ButterKnife.bind(this, v);
        }

        public void bind(DirectoryInfo directoryInfo) {
            if (directoryInfo.getDataDir() != null && !directoryInfo.getDataDir().isEmpty()) {
                tvDataDir.setText(directoryInfo.getDataDir());
            } else {
                tvDataDirHeader.setVisibility(View.GONE);
                tvDataDir.setVisibility(View.GONE);
            }

            if (directoryInfo.getSourceDir() != null && !directoryInfo.getSourceDir().isEmpty()) {
                tvSourceDir.setText(directoryInfo.getSourceDir());
            } else {
                tvSourceDirHeader.setVisibility(View.GONE);
                tvSourceDir.setVisibility(View.GONE);
            }

            if (directoryInfo.getNativeLibDir() != null && !directoryInfo.getNativeLibDir().isEmpty()) {
                tvNativeLibDir.setText(directoryInfo.getNativeLibDir());
            } else {
                tvNativeLibDirHeader.setVisibility(View.GONE);
                tvNativeLibDir.setVisibility(View.GONE);
            }

            if (directoryInfo.getPublicSourceDir() != null && !directoryInfo.getPublicSourceDir().isEmpty()) {
                tvPublicSourceDir.setText(directoryInfo.getPublicSourceDir());
            } else {
                tvPublicSourceDirHeader.setVisibility(View.GONE);
                tvPublicSourceDir.setVisibility(View.GONE);
            }

            if (directoryInfo.getSplitPublicSourceDirs() != null && directoryInfo.getSplitPublicSourceDirs().length > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < directoryInfo.getSplitPublicSourceDirs().length; i++) {
                    if (i != 0) {
                        sb.append("\n");
                    }

                    sb.append(directoryInfo.getSplitPublicSourceDirs()[i]);
                }
                tvSplitPublicSourceDirs.setText(sb);
            } else {
                tvSplitPublicSourceDirsHeader.setVisibility(View.GONE);
                tvSplitPublicSourceDirs.setVisibility(View.GONE);
            }

            if (directoryInfo.getSplitSourceDirs() != null && directoryInfo.getSplitSourceDirs().length > 0) {
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < directoryInfo.getSplitSourceDirs().length; i++) {
                    if (i != 0) {
                        sb.append("\n");
                    }

                    sb.append(directoryInfo.getSplitSourceDirs()[i]);
                }
                tvSplitSourceDirs.setText(sb);
            } else {
                tvSplitSourceDirsHeader.setVisibility(View.GONE);
                tvSplitSourceDirs.setVisibility(View.GONE);
            }
        }
    }

    public static class GeneralViewHolder extends ViewHolder {

        @Bind(R.id.tvPackageNameHeader)
        public TextView tvPackageNameHeader;
        @Bind(R.id.tvPackageName)
        public TextView tvPackageName;
        @Bind(R.id.tvVersionCodeHeader)
        public TextView tvVersionCodeHeader;
        @Bind(R.id.tvVersionCode)
        public TextView tvVersionCode;
        @Bind(R.id.tvVersionNameHeader)
        public TextView tvVersionNameHeader;
        @Bind(R.id.tvVersionName)
        public TextView tvVersionName;
        @Bind(R.id.tvUIDHeader)
        public TextView tvUIDHeader;
        @Bind(R.id.tvUID)
        public TextView tvUID;
        @Bind(R.id.tvProcessNameHeader)
        public TextView tvProcessNameHeader;
        @Bind(R.id.tvProcessName)
        public TextView tvProcessName;
        @Bind(R.id.tvTargetSDKHeader)
        public TextView tvTargetSDKHeader;
        @Bind(R.id.tvTargetSDK)
        public TextView tvTargetSDK;
        @Bind(R.id.tvEnabledHeader)
        public TextView tvEnabledHeader;
        @Bind(R.id.tvEnabled)
        public TextView tvEnabled;
        @Bind(R.id.tvTaskAffinityHeader)
        public TextView tvTaskAffinityHeader;
        @Bind(R.id.tvTaskAffinity)
        public TextView tvTaskAffinity;
        @Bind(R.id.tvFirstInstallHeader)
        public TextView tvFirstInstallHeader;
        @Bind(R.id.tvFirstInstall)
        public TextView tvFirstInstall;
        @Bind(R.id.tvLastUpdateHeader)
        public TextView tvLastUpdateHeader;
        @Bind(R.id.tvLastUpdate)
        public TextView tvLastUpdate;
        @Bind(R.id.tvSharedUIDHeader)
        public TextView tvSharedUIDHeader;
        @Bind(R.id.tvSharedUID)
        public TextView tvSharedUID;
        @Bind(R.id.tvSharedUserLabelHeader)
        public TextView tvSharedUserLabelHeader;
        @Bind(R.id.tvSharedUserLabel)
        public TextView tvSharedUserLabel;

        public GeneralViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v, listener);

            ButterKnife.bind(this, v);
        }

        public void bind(GeneralInfo gi) {
            tvPackageName.setText(gi.getPackageName());
            tvVersionCode.setText(String.valueOf(gi.getVersionCode()));
            tvVersionName.setText(gi.getVersionName());
            tvUID.setText(String.valueOf(gi.getUID()));
            tvTargetSDK.setText(String.valueOf(gi.getTargetSDK()));
            tvProcessName.setText(gi.getProcessName());
            tvEnabled.setText(String.valueOf(gi.isEnabled()));
            tvTaskAffinity.setText(gi.getTaskAffinity());
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            tvFirstInstall.setText(sdf.format(new Date(gi.getFirstInstallTime())));
            tvLastUpdate.setText(sdf.format(new Date(gi.getLastUpdateTime())));
            tvSharedUID.setText(gi.getSharedUID());
            tvSharedUserLabel.setText(String.valueOf(gi.getSharedUserLabel()));
        }
    }
}
