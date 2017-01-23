package com.fesco.bookpay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fesco.bookpay.adapter.RankOverAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.RankOverBean;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gong.min on 2016/10/27.
 */
public class RankOverTimeActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private RankOverAdapter rankOverAdapter;
    private LinearLayout rank_heard;
    private LinearLayout rank_empty;
    private Gson gson;
    private TextView oneName;
    private TextView twoName;
    private TextView threeName;
    private TextView oneTime;
    private TextView twoTime;
    private TextView threeTime;
    private LinearLayout one_linear;
    private LinearLayout two_linear;
    private LinearLayout three_linear;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranktime);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("加班排行");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                RankOverTimeActivity.this.finish();
            }
        });



        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_overtime);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        rankOverAdapter = new RankOverAdapter(RankOverTimeActivity.this);
        mRecyclerView.setAdapter(rankOverAdapter);
        LoginEntity loginEntity = (LoginEntity) getIntent().getExtras().getSerializable("RankOverTimeActivity");
        gson = new Gson();
        loadData(HttpUtil.getWorkRanking, loginEntity);


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
                        RankOverBean rankOverBean = gson.fromJson(jsonObject.toString(), RankOverBean.class);

                        if (rankOverBean != null && 0 == rankOverBean.getErrcode()) {
                            List<RankOverBean.RankListBean> listBean = rankOverBean.getRankList();
                            Collections.reverse(listBean);
                            rankOverAdapter.setListBean(listBean);
                            rankOverAdapter.notifyDataSetChanged();

                        } else {
                            rankOverAdapter.setListBean(null);
                            rankOverAdapter.notifyDataSetChanged();
                        }


                    }
                }
        );
    }

    private void initValuation(List<RankOverBean.RankListBean> rankOverBean, int type) {
        rank_empty.setVisibility(View.GONE);
        switch (type) {
            case 1:
                oneName.setText(rankOverBean.get(0).getEmp_Name());
                oneTime.setText("累计加班: " + rankOverBean.get(0).getDuration() + "H");
                two_linear.setVisibility(View.INVISIBLE);
                three_linear.setVisibility(View.INVISIBLE);
                break;
            case 2:
                oneName.setText(rankOverBean.get(0).getEmp_Name());
                oneTime.setText("累计加班: " + rankOverBean.get(0).getDuration() + "H");
                twoName.setText(rankOverBean.get(1).getEmp_Name());
                twoTime.setText("累计加班: " + rankOverBean.get(1).getDuration() + "H");
                three_linear.setVisibility(View.INVISIBLE);
                break;
            case 3:
                oneName.setText(rankOverBean.get(0).getEmp_Name());
                oneTime.setText("累计加班: " + rankOverBean.get(0).getDuration() + "H");
                twoName.setText(rankOverBean.get(1).getEmp_Name());
                twoTime.setText("累计加班: " + rankOverBean.get(1).getDuration() + "H");
                threeName.setText(rankOverBean.get(2).getEmp_Name());
                threeTime.setText("累计加班: " + rankOverBean.get(2).getDuration() + "H");
                break;

        }


    }

}
