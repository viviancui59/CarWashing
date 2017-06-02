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

public class NewsFragment extends Fragment {

	View mainView;
	
	public NewsFragment(){
		super();
	}

	LocalActivityManager localActivityManager;
	TabHost tabHost;
	TabWidget tabWidget;
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
        mainView = inflater.inflate(R.layout.news_layout, container, false);

        findTabView();
        localActivityManager = new LocalActivityManager(getActivity(), true);
        localActivityManager.dispatchCreate(savedInstanceState);
        tabHost.setup(localActivityManager);
        Resources localResources = getResources();

        Intent localIntent1 = new Intent();
        localIntent1.setClass(getActivity(), GoodsTabActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tabhost1").setIndicator("全部").setContent(localIntent1));

        Intent localIntent2 = new Intent();
        localIntent2.setClass(getActivity(), GoodsTabPartsActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tabhost2").setIndicator("配件").setContent(localIntent2));

        Intent localIntent3 = new Intent();
        localIntent3.setClass(getActivity(), GoodsTabInteriorActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tabhost3").setIndicator("内饰").setContent(localIntent3));

        Intent localIntent4 = new Intent();
        localIntent4.setClass(getActivity(), GoodsTabElecActivity.class);
        tabHost.addTab(tabHost.newTabSpec("tabhost4").setIndicator("电子").setContent(localIntent4));

        TabWidget tabWidget = tabHost.getTabWidget();
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
        tabHost = (TabHost) mainView.findViewById(R.id.goodstabhost);
        tabWidget = (TabWidget) mainView.findViewById(android.R.id.tabs);
    }

}
