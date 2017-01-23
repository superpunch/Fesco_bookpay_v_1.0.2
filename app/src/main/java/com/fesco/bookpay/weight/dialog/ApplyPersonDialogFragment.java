package com.fesco.bookpay.weight.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.activity.ReimburBillActivity;
import com.fesco.bookpay.entity.ConsumptionBean;
import com.orhanobut.logger.Logger;

import java.util.List;

/**
 * Created by gong.min on 2016/11/10.
 */
public class ApplyPersonDialogFragment extends DialogFragment {
    public ReimburBillActivity mActivity;
    public Context mContext;
    public   RadioButton radio;
    public   RadioGroup group;
    public String emp_Id;
    public  String emp_Name;

    public interface ApplyPersonDialogListener {
        void onApplyPersonListener(String typeName, String typeCode);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (ReimburBillActivity) context;
        Logger.d("mActivity:"+mActivity);
        System.out.println("mActivity---:"+mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
          getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
        final View view = inflater.inflate(R.layout.dialog_applytype, container);
        TextView title = (TextView) view.findViewById(R.id.dialog_title);
        title.setText("请选择审批人");
        group = (RadioGroup)view.findViewById(R.id.radioGroup);
        Bundle bundle = getArguments();
        final List<ConsumptionBean.ApprovalManListBean> approvalManListBeen= (List<ConsumptionBean.ApprovalManListBean>) bundle.getSerializable(ReimburBillActivity.APPLY_PERSON);

        for ( int i = 0; i < approvalManListBeen.size(); i++) {
            RadioButton    radio = new RadioButton(getActivity());
            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,100);
            radio.setLayoutParams(layoutParams);
            ImageView imageView=new ImageView(getActivity());
                        LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, 2);
            imageView.setImageResource(R.drawable.dialog_line);
            imageParams.setMargins(0,2,5,0);
            radio.setText( approvalManListBeen.get(i).getEmp_Name());
            radio.setTextColor(getResources().getColor(R.color.white));
            radio.setButtonDrawable(R.drawable.btn_checkbox_bill);

            if(i==0){
                radio.setPadding(60, 0, 0, 6);
                emp_Id=Integer.toString(approvalManListBeen.get(i).getEmp_Id());
                emp_Name=approvalManListBeen.get(i).getEmp_Name();
            }else if(i==approvalManListBeen.size()-1){
                radio.setPadding(60, 9, 0, 6);
            }else {
                radio.setPadding(60, 12, 0, 6);
                group.addView(imageView, imageParams);
            }

            group.addView(radio);
        }

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton rb=(RadioButton)view.findViewById(checkedId);
                if (!rb.isPressed()) {
                    return;
                }
                if(rb.isChecked()){
                    for(ConsumptionBean.ApprovalManListBean approvalManListBean:approvalManListBeen){
                        if(rb.getText().equals(approvalManListBean.getEmp_Name())){
                          //  rb.setChecked(true);
                             group.check(rb.getId());
                             emp_Id=Integer.toString(approvalManListBean.getEmp_Id());
                             emp_Name=approvalManListBean.getEmp_Name();
                          //  getDialog().cancel();
                        }
                    }

                }
            }

        });



     Button btnSumbit= (Button) view.findViewById(R.id.dialog_sumbit);

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
                ApplyPersonDialogListener dialogListener = (ApplyPersonDialogListener) getActivity();
                dialogListener.onApplyPersonListener(emp_Name,emp_Id);
            }
        });

        Button btnCancle= (Button) view.findViewById(R.id.dialog_cancel);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();


            }
        });



        return view;
    }




}
