package com.example.mysecondapp.fragment.list;

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
import com.example.mysecondapp.fragment.homepage.ChoiceFragment;
import com.example.mysecondapp.fragment.homepage.SendColleagueFragment;
import com.example.mysecondapp.fragment.homepage.SendGayFFragment;
import com.example.mysecondapp.fragment.homepage.SendGirlFFragment;
import com.example.mysecondapp.fragment.homepage.SendSeniorFragment;

import java.util.ArrayList;

public class ListFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager myViewPager;
    private ArrayList<Fragment> fragment = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_list,null);
        return  view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        myViewPager = getActivity().findViewById(R.id.vp_list);
        tabLayout = getActivity().findViewById(R.id.tl_list);

        titles.add("走心礼盒区");
        titles.add("礼物TOP100");
        titles.add("新星榜");
        titles.add("每日推荐");

        if (fragment.isEmpty()) {
            fragment.add(new CordialBoxAreaFragment());
            fragment.add(new GiftTopHundredFragment());
            fragment.add(new NovaListFragment());
            fragment.add(new DailyRecommendationFragment());
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
