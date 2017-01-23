package com.fesco.bookpay.fragment.tabfragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.activity.SocialActivity;
import com.fesco.bookpay.weight.dialog.SocialDialogFragment;

/**
 * Created by gong.min on 2016/10/19.
 */
public class HelpFragment extends Fragment {
    private View view;
    public static final String CHOOSE_DIALOG = "choose_dialog";
    public static final int REQUEST_CHOOSE = 0X110;
    private Context context;
    private Activity mActivity;
    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
        this.mActivity = (Activity) context;

    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (view == null) {
            view = inflater.inflate(R.layout.tab_bottom_help, container, false);
        }

        LinearLayout linear_social = (LinearLayout) view.findViewById(R.id.linear_social);

        linear_social.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SocialDialogFragment dialog = new SocialDialogFragment();
                //注意setTargetFragment
                dialog.setTargetFragment(HelpFragment.this, REQUEST_CHOOSE);
                dialog.show(getFragmentManager(), CHOOSE_DIALOG);
            }
        });

        return view;


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CHOOSE) {
            boolean falg = data.getBooleanExtra(SocialDialogFragment.RESPONSE_CHOOSE,false);
            if (falg) {
                Intent intent = new Intent(mActivity, SocialActivity.class);
                startActivity(intent);
            }

            //	getActivity().setResult(ListTitleFragment.REQUEST_DETAIL, intent);


        }


    }

}
