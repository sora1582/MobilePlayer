package com.example.sora.mobileplayer.activity;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;

import com.example.sora.mobileplayer.R;
import com.example.sora.mobileplayer.base.BasePager;
import com.example.sora.mobileplayer.pager.AudioPager;
import com.example.sora.mobileplayer.pager.VideoPager;

import java.util.ArrayList;

/**
 * Created by sora on 2018/6/9.
 * 主页面
 */
public class MainActivity extends FragmentActivity{


    private RadioGroup rg_bottom_tag;


    /**
     * 页面的集合
     */
    private ArrayList<BasePager> basePagers;

    /**
     * 选中的位置
     */
    private int position;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);



        rg_bottom_tag = (RadioGroup) findViewById(R.id.rg_bottom_tag);

       /* rb_video = (RadioButton)findViewById(R.id.rb_video);
        rb_audio = (RadioButton)findViewById(R.id.rb_audio);
        rb_net_video = (RadioButton)findViewById(R.id.rb_net_video);
        rb_netaudio = (RadioButton)findViewById(R.id.rb_netaudio);*/


        basePagers = new ArrayList<>();
        basePagers.add(new VideoPager(this));//添加本地视频页面-0
        basePagers.add(new AudioPager(this));//添加本地音乐页面-1


        //设置RadioGroup的监听
        rg_bottom_tag.setOnCheckedChangeListener(new MyOnCheckedchangeListener());
        rg_bottom_tag.check(R.id.rb_video);//默认选中首页

    }

    class MyOnCheckedchangeListener implements RadioGroup.OnCheckedChangeListener{

        @Override
        public void onCheckedChanged(RadioGroup group, int checkedId) {
            switch (checkedId){
                default:
                    position = 0;
                    break;
                case R.id.rb_audio://音频
                    position = 1;
                    break;

            }

            setFragment();
        }
    }

    /**
     * 把页面添加到Fragment中
     */
    private void setFragment() {
        //1.得到FragmenManger
        FragmentManager manger = getSupportFragmentManager();
        //2。开启事务
        FragmentTransaction ft = manger.beginTransaction();
        //3.替换

        class ReplaceFragment extends Fragment {

            private BasePager basePager;

            public ReplaceFragment(BasePager basePager) {
                this.basePager=basePager;
            }


            @Override
            public void onCreate(Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
            }

            @Override
            public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                     Bundle savedInstanceState) {
                return basePager.rootView;
            }
        }
        ft.replace(R.id.fl_main_content, new ReplaceFragment(getBasePager()));
        //4.提交事务
        ft.commit();

    }

    /**
     * 根据位置得到对应的页面
     * @return
     */
    private BasePager getBasePager() {
        BasePager basePager = basePagers.get(position);

        if(basePager != null && !basePager.isInitData){
            basePager.initDate();//联网请求或绑定数据
            basePager.isInitData = true;
        }
        return basePager;
    }
}
