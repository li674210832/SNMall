package com.ln.xm.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Lenovo on 2017/5/2.
 * author ：李宁
 * 类注释：事件就是SP缓存工具类,保存,读取数据在本地上
 */

public class CaCheUtils {
    //得到保存的String类型数据
    public static String getString(Context context,String key){
        SharedPreferences yds = context.getSharedPreferences("yds", Context.MODE_PRIVATE);
        return  yds.getString(key,"");
    }
    //保存String类型数据,参数:1上下文 2.保存键 3.保存String类型数据
    public static void saveString(Context context, String key,String value) {
        SharedPreferences yds = context.getSharedPreferences("yds", Context.MODE_PRIVATE);
        yds.edit().putString(key, value).commit();


    }


}
