package com.fesco.bookpay.weight.dialog;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.util.TypedValue;
import android.view.Gravity;
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
public class ApplyTypsDialogFragment extends DialogFragment {
    public ReimburBillActivity mActivity;
    public Context mContext;
    public RadioButton radio;
    public RadioGroup group;

    public String type_Name;
    public String type_Code;

    public interface ApplyTypsDialogListener {

        void onApplyTypeListener(String typeName, String typeCode);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (ReimburBillActivity) context;
        Logger.d("mActivity:" + mActivity);
        System.out.println("mActivity---:" + mActivity);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
        final View view = inflater.inflate(R.layout.dialog_applytype, container);
        TextView title = (TextView) view.findViewById(R.id.dialog_title);
        title.setText("请选择模板");
        //   LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear_dialog_apply);
        Bundle bundle = getArguments();
        final List<ConsumptionBean.ApplyTypesBean> applyTypesBeen = (List<ConsumptionBean.ApplyTypesBean>) bundle.getSerializable(ReimburBillActivity.KEY);
        Logger.d(applyTypesBeen.get(0).getType_Name());
//        for ( int i = 0; i < applyTypesBeen.size(); i++) {
//            final String typeName= applyTypesBeen.get(i).getType_Name();
//            final String type_Code= Integer.toString(applyTypesBeen.get(i).getType_Code());
//            TextView textView = new TextView(getActivity());
//            textView.setTextSize(TypedValue.COMPLEX_UNIT_SP,16); //22SP
//            LinearLayout.LayoutParams textLayoutParams = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//
//            textView.setText(typeName);
//            if(i==0){
//                textView.setPadding(0, 39, 0, 6);
//            }else if(i==applyTypesBeen.size()-1){
//                textView.setPadding(0, 9, 0, 36);
//            }else
//            textView.setPadding(0, 9, 0, 6);
//            textView.setTextColor(getResources().getColor(R.color.bookBlue));
//            textView.setOnClickListener(new View.OnClickListener() {
//
//                @Override
//                public void onClick(View v) {
//                    getDialog().cancel();
//                    ApplyTypsDialogListener dialogListener = (ApplyTypsDialogListener) getActivity();
//                    dialogListener.onApplyTypeListener(typeName,type_Code);
//                }
//            });
//         //   linearLayout.addView(textView, textLayoutParams);
//
//            ImageView imageView = new ImageView(getActivity());
//            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
//                    ViewGroup.LayoutParams.WRAP_CONTENT, 5);
//            imageView.setBackgroundResource(R.color.bookBlue);
//         //   linearLayout.addView(imageView, imageParams);
//        }

        group = (RadioGroup) view.findViewById(R.id.radioGroup);

//      LinearLayout linearLayout = (LinearLayout) view.findViewById(R.id.linear_dialog_apply);
        // ScrollView scrollView = (ScrollView) view.findViewById(R.id.linear_dialog_apply);
        //Bundle bundle = getArguments();
        //  final List<ConsumptionBean.ApprovalManListBean> approvalManListBeen= (List<ConsumptionBean.ApprovalManListBean>) bundle.getSerializable(ReimburBillActivity.APPLY_PERSON);
        // Logger.d(approvalManListBeen.get(0).getGroup_Name());

        for (int i = 0; i < applyTypesBeen.size(); i++) {
            RadioButton radio = new RadioButton(getActivity());

            //   radioButton.setButtonDrawable(android.R.color.transparent);//隐藏单选圆形按钮



            RadioGroup.LayoutParams layoutParams = new RadioGroup.LayoutParams(RadioGroup.LayoutParams.WRAP_CONTENT,100);
            radio.setGravity(Gravity.CENTER);
            radio.setText(applyTypesBeen.get(i).getType_Name());
            radio.setLayoutParams(layoutParams);
            radio.setTextSize(TypedValue.COMPLEX_UNIT_SP, 16); //22SP
            radio.setTextColor(getResources().getColor(R.color.white));
       //     radio.setButtonDrawable(android.R.color.transparent);
            radio.setButtonDrawable(R.drawable.btn_checkbox_bill);
            ImageView imageView = new ImageView(getActivity());
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, 2);
            imageView.setImageResource(R.drawable.dialog_line);
            imageParams.setMargins(0, 2, 10, 0);

            if (i == 0) {
                radio.setPadding(60, 12, 0, 6);
          //      radio.setChecked(true);
                type_Code = Integer.toString(applyTypesBeen.get(i).getType_Code());
                type_Name = applyTypesBeen.get(i).getType_Name();

            } else if (i == applyTypesBeen.size() - 1) {
                radio.setPadding(60, 9, 0, 6);
                group.addView(imageView, imageParams);
            } else {
                radio.setPadding(60, 12, 0, 6);
               group.addView(imageView, imageParams);
            }
            group.addView(radio);
        }

        group.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {

            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                // TODO Auto-generated method stub
                RadioButton rb = (RadioButton) view.findViewById(checkedId);

                if (!rb.isPressed()) {
                    return;
                }

                if (rb.isChecked()) {
                    for (ConsumptionBean.ApplyTypesBean applyTypesBean : applyTypesBeen) {
                        if (rb.getText().equals(applyTypesBean.getType_Name())) {
                          //  rb.setChecked(true);
                            group.check(rb.getId());
                            type_Code = Integer.toString(applyTypesBean.getType_Code());
                            type_Name = applyTypesBean.getType_Name();
                            //  getDialog().cancel();
                        }
                    }

                }
            }

        });


        Button btnSumbit = (Button) view.findViewById(R.id.dialog_sumbit);

        btnSumbit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
                ApplyTypsDialogListener dialogListener = (ApplyTypsDialogListener) getActivity();
                dialogListener.onApplyTypeListener(type_Name, type_Code);
            }
        });

        Button btnCancle = (Button) view.findViewById(R.id.dialog_cancel);

        btnCancle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();


            }
        });

        return view;
    }
//    @Override
//    public void onStart() {
//        super.onStart();
//        Dialog dialog = getDialog();
//        if (dialog != null) {
//            DisplayMetrics dm = new DisplayMetrics();
//            getActivity().getWindowManager().getDefaultDisplay().getMetrics(dm);
//            dialog.getWindow().setLayout((int) (dm.widthPixels * 0.75), ViewGroup.LayoutParams.WRAP_CONTENT);
//        }
//        Logger.d("onStart:"+dialog.isShowing());
//
//    }


}
