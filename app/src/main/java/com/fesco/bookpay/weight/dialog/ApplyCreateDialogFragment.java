package com.fesco.bookpay.weight.dialog;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;

/**
 * Created by gong.min on 2016/11/10.
 */
public class ApplyCreateDialogFragment extends DialogFragment {

    public interface DialogListener {

        void onNewTypeListener();
        void onImportTypeListener();


    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE); //去除标题栏
        View view = inflater.inflate(R.layout.dialog_consumption, container);
        TextView cspNew = (TextView) view.findViewById(R.id.dialog_new);
        TextView cspImport = (TextView) view.findViewById(R.id.dialog_import);
        TextView cspCancel = (TextView) view.findViewById(R.id.dialog_cancel);
        cspNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
                DialogListener dialogListener= (DialogListener) getActivity();
                dialogListener.onNewTypeListener();
            }
        });
        cspImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().cancel();
                DialogListener dialogListener= (DialogListener) getActivity();
                dialogListener.onImportTypeListener();
            }
        });

        cspCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //    getDialog().setCanceledOnTouchOutside(true);
                Log.e("Fragment", "cspCancel");
                getDialog().cancel();
            }
        });
        return view;
    }
}
