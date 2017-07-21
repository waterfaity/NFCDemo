package com.xueduoduo.http;


import com.waterfairy.nfcdemo.bean.ClassInfoBean;
import com.waterfairy.nfcdemo.bean.DisciplineCodeBean;
import com.waterfairy.nfcdemo.bean.StudentBean;
import com.waterfairy.nfcdemo.bean.UserBean;
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

    /**
     * @param userId
     * @return classList
     */
    @FormUrlEncoded
    @POST("user/queryClassList")
    Call<BaseResponse<ClassInfoBean>> queryTeacherClass(@Field("userId") String userId);

    /**
     * @param userId
     * @return list
     */
    @FormUrlEncoded
    @POST("user/queryStudentList")
    Call<BaseResponse<StudentBean>> queryStudentList(@Field("userId") String userId,
                                                     @Field("classId") String classId);

    /**
     * @param userId
     * @param classId
     * @return list
     */
    @FormUrlEncoded
    @POST("user/queryTeacherDiscipline")
    Call<BaseResponse<DisciplineCodeBean>> queryTeacherDiscipline(@Field("userId") String userId,
                                                                  @Field("classId") String classId);

    /**
     * @param userId
     * @return record
     */
    @FormUrlEncoded
    @POST("user/queryUserInfo")
    Call<BaseResponse<StudentBean>> queryStudentInfo(@Field("userId") String userId);
//    登录
//    http://180.153.67.147:8093/medicine/login?account=teacher&password=c230415d9f455e93e080d23e7727a1a8
//
//    查班级
//    http://180.153.67.147:8093/medicine/user/queryClassList?userId=2
//
//    查学生
//    http://180.153.67.147:8093/medicine/user/queryStudentList?classId=51
//
//    查学科
//    http://180.153.67.147:8093/medicine/user/queryTeacherDiscipline?userId=2&classId=51

}