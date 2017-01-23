package com.fesco.bookpay.activity;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import com.fesco.bookpay.adapter.RbmTypeListAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.ConsumptionBean;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.RankOutLateBean;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.HashMap;
import java.util.List;

/**
 * Created by gong.min on 2016/11/11.
 */
public class ConsumptionTypedetailActivity extends BaseActivity  {

    public static final String SUBTYPES_BEAN= "SubTypesBean";
    public static final String DATE_TYPE= "date_Type";
    public static final String NEED_CITY= "need_City";
    public static final String TYPE_ICON= "TYPE_ICON";


    private RecyclerView mRecyclerView;
    private RbmTypeListAdapter rbmTypeListAdapter;
    private Gson gson;
    private Typeface font;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_type_consumption);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        TextView textView = (TextView) findViewById(R.id.toolbar_text);
        textView.setText("新建报销类型");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ConsumptionTypedetailActivity.this.finish();
            }
        });
//Font Awesome 图标
         font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
//        TextView tvIcon1= (TextView) findViewById(R.id.font);
//        TextView tvIcon= (TextView) findViewById(R.id.font1);
//        tvIcon.setText("&#xf29d;");
//        tvIcon1.setTypeface(font);
//        tvIcon.setTypeface(font);
  //      List<ConsumptionBean.SpendTypesBean> spendTypes=  (List<ConsumptionBean.SpendTypesBean>) getIntent().getSerializableExtra("ConsumptionTypeActivity");
        ConsumptionBean.SpendTypesBean  spendTypesBean= (ConsumptionBean.SpendTypesBean) getIntent().getExtras().getSerializable(ConsumptionTypeActivity.TYPE_DETAIL);
        final String  TypeIcon=getIntent().getExtras().getString(ConsumptionTypeActivity.TYPE_ICON);


        final int date_Type=spendTypesBean.getDate_Type();
      final int   need_City=spendTypesBean.getNeed_City();
        final List<ConsumptionBean.SpendTypesBean.SubTypesBean> spendTypesDateil= spendTypesBean.getSub_Types();

//        final  List<ConsumptionBean.SpendTypesBean.SubTypesBean>   spendTypesDateil= (List<ConsumptionBean.SpendTypesBean.SubTypesBean>) getIntent().getExtras().getSerializable("TYPEDETAIL");


        mRecyclerView= (RecyclerView) this.findViewById(R.id.recycler_type);
        rbmTypeListAdapter=new RbmTypeListAdapter(this);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(rbmTypeListAdapter);
       rbmTypeListAdapter.setSpendTypesDateil(spendTypesDateil,font);

        rbmTypeListAdapter.setOnItemClickListener(new RbmTypeListAdapter.ItemTypeClickListener() {
            @Override
            public void onItemClick(View view, int postion) {
//                  String typeName=   spendTypesDateil.get(postion).getType_Name();
//                  String id= Integer.toString(spendTypesDateil.get(postion).getId());

                ConsumptionBean.SpendTypesBean.SubTypesBean subTypesBean = spendTypesDateil.get(postion);

                Intent intent = new Intent(ConsumptionTypedetailActivity.this, ConsumptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(SUBTYPES_BEAN,subTypesBean);
                bundle.putString(TYPE_ICON,TypeIcon);
                bundle.putInt(DATE_TYPE,date_Type);
                bundle.putInt(NEED_CITY,need_City);
//                bundle.putParcelableArrayList("ConsumptionTypeActivity", (ArrayList<? extends Parcelable>) spendTypes);
//                intent.putExtra("TYPEDETAIL", typeName);
//                intent.putExtra("TYPEDETAIL_ID", id);
//                startActivityForResult(intent,2);
                intent.putExtras(bundle);
                startActivity(intent);
                finish();

            }
        });
        gson= new Gson();

     //   loadData(HttpUtil.getCedRanking);


    }

    /**
     * 网络请求
     * emp_Id,cust_Id
     *
     * @param loadEmpInfo
     * @param loginEntity
     */
    public  void loadData(String loadEmpInfo, LoginEntity loginEntity) {
        String[] key = new String[]{"cust_Id"};
        String[] value = new String[]{ Integer.toString(loginEntity.getCust_Id()) };
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());
                        RankOutLateBean rankOutLateBean=    gson.fromJson(jsonObject.toString(), RankOutLateBean.class);
                        if(rankOutLateBean!=null && 0==rankOutLateBean.getErrcode()){

                        }else {

                        }


                    }
                }
        );
    }



}
