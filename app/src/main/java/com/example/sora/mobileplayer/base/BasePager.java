package com.example.sora.mobileplayer.base;

import android.content.Context;
import android.view.View;

/**
 * Created by sora on 2018/6/10.
 * 基类，公共类
 * videoPager
 * AudioPager
 * NetVideoPager
 * NetAudioPager
 * 继承BasePager
 */

public abstract class BasePager {
    /**
     * 上下文
     */
    public final Context context;

    public View rootView;
    public boolean isInitData;

    public BasePager(Context context){
        this.context = context;
        rootView = initView();
    }
    /**
     * 强制由孩子实现，实现特定效果
     * @return
     */
    public abstract View initView() ;

    /**
     * 当子页面需要初始化数据，联网请求数据，或者绑定数据的时候要重写该方法
     */
    public void initDate(){

    }
}
