package com.fesco.bookpay.view;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;

/**
 * Created by gong.min on 2016/9/26.
 */
public class CustomDialogCheck extends Dialog {
    private EditText editText;
    private Button positiveButton, negativeButton;
    private TextView title;

    public CustomDialogCheck(Context context) {
        super(context,R.style.Dialog);
        setCustomDialog();
    }



    private void setCustomDialog() {
        View mView = LayoutInflater.from(getContext()).inflate(R.layout.dialog_normal_layout, null);
        editText = (EditText) mView.findViewById(R.id.number);
        positiveButton = (Button) mView.findViewById(R.id.positiveButton);
        negativeButton = (Button) mView.findViewById(R.id.negativeButton);
        super.setContentView(mView);
    }

    public View getEditText(){
        return editText;
    }

    @Override
    public void setContentView(int layoutResID) {
    }


    @Override
    public void setContentView(View view) {
    }

    /**
     * 确定键监听器
     * @param listener
     */
    public void setOnPositiveListener(View.OnClickListener listener){
        positiveButton.setOnClickListener(listener);
    }
    /**
     * 取消键监听器
     * @param listener
     */
    public void setOnNegativeListener(View.OnClickListener listener){
        negativeButton.setOnClickListener(listener);
    }
}
