package com.fesco.bookpay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.TypedValue;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RadioButton;
import android.widget.RadioGroup;

/**
 * Created by gong.min on 2016/12/1.
 */
public class ScrollViewActivity  extends AppCompatActivity {


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.test_scrollview);

        RadioGroup group2 = (RadioGroup) this.findViewById(R.id.radioGroup2);
        RadioGroup group3 = (RadioGroup) this.findViewById(R.id.radioGroup3);

        for(int i=0;i<3;i++){
            RadioButton radio = new RadioButton(this);
//            radioButton.setButtonDrawable(android.R.color.transparent);//隐藏单选圆形按钮
//            radioButton.setPadding(10, 10, 10, 10);
//            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//                radioButton.setBackground(getResources().getDrawable(R.drawable.btn_checkbox_selector));//设置按钮选中/未选中的背景  (R.drawable.btn_checkbox_selector);
//            }else {
//                radioButton.setBackgroundDrawable(getResources().getDrawable(R.drawable.btn_checkbox_selector));
//            }
            radio.setBackgroundResource(R.drawable.btn_map);   // 设置RadioButton的背景图片
            radio.setButtonDrawable(R.drawable.btn_checkbox_selector);           // 设置按钮的样式
            radio.setPadding(80, 0, 0, 0);                 // 设置文字距离按钮四周的距离
            radio.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22); //22SP
            radio.setText("测试"+i);
            group2.addView(radio);
        }

        for(int i=0;i<3;i++){
            RadioButton radio = new RadioButton(this);
         //   radioButton.setButtonDrawable(android.R.color.transparent);//隐藏单选圆形按钮
            radio.setBackgroundResource(R.drawable.btn_map);   // 设置RadioButton的背景图片
            radio.setButtonDrawable(R.drawable.btn_checkbox_selector);           // 设置按钮的样式
            radio.setPadding(60, 5, 0, 5);
            radio.setTextSize(TypedValue.COMPLEX_UNIT_SP, 22); //22SP
            radio.setText("测试a"+i);

            ImageView imageView = new ImageView(this);
            LinearLayout.LayoutParams imageParams = new LinearLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, 5);
            imageView.setImageResource(R.drawable.dialog_line);
            imageParams.setMargins(10, 5, 10, 0);
            group3.addView(imageView, imageParams);
            group3.addView(radio);
        }

    }





}
