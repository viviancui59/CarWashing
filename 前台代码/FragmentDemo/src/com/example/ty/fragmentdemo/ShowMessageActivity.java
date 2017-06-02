package com.example.ty.fragmentdemo;

import ty.app.Myapp;
import ty.entity.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ListView;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.ShowMessageTask;

public class ShowMessageActivity extends Activity {

    private ListView lst_message;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_show_message);

        lst_message=(ListView)findViewById(R.id.lst_message);

        Myapp myApp = (Myapp)getApplication();
		User user=myApp.getUser();
		String url=myApp.getUrl();

        new ShowMessageTask(this,lst_message,url).execute(user.getUserid()+"");
    }

    public void back(View v){
    	Intent intent = new Intent(ShowMessageActivity.this,MainActivity.class);
		 
		startActivity(intent);
        finish();
    }
}
