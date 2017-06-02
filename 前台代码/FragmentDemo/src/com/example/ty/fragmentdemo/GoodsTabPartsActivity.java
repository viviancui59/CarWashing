package com.example.ty.fragmentdemo;

import ty.app.Myapp;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.GoodsTabTask;

import android.app.Activity;
import android.app.ActionBar;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.os.Build;

public class GoodsTabPartsActivity extends Activity {

    private ListView lst_goodeparts;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_tab_parts);
        
        
        Myapp myApp = (Myapp)getApplication();
        
		url=myApp.getUrl();

		lst_goodeparts=(ListView)findViewById(R.id.lst_goodsparts);


		System.out.println("GoodsParts");
        //调用异步任务显示listview 传入getCurrentTab()
        new GoodsTabTask(this,lst_goodeparts,url).execute("1");

    }

}