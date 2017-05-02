package com.ln.xm.shoppingcart.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ln.xm.R;
import com.ln.xm.base.BaseFragment;

/**
 * Created by Lenovo on 2017/4/24.
 * author ：李宁
 * 类注释：
 */

public class ShoppingcardFragment extends BaseFragment {

    @Override
    public View initView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View  vv=View.inflate(mContext, R.layout.shoppingcard_activity,null);
        return vv;
    }

    @Override
    public void initData() {

    }
}
