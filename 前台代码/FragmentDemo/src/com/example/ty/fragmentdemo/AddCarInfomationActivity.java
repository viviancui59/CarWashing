package com.example.ty.fragmentdemo;

import java.util.HashMap;
import java.util.Map;

import com.example.ty.fragmentdemo.R;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class AddCarInfomationActivity extends Activity {

	
	private Button btnSelect;
	private EditText txtPlate;
	private String imgCar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add_car_infomation);
            btnSelect=(Button) findViewById(R.id.select_car);
            txtPlate=(EditText) findViewById(R.id.txtinputPlate);
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}
	public void back(View v)
	{
		//创建意图
		Intent intent = new Intent(AddCarInfomationActivity.this, CarInformationActivity.class);
		
		//带返回结果的方式跳转到添加界面
		startActivity(intent);
		finish();
	}
	public void Save_Car(View v)
	{
		//接收意图
				Intent intent = this.getIntent();
				
				//创建并填充bundle包
				Bundle bundle = new Bundle();
				
				
				
				bundle.putString("imgBrand",imgCar );
				
				bundle.putString("txtBrand", this.btnSelect.getText().toString());
				
				bundle.putString("txtPlate", this.txtPlate.getText().toString());
				intent.putExtras(bundle);				
				//返回到上一个界面
				this.setResult(RESULT_OK, intent);			
				//关闭当前界面
				this.finish();
				
			}

	
	public void Select_Car(View v){
	//	btnSelect.setText("宝马");
		//imgCar="baoma.jpg";
		
		//创建意图
		Intent intent = new Intent(AddCarInfomationActivity.this, ShowCarBrandActivity.class);
		
		////带返回结果的方式跳转到添加界面
		startActivityForResult(intent, 2);
		
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		
		if(requestCode == 2){    //日记添加的跳转
			
			if(resultCode == RESULT_OK){   //添加操作
				
				//获取intent中的数据
				Bundle bundle = data.getExtras();
			btnSelect.setText(bundle.getString("txtBrand"));
			imgCar=bundle.getString("imgBrand");
			
				
				
			}
		}
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add_car_infomation, menu);
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
			View rootView = inflater.inflate(
					R.layout.fragment_add_car_infomation, container, false);
			return rootView;
		}
	}

}
