package com.example.ty.fragmentdemo;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.ref.SoftReference;
import java.util.ArrayList;
import java.util.Collections;
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
import ty.entity.CarBrand;
import ty.entity.User;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ty.fragmentdemo.R;
import com.example.ty.fragmentdemo.CarInformationActivity.MyCarRunner;
import com.example.ty.sortlistview.CharacterParser;
import com.example.ty.sortlistview.ClearEditText;
import com.example.ty.sortlistview.PinyinComparator;
import com.example.ty.sortlistview.SideBar;
import com.example.ty.sortlistview.SortAdapter;
import com.example.ty.sortlistview.SortModel;
import com.example.ty.sortlistview.SideBar.OnTouchingLetterChangedListener;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

public class ShowCarBrandActivity extends Activity {
	private ListView sortListView;
	private SideBar sideBar;
	private TextView dialog;
	private SortAdapter adapter;
	private ClearEditText mClearEditText;
	public String carbrandJSON;
	public Handler handler;
	private String url;
	private String myurl;
	/**
	 * ����ת����ƴ������
	 */
	public List<CarBrand> list=new ArrayList<CarBrand>();
	private CharacterParser characterParser;
	private List<SortModel> SourceDateList;
	private  HashMap<String,SoftReference<Bitmap>> imageCache=new HashMap<String,SoftReference<Bitmap>>();
	private	String sdcardCacheDir=Environment.getExternalStorageDirectory().getPath()+"/carImage";
	/**
	 * ���ƴ��������ListView����������
	 */
	private PinyinComparator pinyinComparator;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show_car_brand);
		sortListView = (ListView) findViewById(R.id.country_lvcountry);

		
		Myapp myapp=(Myapp)getApplication();
		myurl=myapp.getUrl();
		url=myurl+"FindCarBrandServlet";
		
		// ���a-z��������Դ���

		
		//Toast.makeText(getActivity(), "success2", Toast.LENGTH_SHORT).show();
		handler=new Handler(){
			public void handleMessage(Message msg){
				super.handleMessage(msg);
				
				if(msg.what==1){
			
				//	System.out.println("success");
					Gson gson = new Gson();	
					
				//	list = (List<OrderList>) gson.fromJson(orderJSON, User.class);
					Log.i("carbrand",carbrandJSON);
					list = gson.fromJson(carbrandJSON,  new TypeToken<List<CarBrand>>(){}.getType());
					
					SourceDateList = filledData(list);
					Collections.sort(SourceDateList, pinyinComparator);
					
		
		adapter = new SortAdapter(getApplicationContext(), SourceDateList,imageCache,sdcardCacheDir,myurl);
		sortListView.setAdapter(adapter);
					int i=0;
					
					}
			
				}

				
		};new Thread(new MyCarBrandRunner()).start();
		//Toast.makeText(getActivity(), "success2", Toast.LENGTH_SHORT).show();
		Log.i("kong",list.size()+"");
		
		initViews();
	}

	private void initViews() {
		//ʵ����תƴ����
		characterParser = CharacterParser.getInstance();
		
		pinyinComparator = new PinyinComparator();
		
		sideBar = (SideBar) findViewById(R.id.sidrbar);
		dialog = (TextView) findViewById(R.id.dialog);
		sideBar.setTextView(dialog);
		
		//�����Ҳഥ������
		sideBar.setOnTouchingLetterChangedListener(new OnTouchingLetterChangedListener() {
			
			@Override
			public void onTouchingLetterChanged(String s) {
				//����ĸ�״γ��ֵ�λ��
				int position = adapter.getPositionForSection(s.charAt(0));
				if(position != -1){
					sortListView.setSelection(position);
				}
				
			}
		});
		
		
		sortListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				//����Ҫ����adapter.getItem(position)����ȡ��ǰposition���Ӧ�Ķ���
				//单击项目返回名字！！！
				//接收意图
				Intent intent = getIntent();
				
				//创建并填充bundle包
				Bundle bundle = new Bundle();
				
				
				
				bundle.putString("txtBrand",((SortModel)adapter.getItem(position)).getcarbrand().getType() );
				bundle.putString("imgBrand",((SortModel)adapter.getItem(position)).getcarbrand().getImage() );
				
			
				intent.putExtras(bundle);				
				//返回到上一个界面
				setResult(RESULT_OK, intent);			
				//关闭当前界面
				finish();
			//	Toast.makeText(getApplication(), ((SortModel)adapter.getItem(position)).getName(), Toast.LENGTH_SHORT).show();
			}
		});
		
		
		
		
		
		mClearEditText = (ClearEditText) findViewById(R.id.filter_edit);
		
		//������������ֵ�ĸı�����������
		mClearEditText.addTextChangedListener(new TextWatcher() {
			
			@Override
			public void onTextChanged(CharSequence s, int start, int before, int count) {
				//������������ֵΪ�գ�����Ϊԭ�����б?����Ϊ��������б�
				filterData(s.toString());
			}
			
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				
			}
			
			@Override
			public void afterTextChanged(Editable s) {
			}
		});
	}


	/**
	 * ΪListView������
	 * @param date
	 * @return
	 *///
	public List<SortModel> filledData(List<CarBrand> date){
		List<SortModel> mSortList = new ArrayList<SortModel>();
		
			Log.i("youkong",date.size()+"");
		
		for(int i=0; i<date.size(); i++){
			SortModel sortModel = new SortModel();
			sortModel.setcarbrand(date.get(i));
			//����ת����ƴ��
			String pinyin = characterParser.getSelling(date.get(i).getType());
			String sortString = pinyin.substring(0, 1).toUpperCase();
			
			// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
			if(sortString.matches("[A-Z]")){
				sortModel.setSortLetters(sortString.toUpperCase());
			}else{
				sortModel.setSortLetters("#");
			}
			
			mSortList.add(sortModel);
		}
		return mSortList;
		
	}
	class MyCarBrandRunner implements Runnable{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			 HttpClient client = new DefaultHttpClient();

			  HttpPost request = new HttpPost(url);
	         
			  List<NameValuePair> params = new ArrayList<NameValuePair>();
			  params.add(new BasicNameValuePair("carbrand",""));
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
				
				       
				 carbrandJSON=EntityUtils.toString(response.getEntity());
					
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
	/**
	 * ���������е�ֵ��������ݲ�����ListView
	 * @param filterStr
	 */
	public void filterData(String filterStr){
		List<SortModel> filterDateList = new ArrayList<SortModel>();
		
		if(TextUtils.isEmpty(filterStr)){
			filterDateList = SourceDateList;
		}else{
			filterDateList.clear();
			for(SortModel sortModel : SourceDateList){
				String name = sortModel.getcarbrand().getType();
				if(name.indexOf(filterStr.toString()) != -1 || characterParser.getSelling(name).startsWith(filterStr.toString())){
					filterDateList.add(sortModel);
				}
			}
		}
		
		// ���a-z��������
		Collections.sort(filterDateList, pinyinComparator);
		adapter.updateListView(filterDateList);
	}
	
}
