package com.example.ty.adapter;

import java.util.List;

import ty.entity.OrderList;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.ty.fragmentdemo.R;

public class OrderlistAdapter extends BaseAdapter {

    List<OrderList> list;
    Context context;

    public OrderlistAdapter(Context context, List<OrderList> list) {
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

        convertView = View.inflate(context, R.layout.layout_orderlistlst, null);

        final OrderList orderlist = list.get(position);

        //获取控件
        TextView txt_orderid=(TextView)convertView.findViewById(R.id.txt_orderid);
        TextView txt_ordertime=(TextView)convertView.findViewById(R.id.txt_ordertime);
        TextView txt_ordertype=(TextView)convertView.findViewById(R.id.txt_ordertype);
        TextView txt_orderplate=(TextView)convertView.findViewById(R.id.txt_orderplate);
        TextView txt_orderprice=(TextView)convertView.findViewById(R.id.txt_orderprice);
        TextView txt_orderstore=(TextView)convertView.findViewById(R.id.txt_orderstore);

        //显示listview内容
        txt_orderid.setText("订单编号："+orderlist.getOrderid());
        txt_ordertime.setText(orderlist.getTime());
        if(orderlist.getType()==1){
            //1--洗车 2--打蜡 3--保养
            txt_ordertype.setText("洗车服务");
        }else if(orderlist.getType()==2){
            txt_ordertype.setText("打蜡服务");
        }else if(orderlist.getType()==3){
            txt_ordertype.setText("保养服务");
        }
        txt_orderplate.setText(orderlist.getCarplate());
        txt_orderprice.setText("金额：¥"+orderlist.getPrice());
        txt_orderstore.setText("店面："+orderlist.getStorename());

        return convertView;
    }

}
