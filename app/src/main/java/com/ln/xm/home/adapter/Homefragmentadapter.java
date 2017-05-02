package com.ln.xm.home.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ln.xm.BuildConfig;
import com.ln.xm.R;
import com.ln.xm.app.GoodsInfoActivity;
import com.ln.xm.eventbus.EventbusMessage;
import com.ln.xm.home.bean.GoodsBean;
import com.ln.xm.home.bean.ResultBeanData;
import com.ln.xm.utils.Constants;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.Transformer;
import com.youth.banner.listener.OnBannerClickListener;
import com.youth.banner.listener.OnLoadImageListener;
import com.zhy.magicviewpager.transformer.ScaleInTransformer;

import org.greenrobot.eventbus.EventBus;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Lenovo on 2017/4/26.
 * author ：李宁
 * 类注释：HomeFragment下的RecyclerView适配器,在这里实现主页面丰富多彩的效果
 */

public class Homefragmentadapter extends RecyclerView.Adapter {
    public Context context;
    private ResultBeanData.ResultBean mResultBean;
    private final LayoutInflater mLayoutInflater;

    public Homefragmentadapter(Context context, ResultBeanData.ResultBean resultBean) {
        this.context = context;
        mResultBean = resultBean;
        mLayoutInflater = LayoutInflater.from(context);

    }

//相当于创建getView里的创建item一样(这里创建的是Holder)
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //判断返回类型,根据返回类型,决定创建什么样的Holder
        if(viewType==BANNER){
            //返回创建的BannerViewHolder
            View inflate = mLayoutInflater.inflate(R.layout.home_adapter_banner_viewpager, null);
           //返回创建的BannerViewHolder
            return  new BannerViewHolder(context,inflate);
        }//返回创建的ChannelViewHolder
        else if(viewType==CHANNEL){
            return  new ChannelViewHolder(context,mLayoutInflater.inflate(R.layout.home_channel_itme,null));
        }else if(viewType==ACT){
          return  new ActViewHolder(context,mLayoutInflater.inflate(R.layout.home_act_viewpager,null));
        }else if(viewType==SECKILL){
            return  new SeckillViewHolder(context,mLayoutInflater.inflate(R.layout.home_seckill,null));
        }else if(viewType==RECOMMEND){
            return  new RecommentViewHolder(context,mLayoutInflater.inflate(R.layout.home_recomment,null));

        }else if(viewType==HOT){
            return  new HotViewHolder(context,mLayoutInflater.inflate(R.layout.home_hot,null));
        }
        return null;
    }
   //相当getView中的绑定数据
    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        //B.更加Item的位置,调用getItemViewType,知道给此item绑定什么类型的ViewHolder
        if(getItemViewType(position)==BANNER){
             BannerViewHolder bannerViewHolder= (BannerViewHolder) holder;
            //给BannerViewHolder设置数据
            bannerViewHolder.setDate(mResultBean.getBanner_info());
         }else if(getItemViewType(position)==CHANNEL){
            ChannelViewHolder channelViewHolder= (ChannelViewHolder) holder;
            channelViewHolder.setDate(mResultBean.getChannel_info());

        }else if(getItemViewType(position)==ACT){
            ActViewHolder  actViewHolder= (ActViewHolder) holder;
            actViewHolder.setDate(mResultBean.getAct_info());
        }else if(getItemViewType(position)==SECKILL){
            SeckillViewHolder seckillViewHolder= (SeckillViewHolder) holder;
               seckillViewHolder.setDate(mResultBean.getSeckill_info());
        }else if(getItemViewType(position)==RECOMMEND){
            RecommentViewHolder recommentViewHolder= (RecommentViewHolder) holder;
            recommentViewHolder.setDate(mResultBean.getRecommend_info());
        }else if(getItemViewType(position)==HOT){
            HotViewHolder holder1= (HotViewHolder) holder;
            holder1 .setDate(mResultBean.getHot_info());

        }
    }

    //A.广告条幅类型(int数从0开始,数组从0开始,程序界:万物从0开始)
    private static final int BANNER =0;
    //A.频道类型
    private static final int CHANNEL = 1;
    //A.活动类型
    private static final int ACT = 2;
    //A.秒杀类型
    private static final int SECKILL = 3;
    //A.推荐类型
    private static final int RECOMMEND = 4;
    //A.热卖类型
    private static final int HOT = 5;

    //A.当前类型
    private int currentType =BANNER;


    //得到类型  position就代表条目的位置
    @Override
    public int getItemViewType(int position) {
            switch (position){
                case BANNER:
                    currentType =BANNER;
                    break;
                case CHANNEL:
                    currentType =CHANNEL;
                    break;
                case ACT:
                    currentType =ACT;
                    break;
                case SECKILL:
                    currentType =SECKILL;
                    break;
                case RECOMMEND:
                    currentType =RECOMMEND;
                    break;
                case HOT:
                    currentType =HOT;
                    break;

            }
        return currentType;
    }

    @Override
    public int getItemCount() {
        return 6;
    }






    /**
     * B.主界面的广告条,Banner,各种类型的VIewHolder都要集成到RecyclerView的VIewHolder
     */
    public class BannerViewHolder extends RecyclerView.ViewHolder{
        private Banner mBanner;
        private  Context mContext;

        public BannerViewHolder(Context context,View itemView) {
            super(itemView);
            mContext=context;
            mBanner= (Banner) itemView.findViewById(R.id.banner);

        }
        //从外界拿到所需的数据,设置给Banner控件
        public  void setDate(List<ResultBeanData.ResultBean.BannerInfoBean>bannerInfoBeen){
            //给Banner控件设置加载图片的数据,如果仅仅上午加载网址,就要设置监听,在其内部使用图片开源框架加载图片Glide
            ArrayList<String> imageurl = new ArrayList<>();
            //从BannerInfoBean容器中拿到图片的网址,在放到集合里
            for (int i = 0; i < bannerInfoBeen.size(); i++) {
                String image = bannerInfoBeen.get(i).getImage();
                Log.d("zzz","imggg"+image);
                imageurl.add(Constants.BASE_URL_IMAGE+image);
            }
            //设置广告条循环时所用到的小点.
            mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR);
            //参数:1.String类型的集合    2.加载图片后的回调监听
            mBanner.setImages(imageurl);
            /*或
            *  mBanner.setImages(imagersUrl, new OnLoadImageListener() {
                @Override
                public void OnLoadImage(ImageView view, Object url) {
                    //联网请求图片,使用的是Glide根据网址获取到图片,使ImageView与Glide转换的数据进行绑定
                    Glide.with(mContext)
                            .load(Constants.BASE_URL_IMAGE + url)
                            .into(view);
                }
            });*/
            //设置广告条轮播时就是手风琴的效果
            mBanner.setBannerAnimation(Transformer.Accordion);
            //设置item的点击事件
            mBanner.setOnBannerClickListener(new OnBannerClickListener() {
                @Override
                public void OnBannerClick(int position) {
                    Toast.makeText(mContext, "Position=="+position, Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    //c.主页频道的的ViewHolder
   public class ChannelViewHolder extends RecyclerView.ViewHolder{
        private  Context mmContext;
        private  GridView  gridView;
        public ChannelViewHolder(Context context,View itemView) {
            super(itemView);
            mmContext=context;
             gridView= (GridView) itemView.findViewById(R.id.gv_channel);
        }
        public void setDate(List<ResultBeanData.ResultBean.ChannelInfoBean>channelInfoBeen){
            //得到数据,创建适配器对象
            ChannelAdapter adapter = new ChannelAdapter(mmContext, channelInfoBeen);

            gridView.setAdapter(adapter);
            gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(mmContext, "position"+position, Toast.LENGTH_SHORT).show();

                }
            });
        }
    }

    private class ActViewHolder extends RecyclerView.ViewHolder{
        private  Context mContext;
        ViewPager  vp;
        public ActViewHolder(Context context,View itemView) {
            super(itemView);
            mContext=context;
              vp= (ViewPager) itemView.findViewById(R.id.act_viewpager);
            //给ViewPager设置间距
           vp.setPageMargin(40);
            vp.setOffscreenPageLimit(3);//>=3
            //setPageTransformer 决定动画效果
           vp.setPageTransformer(true, new
                   ScaleInTransformer());
        }
      public void setDate(List<ResultBeanData.ResultBean.ActInfoBean>actInfoBeen){
          ActviewpagerAdapter adapter = new ActviewpagerAdapter(mContext, actInfoBeen);
          vp.setAdapter(adapter);
      }
    }
    private class SeckillViewHolder extends RecyclerView.ViewHolder{
        private Context mContext;
        private TextView tv_time_seckill;
        private TextView tv_more_seckill;
        private RecyclerView rv_seckill;
        //E2.倒计时的时间,从服务器那拿两个值,进行相减得到倒计时的真实数值.这里定义了个临时变量
        private long dt=0;
        Handler handler=new Handler(){
            @Override
            public void handleMessage(Message msg) {
                dt=dt-1000;
                //E2.把拿到的秒值时间数据转换为小时,分,秒的形式
                SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
                String time = simpleDateFormat.format(new Date(dt));
                //E2.把倒计时显示到UI上
                tv_time_seckill.setText(time);
                //E2.在不断发送消息前先移除一下,减少OOM
                handler.removeMessages(0);
                handler.sendEmptyMessageDelayed(0,1000);
                //E2.经过我们的仔细观察可以看到,时间为0时,依然再减,所以要进行判断
                if(dt <=0 ){
                    //把所有消息移除
                    handler.removeCallbacksAndMessages(null);
                }
            }
        };
        public SeckillViewHolder(Context context,View itemView) {
            super(itemView);
            mContext=context;
            tv_time_seckill = (TextView) itemView.findViewById(R.id.tv_time_seckill);
            tv_more_seckill = (TextView) itemView.findViewById(R.id.tv_more_seckill);
            rv_seckill = (RecyclerView) itemView.findViewById(R.id.rv_seckill);

        }
         public  void setDate(ResultBeanData.ResultBean.SeckillInfoBean seckillInfoBeen){
             //得到数据了,设置数据:文本和RecycleView的数据,
             SeckillRecycleviewAdapter adapter = new SeckillRecycleviewAdapter(mContext, seckillInfoBeen.getList());
             rv_seckill.setAdapter(adapter);
             //设置布局管理器,参数:  1.上下文    2.设置方向(LinearLayoutManager.HORIZONTAL水平方向)   3.是不是倒序,false代表不是
             //提示:要有效果的话,记得把getItemCount()该为3即可
             rv_seckill.setLayoutManager(new LinearLayoutManager(mContext,LinearLayoutManager.HORIZONTAL,false));
               adapter.setonSeckillRecycleview(new SeckillRecycleviewAdapter.OnseckillRecycleview() {
                   @Override
                   public void Onitme(int position) {
                       Toast.makeText(mContext, "秒杀==="+getLayoutPosition(), Toast.LENGTH_SHORT).show();
                   }
               });

             //E2.计算秒杀倒计时,应为从bean集合里,拿到的时间数据不是int型,所以用Integer进行转换(其逻辑代码就是java基础的内容)
             dt=Integer.valueOf(seckillInfoBeen.getEnd_time()) -Integer.valueOf(seckillInfoBeen.getStart_time());
             //E2.建立handler实现定时器的效果,循环发送消息,以便能够使时间不断减一
             handler.sendEmptyMessageDelayed(0,1000);
         }
    }

    private class RecommentViewHolder extends  RecyclerView.ViewHolder{
        private  Context mContext;
        private TextView tv_more_recommend;
        private GridView gv_recommend;
        //进行要用到对象的初始化

        public RecommentViewHolder(Context context,View itemView) {
            super(itemView);
            mContext=context;
            tv_more_recommend= (TextView) itemView.findViewById(R.id.tv_more_recommend);
            gv_recommend= (GridView) itemView.findViewById(R.id.gv_recommend);
            gv_recommend.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                    Toast.makeText(mContext, "点击了"+position, Toast.LENGTH_SHORT).show();

                }
            });


        }
        private void setDate(List<ResultBeanData.ResultBean.RecommendInfoBean>recommendInfoBeen){
            RecommendGridviewAdapter adapter = new RecommendGridviewAdapter(mContext, recommendInfoBeen);
            gv_recommend.setAdapter(adapter);
        }
    }


    private  class HotViewHolder  extends RecyclerView.ViewHolder{
        private  Context mContext;
        private TextView tv_more_hot;
        private GridView gv_hot;
        public HotViewHolder(Context context,View itemView) {
            super(itemView);
            mContext=context;
            tv_more_hot= (TextView) itemView.findViewById(R.id.tv_more_hot);
            gv_hot= (GridView) itemView.findViewById(R.id.gv_hot);

        }

        private void setDate(final List<ResultBeanData.ResultBean.HotInfoBean>hotInfoBeen){
            HotGridviewAdapter adapter = new HotGridviewAdapter(context, hotInfoBeen);
            gv_hot.setAdapter(adapter);
              gv_hot.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                  @Override
                  public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                      //F.拿到对应位置的大数据的容器
                     // ResultBeanData.ResultBean.HotInfoBean hotInfoBean = hotInfoBeen.get(i);
                      Intent intent = new Intent(context, GoodsInfoActivity.class);
                      Log.d("zzz", "zz传 ******"+hotInfoBeen.toString());

                      EventBus.getDefault().postSticky(new EventbusMessage(hotInfoBeen.get(i).getCover_price(),
                              hotInfoBeen.get(i).getFigure(),hotInfoBeen.get(i).getName(),hotInfoBeen.get(i).getProduct_id()));
                      context.startActivity(intent);

                     /* //F.创建单独存放商品详情数据的容器
                      GoodsBean goodsBean = new GoodsBean();
                      //F.把容器中的数据放入到GoodsBean数据里.
                      goodsBean.setCover_price(hotInfoBean.getCover_price()+"");
                      goodsBean.setFigure(hotInfoBean.getFigure()+"");
                      goodsBean.setName(hotInfoBean.getName()+"");
                      goodsBean.setProduct_id(hotInfoBean.getProduct_id()+"");

                      //跳转到商品详情页
                      startGoodsInfoActivity( goodsBean);*/
                  }
              });
        }
    }
   /* private static final String  GOODS_BEAN= "goodsBean";
    private void startGoodsInfoActivity(GoodsBean goodsBean) {
        Intent intent = new Intent(context, GoodsInfoActivity.class);
        intent.putExtra(GOODS_BEAN,goodsBean);
        context.startActivity(intent);
    }*/



}
