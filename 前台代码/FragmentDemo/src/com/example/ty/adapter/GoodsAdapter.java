package com.example.ty.adapter;

import java.util.List;

import ty.entity.Goods;
import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ty.fragmentdemo.R;
import com.example.ty.task.LoadBitmapTask;

public class GoodsAdapter extends BaseAdapter {

    List<Goods> list;
    Context context;
    String url;

    public GoodsAdapter(Context context, List<Goods> list,String url) {
        this.context = context;
        this.list = list;
        this.url=url+"image/";
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

        convertView = View.inflate(context, R.layout.layout_goodslst, null);

        final Goods goods = list.get(position);

        //获取控件
        ImageView img_goods = (ImageView) convertView.findViewById(R.id.img_goods);
        TextView txt_goodsname = (TextView) convertView.findViewById(R.id.txt_goodsname);
        TextView txt_goodsprice = (TextView) convertView.findViewById(R.id.txt_goodsprice);

        //显示listview内容
        txt_goodsname.setText(goods.getName());
        txt_goodsprice.setText("¥"+goods.getPrice());
        //加载图片
        new LoadBitmapTask(img_goods).execute(url+goods.getImage());

        return convertView;
    }
}
