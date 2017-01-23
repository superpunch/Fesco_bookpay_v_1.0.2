package com.fesco.bookpay.fragment;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fesco.bookpay.activity.R;
import com.fesco.bookpay.adapter.RestPatchApprovalAdapter;
import com.fesco.bookpay.base.BasePageFragment;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.RestRecordBean;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.StringUtils;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;

/**
 * 休假记录
 * Created by gong.min on 2016/9/7.
 */
public class RestRecordFragment extends BasePageFragment {

    private static final String KEY = "EXTRA";
    private LoginEntity flag;
    private int emp_Id, cust_Id;
    private String token;
    private RecyclerView mRecyclerView;
    private Gson gson;
    private RestRecordBean restRecordBean;
    private RestPatchApprovalAdapter restPatchApprovalAdapter;

    public static RestRecordFragment getInstance(final LoginEntity flag) {
//传值
        Bundle bundle = new Bundle();
        bundle.putSerializable(KEY, flag);
        RestRecordFragment restRecordFragment = new RestRecordFragment();
        restRecordFragment.setArguments(bundle);
        return restRecordFragment;
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
            restPatchApprovalAdapter = new RestPatchApprovalAdapter(mActivity);
        }
        return mRecyclerView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(restPatchApprovalAdapter);
    }

    boolean isLoad;

    public void showParam(RestRecordBean restRecordBean, boolean isLoad) {//fragment传递的值
        this.restRecordBean = restRecordBean;
        this.isLoad = isLoad;
    }


    /**
     * 可见时执行网络请求
     * isLoad true 加载以传过的Bean
     */
    @Override
    public void fetchData() {
        Log.d("Fragment", "fetchData---Fragment 加班记录   " + isLoad);

        if (isLoad) {
            if (restRecordBean != null || restRecordBean.getList().size() > 0) {
                restPatchApprovalAdapter.setListBean(restRecordBean.getList());
                restPatchApprovalAdapter.notifyDataSetChanged();
                deleteItemData();
            } else {
                restPatchApprovalAdapter.setListBean(null);
                restPatchApprovalAdapter.notifyDataSetChanged();
            }
        } else
            loadData(HttpUtil.getEmpHol);


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
                        restRecordBean = gson.fromJson(jsonObject.toString(), RestRecordBean.class);
                        if (restRecordBean != null || restRecordBean.getList().size() > 0) {
                            restPatchApprovalAdapter.setListBean(restRecordBean.getList());
                            restPatchApprovalAdapter.notifyDataSetChanged();
                            deleteItemData();
                        } else {
                            restPatchApprovalAdapter.setListBean(null);
                            restPatchApprovalAdapter.notifyDataSetChanged();
                        }


                    }
                }
        );
    }


    private void deleteItemData() {

        restPatchApprovalAdapter.setOnItemClickListener(new RestPatchApprovalAdapter.OnItemClickListener() {

            @Override
            public void onItemClick(View view, int position) {

            }

            @Override
            public void onDeleteClick(int position) {
                showDialog(position);
            }
        });


    }

    /**
     * 删除请假申请（仅限当月）：{'methodname':'kq/delHolApply.json','cust_Id':'','holEmpExamId':''}
     *
     * @param delHolApply
     * @param listBean
     */
    private void deleteHttpData(String delHolApply, RestRecordBean.ListBean listBean) {
        String[] key = new String[]{"cust_Id", "holEmpExamId"};
        String[] value = new String[]{cust_Id + "", listBean.getHol_Emp_Exam_Id() + ""};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(delHolApply, key, value, token);
        OKManager manager = OKManager.getInstance(mActivity);
        manager.sendComplexForm(delHolApply, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {

                Logger.json(jsonObject.toString());

            }
        });

    }

    /**
     * 这是兼容的 AlertDialog
     *
     * @param position
     */
    private void showDialog(final int position) {
  /*
  这里使用了 android.support.v7.app.AlertDialog.Builder
  可以直接在头部写 import android.support.v7.app.AlertDialog
  那么下面就可以写成 AlertDialog.Builder
  */
        AlertDialog.Builder builder = new AlertDialog.Builder(mActivity);
        builder.setTitle("休假记录");
        builder.setMessage("您确定删除吗");
        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                isDeleteHttpData(position);
            }
        });
        builder.show();
    }

    private void isDeleteHttpData(int position) {
        boolean isThisMonth = StringUtils.isThisTime(restRecordBean.getList().get(position).getAppl_Date(), "yyyy-MM");
        if (isThisMonth) {
            deleteHttpData(HttpUtil.delHolApply, restRecordBean.getList().get(position));
            restPatchApprovalAdapter.removeItem(position);
        } else
            AppToast.showShortText(mActivity, "只能删除本月数据哦");


    }


    @Override
    public void onDestroy() {
        Log.d("Fragment", System.currentTimeMillis() + "   加班记录");
        super.onDestroy();
    }


}
