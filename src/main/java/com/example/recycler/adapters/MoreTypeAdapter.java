package com.example.recycler.adapters;

import android.view.View;
import android.view.ViewGroup;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler.R;
import com.example.recycler.beans.MoreTypeBean;

import java.util.List;

public class MoreTypeAdapter extends RecyclerView.Adapter {

    //定义三个常量标识，因为有三种条目类型
    public static final int TYPE_FULL_IMAGE = 0;
    public static final int TYPE_RIGHT_IMAGE = 1;
    public static final int TYPE_THREE_IMAGES = 2;

    private final List<MoreTypeBean> mData;
    public MoreTypeAdapter(List<MoreTypeBean> data) {
        this.mData = data;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //根据 ViewType 去创建不同的条目
        View view;

        if (viewType == TYPE_FULL_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_full_image, null);
            return new FullImageHolder(view);
        } else if (viewType == TYPE_RIGHT_IMAGE) {
            view = View.inflate(parent.getContext(), R.layout.item_type_left_title_right_image, null);
            return new RightImageHolder(view);
        } else {
            view = View.inflate(parent.getContext(), R.layout.item_type_three_image, null);
            return new ThreeImagesHolder(view);
        }
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //TODO:这里就不设置数据了
        View itemView = holder.itemView;
    }

    @Override
    public int getItemCount() {
        if (mData != null) {
            return mData.size();
        }
        return 0;
    }

    //我们要复写一个方法,这个方法是根据条件来返条目类型的
    @Override
    public int getItemViewType(int position) {
        MoreTypeBean moreTypeBean = mData.get(position);
        if (moreTypeBean.type == 0) {
            return TYPE_FULL_IMAGE;
        } else if (moreTypeBean.type == 1) {
            return TYPE_RIGHT_IMAGE;
        } else {
            return TYPE_THREE_IMAGES;
        }
    }

    private class FullImageHolder extends RecyclerView.ViewHolder {
        public FullImageHolder(View itemView) {
            super(itemView);
        }
    }

    private class ThreeImagesHolder extends RecyclerView.ViewHolder {
        public ThreeImagesHolder(View itemView) {
            super(itemView);
        }
    }

    private class RightImageHolder extends RecyclerView.ViewHolder {
        public RightImageHolder(View itemView) {
            super(itemView);
        }
    }
}
