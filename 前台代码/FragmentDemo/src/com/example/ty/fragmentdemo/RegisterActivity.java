package com.example.ty.fragmentdemo;

import ty.app.Myapp;
import ty.entity.User;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;

import com.example.ty.fragmentdemo.R;
import com.google.gson.Gson;

public class RegisterActivity extends Activity {
	

	private EditText edtRegisterPhone;
	private EditText edtRegisterPassword;
	private EditText edtRegisterCode;
	public User user=new User();
	private String userJSON;
	private Handler handler;
	private String url;
	private Button btnGetCode;
	int i = 60;
	private boolean Code=false;
	private boolean res=false;
int event;
	
	int result;
	Object data;
	private Handler handlerrr;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		enableStrictMode(this);
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_register);
		
		Myapp myapp=(Myapp)getApplication();
		url=myapp.getUrl()+"UserRegisterServlet";
		btnGetCode=(Button) findViewById(R.id.btnGetCode);
		edtRegisterPhone=(EditText) findViewById(R.id.edtRegisterPhone);
		edtRegisterPassword=(EditText) findViewById(R.id.edtRegisterPassword);
		edtRegisterCode=(EditText) findViewById(R.id.edtRegisterCode);
		SMSSDK.initSDK(this, "1e4f8ef92ff9e", "458f74b137f37534b889209dc3d266ee");
		init();
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
						
						Toast.makeText(getApplicationContext(), "该用户已注册，请重新输入", Toast.LENGTH_SHORT).show();
					}
				}else{
					//鍒ゆ柇鏄惁鎺ユ敹鍒颁俊鎭�
					Toast.makeText(getApplicationContext(), "连接服务器失败", Toast.LENGTH_SHORT).show();
					
				}
			}
		};
	}
void init(){
		
		EventHandler eventHandler = new EventHandler(){
			@Override
			public void afterEvent(int event, int result, Object data) {
				Message msg = new Message();
				msg.arg1 = event;
				msg.arg2 = result;
				msg.obj = data;
				handlerr.sendMessage(msg);
			}
		};
		//ע��ص������ӿ�
		SMSSDK.registerEventHandler(eventHandler);
	}
	public void RequestCode(View v){
		String phoneNums = edtRegisterPhone.getText().toString();
		
		Log.e("event", phoneNums);
		if (!judgePhoneNums(phoneNums)) {
			return;
		}
		SMSSDK.getVerificationCode("86", phoneNums);
		btnGetCode.setClickable(false);
		btnGetCode.setText("正在发送(" + i + ")");
		new Thread(new CodeRunnable()).start();
	}
private class CodeRunnable implements Runnable{
		
		

		@Override
		public void run() {
			// TODO Auto-generated method stub
			for (; i > 0; i--) {
				handlerr.sendEmptyMessage(-9);
				if (i <= 0) {
					break;
				}
				try {
					Thread.sleep(1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			handlerr.sendEmptyMessage(-8);
		}
		
	}
	private boolean judgePhoneNums(String phoneNums) {
		if (isMatchLength(phoneNums, 11)
				&& isMobileNO(phoneNums)) {
			return true;
		}
		Toast.makeText(this, "电话号码长度不是11位",Toast.LENGTH_SHORT).show();
		return false;
	}
	public static boolean isMobileNO(String mobileNums) {
		
		String telRegex = "[1][358]\\d{9}";// "[1]"�����1λΪ����1��"[358]"����ڶ�λ����Ϊ3��5��8�е�һ����"\\d{9}"��������ǿ�����0��9�����֣���9λ��
		if (TextUtils.isEmpty(mobileNums))
			return false;
		else
			return mobileNums.matches(telRegex);
	}
	public static boolean isMatchLength(String str, int length) {
		if (str.isEmpty()) {
			return false;
		} else {
			return str.length() == length ? true : false;
		}
	}
	Handler handlerr = new Handler() {
		public void handleMessage(Message msg) {
			if (msg.what == -9) {
				btnGetCode.setText("重新发送(" + i + ")");
			} else if (msg.what == -8) {
				btnGetCode.setText("获取验证码");
				btnGetCode.setClickable(true);
				i = 60;
			} else {
				 event = msg.arg1;
				 result = msg.arg2;
				 data = msg.obj;
				Log.e("event", "event=" + event);
				Log.e("event", "result=" + result);
				Log.e("event", "result=" + data);
				
					
						// TODO Auto-generated method stub
						if (result == SMSSDK.RESULT_COMPLETE) {
					// 短信注册成功后，返回MainActivity,然后提示新好友  
					if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {// 提交验证码成功 
						Toast.makeText(getApplicationContext(), "提交验证码成功",
								Toast.LENGTH_SHORT).show();
						Code=true;
						
						/*Intent intent = new Intent(LoginActivity.this,
								MainActivity.class);
						startActivity(intent);*/
					} else if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
						Toast.makeText(getApplicationContext(), "验证码已经发送",
								Toast.LENGTH_SHORT).show();
						
					} else {
						((Throwable) data).printStackTrace();
					
					}
					if(res){
						if(event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE&&result == SMSSDK.RESULT_COMPLETE)
						{
							Intent intent = new Intent(RegisterActivity.this, CompleteRegisterActivity.class);

							intent.putExtra("userphonenum", edtRegisterPhone.getText().toString()); 
							intent.putExtra("password", edtRegisterPassword.getText().toString());
						//	intent.putExtra("username", edtRegisterName.getText().toString());

							startActivity(intent);
							
							finish();
						//new Thread(new PostRunner()).start();
						}
						else
						{
							Toast.makeText(getApplicationContext(), "验证码验证失败", Toast.LENGTH_SHORT).show();
							res=false;
						}		
					}				
			}
						else
						{
							((Throwable) data).printStackTrace();
						}
		}
	}
	};
	@Override
	protected void onDestroy() {
		SMSSDK.unregisterAllEventHandler();
		super.onDestroy();
	}
	
	
	
	/*public void rigester(View v){
		String phoneNums = edtRegisterPhone.getText().toString();
		res=true;
		SMSSDK.submitVerificationCode("86", phoneNums, edtRegisterCode
				.getText().toString());
		
		
			//userType = spnType.getSelectedItem().toString();
			//new Thread(new PostRunner()).start();
			//Toast.makeText(getApplicationContext(), "成功1", Toast.LENGTH_SHORT).show();
			
		}
	*/
public void registernext(View v){
	
		/*if(edtRegisterPhone.getText().toString().equals("")||edtRegisterPassword.getText().toString().equals(""))
		{
			Toast.makeText(getApplicationContext(), "信息不能为空", Toast.LENGTH_SHORT).show();
		}
		else{*/
			String phoneNums = edtRegisterPhone.getText().toString();
			res=true;
			SMSSDK.submitVerificationCode("86", phoneNums, edtRegisterCode
					.getText().toString());
	
		
		
	}

	/*public void registernext(View v){
		
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
		
	}*/
	

	public void registerback(View v){
		Intent intent = new Intent(RegisterActivity.this,LoginActivity.class);
		 
		startActivity(intent);
		finish();
	}
	public static void enableStrictMode(Context context) {
	    StrictMode.setThreadPolicy(
	    new StrictMode.ThreadPolicy.Builder()
	    .detectDiskReads()
	    .detectDiskWrites()
	    .detectNetwork()
	    .penaltyLog()
	    .build());
	    StrictMode.setVmPolicy(
	    new StrictMode.VmPolicy.Builder()
	    .detectLeakedSqlLiteObjects()
	    .penaltyLog()
	    .build());
	    }
/*	private class PostRunner implements Runnable{

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
		
	}*/
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.register, menu);
		return true;
	}


}
