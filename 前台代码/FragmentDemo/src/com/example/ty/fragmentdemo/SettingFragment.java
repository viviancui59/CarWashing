package com.example.ty.fragmentdemo;


import com.example.ty.fragmentdemo.R;

import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SettingFragment extends Fragment implements View.OnClickListener{

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		View settingLayout = inflater.inflate(R.layout.activity_search,
				container, false);
		return settingLayout;
	}
	public void toArticle(View v){
		Intent intent = new Intent(getActivity(),ArticleActivity.class);
		 
		startActivity(intent);
		
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.searchlayout:
			Intent intent = new Intent(getActivity(),ArticleActivity.class);
			 
			startActivity(intent);
			break;
		
	}

	}
}
