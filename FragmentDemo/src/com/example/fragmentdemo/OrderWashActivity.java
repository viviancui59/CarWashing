package com.example.fragmentdemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;
import org.apache.http.util.EntityUtils;

import ty.app.Myapp;
import ty.entity.Car;
import ty.entity.OrderList;
import ty.entity.User;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.gson.Gson;

@SuppressLint("NewApi")
public class OrderWashActivity extends Activity {

	private TextView txtDate;
	private Button btnSelectDate;
	private Button btnSelectTime;
	private Button btnOrderWash;
	private TextView txtOrderDate;
	private TextView txtOrderTime;
	private Spinner spnWashCar;
	private ArrayAdapter<String> carAdapter; 
	private DatePickerDialog datedialog;
	private TimePickerDialog timedialog;
	private int year,month,day,hour,minute;
	
	private String orderdate;
	private String ordertime;
	private String orderTime;
	private String mes;
	private String orderPlate;
	private Dialog dlg;
  	private Builder build;
	
  	private List<Car> carlist = new ArrayList<Car>(); 
  	private List<String> platelist = new ArrayList<String>(); 
	
	private String url = "http://192.168.1.105:8080/CarWashing/OrderWashServlet";
	private String orderJSON;
	private Handler handler;
	private Intent intent;
	
	
	public User user;
	private String todaydate;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_order_wash);

		SimpleDateFormat df=new SimpleDateFormat("yyyy-MM-dd");
		todaydate=df.format(new Date());
		
		txtDate=(TextView)findViewById(R.id.txtDate);
		txtOrderDate=(TextView)findViewById(R.id.txtOrderDate);
		txtOrderTime=(TextView)findViewById(R.id.txtOrderTime);
		btnSelectDate=(Button)findViewById(R.id.btnSelectDate);
		btnSelectTime=(Button)findViewById(R.id.btnSelectTime);
		btnOrderWash=(Button)findViewById(R.id.btnOrderWash);
		spnWashCar=(Spinner)findViewById(R.id.spnWashCar);
		
		txtDate.setText(todaydate);
		
		Calendar calendar=Calendar.getInstance();
		year=calendar.get(Calendar.YEAR);
		month=calendar.get(Calendar.MONTH);
		day=calendar.get(Calendar.DAY_OF_MONTH);
		hour=calendar.get(Calendar.HOUR_OF_DAY);
		minute=calendar.get(Calendar.MINUTE);

		user=new User();
		Myapp myApp = (Myapp)this.getApplication();
		user=myApp.getUser();
		
		intent = this.getIntent();
		build = new Builder(this);
		build.setPositiveButton("确定", new SuccessImpl());
		
		/*Car car1=new Car();
		Car car2=new Car();
		car1.setPlate("津JDS268");
		car2.setPlate("津CS2796");
		List<Car> carli = new ArrayList<Car>();
		carli.add(car1);
		carli.add(car2);
		Log.i("car", carli.get(1).getPlate());
		user.setCar(carli);
		user.setUserid(10);
		*/
		carlist=user.getCar();
		for(Car car:carlist)
		{
			platelist.add(car.getPlate());
		}
		
		carAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item, platelist);    
        //为适配器设置下拉列表下拉时的菜单样式。    
		carAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);    
        //将适配器添加到下拉列表上    
		spnWashCar.setAdapter(carAdapter);    
        //为下拉列表设置各种事件的响应，这个事响应菜单被选中    
		spnWashCar.setOnItemSelectedListener(new Spinner.OnItemSelectedListener(){    
              
           
			@Override
			public void onItemSelected(AdapterView<?> arg0, View arg1,
					int arg2, long arg3) {
				// TODO Auto-generated method stub
				orderPlate=carAdapter.getItem(arg2).toString();
				arg0.setVisibility(View.VISIBLE);
				
			}
			@Override
			public void onNothingSelected(AdapterView<?> arg0) {
				// TODO Auto-generated method stub
				arg0.setVisibility(View.VISIBLE);
			}    
        });    
		
		
		handler = new Handler() {
			public void handleMessage(Message msg) {
				super.handleMessage(msg);
				Log.i("c", "ccc");
				if (msg.what == 1) {

					OrderList order = new OrderList();
					Gson gson = new Gson();

					order = gson.fromJson(orderJSON,OrderList.class);

					if (order != null) {
						
						mes = "预约成功！可从我的订单中查看订单信息。";
						

						
					}
					else{
						mes = "抱歉，预约失败，请重新预约！";
					}
					setResult(RESULT_OK, intent); 
					
					build.setMessage(mes);
					dlg = build.create();
					dlg.show();
				}
			}
		};

		
		
	}

	private DatePickerDialog.OnDateSetListener Datelistener=new DatePickerDialog.OnDateSetListener()
    {
        
        @Override
        public void onDateSet(DatePicker view, int myyear, int monthOfYear,int dayOfMonth) {
            
            
            //修改year、month、day的变量值，以便以后单击按钮时，DatePickerDialog上显示上一次修改后的值
            year=myyear;
            month=monthOfYear+1;
            day=dayOfMonth;
            //更新日期
            updateDate();
            
        }
        //当DatePickerDialog关闭时，更新日期显示
        private void updateDate()
        {
            //在TextView上显示日期
        	if(month<10)
        	{
        		if(day<10)
        		{
        			orderdate=year+"-0"+month+"-0"+day;
        		}else{
        		orderdate=year+"-0"+month+"-"+day;
        		}
        	}
        	else{
        		if(day<10){
        			orderdate=year+"-"+month+"-0"+day;
        		}else{
        			orderdate=year+"-"+month+"-"+day;
        		}
           
        	}
           txtOrderDate.setText(orderdate);
        }
    };
    private TimePickerDialog.OnTimeSetListener Timelistener=new TimePickerDialog.OnTimeSetListener()
    {
        
        
        //当DatePickerDialog关闭时，更新日期显示
      
		@Override
		public void onTimeSet(TimePicker arg0, int myhour, int myminute) {
			// TODO Auto-generated method stub
			hour=myhour;
			minute=myminute;
			updateTime();
		}
		  private void updateTime()
	        {
			  if(hour<10)
			  {
				  if(minute<10)
				  {
					  ordertime="0"+hour+":0"+minute;
				  }else{
					  ordertime="0"+hour+":"+minute;
				  }
				  
			  }else{
				  if(minute<10)
				  {
					  ordertime=hour+":0"+minute;
				  }else{
					  ordertime=hour+":"+minute;
				  }
				  
			  }
	            //在TextView上显示日期
	           
	           txtOrderTime.setText(ordertime);
	        }
    };
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.order_wash, menu);
		return true;
	}

	public void back(View v){
		Intent intent = new Intent(OrderWashActivity.this,MainActivity.class);
		 
		startActivity(intent);
		finish();
	}
	
	
	

	public void selectDate(View v){
		datedialog=new DatePickerDialog(OrderWashActivity.this,Datelistener,year,month,day);

		long mindate=System.currentTimeMillis()-1000L;
		long maxdate=mindate + 7*24*3600*1000L;

		DatePicker dp=datedialog.getDatePicker();
		
		dp.setMinDate(mindate);
		dp.setMaxDate(maxdate);
		
		datedialog.show();
	}
	
	public void selectTime(View v){
		timedialog=new TimePickerDialog(OrderWashActivity.this,Timelistener,hour,minute,true);
		timedialog.show();
	}
	public void orderWash(View v) throws ParseException{
		 
		 orderTime=orderdate+" "+ordertime;
	//	 SimpleDateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		 SimpleDateFormat df2 = new SimpleDateFormat("HH:mm");
		 
		 String nowtimes=df2.format(new Date());
		 Date nowtime=df2.parse(nowtimes);		 
		 Date time=df2.parse(ordertime);
		 Date closetime=df2.parse("18:00");
		 Date starttime=df2.parse("09:00");
		 
		 long diff=time.getTime()-nowtime.getTime();
		 long diff2=closetime.getTime()-time.getTime();
		 long diff3=time.getTime()-starttime.getTime();
		 
		/* Date blacktime=df.parse(user.getBlacktime());
			Date nowtime=new Date(System.currentTimeMillis());
			diff=nowtime.getTime()-blacktime.getTime();*/
		 if(todaydate.equals(orderdate))
		 {
			 if(diff>0&&diff2>0&&diff3>0)
			 {
				 Log.i("123", diff+"");
				 new Thread(new OrderRunner()).start();
			 }
			 else{
				 Toast.makeText(getApplicationContext(), "预约时间无效，请重新预约", Toast.LENGTH_SHORT).show();
					
			 }
		 }
		 else{
			 if(diff2>0&&diff3>0)
			 {
				// Log.i("111", todaydate+"   "+orderdate);
				 new Thread(new OrderRunner()).start();
			 }
			 else{
				 Toast.makeText(getApplicationContext(), "预约时间无效，请重新预约", Toast.LENGTH_SHORT).show();
					
			 }
		 }
	}
	
	//spinner选择车辆***************
	
	
	public class SuccessImpl implements DialogInterface.OnClickListener {

		@Override
		public void onClick(DialogInterface arg0, int arg1) {
			// TODO Auto-generated method stub
			Intent intent = new Intent(OrderWashActivity.this,MainActivity.class);
			 
			startActivity(intent);
			finish();
		}
		
	}
	public class OrderRunner implements Runnable {

		@Override
		public void run() {
			// TODO Auto-generated method stub
			HttpClient client = new DefaultHttpClient();

			HttpPost request = new HttpPost(url);

			List<NameValuePair> params = new ArrayList<NameValuePair>();

			params.add(new BasicNameValuePair("time", orderTime));
			params.add(new BasicNameValuePair("userid", user.getUserid()+""));
			params.add(new BasicNameValuePair("price", "25"));
			params.add(new BasicNameValuePair("type", "1"));
			params.add(new BasicNameValuePair("plate", orderPlate));
			params.add(new BasicNameValuePair("storeid", "1"));
			
			
		//*****	params.add(new BasicNameValuePair("car", ordercar));

			try {
				request.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));

				HttpResponse response = client.execute(request);
				Log.i("a", "aaa");

				if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

					Log.i("b", "bbb");
					orderJSON = EntityUtils.toString(response.getEntity());
				
					handler.sendEmptyMessage(1);
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ClientProtocolException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}


}

