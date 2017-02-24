package com.fesco.bookpay.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fesco.bookpay.adapter.RankOverAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.RankOverBean;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by gong.min on 2016/10/27.
 */
public class RankOverTimeActivity extends BaseActivity {
    private RecyclerView mRecyclerView;
    private RankOverAdapter rankOverAdapter;


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
                        RankOverBean rankOverBean = gson.fromJson(jsonObject.toString(), RankOverBean.class);

                        if (rankOverBean != null && 0 == rankOverBean.getErrcode()) {
                            List<RankOverBean.RankListBean> listBean = rankOverBean.getRankList();
                            List<RankOverBean.RankListBean> newlistBean =new ArrayList<>();
                            RankOverBean.RankListBean objectBean[] = new RankOverBean.RankListBean[listBean.size()];
                            for (int i = 0; i < listBean.size(); i++) {
                                objectBean[i] = listBean.get(i);
                            }

                            for (int i = 0; i < listBean.size(); i++) {
                                for (int j = i; j < listBean.size(); j++) {
                                    if(objectBean[i].getDuration() < objectBean[j].getDuration()){

                                        RankOverBean.RankListBean  temp = objectBean[i];
                                        objectBean[i] = objectBean[j];
                                        objectBean[j] = temp;
                                    }
                                }
                            }

                            for(int i = 0; i < listBean.size(); i++){
                                 newlistBean.add( objectBean[i]);
                             }
                            rankOverAdapter.setListBean(newlistBean);
                            rankOverAdapter.notifyDataSetChanged();

                        } else {
                            rankOverAdapter.setListBean(null);
                            rankOverAdapter.notifyDataSetChanged();
                        }


                    }
                }
        );
    }



}
