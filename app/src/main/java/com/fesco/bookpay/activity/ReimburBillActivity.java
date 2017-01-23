package com.fesco.bookpay.activity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bigkoo.pickerview.OptionsPickerView;
import com.bigkoo.pickerview.TimePickerView;
import com.fesco.bookpay.FApplication;
import com.fesco.bookpay.adapter.RbmBillListAdapter;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.ConsumptionBean;
import com.fesco.bookpay.entity.ExpenseApplyListBean;
import com.fesco.bookpay.entity.MessageBean;
import com.fesco.bookpay.entity.ProvinceBean;
import com.fesco.bookpay.entity.rbmbean.ConsumptionEditBean;
import com.fesco.bookpay.entity.rbmbean.ConsumptionTurnBean;
import com.fesco.bookpay.entity.rbmbean.SpendTypesBean;
import com.fesco.bookpay.event.ConsumptionEvent;
import com.fesco.bookpay.impl.RetrofitService;
import com.fesco.bookpay.util.ACache;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.CommonUtils;
import com.fesco.bookpay.util.ConversionUtil;
import com.fesco.bookpay.util.DoubleUtil;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.util.okhttp.RetrofitClient;
import com.fesco.bookpay.view.FullyLinearLayoutManager;
import com.fesco.bookpay.weight.dialog.ApplyCreateDialogFragment;
import com.fesco.bookpay.weight.dialog.ApplyPersonDialogFragment;
import com.fesco.bookpay.weight.dialog.ApplyTypsDialogFragment;
import com.fesco.bookpay.weight.dialog.CustomIosDialog;
import com.orhanobut.logger.Logger;

import org.greenrobot.eventbus.EventBus;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

import me.bookpay.greendao.Consume;
import me.bookpay.greendao.ConsumeDao;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by gong.min on 2016/11/4.
 */
public class ReimburBillActivity extends BaseActivity implements View.OnClickListener, ApplyCreateDialogFragment.DialogListener, ApplyTypsDialogFragment.ApplyTypsDialogListener, ApplyPersonDialogFragment.ApplyPersonDialogListener, CustomIosDialog.confirmDialogListener {

    public static final String SPEND_TYPES = "SPEND_TYPES";
    public static final String KEY = "APPLY";
    public static final String APPLY_PERSON = "APPLY_PERSON";
    public static final String NEW_SPEND_TYPE = "NEW_SPEND_TYPE"; //标识 创建新的ConsumptionActivity页面

    public static final String OPNE_SPEND_TYPE = "OPNE_SPEND_TYPE"; //标识 预览ConsumptionActivity页面


    private static final int SPENTY_CODE = 3;
    private String approval_Man; //审批人 对应的 emp_id
    private String account_Id = ""; //账户 id
    private String group_Id = ""; //部门 对应的 id
    private String type_Code = ""; //报销单 对应的 id    1 ：日常报销单
    private String apply_Id = ""; // 新增报销单 对应的 id  为kong    网络获取必填
    private String edit_apply_Id = ""; // 驳回后的待编辑报销单 对应的 id  为kong    网络获取必填
    private String detail_Id; // 新增报销单消费 对应的 id  为kong    网络获取必填

    private double amountMoney; //总金额

    private LinearLayout linear_apply;
    private TextView mApply;
    private TextView mAdd;
    private EditText mTitle;
    private TextView mOption;
    private TextView mPerson;
    private TextView mDepartment;
    private EditText mEmo;
    private Button mSumit;
    private Button mAmount;
    private ImageView toolbarDetele;

    private ApplyTypsDialogFragment applyTypsDialogFragment;
    private ApplyPersonDialogFragment applyPersonDialogFragment;
    private List<ConsumptionBean.SpendTypesBean> spendTypes;
    private List<ConsumptionBean.ApplyTypesBean> applyTypesBeen;
    private List<ConsumptionBean.ApprovalManListBean> approvalManList;
    private Context mContext;
    private RecyclerView mRecyclerView;
    private RbmBillListAdapter rbmBillListAdapter;

    private TimePickerView pvTimeDay;
    private OptionsPickerView pvOptionsPerson;
    private OptionsPickerView pvOptionsGroups;
    private ArrayList<ProvinceBean> options1Ttems = new ArrayList<>();
    private ArrayList<ProvinceBean> options2Items = new ArrayList<>();

    private List<Consume> consumeList = new ArrayList<>();
    private CustomIosDialog.Builder ibuilder;

    private boolean isFlag; //是否预加载之前已编辑的信息
    private ACache mCache;
    private Typeface font;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reimburse_bill);
        initViews();
        timeOptionDay();
        mCache = ACache.get(this);
        String save_apply_Id = getIntent().getStringExtra(ReimbursementActivity.SAVE_LISTBEAN);  //待提交

        String edit_apply_Id = getIntent().getStringExtra(ReimbursementApproveActivity.NOTPASS_ID);//未通过
        if (!TextUtils.isEmpty(save_apply_Id)) {
            apply_Id = save_apply_Id;
            isFlag = true;
            loadEditData(HttpUtil.loadEditApply, save_apply_Id);
            toolbarDetele();
            Log.e("Fragment", "保存过apply_Id :" + apply_Id + " onCreate isFlag: " + isFlag);
        } else if (!TextUtils.isEmpty(edit_apply_Id)) {
            apply_Id = edit_apply_Id;
            isFlag = true;
            loadTurnEditData(HttpUtil.loadNewExpenseExamInfo, edit_apply_Id);
            toolbarDetele();
            Log.e("Fragment", "待编辑过apply_Id :" + edit_apply_Id + " onCreate isFlag: " + isFlag);
        } else {
            Log.e("Fragment", "新见apply_Id :" + apply_Id + " onCreate isFlag: " + isFlag);
            apply_Id = "";
            Date d = new Date();
            String nowDay = CommonUtils.sdfDay.format(d);
            mOption.setText(nowDay);
            String p = mCache.getAsString("Person");
            String dd = mCache.getAsString("Department");
            String a = mCache.getAsString("account_Id");
            String e = mCache.getAsString("mApply");

            Log.e("Fragment", "onCreate mCache: " + p + "---" + dd + "--" + a + "---" + e);
            if (!TextUtils.isEmpty(mCache.getAsString("Person"))) {
                mPerson.setText(mCache.getAsString("Person"));
            }

            if (!TextUtils.isEmpty(mCache.getAsString("Department"))) {
                mDepartment.setText(mCache.getAsString("Department"));
            }

            if (!TextUtils.isEmpty(mCache.getAsString("mApply"))) {
                mApply.setText(mCache.getAsString("mApply"));
            }


            account_Id = mCache.getAsString("account_Id");
            group_Id = mCache.getAsString("group_Id");
            type_Code = mCache.getAsString("type_Code");
            if (TextUtils.isEmpty(account_Id)) {
                account_Id = "";
            }
            if (TextUtils.isEmpty(group_Id)) {
                group_Id = "";
            }
            if (TextUtils.isEmpty(type_Code)) {
                type_Code = "";
            }


        }

        loadRetrofitData(HttpUtil.loadAddApply);


    }


    private void initValuation(ExpenseApplyListBean.ListBean listBean) {

        //  apply_Id = Integer.toString(listBean.getApply_Id());
        group_Id = Integer.toString(listBean.getGroup_Id());
        account_Id = listBean.getAccount_Id();

        mApply.setText(listBean.getType_Str());
        mTitle.setText(listBean.getTitle());
        if (!TextUtils.isEmpty(listBean.getApply_Date())) {
            String date = ConversionUtil.getLongDateTime(listBean.getApply_Date());
            mOption.setText(date);
            Log.d("Fragment", "getLongDateTime: " + listBean.getApply_Date());
            Log.d("Fragment", "getLongDateTime date: " + date);
        }

        if (listBean.getMemo() != null) {
            mEmo.setText(listBean.getMemo());
        }

        List<ExpenseApplyListBean.ListBean.DetailsBean> detailsBeanList = listBean.getDetails();

        int money_Sum = 0;
        for (ExpenseApplyListBean.ListBean.DetailsBean detailsBean : detailsBeanList) {

            DoubleUtil.add(money_Sum, Double.valueOf(detailsBean.getMoney_Amount()));

            String spend_Begin = detailsBean.getSpend_Begin();
            String spend_End = detailsBean.getSpend_End();
            if (null != spend_Begin) {
                spend_Begin = ConversionUtil.getLongDateTime(spend_Begin);
            } else if (null == spend_Begin) {
                spend_Begin = "";
            }
            if (null != spend_End) {
                spend_End = ConversionUtil.getLongDateTime(spend_End);
            }
            if (null == spend_End) {
                spend_End = "";
            }
            String pic_Ids = detailsBean.getPic_Ids();
            String spend_City = detailsBean.getSpend_City();
            String detail_Id = Integer.toString(detailsBean.getDetail_Id());
            String detail_Memo = detailsBean.getDetail_Memo();
            if (null == pic_Ids) {
                pic_Ids = "";
            }
            if (null == spend_City) {
                spend_City = "";
            }
            if (null == detail_Id) {
                detail_Id = "";
            }
            if (null == detail_Memo) {
                detail_Memo = "";
            }

            Consume consumeDao = new Consume(null, detail_Id, "", detailsBean.getSpend_Type_Str(), Integer.toString(detailsBean.getSpend_Type()), pic_Ids, detailsBean.getMoney_Amount(), spend_Begin, spend_End, spend_City, detailsBean.getBill_Num(), detail_Memo);
            getConsumeDao().insert(consumeDao);
        }
        //  mAmount.setText("￥" + money_Sum);


        consumeList = getConsumeDao().loadAll();
        rbmBillListAdapter.setConsumeList(consumeList, font);
        rbmBillListAdapter.notifyDataSetChanged();


        //loadEditApply(HttpUtil.loadEditApply);


    }

    private void toolbarDetele() {
        toolbarDetele.setVisibility(View.VISIBLE);
        toolbarDetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backDialog(false);
                // showDialog(true);

            }
        });
    }


    private void initViews() {
        mContext = this;
//Font Awesome 图标
        font = Typeface.createFromAsset(getAssets(), "fontawesome-webfont.ttf");
        linear_apply = (LinearLayout) findViewById(R.id.linear_applybill);
        mApply = (TextView) findViewById(R.id.applybill);
        mAdd = (TextView) findViewById(R.id.add_bill);
        mTitle = (EditText) findViewById(R.id.rb_title);
        //   mTitle.setInputType(InputType.TYPE_NULL);
        mOption = (TextView) findViewById(R.id.rb_day);
        mPerson = (TextView) findViewById(R.id.rb_person);
        mDepartment = (TextView) findViewById(R.id.rb_department);
        mEmo = (EditText) findViewById(R.id.rb_memo);
        mSumit = (Button) findViewById(R.id.rb_sumit);
        mAmount = (Button) findViewById(R.id.rb_amount);

        linear_apply.setOnClickListener(this);
        //   mApply.setOnClickListener(this);
        mAdd.setOnClickListener(this);
        mOption.setOnClickListener(this);
        mDepartment.setOnClickListener(this);
        mPerson.setOnClickListener(this);
        mSumit.setOnClickListener(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.reimbur_toolbar);
        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_text_save);
        ImageView toolbarSave = (ImageView) toolbar.findViewById(R.id.toolbar_save);
        toolbarDetele = (ImageView) toolbar.findViewById(R.id.toolbar_delete);
        toolbarSave.setVisibility(View.VISIBLE);
        textView.setText("新建报销单");
        toolbar.setTitle("");
        setSupportActionBar(toolbar);
        deleteAllNote();
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.e("Fragment", "toolbar isFlag: " + isFlag);

                showDialog();

            }
        });
        toolbarSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postApplyBillData(HttpUtil.saveExpenseApply, false);
            }
        });

        mRecyclerView = (RecyclerView) this.findViewById(R.id.recycler_bill);
        rbmBillListAdapter = new RbmBillListAdapter(this);
        FullyLinearLayoutManager mLayoutManager = new FullyLinearLayoutManager(this);
        //   mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(mRecyclerView.getContext()));
        mRecyclerView.setAdapter(rbmBillListAdapter);
        String orderBy = ConsumeDao.Properties.Id.columnName + " DESC";//根据Id降序排序
        //    cursor = getDb().query(getConsumeDao().getTablename(), getConsumeDao().getAllColumns(), null, null, null, null, orderBy);


        onValuationListener();

    }

    private void onValuationListener() {

        rbmBillListAdapter.setOnItemDeleteListener(new RbmBillListAdapter.OnDelelteListener() {
            @Override
            public void itemDelete(int adapterPosition) {
                Log.e("Fragment", "删除事件ID： " + consumeList.get(adapterPosition).getId());
                Log.d("Fragmenti", "删除事件 Money：-- " + consumeList.get(adapterPosition).getMoney());
                Log.i("Fragment", "删除事件 amountMoney：-- " + amountMoney);

                double v1 = Double.valueOf(consumeList.get(adapterPosition).getMoney());
                double doubleSum = DoubleUtil.sub(v1, amountMoney);

                Log.i("Fragment", "删除事件 剩余M：-- " + amountMoney);
                if (amountMoney != 0) {
                    mAmount.setText("￥" + Double.toString(doubleSum));
                } else
                    mAmount.setText("￥0.00");
                getConsumeDao().deleteByKey(consumeList.get(adapterPosition).getId());
                // rbmBillListAdapter.setConsumeList(getConsumeDao().loadAll());
                consumeList.remove(adapterPosition);
                rbmBillListAdapter.notifyDataSetChanged();
            }

            @Override
            public void itemOpenActivity(int adapterPosition) {
                EventBus.getDefault().postSticky(new ConsumptionEvent(NEW_SPEND_TYPE));
                Intent intent = new Intent(ReimburBillActivity.this, ConsumptionActivity.class);
                Bundle bundle = new Bundle();
                bundle.putSerializable(OPNE_SPEND_TYPE, consumeList.get(adapterPosition));
                intent.putExtras(bundle);
                startActivity(intent);
                //finish();

            }
        });

        rbmBillListAdapter.setOnMoneyListener(new RbmBillListAdapter.OnAmountListener() {
            @Override
            public void onAmountMoney(double amount) {
                amountMoney = amount;
                mAmount.setText("￥" + Double.toString(amount));
            }
        });
    }


    @Override
    protected void onRestart() {
        super.onRestart();


        consumeList = getConsumeDao().loadAll();
        rbmBillListAdapter.setConsumeList(consumeList, font);
        rbmBillListAdapter.notifyDataSetChanged();


    }


    List<String> detailIdList = new ArrayList<>();

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        switch (requestCode) {
// CheckBox 选中Item后的addSpendTypesBeanList
            case SPENTY_CODE:
                if (resultCode == RESULT_OK) {
                    List<SpendTypesBean.ListBean> addSpendTypesBeanList = (List<SpendTypesBean.ListBean>) data.getSerializableExtra(ConsumptionNotOptionActivity.SPEND_TYPES);

                    if (addSpendTypesBeanList != null) {
                        Log.e("Fragment", "SpendTypesBeanList: " + addSpendTypesBeanList.get(0).getMoney_Amount());
                        Log.e("Fragment", "SpendTypesBeanList: " + addSpendTypesBeanList.size());

                        for (SpendTypesBean.ListBean detailsBean : addSpendTypesBeanList) {
                            String spend_Begin = detailsBean.getSpend_Begin();
                            String spend_End = detailsBean.getSpend_End();
                            if (!TextUtils.isEmpty(spend_Begin)) {
                                spend_Begin = ConversionUtil.getLongDateTime(spend_Begin);
                            }
                            if (!TextUtils.isEmpty(spend_End)) {
                                spend_End = ConversionUtil.getLongDateTime(spend_End);
                            }

                            String pic_Ids = detailsBean.getPic_Ids();
                            String spend_City = detailsBean.getSpend_City();
                            String detail_Id = Integer.toString(detailsBean.getDetail_Id());
                            String detail_Memo = detailsBean.getDetail_Memo();
                            if (null == pic_Ids) {
                                pic_Ids = "";
                            }
                            if (null == spend_City) {
                                spend_City = "";
                            }
                            if (null == detail_Id) {
                                detail_Id = "";
                            }
                            if (null == detail_Memo) {
                                detail_Memo = "";
                            }
                            Log.e("Fragment", "SpendTypesBeanList: " + addSpendTypesBeanList.get(0).getMoney_Amount());
                            Log.e("Fragment", "SpendTypesBeanList: " + addSpendTypesBeanList.size());
                            Consume consumeDao = new Consume(null, Integer.toString(detailsBean.getDetail_Id()), detailsBean.getAndroid_Icon(), detailsBean.getSpend_Type_Str(), Integer.toString(detailsBean.getSpend_Type()), pic_Ids, detailsBean.getMoney_Amount(), spend_Begin, spend_End, spend_City, detailsBean.getBill_Num(), detail_Memo);
                            getConsumeDao().insert(consumeDao);

                            detailIdList.add(Integer.toString(detailsBean.getDetail_Id()));

                            consumeList = getConsumeDao().loadAll();
                            rbmBillListAdapter.setConsumeList(consumeList, font);
                            rbmBillListAdapter.notifyDataSetChanged();
                        }


                    }

                }
                break;

        }
    }

    private ConsumeDao getConsumeDao() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
        return ((FApplication) this.getApplicationContext()).getDaoSession().getConsumeDao();
    }

    private SQLiteDatabase getDb() {
        // 通过 BaseApplication 类提供的 getDb() 获取具体 db
        return ((FApplication) this.getApplicationContext()).getDb();
    }

    /**
     * 删除所有数据
     */
    public void deleteAllNote() {
        getConsumeDao().deleteAll();
        amountMoney = 0;
    }


    @Override
    public void onClick(View v) {

        switch (v.getId()) {

            case R.id.add_bill:
                showEditDialog();
                break;
            case R.id.linear_applybill:
                showApplyDialog();
                break;
            case R.id.rb_day:
                pvTimeDay.show();
                break;
            case R.id.rb_department:
                pvOptionsGroups.show();
                break;
            case R.id.rb_person:
                pvOptionsPerson.show();
                break;
            case R.id.rb_sumit:

                if (TextUtils.isEmpty(type_Code)) {
                    AppToast.makeShortToast(this, "请选择报销单类型");
                    return;
                }
                if (TextUtils.isEmpty(mTitle.getText())) {
                    AppToast.makeShortToast(this, "请输入标题");
                    return;
                }
                if (TextUtils.isEmpty(mOption.getText())) {
                    AppToast.makeShortToast(this, "请选择报销日期");
                    return;
                }
                if (TextUtils.isEmpty(mDepartment.getText())) {
                    AppToast.makeShortToast(this, "请选择报销部门");
                    return;
                }

                if (TextUtils.isEmpty(mPerson.getText())) {
                    AppToast.makeShortToast(this, "请选择收款人");
                    return;
                }
                if (consumeList != null && consumeList.size() > 0) {
                    showApplyPersonDialog();
                } else {
                    AppToast.makeShortToast(this, "请添加消费记录");
                }

                //          showApplyPersonDialog();
                break;

        }
    }

    public void showEditDialog() {
        ApplyCreateDialogFragment consumeDialogFragment = new ApplyCreateDialogFragment();
        consumeDialogFragment.show(getFragmentManager(), "EditNameDialog");
    }

    public void showApplyDialog() {
        if (applyTypesBeen != null && applyTypesBeen.size() > 0) {
            applyTypsDialogFragment.show(getSupportFragmentManager(), "EditNameDialog");
        } else {
            AppToast.makeShortToast(this, "请稍等正在加载网络数据！");
        }
    }

    public void showApplyPersonDialog() {
        if (approvalManList != null && approvalManList.size() > 0) {
            applyPersonDialogFragment.show(getSupportFragmentManager(), "EditNameDialog");
        } else {
            AppToast.makeShortToast(this, "请稍等正在加载网络数据！");
        }
    }


    @Override
    public void onApplyTypeListener(String typeName, String type_Code) {
        if (applyTypesBeen != null) {
            mApply.setText(typeName);
            this.type_Code = type_Code;
        } else {
            AppToast.makeShortToast(this, "请稍等正在加载网络数据！");
        }
    }

    @Override
    public void onNewTypeListener() {
        if (spendTypes != null) {
            EventBus.getDefault().postSticky(new ConsumptionEvent(NEW_SPEND_TYPE));
            Intent intent = new Intent(this, ConsumptionTypeActivity.class);
            startActivity(intent);
        } else {
            AppToast.makeShortToast(this, "请稍等正在加载网络数据！");
        }
    }

    @Override
    public void onImportTypeListener() {
        if (spendTypes != null) {
            Intent intent = new Intent(this, ConsumptionNotOptionActivity.class);
            //  intent.putExtra(SPEND_TYPES, (Serializable) spendTypes);
            //     startActivity(intent);
            startActivityForResult(intent, SPENTY_CODE);


        } else {
            AppToast.makeShortToast(this, "请稍等正在加载网络数据！");
        }
    }

    @Override
    public void onApplyPersonListener(String emp_Name, String emp_Id) {
        if (approvalManList != null) {
            this.approval_Man = emp_Id;
            postApplyBillData(HttpUtil.submitExpenseApply, true);
            //    AppToast.makeShortToast(this, "请稍等正在加载网络数据！+ "+emp_Name+"----"+approval_Man);
        } else {
            AppToast.makeShortToast(this, "请稍等正在加载网络数据！");
        }

    }

    /**
     * 网络请求
     * emp_Id,cust_Id apply_Id
     *
     * @param loadEmpInfo
     */
    public void loadEditApply(String loadEmpInfo) {

        String[] key = new String[]{"cust_Id", "emp_Id", "apply_Id"};
        String[] value = new String[]{loginEntity.getCust_Id() + "", loginEntity.getEmp_Id() + "", apply_Id};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, token);

        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        Logger.json(jsonObject.toString());

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
        HashMap<String, String> map = HttpOkManagerUtils.okManagerPost(loadEmpInfo, Integer.toString(cust_Id), Integer.toString(emp_Id), token);
        RetrofitService service = RetrofitClient.getInstance(this);
        Call<ConsumptionBean> personInfo = service.postConsumptionBean(map);
        personInfo.enqueue(new Callback<ConsumptionBean>() {
            @Override
            public void onResponse(Call<ConsumptionBean> call, Response<ConsumptionBean> response) {
                Logger.d(response.body().toString());
                final ConsumptionBean consumptionBean = response.body();
                if (consumptionBean.getErrcode() == 1) {
                    AppToast.makeShortToast(ReimburBillActivity.this, "数据出错，请联系管理员");

                } else {
                    Bundle bundle = new Bundle();
                    applyTypesBeen = consumptionBean.getApplyTypes();
                    bundle.putSerializable(KEY, (Serializable) applyTypesBeen);
                    applyTypsDialogFragment = new ApplyTypsDialogFragment();
                    applyTypsDialogFragment.setArguments(bundle);

                    Bundle bundleApproval = new Bundle();
                    approvalManList = consumptionBean.getApprovalManList();

                    bundleApproval.putSerializable(APPLY_PERSON, (Serializable) approvalManList);
                    applyPersonDialogFragment = new ApplyPersonDialogFragment();
                    applyPersonDialogFragment.setArguments(bundleApproval);


                    spendTypes = consumptionBean.getSpendTypes();

                    List<ConsumptionBean.GroupsBean> groupsBeanList = consumptionBean.getGroups();

                    List<ConsumptionBean.BankAccountsBean> bankAccountsBeen = consumptionBean.getBankAccounts();


                    loadPersonOption(bankAccountsBeen);
                    loadGroupsOption(groupsBeanList);


                    if (isFlag) {
                        loadLastVisit(groupsBeanList, bankAccountsBeen);
                        Log.e("Fragment", "isFlag: " + isFlag);
                    }

                }
            }

            @Override
            public void onFailure(Call<ConsumptionBean> call, Throwable t) {
                Logger.i("response.body().toString()");
            }
        });

    }

    private void loadEditData(String loadEditApply, String save_apply_Id) {

        String[] key = new String[]{"emp_Id", "cust_Id", "apply_Id"};
        String[] value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), save_apply_Id};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEditApply, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEditApply, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());
                ConsumptionEditBean comsumEditBean = gson.fromJson(jsonObject.toString(), ConsumptionEditBean.class);
                if (comsumEditBean == null || comsumEditBean.getErrcode() == 1) {
                    AppToast.makeShortToast(ReimburBillActivity.this, "数据出错，请联系管理员");
                } else
                    //赋值给 详细信息列表
                    initEditValuation(comsumEditBean.getApply());
            }
        });

    }

    private void loadTurnEditData(String url, String edit_apply_Id) {

        String[] key = new String[]{"cust_Id", "apply_Id"};
        String[] value = new String[]{Integer.toString(cust_Id), edit_apply_Id};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(url, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(url, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());
                ConsumptionTurnBean consumptionTurnBean = gson.fromJson(jsonObject.toString(), ConsumptionTurnBean.class);
                if (consumptionTurnBean == null || consumptionTurnBean.getErrcode() == 1) {
                    AppToast.makeShortToast(ReimburBillActivity.this, "数据出错，请联系管理员");

                } else
                    //赋值给 详细信息列表
                    initTurnValuation(consumptionTurnBean.getApply());
            }
        });

    }

    private void initTurnValuation(ConsumptionTurnBean.ApplyBean apply) {

        //apply_Id = Integer.toString(apply.getApply_Id());
        group_Id = Integer.toString(apply.getGroup_Id());
        account_Id = Integer.toString(apply.getAccount_Id());

        switch (apply.getType()) {
            case 1:
                mApply.setText("日常报销");
                type_Code = "1";
                break;
            case 2:
                mApply.setText("差旅报销单");
                type_Code = "2";
                break;
            case 3:
                mApply.setText("付款申请单");
                type_Code = "2";
                break;
        }

        if (apply.getType_Str() != null) {
            mApply.setText(apply.getType_Str());
        }
        mTitle.setText(apply.getTitle());


        String date = ConversionUtil.getLongDateTime(apply.getApply_Date());
        mOption.setText(date);

        if (apply.getMemo() != null) {
            mEmo.setText(apply.getMemo());
        }
        if (apply.getGroup_Name() != null) {
            mDepartment.setText(apply.getGroup_Name());
        }
        if (apply.getAccount_Name() != null) {
            mPerson.setText(apply.getAccount_Name());
        }


        List<ConsumptionTurnBean.ApplyBean.DetailsBean> detailsBeanList = apply.getDetails();

        for (ConsumptionTurnBean.ApplyBean.DetailsBean detailsBean : detailsBeanList) {

            // money_Sum += detailsBean.getMoney_Amount();

            String spend_Begin = detailsBean.getSpend_Begin();
            String spend_End = detailsBean.getSpend_End();
            if (null != spend_Begin) {
                spend_Begin = ConversionUtil.getLongDateTime(spend_Begin);
            } else if (null == spend_Begin) {
                spend_Begin = "";
            }
            if (null != spend_End) {
                spend_End = ConversionUtil.getLongDateTime(spend_End);
            }
            if (null == spend_End) {
                spend_End = "";
            }
            String pic_Ids = detailsBean.getPic_Ids();

            List<ConsumptionTurnBean.ApplyBean.DetailsBean.PicsBean>   PicsBean=      detailsBean.getPics();
            String strPic = "";
            for( ConsumptionTurnBean.ApplyBean.DetailsBean.PicsBean pics :PicsBean){
                strPic += pics.getId() + ",";
            }
            strPic = strPic.substring(0, strPic.lastIndexOf(","));

            Log.e("Fragment", "ConsumptionTurnBean:" + strPic);

            String spend_City = detailsBean.getSpend_City();
            String detail_Id = Integer.toString(detailsBean.getDetail_Id());
            String detail_Memo = detailsBean.getDetail_Memo();

            if (null == pic_Ids) {
                pic_Ids = "";
            }
            if (null == spend_City) {
                spend_City = "";
            }
            if (null == detail_Id) {
                detail_Id = "";
            }
            if (null == detail_Memo) {
                detail_Memo = "";
            }

            Consume consumeDao = new Consume(null, detail_Id, detailsBean.getAndroid_Icon(), detailsBean.getSpend_Type_Str(), Integer.toString(detailsBean.getSpend_Type()), strPic, detailsBean.getMoney_Amount(), spend_Begin, spend_End, spend_City, detailsBean.getBill_Num(), detail_Memo);
            getConsumeDao().insert(consumeDao);
        }
        //  mAmount.setText("￥" + money_Sum);
        consumeList = getConsumeDao().loadAll();
        rbmBillListAdapter.setConsumeList(consumeList, font);
        rbmBillListAdapter.notifyDataSetChanged();

        //loadEditApply(HttpUtil.loadEditApply);
        toolbarDetele.setVisibility(View.VISIBLE);
        toolbarDetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backDialog(false);
                // showDialog(true);

            }
        });


    }


    private void initEditValuation(ConsumptionEditBean.ApplyBean apply) {

        //apply_Id = Integer.toString(apply.getApply_Id());
        group_Id = Integer.toString(apply.getGroup_Id());
        account_Id = Integer.toString(apply.getAccount_Id());

        switch (apply.getType()) {
            case 1:
                mApply.setText("日常报销");
                type_Code = "1";
                break;
            case 2:
                mApply.setText("差旅报销单");
                type_Code = "2";
                break;
            case 3:
                mApply.setText("付款申请单");
                type_Code = "2";
                break;
        }

        if (apply.getType_Str() != null) {
            mApply.setText(apply.getType_Str());
        }
        mTitle.setText(apply.getTitle());


        String date = ConversionUtil.getLongDateTime(apply.getApply_Date());
        mOption.setText(date);

        if (apply.getMemo() != null) {
            mEmo.setText(apply.getMemo());
        }

        if (apply.getGroup_Name() != null) {
            mDepartment.setText(apply.getGroup_Name());
        }
        if (apply.getAccount_Name() != null) {
            mPerson.setText(apply.getAccount_Name());
        }
        List<ConsumptionEditBean.ApplyBean.DetailsBean> detailsBeanList = apply.getDetails();

        for (ConsumptionEditBean.ApplyBean.DetailsBean detailsBean : detailsBeanList) {

            // money_Sum += detailsBean.getMoney_Amount();

            String spend_Begin = detailsBean.getSpend_Begin();
            String spend_End = detailsBean.getSpend_End();
            if (null != spend_Begin) {
                spend_Begin = ConversionUtil.getLongDateTime(spend_Begin);
            } else if (null == spend_Begin) {
                spend_Begin = "";
            }
            if (null != spend_End) {
                spend_End = ConversionUtil.getLongDateTime(spend_End);
            }
            if (null == spend_End) {
                spend_End = "";
            }
            String pic_Ids = detailsBean.getPic_Ids();
            List<ConsumptionEditBean.ApplyBean.DetailsBean.PicsBean> picsBeanList=    detailsBean.getPics();
            String strPic = "";
            for( ConsumptionEditBean.ApplyBean.DetailsBean.PicsBean pics :picsBeanList){
                strPic += pics.getId() + ",";
            }
            strPic = strPic.substring(0, strPic.lastIndexOf(","));

            Log.e("Fragment", "ConsumptionEditBean:" + strPic);

            String spend_City = detailsBean.getSpend_City();
            String detail_Id = Integer.toString(detailsBean.getDetail_Id());
            String detail_Memo = detailsBean.getDetail_Memo();
            if (null == pic_Ids) {
                pic_Ids = "";
            }
            if (null == spend_City) {
                spend_City = "";
            }
            if (null == detail_Id) {
                detail_Id = "";
            }
            if (null == detail_Memo) {
                detail_Memo = "";
            }

            Consume consumeDao = new Consume(null, detail_Id, detailsBean.getAndroid_Icon(), detailsBean.getSpend_Type_Str(), Integer.toString(detailsBean.getSpend_Type()), strPic, detailsBean.getMoney_Amount(), spend_Begin, spend_End, spend_City, detailsBean.getBill_Num(), detail_Memo);
            getConsumeDao().insert(consumeDao);
        }
        //  mAmount.setText("￥" + money_Sum);
        consumeList = getConsumeDao().loadAll();
        rbmBillListAdapter.setConsumeList(consumeList, font);
        rbmBillListAdapter.notifyDataSetChanged();

        //loadEditApply(HttpUtil.loadEditApply);
        toolbarDetele.setVisibility(View.VISIBLE);
        toolbarDetele.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                backDialog(false);
                // showDialog(true);

            }
        });
    }


    private void loadLastVisit(List<ConsumptionBean.GroupsBean> groupsBeanList, List<ConsumptionBean.BankAccountsBean> bankAccountsBeen) {
        for (ConsumptionBean.GroupsBean groupsBean : groupsBeanList) {
            if (group_Id.equals(Integer.toString(groupsBean.getGroup_Id()))) {
                Log.e("Fragment", "group_Id isFlag: " + group_Id);
                String Group_Name = groupsBean.getGroup_Name();
                mDepartment.setText(Group_Name);
                break;
            }

        }
        for (ConsumptionBean.BankAccountsBean bankAccounts : bankAccountsBeen) {
            if (Integer.toString(bankAccounts.getEmp_Bank_Id()).equals(account_Id)) {
                String bankCard = bankAccounts.getEmp_Bank_No();//卡号
                String lastCard = bankCard.substring(bankCard.length() - 4, bankCard.length());
                StringBuffer sb = new StringBuffer();
                sb.append(bankAccounts.getBank_Pay_Name()).append(" (").append(bankCard.substring(0, 4)).append("...").append(lastCard).append(")");
                mPerson.setText(sb.toString());
            }
            break;
        }


    }


    /**
     * 提交报销申请
     * <p/>
     * {'apply':
     * [{'group_Id':'97','emp_Id':'163','account_Id':'1922226633855887','cust_Id':'29','memo':'cesi','title':'IDIDMM','type':'2','apply_Date':'1480057041000','apply_Id':'35'}
     * ],
     * 'approval_Man':'100','details':
     * [{'detail_Id':'66','spend_End':'','spend_Begin':'1479830400000','money_Amount':'200','spend_Type':'19','bill_Num':'1','detail_Memo':'ceshi','pic_Ids':'13,14','spend_City':''}
     * ],
     * 'methodname':'expense/submitExpenseApply.json','emp_Id':'163','cust_Id':'29'}
     * <p/>
     * 5. 保存报销申请
     * {'methodname':'expense/saveExpenseApply.json','emp_Id':'','cust_Id':'','apply':'','details':''}
     */

    public String applyObject;
    public String detailsObject;

    public void postApplyBillData(String loadEmpInfo, boolean flag) {

        String memo = mEmo.getText().toString();
        String title = mTitle.getText().toString();
        String apply_Date = mOption.getText().toString();


        String[] keyApply;
        String[] valueApply;

//        if (TextUtils.isEmpty(apply_Id)) {
//            keyApply = new String[]{"cust_Id", "emp_Id", "group_Id", "account_Id", "memo", "title", "type", "apply_Date"};
//            valueApply = new String[]{Integer.toString(cust_Id), Integer.toString(emp_Id), group_Id, account_Id, memo, title, type_Code, apply_Date};
//        } else {
        keyApply = new String[]{"cust_Id", "emp_Id", "group_Id", "account_Id", "memo", "title", "type", "apply_Date", "apply_Id"};
        valueApply = new String[]{Integer.toString(cust_Id), Integer.toString(emp_Id), group_Id, account_Id, memo, title, type_Code, apply_Date, apply_Id};
        //       }
        applyObject = HttpOkManagerUtils.StringBufferJson(keyApply, valueApply);
        StringBuffer sbApply = new StringBuffer();
        applyObject = sbApply.append("{").append(applyObject).append("}").toString();
        List<String> listDetail = new ArrayList<>();
        detail_Id = "";
        for (Consume consume : consumeList) {
            String spend_Type = consume.getTypeID();
            String spend_Begin = consume.getStarDate();
            String spend_End = consume.getEndDate();
            String spend_City = consume.getCity();
            String pic_Ids = consume.getPic_Ids();


            String detail_Memo = consume.getDescription();
            String money_Amount = consume.getMoney();
            String bill_Num = Integer.toString(consume.getCount());

            String detail_Id = consume.getDetail_Id();
            Log.d("Fragment", "pic_Ids:" + pic_Ids);
            Log.d("Fragment", "detail_Id:" + detail_Id);
            String[] keyDetail = new String[10];
            String[] valueDetail = new String[9];
//            if (TextUtils.isEmpty(detail_Id)) {
//                keyDetail = new String[]{"spend_End", "spend_Begin", "money_Amount", "spend_Type", "bill_Num", "detail_Memo", "pic_Ids", "spend_City"};
//                valueDetail = new String[]{spend_End, spend_Begin, money_Amount, spend_Type, bill_Num, detail_Memo, pic_Ids, spend_City};
//            } else {

            //    String detail_Id_Before_Imported = consume.getDetail_Id();

//未制消费单数据导入过来进行匹配参数 使用detail_Id_Before_Imported 参数，否则detail_Id参数匹配
            if (detailIdList.size() > 0) {
                for (String id : detailIdList) {
                    if ( detail_Id.equals(id)) {
                        keyDetail = new String[]{"detail_Id", "detail_Id_Before_Imported", "spend_End", "spend_Begin", "money_Amount", "spend_Type", "bill_Num", "detail_Memo", "pic_Ids", "spend_City"};
                        valueDetail = new String[]{"", detail_Id, spend_End, spend_Begin, money_Amount, spend_Type, bill_Num, detail_Memo, pic_Ids, spend_City};
                    }
                }
//                for (int i = 0; i < detailIdList.size(); i++) {
//                    keyDetail = new String[]{"detail_Id", "detail_Id_Before_Imported", "spend_End", "spend_Begin", "money_Amount", "spend_Type", "bill_Num", "detail_Memo", "pic_Ids", "spend_City"};
//                    valueDetail = new String[]{"", detail_Id_Before_Imported, spend_End, spend_Begin, money_Amount, spend_Type, bill_Num, detail_Memo, pic_Ids, spend_City};
//                }
            } else {
                keyDetail = new String[]{"detail_Id", "spend_End", "spend_Begin", "money_Amount", "spend_Type", "bill_Num", "detail_Memo", "pic_Ids", "spend_City"};
                valueDetail = new String[]{detail_Id, spend_End, spend_Begin, money_Amount, spend_Type, bill_Num, detail_Memo, pic_Ids, spend_City};
            }


            detailsObject = HttpOkManagerUtils.StringBufferJson(keyDetail, valueDetail);
            StringBuffer stringBuffer = new StringBuffer();
            detailsObject = stringBuffer.append("{").append(detailsObject).append("}").toString();
            listDetail.add(detailsObject);
        }
        Logger.d(listDetail);


        List<String> listApplay = new ArrayList<>();
        listApplay.add(applyObject);

        Logger.d(listApplay);


        String[] key;
        String[] value;
        if (flag) {
            key = new String[]{"emp_Id", "cust_Id", "apply", "details", "approval_Man"};
            value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), String.valueOf(listApplay), String.valueOf(listDetail), approval_Man};
        } else {
            key = new String[]{"emp_Id", "cust_Id", "apply", "details"};
            value = new String[]{Integer.toString(emp_Id), Integer.toString(cust_Id), String.valueOf(listApplay), String.valueOf(listDetail)};

        }


        HashMap<String, String> map = HttpOkManagerUtils.updateHashMapPost(loadEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.d(jsonObject.toString());

                MessageBean messageBean = gson.fromJson(jsonObject.toString(), MessageBean.class);
                if ("success".equals(messageBean.getMessage())) {

                    if (TextUtils.isEmpty(apply_Id)) {
                        mCache.put("Person", mPerson.getText().toString());
                        mCache.put("account_Id", account_Id);
                        mCache.put("Department", mDepartment.getText().toString());
                        mCache.put("group_Id", group_Id);
                        mCache.put("type_Code", type_Code);
                        mCache.put("mApply", mApply.getText().toString());
                    }

                    ReimburBillActivity.this.finish();
                } else
                    AppToast.makeShortToast(mContext, "提交失败");
                Logger.d(messageBean.getMessage());
            }

        });

    }

    /**
     * yyyy-MM-dd
     * 加载开始时间选项 pickerview
     */
    private void timeOptionDay() {

        pvTimeDay = new TimePickerView(this, TimePickerView.Type.YEAR_MONTH);
        pvTimeDay.setTime(new Date());
        pvTimeDay.setCyclic(false);
        pvTimeDay.setCancelable(true);
        //时间选择后回调
        pvTimeDay.setOnTimeSelectListener(new TimePickerView.OnTimeSelectListener() {
            @Override
            public void onTimeSelect(Date date) {
                //    hol_Begin = ConversionUtil.getTime(date);
                mOption.setText(ConversionUtil.getTime(date));


            }
        });

    }

    /**
     * 加载 审批人选项
     *
     * @param listBeen
     */
    private void loadPersonOption(final List<ConsumptionBean.BankAccountsBean> listBeen) {

        //选项选择器
        pvOptionsPerson = new OptionsPickerView(this);
        //选项1
        int i = 0;
        for (ConsumptionBean.BankAccountsBean bankAccountsBean : listBeen) {
            String bankCard = bankAccountsBean.getEmp_Bank_No();//卡号
            String lastCard = bankCard.substring(bankCard.length() - 4, bankCard.length());
            StringBuffer sb = new StringBuffer();
            sb.append(bankAccountsBean.getBank_Pay_Name()).append(" (").append(bankCard.substring(0, 4)).append("...").append(lastCard).append(")");

            options1Ttems.add(new ProvinceBean(i++, sb.toString(), "11", "1"));
        }

        pvOptionsPerson.setPicker(options1Ttems);
        pvOptionsPerson.setTitle("请选择");
        pvOptionsPerson.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsPerson.setSelectOptions(1);
        pvOptionsPerson.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options1Ttems.get(options1).getPickerViewText();
                mPerson.setText(tx);
                account_Id = Integer.toString(listBeen.get(options1).getEmp_Bank_Id());


            }
        });
    }

    /**
     * 加载 部门选项
     *
     * @param listBeen
     */
    private void loadGroupsOption(final List<ConsumptionBean.GroupsBean> listBeen) {

        //选项选择器
        pvOptionsGroups = new OptionsPickerView(this);
        //选项1
        int i = 0;
        int j = 0;
        for (ConsumptionBean.GroupsBean groupsBean : listBeen) {
            options2Items.add(new ProvinceBean(i++, groupsBean.getGroup_Name(), "11", "1"));
        }

        pvOptionsGroups.setPicker(options2Items);
        pvOptionsGroups.setTitle("请选择");
        pvOptionsGroups.setCyclic(false);
        //设置默认选中的1级项目
        pvOptionsGroups.setSelectOptions(1);
        pvOptionsGroups.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {

                String tx = options2Items.get(options1).getPickerViewText();
                mDepartment.setText(tx);
                group_Id = String.valueOf(listBeen.get(options1).getGroup_Id());


            }
        });
    }


    private void backDialog(boolean b) {
        if (ibuilder == null) {
            ibuilder = new CustomIosDialog.Builder(this);
        }
        ibuilder.setTitle(R.string.prompt);
        if (b) {
            ibuilder.setMessage(R.string.exit_app);
            ibuilder.setPositiveButton(R.string.back, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {

                    dialog.dismiss();
                    deleteAllNote();
                    ReimburBillActivity.this.finish();
                }
            });
        } else {
            ibuilder.setMessage(R.string.exit_delete_app);
            ibuilder.setPositiveButton(R.string.determine, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                    onDeleteSaveBill(HttpUtil.deleteApply);
                    ReimburBillActivity.this.finish();
                }
            });
        }


        ibuilder.setNegativeButton(R.string.cancel, null);
        ibuilder.create().show();
    }

    @Override
    public void confirmDialog() {

    }

    /**
     * 这是兼容的 AlertDialog
     */
    private void showDialog(boolean b) {
  /*
  这里使用了 android.support.v7.app.AlertDialog.Builder
  可以直接在头部写 import android.support.v7.app.AlertDialog
  那么下面就可以写成 AlertDialog.Builder
  */
        android.support.v7.app.AlertDialog.Builder builder = new android.support.v7.app.AlertDialog.Builder(this);
        builder.setTitle("Material Design Dialog");
        if (b) {
            builder.setMessage(R.string.exit_delete_app);
            builder.setPositiveButton("确定", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    onDeleteSaveBill(HttpUtil.deleteApply);
                    ReimburBillActivity.this.finish();
                }
            });
        } else {
            builder.setMessage(R.string.exit_app);
            builder.setPositiveButton("返回", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    deleteAllNote();
                    ReimburBillActivity.this.finish();
                }
            });
        }


        builder.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });

        builder.show();
    }

    private void onDeleteSaveBill(String deleteApply) {
        String[] key = new String[]{"apply_Id"};
        String[] value = new String[]{apply_Id};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(deleteApply, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(deleteApply, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.d(jsonObject.toString());
            }
        });
    }

    @Override
    public void onBackPressed() {
        //   super.onBackPressed();
        // showDialog(false);
        Log.e("Fragment", "onBackPressed isFlag: " + isFlag);
        showDialog();
    }

    public void showDialog() {

        if (!isFlag) {
            if (!TextUtils.isEmpty(mTitle.getText())) {
                backDialog(true);
            } else {
                deleteAllNote();
                ReimburBillActivity.this.finish();
            }
        } else {
            deleteAllNote();
            ReimburBillActivity.this.finish();
        }
    }

}
