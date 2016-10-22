package com.qf.liuyong.listviewmvp1021.model;

import com.qf.liuyong.listviewmvp1021.http.Contansts;
import com.qf.liuyong.listviewmvp1021.http.HttpUtils;
import com.qf.liuyong.listviewmvp1021.view.DataCallBack;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/10/21.
 */
public class GiftModel implements IGiftModel {
    @Override
    public void queryGiftList(final DataCallBack dataCallBack) {
        HttpUtils.request(Contansts.PATH, false, null, new HttpUtils.ICallBack() {
            @Override
            public void success(String result) {
                List<String> datas = new ArrayList<String>();
                try {
                    JSONObject jsonObject = new JSONObject(result);
                    JSONArray jsonArray = jsonObject.getJSONArray("info");
                    for (int i = 0; i < jsonArray.length(); i++) {
                        JSONObject jsonObject1 = jsonArray.getJSONObject(i);
                        String gname = jsonObject1.getString("gname");
                        datas.add(gname);
                    }
                    dataCallBack.setDataCallback(datas);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
