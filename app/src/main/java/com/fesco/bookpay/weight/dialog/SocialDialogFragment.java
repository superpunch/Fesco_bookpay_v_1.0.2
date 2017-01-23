package com.fesco.bookpay.weight.dialog;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.activity.ReimburBillActivity;
import com.fesco.bookpay.fragment.tabfragment.HelpFragment;

/**
 * Created by gong.min on 2016/11/10.
 */
public class SocialDialogFragment extends DialogFragment {
    public ReimburBillActivity mActivity;
    public Context mContext;
    public RadioButton radio;
    public RadioGroup group;
    public String emp_Id;
    public String emp_Name;
    public boolean flag=true;

    public static final String RESPONSE_CHOOSE = "response_choose";


    public interface ChooseDialogListener {
        void onChooseListener(boolean flag);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
         View view = inflater.inflate(R.layout.dialog_hlep_social, container);
        group = (RadioGroup) view.findViewById(R.id.dialog_social_radiogroup);
        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                switch (checkedId) {
                    case R.id.dialog_social_yes:
                        flag = true;
                        Log.e("Fragment", "group"+flag);
                        break;
                    case R.id.dialog_social_no:
                        flag = false;
                        Log.e("Fragment", "group"+flag);
                        break;

                }

            }

        });

        Button btnSumbit = (Button) view.findViewById(R.id.dialog_social_sumbit);
        Button btnCancel = (Button) view.findViewById(R.id.dialog_social_cancel);
        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                setResult();
                getDialog().cancel();
//                ChooseDialogListener dialogListener = (ChooseDialogListener) getActivity();
//                dialogListener.onChooseListener(flag);

            }
        });

        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                flag = false;
                getDialog().cancel();
            }
        });


        return view;
    }


    // 设置返回数据
    protected void setResult() {
        // 判断是否设置了targetFragment
        if (getTargetFragment() == null)
            return;
        Intent intent = new Intent();
        intent.putExtra(RESPONSE_CHOOSE, flag);
        getTargetFragment().onActivityResult(HelpFragment.REQUEST_CHOOSE,
                Activity.RESULT_OK, intent);

    }

}
