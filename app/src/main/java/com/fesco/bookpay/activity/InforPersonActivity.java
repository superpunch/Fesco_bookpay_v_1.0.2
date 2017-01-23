package com.fesco.bookpay.activity;

import android.Manifest;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Looper;
import android.provider.MediaStore;
import android.provider.Settings;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.bigkoo.pickerview.OptionsPickerView;
import com.fesco.bookpay.base.BaseActivity;
import com.fesco.bookpay.entity.InformationBean;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.entity.MessageBean;
import com.fesco.bookpay.entity.ProvinceBean;
import com.fesco.bookpay.impl.RetrofitService;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.BitmapUtils;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.okhttp.CropSquareTrans;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.fesco.bookpay.util.okhttp.RetrofitClient;
import com.fesco.bookpay.view.cliperimage.CircleImageView;
import com.fesco.bookpay.weight.dialog.PermissionDialogInfo;
import com.google.gson.Gson;
import com.orhanobut.logger.Logger;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * 人物信息模块
 * 使用pickerView加载选项
 * 用Fragment 搭建人物信息模块 可见InfromationActivity
 * 暂定使用InforPersonActivity来管理控制
 * Created by gong.min on 2016/9/20.
 */
public class InforPersonActivity extends BaseActivity implements View.OnClickListener {


    //缓存头像
    public static final String IMAGE_HEAD = "IMAGE_HEAD";

    private static final int REQUEST_CAPTURE = 100;
    //请求相册
    private static final int REQUEST_PICK = 101;
    //请求截图
    private static final int REQUEST_CROP_PHOTO = 102;
    //请求访问外部存储
    private static final int READ_EXTERNAL_STORAGE_REQUEST_CODE = 103;
    //请求写入外部存储
    private static final int WRITE_EXTERNAL_STORAGE_REQUEST_CODE = 104;
    //头像1
    private CircleImageView headImage1;
    //头像2
    private ImageView headImage2;
    //调用照相机返回图片临时文件
    private File tempFile;
    // 1: qq, 2: weixin
    private int type;


    private Button btnSaveInfor, btnMoadityPass;
    private Toolbar toolbar;
    private ImageView inforOption;
    private TextView inforName, inforSex;
    private EditText etMobile, etPhone, etWeixin, etMail, etAddress, et_postcode;
    private LoginEntity loginEntity;
    private String token;
    private int cust_Id, emp_Id;
    private int gender = 1;
    private Gson gson;

    private OptionsPickerView pvOptions; //选项选择器
    private ArrayList<ProvinceBean> options1Items = new ArrayList<>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_information);
        Bundle bundle = getIntent().getExtras();

        loginEntity = (LoginEntity) bundle.getSerializable("InformationActivity");
        if (loginEntity != null) {
            token = loginEntity.getToken();
            cust_Id = loginEntity.getCust_Id();
            emp_Id = loginEntity.getEmp_Id();
        }


        gson = new Gson();
        loadSexOption();
        initView();
        headImage1 = (CircleImageView) findViewById(R.id.image_informa);
        //  headImage2 = (ImageView) findViewById(R.id.head_image2);
//        RelativeLayout qqLayout = (RelativeLayout) findViewById(R.id.qqLayout);
//        RelativeLayout weixinLayout = (RelativeLayout) findViewById(R.id.weixinLayout);
        headImage1.setOnClickListener(this);
        //创建拍照存储的临时文件
        createCameraTempFile(savedInstanceState);
        //loadImageData(HttpUtil.showPicture);
        loadImageHead();


    }

    private void loadImageHead() {
       Bitmap bitmap= aCache.getAsBitmap(IMAGE_HEAD);
        if (bitmap != null) {
            headImage1.setImageBitmap(bitmap);
        } else
            {
                loadImageData(HttpUtil.showPicture);
            }
    }


    public void loadImageData(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id"};
        String[] value = new String[]{Integer.toString(emp_Id)};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexImageByURL(loadEmpInfo, map, new OKManager.Func2() {
                    @Override
                    public void onResponse(byte[] data) {
                        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {
                            Log.d("TAG", "Main Thread" + data.length);
                        } else {
                            Log.d("TAG", "Not Main Thread");
                        }
                        //        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);  //为何不能写在主线程里？？？？
                        if (data.length > 0) {
                            Bitmap bitmap = new CropSquareTrans().transform(BitmapFactory.decodeByteArray(data, 0, data.length));
                            aCache.put(IMAGE_HEAD, bitmap);
                            if (bitmap != null) {
                                headImage1.setImageBitmap(bitmap);
                            } else headImage1.setImageResource(R.drawable.headmax);
                        }
                    }
                }
        );
    }


    protected void initView() {

        toolbar = (Toolbar) this.findViewById(R.id.infor_toolbar);

        inforOption = (ImageView) this.findViewById(R.id.infor_option);
        inforName = (TextView) this.findViewById(R.id.tv_inforname);
        inforSex = (TextView) this.findViewById(R.id.tv_inforsex);
        etPhone = (EditText) this.findViewById(R.id.et_phone);
        etMobile = (EditText) this.findViewById(R.id.et_mobile);
        etWeixin = (EditText) this.findViewById(R.id.et_weixin);
        etMail = (EditText) this.findViewById(R.id.et_mail);
        etAddress = (EditText) this.findViewById(R.id.et_address);
        et_postcode = (EditText) this.findViewById(R.id.et_postcode);

        btnSaveInfor = (Button) this.findViewById(R.id.infor_save);
        btnMoadityPass = (Button) this.findViewById(R.id.infor_modify);
//（emp_Id, emp_Name, gender, mobile, phone, weixinid, email, address, zipcode)

        TextView textView = (TextView) toolbar.findViewById(R.id.toolbar_text);
        textView.setText("个人信息");
        toolbar.setTitle("");

        //this.setHasOptionsMenu(true);
        this.setSupportActionBar(toolbar);
        //设置导航Icon，必须在setSupportActionBar(toolbar)之后设置
        toolbar.setNavigationIcon(R.mipmap.ic_arrow_back_white_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
        inforOption.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                pvOptions.show();
            }
        });

        btnMoadityPass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(InforPersonActivity.this, InforPswdActivity.class);
                Bundle bundleInfor = new Bundle();
                bundleInfor.putSerializable("InformationActivity", loginEntity);
                intent.putExtras(bundleInfor);
                startActivity(intent);
            }
        });
        btnSaveInfor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUpadte(HttpUtil.updateEmpInfo);//保存个人信息
            }
        });

        loadData(HttpUtil.loadEmpInfo);//加载个人信息


    }

    /**
     * 加载选项 pickerview
     */
    private void loadSexOption() {
        //选项选择器
        pvOptions = new OptionsPickerView(this);
        //选项
        options1Items.add(new ProvinceBean(0, "男", "", ""));
        options1Items.add(new ProvinceBean(1, "女", "", ""));

        pvOptions.setPicker(options1Items);
        pvOptions.setCyclic(false);
        pvOptions.setSelectOptions(1);
        pvOptions.setOnoptionsSelectListener(new OptionsPickerView.OnOptionsSelectListener() {
            @Override
            public void onOptionsSelect(int options1, int option2, int options3) {
                //返回的分别是三个级别的选中位置
                String tx = options1Items.get(options1).getPickerViewText();
                inforSex.setText(tx);
                gender = options1 + 1;

            }
        });


    }

    private void getUpadte(String updateEmpInfo) {
        String emp_Name = inforName.getText().toString();

        String phone = etPhone.getText().toString();
        String mobile = etMobile.getText().toString();
        String weixinid = etWeixin.getText().toString();
        String email = etMail.getText().toString();
        String address = etAddress.getText().toString();
        String zipcode = et_postcode.getText().toString();

        String[] key = new String[]{"emp_Id", "emp_Name", "gender", "phone", "mobile", "weixinid", "email", "address", "zipcode"};
        String[] value = new String[]{emp_Id + "", emp_Name, gender + "", phone, mobile, weixinid, email, address, zipcode};

        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(updateEmpInfo, key, value, token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(updateEmpInfo, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());

                JSONObject object = null;
                try {
                    object = new JSONObject(jsonObject.toString());
                    String message = object.getString("message");
                    if ("success".equals(message)) {
                        Toast.makeText(getApplicationContext(), "修改成功", Toast.LENGTH_SHORT).show();
                    } else
                        Toast.makeText(getApplicationContext(), "保存失败！", Toast.LENGTH_SHORT).show();
                    Logger.i(message);
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }
        });


    }


    /**
     * 网络请求
     *
     * @param loadEmpInfo
     */
    public void loadData(String loadEmpInfo) {
        HashMap<String, String> map = HttpOkManagerUtils.okManagerPost(loadEmpInfo, cust_Id + "", emp_Id + "", token);
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexForm(loadEmpInfo, map, new OKManager.Func4() {
            @Override
            public void onResponse(JSONObject jsonObject) {
                Logger.json(jsonObject.toString());
                InformationBean informationBean = gson.fromJson(jsonObject.toString(), InformationBean.class);
                if (informationBean.getGender() == 2) {
                    inforSex.setText("女");
                } else inforSex.setText("男");
                inforName.setText(informationBean.getEmp_Name());


                if (TextUtils.isEmpty(informationBean.getPhone())) {
                    etMobile.setHint("请输入座机号");

                } else {
                    etMobile.setText(informationBean.getPhone());
                }
                if (TextUtils.isEmpty(informationBean.getMobile())) {
                    etPhone.setHint("请输入手机号");
                } else {
                    etPhone.setText(informationBean.getMobile());
                }
                if (TextUtils.isEmpty(informationBean.getWeixinid())) {
                    etWeixin.setHint("请输入微信号");

                } else {
                    etWeixin.setText(informationBean.getWeixinid());
                }
                if (TextUtils.isEmpty(informationBean.getEmail())) {
                    etMail.setHint("请输入Email");
                } else {
                    etMail.setText(informationBean.getEmail());
                }


                if (null != informationBean.getAddress()) {
                    etAddress.setText(informationBean.getAddress().toString());
                }
                if (null != informationBean.getZipcode()) {
                    et_postcode.setText(informationBean.getZipcode().toString());
                }


            }


        });
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {

        if (keyCode == event.KEYCODE_BACK) {
            if (pvOptions.isShowing()) {
                pvOptions.dismiss();
                return true;
            }
        }
        return super.onKeyDown(keyCode, event);

    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Logger.e("onRestart");
    }

    /**
     * 外部存储权限申请返回
     *
     * @param requestCode
     * @param permissions
     * @param grantResults
     */
    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == WRITE_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoCarema();
            } else {
                // Permission Denied
              //  showMissingPermissionDialog();
                PermissionDialogInfo permissionDialogInfo=new PermissionDialogInfo(context);
                permissionDialogInfo.setMessage("相机权限");
                permissionDialogInfo.show();

            }
        } else if (requestCode == READ_EXTERNAL_STORAGE_REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission Granted
                gotoPhoto();
            } else {
                // Permission Denied

            }
        }
    }

    // 显示缺失权限提示
    private void showMissingPermissionDialog() {
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.help);
        builder.setMessage(R.string.string_help_text);
        // 拒绝, 退出应用
        builder.setNegativeButton(R.string.quit, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                builder.show().dismiss();
            }
        });

        builder.setPositiveButton(R.string.settings, new DialogInterface.OnClickListener() {
            @Override public void onClick(DialogInterface dialog, int which) {
                startAppSettings();
            }
        });

        builder.setCancelable(false);

        builder.show();
    }
    private static final String PACKAGE_URL_SCHEME = "package:"; // 方案
    // 启动应用的设置
    private void startAppSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse(PACKAGE_URL_SCHEME + getPackageName()));
        startActivity(intent);
    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image_informa:
                type = 1;
                uploadHeadImage();
                break;
//            case R.id.weixinLayout:
//                type = 2;
//                uploadHeadImage();
//                break;
        }
    }


    /**
     * 上传头像
     */
    private void uploadHeadImage() {
        View view = LayoutInflater.from(this).inflate(R.layout.layout_popupwindow, null);
        TextView btnCarema = (TextView) view.findViewById(R.id.btn_camera);
        TextView btnPhoto = (TextView) view.findViewById(R.id.btn_photo);
        TextView btnCancel = (TextView) view.findViewById(R.id.btn_cancel);
        final PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        popupWindow.setBackgroundDrawable(getResources().getDrawable(android.R.color.transparent));
        popupWindow.setOutsideTouchable(true);
        View parent = LayoutInflater.from(this).inflate(R.layout.activity_main, null);
        popupWindow.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        //popupWindow在弹窗的时候背景半透明
        final WindowManager.LayoutParams params = getWindow().getAttributes();
        params.alpha = 0.5f;
        getWindow().setAttributes(params);
        popupWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
            @Override
            public void onDismiss() {
                params.alpha = 1.0f;
                getWindow().setAttributes(params);
            }
        });

        btnCarema.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {  //WRITE_EXTERNAL_STORAGE
                //权限判断
                if (ContextCompat.checkSelfPermission(InforPersonActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请WRITE_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(InforPersonActivity.this, new String[]{Manifest.permission.CAMERA},
                            WRITE_EXTERNAL_STORAGE_REQUEST_CODE);



                } else {
                    //跳转到调用系统相机
                    gotoCarema();
                }
                popupWindow.dismiss();
            }
        });
        btnPhoto.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //权限判断
                if (ContextCompat.checkSelfPermission(InforPersonActivity.this, Manifest.permission.READ_EXTERNAL_STORAGE)
                        != PackageManager.PERMISSION_GRANTED) {
                    //申请READ_EXTERNAL_STORAGE权限
                    ActivityCompat.requestPermissions(InforPersonActivity.this, new String[]{Manifest.permission.READ_EXTERNAL_STORAGE},
                            READ_EXTERNAL_STORAGE_REQUEST_CODE);
                } else {
                    //跳转到调用系统图库
                    gotoPhoto();
                }
                popupWindow.dismiss();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popupWindow.dismiss();
            }
        });
    }

    /**
     * 跳转到相册
     */
    private void gotoPhoto() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(Intent.createChooser(intent, "请选择图片"), REQUEST_PICK);
    }


    /**
     * 跳转到照相机
     */
    private void gotoCarema() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(tempFile));
        startActivityForResult(intent, REQUEST_CAPTURE);
    }

    /**
     * 创建调用系统照相机待存储的临时文件
     *
     * @param savedInstanceState
     */
    private void createCameraTempFile(Bundle savedInstanceState) {
        if (savedInstanceState != null && savedInstanceState.containsKey("tempFile")) {
            tempFile = (File) savedInstanceState.getSerializable("tempFile");
        } else {
            tempFile = new File(checkDirPath(Environment.getExternalStorageDirectory().getPath() + "/image/"),
                    System.currentTimeMillis() + ".jpg");
        }
    }

    /**
     * 检查文件是否存在
     */
    private static String checkDirPath(String dirPath) {
        if (TextUtils.isEmpty(dirPath)) {
            return "";
        }
        File dir = new File(dirPath);
        if (!dir.exists()) {
            dir.mkdirs();
        }
        return dirPath;
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putSerializable("tempFile", tempFile);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent intent) {
        switch (requestCode) {
            case REQUEST_CAPTURE: //调用系统相机返回
                if (resultCode == RESULT_OK) {
                    gotoClipActivity(Uri.fromFile(tempFile));
                }
                break;
            case REQUEST_PICK:  //调用系统相册返回
                if (resultCode == RESULT_OK) {
                    Uri uri = intent.getData();
                    gotoClipActivity(uri);
                }
                break;
            case REQUEST_CROP_PHOTO:  //剪切图片返回
                if (resultCode == RESULT_OK) {
                    final Uri uri = intent.getData();
                    if (uri == null) {
                        return;
                    }
                    String camerPath = tempFile.getAbsolutePath();
                    Log.e("Fragment", intent.getStringExtra("path") + "  剪切图片返回 camerPath :" + camerPath);
                    Log.i("Fragment", "uri :" + uri);
                    String clipImagePath = intent.getStringExtra("path");
                    Log.d("Fragment", "剪切图片返回 :" + clipImagePath);
                    //   String cropImagePath = getRealFilePathFromUri(getApplicationContext(), uri);
                    Bitmap bitMap = BitmapUtils.readBitmapFromFileDescriptor(clipImagePath, 80, 80);
                    //     Bitmap bitMap = BitmapFactory.decodeFile(cropImagePath);
                    if (type == 1) {
                        headImage1.setImageBitmap(bitMap);
                    } else {
                        headImage2.setImageBitmap(bitMap);
                    }

                    uploadImageFile(clipImagePath);

                    //此处后面可以将bitMap转为二进制上传后台网络
                    //......

                }
                break;
        }
    }

    public void uploadImageFile(final String pathList) {
        Map<String, RequestBody> bodyMap = new HashMap<>();
        if (!TextUtils.isEmpty(pathList)) {
            File file = new File(pathList);
            bodyMap.put("emp_Id", toRequestBody(Integer.toString(emp_Id)));
            bodyMap.put("cust_Id", toRequestBody(Integer.toString(cust_Id)));
            bodyMap.put("file\"; filename=\"" + file.getName(), RequestBody.create(MediaType.parse("image/png"), file));
        }
        Log.e("Fragment", "emp_Id:" + emp_Id + " cust_Id:" + cust_Id + " 文件路径:" + pathList);
        //    "http://11.0.161.15/payroll/emp/uploadPic.json"; http://11.0.161.15:8080/payroll/emp/showPicture.json
        // RetrofitService service = RetrofitClient.getInstance(this, "http://11.0.161.15:8080/payroll/");
        RetrofitService service = RetrofitClient.getInstance(this);
        Call<MessageBean> personInfo = service.uploadImagesHead(bodyMap);
        personInfo.enqueue(new Callback<MessageBean>() {
            @Override
            public void onResponse(Call<MessageBean> call, Response<MessageBean> response) {
                //   {"picIds":[71,72],"errcode":0}      {"message":"单个文件超出最大值！！！","errcode":1}
                Log.e("Fragment", "Retrofit:" + response.message());
                MessageBean messageBean = response.body();
                if (0 == messageBean.getErrcode()) {
//                    for (String path : mYSelectPath) {
//                        File deleteFile = new File(path);
//                        deleteFile.delete();
//                        Log.e("options", "删除成功！");
//                    }

                    aCache.remove(IMAGE_HEAD);

                   // aCache.put("pathList", pathList);

                } else if (1 == messageBean.getErrcode()) {

                    AppToast.makeShortToast(InforPersonActivity.this, "单个文件超出最大值,请重新选择");
                }


            }

            @Override
            public void onFailure(Call<MessageBean> call, Throwable t) {

            }
        });


    }

    public RequestBody toRequestBody(String value) {        //   text/plain
        RequestBody body = RequestBody.create(MediaType.parse("multipart/form-data"), value);
        return body;

    }


    /**
     * 打开截图界面
     *
     * @param uri
     */
    public void gotoClipActivity(Uri uri) {
        if (uri == null) {
            return;
        }
        Intent intent = new Intent();
        intent.setClass(this, ClipImageActivity.class);
        intent.putExtra("type", type);
        intent.setData(uri);


        startActivityForResult(intent, REQUEST_CROP_PHOTO);
    }


    /**
     * 根据Uri返回文件绝对路径
     * 兼容了file:///开头的 和 content://开头的情况
     *
     * @param context
     * @param uri
     * @return the file path or null
     */
    public static String getRealFilePathFromUri(final Context context, final Uri uri) {
        if (null == uri) return null;
        final String scheme = uri.getScheme();
        String data = null;
        if (scheme == null)
            data = uri.getPath();
        else if (ContentResolver.SCHEME_FILE.equals(scheme)) {
            data = uri.getPath();
        } else if (ContentResolver.SCHEME_CONTENT.equals(scheme)) {
            Cursor cursor = context.getContentResolver().query(uri, new String[]{MediaStore.Images.ImageColumns.DATA}, null, null, null);
            if (null != cursor) {
                if (cursor.moveToFirst()) {
                    int index = cursor.getColumnIndex(MediaStore.Images.ImageColumns.DATA);
                    if (index > -1) {
                        data = cursor.getString(index);
                    }
                }
                cursor.close();
            }
        }
        return data;
    }


}
