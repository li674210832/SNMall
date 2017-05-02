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
 * Created by Lenovo on 2017/4/26.
 * author ：李宁
 * 类注释：
 */

public class ChannelAdapter extends BaseAdapter {
    private Context mContext;
    private List<ResultBeanData.ResultBean.ChannelInfoBean>cc;

    public ChannelAdapter(Context context, List<ResultBeanData.ResultBean.ChannelInfoBean> cc) {

        mContext = context;
        this.cc = cc;
    }

    @Override
    public int getCount() {
        return cc.size();
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
        if(view==null){
             viewHolder = new ViewHolder();
            view  = View.inflate(mContext, R.layout.home_itme_channle, null);
           viewHolder. iv_channel= (ImageView) view.findViewById(R.id.iv_channel);
            viewHolder.tv_channel= (TextView) view.findViewById(R.id.tv_channel);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+cc.get(i).getImage()).into(viewHolder. iv_channel);
        viewHolder.tv_channel.setText(cc.get(i).getChannel_name());
        return view;
    }

    static class ViewHolder{


        public ImageView iv_channel;
        public TextView tv_channel;
    }
}
