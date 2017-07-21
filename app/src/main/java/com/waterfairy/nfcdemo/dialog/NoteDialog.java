package com.waterfairy.nfcdemo.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.waterfairy.nfcdemo.R;

/**
 * Created by water_fairy on 2017/7/20.
 * 995637517@qq.com
 */

public class NoteDialog extends Dialog {
    private LinearLayout mRootView;

    public NoteDialog(@NonNull Context context, String content) {
        super(context, R.style.dialog3);
        mRootView = (LinearLayout) LayoutInflater.from(context).inflate(R.layout.dialog_note, null);
        addContentView(mRootView, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        ((TextView) mRootView.findViewById(R.id.dialog_note_content)).setText(content);
        initView();
    }

    private void initView() {
        OnDismissClickListener onDismissClickListener = new OnDismissClickListener();
        mRootView.findViewById(R.id.close).setOnClickListener(onDismissClickListener);
        mRootView.setOnClickListener(onDismissClickListener);
    }


    public class OnDismissClickListener implements View.OnClickListener {

        @Override
        public void onClick(View v) {
            dismiss();
        }
    }
}