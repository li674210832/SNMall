package com.ln.xm.eventbus;

import com.ln.xm.home.bean.ResultBeanData;

import java.util.List;

/**
 * Created by Lenovo on 2017/5/2.
 * author ：李宁
 * 类注释：
 */

public class EventbusMessage {
    //价格
    private String cover_price;
    private String figure;
    //名称
    private String name;
    //商品ID
    private String product_id;

    public EventbusMessage(String cover_price, String figure, String name, String product_id) {
        this.cover_price = cover_price;
        this.figure = figure;
        this.name = name;
        this.product_id = product_id;

    }

    public String getCover_price() {
        return cover_price;
    }

    public String getFigure() {
        return figure;
    }

    public String getName() {
        return name;
    }

    public String getProduct_id() {
        return product_id;
    }

    @Override
    public String toString() {
        return "EventbusMessage{" +
                "cover_price='" + cover_price + '\'' +
                ", figure='" + figure + '\'' +
                ", name='" + name + '\'' +
                ", product_id='" + product_id + '\'' +
                '}';
    }
}
