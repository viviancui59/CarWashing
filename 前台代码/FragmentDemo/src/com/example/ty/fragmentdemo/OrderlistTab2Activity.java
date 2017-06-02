package com.example.ty.fragmentdemo;

import ty.app.Myapp;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.OrderlistTabTask;

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

public class OrderlistTab2Activity extends Activity {

	private ListView lst_orderlistfinished;
    private int userid;
    private String url;
    
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_orderlist_tabfinished);
		
		Myapp myApp = (Myapp)getApplication();
		userid=myApp.getUser().getUserid();
		url=myApp.getUrl();

		lst_orderlistfinished=(ListView)findViewById(R.id.lst_orderlistfinished);

		//调用异步任务显示listview 传入getCurrentTab()
        new OrderlistTabTask(this,lst_orderlistfinished,url).execute("2",Integer.toString(userid));
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		//调用异步任务显示listview 传入getCurrentTab()
        new OrderlistTabTask(this,lst_orderlistfinished,url).execute("2",Integer.toString(userid));
	
	}

}
