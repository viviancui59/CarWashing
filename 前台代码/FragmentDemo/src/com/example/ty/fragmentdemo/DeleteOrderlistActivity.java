package com.example.ty.fragmentdemo;

import ty.app.Myapp;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.DeleteOrderlistTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class DeleteOrderlistActivity extends Activity {

	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_delete_orderlist);

        Myapp myApp = (Myapp)getApplication();
        
		String url=myApp.getUrl();
		
        //获取发送的Intent
        Intent intent = this.getIntent();
        //获取Intent中的参数
        String orderlistid = intent.getStringExtra("orderlistid");

        new DeleteOrderlistTask(getApplicationContext(),url).execute(orderlistid);

        Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();

        finish();
    }

}
