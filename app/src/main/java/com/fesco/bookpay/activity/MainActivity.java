package com.fesco.bookpay.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.fesco.bookpay.FApplication;
import com.fesco.bookpay.adapter.FragmentTabAdapter;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.UpdateStatus;
import com.fesco.bookpay.entity.VersionInfo;
import com.fesco.bookpay.fragment.tabfragment.HelpFragment;
import com.fesco.bookpay.fragment.tabfragment.MyFragment;
import com.fesco.bookpay.fragment.tabfragment.WordFragment;
import com.fesco.bookpay.util.ACache;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.UpdateVersionUtil;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.google.gson.Gson;
import com.hyphenate.chat.EMClient;
import com.hyphenate.util.EMLog;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import de.greenrobot.dao.query.QueryBuilder;
import me.bookpay.greendao.ImagePhotos;
import me.bookpay.greendao.ImagePhotosDao;

/**
 * 主页面
 */
public class MainActivity extends FragmentActivity {
    public static final String ACCOUNT_CONFLICT = "conflict";
    private RadioGroup rgBottom;
    private RadioButton rbWroid;
    private RadioButton rbHelp;
    private RadioButton rbCounsle;
    private Toolbar toolbar;
    private TextView title;
    private TextView back;

    public List<Fragment> fragments = new ArrayList<Fragment>();
    public ACache aCache;
    public LoginEntity loginEntity;
    private Gson gson;
    private String aCacheVersion_No = "";
    private  AlertDialog.Builder exceptionBuilder;
    private  boolean isExceptionDialogShow = false;
    // private  VersionInfo versionInfo;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window window = getWindow();
            // Translucent status bar
            window.setFlags(
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }

        aCache = ACache.get(this);
        gson = new Gson();
        loginEntity = (LoginEntity) aCache.getAsObject("loginEntity");
        initView();
        showExceptionDialogFromIntent(getIntent());

        initdata();
        //本地测试检测是否有新版本发布
        getversion();
        initPhotodata();
    }



    private void updateGetVersion(VersionInfo versionInfo) {
        if (versionInfo != null) {
            final List<VersionInfo.AppStoreBean> appStoreBeen = versionInfo.getAppStore();
            if (appStoreBeen != null && !appStoreBeen.isEmpty()) {


                UpdateVersionUtil.localCheckedVersion(MainActivity.this, appStoreBeen.get(0), new UpdateVersionUtil.UpdateListener() {

                    @Override
                    public void onUpdateReturned(int updateStatus, VersionInfo.AppStoreBean versionInfo) {
                        //判断回调过来的版本检测状态
                        switch (updateStatus) {
                            case UpdateStatus.YES:
                                //弹出更新提示
                                UpdateVersionUtil.showDialog(MainActivity.this, versionInfo);
                                break;
                            case UpdateStatus.NO:
                                //没有新版本
                                //       AppToast.showShortText(getApplicationContext(), "已经是最新版本了!");
                                //存储c版本号
                                break;
                            case UpdateStatus.NOWIFI:
                                //当前是非wifi网络
                                AppToast.showShortText(getApplicationContext(), "当前非wifi网络,下载会消耗手机流量！");

                                UpdateVersionUtil.showDialog(MainActivity.this, versionInfo);

                                break;
                            case UpdateStatus.ERROR:
                                //检测失败
                                AppToast.showShortText(getApplicationContext(), "检测失败，请稍后重试！");
                                break;
                            case UpdateStatus.TIMEOUT:
                                //链接超时
                                AppToast.showShortText(getApplicationContext(), "链接超时，请检查网络设置!");
                                break;
                        }
                    }

                });
            }
        }
    }



    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    private void initView() {

        rgBottom = (RadioGroup) findViewById(R.id.main_bottom);
        rbWroid = (RadioButton) findViewById(R.id.rb_word);
        rbHelp = (RadioButton) findViewById(R.id.rb_help);
        rbCounsle = (RadioButton) findViewById(R.id.rb_counsle);
        toolbar = (Toolbar) findViewById(R.id.toolbar_main);
        toolbar.setTitle("");
        title = (TextView) findViewById(R.id.toolbar_main_title);
        title.setText("工作");
        back = (TextView) findViewById(R.id.toolbar_main_back);
        ImageView search = (ImageView) findViewById(R.id.toolbar__main_search);
        //  Toolbar   toolbar = (Toolbar)layout.findViewById(R.id.toolbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EMClient.getInstance().logout(true);
                aCache.clear();
                MainActivity.this.finish();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


        fragments.add(WordFragment.getInstance(loginEntity));
        fragments.add(new HelpFragment());
//        fragments.add(new CounsleFragment());

        fragments.add(MyFragment.getInstance(loginEntity));
        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content, rgBottom);
        tabAdapter.setRadioButton(rbWroid, rbHelp, rbCounsle, title, search, back, aCache);
    }


    private void initdata() {

        String[] key = new String[]{"cust_Id"};
        String[] value = new String[]{Integer.toString(loginEntity.getCust_Id())};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(HttpUtil.getMenuPath, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(HttpUtil.getMenuPath, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());

            }


        });

    }


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        showExceptionDialogFromIntent(intent);

    }



    private void showExceptionDialogFromIntent(Intent intent) {

        if (!isExceptionDialogShow && intent.getBooleanExtra(ACCOUNT_CONFLICT, false)) {
            showExceptionDialog();
        }


    }

    private void showExceptionDialog() {
        isExceptionDialogShow = true;
        if (!MainActivity.this.isFinishing()) {
            // clear up global variables
            try {
                if (exceptionBuilder == null)
                    exceptionBuilder = new android.app.AlertDialog.Builder(MainActivity.this);
                exceptionBuilder.setTitle("下线通知");
                exceptionBuilder.setMessage("同一帐号已在其他设备登录");
                exceptionBuilder.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        exceptionBuilder = null;
                        isExceptionDialogShow = false;
                        Log.e("Fragment", "---------Main showExceptionDialog error");
                        finish();
                        Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_NEW_TASK);
                        startActivity(intent);
                    }
                });
                exceptionBuilder.setCancelable(false);
                exceptionBuilder.create().show();
            } catch (Exception e) {
                EMLog.e("Fragment", "---------color conflictBuilder error" + e.getMessage());
            }
        }


    }




    /**
     * 网络请求
     */
    private void initPhotodata() {

        aCacheVersion_No = aCache.getAsString("version_No");
        Log.d("Fragment", " aCacheVersion_No " + aCacheVersion_No);
        if (TextUtils.isEmpty(aCacheVersion_No)) {
            aCacheVersion_No = "0";
        }

        final String token = loginEntity.getToken();
        int cust_Id = loginEntity.getCust_Id();
        String[] key = new String[]{"cust_Id", "version_No"};
        String[] value = new String[]{Integer.toString(cust_Id), aCacheVersion_No};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(HttpUtil.getEmpsPhotos, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(HttpUtil.getEmpsPhotos, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());
                JSONObject jObect = null;
                try {
                    jObect = new JSONObject(jsonObject.toString());
                    if (!jObect.has("empPhotos")) {
                        return;
                    }
                    JSONObject empPhotosObject = jObect.getJSONObject("empPhotos");
                    String version_No = jObect.getString("version_No");
                    if (version_No.equals(aCacheVersion_No)) {
                        return;
                    }

                    aCache.put("version_No", version_No);
                    //通过迭代器获取这段json当中所有的key值
                    Iterator keys = empPhotosObject.keys();
                    //然后通过一个循环取出所有的key值
                    while (keys.hasNext()) {
                        String key = String.valueOf(keys.next());
                        //最后就可以通过刚刚得到的key值去解析后面的json了
                        String base64Data = empPhotosObject.getString(key);
                        QueryBuilder<ImagePhotos> qb = getImagePhotos().queryBuilder().where(ImagePhotosDao.Properties.Emp_Id.eq(key));
                        List<ImagePhotos> imagePhotosList = qb.list();

                        if (imagePhotosList != null && !imagePhotosList.isEmpty()) {
                            Log.d("Fragment", key + " 初始化 更新数据 ");
                            //更新数据
                            for (ImagePhotos imagePhotos : imagePhotosList) {
                                if (key.equals(imagePhotos.getEmp_Id())) {
                                    //  ImagePhotos imagePhotos1 = new ImagePhotos(null, version_No, key, base64Data);
                                    getImagePhotos().update(imagePhotos);
                                    Log.e("Fragment", key + " 初始化 更新数据 成功 " + version_No);
                                }
                            }
                        } else {
                            //插入新数据
                            Log.d("Fragment", key + " 初始化 插入数据 " + version_No);
                            ImagePhotos image = new ImagePhotos(null, version_No, key, base64Data);
                            getImagePhotos().insert(image);

                        }


                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }

        });

    }


    private ImagePhotosDao getImagePhotos() {
        // 通过 BaseApplication 类提供的 getDaoSession() 获取具体 Dao
        return ((FApplication) this.getApplicationContext()).getDaoSession().getImagePhotosDao();
    }


    public void getversion() {
        OKManager manager = OKManager.getInstance(this);

        HashMap<String, String> map = new HashMap<String, String>();
        map.put("app_Type","1");
        map.put("app_Name","书薪APP");
        manager.sendComplexForm(HttpUtil.getAppStore,map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());
                VersionInfo versionInfo = gson.fromJson(jsonObject.toString(), VersionInfo.class);
                updateGetVersion(versionInfo);
            }

//            @Override
//            public void onResponse(String result) {
//                Logger.json(result);
//                VersionInfo versionInfo = gson.fromJson(result, VersionInfo.class);
//                updateGetVersion(versionInfo);
//            }
        });

    }


}
