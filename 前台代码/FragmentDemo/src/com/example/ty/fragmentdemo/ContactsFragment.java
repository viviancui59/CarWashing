package com.example.ty.fragmentdemo;

import com.example.ty.fragmentdemo.R;

import android.app.Fragment;
import android.app.LocalActivityManager;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TabHost;
import android.widget.TabWidget;
import android.widget.TextView;

public class ContactsFragment extends Fragment {
	View mainView;
	
	public ContactsFragment(){
		super();
	}

	LocalActivityManager localActivityManager;
	TabHost tabHost;
	TabWidget tabWidget;
	
	@Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.contacts_layout, container, false);

        findTabView();
        localActivityManager = new LocalActivityManager(getActivity(), true);
        localActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(localActivityManager);
        Resources localResources = getResources();
        
        Intent localIntent1 = new Intent();
        localIntent1.setClass(getActivity(), OrderlistTabActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tabhost1").setIndicator("进行中").setContent(localIntent1));

        Intent localIntent2 = new Intent();
        localIntent2.setClass(getActivity(), OrderlistTab2Activity.class);
        tabHost.addTab(tabHost.newTabSpec("tabhost2").setIndicator("已完成").setContent(localIntent2));

        tabWidget = tabHost.getTabWidget();
        // Change strip(tab bottom line) color
        for(int i=0; i < tabWidget.getChildCount(); i++){
            tabWidget.getChildAt(i).setBackgroundResource(R.drawable.tab_indicator_ab_mmstyle);
        }
        return mainView;
    }

    @Override
    public void onResume() {
        super.onResume();
        localActivityManager.dispatchResume();
    }

    @Override
    public void onPause() {
        super.onPause();
        localActivityManager.dispatchPause(getActivity().isFinishing());
    }

    /**
     * 找到tabhost布局
     */
    public void findTabView() {
        tabHost = (TabHost) mainView.findViewById(R.id.tabhost);
    }
}

