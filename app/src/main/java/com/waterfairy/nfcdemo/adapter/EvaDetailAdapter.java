package com.waterfairy.nfcdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.activity.EvaluationDetailActivity;

import java.util.List;

/**
 * Created by water_fairy on 2017/7/20.
 * 995637517@qq.com
 */

public class EvaDetailAdapter extends BaseAdapter {
    private Context context;

    public EvaDetailAdapter(Context context) {
        this.context=context;
    }

    @Override
    public int getCount() {
        return 20;
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
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.item_detail, parent, false);
        }
        return convertView;
    }
}
