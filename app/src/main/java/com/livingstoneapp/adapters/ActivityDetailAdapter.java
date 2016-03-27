package com.livingstoneapp.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;
import com.livingstoneapp.R;
import com.livingstoneapp.helpers.RecyclerViewOnClickListener;
import com.livingstoneapp.helpers.RecyclerViewOnClickListenerInternal;
import com.livingstoneapp.models.ActivityInfoInternal;

/**
 * Created by Renier on 2016/02/14.
 */
public class ActivityDetailAdapter extends RecyclerView.Adapter<ActivityDetailAdapter.ViewHolder> {
    public static final int GENERAL = 0;
    public static final int CONFIG_CHANGES = 1;
    public static final int CATEGORIES = 2;
    public static final int FLAGS = 3;
    public static final int SOFT_INPUT_MODE = 4;

    private RecyclerViewOnClickListener<Object> mListener;


    private Object[] mData;
    private int[] mDataSetTypes;

    public ActivityDetailAdapter(Object[] items, int[] dataSetTypes) {
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
                            .inflate(R.layout.activity_detail_general, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        } else if (viewType == CONFIG_CHANGES) {
            return new ConfigChangesViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.activity_detail_config_changes, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        } else if (viewType == CATEGORIES) {
            return new CategoriesViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.activity_detail_categories, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        }  else if (viewType == FLAGS) {
            return new FlagsViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.activity_detail_flags, parent, false),
                    position -> {
                        if (mListener != null) {
                            mListener.onItemClick(mData[position]);
                        }
                    });
        }   else if (viewType == SOFT_INPUT_MODE) {
            return new SoftInputModeViewHolder(
                    LayoutInflater.from(parent.getContext())
                            .inflate(R.layout.activity_detail_soft_input_mode, parent, false),
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
            ((GeneralViewHolder) holder).bind((ActivityInfoInternal)mData[position]);
        } else if (holder.getItemViewType() == CONFIG_CHANGES) {
            ((ConfigChangesViewHolder) holder).bind((ArrayList<String>) mData[position]);
        } else if (holder.getItemViewType() == CATEGORIES) {
            ((CategoriesViewHolder) holder).bind((ArrayList<String>)mData[position]);
        }   else if (holder.getItemViewType() == FLAGS) {
            ((FlagsViewHolder) holder).bind((ArrayList<String>)mData[position]);
        }  else if (holder.getItemViewType() == SOFT_INPUT_MODE) {
            ((SoftInputModeViewHolder) holder).bind((ArrayList<String>)mData[position]);
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

    public static class ConfigChangesViewHolder extends ViewHolder {

        @Bind(R.id.info_text)
        public TextView info_text;

        public ConfigChangesViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
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

    public static class CategoriesViewHolder extends ViewHolder {

        @Bind(R.id.info_text)
        public TextView info_text;

        public CategoriesViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
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

    public static class SoftInputModeViewHolder extends ViewHolder {

        @Bind(R.id.info_text)
        public TextView info_text;

        public SoftInputModeViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
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

    public static class GeneralViewHolder extends ViewHolder {

        @Bind(R.id.tvName)
        TextView tvName;
        @Bind(R.id.tvEnabled)
        TextView tvEnabled;
        @Bind(R.id.tvExported)
        TextView tvExported;
        @Bind(R.id.tvProcessName)
        TextView tvProcessName;
        @Bind(R.id.tvIsLauncher)
        TextView tvIsLauncher;
        @Bind(R.id.tvIsMain)
        TextView tvIsMain;
        @Bind(R.id.tvTargetActivity)
        TextView tvTargetActivity;
        @Bind(R.id.tvParentActivity)
        TextView tvParentActivity;
        @Bind(R.id.tvScreenOrientation)
        TextView tvScreenOrientation;
        @Bind(R.id.tvMaxRecents)
        TextView tvMaxRecents;
        @Bind(R.id.tvTaskAffinity)
        TextView tvTaskAffinity;
        @Bind(R.id.tvPersistableMode)
        TextView tvPersistableMode;
        @Bind(R.id.tvPermission)
        TextView tvPermission;
        @Bind(R.id.tvDocumentLaunchMode)
        TextView tvDocumentLaunchMode;


        public GeneralViewHolder(View v, RecyclerViewOnClickListenerInternal listener) {
            super(v, listener);

            ButterKnife.bind(this, v);
        }

        public void bind(ActivityInfoInternal ai) {
            tvName.setText(ai.getName());
            tvEnabled.setText(String.valueOf(ai.isEnabled()));
            tvExported.setText(String.valueOf(ai.isExported()));
            tvProcessName.setText(ai.getProcessName());
            tvIsLauncher.setText(String.valueOf(ai.isLauncher()));
            tvIsMain.setText(String.valueOf(ai.isMain()));
            tvTargetActivity.setText(ai.getTargetActivity());
            tvParentActivity.setText(ai.getParentActivityName());
            tvScreenOrientation.setText(ai.getScreenOrientation());
            tvMaxRecents.setText(String.valueOf(ai.getMaxRecents()));
            tvTaskAffinity.setText(ai.getTaskAffinity());
            tvPersistableMode.setText(ai.getPersitableMode());
            tvPermission.setText(ai.getPermission());
            tvDocumentLaunchMode.setText(ai.getDocumentLaunchMode());
        }
    }
}
