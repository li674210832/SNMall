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
 * Created by Lenovo on 2017/4/28.
 * author ：李宁
 * 类注释：
 */

public class RecommendGridviewAdapter extends BaseAdapter {
     private Context mContext;
    private List<ResultBeanData.ResultBean.RecommendInfoBean>list;

    public RecommendGridviewAdapter(Context context, List<ResultBeanData.ResultBean.RecommendInfoBean> list) {

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
        if(view==null){
           viewHolder = new ViewHolder();
            view=View.inflate(mContext, R.layout.home_itme_recommend,null);
            viewHolder.iv_recommend= (ImageView) view.findViewById(R.id.iv_recommend);
            viewHolder.tv_name= (TextView) view.findViewById(R.id.tv_name);
            viewHolder.tv_price= (TextView) view.findViewById(R.id.tv_price);
            view.setTag(viewHolder);
        }else{
            viewHolder= (ViewHolder) view.getTag();
        }
        //根据位置,得到所需的数据
        ResultBeanData.ResultBean.RecommendInfoBean bean = list.get(i);
        //使用图片处理的开源框架,根据图片网址展示图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+bean.getFigure()).into(viewHolder.iv_recommend);
        viewHolder.tv_name.setText(bean.getName());
        viewHolder.tv_price.setText("人民币:"+bean.getCover_price());
        return view;
    }

    class ViewHolder{

        public ImageView iv_recommend;
        public TextView tv_name;
        public TextView tv_price;
    }
}
