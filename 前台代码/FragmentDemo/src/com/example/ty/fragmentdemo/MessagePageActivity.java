package com.example.ty.fragmentdemo;

import ty.app.Myapp;
import ty.entity.Message;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.ReadMessageTask;

public class MessagePageActivity extends Activity {

    private Message message;
    private TextView txt_msgtype2;
    private TextView txt_msgtime2;
    private ImageView img_msgcontent;
    private String url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_message_page);
        
        Myapp myapp=(Myapp)getApplication();
        url=myapp.getUrl();
        

        txt_msgtype2=(TextView)findViewById(R.id.txt_msgtype2);
        txt_msgtime2=(TextView)findViewById(R.id.txt_msgtime2);
        img_msgcontent=(ImageView)findViewById(R.id.img_msgcontent);

        //��ȡmessage
        Intent intent = this.getIntent();
        message = (Message) intent.getSerializableExtra("message");

        //�ڿؼ�����ʾ
        txt_msgtime2.setText(message.getTime().substring(0,16));
        if(message.getType()==1){
            //��������
            txt_msgtype2.setText("��������");
            img_msgcontent.setImageResource(R.drawable.msgtype1);
        }else if(message.getType()==2){
            //ϵͳ��ʾ
            txt_msgtype2.setText("ϵͳ��ʾ");
            img_msgcontent.setImageResource(R.drawable.msgtype2);
        }

        //��message����
        new ReadMessageTask(getApplicationContext(),url).execute(message.getMessageid()+"");
    }

    public void back(View v){
    	
		finish();
    }
}
