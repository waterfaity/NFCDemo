package com.waterfairy.nfcdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.waterfairy.nfcdemo.R;

import java.util.List;

/**
 * Created by water_fairy on 2017/7/20.
 * 995637517@qq.com
 */

public class ListDialog extends Dialog {
    public static final int TAG_GRADE = 1;
    public static final int TAG_CLASS = 2;
    public static final int TAG_EVA = 3;
    private LinearLayout mRootView;
    private OnSpinnerSelectListener onSelectListener;
    private int tag;

    protected ListDialog(Context context) {
        super(context);
    }

    public ListDialog(Context context, final View targetView, final int tag, int left, int top, int width, final List<String> strings, final OnSpinnerSelectListener listener) {
        super(context, R.style.dialog);
        this.onSelectListener = listener;
        this.tag = tag;
        mRootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_list, null);
        addContentView(mRootView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        LinearLayout.LayoutParams layoutParams1 = (LinearLayout.LayoutParams) mRootView.getChildAt(0).getLayoutParams();
        layoutParams1.leftMargin = left;
        layoutParams1.topMargin = top;
        layoutParams1.width = width;
        ListView listView = (ListView) mRootView.findViewById(R.id.list_view);
        ArrayAdapter<String> adapter = new ArrayAdapter(context, R.layout.item_spinner, R.id.text, strings);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (view != null && view instanceof TextView) {
                    ((TextView) targetView).setText(strings.get(position));
                }
                if (onSelectListener != null) onSelectListener.onSelect(targetView, tag, position);
                dismiss();
            }
        });
        mRootView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public interface OnSpinnerSelectListener {
        void onSelect(View view, int tag, int pos);
    }

    public void setOnSelectListener(OnSpinnerSelectListener onSelectListener) {
        this.onSelectListener = onSelectListener;
    }
}
