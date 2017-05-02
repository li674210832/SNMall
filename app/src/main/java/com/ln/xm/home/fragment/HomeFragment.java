package com.ln.xm.home.fragment;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.ln.xm.R;
import com.ln.xm.base.BaseFragment;
import com.ln.xm.home.adapter.Homefragmentadapter;
import com.ln.xm.home.bean.ResultBeanData;
import com.ln.xm.utils.Constants;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import okhttp3.Call;

/**
 * Created by Lenovo on 2017/4/24.
 * author ：李宁
 * 类注释：
 */

public class HomeFragment extends BaseFragment {
    private RecyclerView rvHome;
    private ImageView ib_top;
    private TextView tv_search_home;
    private TextView tv_message_home;
    private ResultBeanData.ResultBean mResultBean;

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  vv=View.inflate(mContext, R.layout.home_activity,null);
        rvHome = (RecyclerView) vv.findViewById(R.id.rv_home);
        ib_top = (ImageView) vv.findViewById(R.id.ib_top);
        tv_search_home = (TextView) vv.findViewById(R.id.tv_search_home);
        tv_message_home = (TextView) vv.findViewById(R.id.tv_message_home);
        //给按钮设置点击事件
        initListener();

        return vv;


    }
    //给控件设置的点击事件
    private void initListener() {
        //置顶的监听
        ib_top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //回到顶部
                rvHome.scrollToPosition(0);
            }
        });
        //搜素的监听
        tv_search_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "搜索", Toast.LENGTH_SHORT).show();
            }
        });

        //消息的监听
        tv_message_home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext, "进入消息中心", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initData() {
//使用okhttp工具类get请求网络
        OKhttpGETData();
    }
    //使用okhttp工具类get请求网络
    private void OKhttpGETData() {
        //若要改变请求自己的网址,改变url即可
        String url = Constants.HOME_URL;
        OkHttpUtils.get()//设置请求网络的方式
                .url(url)//设置请求网址
                //如果请求网址不需要账号,密码,可以把参数删掉
/*                .addParams("username", "hyman")
                .addParams("password", "123")*/
                .build()
                .execute(new StringCallback() {
                    @Override//请求网络失败回调
                    public void onError(Call call, Exception e, int i) {
                        Toast.makeText(mContext, "网络失败", Toast.LENGTH_SHORT).show();

                    }

                    @Override//请求网络成功回调,使用fastJson进行json的解析
                    public void onResponse(String s, int i) {
                        //测试是否能请求网络成功,及线程是否允许在主线程
                        Toast.makeText(mContext, "网络成功", Toast.LENGTH_SHORT).show();
                        Log.d("zzz",s);
                        //json解析数据
                        processprocessData(s);
                    }
                });
    }
    //json解析数据
    private void processprocessData(String s) {
     //使用fastJson解析数据,把整理好的数据放入指定的容器中     参数: json的数据   ,容器类的字节码
        ResultBeanData resultBeanData = JSON.parseObject(s, ResultBeanData.class);
        //得到容器中装数据的集合
        mResultBean = resultBeanData.getResult();
        //测试是否得解析json数据成功
        String name = mResultBean.getHot_info().get(0).getName();
        Log.d("zzz",name);
       if(mResultBean!=null){
           Homefragmentadapter adapter = new Homefragmentadapter(mContext, mResultBean);
           //RecycleView设置适配器
           rvHome.setAdapter(adapter);
           rvHome.setLayoutManager(new GridLayoutManager(mContext,1));
       }else{
           Toast.makeText(mContext, "没有数据,所以显示为null", Toast.LENGTH_SHORT).show();
       }

    }
}
