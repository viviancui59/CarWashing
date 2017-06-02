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

public class GoodsTabElecActivity extends Activity {
	
    private ListView lst_goodelec;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goods_tab_elec);
        
        
        Myapp myApp = (Myapp)getApplication();
        
		url=myApp.getUrl();

		lst_goodelec=(ListView)findViewById(R.id.lst_goodselec);


        //调用异步任务显示listview 传入getCurrentTab()
        new GoodsTabTask(this,lst_goodelec,url).execute("3");

    }

}