package com.fesco.bookpay.util.okhttp;

import android.content.Context;

import com.fesco.bookpay.impl.RetrofitService;
import com.fesco.bookpay.util.HttpUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by shiyan on 2016/10/9.
 */
public class RetrofitClient {
    /**
     * 接口测试地址
     * http://api.k780.com:88/?app=idcard.get&&appkey=10003&&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&&format=json&&idcard=110101199001011114
     * <p/>
     * {"success":"1","result":{"status":"ALREADY_ATT","par":"110101","idcard":"110101199001011114","born":"1990年01月01日","sex":"男","att":"北京市 东城区 ","postno":"100000","areano":"010","style_simcall":"中国,北京","style_citynm":"中华人民共和国,北京市"}}
     */


    public static String baseUrl1 = " http://api.k7801.com:88";
    private static Context context;
    private static OkHttpClient okHttpClient;

    public static RetrofitService getInstance(Context context) {
        if (okHttpClient == null) {
            RetrofitClient.context = context;
            OkHttpClient.Builder builder = new OkHttpClient().newBuilder();
            //设置超时
            builder.connectTimeout(15, TimeUnit.SECONDS);
            builder.readTimeout(20, TimeUnit.SECONDS);
            builder.writeTimeout(20, TimeUnit.SECONDS);
            //错误重连
            builder.retryOnConnectionFailure(true);

            //OkHttp添加拦截器
            if (true) {
                // Log信息拦截器
                HttpLoggingInterceptor loggingInterceptor = new HttpLoggingInterceptor();
                loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
                //设置 Debug Log 模式
                builder.addInterceptor(loggingInterceptor);
            }
            /**
             * 添加缓存处理
             */
            //buildCache(builder, true);

            /**
             *  添加公共参数拦截器
             */
            //builder.addInterceptor(addQueryParameterInterceptor);


            /**
             * 添加请求头拦截器
             */
            //builder.addInterceptor(headerInterceptor);


            /**
             * 配置Cookie
             */
//            buildCookie(builder);


            okHttpClient = builder.build();
        }


        Retrofit retrofit = new Retrofit.Builder().baseUrl(HttpUtil.baseRetrofitUrl).addConverterFactory(GsonConverterFactory.create()).client(okHttpClient).build();
        RetrofitService service = retrofit.create(RetrofitService.class);
        return service;
    }


}
