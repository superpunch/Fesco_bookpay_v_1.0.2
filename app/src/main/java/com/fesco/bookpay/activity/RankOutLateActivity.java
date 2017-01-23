package com.fesco.bookpay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fesco.bookpay.adapter.RankOutLateAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.RankOutLateBean;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;

/**
 * Created by gong.min on 2016/10/27.
 */
public class RankOutLateActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private RankOutLateAdapter rankOutLateAdapter;
    private Gson gson;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outlate);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("迟到排行");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RankOutLateActivity.this.finish();
            }
        });
        TextView tvDate = (TextView) this.findViewById(R.id.outlate_date);
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        DateFormat df = new SimpleDateFormat("MMMM", Locale.ENGLISH);
        String month = df.format(new Date());
        tvDate.setText("In" + "\t" + month + "," + year);
        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_outlate);
        rankOutLateAdapter = new RankOutLateAdapter(RankOutLateActivity.this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(rankOutLateAdapter);
        LoginEntity loginEntity = (LoginEntity) getIntent().getExtras().getSerializable("RankOutLateActivity");
        gson = new Gson();
        loadData(HttpUtil.getCedRanking, loginEntity);


    }

    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     * @param loginEntity
     */
    public void loadData(String loadEmpInfo, LoginEntity loginEntity) {
        String[] key = new String[]{"cust_Id"};
        String[] value = new String[]{Integer.toString(loginEntity.getCust_Id())};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        RankOutLateBean rankOutLateBean = gson.fromJson(jsonObject.toString(), RankOutLateBean.class);
                        if (rankOutLateBean != null && 0 == rankOutLateBean.getErrcode()) {
                            List<RankOutLateBean.RankListBean> listBean = rankOutLateBean.getRankList();
                            Collections.reverse(listBean);
                            rankOutLateAdapter.setrankOutLateBean(listBean);
                            rankOutLateAdapter.notifyDataSetChanged();
                        } else {
                            rankOutLateAdapter.setrankOutLateBean(null);
                            rankOutLateAdapter.notifyDataSetChanged();
                        }


                    }
                }
        );
    }


}
