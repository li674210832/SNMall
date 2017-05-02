package com.ln.xm.base;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by Lenovo on 2017/4/24.
 * author ：李宁
 * 类注释：
 */

public abstract class BaseFragment extends Fragment {

    public  Context mContext;

    //该方法在Fragment创建时回调,得到上下文
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = getActivity();
    }
    //让Fragment加载XML布局资源,因为每个子类Fragment显示的界面都不同,所以返回一个抽象方法
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        return initView(inflater, container, savedInstanceState);
    }

    /**
     *加载Fragment布局资源,子类必须要覆写的抽象方法
     */
    public abstract View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) ;
    //Fragment与Activity被创建是回调,进行数据的初始化

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
        }

/**
 * 联网获取数据,进行数据的初始化,展示数据,子类必须要覆写抽象方法
 */
public abstract void initData();
}
