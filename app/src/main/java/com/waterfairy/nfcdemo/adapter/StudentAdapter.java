package com.waterfairy.nfcdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.widget.FiveView;

import java.util.List;

/**
 * Created by water_fairy on 2017/7/20.
 * 995637517@qq.com
 */

public class StudentAdapter extends BaseAdapter {
    private Context context;
    private List<String> list;

    public StudentAdapter(Context context, List<String> list) {
        this.context = context;
        this.list = list;
    }

    @Override
    public int getCount() {
        if (list == null) return 0;
        return list.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_grid_student, parent, false);
        }
        View view = convertView.findViewById(R.id.card_view);
        view.setTag(position);
        view.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                onItemClickListener.onItemClick(null, v, (int) v.getTag(), 0);
            }
        });
        ImageView imageView = (ImageView) convertView.findViewById(R.id.student_head_icon);
        imageView.setImageResource(R.mipmap.ic_launcher);
        TextView textView = (TextView) convertView.findViewById(R.id.student_name);
        textView.setText(list.get(position));
        return convertView;
    }

    AdapterView.OnItemClickListener onItemClickListener;

    public void setOnItemClickListener(AdapterView.OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;

    }
}
