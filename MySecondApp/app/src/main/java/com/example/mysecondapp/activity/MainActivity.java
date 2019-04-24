package com.example.mysecondapp.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import com.example.mysecondapp.R;
import com.example.mysecondapp.fragment.FourFragment;
import com.example.mysecondapp.fragment.OneFragment;
import com.example.mysecondapp.fragment.ThreeFragment;
import com.example.mysecondapp.fragment.TwoFragment;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    private ViewPager myViewPager;
    private TabLayout tabLayout;
    private ArrayList<Fragment> fragment = new ArrayList<>();
    private ArrayList<String> titles = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();

        titles.add("页面1");
        titles.add("页面2");
        titles.add("页面3");
        titles.add("页面4");

        fragment.add(new OneFragment());
        fragment.add(new TwoFragment());
        fragment.add(new ThreeFragment());
        fragment.add(new FourFragment());

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

        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        myViewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {

            }

            @Override
            public void onPageScrollStateChanged(int i) {

            }
        });
    }

    @Override
    public void initView() {
        tabLayout =findViewById(R.id.tabLayout);
        myViewPager = findViewById(R.id.myViewPager);
        tabLayout =findViewById(R.id.tabLayout);
        myViewPager = findViewById(R.id.myViewPager);
    }
}
