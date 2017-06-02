package com.example.fragmentdemo;

import ty.entity.User;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.example.task.ShowMessageTask;

public class ShowMessageActivity extends Activity {

    private ListView lst_message;
    private User user=new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_message);

        lst_message=(ListView)findViewById(R.id.lst_message);

        //从全局变量中获取user
        user.setUserid(1);

        new ShowMessageTask(this,lst_message).execute(user.getUserid()+"");
    }

    public void back(View v){
        finish();
    }
}
