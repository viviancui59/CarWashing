package com.example.fragmentdemo;

import ty.entity.Message;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.task.ReadMessageTask;

public class MessagePageActivity extends Activity {

    private Message message;
    private TextView txt_msgtype2;
    private TextView txt_msgtime2;
    private ImageView img_msgcontent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_message_page);

        txt_msgtype2=(TextView)findViewById(R.id.txt_msgtype2);
        txt_msgtime2=(TextView)findViewById(R.id.txt_msgtime2);
        img_msgcontent=(ImageView)findViewById(R.id.img_msgcontent);

        //获取message
        Intent intent = this.getIntent();
        message = (Message) intent.getSerializableExtra("message");

        //在控件中显示
        txt_msgtime2.setText(message.getTime().substring(0,16));
        if(message.getType()==1){
            //服务提醒
            txt_msgtype2.setText("服务提醒");
            img_msgcontent.setImageResource(R.drawable.msgtype1);
        }else if(message.getType()==2){
            //系统提示
            txt_msgtype2.setText("系统提示");
            img_msgcontent.setImageResource(R.drawable.msgtype2);
        }

        //读message任务
        new ReadMessageTask(getApplicationContext()).execute(message.getMessageid()+"");
    }

    public void back(View v){
        finish();
    }
}
