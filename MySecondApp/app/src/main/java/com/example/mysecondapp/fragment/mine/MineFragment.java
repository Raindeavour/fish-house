package com.example.mysecondapp.fragment.mine;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mysecondapp.R;

import java.util.ArrayList;

public class MineFragment extends Fragment {

    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> sfragment = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_mine, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tabLayout = getActivity().findViewById(R.id.tl_four);
        myViewPager = getActivity().findViewById(R.id.vp_four);

        titles.add("单品");
        titles.add("攻略");

        if (sfragment.isEmpty())    //避免重复创建
        {
            sfragment.add(new MineOneFragment());
            sfragment.add(new MineTwoFragment());
        }

//        getFragmentManager()所得到的是所在fragment 的父容器的管理器。
//        getChildFragmentManager()所得到的是在fragment  里面子容器的管理器。
//        getSupportFragmentManager()用于获取继承自android.support.v4.app.Fragment的fragment的管理器

        myViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return sfragment.get(i);
            }

            @Override
            public int getCount() {
                return sfragment.size();
            }

            @Override
            public CharSequence getPageTitle(int i) {
                return titles.get(i);
            }
        });

        tabLayout.setupWithViewPager(myViewPager);

//        //禁止滑动
//        myViewPager.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                return true;
//            }
//        });
    }
}
