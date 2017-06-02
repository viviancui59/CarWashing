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

        //��ȡ���͵�Intent
        Intent intent = this.getIntent();
        //��ȡIntent�еĲ���
        String messageid = intent.getStringExtra("messageid");

        new DeleteMessageTask(getApplicationContext()).execute(messageid);

        Toast.makeText(getApplicationContext(), "ɾ���ɹ�", Toast.LENGTH_SHORT).show();

        finish();
    }
}
