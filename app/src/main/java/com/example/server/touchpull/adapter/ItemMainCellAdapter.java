package com.example.server.touchpull.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.server.touchpull.R;

import java.util.List;

/**
 * Created by Server on 2018/1/22.
 */

public class ItemMainCellAdapter extends BaseAdapter {
    private Context context;
    private List<ItemActivity> list;

    public ItemMainCellAdapter(Context context, List<ItemActivity> list){
        this.context=context;
        this.list=list;

    }

    @Override
    public int getCount() {
        return list.size();
    }

    @Override
    public Object getItem(int i) {
        return list.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(context).inflate(R.layout.item_main,null);
            holder = new ViewHolder();
            holder.tv_title = (TextView)view.findViewById(R.id.tv_title);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final ItemActivity item = list.get(i);
        holder.tv_title.setText(item.getStrName());
        return view;
    }

    static class ViewHolder {
        TextView tv_title;
    }
}
