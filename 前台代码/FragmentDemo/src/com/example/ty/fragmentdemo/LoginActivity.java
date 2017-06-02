package com.example.ty.fragmentdemo;



import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import ty.app.Myapp;
import ty.entity.User;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.ty.fragmentdemo.R;
import com.google.gson.Gson;

public class LoginActivity extends Activity {

	
	private EditText edtPassword;
	private EditText edtUserphone;
	
	private String password;
	private String userphone;
	
	private Button btnlogin;
	
	private String userJSON;
	
	private Handler handler;
	private ProgressDialog pdialog;
	
	String url;
	
	
//	private String url = "http://10.134.161.200:8080/CarWashing/UserLoginServlet";
	
	public User user=new User();
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_login);
		
		Myapp myApp = (Myapp)getApplication();
		url=myApp.getUrl()+"UserLoginServlet";
		
		edtPassword=(EditText) findViewById(R.id.edtPassword);
		edtUserphone=(EditText) findViewById(R.id.edtUserphone);
		btnlogin=(Button) findViewById(R.id.btnlogin);
		

		StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
        .detectDiskReads()
        .detectDiskWrites()
        .detectNetwork()   // or .detectAll() for all detectable problems
        .penaltyLog()
        .build());
StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
        .detectLeakedSqlLiteObjects()
        .detectLeakedClosableObjects()
        .penaltyLog()
.penaltyDeath()
.build());

		/*if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}*/
		
		handler=new Handler(){
			public void handleMessage(Message msg){
				//������Ϣ
				super.handleMessage(msg);

				pdialog.dismiss();
				//��ٽ�����Ի���
				
				if(msg.what==1){
					
					//����Intent
					Gson gson = new Gson();	
					System.out.print(userJSON);
					user = gson.fromJson(userJSON, User.class);
				
//					Log.i("User", user.toString());
					if(user!=null){   //��¼�ɹ�
						
					Log.i("UserLogin", user.getCar().toString());
					Myapp myApp = (Myapp)getApplication();
					myApp.setUser(user);
					
					
					Intent intent = new Intent(LoginActivity.this,MainActivity.class);//////
					 
					startActivity(intent);
					finish();
					
					}else{
						//�ж���ݿ����Ƿ�����û���Ϣ
						//pdialog.dismiss();
						Toast.makeText(getApplicationContext(), "手机号或密码输入错误", Toast.LENGTH_SHORT).show();
					}
				}else{
					//�ж��Ƿ���յ���Ϣ
					//pdialog.dismiss();
					Toast.makeText(getApplicationContext(), "登录不成功，请重试", Toast.LENGTH_SHORT).show();
					
				}
			}
		};
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.login, menu);
		return true;
	}



    
	public void login(View v){

		user=new User();
		pdialog=new ProgressDialog(this);
		pdialog.setMessage("正在登陆.....");
		pdialog.show();
		password=edtPassword.getText().toString();
		userphone=edtUserphone.getText().toString();
		new Thread(new PostRunner()).start();
	}
	

	public void register(View v){
		
		 
		Intent intent = new Intent(LoginActivity.this,RegisterActivity.class);
		 
		startActivity(intent);
		finish();
	}
	

	public void forgetpassword(View v){
		
	}
	

	private class PostRunner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub

			  
			  HttpClient client = new DefaultHttpClient();

			  HttpPost request = new HttpPost(url);
			  
			  Log.i("LoginActivity", "1 ");
			  Log.i("LoginActivity", "2 ");
			  Log.i("LoginActivity", "3 ");
			  //System.out.println(user.getUsername());////
			  List<NameValuePair> params = new ArrayList<NameValuePair>();
			  Log.i("LoginActivity", "4 ");
			  params.add(new BasicNameValuePair("userphonenum",userphone));
			  params.add(new BasicNameValuePair("password",password));
			
			  Log.i("LoginActivity", "5 ");
			  try {
				request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
				  Log.i("LoginActivity", "6 ");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			   try {
				HttpResponse response = client.execute(request);
				  Log.e("response======", "==" + response);
				  Log.i("LoginActivity", "7 ");
				  //pdialog.dismiss();
				  if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					
					  Log.i("LoginActivity", "8 "); 
				  userJSON=EntityUtils.toString(response.getEntity());
				  Log.i("userJSON", userJSON); 
				  handler.sendEmptyMessage(1);
				
				    }
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				
			}
		}
		
	}
	
}
