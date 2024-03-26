package com.example.recycler;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.recycler.adapters.MoreTypeAdapter;
import com.example.recycler.beans.Datas;
import com.example.recycler.beans.MoreTypeBean;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MoreTypeActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<MoreTypeBean> mData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_more_type);

        mRecyclerView = findViewById(R.id.more_type_list);
        mData = new ArrayList<>();
        //从网络中获取数据
        initDatas();

        //创建和设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);

        //创建适配器
        MoreTypeAdapter adapter = new MoreTypeAdapter(mData);
        mRecyclerView.setAdapter(adapter);
    }

    private void initDatas() {
        Random random = new Random();
        for(int i = 0; i < Datas.icons.length; i++) {
            MoreTypeBean data = new MoreTypeBean();
            //生成 0, 1 或 2 中的一个数字作为结果
            data.type = random.nextInt(3);
            data.title = "这是第" + i + "个条目";
            data.pic01 = Datas.icons[i];
            data.pic02 = Datas.icons[i];
            data.pic03 = Datas.icons[i];
            mData.add(data);
        }
    }
}