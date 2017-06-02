package com.example.ty.fragmentdemo;

import ty.app.Myapp;
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

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.ModifyMyInfoTask;

public class MyInfoActivity extends Activity {

    private TextView txt_phonenum;
    private EditText edt_username;
    private RadioGroup rgp_gender;
    private RadioButton gender_male;
    private RadioButton gender_female;
    private EditText edt_age;
    private User user=new User();
    private String url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_my_info);

        //��ȡ�������
        txt_phonenum=(TextView)findViewById(R.id.txt_phonenum);
        edt_username=(EditText)findViewById(R.id.edt_username);
        rgp_gender=(RadioGroup)findViewById(R.id.rgp_gender);
        gender_male=(RadioButton)findViewById(R.id.gender_male);
        gender_female=(RadioButton)findViewById(R.id.gender_female);
        edt_age=(EditText) findViewById(R.id.edt_age);

        //��MyApp�л�ȡUser
        Myapp myApp = (Myapp)getApplication();
		user=myApp.getUser();
		url=myApp.getUrl();
		
		
        //�����ʼ��
        txt_phonenum.setText(user.getPhonenum());
        edt_username.setText(user.getName());
        if(user.getGender().equals("��")){
            gender_male.setChecked(true);
        }else if(user.getGender().equals("Ů")){
            gender_female.setChecked(true);
        }
        edt_age.setText(user.getAge()+"");
    }

    public void modifyMyInfo(View v){
        //��ȡ�޸���Ϣ
        user.setName(edt_username.getText().toString());
        if(gender_male.getId()==rgp_gender.getCheckedRadioButtonId())
        {
            user.setGender("��");
        }else if(gender_female.getId()==rgp_gender.getCheckedRadioButtonId()){
           user.setGender("Ů");
        }
        user.setAge(Integer.parseInt(edt_age.getText().toString()));

        //�����첽����
        new ModifyMyInfoTask(this,url).execute(Integer.toString(user.getUserid()),user.getName(),user.getGender(),Integer.toString(user.getAge()));
        Toast.makeText(getApplicationContext(), "�޸ĳɹ�", Toast.LENGTH_SHORT).show();

        //��user��ֵ��ȫ�ֱ����е�user

    }

    public void back(View v){
        finish();
    }
}