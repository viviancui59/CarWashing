package com.example.fragmentdemo;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.view.Window;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.AddFeedbackTask;

public class AddFeedbackActivity extends Activity {

    private TextView txt_feedback;

    //userid从MyApp中获取
    private int userid=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_add_feedback);


        txt_feedback=(TextView)findViewById(R.id.txt_feedback);

}

    public void addFeedback(View v){
        String feedbackContent=txt_feedback.getText().toString();
        new AddFeedbackTask(this).execute(feedbackContent,Integer.toString(userid));
        Toast.makeText(getApplicationContext(), "发送成功", Toast.LENGTH_SHORT).show();

    }
    public void back(View v){
        finish();
    }
}
