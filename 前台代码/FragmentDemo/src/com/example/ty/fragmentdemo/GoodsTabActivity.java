package com.example.ty.fragmentdemo;

import ty.app.Myapp;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.GoodsTabTask;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class GoodsTabActivity extends Activity {
	
    private ListView lst_goodsall;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_tab);
        
        
        Myapp myApp = (Myapp)getApplication();
        
		url=myApp.getUrl();

		lst_goodsall=(ListView)findViewById(R.id.lst_goodsall);


        //调用异步任务显示listview 传入getCurrentTab()
        new GoodsTabTask(this,lst_goodsall,url).execute("0");

    }

}