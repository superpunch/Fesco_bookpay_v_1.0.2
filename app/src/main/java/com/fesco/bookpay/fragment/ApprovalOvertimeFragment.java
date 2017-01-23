package com.fesco.bookpay.fragment;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fesco.bookpay.activity.DetailOverActivity;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.approvaladapter.ApprovalOvertimeAdapter;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.approvalbean.OverListBean;
import com.fesco.bookpay.impl.ItemClickListener;
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
public class ApprovalOvertimeFragment extends Fragment {

    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id, cust_Id;
    private String token;
    private RecyclerView mRecyclerView;
    private Gson gson;
    public Activity mActivity;
    private ApprovalOvertimeAdapter approvalOvertimeAdapter;

    public static ApprovalOvertimeFragment getInstance(LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        ApprovalOvertimeFragment approavlOvertimeFragment = new ApprovalOvertimeFragment();
        approavlOvertimeFragment.setArguments(bundle);
        return approavlOvertimeFragment;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
        System.out.println("BasePageFragment    onAttach-------加班-" + mActivity);
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
            //   empty_record   = (ImageView)mRecyclerView .findViewById(R.id.empty_record);
            gson = new Gson();
            approvalOvertimeAdapter = new ApprovalOvertimeAdapter(getActivity());
        }
        Log.i("Fragment", "马丹----初始化-View---Fragment 加班记录   " + mRecyclerView);

        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Fragment", "onActivityCreated---Fragment 加班记录   ");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(approvalOvertimeAdapter);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("Fragment", "setUserVisibleHint---Fragment 加班---   " + isVisibleToUser);

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment", "onResume---Fragment 加班---   ----");
        loadData(HttpUtil.workExamList);

    }

    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     */
    public void loadData(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id"};
        String[] value = new String[]{emp_Id + ""};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                        Logger.json(jsonObject.toString());

                        final OverListBean overListBean = gson.fromJson(jsonObject.toString(), OverListBean.class);
                        if (overListBean != null && overListBean.getList().size() > 0) {
                            approvalOvertimeAdapter.setListBean(overListBean.getList());
                            approvalOvertimeAdapter.notifyDataSetChanged();

                            approvalOvertimeAdapter.setOnItemClickListener(new ItemClickListener() {
                                @Override
                                public void onItemClick(View view, int postion) {
                                    Intent inentApproval = new Intent(mActivity, DetailOverActivity.class);
                                    int applyId = overListBean.getList().get(postion).getApply_Id();
                                    Bundle bundleApproval = new Bundle();
                                    bundleApproval.putInt("Apply_Id", applyId);
                                    bundleApproval.putSerializable("DetailOverActivity", flag);
                                    inentApproval.putExtras(bundleApproval);
                                    startActivity(inentApproval);
                                }
                            });
                        } else {
                            approvalOvertimeAdapter.setListBean(null);
                            approvalOvertimeAdapter.notifyDataSetChanged();

                        }


                    }
                }
        );
    }


    @Override
    public void onDestroy() {
        Log.d("Fragment", System.currentTimeMillis() + "   -----------1");
        super.onDestroy();
    }

}
