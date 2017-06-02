package com.example.ty.fragmentdemo;

import ty.app.Myapp;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.OrderlistTabTask;

import android.annotation.SuppressLint;
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

public class OrderlistTabActivity extends Activity {

    private ListView lst_orderlistfuture;
    private int userid;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_orderlist_tab);

        Myapp myApp = (Myapp)getApplication();
		userid=myApp.getUser().getUserid();
		url=myApp.getUrl();

		lst_orderlistfuture=(ListView)findViewById(R.id.lst_orderlistfuture);

		//调用异步任务显示listview 传入getCurrentTab()
        new OrderlistTabTask(this,lst_orderlistfuture,url).execute("1",Integer.toString(userid));
    }
    
    @Override
    protected void onResume() {
    	// TODO Auto-generated method stub
    	super.onResume();
    	//调用异步任务显示listview 传入getCurrentTab()
        new OrderlistTabTask(this,lst_orderlistfuture,url).execute("1",Integer.toString(userid));
    }
}
