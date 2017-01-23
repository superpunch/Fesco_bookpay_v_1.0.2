package com.fesco.bookpay.fragment;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.OverPatchApprovalAdapter;
import com.fesco.bookpay.base.BasePageFragment;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.OverPatchRecordBean;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 加班记录
 * Created by gong.min on 2016/9/7.
 */
public class OvertimeRecordFragment extends BasePageFragment {

    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id, cust_Id;
    private String token;
    private RecyclerView mRecyclerView;
    private Gson gson;

    private OverPatchApprovalAdapter overPatchApprovalAdapter;

    public static OvertimeRecordFragment getInstance(LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        OvertimeRecordFragment overtimeRecordFragment = new OvertimeRecordFragment();
        overtimeRecordFragment.setArguments(bundle);
        return overtimeRecordFragment;

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        Bundle bundle = getArguments();
        if (bundle != null) {
            flag = (LoginEntity) bundle.getSerializable(KEY);
            emp_Id = flag.getEmp_Id();
            cust_Id = flag.getCust_Id();
            token = flag.getToken();
        }

        if (mRecyclerView == null) {
            mRecyclerView =
                    (RecyclerView) inflater.inflate(R.layout.list_fragment, container, false);
            gson = new Gson();
            overPatchApprovalAdapter = new OverPatchApprovalAdapter(mActivity);
        }
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(overPatchApprovalAdapter);
    }


    /**
     * 可见时执行网络请求
     */
    @Override
    public void fetchData() {
        loadData(HttpUtil.getEmpWorkList);
    }


    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     */
    public void loadData(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id", "cust_Id"};
        String[] value = new String[]{emp_Id + "", cust_Id + ""};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Logger.json(jsonObject.toString());

                        OverPatchRecordBean overPatchRecordBean = gson.fromJson(jsonObject.toString(), OverPatchRecordBean.class);
                        if (overPatchRecordBean != null || overPatchRecordBean.getList().size() > 0) {
                            overPatchApprovalAdapter.setListBean(overPatchRecordBean.getList());
                            overPatchApprovalAdapter.notifyDataSetChanged();
                        } else {
                            overPatchApprovalAdapter.setListBean(null);
                            overPatchApprovalAdapter.notifyDataSetChanged();
                        }


                    }
                }
        );
    }




}
