package com.ln.xm.home.adapter;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ln.xm.home.bean.ResultBeanData;
import com.ln.xm.utils.Constants;

import java.util.List;

/**
 * Created by Lenovo on 2017/4/27.
 * author ：李宁
 * 类注释：
 */

public class ActviewpagerAdapter extends PagerAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ActInfoBean>list;

    public ActviewpagerAdapter(Context context, List<ResultBeanData.ResultBean.ActInfoBean> list) {
        mContext = context;
        this.list = list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public boolean isViewFromObject(View view, Object object) {
        return view==object;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object) {
        container.removeView((View) object);

    }

    @Override
    public Object instantiateItem(ViewGroup container, final int position) {
        ImageView imageView = new ImageView(mContext);
        imageView.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,ViewGroup.LayoutParams.FILL_PARENT));
        imageView.setScaleType(ImageView.ScaleType.FIT_XY);
        //给图片设置点击事件
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mContext, "点击了"+position, Toast.LENGTH_SHORT).show();
            }
        });
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+list.get(position).getIcon_url()).into(imageView);
        container.addView(imageView);

        return imageView;
    }
}
