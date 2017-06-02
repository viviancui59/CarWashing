package com.example.fragmentdemo;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import ty.entity.Car;
import ty.entity.User;
import android.app.Activity;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.task.ImageTask;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class CarInformationActivity extends Activity {
	//声明组件
		private ListView ListView_CarInfo;
		public String carJSON;
		private String userJSON;
		private String urlUser = "http://10.130.58.126:8080/NewBee/UserLoginServlet";
		private String url = "http://10.130.58.126:8080/CarWashing/FindCarServlet";
		private String urlDeleteCar = "http://10.130.58.126:8080/CarWashing/DeleteCarServlet";
		private String urlInsertCar = "http://10.130.58.126:8080/CarWashing/InsertCarServlet";
		String imageBaseUrl="http://10.130.58.126:8080/CarWashing/carImage/";
		private  HashMap<String,SoftReference<Bitmap>> imageCache=new HashMap<String,SoftReference<Bitmap>>();
		private	String sdcardCacheDir=Environment.getExternalStorageDirectory().getPath()+"/carImage";
		//定义数据列表
		private List<Map<String, Object>> dataList = new ArrayList<Map<String, Object>>();  
		public Handler handler;
		//声明适配器
		//SimpleAdapter adapter;
		public List<Car> list=new ArrayList<Car>();
		MyAdapter adapter;
		//当前车辆信息的位置
		private int currentPosition;
		//声明对话框，是否删除或者退出
		private Dialog dlgExit, dlgDelete;
		public User user;
		

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_car_information);

		//获取组件
		ListView_CarInfo = (ListView) findViewById(R.id.listView_CarInfo);
		
		//填充数据列表
				fillDataList(); 
				user=new User();
				//user.setUserid(1412666);
					Myapp myApp = (Myapp)getApplication();
				user=myApp.getUser();
				
				
				adapter = new MyAdapter(imageCache,sdcardCacheDir);

	
	//绑定长击事件监听器
	ListView_CarInfo.setOnItemLongClickListener(new OnItemLongClickListenerImpl());

	//创建对话框
			createDialog();
			handler=new Handler(){
				public void handleMessage(Message msg){
					super.handleMessage(msg);
					dataList.clear();
					adapter.notifyDataSetChanged();
					//绑定适配器
					ListView_CarInfo.setAdapter(adapter);
					if(msg.what==1){
				
					//	System.out.println("success");
						Gson gson = new Gson();	
						
					//	list = (List<OrderList>) gson.fromJson(orderJSON, User.class);
						
						list = gson.fromJson(carJSON,  new TypeToken<List<Car>>(){}.getType());
						int i=0;
						user.setCar(list);
						Myapp myApp = (Myapp)getApplication();
						myApp.setUser(user);
						
						for(Car car:list){
						
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("imgBrand", car.getBrandimage());
							map.put("txtBrand", car.getBrandtype());
							map.put("txtPlate", car.getPlate());
							
							dataList.add(i, map);
							i++;
							
							
							
							
							adapter.notifyDataSetChanged();
							ListView_CarInfo.setAdapter(adapter);
						}
				
					}else if(msg.what==2){
						
							
						Gson gson = new Gson();	
						user = gson.fromJson(userJSON, User.class);
						Myapp myApp = (Myapp)getApplication();
					    myApp.setUser(user);
					
					
				    }

					dataList.clear();

						
					Gson gson = new Gson();	
						
					//	list = (List<OrderList>) gson.fromJson(orderJSON, User.class);
						
						list = gson.fromJson(carJSON,  new TypeToken<List<Car>>(){}.getType());
						int i=0;
						for(Car car:list){
						
							Map<String, Object> map = new HashMap<String, Object>();
							map.put("imgBrand", car.getBrandimage());
							map.put("txtBrand", car.getBrandtype());
							map.put("txtPlate", car.getPlate());
							dataList.add(i, map);
							i++;
							adapter.notifyDataSetChanged();
							ListView_CarInfo.setAdapter(adapter);
							//Myapp myApp = (Myapp)getActivity().getApplication();
							//System.out.println(orderlist.getUser().toString());
							//myApp.setUser(orderlist.getUser());//修改！！！！！！
					}
				}
				
			};
			//Toast.makeText(getActivity(), "success2", Toast.LENGTH_SHORT).show();
			new Thread(new MyCarRunner()).start();
		
	}
	class MyCarRunner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			 HttpClient client = new DefaultHttpClient();

			  HttpPost request = new HttpPost(url);
			  //Myapp myApp = (Myapp)getApplication();
			//	user=myApp.getUser();
			  List<NameValuePair> params = new ArrayList<NameValuePair>();
			  
			  Log.i("test userid is null",user.getUserid()+"");
			  params.add(new BasicNameValuePair("userid",user.getUserid()+""));
			 // System.out.println("success1");
			  																		
			  try {
				request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			   try {
				  HttpResponse response = client.execute(request);
				
				 
				  if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				
				       
				 carJSON=EntityUtils.toString(response.getEntity());
					
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
	
	//定义对话框按钮的事件监听器
		private class OnClickListenerImpl implements DialogInterface.OnClickListener{

			@Override
			public void onClick(DialogInterface dialog, int id) {
				
				if(id==Dialog.BUTTON_POSITIVE){    //确定按钮
					
				
					
					if(dialog==dlgDelete){ //删除日记对话框
						
						//删除数据列表中的数据项
						dataList.remove(currentPosition);
						
						//通知适配器数据列表已经改变
						adapter.notifyDataSetChanged();
						new Thread(new CancelOrderRunner()).start();
						
						Toast.makeText(getApplicationContext(), "车辆信息删除成功", Toast.LENGTH_SHORT).show();
					}
				}
			}
		}
		//定义长击事件监听器
		private class OnItemLongClickListenerImpl implements AdapterView.OnItemLongClickListener{

			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				
				//记录当前日记的位置
				currentPosition = position;
						
				//显示删除确认对话框
				dlgDelete.show();
			
				return true;
			}
			
		}
		//添加车辆信息
		public void Add_Car(View v){
			
			//创建意图
			Intent intent = new Intent(CarInformationActivity.this, AddCarInfomationActivity.class);
			
			//带返回结果的方式跳转到添加界面
			startActivityForResult(intent, 1);
			
		}
		
public void back(View v){
			
			//创建意图
			Intent intent = new Intent(CarInformationActivity.this, MainActivity.class);
			
			//带返回结果的方式跳转到添加界面
			startActivity(intent);
			finish();
			
		}
		@Override
		protected void onActivityResult(int requestCode, int resultCode, Intent data) {
			super.onActivityResult(requestCode, resultCode, data);
			
			if(requestCode == 1){    //日记添加的跳转
				
				if(resultCode == RESULT_OK){   //添加操作
					
					//获取intent中的数据
					Bundle bundle = data.getExtras();
					
					//添加新的Map集合
					Map<String, Object> item = new HashMap<String, Object>();
					item.put("imgBrand", bundle.getString("imgBrand"));
					item.put("txtBrand", bundle.getString("txtBrand"));
					item.put("txtPlate", bundle.getString("txtPlate"));
					
					
					//在列表的顶部添加新日记
					dataList.add( item);
					
					new Thread(new InsertCarRunner(dataList)).start();
					//通知适配器数据更新
					adapter.notifyDataSetChanged();
					
					
					Toast.makeText(getApplicationContext(), "车辆信息添加成功", Toast.LENGTH_SHORT).show();
					
				}
			}
			
		}


	//创建对话框
		private void createDialog() {
			
			Builder builder = new Builder(this);
		
			//设置图标
			builder.setIcon(R.drawable.question);
			
			//设置标题
			builder.setTitle("删除确认对话框");
			
			//设置信息
			builder.setMessage("是否确认删除车辆信息");
			
			//设置操作按钮
			builder.setPositiveButton("是", new OnClickListenerImpl());  
			builder.setNegativeButton("否", null);
			
			//创建确认删除对话框
			this.dlgDelete = builder.create();
		}

	private void fillDataList() {
		// TODO Auto-generated method stub
		
		
	}
	
	private class MyAdapter extends BaseAdapter{

		 
		 HashMap<String,SoftReference<Bitmap>> imageCache;
			String sdcardCacheDir;
			public MyAdapter(HashMap<String,SoftReference<Bitmap>> imageCache,String sdcardCacheDir)
			{
				this.imageCache=imageCache;
				this.sdcardCacheDir=sdcardCacheDir;
			}
		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			return dataList.size();

			
		}

		@Override
		public Object getItem(int position) {
			// TODO Auto-generated method stub
			return dataList.get(position);

		}

		@Override
		public long getItemId(int position) {
			// TODO Auto-generated method stub
			return position;

		}

		@Override
		public View getView(int position, View view, ViewGroup parent) {
			// TODO Auto-generated method stub
			//根据布局资源获取view对象
			String imageUrl;
			Bitmap bitmap;
			view = View.inflate(getApplicationContext(), R.layout.item_car_infp, null);
			
			//获取当前的数据项
			Map<String, Object> map = dataList.get(position);
			
			//根据数据填充view对象中的每个组件
			
			ImageView imgBrand = (ImageView) view.findViewById(R.id.imgbrand);
			//imgWeather.setImageResource(Integer.parseInt(map.get("weather").toString()));
		//	imgBrand.setImageResource((Integer)(map.get("imgBrand")));
			imageUrl=imageBaseUrl+map.get("imgBrand").toString();
			Log.i("mapget",map.get("imgBrand").toString());
			bitmap=findBitmap(imageUrl);
			if(bitmap!=null)
			{
				imgBrand.setImageBitmap(bitmap);
			}
			else{
           new	ImageTask(imgBrand).execute(imageUrl);}
			
			TextView txtBrand = (TextView) view.findViewById(R.id.brand);
			//rtbMood.setRating(Float.parseFloat(map.get("mood").toString()));
			txtBrand.setText((map.get("txtBrand")).toString());
			
			
			
			TextView txtPlate = (TextView) view.findViewById(R.id.txt_plate);
			txtPlate.setText(map.get("txtPlate").toString());
			
			
			return view;
		}
		public Bitmap findBitmap(String imageUrl)
		{
			Log.i("CAr",imageUrl);
			if(imageCache.containsKey(imageUrl))
			{
				SoftReference<Bitmap> reference=imageCache.get(imageUrl);
				Bitmap bitmap=reference.get();
				if(bitmap!=null)
				{
					Log.i("car>>>已经获取内存图片",">>>已经获取内存图片");
					System.out.println(">>>已经获取内存图片");
					return bitmap;
					
				}
			}
			
			String bitmapName=imageUrl.substring(imageUrl.lastIndexOf("/")+1);
			File cacheDir=new File(sdcardCacheDir);
			if(cacheDir.exists())
			{
				File[] cacheFiles=cacheDir.listFiles();
				int i=0;
				for(;i<cacheFiles.length;i++)
				{
					if(bitmapName.equals(cacheFiles[i].getName()))
					{
						break;
					}
				}
				if(i<cacheFiles.length)
				{
					Log.i("car>>>已加载SD缓存图片",">>>已加载SD缓存图片");

					System.out.println(">>已加载SD缓存图片");
					Bitmap bitmap=BitmapFactory.decodeFile(sdcardCacheDir+"/"+bitmapName);
					imageCache.put(imageUrl,new SoftReference<Bitmap>(bitmap));
					return bitmap;
				}
			}
			return null;
			
		}
		

		}
	
	class CancelOrderRunner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			 HttpClient client = new DefaultHttpClient();

			  HttpPost request = new HttpPost(urlDeleteCar);
	         
			  List<NameValuePair> params = new ArrayList<NameValuePair>();
			  params.add(new BasicNameValuePair("userid",user.getUserid()+""));
			  params.add(new BasicNameValuePair("carid",user.getCar().get(currentPosition).getCarid()+""));
			  user.getCar().remove(currentPosition);
			  Myapp myApp = (Myapp)getApplication();
				
			  myApp.setUser(user);
			  
			  //Toast.makeText(getActivity(), cancelorderid, Toast.LENGTH_SHORT).show();
			  // System.out.println(cancelorderid);
			  
			  try {
				request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			   try {
				  HttpResponse response = client.execute(request);
				
				 
				  if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				
				       
				  carJSON=EntityUtils.toString(response.getEntity());
					
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
	class InsertCarRunner implements Runnable{

		List<Map<String, Object>> dataList;
		InsertCarRunner(List<Map<String, Object>> dataList)
		{
			this.dataList=dataList;
		}
		@Override
		public void run() {
			// TODO Auto-generated method stub
			 HttpClient client = new DefaultHttpClient();

			  HttpPost request = new HttpPost(urlInsertCar);
	         
			  List<NameValuePair> params = new ArrayList<NameValuePair>();
			  params.add(new BasicNameValuePair("userid",user.getUserid()+""));
			 String txtBrand=dataList.get((dataList.size()-1)).get("txtBrand").toString();
			  params.add(new BasicNameValuePair("txtBrand",txtBrand));
			  String txtPlate=dataList.get((dataList.size()-1)).get("txtPlate").toString();
			  params.add(new BasicNameValuePair("txtPlate",txtPlate));
			  //传过去了userid和牌子名称以及车牌
		
			  //Toast.makeText(getActivity(), cancelorderid, Toast.LENGTH_SHORT).show();
			  // System.out.println(cancelorderid);
			  
			  try {
				request.setEntity(new UrlEncodedFormEntity(params,HTTP.UTF_8));
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			   try {
				  HttpResponse response = client.execute(request);
				
				 
				  if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				
				       
				  carJSON=EntityUtils.toString(response.getEntity());
					
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
		getMenuInflater().inflate(R.menu.car_information, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_car_information,
					container, false);
			return rootView;
		}
	}

}
