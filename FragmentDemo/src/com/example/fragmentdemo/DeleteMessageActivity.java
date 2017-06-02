package com.example.fragmentdemo;

import com.example.task.DeleteMessageTask;

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

        //获取发送的Intent
        Intent intent = this.getIntent();
        //获取Intent中的参数
        String messageid = intent.getStringExtra("messageid");

        new DeleteMessageTask(getApplicationContext()).execute(messageid);

        Toast.makeText(getApplicationContext(), "删除成功", Toast.LENGTH_SHORT).show();

        finish();
    }
}
