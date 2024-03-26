package com.example.recycler.adapters;

import android.view.View;
import android.view.ViewGroup;

import com.example.recycler.R;
import com.example.recycler.beans.ItemBean;

import java.util.List;

public class GridViewAdapter extends BaseAdapter {
    public GridViewAdapter(List<ItemBean> data) {
        super(data);
    }

    @Override
    protected View getSubView(ViewGroup parent, int viewType) {
        return View.inflate(parent.getContext(), R.layout.item_grid_view, null);
    }
}


