package com.ln.xm.home.adapter;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ln.xm.R;
import com.ln.xm.home.bean.ResultBeanData;
import com.ln.xm.utils.Constants;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/2.
 * author ：李宁
 * 类注释：
 */

public class HotGridviewAdapter  extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.HotInfoBean>list;

    public HotGridviewAdapter(Context context, List<ResultBeanData.ResultBean.HotInfoBean> list) {

        mContext = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder viewHolder;
        if(view==null) {
            viewHolder = new ViewHolder();
            view = View.inflate(mContext, R.layout.home_itme_hot_gridview, null);
            viewHolder.iv_hot = (ImageView) view.findViewById(R.id.iv_hot);
            viewHolder.tv_name = (TextView) view.findViewById(R.id.tv_name);
            viewHolder.tv_price = (TextView) view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        ResultBeanData.ResultBean.HotInfoBean hotInfoBean = list.get(i);
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+hotInfoBean.getFigure()).into(viewHolder.iv_hot);
        viewHolder.tv_name.setText(hotInfoBean.getName());
        viewHolder.tv_price.setText("$ "+hotInfoBean.getCover_price());
        return view;
    }

    class ViewHolder{

        public ImageView iv_hot;
        public TextView tv_name;
        public TextView tv_price;
    }
}
