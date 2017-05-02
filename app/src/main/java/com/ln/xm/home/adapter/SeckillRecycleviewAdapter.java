package com.ln.xm.home.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ln.xm.R;
import com.ln.xm.home.bean.ResultBeanData;
import com.ln.xm.utils.Constants;

import java.util.List;

/**
 * Created by Lenovo on 2017/4/27.
 * author ：李宁
 * 类注释：
 */

public class SeckillRecycleviewAdapter extends RecyclerView.Adapter<SeckillRecycleviewAdapter.ViewHolder> {
       private Context mContext;
    private List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean>list;

    public SeckillRecycleviewAdapter(Context context, List<ResultBeanData.ResultBean.SeckillInfoBean.ListBean> list) {

        mContext = context;
        this.list = list;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //View inflate = LayoutInflater.from(mContext).inflate(R.layout.home_itme_seckill, parent);
       View inflate= View.inflate(mContext,R.layout.home_itme_seckill,null);
        return new ViewHolder(inflate);
    }

    @Override //给VIewHolder进行数据的绑定.
    public void onBindViewHolder(ViewHolder holder, int position) {
        //使用Glide开源框架根据网址是ImageView加载图片
        Glide.with(mContext).load(Constants.BASE_URL_IMAGE+list.get(position).getFigure()).into(holder.iv_figure);
        //设置价格
        holder.tv_cover_price.setText(list.get(position).getCover_price()+"");
        holder.tv_origin_price.setText(list.get(position).getOrigin_price()+"");
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView iv_figure;
        private TextView tv_cover_price;
        private TextView tv_origin_price;
        public ViewHolder(View itemView) {

            super(itemView);
            iv_figure= (ImageView) itemView.findViewById(R.id.iv_figure);
            tv_cover_price= (TextView) itemView.findViewById(R.id.tv_cover_price);
            tv_origin_price= (TextView) itemView.findViewById(R.id.tv_origin_price);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(mContext, "秒杀="+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                    if(mOnseckillRecycleview!=null){
                        mOnseckillRecycleview.Onitme(getLayoutPosition());
                    }
                }
            });
        }
    }

    //自定义的一个RecycleView点击事件的接口.
public interface OnseckillRecycleview{
        //当某条被点击时回调
        void Onitme(int position);
    }

    private  OnseckillRecycleview mOnseckillRecycleview;

    public void setonSeckillRecycleview(OnseckillRecycleview onseckillRecycleview) {
        mOnseckillRecycleview = onseckillRecycleview;
    }
}
