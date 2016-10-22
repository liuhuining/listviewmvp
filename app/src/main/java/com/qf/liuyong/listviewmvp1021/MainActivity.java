package com.qf.liuyong.listviewmvp1021;

import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.qf.liuyong.listviewmvp1021.presenter.GiftPresenter;
import com.qf.liuyong.listviewmvp1021.view.DataCallBack;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements DataCallBack {

    private ListView listView;
    private List<String> datas = new ArrayList<>();
    private ArrayAdapter<String> arrayAdapter;
    private GiftPresenter giftPresenter;
    private ImageView imageView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageView = (ImageView)findViewById(R.id.imageview);
        listView = (ListView) findViewById(R.id.listview);
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, datas);
        listView.setAdapter(arrayAdapter);
        //添加帧动画
        AnimationDrawable drawable = (AnimationDrawable)imageView.getBackground();
        //启动动画
        drawable.start();
        listView.setEmptyView(imageView);
        giftPresenter = new GiftPresenter(this);
        giftPresenter.queryGiftList();
    }

    /**
     *
     * @param list
     */
    @Override
    public void setDataCallback(List<String> list) {
        this.datas.addAll(list);
        this.arrayAdapter.notifyDataSetChanged();
    }
}
