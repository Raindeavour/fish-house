package com.example.mysecondapp.fragment.homepage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.example.mysecondapp.R;
import com.example.mysecondapp.interfaces.HomePageAPI;
import com.example.mysecondapp.adapter.ChoiceXRVAdapter;
import com.example.mysecondapp.bean.HomePageBean;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.ProgressStyle;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.youth.banner.Banner;
import com.youth.banner.BannerConfig;
import com.youth.banner.listener.OnBannerListener;
import com.youth.banner.loader.ImageLoader;

import java.util.ArrayList;
import java.util.List;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ChoiceFragment extends Fragment {

    XRecyclerView choiceXRV;
    ChoiceXRVAdapter choiceXRVAdapter;
    List<String> data = new ArrayList<>();
    List<String> introduction = new ArrayList<>();
    List<String> cover_image_url = new ArrayList<>();
    List<String> title = new ArrayList<>();
    List<String> column_title = new ArrayList<>();
    List<Integer> likes_count = new ArrayList<>();
    List<Boolean> liked = new ArrayList<>();
    String str1;

    private Banner mBanner;
    private ArrayList<String> images;
    private ArrayList<String> imageTitle;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_homepage_choice, null);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        choiceXRV = (XRecyclerView) ((getActivity()).findViewById(R.id.xRV_choice));
        choiceXRVAdapter = new ChoiceXRVAdapter(getContext(), cover_image_url, title, column_title, introduction, likes_count, liked);
        choiceXRV.setLayoutManager(new LinearLayoutManager(getContext()));
        choiceXRV.setAdapter(choiceXRVAdapter);
        // 可以设置是否开启加载更多/下拉刷新
        //  choiceXRV.setLoadingMoreEnabled(true);
        choiceXRV.setLoadingMoreEnabled(false);
        choiceXRV.setRefreshProgressStyle(ProgressStyle.BallZigZag); //设定下拉刷新样式
        choiceXRV.setLoadingMoreProgressStyle(ProgressStyle.BallZigZag);//设定上拉加载样式
        //     initData();   //初始化数据

        //添加头部
        LayoutInflater headerInflater = LayoutInflater.from(getContext());
        View headerView = headerInflater.inflate(R.layout.homepage_choice_xrv_header, null);
        choiceXRV.addHeaderView(headerView);

        mBanner = (Banner) ((headerView).findViewById(R.id.banner));

        initBannerData();
        initBanner();

//        textView = (TextView)findViewById(R.id.textView);       //加入xRecyclerView、注释v7... 强转必须加上，否则报错

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://api.liwushuo.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();


        HomePageAPI homePageAPI = retrofit.create(HomePageAPI.class);

//        Call<ResponseBody> callShops = homePageAPI.getHomePage("2", "1", "1", "20", "0");
        Call<ResponseBody> callShops = homePageAPI.getHomePage();

        callShops.enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    Log.e("response", response.toString());
                    str1 = new String(response.body().bytes());//把原始数据转为字符串
                    //       String str1 = response.body().bytes().toString();     //toStrng方法默认返回当前对象的内存地址,即hashCode;new String返回的是真实的值
                    //              textView.setText(str1);
                    //   data.add(str1);
                    //     List<HomePageBean> homePageBean = gson.fromJson((new String(response.body().bytes())),new TypeToken<<List<HomePageBean>>>().getType());
                    HomePageBean homePageBean = new Gson().fromJson(str1, HomePageBean.class);
                    List<HomePageBean.Data.Items> items = homePageBean.getData().getItems();
                    for (int i = 1; i < items.size()-1; i++) {
                        cover_image_url.add(items.get(i).getCover_image_url());
                        title.add(items.get(i).getTitle());
                        column_title.add(items.get(i).getColumn().getTitle());
                        introduction.add(items.get(i).getIntroduction());
                        likes_count.add(items.get(i).getLikes_count());
                        liked.add(items.get(i).getLiked());
                    }
                    //刷新页面
                    choiceXRVAdapter.notifyDataSetChanged();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                Log.e("failure", "fail");
            }
        });

        choiceXRV.setLoadingListener(new XRecyclerView.LoadingListener() {
            //下拉刷新监听
            @Override
            public void onRefresh() {
                choiceXRV.refreshComplete();
            }


            //上拉加载监听
            @Override
            public void onLoadMore() {
            }
        });
    }

    private void initBanner() {
     //   mBanner.setLayoutParams(new RecyclerView.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        //设置样式,默认为:Banner.NOT_INDICATOR(不显示指示器和标题)
        //可选样式如下:
        //1. Banner.CIRCLE_INDICATOR    显示圆形指示器
        //2. Banner.NUM_INDICATOR   显示数字指示器
        //3. Banner.NUM_INDICATOR_TITLE 显示数字指示器和标题
        //4. Banner.CIRCLE_INDICATOR_TITLE  显示圆形指示器和标题
        //设置banner样式
        mBanner.setBannerStyle(BannerConfig.CIRCLE_INDICATOR_TITLE);
        //设置图片加载器
        mBanner.setImageLoader(new GlideImageLoader());
        //设置标题集合（当banner样式有显示title时）
        mBanner.setBannerTitles(imageTitle);
        //设置轮播样式（没有标题默认为右边,有标题时默认左边）
        //可选样式:
        //Banner.LEFT   指示器居左
        //Banner.CENTER 指示器居中
        //Banner.RIGHT  指示器居右
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        //设置是否允许手动滑动轮播图
        mBanner.setViewPagerIsScroll(true);
        //设置是否自动轮播（不设置则默认自动）
        mBanner.isAutoPlay(true);
        //设置轮播图片间隔时间（不设置默认为2000）
        mBanner.setDelayTime(3000);
        //设置图片资源:可选图片网址/资源文件，默认用Glide加载,也可自定义图片的加载框架
        //所有设置参数方法都放在此方法之前执行
        mBanner.setIndicatorGravity(BannerConfig.CENTER);
        mBanner.setImages(images)
                .setOnBannerListener(new OnBannerListener() {
                    @Override
                    public void OnBannerClick(int position) {
                        Toast.makeText(getContext(), "你点了第" + (position + 1) + "张轮播图", Toast.LENGTH_SHORT).show();
                    }
                }).start();

    }

    private void initBannerData() {
        //设置图片资源:url或本地资源
        images = new ArrayList<>();
        images.add("http://img.zcool.cn/community/0166c756e1427432f875520f7cc838.jpg");
        images.add("http://img.zcool.cn/community/018fdb56e1428632f875520f7b67cb.jpg");
        images.add("http://img.zcool.cn/community/01c8dc56e1428e6ac72531cbaa5f2c.jpg");
        images.add("http://img.zcool.cn/community/01fda356640b706ac725b2c8b99b08.jpg");
        images.add("http://img.zcool.cn/community/01fd2756e142716ac72531cbf8bbbf.jpg");
        images.add("http://img.zcool.cn/community/0114a856640b6d32f87545731c076a.jpg");
        //设置图片标题:自动对应
        imageTitle = new ArrayList<>();
        imageTitle.add("十大星级品牌联盟，全场2折起");
        imageTitle.add("嗨购5折不要停");
        imageTitle.add("双12趁现在");
        imageTitle.add("嗨购5折不要停，12.12趁现在");
        imageTitle.add("实打实大优惠");
        imageTitle.add("买到就是赚到");

    }

    /**
     * 网络加载图片
     * 使用了Glide图片加载框架
     */
    public class GlideImageLoader extends ImageLoader {
        @Override
        public void displayImage(Context context, Object path, ImageView imageView) {
            Glide.with(context)
                    .load((String) path)
                    .into(imageView);
        }

    }

}
