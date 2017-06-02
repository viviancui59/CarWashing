package com.example.task;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import ty.entity.Message;
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

import com.example.adapter.MessageAdapter;
import com.example.fragmentdemo.DeleteMessageActivity;
import com.example.fragmentdemo.MessagePageActivity;
import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

public class ShowMessageTask extends AsyncTask<String, Void, List<Message>> {
    private ListView lst_message;
    private Context context;
    private List<Message> test;

    public ShowMessageTask(Context context, ListView lst_message){
        this.lst_message=lst_message;
        this.context=context;
    }

    @Override
    protected List<Message> doInBackground(String... arg0) {
        String url = "http://10.0.2.2:8080/CarWashing/ShowMessageServlet";
        HttpClient client = new DefaultHttpClient();
        HttpPost request = new HttpPost(url);

        List<NameValuePair> params = new ArrayList<NameValuePair>();
        params.add(new BasicNameValuePair("userid", arg0[0]));

        List<Message> list=null;
        try {
            request.setEntity(new UrlEncodedFormEntity(params, "UTF-8"));
            System.out.println("开始执行");
            HttpResponse response = client.execute(request);
            System.out.println("成功执行");
            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {

                String msgListGson = EntityUtils.toString(response.getEntity());

                Gson gson = new Gson();

                System.out.println(msgListGson);

                list = gson.fromJson(msgListGson,
                        new TypeToken<List<Message>>() {
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
        } catch (ParseException e) {
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
    protected void onPostExecute(List<Message> result) {

        test = result;
        ListAdapter msglistAdapter = new MessageAdapter(context, result);
        lst_message.setAdapter(msglistAdapter);

        lst_message
                .setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0,
                                            View arg1, int arg2, long arg3) {
                        Intent intent = new Intent(context,
                                MessagePageActivity.class);
                        intent.putExtra("message", test.get(arg2));

                        context.startActivity(intent);
                        System.out.println(arg2);
                    }
                });

        //setListViewHeightBasedOnChildren(lst_message);
        lst_message.setVisibility(View.VISIBLE);

        lst_message
                .setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                    @Override
                    public boolean onItemLongClick(AdapterView<?> arg0,
                                                   View arg1, int arg2, long arg3) {

                        //长按弹出对话框

                        Dialog alertDialog = new AlertDialog.Builder(context).
                                setTitle("删除").
                                setMessage("您确定删除该条消息提示吗？").
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
                    DeleteMessageActivity.class);
            intent.putExtra("messageid",test.get(position).getMessageid()+"");
            // 启动Activity
            context.startActivity(intent);
            dialogInterface.dismiss();
        }
    };

}
