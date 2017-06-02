package com.example.ty.fragmentdemo;

import ty.app.Myapp;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.DeleteMessageTask;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Window;
import android.widget.Toast;

public class DeleteMessageActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_delete_message);

        Myapp myApp = (Myapp)getApplication();
        
		String url=myApp.getUrl();
        //��ȡ���͵�Intent
        Intent intent = this.getIntent();
        //��ȡIntent�еĲ���
        String messageid = intent.getStringExtra("messageid");

        new DeleteMessageTask(getApplicationContext(),url).execute(messageid);

        Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();

        finish();
    }
}
