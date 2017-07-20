package com.xueduoduo.http;


import com.xueduoduo.http.request.PopParamsUtils;

/**
 * Created by water_fairy on 2017/5/18.
 * 995637517@qq.com
 */

public class RetrofitRequest {
    private static RetrofitRequest retrofitRequest;
    private RetrofitService userRetrofit, normalRetrofit;

    private RetrofitRequest() {

    }

    public static RetrofitRequest getInstance() {
        if (retrofitRequest == null) retrofitRequest = new RetrofitRequest();
        return retrofitRequest;
    }

    /**
     * 和用户信息有关
     *
     * @return
     */
    public RetrofitService getUserRetrofit() {
        if (userRetrofit == null) {
            RetrofitHttpClient build = RetrofitHttpClient.build(RetrofitConfig.UserInfoModifyUrl);
            build.setPopParams(PopParamsUtils.getPopParams());
            userRetrofit = build.getRetrofit()
                    .create(RetrofitService.class);
        }
        return userRetrofit;
    }

    /**
     * 常规数据请求
     *
     * @return
     */
    public RetrofitService getNormalRetrofit() {
        if (normalRetrofit == null) {
            RetrofitHttpClient build = RetrofitHttpClient.build(RetrofitConfig.BaseUrl);
            build.setPopParams(PopParamsUtils.getPopParams());
            normalRetrofit = build.getRetrofit()
                    .create(RetrofitService.class);
        }
        return normalRetrofit;
    }

    /**
     * 常规数据请求
     *
     * @return
     */
    public RetrofitService getNormalRetrofit(boolean hasGson) {
        RetrofitHttpClient build = RetrofitHttpClient.build(RetrofitConfig.BaseUrl, hasGson);
        build.setPopParams(PopParamsUtils.getPopParams());
        return build.getRetrofit()
                .create(RetrofitService.class);

    }
}
