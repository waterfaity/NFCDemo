package com.xueduoduo.http;


import com.xueduoduo.http.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

/**
 * Created by water_fairy on 2017/5/18.
 * 995637517@qq.com
 */

public interface RetrofitService {
    @FormUrlEncoded
    @POST("login")
    Call<BaseResponse> login(@Field("account") String account,
                             @Field("password") String password);

}