package com.qf.liuyong.listviewmvp1021.presenter;

import com.qf.liuyong.listviewmvp1021.model.GiftModel;
import com.qf.liuyong.listviewmvp1021.model.IGiftModel;
import com.qf.liuyong.listviewmvp1021.view.DataCallBack;

import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class GiftPresenter implements DataCallBack,IGiftPresenter{
    private IGiftModel giftModel = new GiftModel();
    private DataCallBack dataCallBack;

    public GiftPresenter(DataCallBack dataCallBack) {
        this.dataCallBack = dataCallBack;
    }

    @Override
    public void setDataCallback(List<String> list) {
        if (list == null || list.isEmpty()){
            return;
        }
        this.dataCallBack.setDataCallback(list);
    }

    @Override
    public void queryGiftList() {
        giftModel.queryGiftList(this);
    }
}
