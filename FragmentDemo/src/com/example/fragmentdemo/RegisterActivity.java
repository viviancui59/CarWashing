package com.example.fragmentdemo;

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
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;

public class RegisterActivity extends Activity {
	

	private EditText edtRegisterPhone;
	private EditText edtRegisterPassword;
	private EditText edtRegisterName;
	private String url = "http://10.0.2.2:8080/CarWashing/UserRegisterServlet";
	public User user=new User();
	private String userJSON;
	private Handler handler;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
	
		edtRegisterPhone=(EditText) findViewById(R.id.edtRegisterPhone);
		edtRegisterPassword=(EditText) findViewById(R.id.edtRegisterPassword);
		edtRegisterName=(EditText) findViewById(R.id.edtRegisterName);
		
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
					
					Intent intent = new Intent(RegisterActivity.this,CompleteRegisterActivity.class);
					 
					startActivity(intent);
					finish();
					
					}else{
						
						Toast.makeText(getApplicationContext(), "璇ョ敤鎴峰凡娉ㄥ唽锛岃閲嶆柊杈撳叆", Toast.LENGTH_SHORT).show();
					}
				}else{
					//鍒ゆ柇鏄惁鎺ユ敹鍒颁俊鎭�
					Toast.makeText(getApplicationContext(), "杩炴帴鏈嶅姟鍣ㄥけ璐�", Toast.LENGTH_SHORT).show();
					
				}
			}
		};
	}


	public void registernext(View v){
		
		if(edtRegisterPhone.getText().toString().equals("")||edtRegisterPassword.getText().toString().equals("")||edtRegisterName.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "鎵�鏈変俊鎭潎涓嶈兘涓虹┖", Toast.LENGTH_SHORT).show();
		}
		else{
		Intent intent = new Intent(RegisterActivity.this, CompleteRegisterActivity.class);

		intent.putExtra("userphonenum", edtRegisterPhone.getText().toString()); 
		intent.putExtra("password", edtRegisterPassword.getText().toString());
		intent.putExtra("username", edtRegisterName.getText().toString());

		startActivity(intent);
		
		finish();
		}
		/*if(edtRegisterPhone.getText().toString().equals("")||edtRegisterPassword.getText().toString().equals("")||edtRegisterName.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "鎵�鏈変俊鎭潎涓嶈兘涓虹┖", Toast.LENGTH_SHORT).show();
		}
		else{
			new Thread(new PostRunner()).start();
		}*/
		
	}
	

	public void registerback(View v){
		Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
		 
		startActivity(intent);
		finish();
	}
	
	private class PostRunner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			
			  HttpClient client = new DefaultHttpClient();

			  HttpPost request = new HttpPost(url);
			 
			  user.setPhonenum(edtRegisterPhone.getText().toString());
			  user.setPassword(edtRegisterPassword.getText().toString());
			  user.setName(edtRegisterName.getText().toString());
			  
	
			  List<NameValuePair> params = new ArrayList<NameValuePair>();
			  params.add(new BasicNameValuePair("userphonenum",user.getPhonenum()));
			  params.add(new BasicNameValuePair("password",user.getPassword()));
			  params.add(new BasicNameValuePair("username",user.getName()));
			
			  
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
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}


}
