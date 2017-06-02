package com.example.ty.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import ty.entity.Goods;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.ty.adapter.GoodsAdapter;
import com.example.ty.fragmentdemo.GoodsPageActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Wang JingYuan on 2017/5/4.
 */

public class GoodsTabTask extends AsyncTask<String, Void, List<Goods>> {
    private ListView lst_goods;
    private Context context;
    private List<Goods> test;
    private String url;

    public GoodsTabTask(Context context, ListView lst_goods,String url){
        this.lst_goods=lst_goods;
        this.context=context;
        this.url=url;
    }

    @Override
    protected List<Goods> doInBackground(String... arg0) {
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url+"GoodsTabServlet");

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("type", arg0[0]));

        List<Goods> list=null;
        try {
            request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            System.out.println("开始执行");
            HttpResponse response = client.execute(request);
            System.out.println("成功执行");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                String goodsListGson = EntityUtils.toString(response.getEntity());

                Gson gson = new Gson();

                System.out.println(goodsListGson);

                list = gson.fromJson(goodsListGson,
                        new TypeToken<List<Goods>>() {
                        }.getType());
                return list;
            } else {
                return null;
            }
        } catch (JsonSyntaxException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (ClientProtocolException e) {
            e.printStackTrace();
        } catch (android.net.ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;
    }


/*

    public void setListViewHeightBasedOnChildren(ListView listView) {

        ListAdapter listAdapter = listView.getAdapter();

        if (listAdapter == null) {

            // pre-condition

            return;

        }

        int totalHeight = 0;

        for (int i = 0; i < listAdapter.getCount(); i++) {

            View listItem = listAdapter.getView(i, null, listView);

            listItem.measure(0, 0);

            totalHeight += listItem.getMeasuredHeight();

        }



        ViewGroup.LayoutParams params = listView.getLayoutParams();

        params.height = totalHeight

                + (listView.getDividerHeight() * (listAdapter.getCount() - 1));

        listView.setLayoutParams(params);

    }

*/



    @Override
    protected void onPostExecute(List<Goods> result) {

        test = result;
        if(test!=null){
            ListAdapter goodslistAdapter = new GoodsAdapter(context, result,url);
            lst_goods.setAdapter(goodslistAdapter);

            lst_goods
                    .setOnItemClickListener(new AdapterView.OnItemClickListener() {

                        @Override
                        public void onItemClick(AdapterView<?> arg0,
                                                View arg1, int arg2, long arg3) {
                            Goods goods=test.get(arg2);
                            Intent intent = new Intent(context,
                                    GoodsPageActivity.class);
                            Bundle bundle=new Bundle();
                            bundle.putInt("goodsid",goods.getGoodsid());
                            bundle.putString("name",goods.getName());
                            bundle.putDouble("price",goods.getPrice());
                            bundle.putInt("type",goods.getType());
                            bundle.putString("image",goods.getImage());
                            bundle.putString("intro",goods.getIntroduction());

                            intent.putExtras(bundle);

                            context.startActivity(intent);

                        }
                    });

            //setListViewHeightBasedOnChildren(lst_message);
            lst_goods.setVisibility(View.VISIBLE);

        }
        super.onPostExecute(result);
    }

}
