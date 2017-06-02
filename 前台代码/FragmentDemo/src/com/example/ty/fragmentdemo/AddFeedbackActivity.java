package com.example.ty.fragmentdemo;

import ty.app.Myapp;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.AddFeedbackTask;

public class AddFeedbackActivity extends Activity {

    private TextView txt_feedback;

    //userid��MyApp�л�ȡ
    private int userid;
    private String url;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_feedback);

        Myapp myApp = (Myapp)getApplication();
        
		userid=myApp.getUser().getUserid();
		url=myApp.getUrl();
		
        txt_feedback=(TextView)findViewById(R.id.txt_feedback);

}

    public void addFeedback(View v){
        String feedbackContent=txt_feedback.getText().toString();
        new AddFeedbackTask(this,url).execute(feedbackContent,Integer.toString(userid));
        Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();

    }
    public void back(View v){
    	Intent intent = new Intent(AddFeedbackActivity.this,MainActivity.class);
		 
		startActivity(intent);
		finish();
    }
}
