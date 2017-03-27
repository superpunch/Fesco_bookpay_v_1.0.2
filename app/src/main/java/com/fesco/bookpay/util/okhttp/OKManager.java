package com.fesco.bookpay.util.okhttp;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Handler;
import android.os.Looper;

import com.fesco.bookpay.activity.LoginActivity;
import com.fesco.bookpay.entity.MessageBean;
import com.fesco.bookpay.util.ACache;
import com.fesco.bookpay.util.AppToast;
import com.fesco.bookpay.util.HTTPSUtils;
import com.fesco.bookpay.util.ptutils.ActivityManager;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.security.GeneralSecurityException;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by gm on 16/5/13.
 * 封装工具类
 */
public class OKManager {
    private static Activity mActivity;
    private OkHttpClient client;
    private volatile static OKManager manager;
    private Handler handler;
    private Gson gson = new Gson();
    //提交json数据
    private static final MediaType JSON = MediaType.parse("application/json;charset=utf-8");
    //提交字符串
    private static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown;charset=utf-8");

    private OKManager() {
        //client = new OkHttpClient();
        handler = new Handler(Looper.getMainLooper());

    //    mContext = context;
        X509TrustManager trustManager;
        SSLSocketFactory sslSocketFactory;
        final InputStream inputStream;
        try {
            inputStream = mActivity.getAssets().open("zrfesco.cer"); // 得到证书的输入流
            try {

                trustManager = HTTPSUtils.trustManagerForCertificates(inputStream);//以流的方式读入证书
                SSLContext sslContext = SSLContext.getInstance("TLS");
                sslContext.init(null, new TrustManager[]{trustManager}, null);
                sslSocketFactory = sslContext.getSocketFactory();

            } catch (GeneralSecurityException e) {
                throw new RuntimeException(e);
            }

            client = new OkHttpClient.Builder().
                    connectTimeout(10, TimeUnit.SECONDS)
                    .readTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(10, TimeUnit.SECONDS)
                    .sslSocketFactory(sslSocketFactory, trustManager)
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .build();
        } catch (IOException e) {
            e.printStackTrace();
        }









    }

    //采用单例模式获取对象
    public static OKManager getInstance(Activity activity) {
        //     OKManager instance = null;
        mActivity = activity;
        if (manager == null) {
            synchronized (OKManager.class) {
                if (manager == null) {
                    manager = new OKManager();
                    //   manager = instance;
                }
            }
        }
        return manager;
    }

    /**
     * 同步请求，在android开发中不常用，因为会阻塞UI线程
     *
     * @param url
     * @return
     */
    public String syncGetByURL(String url) {
        //构建一个request请求
        Request request = new Request.Builder().url(url).build();
        Response response = null;
        try {
            response = client.newCall(request).execute();//同步请求数据
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 请求指定的url返回的结果是json字符串
     *
     * @param url
     * @param callBack
     */
    public void asyncJsonStringByURL(String url, final Func1 callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonStringMethod(response.body().string(), callBack);
                }
            }
        });
    }

    /**
     * 请求返回的是json对象
     *
     * @param url
     * @param callBack
     */
    public void asyncJsonObjectByURL(String url, final Func4 callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonObjectMethod(response.body().toString(), callBack);
                }
            }
        });
    }

    /**
     * 请求返回的是byte字节数组
     *
     * @param url
     * @param callBack
     */
    public void asyncGetByteByURL(String url, final Func2 callBack) {
        final Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessByteMethod(response.body().bytes(), callBack);
                }
            }
        });
    }

    /**
     * 请求返回结果是imageView类型 bitmap 类型
     *
     * @param url
     * @param callBack
     */
    public void asyncDownLoadImageByURL(String url, final Func3 callBack) {
        Request request = new Request.Builder().url(url).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    byte[] data = response.body().bytes();
                    Bitmap bitmap = new CropSquareTrans().transform(BitmapFactory.decodeByteArray(data, 0, data.length));
                  //  callBack.onResponse(bitmap);
                }
            }
        });
    }

    /**
     * 模拟表单提交
     *
     * @param url
     * @param params
     * @param callBack
     */
    int serversLoadTimes=0;
    int maxLoadTimes=3;
    public void sendComplexForm(String url, Map<String, String> params, final Func4 callBack) {
        FormBody.Builder form_builder = new FormBody.Builder();//表单对象，包含以input开始的对象，以html表单为主
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                form_builder.add(entry.getKey(), entry.getValue());
            }
        }
        serversLoadTimes = 0;
        RequestBody request_body = form_builder.build();
        Request request = new Request.Builder().url(url).post(request_body).build();//采用post方式提交
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
                Looper.prepare();
                AppToast.makeShortToast(mActivity,"网络连接超时，请稍后再次重试");
                Looper.loop();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonObjectMethod(response.body().string(), callBack);

                }


            }
        });
    }

    /**
     * back Image
     *
     * @param url
     * @param params
     * @param callBack
     */
    public void sendComplexImageByURL(String url, Map<String, String> params, final Func2 callBack) {
        FormBody.Builder form_builder = new FormBody.Builder();//表单对象，包含以input开始的对象，以html表单为主
        if (params != null && !params.isEmpty()) {
            for (Map.Entry<String, String> entry : params.entrySet()) {
                form_builder.add(entry.getKey(), entry.getValue());
            }
        }
        RequestBody request_body = form_builder.build();
        Request request = new Request.Builder().url(url).post(request_body).build();//采用post方式提交
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {

                if (response != null && response.isSuccessful()) {
//流写入  与 字节写入 两种方式
                    //    Bitmap bitmap = BitmapFactory.decodeStream(inputStream);
                    //    Bitmap bitmap = new CropSquareTrans().transform(BitmapFactory.decodeByteArray(data, 0, data.length));z

//                    byte[] data = response.body().bytes();
//                    onSuccessByteMethod(data, callBack);

                    //InputStream inputStream = response.body().byteStream();
                    onSuccessByteMethod(response.body().bytes(), callBack);

                }

            }
        });
    }


    /**
     * 向服务器提交String请求
     *
     * @param url
     * @param content
     * @param callBack
     */
    public void sendStringByPostMethod(String url, String content, final Func4 callBack) {
        Request request = new Request.Builder().url(url).post(RequestBody.create(MEDIA_TYPE_MARKDOWN, content)).build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                e.printStackTrace();

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (response != null && response.isSuccessful()) {
                    onSuccessJsonObjectMethod(response.body().string(), callBack);
                }
            }
        });
    }

    /**
     * 请求返回的结果是json字符串
     *
     * @param jsonValue
     * @param callBack
     */
    private void onSuccessJsonStringMethod(final String jsonValue, final Func1 callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        callBack.onResponse(jsonValue);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }

                }
            }
        });
    }

    /**
     * 请求返回的是byte[] 数组
     *
     * @param data
     * @param callBack
     */
    private void onSuccessByteMethod(final byte[] data, final Func2 callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onResponse(data);
                }
            }
        });
    }

    /**
     * 请求返回的是InputStream
     *
     * @param data
     * @param callBack
     */
    private void onSuccessInputStreamMethod(final InputStream data, final Func3 callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    callBack.onResponse(data);
                }
            }
        });
    }


    /**
     * 返回响应的结果是json对象
     *
     * @param jsonValue
     * @param callBack
     */
    private void onSuccessJsonObjectMethod(final String jsonValue, final Func4 callBack) {
        handler.post(new Runnable() {
            @Override
            public void run() {
                if (callBack != null) {
                    try {
                        ActivityManager.getInstance().addActivity(mActivity);
                        MessageBean messageBean = gson.fromJson(String.valueOf(new JSONObject(jsonValue)), MessageBean.class);
                        if ("time out".equals(messageBean.getMessage())) {
                            //  callBack.onLoginInvalided();
                            ACache aCache = ACache.get(mActivity);
                            aCache.clear();
                            ActivityManager.getInstance().finishActivitys();


                            Intent intent = new Intent(mActivity, LoginActivity.class);
                            mActivity.startActivity(intent);

                        } else

                            callBack.onResponse(new JSONObject(jsonValue));


                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
    }


    public interface Func1 {
        void onResponse(String result);
    }

    public interface Func2 {
        void onResponse(byte[] result);
    }

    public interface Func3 {
        void onResponse(InputStream inputStream);
    }

    public interface Func4 {
        void onResponse(JSONObject jsonObject);
        //   void onLoginInvalided();
        //  void OnError(String error);
    }
}
