package com.example.fragmentdemo;

import ty.entity.User;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.ModifyMyInfoTask;

public class MyInfoActivity extends Activity {

    private User user=new User();
    private TextView txt_phonenum;
    private EditText edt_username;
    private RadioGroup rgp_gender;
    private RadioButton gender_male;
    private RadioButton gender_female;
    private EditText edt_age;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_info);

        //获取各个组件
        txt_phonenum=(TextView)findViewById(R.id.txt_phonenum);
        edt_username=(EditText)findViewById(R.id.edt_username);
        rgp_gender=(RadioGroup)findViewById(R.id.rgp_gender);
        gender_male=(RadioButton)findViewById(R.id.gender_male);
        gender_female=(RadioButton)findViewById(R.id.gender_female);
        edt_age=(EditText) findViewById(R.id.edt_age);

        //从MyApp中获取User
        user.setUserid(1);
        user.setPhonenum("13299999999");
        user.setPassword("123");
        user.setType(1);
        user.setName("amy");
        user.setGender("女");
        user.setAge(12);

        //组件初始化
        txt_phonenum.setText(user.getPhonenum());
        edt_username.setText(user.getName());
        if(user.getGender().equals("男")){
            gender_male.setChecked(true);
        }else if(user.getGender().equals("女")){
            gender_female.setChecked(true);
        }
        edt_age.setText(user.getAge()+"");
    }

    public void modifyMyInfo(View v){
        //获取修改信息
        user.setName(edt_username.getText().toString());
        if(gender_male.getId()==rgp_gender.getCheckedRadioButtonId())
        {
            user.setGender("男");
        }else if(gender_female.getId()==rgp_gender.getCheckedRadioButtonId()){
           user.setGender("女");
        }
        user.setAge(Integer.parseInt(edt_age.getText().toString()));

        //调用异步任务
        new ModifyMyInfoTask(this).execute(Integer.toString(user.getUserid()),user.getName(),user.getGender(),Integer.toString(user.getAge()));
        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();

        //将user赋值给全局变量中的user

    }

    public void back(View v){
        finish();
    }
}