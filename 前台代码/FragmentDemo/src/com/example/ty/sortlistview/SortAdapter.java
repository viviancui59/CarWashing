package com.example.ty.sortlistview;

import java.io.File;
import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.List;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.ImageTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.SectionIndexer;
import android.widget.TextView;

public class SortAdapter extends BaseAdapter implements SectionIndexer{
	private List<SortModel> list;
	String imageBaseUrl;
	private Context mContext;
	HashMap<String,SoftReference<Bitmap>> imageCache;
			String sdcardCacheDir;
	public SortAdapter(Context mContext, List<SortModel> list,HashMap<String,SoftReference<Bitmap>> imageCache,String sdcardCacheDir,String url) {
		this.mContext = mContext;
		this.list = list;
		this.imageCache=imageCache;
		this.sdcardCacheDir=sdcardCacheDir;
		imageBaseUrl=url+"carImage/";

		 
	}
			/*public SortAdapter(Context mContext, List<SortModel> list) {
				this.mContext = mContext;
				this.list = list;
			}*/
	/**
	 * ��ListView��ݷ���仯ʱ,���ô˷���������ListView
	 * @param list
	 */
	public void updateListView(List<SortModel> list){
		this.list = list;
		notifyDataSetChanged();
	}

	public int getCount() {
		if(list==null)
		{
			Log.i("kong","kong");
		}
		return this.list.size();
	}

	public Object getItem(int position) {
		return list.get(position);
	}

	public long getItemId(int position) {
		return position;
	}

	public View getView(final int position, View view, ViewGroup arg2) {
		ViewHolder viewHolder = null;
		String imageUrl;
		Bitmap bitmap;
		final SortModel mContent = list.get(position);
		if (view == null) {
			viewHolder = new ViewHolder();
			view = LayoutInflater.from(mContext).inflate(R.layout.item_car_brand, null);
			viewHolder.tvBrand = (TextView) view.findViewById(R.id.brand);
			viewHolder.tvLetter = (TextView) view.findViewById(R.id.catalog);
			viewHolder.tvImage=(ImageView) view.findViewById(R.id.imgbrand);
			view.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) view.getTag();
		}
		
		//���position��ȡ���������ĸ��Char asciiֵ
		int section = getSectionForPosition(position);
		
		//���ǰλ�õ��ڸ÷�������ĸ��Char��λ�� ������Ϊ�ǵ�һ�γ���
		if(position == getPositionForSection(section)){
			viewHolder.tvLetter.setVisibility(View.VISIBLE);
			viewHolder.tvLetter.setText(mContent.getSortLetters());
		}else{
			viewHolder.tvLetter.setVisibility(View.GONE);
		}
		imageUrl=imageBaseUrl+this.list.get(position).getcarbrand().getImage();
		Log.i("mapget",this.list.get(position).getcarbrand().getImage());
		bitmap=findBitmap(imageUrl);
		if(bitmap!=null)
		{
			viewHolder.tvImage.setImageBitmap(bitmap);
		}
		else{
       new	ImageTask(viewHolder.tvImage).execute(imageUrl);}
		viewHolder.tvBrand.setText(this.list.get(position).getcarbrand().getType());
		
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

	final static class ViewHolder {
		TextView tvLetter;
		TextView tvBrand;
		ImageView tvImage;
	}


	/**
	 * ���ListView�ĵ�ǰλ�û�ȡ���������ĸ��Char asciiֵ
	 */
	public int getSectionForPosition(int position) {
		return list.get(position).getSortLetters().charAt(0);
	}

	/**
	 * ��ݷ��������ĸ��Char asciiֵ��ȡ���һ�γ��ָ�����ĸ��λ��
	 */
	public int getPositionForSection(int section) {
		for (int i = 0; i < getCount(); i++) {
			String sortStr = list.get(i).getSortLetters();
			char firstChar = sortStr.toUpperCase().charAt(0);
			if (firstChar == section) {
				return i;
			}
		}
		
		return -1;
	}
	
	/**
	 * ��ȡӢ�ĵ�����ĸ����Ӣ����ĸ��#���档
	 * 
	 * @param str
	 * @return
	 */
	private String getAlpha(String str) {
		String  sortStr = str.trim().substring(0, 1).toUpperCase();
		// ������ʽ���ж�����ĸ�Ƿ���Ӣ����ĸ
		if (sortStr.matches("[A-Z]")) {
			return sortStr;
		} else {
			return "#";
		}
	}

	@Override
	public Object[] getSections() {
		return null;
	}
}