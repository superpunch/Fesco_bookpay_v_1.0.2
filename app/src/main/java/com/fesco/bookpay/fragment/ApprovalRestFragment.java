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

import com.fesco.bookpay.activity.DetailRestActivity;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.approvaladapter.ApprovalRestAdapter;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.approvalbean.RestListBean;
import com.fesco.bookpay.impl.ItemClickListener;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;

/** 加班记录
 * Created by gong.min on 2016/9/7.
 */
public class ApprovalRestFragment extends Fragment {

    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id,cust_Id;
    private String token;
    private RecyclerView mRecyclerView;
    private Gson gson;
    public Activity mActivity;
    private ApprovalRestAdapter approvalRestAdapter;
    public static ApprovalRestFragment getInstance(LoginEntity flag) {
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        ApprovalRestFragment approavlRestFragment = new ApprovalRestFragment();
        approavlRestFragment.setArguments(bundle);
        return approavlRestFragment;

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
        System.out.println("BasePageFragment    onAttach-------请假-"+mActivity);
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
            gson=new Gson();
            approvalRestAdapter = new ApprovalRestAdapter(mActivity);
        }
        Log.i("Fragment", "马丹----初始化-View---Fragment 休假记录   "+mRecyclerView);

        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        Log.d("Fragment", "onActivityCreated---Fragment 休假   ");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(approvalRestAdapter);

    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("Fragment", "setUserVisibleHint---Fragment 加班记录   "+isVisibleToUser);

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment", "onResume---Fragment 请假审批---   ----");
        loadData(HttpUtil.holExamList);

    }

    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     */
    public  void loadData(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id"};
        String[] value = new String[]{emp_Id + ""};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                   Logger.json(jsonObject.toString());

                final RestListBean restListBean=gson.fromJson(jsonObject.toString(), RestListBean.class);

                if(restListBean !=null && restListBean.getList().size()>0){
                    approvalRestAdapter.setListBean(restListBean.getList());
                    approvalRestAdapter.notifyDataSetChanged();
                    Log.d("Fragment", "fetchData---Fragment ------------------   "+approvalRestAdapter);

                    approvalRestAdapter.setOnItemClickListener(new ItemClickListener() {
                        @Override
                        public void onItemClick(View view, int postion) {
                            Intent inentApproval=new Intent(mActivity,  DetailRestActivity.class);
                            int   holEmpExamId=   restListBean.getList().get(postion).getHol_Emp_Exam_Id();
                            Bundle bundleApproval=new Bundle();
                            bundleApproval.putInt("holEmpExamId",holEmpExamId);
                            bundleApproval.putSerializable("DetailRestActivity",flag);
                            inentApproval.putExtras(bundleApproval);
                            startActivity(inentApproval);
                        }
                    });

                }else {
                    approvalRestAdapter.setListBean(null);
                    approvalRestAdapter.notifyDataSetChanged();
                }





            }
                }
            );
    }

    @Override
    public void onDestroy()
    {
        Log.d("Fragment", System.currentTimeMillis()+ "   -----------3");
        super.onDestroy();
    }



}
