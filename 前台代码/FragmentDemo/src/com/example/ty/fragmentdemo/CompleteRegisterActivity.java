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

import com.example.ty.fragmentdemo.R;
import com.google.gson.Gson;

import ty.app.Myapp;
import ty.entity.User;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;


public class CompleteRegisterActivity extends Activity {

	//private EditText edtRegisterPlate;
	private Spinner spnGender;
	private EditText edtRegisterAge;
	
	private String userphonenum;
	private String password;
	private String username;
	
	private String usergender;
	
	private String url;
	public User user=new User();
	private String userJSON;
	private Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_complete_register);
		
		Myapp myapp=(Myapp)getApplication();
		url=myapp.getUrl()+ "UserRegisterServlet";;

		//edtRegisterPlate=(EditText) findViewById(R.id.edtRegisterPlate);
		spnGender=(Spinner) findViewById(R.id.spnGender);
		edtRegisterAge=(EditText) findViewById(R.id.edtRegisterAge);
		
		Intent intent = this.getIntent();

		//鑾峰彇Intent涓殑鍙傛暟
		userphonenum = intent.getStringExtra("userphonenum");
		password = intent.getStringExtra("password");
	//	username = intent.getStringExtra("username");
		usergender="";
		
		handler=new Handler(){
			public void handleMessage(Message msg){
				//鎺ユ敹娑堟伅
				super.handleMessage(msg);
				if(msg.what==1){
					
					//鍒涘缓Intent
					Gson gson = new Gson();	
					
					user = gson.fromJson(userJSON, User.class);
					
					if(user!=null){   //娉ㄥ唽鎴愬姛
						
					Myapp myApp = (Myapp)getApplication();
					myApp.setUser(user);
					
					Intent intent = new Intent(CompleteRegisterActivity.this,MainActivity.class);
					 
					startActivity(intent);
					finish();
					
					}else{
						
						Toast.makeText(getApplicationContext(), "注册失败，请重新注册", Toast.LENGTH_SHORT).show();
						
						Intent intent = new Intent(CompleteRegisterActivity.this,RegisterActivity.class);
						 
						startActivity(intent);
						finish();
					}
				}else{
					//鍒ゆ柇鏄惁鎺ユ敹鍒颁俊鎭�
					Toast.makeText(getApplicationContext(), "连接失败", Toast.LENGTH_SHORT).show();
					
				}
			}
		};
		
	}
	

	
	private class PostRunner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			  HttpClient client = new DefaultHttpClient();

			  HttpPost request = new HttpPost(url);
			 
			  user.setPhonenum(userphonenum);
			  user.setPassword(password);
			  user.setName(username);
			  
			  user.setGender(usergender);
			  user.setAge(Integer.parseInt(edtRegisterAge.getText().toString()));
			 // String carid=edtRegisterPlate.getText().toString();
			 
			  //user.setCar(car);
			 
			  //杞﹁締淇℃伅
	
			  List<NameValuePair> params = new ArrayList<NameValuePair>();
			  params.add(new BasicNameValuePair("userphonenum",user.getPhonenum()));
			  params.add(new BasicNameValuePair("password",user.getPassword()));
			  params.add(new BasicNameValuePair("username",user.getName()));
			  params.add(new BasicNameValuePair("usergender",user.getGender()));
			  params.add(new BasicNameValuePair("userage",user.getAge()+""));
			 // params.add(new BasicNameValuePair("username",user.getName()));
		
			  
			  try {
				request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			   try {
				HttpResponse response = client.execute(request);
				
				
				  if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				
					
				  userJSON=EntityUtils.toString(response.getEntity());
				  Log.i("LoginActivityaaa", userJSON+"jjjj");
				  
				  //Toast.makeText(getApplicationContext(), "鎴愬姛", Toast.LENGTH_SHORT).show();
					
					
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
			}
		}
		
	}
	
	public void completeregister(View v){
		if(edtRegisterAge.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "年龄不能为空", Toast.LENGTH_SHORT).show();
		}
		else{
		usergender = spnGender.getSelectedItem().toString();
		new Thread(new PostRunner()).start();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.complete_register, menu);
		return true;
	}


	

}
