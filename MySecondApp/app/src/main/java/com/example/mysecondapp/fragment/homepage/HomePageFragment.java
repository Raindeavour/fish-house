package com.example.mysecondapp.fragment.homepage;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.TextView;

import com.example.mysecondapp.R;

import java.util.ArrayList;


public class HomePageFragment extends Fragment {
    SearchView searchView;
    private TabLayout tabLayout;
    private ViewPager myViewPager;
    private ArrayList<Fragment> fragment = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_homepage, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        searchView = getActivity().findViewById(R.id.sv_one);
        int id = searchView.getContext().getResources().getIdentifier("android:id/search_src_text", null, null);
        TextView textView = searchView.findViewById(id);
        textView.setTextSize(15);//字体、提示字体大小

        myViewPager = getActivity().findViewById(R.id.vp_one);
        tabLayout = getActivity().findViewById(R.id.tl_one);

        titles.add("精选");
        titles.add("送女友");
        titles.add("送同事");
        titles.add("送基友");
        titles.add("送长辈");

        if (fragment.isEmpty()) {
            fragment.add(new ChoiceFragment());
            fragment.add(new SendGirlFFragment());
            fragment.add(new SendColleagueFragment());
            fragment.add(new SendGayFFragment());
            fragment.add(new SendSeniorFragment());
        }
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(0)));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(1)));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(2)));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(3)));
//        tabLayout.addTab(tabLayout.newTab().setText(titles.get(4)));
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
