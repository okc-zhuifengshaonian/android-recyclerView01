package com.example.recycler.adapters;

import android.annotation.SuppressLint;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.example.recycler.R;
import com.example.recycler.beans.ItemBean;

import java.util.List;

public abstract class BaseAdapter extends RecyclerView.Adapter<BaseAdapter.InnerHolder>{
    private final List<ItemBean> mData;
    public BaseAdapter(List<ItemBean> data) {
        this.mData = data;
    }

    protected abstract View getSubView(ViewGroup parent, int viewType);

    @Override
    public InnerHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = getSubView(parent, viewType);
        return new InnerHolder(view);
    }

    //这个方法一般用来设置数据
    @Override
    public void onBindViewHolder(InnerHolder holder, @SuppressLint("RecyclerView") int position) {
        View itemView = holder.itemView;
        ImageView mIcon = itemView.findViewById(R.id.icon);
        TextView mTitle = itemView.findViewById(R.id.title);
        ItemBean itemBean = mData.get(position);
        mIcon.setImageResource(itemBean.icon);
        mTitle.setText(itemBean.title);

        itemView.setOnClickListener(v -> {
            if(mOnItemClickListener != null) {
                mOnItemClickListener.onItemClick(position);
            }
        });
    }

    //返回条目的个数
    @Override
    public int getItemCount() {
        if(mData != null) {
            return mData.size();
        }
        return 0;
    }

    public class InnerHolder extends RecyclerView.ViewHolder {
        public InnerHolder(View itemView) {
            super(itemView);
        }
    }

    /*
     * 编写回调的步骤：
     * 1. 创建这个接口
     * 2. 定义接口内部的方法
     * 3. 提供设置接口的方法（其实是外部实现）
     * 4. 接口方法调用
     * */

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener listener) {
        this.mOnItemClickListener = listener;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }
}
