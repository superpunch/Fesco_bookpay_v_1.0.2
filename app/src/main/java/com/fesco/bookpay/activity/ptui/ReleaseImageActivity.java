package com.fesco.bookpay.activity.ptui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.photoadapter.ImagePublishAdapter;
import com.fesco.bookpay.event.RemoveImageEvent;
import com.fesco.bookpay.util.ptutils.ActivityManager;
import com.fesco.bookpay.util.ptutils.Constants;
import com.fesco.bookpay.util.ptutils.KUtils;
import com.fesco.bookpay.util.ptutils.ShowUtils;
import com.fesco.bookpay.util.ptutils.SnackBarUtils;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.io.Serializable;
import java.lang.ref.WeakReference;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by KCrason on 2016/6/7.
 */
public class ReleaseImageActivity extends AppCompatActivity
        implements AdapterView.OnItemClickListener, DialogInterface.OnClickListener {

    private static final int MSG_PB = 0x01;

    private final static int REQUEST_IMAGE = 3;

    private ImagePublishAdapter mAdapter;

    private ProgressDialog progressDialog;

    private ArrayList<String> mSelectPath = new ArrayList<>();

    private MessageHandler mMessageHandler;

    @BindView(R.id.rootView)
    LinearLayout mRootView;

    @BindView(R.id.txt_send)
    TextView mSendImage;



    @BindView(R.id.gv_images)
    GridView mGridView;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
        setContentView(R.layout.activity_relese_image);
        ButterKnife.bind(this);
        initViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAdapter.notifyDataSetChanged();
    }

    @OnClick({R.id.back, R.id.txt_send})
    void onClicks(View view) {
        switch (view.getId()) {
            case R.id.back:
                if (mSelectPath.size() != 0) {
                    ShowUtils.showDialog(this, getString(R.string.exit_edit), this);
                } else {
                    recoveryActivitys();
                }
                break;
            case R.id.txt_send:
                sendSelectedImages();
                break;
        }
    }


    @Override
    protected void onDestroy() {
        EventBus.getDefault().unregister(this);
        if (progressDialog != null && progressDialog.isShowing()) {
            progressDialog.dismiss();
        }
        super.onDestroy();
    }

    private void selectAblum() {
        Intent intent = new Intent(ReleaseImageActivity.this, KSelectImagesActivity.class);
        // 是否显示拍摄图片
        intent.putExtra(Constants.EXTRA_SHOW_CAMERA, true);
        // 最大可选择图片数量
        intent.putExtra(Constants.EXTRA_SELECT_COUNT, 5);
        // 选择模式
        intent.putExtra(Constants.EXTRA_SELECT_MODE, KSelectImagesActivity.MODE_MULTI);
        // 默认选择
        intent.putExtra(Constants.EXTRA_DEFAULT_SELECTED_LIST, mSelectPath);

        startActivityForResult(intent, REQUEST_IMAGE);
        overridePendingTransition(R.anim.selecter_image_alpha_enter, R.anim.selecter_image_alpha_exit);
    }


    public void initViews() {
        ButterKnife.bind(this);
        ActivityManager.getInstance().addActivity(this);
        mMessageHandler = new MessageHandler(this);

        Bundle bundle = getIntent().getBundleExtra(Constants.KEY);
        if (bundle != null) {
            mSelectPath = bundle.getStringArrayList(Constants.EXTRA_RESULT);
        }

        mGridView.setSelector(new ColorDrawable(Color.TRANSPARENT));
        mAdapter = new ImagePublishAdapter(this, mSelectPath);
        mGridView.setAdapter(mAdapter);
        mGridView.setOnItemClickListener(this);
    }


    private int getDataSize() {
        return mSelectPath == null ? 0 : mSelectPath.size();
    }

    private void recoveryActivitys() {
        if (mSelectPath != null) {
            mSelectPath.clear();
        }
        ActivityManager.getInstance().finishActivitys();
    }


    private void sendSelectedImages() {
        if (mSelectPath.size() != 0) {
            if (KUtils.isNetworkAvailable()) {
        //        KUtils.putAwaySoftKeyboard(ReleaseImageActivity.this, mInputContent);
                /**
                 * 遍历图片地址，如果发现有空值的则无法上传
                 */
                for (String fp : mSelectPath) {
                    Log.e("Fragment","路径: "+fp);
                    if (TextUtils.isEmpty(fp)) {
                        SnackBarUtils.showSnackBar(mRootView, getString(R.string.image_error));


                        return;
                    }
                }
                progressDialog = ShowUtils.showProgressDialog(this, getString(R.string.loading));

                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            Thread.sleep(3000);
                            mMessageHandler.sendEmptyMessage(MSG_PB);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            } else {
                SnackBarUtils.showSnackBar(mRootView, getString(R.string.not_network));
            }
        } else {
            SnackBarUtils.showSnackBar(mRootView, getString(R.string.at_least_one_photo));
        }
    }


    static class MessageHandler extends Handler {

        WeakReference<Activity> mWeakReference;

        public MessageHandler(ReleaseImageActivity releaseImageActivity) {
            this.mWeakReference = new WeakReference<Activity>(releaseImageActivity);
        }

        @Override
        public void handleMessage(Message msg) {
            ReleaseImageActivity releaseImageActivity = (ReleaseImageActivity) this.mWeakReference.get();
            switch (msg.what) {
                case MSG_PB:
                    SnackBarUtils.showSnackBar(releaseImageActivity.mRootView, releaseImageActivity.getString(R.string.upload_sucess));
                    releaseImageActivity.progressDialog.dismiss();
                    break;
            }
            super.handleMessage(msg);
        }
    }



    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        if (position == getDataSize()) {
        //    KUtils.putAwaySoftKeyboard(ReleaseImageActivity.this, mInputContent);
            selectAblum();
        } else {
            Intent intent = new Intent(ReleaseImageActivity.this, ImageZoomActivity.class);
            intent.putExtra(Constants.EXTRA_RESULT, (Serializable) mSelectPath);
            intent.putExtra(Constants.EXTRA_CURRENT_IMG_POSITION, position);
            startActivity(intent);
            overridePendingTransition(R.anim.selecter_image_alpha_enter, R.anim.selecter_image_alpha_exit);
        }
    }


    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK) {
            if (progressDialog != null && progressDialog.isShowing()) {
                progressDialog.dismiss();
            }
            if (mSelectPath.size() != 0) {
                ShowUtils.showDialog(this, getString(R.string.exit_edit), this);
            } else {
                recoveryActivitys();
            }
        }
        return true;
    }

    @Subscribe
    public void onImageChangeListener(RemoveImageEvent event) {
        boolean isRevoke = event.getIsRevoke();
        int index = event.getIndex();
        if (isRevoke) {
            String path = event.getPath();
            if (!TextUtils.isEmpty(path)) {
                mSelectPath.add(index, event.getPath());
            }
        } else {
            mSelectPath.remove(index);
        }
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        recoveryActivitys();
    }
}
