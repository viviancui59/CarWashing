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

import ty.entity.OrderList;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.example.ty.adapter.OrderlistAdapter;
import com.example.ty.fragmentdemo.DeleteOrderlistActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

/**
 * Created by Wang JingYuan on 2017/5/6.
 */

public class OrderlistTabTask extends AsyncTask<String, Void, List<OrderList>> {
    private ListView lst_orderlist;
    private Context context;
    private List<OrderList> test;
    private String url;

    public OrderlistTabTask(Context context, ListView lst_orderlist,String url){
        this.lst_orderlist=lst_orderlist;
        this.context=context;
        this.url=url;
    }

    @Override
    protected List<OrderList> doInBackground(String... arg0) {
        url = url+"OrderlistTabServlet";
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("flag", arg0[0]));
        params.add(new BasicNameValuePair("userid", arg0[1]));

        List<OrderList> list=null;
        try {
            request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            System.out.println("开始执行");
            HttpResponse response = client.execute(request);
            System.out.println("成功执行");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                String orderlistGson = EntityUtils.toString(response.getEntity());

                Gson gson = new Gson();

                System.out.println(orderlistGson);

                list = gson.fromJson(orderlistGson,
                        new TypeToken<List<OrderList>>() {
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
    protected void onPostExecute(List<OrderList> result) {

        test = result;
        ListAdapter orderlistAdapter = new OrderlistAdapter(context, result);
        lst_orderlist.setAdapter(orderlistAdapter);

        //setListViewHeightBasedOnChildren(lst_message);
        lst_orderlist.setVisibility(View.VISIBLE);

        lst_orderlist
                .setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0,
                                                   View arg1, int arg2, long arg3) {

                        //长按弹出对话框

                        Dialog alertDialog = new AlertDialog.Builder(context).
                                setTitle("删除").
                                setMessage("您确定删除该订单吗？删除未完成订单将会取消预订。").
                                setPositiveButton("确定", new DeleteOnClickListener(arg2)).
                                setNegativeButton("取消", new DialogInterface.OnClickListener() {

                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        // TODO Auto-generated method stub
                                        dialog.dismiss();
                                    }
                                }).
                                create();
                        alertDialog.show();


                        return false;
                    }
                });


        super.onPostExecute(result);
    }

    public class DeleteOnClickListener implements DialogInterface.OnClickListener
    {

        int position;
        public DeleteOnClickListener(int position) {
            this.position = position;
        }

        @Override
        public void onClick(DialogInterface dialogInterface, int i) {
            // 创建Intent
            Intent intent = new Intent(context,
                    DeleteOrderlistActivity.class);
            intent.putExtra("orderlistid",test.get(position).getOrderid()+"");
            // 启动Activity
            context.startActivity(intent);
            dialogInterface.dismiss();
        }
    };

}
