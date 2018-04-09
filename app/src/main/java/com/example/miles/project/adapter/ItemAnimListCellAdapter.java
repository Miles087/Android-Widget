package com.example.miles.project.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.miles.project.R;

import java.util.List;

/**
 * Created by Server on 2018/4/9.
 */

public class ItemAnimListCellAdapter extends BaseAdapter {

    private Context mContext;
    private List<AnimListCell> list;


    public ItemAnimListCellAdapter(Context context, List<AnimListCell> list){
        this.mContext=context;
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
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        final ViewHolder holder;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.item_anim_list,null);
            holder = new ViewHolder();
            holder.tv_time = (TextView)view.findViewById(R.id.tv_time);
            holder.tv_content = (TextView)view.findViewById(R.id.tv_content);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        final AnimListCell item = list.get(i);
        holder.tv_time.setText(item.getStrTime());
        holder.tv_content.setText(item.getStrContent());
        return view;
    }

    static class ViewHolder {
        TextView tv_time;
        TextView tv_content;
    }
}
