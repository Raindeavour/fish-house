package com.example.mysecondapp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

import com.example.mysecondapp.R;
import com.example.mysecondapp.fragment.mine.MineFragment;
import com.example.mysecondapp.fragment.homepage.HomePageFragment;
import com.example.mysecondapp.fragment.classification.ClassificationFragment;
import com.example.mysecondapp.fragment.list.ListFragment;
import com.example.mysecondapp.util.NoScrollViewPager;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private NoScrollViewPager myViewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragment = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();
        initView();

        titles.add("首页");
        titles.add("榜单");
        titles.add("分类");
        titles.add("我的");

        fragment.add(new HomePageFragment());
        fragment.add(new ListFragment());
        fragment.add(new ClassificationFragment());
        fragment.add(new MineFragment());

        myViewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragment.get(i);
            }

            @Override
            public int getCount() {
                return fragment.size();
            }

            @Override
            public  CharSequence getPageTitle(int i)
            {
                return  titles.get(i);
            }
        });

        tabLayout.setupWithViewPager(myViewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.pagehome_select);
        tabLayout.getTabAt(1).setIcon(R.drawable.list_select);
        tabLayout.getTabAt(2).setIcon(R.drawable.sort_select);
        tabLayout.getTabAt(3).setIcon(R.drawable.person_select);

        //默认为3，避免切换fragment时，重新创建fragment时旧fragment数据没清楚，而产生重复数据
        myViewPager.setOffscreenPageLimit(4);

        //这个方法扔能轻微滑动至翻页
//        //禁止滑动
//        myViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
    }

    @Override
    public void initView() {
        tabLayout =findViewById(R.id.tabLayout);
        myViewPager = findViewById(R.id.myViewPager);
    }
}
