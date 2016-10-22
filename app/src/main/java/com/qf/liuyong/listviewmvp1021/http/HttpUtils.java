package com.qf.liuyong.listviewmvp1021.http;

import android.os.Handler;
import android.os.Message;
import android.util.Log;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by bluesky on 16/9/13.
 */
public class HttpUtils {
    private static ExecutorService executorService;


    public static void request(String path, boolean isPost, String param,ICallBack callBack) {
        if (executorService == null) {
            executorService = Executors.newFixedThreadPool(4);
        }

        executorService.execute(new NetworkRunnable(path,isPost,param,callBack));
    }

    private static final String TAG = "androidxx";


    static class NetworkRunnable implements Runnable {

        private String path;
        private boolean isPost;
        private String param;
        private ICallBack callBack;

        private Handler mHandler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                super.handleMessage(msg);
                String result = msg.obj.toString();
                Log.i(TAG, "handleMessage: " + result);
                callBack.success(result);
            }
        };

        public NetworkRunnable(String path, boolean isPost, String param,ICallBack callBack) {
            this.path = path;
            this.isPost = isPost;
            this.param = param;
            this.callBack = callBack;
        }

        @Override
        public void run() {
            try {
                URL url = new URL(path);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                if (isPost) {
                    httpURLConnection.setRequestMethod("POST");
                    httpURLConnection.setDoOutput(true);
                    httpURLConnection.getOutputStream().write(param.getBytes());
                    httpURLConnection.getOutputStream().flush();
                }
                httpURLConnection.connect();
                if (httpURLConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                    InputStream inputStream = httpURLConnection.getInputStream();
                    byte[] buffer = new byte[1024];
                    int len = 0;
                    StringBuilder stringBuilder = new StringBuilder();
                    while((len = inputStream.read(buffer)) != -1) {
                        stringBuilder.append(new String(buffer,0,len));
                    }

                    Message message = mHandler.obtainMessage();
                    message.obj = stringBuilder.toString();
                    mHandler.sendMessage(message);
                }
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public interface ICallBack {
        void success(String result);
    }
}
