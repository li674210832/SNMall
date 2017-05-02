package com.ln.xm.app;

import android.app.Application;
import android.content.Context;

import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Lenovo on 2017/4/25.
 * author ：李宁
 * 类注释：
 */

public class Myapplication extends Application {
  private  static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        //初始化okhttp工具类
        initOKhttp();
        mContext=this;
    }
    public static Context getContext(){
        return mContext;
    }

    private void initOKhttp() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                //  .addInterceptor(new LoggerInterceptor("TAG"))
                //设置连接超时
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();
        //  //进行okhttp工具类初始化的操作
        OkHttpUtils.initClient(okHttpClient);
    }
}
