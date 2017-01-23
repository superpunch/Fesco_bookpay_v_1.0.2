package com.fesco.bookpay.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fesco.bookpay.util.AppToast;

/**
 * Gradle build finished with 472 error(s) in 2m 15s 657ms
 * Created by gong.min on 2016/11/18.
 */
public class ConsumptionTowActivity extends AppCompatActivity {
    TextView textView;
    float scaleWidth;
    float scaleHeight;
    ImageView  imageview;
    Bitmap      bp;
    int h;
    boolean num=false;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       setContentView(R.layout.frametest);
      //  LinearLayout linearLayout= (LinearLayout) findViewById(R.id.linear);
        FrameLayout frameLayout= (FrameLayout) findViewById(R.id.fragmens);
            FrameLayout layout = new FrameLayout(this);
            // 为布局设置宽度和高度
            FrameLayout.LayoutParams LayoutParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
            // 为图片设置高度和宽度
            FrameLayout.LayoutParams imageLayoutParams = new FrameLayout.LayoutParams(
                    111, 111);
            // 为按钮设置宽度和高度
            FrameLayout.LayoutParams buttonLayoutParams = new FrameLayout.LayoutParams(
                    180, 173);
            // 为文字设置宽和高
            FrameLayout.LayoutParams textLayoutParams = new FrameLayout.LayoutParams(
                    ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            ImageView imageView = new ImageView(this);// 创建ImageView对象
            imageView.setImageResource(R.drawable.head);// 设置图片信息
            layout.addView(imageView, imageLayoutParams);// 将imageView添加到Framelayout布局当中

            Button button = new Button(this);//创建Button对象
            button.setText("button");//设置标题
            layout.addView(button, buttonLayoutParams);//将按钮增加到Framelayout布局当中

            TextView textView = new TextView(this);//创建textView对象
            textView.setId(R.id.addName_1);
            textView.setText("TextView");//设置标题
            textLayoutParams.gravity = Gravity.TOP | Gravity.RIGHT;
            layout.addView(textView, textLayoutParams);//将TextView添加到Framelayout当中
            frameLayout.addView(layout, LayoutParams);

     //   }

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppToast.makeShortToast(ConsumptionTowActivity.this,"1234556667");

            }
        });
        LayoutInflater flater = LayoutInflater.from(this);
        View xmlView  = flater.inflate(R.layout.frametest, null);



        LinearLayout     mLl_parent=(LinearLayout) findViewById(R.id.ll_parent);
        LinearLayout     mLl_parent2=(LinearLayout) findViewById(R.id.ll_parent2);
        LinearLayout     mLl_parent3=(LinearLayout) findViewById(R.id.ll_parent3);
        LinearLayout     mLl_parent4=(LinearLayout) findViewById(R.id.ll_parent4);

        mLl_parent4.addView(addView4());
        mLl_parent.addView(addView1());

        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        FrameLayout.LayoutParams vlp2 = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);


        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        vlp2.gravity = Gravity.TOP | Gravity.RIGHT;
        tv1.setLayoutParams(vlp);//设置TextView的布局
        tv2.setLayoutParams(vlp2);
        tv1.setText("姓名:");
        tv2.setText("李四");
        tv2.setPadding(50, 0, 0, 0);//设置边距


        mLl_parent2.addView(addView2());
        mLl_parent2.addView(addView3());
        mLl_parent3.addView(tv1);
        mLl_parent3.addView(tv2);

        WindowManager wm = (WindowManager) this.getSystemService(Context.WINDOW_SERVICE);
        int w = wm.getDefaultDisplay().getWidth();
        int h = wm.getDefaultDisplay().getHeight();
        imageview=(ImageView)findViewById(R.id.imageview);
        bp= BitmapFactory.decodeResource(getResources(),R.drawable.trophy);
        int width=bp.getWidth();
        int height=bp.getHeight();
//        int w=display.getWidth();
//        int h=display.getHeight();
        scaleWidth=((float)w)/width;
        scaleHeight=((float)h)/height;
        imageview.setImageBitmap(bp);



    }

    private View addView4(){
        //  LinearLayout linearLayout= (LinearLayout) findViewById(R.id.linear);
        FrameLayout layout = new FrameLayout(this);

        // 为布局设置宽度和高度
        FrameLayout.LayoutParams LayoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        layout.setLayoutParams(LayoutParams);
        // 为图片设置高度和宽度
        FrameLayout.LayoutParams imageLayoutParams = new FrameLayout.LayoutParams(
                111, 111);
        // 为按钮设置宽度和高度

        FrameLayout.LayoutParams buttonLayoutParams = new FrameLayout.LayoutParams(
                180, 173);
        // 为文字设置宽和高
        FrameLayout.LayoutParams textLayoutParams = new FrameLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);

        ImageView imageView = new ImageView(this);// 创建ImageView对象
        imageView.setImageResource(R.drawable.head);// 设置图片信息
        layout.addView(imageView, imageLayoutParams);// 将imageView添加到Framelayout布局当中

        Button button = new Button(this);//创建Button对象
        button.setText("button");//设置标题
        layout.addView(button, buttonLayoutParams);//将按钮增加到Framelayout布局当中

        TextView textView = new TextView(this);//创建textView对象
        textView.setId(R.id.addName_1);
        textView.setText("TextView");//设置标题
        textLayoutParams.gravity = Gravity.TOP | Gravity.RIGHT;
        layout.addView(textView, textLayoutParams);//将TextView添加到Framelayout当中

        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppToast.makeShortToast(ConsumptionTowActivity.this,"44444444444");





            }
        });
        return  layout;

   //     Display display=getWindowManager().getDefaultDisplay();


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch(event.getAction()){

            case MotionEvent.ACTION_DOWN:
                if(num==true)        {
                    Matrix matrix=new Matrix();
                    matrix.postScale(scaleWidth,scaleHeight);

                    Bitmap newBitmap=Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
                    imageview.setImageBitmap(newBitmap);
                    num=false;
                }
                else{
                    Matrix matrix=new Matrix();
                    matrix.postScale(1.0f,1.0f);
                    Bitmap newBitmap=Bitmap.createBitmap(bp, 0, 0, bp.getWidth(), bp.getHeight(), matrix, true);
                    imageview.setImageBitmap(newBitmap);
                    num=true;
                }
                break;
        }


        return super.onTouchEvent(event);
    }



    private View addView1() {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(this);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        tv1.setLayoutParams(vlp);//设置TextView的布局
        tv2.setLayoutParams(vlp2);
        tv1.setText("姓名:");
        tv2.setText("李四");
        tv2.setPadding(50, 0, 0, 0);//设置边距
        view.addView(tv1);//将TextView 添加到子View 中
        view.addView(tv2);//将TextView 添加到子View 中
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppToast.makeShortToast(ConsumptionTowActivity.this,"11111111");

            }
        });
        return view;
    }

    private View addView2() {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.  LayoutParams.WRAP_CONTENT,ViewGroup. LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(this);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        tv1.setLayoutParams(vlp);//设置TextView的布局
        tv2.setLayoutParams(vlp2);
        tv1.setText("姓名:");
        tv2.setText("李四");
        tv2.setPadding(50, 0, 0, 0);//设置边距
       // view.addView(tv1);//将TextView 添加到子View 中
      //  view.addView(tv2);//将TextView 添加到子View 中
        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppToast.makeShortToast(ConsumptionTowActivity.this,"22222222");

            }
        });
        return tv1;
    }

    private View addView3() {
        // TODO 动态添加布局(java方式)
        LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
                ViewGroup.  LayoutParams.WRAP_CONTENT,ViewGroup. LayoutParams.WRAP_CONTENT);
        LinearLayout view = new LinearLayout(this);
        view.setLayoutParams(lp);//设置布局参数
        view.setOrientation(LinearLayout.HORIZONTAL);// 设置子View的Linearlayout// 为垂直方向布局
        //定义子View中两个元素的布局
        ViewGroup.LayoutParams vlp = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);
        ViewGroup.LayoutParams vlp2 = new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT);

        TextView tv1 = new TextView(this);
        TextView tv2 = new TextView(this);
        tv1.setLayoutParams(vlp);//设置TextView的布局
        tv2.setLayoutParams(vlp2);
        tv1.setText("姓名:");
        tv2.setText("李四");
        tv2.setPadding(50, 0, 0, 0);//设置边距
        // view.addView(tv1);//将TextView 添加到子View 中
        //  view.addView(tv2);//将TextView 添加到子View 中

        tv1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AppToast.makeShortToast(ConsumptionTowActivity.this,"33333333333");

            }
        });
        return tv2;
    }


}
