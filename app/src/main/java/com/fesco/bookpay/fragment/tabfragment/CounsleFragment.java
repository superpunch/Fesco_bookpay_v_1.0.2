package com.fesco.bookpay.fragment.tabfragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fesco.bookpay.activity.R;

/**
 * Created by gong.min on 2016/10/19.
 */
public class CounsleFragment extends Fragment {
private     View view ;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        if(view ==null){
            view=      inflater.inflate(R.layout.tab_bottom_counsle,container,false);
        }
//        EditText mEditText= (EditText) view.findViewById(R.id.url_edittext);
//        mEditText.setFocusable(true);
//        mEditText.setFocusableInTouchMode(true);
//     //   mEditText.requestFocus();//获取焦点 光标出现
//        mEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View v, boolean hasFocus) {
//                Log.e("Fragment", "Counsle is MotionEvent.hasFocus"+hasFocus);
//            }
//        });
        return view;
    }
}
