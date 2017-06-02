package com.example.ty.fragmentdemo;


import java.util.ArrayList;
import java.util.List;

import com.example.ty.fragmentdemo.R;
import com.special.ResideMenu.ResideMenu;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;

public class MessageFragment extends Fragment {
	private ViewPager mViewPager;
	private TestAdapter mAdapter;
	private ResideMenu resideMenu;

	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View messageLayout = inflater.inflate(R.layout.message_layout,
				container, false);

       

      MainActivity parentActivity = (MainActivity) getActivity();
        resideMenu = parentActivity.getResideMenu();

		// add gesture operation's ignored views
         
        // add gesture operation's ignored views
       View  ignored_view =  messageLayout.findViewById(R.id.pager);
        //这句代码的作用是：手指在ignored_view扢�在区域内滑动不会显示菜单
       resideMenu.addIgnoredView(ignored_view);

	    //这句代码的作用是：手指在ignored_view扢�在区域内滑动不会显示菜单
	  
		mViewPager = (ViewPager) messageLayout.findViewById(R.id.pager);
		
		mAdapter = new TestAdapter(getActivity().getApplicationContext());
		mAdapter.change(getList());
		mViewPager.setAdapter(mAdapter);
		return messageLayout;
	}
	private List<Integer> getList() {
		List<Integer> list = new ArrayList<Integer>();
		list.add(R.drawable.page1);
		list.add(R.drawable.page2);
		list.add(R.drawable.page3);
		return list;
	}

	public class TestAdapter extends PagerAdapter {

		private List<Integer> mPaths;

		private Context mContext;

		public TestAdapter(Context cx) {
			mContext = cx.getApplicationContext();
		}

		public void change(List<Integer> paths) {
			mPaths = paths;
		}

		@Override
		public int getCount() {
			// TODO Auto-generated method stub
			if (mPaths != null) {
				return mPaths.size();
			}
			return 0;
		}

		@Override
		public boolean isViewFromObject(View view, Object obj) {
			// TODO Auto-generated method stub
			return view == (View) obj;
		}

		@Override
		public Object instantiateItem(ViewGroup container, int position) {
			ImageView iv = new ImageView(mContext);
			iv.setImageResource(mPaths.get(position));
			 iv.setScaleType(ImageView.ScaleType.CENTER_CROP);
	            iv.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
			((ViewPager) container).addView(iv);
			return iv;
		}

		@Override
		public void destroyItem(ViewGroup container, int position, Object object) {
			container.removeView((View) object);
		}
	}

}
