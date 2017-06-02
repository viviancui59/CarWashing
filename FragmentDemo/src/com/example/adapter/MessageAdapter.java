package com.example.adapter;

import java.util.List;

import com.example.fragmentdemo.R;

import ty.entity.Message;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MessageAdapter extends BaseAdapter {

    List<Message> list;
    Context context;

    public MessageAdapter(Context context, List<Message> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list != null) {
            return list.size();
        } else
            return 0;
    }

    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

    	convertView = View.inflate(context, R.layout.layout_messagelst, null);

        final Message msg = list.get(position);

        //获取控件
        ImageView img_msgtype = (ImageView) convertView.findViewById(R.id.img_msgtype);
        TextView txt_msgtype = (TextView) convertView.findViewById(R.id.txt_msgtype);
        TextView txt_msgtime = (TextView) convertView.findViewById(R.id.txt_msgtime);
        TextView txt_msgcontent = (TextView) convertView.findViewById(R.id.txt_msgcontent);
        ImageView img_unread = (ImageView) convertView.findViewById(R.id.img_unread);

        //显示listview内容
        if (msg.getType() == 1) {
            //服务提醒
            img_msgtype.setImageResource(R.drawable.reminder);
            txt_msgtype.setText("服务提醒");
        } else if (msg.getType() == 2) {
            //系统提示
            img_msgtype.setImageResource(R.drawable.notice);
            txt_msgtype.setText("系统提示");
        }
        txt_msgtime.setText(msg.getTime().substring(0,10));
        txt_msgcontent.setText(msg.getContent());
        if (msg.getReadtag() == 1) {
            img_unread.setImageResource(R.drawable.unread);
            img_unread.setVisibility(View.VISIBLE);
        } else {
            img_unread.setVisibility(View.GONE);
        }

        return convertView;
    }
}
