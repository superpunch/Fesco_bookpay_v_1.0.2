package com.fesco.bookpay.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Looper;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.fesco.bookpay.adapter.FragmentTabAdapter;
import com.fesco.bookpay.adapter.cycleviewadapter.CycleViewPager;
import com.fesco.bookpay.entity.ADInfo;
import com.fesco.bookpay.entity.LoginEntity;
import com.fesco.bookpay.fragment.tabfragment.CounsleFragment;
import com.fesco.bookpay.fragment.tabfragment.HelpFragment;
import com.fesco.bookpay.fragment.tabfragment.MyFragment;
import com.fesco.bookpay.fragment.tabfragment.ToolFragment;
import com.fesco.bookpay.fragment.tabfragment.WordFragment;
import com.fesco.bookpay.util.ACache;
import com.fesco.bookpay.util.HttpUtil;
import com.fesco.bookpay.util.ViewFactory;
import com.fesco.bookpay.util.okhttp.CropSquareTrans;
import com.fesco.bookpay.util.okhttp.HttpOkManagerUtils;
import com.fesco.bookpay.util.okhttp.OKManager;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.orhanobut.logger.Logger;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * 主页面
 */
public class MainActivity extends FragmentActivity {
    private RadioGroup rgBottom;
    private RadioButton rbWroid;
    private RadioButton rbHelp;
    private RadioButton rbCounsle;
    private RadioButton rbTool;
    private RadioButton rbMy;
    private Toolbar toolbar;
    private TextView title;
    private TextView back;
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<ADInfo> infos = new ArrayList<ADInfo>();
    private CycleViewPager cycleViewPager;

    public List<Fragment> fragments = new ArrayList<Fragment>();
    public ACache aCache;
    public LoginEntity loginEntity;
    private String[] imageUrls = {"http://img.taodiantong.cn/v55183/infoimg/2013-07/130720115322ky.jpg",
            "http://pic30.nipic.com/20130626/8174275_085522448172_2.jpg",
            "http://pic18.nipic.com/20111215/577405_080531548148_2.jpg"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
//            Window window = getWindow();
//            // Translucent status bar
//            window.setFlags(
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS,
//                    WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
//        }
        aCache = ACache.get(this);
        loginEntity = (LoginEntity) aCache.getAsObject("loginEntity");

        String test = aCache.getAsString("test");
        Log.i("Fragment", "--------getToken: " + loginEntity.getToken());
        initView();
//        configImageLoader();
//        initialize();

        initdata();


    }

    @Override
    protected void onResume() {
        super.onResume();
        loadImageHead();
    }

    private void loadImageHead() {
        Bitmap bitmap= aCache.getAsBitmap(InforPersonActivity.IMAGE_HEAD);
        if (bitmap == null) {
            // java.lang.NullPointerException: Attempt to get length of null array
            loadImageData(HttpUtil.showPicture);
            Log.d("Fragment", "网络请求头像  ");
        }
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();

        Log.d("Fragment", "onDestroy  消耗Main页面");
    }

    private void initView() {

        rgBottom = (RadioGroup) findViewById(R.id.main_bottom);
        rbWroid = (RadioButton) findViewById(R.id.rb_word);
        rbHelp = (RadioButton) findViewById(R.id.rb_help);
        rbCounsle = (RadioButton) findViewById(R.id.rb_counsle);
        rbTool = (RadioButton) findViewById(R.id.rb_tool);
        rbMy = (RadioButton) findViewById(R.id.rb_my);
        //     View layout = getLayoutInflater().inflate(R.layout.test, null);
//        Toolbar  toolbar = (Toolbar) findViewById(R.id.main_toobar);
//                          findViewById(R.id.toolbar);
        title = (TextView) findViewById(R.id.toolbar_text);
        back = (TextView) findViewById(R.id.toolbar_back);
        //  Toolbar   toolbar = (Toolbar)layout.findViewById(R.id.toolbar);
        Logger.e("Main : " + title);
        Logger.e("Main : " + toolbar);
        back.setVisibility(View.VISIBLE);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                aCache.clear();
                MainActivity.this.finish();
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);

            }
        });


        title.setText("工作");
//        toolbar.setTitle("");
        fragments.add(WordFragment.getInstance(loginEntity));
        fragments.add(new HelpFragment());
        fragments.add(new CounsleFragment());
        fragments.add(new ToolFragment());
        fragments.add(new MyFragment());
        FragmentTabAdapter tabAdapter = new FragmentTabAdapter(this, fragments, R.id.tab_content, rgBottom, title);


        //   toolbar = (Toolbar)main_toobar.findViewById(R.id.toolbar);
        //    title = (TextView)main_toobar. findViewById(R.id.toolbar_text);
        //     toolbar.setTitle("");
//        rgBottom.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
//            @Override
//            public void onCheckedChanged(RadioGroup group, int checkedId) {
//                switch (checkedId) {
//                    case 0:
//                        title.setText("首页");
//                        break;
//                    case 1:
//                        title.setText("自助");
//                        break;
//                    case 2:
//                        title.setText("咨询");
//                        break;
//                    case 3:
//                        title.setText("工具");
//                        break;
//                    case 4:
//                        title.setText("我的");
//                        break;
//                }
//            }
//        });
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
    public void loadImageData(String loadEmpInfo) {

        String[] key = new String[]{"emp_Id"};
        String[] value = new String[]{Integer.toString(loginEntity.getEmp_Id())};
        HashMap<String, String> map = HttpOkManagerUtils.updateEmpInfoPost(loadEmpInfo, key, value, loginEntity.getToken());
        OKManager manager = OKManager.getInstance(this);
        manager.sendComplexImageByURL(loadEmpInfo, map, new OKManager.Func2() {
                    @Override
                    public void onResponse(byte[] data) {
                        if (Looper.getMainLooper().getThread() == Thread.currentThread()) {

                            Log.d("TAG", "Main Thread"+data.length);
                        } else {
                            Log.d("TAG", "Not Main Thread");
                        }

                        if(data.length>0){
                            Log.d("TAG", "Main Thread222"+data.length);
                            //        Bitmap bitmap = BitmapFactory.decodeStream(inputStream);  //为何不能写在主线程里？？？？
                            Bitmap bitmap = new CropSquareTrans().transform(BitmapFactory.decodeByteArray(data, 0, data.length));
                            aCache.put(InforPersonActivity.IMAGE_HEAD,bitmap);
                        }


                    }
                }
        );
    }


    private void initialize() {

        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);

        for (int i = 0; i < imageUrls.length; i++) {
            ADInfo info = new ADInfo();
            info.setUrl(imageUrls[i]);
            info.setContent("图片-->" + i);
            infos.add(info);
        }

        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(infos.size() - 1).getUrl()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(this, infos.get(i).getUrl()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(this, infos.get(0).getUrl()));

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);

        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);

        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(ADInfo info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
                Toast.makeText(MainActivity.this,
                        "position-->" + info.getContent(), Toast.LENGTH_SHORT)
                        .show();
            }

        }

    };

    /**
     * 配置ImageLoder
     */
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.drawable.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.drawable.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.drawable.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(getApplicationContext()).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }


}
