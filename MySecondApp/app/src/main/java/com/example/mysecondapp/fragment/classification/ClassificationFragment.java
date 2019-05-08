package com.example.mysecondapp.fragment.classification;

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
import com.example.mysecondapp.fragment.mine.MineOneFragment;
import com.example.mysecondapp.fragment.mine.MineTwoFragment;

import java.util.ArrayList;

public class ClassificationFragment extends Fragment {
    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragment = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_classification, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        tabLayout = getActivity().findViewById(R.id.tl_classification);
        myViewPager = getActivity().findViewById(R.id.vp_classification);

        titles.add("攻略");
        titles.add("单品");

        if (fragment.isEmpty())    //避免重复创建
        {
            fragment.add(new StrategyFragment());
            fragment.add(new SingleProductFragment());
        }


        myViewPager.setAdapter(new FragmentPagerAdapter(getChildFragmentManager()) {
            @Override
            public Fragment getItem(int i) {
                return fragment.get(i);
            }

            @Override
            public int getCount() {
                return fragment.size();
            }

            @Override
            public CharSequence getPageTitle(int i) {
                return titles.get(i);
            }
        });

        tabLayout.setupWithViewPager(myViewPager);
    }
}
