package com.fesco.bookpay.impl;

import com.fesco.bookpay.entity.ConsumptionBean;
import com.fesco.bookpay.entity.MessageBean;
import com.fesco.bookpay.entity.approvalbean.CheckListBean;
import com.fesco.bookpay.entity.rbmbean.UpImagePicsBean;

import java.util.Map;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.PartMap;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

/**
 * Created by shiyan on 2016/10/9.
 */
public interface RetrofitService {

    @Multipart
    @POST("upload")
    Call<ResponseBody> upload(@Part("description") RequestBody description,
                              @Part MultipartBody.Part file);


    /**
     * http://api.k780.com:88/?app=idcard.get&&appkey=10003&&sign=b59bc3ef6191eb9f747dd4e83c99f2a4&&format=json&&idcard=110101199001011114
     */

    @GET("/")
    Call<CheckListBean> getOnePersonInfo(@Query("emp_Id") String app);
  //  HttpUtil.signLaterExamList
   // "http://www.payrollpen.com/payroll/kq/signLaterExamList.json"; // 补签审批列表
    @GET("/signLaterExamList.json/")
    Call<CheckListBean> getTwoPersonInfo(@QueryMap Map<String, String> map);

    @FormUrlEncoded
    @POST("/")
    Call<CheckListBean> postOnePersonInfo(@Field("app") String app, @Field("appkey") String appkey, @Field("sign") String sign, @Field("format") String format, @Field("idcard") String idcard);

    @FormUrlEncoded
    @POST("kq/signLaterExamList.json")
    Call<CheckListBean> postTwoPersonInfo(@FieldMap Map<String, String> map);

    @FormUrlEncoded
    @POST("expense/loadAddApply.json")
    Call<ConsumptionBean> postConsumptionBean(@FieldMap Map<String, String> map);

  @FormUrlEncoded
  @POST("expense/submitExpenseApply.json")
  Call<MessageBean> postSubmitExpenseApply(@FieldMap Map<String, String> map);



  @Multipart
  @FormUrlEncoded
  @POST("payroll/expense/loadAddApply.json")
  Call<ConsumptionBean> postConsumptionBean(@FieldMap Map<String, String> map, @Part("avatar\"; filename=\"avatar.jpg") RequestBody avatar);

  @Multipart
  @POST("v1/public/core/?service=user.updateAvatar")
  Call<MessageBean>  uploadMultipleTypeFile(@Part("data") String des, @PartMap Map<String,RequestBody> params);

  /**
   * 上传一张图片
   * @param
   * @param imgs
   * @return
   */
  @Multipart
  @POST("expense/uploadPic.json") //expense/uploadPic.json?request                  //"file\"; filename=\"image.png\""
  Call<MessageBean> uploadImage(
                           @Part("file\"; filename=\"image.png")RequestBody imgs);

  @Multipart
  @POST("expense/uploadPic.json")
  Call<UpImagePicsBean> uploadImages(@PartMap Map<String,RequestBody> params);

  //"http://11.0.161.15/payroll/emp/uploadPic.json";
  @Multipart
  @POST("emp/uploadPic.json")
  Call<MessageBean> uploadImagesHead(@PartMap Map<String,RequestBody> params);

  @Multipart
  @POST("you methd url upload/")
  Call<ResponseBody> uploadFile(
          @Part("avatar\"; filename=\"avatar.jpg") RequestBody file);
  @FormUrlEncoded
    @POST("/payroll/kq/signLaterExamList.json")
    Call<CheckListBean> postTwoPersonInfo2(@FieldMap Map<String, String> map);

    @POST("/")
    Call<CheckListBean> postThreePersonInfo(@Body CheckListBean requestInfo);

    /**
     * 示例: http://192.168.0.247/kuke/kukie/user/teacherLogin?userName=我是老师&password=123456
     */
    @GET("/kuke/kukie/user/teacherLogin")
    Call<String> getCookieInfo(@QueryMap Map<String, String> map);

    /**
     * 示例: "http://192.168.0.247/kuke/kukie/textbook/list?pageNo=1&pageSize=5&textbookName=小汤普森&tbType=1&diffLevel=1"
     */
    @GET("/kuke/kukie/textbook/list")
    Call<String> list(@QueryMap Map<String, String> map);

    /**
     * 示例: "http://192.168.0.247/kuke/kukie/clazz/list4manage"
     */
    @GET("/kuke/kukie/clazz/list4manage")
    Call<String> listClass();

    /**
     * RxJava
     */

//    @GET("/")
//    Observable<PersonInfo> rxjavaGetOne(@Query("app") String app, @Query("appkey") String appkey, @Query("sign") String sign, @Query("format") String format, @Query("idcard") String idcard);
//
//    @GET("/")
//    Observable<PersonInfo2> rxjavaGetTwo(@Query("app") String app, @Query("appkey") String appkey, @Query("sign") String sign, @Query("format") String format, @Query("idcard") String idcard);
//


}
