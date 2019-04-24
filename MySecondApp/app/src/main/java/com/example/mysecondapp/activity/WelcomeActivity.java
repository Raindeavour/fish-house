package com.example.mysecondapp.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import com.example.mysecondapp.R;
import com.squareup.picasso.Picasso;


public class WelcomeActivity extends Activity implements  Runnable {

    Button skipButton;
    ImageView imageView;

    final Handler handler = new Handler();
    Runnable runnable = new Runnable() {
        @Override
        public void run() {
          startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.avtivity_welcome);

        imageView= findViewById(R.id.img_welcome);
        String url ="https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1556009207307&di=80d409919c1f0eade760bcd35f76eb7e&imgtype=0&src=http%3A%2F%2Fimg.zcool.cn%2Fcommunity%2F012a55556bd67a0000009fcb2d9aeb.jpg%401280w_1l_2o_100sh.png";
        Picasso.get().load(url).placeholder(R.mipmap.img_load).into(imageView);        //一定在Manifest添加访问网络权限，不然加载不出来

        handler.postDelayed(runnable,5000);
        skipButton = findViewById(R.id.skipButton);
        skipButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(WelcomeActivity.this,LoginActivity.class));
                handler.removeCallbacks(runnable);
                finish();
            }
        });
    }

    @Override
    public void run() {

    }
}
