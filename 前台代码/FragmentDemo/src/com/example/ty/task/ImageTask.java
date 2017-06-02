package com.example.ty.task;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.ref.SoftReference;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

public class ImageTask extends AsyncTask<String, Void, Bitmap> {
	private HashMap<String,SoftReference<Bitmap>> imageCache=new HashMap<String,SoftReference<Bitmap>>();
	private String sdcardCacheDir=Environment.getExternalStorageDirectory().getPath()+"/carImage/";

	private ImageView imgUserphoto;
	public ImageTask(ImageView imgUserphoto)
	{
		this.imgUserphoto=imgUserphoto;
	}
	
	@Override
	protected Bitmap doInBackground(String... urlString) {
		// TODO Auto-generated method stub
		if(imageCache.containsKey(urlString[0]))
		{
			SoftReference<Bitmap> reference=imageCache.get(urlString[0]);
			Bitmap bitmap=reference.get();
			if(bitmap!=null)
			{
				Log.i("task>>>已经获取内存图片",">>>已经获取内存图片");
				System.out.println(">>>已经获取内存图片");
				return bitmap;
			}
		}
		
		String bitmapName=urlString[0].substring(urlString[0].lastIndexOf("/")+1);
		File cacheDir=new File(sdcardCacheDir);
		if(!cacheDir.exists())
		{
			cacheDir.mkdirs();
		}
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
				Log.i("task>>>已加载SD缓存图片",">>>已加载SD缓存图片");

				System.out.println(">>已加载SD缓存图片");
				Bitmap bitmap=BitmapFactory.decodeFile(sdcardCacheDir+"/"+bitmapName);
				imageCache.put(urlString[0],new SoftReference<Bitmap>(bitmap));
				return bitmap;
			}
		}
		InputStream is;
		try {
			// TODO Auto-generated method stub
			URL url=new URL(urlString[0]);
			
			is = url.openStream();
			 Bitmap bitmap=BitmapFactory.decodeStream(is);
			is.close();
			
			imageCache.put(urlString[0], new SoftReference<Bitmap>(bitmap));
			
			File dir=new File(sdcardCacheDir);
			if(!dir.exists())
			{
				dir.mkdirs();
			}
			File bitmatFile=new File(sdcardCacheDir+"/"+urlString[0].substring(urlString[0].lastIndexOf("/")+1));
			FileOutputStream fos=new FileOutputStream(bitmatFile);
			bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
			fos.close();
			return bitmap;
			//handler.sendEmptyMessage(1);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	
		return null;
	}
	
	
	@Override
	protected void onPostExecute(Bitmap bitmap) {
		// TODO Auto-generated method stub
		
			imgUserphoto.setImageBitmap(bitmap);
		
		super.onPostExecute(bitmap);
	}

}
