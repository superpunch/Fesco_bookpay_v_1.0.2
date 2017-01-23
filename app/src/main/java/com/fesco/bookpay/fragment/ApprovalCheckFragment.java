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
import android.widget.ImageView;

import com.fesco.bookpay.activity.DetailCheckActivity;
import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.approvaladapter.ApprovalCheckAdapter;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.approvalbean.CheckListBean;
import com.fesco.bookpay.impl.ItemClickListener;
import com.fesco.bookpay.impl.RetrofitService;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.util.okhttp.RetrofitClient;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 加班记录
 * Created by gong.min on 2016/9/7.
 */
public class ApprovalCheckFragment extends Fragment {

    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id, cust_Id;
    private String token;
    private RecyclerView mRecyclerView;
    private ImageView empty_record;
    private Gson gson;
    public Activity mActivity;
    private ApprovalCheckAdapter approvalCheckAdapter;

    public static ApprovalCheckFragment getInstance(LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        ApprovalCheckFragment approavlOvertimeFragment = new ApprovalCheckFragment();
        approavlOvertimeFragment.setArguments(bundle);
        return approavlOvertimeFragment;

    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.mActivity = (Activity) context;
        System.out.println("BasePageFragment    onAttach------签到--" + mActivity);
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
            approvalCheckAdapter = new ApprovalCheckAdapter(mActivity);
        }
        Log.i("Fragment", "马丹----初始化-View---Fragment 签到   " + mRecyclerView);

        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        Log.d("Fragment", "onActivityCreated---Fragment 签到   ");
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(approvalCheckAdapter);
    }


    @Override
    public void setUserVisibleHint(boolean isVisibleToUser) {
        super.setUserVisibleHint(isVisibleToUser);
        Log.e("Fragment", "setUserVisibleHint---Fragment 签到   " + isVisibleToUser);

    }


    @Override
    public void onResume() {
        super.onResume();
        Log.d("Fragment", "onResume---Fragment 签到---   ----");
     //   loadData(HttpUtil.signLaterExamList);
        loadRetrofitData(HttpUtil.signLaterExamList);

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
                        final CheckListBean checkListBean = gson.fromJson(jsonObject.toString(), CheckListBean.class);
                        if (checkListBean != null || checkListBean.getList().size() > 0) {
                            approvalCheckAdapter.setListBean(checkListBean.getList());
                            approvalCheckAdapter.notifyDataSetChanged();
                            approvalCheckAdapter.setOnItemClickListener(new ItemClickListener() {
                                @Override
                                public void onItemClick(View view, int postion) {
                                    Intent inentApproval = new Intent(mActivity, DetailCheckActivity.class);
                                    int applyId = checkListBean.getList().get(postion).getApply_Id();
                                    Bundle bundleApproval = new Bundle();
                                    bundleApproval.putInt("Apply_Id", applyId);
                                    bundleApproval.putSerializable("DetailCheckActivity", flag);
                                    inentApproval.putExtras(bundleApproval);
                                    startActivity(inentApproval);
                                }
                            });
                        } else {
                            approvalCheckAdapter.setListBean(null);
                            approvalCheckAdapter.notifyDataSetChanged();
                        }


                    }
                }
        );
    }
    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     */
    public void loadRetrofitData(String loadEmpInfo) {
        HashMap<String, String> map = HttpOkManagerUtils.okManagerPost(loadEmpInfo, null, Integer.toString(emp_Id), token);
        RetrofitService service = RetrofitClient.getInstance(mActivity);
        Call<CheckListBean> personInfo = service.postTwoPersonInfo(map);
            personInfo.enqueue(new Callback<CheckListBean>() {
                @Override
                public void onResponse(Call<CheckListBean> call, Response<CheckListBean> response) {
//                    Logger.d(response.body().toString());

                    final CheckListBean checkListBean = response.body();
                    if (checkListBean != null && checkListBean.getList().size() > 0) {
                        approvalCheckAdapter.setListBean(checkListBean.getList());
                        approvalCheckAdapter.notifyDataSetChanged();
                        approvalCheckAdapter.setOnItemClickListener(new ItemClickListener() {
                            @Override
                            public void onItemClick(View view, int postion) {
                                Intent inentApproval = new Intent(mActivity, DetailCheckActivity.class);
                                int applyId = checkListBean.getList().get(postion).getApply_Id();
                                Bundle bundleApproval = new Bundle();
                                bundleApproval.putInt("Apply_Id", applyId);
                                bundleApproval.putSerializable("DetailCheckActivity", flag);
                                inentApproval.putExtras(bundleApproval);
                                startActivity(inentApproval);
                            }
                        });
                    } else {
                        approvalCheckAdapter.setListBean(null);
                        approvalCheckAdapter.notifyDataSetChanged();
                    }

                }

                @Override
                public void onFailure(Call<CheckListBean> call, Throwable t) {
                    Logger.i("response.body().toString()");
                }
            });

    }

    @Override
    public void onDestroy() {
        Log.d("Fragment", System.currentTimeMillis() + "   -----------2");
        super.onDestroy();
    }


}
