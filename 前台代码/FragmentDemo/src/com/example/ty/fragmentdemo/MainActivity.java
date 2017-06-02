package com.example.ty.fragmentdemo;

import com.example.ty.fragmentdemo.R;
import com.special.ResideMenu.ResideMenu;
import com.special.ResideMenu.ResideMenuItem;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.graphics.Color;


public class MainActivity extends Activity  implements View.OnClickListener {

	 private ResideMenu resideMenu;
	 
	    private ResideMenuItem itemHome;
	    private ResideMenuItem itemyijian;
	    private ResideMenuItem itemProfile;
	    private ResideMenuItem itemCalendar;
	    private ResideMenuItem itemSettings;

//	private Toolbar mToolbar;
	
	private MessageFragment messageFragment;

	
	private ContactsFragment contactsFragment;

	
	private NewsFragment newsFragment;

	
	private SettingFragment settingFragment;

	
	private View messageLayout;

	
	private View contactsLayout;

	
	private View newsLayout;

	
	private View settingLayout;

	
	private ImageView messageImage;

	
	private ImageView contactsImage;

	
	private ImageView newsImage;

	
	private ImageView settingImage;
	
	private ImageView settingimage;

	
	private TextView txtTitle;

	
	private TextView contactsText;

	
	private TextView newsText;

	
	private TextView settingText;

	private FragmentManager fragmentManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);
		/*Intent intert=getIntent();
		int id = intert.getIntExtra("search",-1);
		
		if(id==1){
			
		}*/
		txtTitle=(TextView)findViewById(R.id.txtTitle);
		initViews();
		 setUpMenu();
		
		fragmentManager = getFragmentManager();
		
		setTabSelection(0);
		
		
	}


    private void setUpMenu() {

        resideMenu = new ResideMenu(this);
        //çå§çé¿æ»å´é¨å®åéîæµ
      // resideMenu.setBackground(R.drawable.menu_background);
       resideMenu.setBackground(R.drawable.menu_background);
        //çåå½éæç¬è¤°æ³å¢ Activityéå® ä»ç§éæ½µ
        resideMenu.attachToActivity(this);
        
     //  resideMenu.setMenuListener(menuListener);
        //valid scale factor is between 0.0f and 1.0f. leftmenu'width is 150dip. 
        //çå§çé¿æ»å´éç³åµçå¿ç®·ç¹èå®³é¨å¬ç®æ¸ï¿½(0.0f~1.0fæ¶çæ¹éå ï¿½ï¿½)
        resideMenu.setScaleValue(0.6f);
        
        //éæ¶ç¼é¿æ»å´æ¶î¡î¦éå§ãé¨å¬æ½¯é©ï¿½
        itemHome     = new ResideMenuItem(this, R.drawable.carinfo,     "车辆信息");
        itemProfile  = new ResideMenuItem(this, R.drawable.info,  "消息通知");
        itemCalendar = new ResideMenuItem(this, R.drawable.intro, "功能介绍");
        itemSettings = new ResideMenuItem(this, R.drawable.icon_aboutus, "关于我们");
        itemyijian = new ResideMenuItem(this, R.drawable.idea, "意见反馈");

        //ç¼æ¬å½éæ æ®éï¼æ´°çå§çé©ææ
        itemHome.setOnClickListener(this);
        itemProfile.setOnClickListener(this);
        itemCalendar.setOnClickListener(this);
        itemSettings.setOnClickListener(this);
        itemyijian.setOnClickListener(this);
        
        //çåå½éææ°éï¼æ´°éå® ä»ç§éæ½µ
        //é¦ã¥ä¹æè§æ¨ç»è¹æ®éï¼æ´°
        resideMenu.addMenuItem(itemHome, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemProfile, ResideMenu.DIRECTION_LEFT);
        //é¦ã¥å½¸æè§æ¨ç»è¹æ®éï¼æ´°
        resideMenu.addMenuItem(itemCalendar, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemSettings, ResideMenu.DIRECTION_LEFT);
        resideMenu.addMenuItem(itemyijian, ResideMenu.DIRECTION_LEFT);

        // You can disable a direction by setting ->
        // resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);

        //æµ£ç²å½²æµ ã©ï¿½æ°³ç¹çå§çéã§î¦é¢ã¥ä¹æè§å¨é°å­å½¸æå­æ®é¿æ»å´éå§ã
        resideMenu.setSwipeDirectionDisable(ResideMenu.DIRECTION_RIGHT);
        //æ¶å©æ½°æ©æ¬å½æµ ï½ççè¾¨æ§¸ç»ä½¹îéå® ç«é¨å®å½éææ¨ç»ï¿½
        findViewById(R.id.title_bar_left_menu).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	//éå§ãå®¸ï¹ç«é¿æ»å´
                resideMenu.openMenu(ResideMenu.DIRECTION_LEFT);
            }
        });
       
    }
    public ResideMenu getResideMenu(){
        return resideMenu;
    }
    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
    	/**
		 * çâå½éæç«´å¯®ï¿½æ¿®å¬ªæ°¨é³ä»ï¿½æ°³ç¹å©æå§©çå¿ç®·éã¦æ¨ç»ï¿½(æ©æ¬å½çæ¿ç®æå©å¨çï¿½)
		 * éîäºå¨ã©å´æ©æ¬ééè§ç¡¶éã§æ¹éªå¬«æ¹æµ ï¿½æ¶å ç¬éå²æ®éå ç
		 */
        return resideMenu.dispatchTouchEvent(ev);
    }

   

   /* private ResideMenu.OnMenuListener menuListener = new ResideMenu.OnMenuListener() {
        @Override
        public void openMenu() {
            Toast.makeText(mContext, "Menu is opened!", Toast.LENGTH_SHORT).show();
        }

        @Override
        public void closeMenu() {
            Toast.makeText(mContext, "Menu is closed!", Toast.LENGTH_SHORT).show();
        }
    };*/

	private void initViews() {
		messageLayout = findViewById(R.id.message_layout);
		contactsLayout = findViewById(R.id.contacts_layout);
		newsLayout = findViewById(R.id.news_layout);
		settingLayout = findViewById(R.id.setting_layout);
		
		
		messageImage = (ImageView) findViewById(R.id.xiche_image);
		contactsImage = (ImageView) findViewById(R.id.order_image);
		newsImage = (ImageView) findViewById(R.id.store_image);
		settingImage = (ImageView) findViewById(R.id.finding_image);
	//	settingImage = (ImageView) findViewById(R.id.setting_image);
		
//	 settingimage=(ImageView) findViewById(R.id.setting_image);
	//	messageText = (TextView) findViewById(R.id.plus_text);
	//	contactsText = (TextView) findViewById(R.id.order_text);
	//	newsText = (TextView) findViewById(R.id.his_text);
	//	settingText = (TextView) findViewById(R.id.info_text);
		
		
		messageLayout.setOnClickListener(this);
		contactsLayout.setOnClickListener(this);
		newsLayout.setOnClickListener(this);
		settingLayout.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.message_layout:
			
			setTabSelection(0);
			break;
		case R.id.contacts_layout:
			
			setTabSelection(1);
			break;
		case R.id.news_layout:
			
			setTabSelection(2);
			break;
		case R.id.setting_layout:
			
			setTabSelection(3);
			break;
		default:
			break;
		}

        if (v == itemHome){ //车辆信息
        	Intent intent = new Intent(MainActivity.this,CarInformationActivity.class);//////
			 
			startActivity(intent);
			finish();
           // changeFragment(new HomeFragment());
        }else if (v == itemProfile){ //消息通知
          //  changeFragment(new ProfileFragment());
        	Intent intent = new Intent(MainActivity.this,ShowMessageActivity.class);//////
			 
			startActivity(intent);
			finish();
        }
        else if (v == itemCalendar){ //功能介绍
            //  changeFragment(new ProfileFragment());
          	Intent intent = new Intent(MainActivity.this,FunctionActivity.class);//////
  			 
  			startActivity(intent);
  			finish();
          }
        else if (v == itemSettings){ //关于我们
            //  changeFragment(new ProfileFragment());
          	Intent intent = new Intent(MainActivity.this,AboutusActivity.class);//////
  			 
  			startActivity(intent);
  			finish();
          }
        else if (v == itemyijian){ //意见反馈
            //  changeFragment(new ProfileFragment());
          	Intent intent = new Intent(MainActivity.this,AddFeedbackActivity.class);//////
  			 
  			startActivity(intent);
  			finish();
          }
        

        resideMenu.closeMenu();
	}

	
	private void setTabSelection(int index) {
		
		clearSelection();
		
		FragmentTransaction transaction = fragmentManager.beginTransaction();
		
		hideFragments(transaction);
	    resideMenu = getResideMenu();
	    View  ignored_view = findViewById(R.id.pager);
		switch (index) {
		case 0:
			 
		        //杩欏彞浠ｇ爜鐨勪綔鐢ㄦ槸锛氭墜鎸囧湪ignored_view鎵€鍦ㄥ尯鍩熷唴婊戝姩涓嶄細鏄剧ず鑿滃崟
		   txtTitle.setText("主页");     
			messageImage.setImageResource(R.drawable.xiche_selected);
		//	messageText.setTextColor(Color.WHITE);
			if (messageFragment == null) {
				
				messageFragment = new MessageFragment();
				transaction.add(R.id.content, messageFragment);
			} else {
				
				transaction.show(messageFragment);
			}
			//resideMenu.addIgnoredView(ignored_view);
			break;
		case 1:
			 
			 txtTitle.setText("订单");
		     //   resideMenu.removeIgnoredView(findViewById(R.id.pager));
			contactsImage.setImageResource(R.drawable.order_selected);
			//contactsText.setTextColor(Color.WHITE);
			if (contactsFragment == null) {
				
				contactsFragment = new ContactsFragment();
				transaction.add(R.id.content, contactsFragment);
			} else {
				
				transaction.show(contactsFragment);
			}
			// resideMenu.removeIgnoredView(ignored_view);
			break;
		case 2:
			 txtTitle.setText("商城");
			// resideMenu.removeIgnoredView(findViewById(R.id.pager));
			newsImage.setImageResource(R.drawable.store_selected);
			//newsText.setTextColor(Color.WHITE);
			if (newsFragment == null) {
				
				newsFragment = new NewsFragment();
				transaction.add(R.id.content, newsFragment);
			} else {
				
				transaction.show(newsFragment);
			}
			break;
		case 3:
		default:
			 txtTitle.setText("发现");
			resideMenu.removeIgnoredView(ignored_view);
			// resideMenu.removeIgnoredView(findViewById(R.id.pager));
			settingImage.setImageResource(R.drawable.finding_selected);
			//settingText.setTextColor(Color.WHITE);
			if (settingFragment == null) {
				
				settingFragment = new SettingFragment();
				transaction.add(R.id.content, settingFragment);
			} else {
				
				transaction.show(settingFragment);
			}
		//	resideMenu.removeIgnoredView(ignored_view);
			break;
		}
		transaction.commit();
	}

	
	private void clearSelection() {
		messageImage.setImageResource(R.drawable.xiche_unselected);
	//	messageText.setTextColor(Color.parseColor("#FFFFFF"));
		contactsImage.setImageResource(R.drawable.order_unselected);
	//	contactsText.setTextColor(Color.parseColor("#FFFFFF"));
		newsImage.setImageResource(R.drawable.store_unselected);
	//	newsText.setTextColor(Color.parseColor("#FFFFFF"));
		settingImage.setImageResource(R.drawable.finding_unselected);
	//	settingText.setTextColor(Color.parseColor("#FFFFFF"));
	}

	
	private void hideFragments(FragmentTransaction transaction) {
		if (messageFragment != null) {
			transaction.hide(messageFragment);
		}
		if (contactsFragment != null) {
			transaction.hide(contactsFragment);
		}
		if (newsFragment != null) {
			transaction.hide(newsFragment);
		}
		if (settingFragment != null) {
			transaction.hide(settingFragment);
		}
	}
	  public void orderwash(View v)
	    
	    {
	    	Intent intent = new Intent(MainActivity.this,OrderWashActivity.class);//////
			 
			startActivity(intent);
			
	    }
	    
	public void fuwudianmian(View v)
	    
	    {
	    	Intent intent = new Intent(MainActivity.this,StoreActivity.class);//////
			 
			startActivity(intent);
			
	    }
	public void fuwucheliang(View v)

	{
		Intent intent = new Intent(MainActivity.this,CarInformationActivity.class);//////
		 
		startActivity(intent);
		
	}
	public void yuyuedala(View v)

	{
		Intent intent = new Intent(MainActivity.this,OrderWaxActivity.class);//////
		 
		startActivity(intent);
		
	}
	public void yuyuebaoyang(View v)

	{
		Intent intent = new Intent(MainActivity.this,OrderMaintenanceActivity.class);//////
		 
		startActivity(intent);
		
	}
	
	public void toArticle(View v){
		Intent intent = new Intent(MainActivity.this,ArticleActivity.class);
		 
		startActivity(intent);
	}
		
}
