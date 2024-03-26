package com.example.recycler;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.recycler.adapters.BaseAdapter;
import com.example.recycler.adapters.GridViewAdapter;
import com.example.recycler.adapters.ListViewAdapter;
import com.example.recycler.beans.Datas;
import com.example.recycler.beans.ItemBean;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private List<ItemBean> mData;      //要展示的数据
    private BaseAdapter mAdapter;
    private SwipeRefreshLayout mRefreshLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //找到控件
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.refresh_layout);

        //准备数据，一般来说，实际开发中，数据是从网络中获取的，这里展示用的是模拟数据
        initData();
        //设置默认的显示样式为 ListView 效果
        showList(true, false);

        //处理下拉刷新
        handlerDownPullUpdate();
    }

    @SuppressLint("ResourceAsColor")
    private void handlerDownPullUpdate() {
        //设置下拉刷新时进度条的颜色，参数是一个可变长度数组
        mRefreshLayout.setColorSchemeColors(R.color.purple_200, R.color.teal_200, R.color.white);
        mRefreshLayout.setOnRefreshListener(() -> {
            //当我们在顶部下拉刷新的时候，这个方法就会被触发
            //添加数据
            ItemBean data = new ItemBean();
            data.title = "我是新添加的数据";
            data.icon = R.mipmap.pic_07;
            //添加到 List 集合首部，如果直接 mData.add(data) 则会把元素添加到集合尾部
            mData.add(0, data);
            //更新 UI
            runOnUiThread(new Runnable() {
                @Override
                public void run() {
                    //如果没有这下面这一行代码，刷新确实是完成了，就是数据没显示出来
                    mAdapter.notifyDataSetChanged();
                    //如果没有下面这一行代码，就会一直转圈
                    mRefreshLayout.setRefreshing(false);
                }
            });

/*            new Handler().postDelayed(new Runnable() {
                @Override
                public void run() {
                    mAdapter.notifyDataSetChanged();
                    mRefreshLayout.setRefreshing(false);
                }
            }, 3000);*/
        });

    }

    private void initData() {
        mData = new ArrayList<>();
        //创建模拟数据
        for(int i = 0; i < Datas.icons.length; i++) {
            ItemBean item = new ItemBean();
            item.icon = Datas.icons[i];
            item.title = "第" + i + "个条目";
            mData.add(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_file, menu);
        return super.onCreateOptionsMenu(menu);
    }

    //菜单项被选中的时候，就会执行这个方法
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemId = item.getItemId();
        switch(itemId) {
            //ListView 效果
            case R.id.list_view_vertical_stander :
                showList(true, false);
                break;
            case R.id.list_view_vertical_revise :
                showList(true, true);
                break;
            case R.id.list_view_horizontal_stander :
                showList(false, false);
                break;
            case R.id.list_view_horizontal_revise :
                showList(false, true);
                break;

            //GridView 效果
            case R.id.grid_view_vertical_stander :
                showGrid(true, false);
                break;
            case R.id.grid_view_vertical_revise :
                showGrid(true, true);
                break;
            case R.id.grid_view_horizontal_stander :
                showGrid(false, false);
                break;
            case R.id.grid_view_horizontal_revise :
                showGrid(false, true);
                break;

            //多种条目类型被点击了
            case R.id.more_type:
                //跳转到一个新的 Activity 里去实现这个功能
                Intent intent = new Intent(this, MoreTypeActivity.class);
                startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    private void showGrid(boolean isVertical, boolean isReverse) {
        //1. 创建布局管理器
        GridLayoutManager layoutManager = new GridLayoutManager(this, 3);
        //1.1 设置水平还是垂直
        layoutManager.setOrientation(isVertical ? GridLayoutManager.VERTICAL : GridLayoutManager.HORIZONTAL);
        //1.2 设置标准（正向）还是反向
        layoutManager.setReverseLayout(isReverse);
        //1.3 给 RecyclerView 设置布局管理器
        mRecyclerView.setLayoutManager(layoutManager);

        //2.1 创建适配器
        mAdapter = new GridViewAdapter(mData);
        //2.2 给 RecyclerView 设置适配器
        mRecyclerView.setAdapter(mAdapter);
        //初始化事件
        initListener();
    }

    private void showList(boolean isVertical, boolean isReverse) {
        //RecyclerView 需要设置样式，其实就是设置布局管理器
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置水平还是垂直
        layoutManager.setOrientation(isVertical ? LinearLayoutManager.VERTICAL : LinearLayoutManager.HORIZONTAL);
        //设置标准（正向）还是反向
        layoutManager.setReverseLayout(isReverse);
        mRecyclerView.setLayoutManager(layoutManager);

        //创建适配器
        mAdapter = new ListViewAdapter(mData);
        mRecyclerView.setAdapter(mAdapter);
        //初始化事件
        initListener();
    }

    private void initListener() {
        mAdapter.setOnItemClickListener(new BaseAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {
                Toast.makeText(MainActivity.this, "您点击的是第" + position + "个条目", Toast.LENGTH_SHORT).show();
            }
        });
    }
}



