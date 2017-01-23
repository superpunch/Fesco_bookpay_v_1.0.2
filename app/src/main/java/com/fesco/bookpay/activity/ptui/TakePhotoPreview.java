package com.fesco.bookpay.activity.ptui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.fesco.bookpay.activity.ConsumptionActivity;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.photoadapter.TakePhotoPreviewAdapter;
import com.fesco.bookpay.event.TakePhotoEvent;
import com.fesco.bookpay.util.ptutils.Constants;
import com.fesco.bookpay.weight.ViewPagerFixed;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;


/**
 * Created by KCrason on 2016/6/7.
 */

public class TakePhotoPreview extends AppCompatActivity implements OnClickListener {

    private RelativeLayout rl_title_bar;
    private ViewPagerFixed viewpager;
    private ArrayList<String> mDataList;
    private Button previewCommit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_photo_preview);
        init();
    }


    public void onBack(View view) {
        cancelResult();
    }

    @Override
    public void onBackPressed() {
        cancelResult();
    }

    private void cancelResult() {
        EventBus.getDefault().post(new TakePhotoEvent(mDataList.size() - 1));
        finish();
        overridePendingTransition(R.anim.selecter_image_alpha_enter, R.anim.selecter_image_alpha_exit);
    }

    private void init() {
        mDataList = getIntent().getStringArrayListExtra(Constants.EXTRA_RESULT);
        rl_title_bar = (RelativeLayout) findViewById(R.id.rl_title_bar);
        viewpager = (ViewPagerFixed) findViewById(R.id.viewpager);
        previewCommit = (Button) findViewById(R.id.preview_commit);
        previewCommit.setText(getString(R.string.finish));
        previewCommit.setOnClickListener(this);
        viewpager.setAdapter(new TakePhotoPreviewAdapter(this, mDataList, rl_title_bar));
    }

    @Override
    public void onClick(View arg0) {
        switch (arg0.getId()) {
            case R.id.preview_commit:
//                Bundle bundle = new Bundle();
//                bundle.putStringArrayList(Constants.EXTRA_RESULT, mDataList);
//                KUtils.actionStart(this, ConsumptionActivity.class, bundle);
                Intent intent = new Intent(this,ConsumptionActivity.class);
                intent.putStringArrayListExtra(Constants.EXTRA_RESULT, mDataList);
                startActivity(intent);

                overridePendingTransition(R.anim.selecter_image_alpha_enter, R.anim.selecter_image_alpha_exit);
             //   ActivityManager.getInstance().finishActivitys();
                finish();
                break;
            default:
                break;
        }
    }
}
