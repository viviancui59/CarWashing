package com.example.ty.fragmentdemo;

import ty.app.Myapp;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.LoadBitmapTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

public class GoodsPageActivity extends Activity {

	private ImageView img_goods1;
    private TextView txt_goodsname1;
    private TextView txt_goodsprice1;
    private TextView txt_goodsintro1;
    String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_goods_page);
        
        Myapp myApp = (Myapp)getApplication();
        url=myApp.getUrl()+"image/";

        img_goods1=(ImageView)findViewById(R.id.img_goods1);
        txt_goodsname1=(TextView)findViewById(R.id.txt_goodsname1);
        txt_goodsprice1=(TextView)findViewById(R.id.txt_goodsprice1);
        txt_goodsintro1=(TextView)findViewById(R.id.txt_goodsintro1);

        Intent intent = this.getIntent();
        //获取Intent中的数据包
        Bundle bundle = intent.getExtras();
        //获取数据包中的数据
        int goodsid=bundle.getInt("goodsid");
        String name=bundle.getString("name");
        double price=bundle.getDouble("price");
        int type=bundle.getInt("type");
        String image=bundle.getString("image");
        String intro=bundle.getString("intro");

        new LoadBitmapTask(img_goods1).execute(url+image);
        txt_goodsname1.setText(name);
        txt_goodsprice1.setText("¥"+price);
        txt_goodsintro1.setText(intro);
    }

    public void back(View v){
		finish();
    }

}
