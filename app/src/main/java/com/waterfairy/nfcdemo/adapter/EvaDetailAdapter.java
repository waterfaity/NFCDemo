package com.waterfairy.nfcdemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.waterfairy.nfcdemo.R;
import com.waterfairy.nfcdemo.activity.EvaluationDetailActivity;
import com.waterfairy.nfcdemo.database.EvaluationDB;
import com.waterfairy.nfcdemo.dialog.ListDialog;
import com.waterfairy.nfcdemo.widget.StartView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by water_fairy on 2017/7/20.
 * 995637517@qq.com
 */

public class EvaDetailAdapter extends BaseAdapter {
    private Context context;
    private List<EvaluationDB> list;

    public EvaDetailAdapter(Context context, List<EvaluationDB> evaluationDBs) {
        this.context = context;
        this.list = evaluationDBs;

    }

    @Override
    public int getCount() {
        if (list == null) return 0;
        else return list.size();
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
        StartView startView1 = (StartView) convertView.findViewById(R.id.star1);
        StartView startView2 = (StartView) convertView.findViewById(R.id.star2);
        StartView startView3 = (StartView) convertView.findViewById(R.id.star3);
        StartView startView4 = (StartView) convertView.findViewById(R.id.star4);
        EvaluationDB evaluationDB = list.get(position);

        startView1.setStar(evaluationDB.getListenScore());
        startView2.setStar(evaluationDB.getReadAloudScore());
        startView3.setStar(evaluationDB.getReadScore());
        startView4.setStar(evaluationDB.getSpeakScore());

        ((TextView) convertView.findViewById(R.id.result)).setText(evaluationDB.getResult());

        ((TextView) convertView.findViewById(R.id.time)).setText("总评 (" + (new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date(evaluationDB.getTime()))) + ")");
        return convertView;
    }
}
