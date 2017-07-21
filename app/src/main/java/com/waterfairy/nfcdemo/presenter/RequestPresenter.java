package com.waterfairy.nfcdemo.presenter;

import android.os.Message;

import com.waterfairy.nfcdemo.bean.ClassInfoBean;
import com.waterfairy.nfcdemo.bean.DisciplineCodeBean;
import com.waterfairy.nfcdemo.bean.StudentBean;
import com.xueduoduo.application.MyApp;
import com.xueduoduo.http.BaseCallback;
import com.xueduoduo.http.RetrofitRequest;
import com.xueduoduo.http.RetrofitService;
import com.xueduoduo.http.response.BaseResponse;

import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by water_fairy on 2017/7/21.
 * 995637517@qq.com
 */

public class RequestPresenter {
    public void requestClassInfo(final OnRequestListener onRequestListener) {
        RetrofitService normalRetrofit = RetrofitRequest.getInstance().getNormalRetrofit();
        normalRetrofit.queryTeacherClass(MyApp.getInstance().getUserInfo().getUserIdStr()).enqueue(new BaseCallback<BaseResponse<ClassInfoBean>>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                ArrayList classList = baseResponse.getList();
                if (classList == null || classList.size() == 0) {

                } else {
                    MyApp.getInstance().setClassInfo(classList);
                }
                if (onRequestListener != null) onRequestListener.onSuccess(classList);
            }

            @Override
            public void onFailed(int code, String message) {
                if (onRequestListener != null) onRequestListener.onError(message);
            }
        });
    }

    public void requestClassStudent(long classId, final OnRequestListener onRequestListener) {
        RetrofitService normalRetrofit = RetrofitRequest.getInstance().getNormalRetrofit();
        normalRetrofit.queryStudentList(MyApp.getInstance().getUserInfo().getUserIdStr(), classId + "").enqueue(new BaseCallback<BaseResponse<StudentBean>>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                ArrayList classList = baseResponse.getList();
                if (onRequestListener != null) onRequestListener.onSuccess(classList);
            }

            @Override
            public void onFailed(int code, String message) {
                if (onRequestListener != null) onRequestListener.onError(message);
            }
        });
    }

    public void requestTeacherDiscipline(long classId, final OnRequestListener onRequestListener) {
        RetrofitService normalRetrofit = RetrofitRequest.getInstance().getNormalRetrofit();
        normalRetrofit.queryTeacherDiscipline(MyApp.getInstance().getUserInfo().getUserIdStr(), classId + "").enqueue(new BaseCallback<BaseResponse<DisciplineCodeBean>>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                ArrayList classList = baseResponse.getList();
                if (onRequestListener != null) onRequestListener.onSuccess(classList);
            }

            @Override
            public void onFailed(int code, String message) {
                if (onRequestListener != null) onRequestListener.onError(message);
            }
        });
    }

    public void requestStudentInfo(String studentId, final OnRequestListener onRequestListener) {
        RetrofitService normalRetrofit = RetrofitRequest.getInstance().getNormalRetrofit();
        normalRetrofit.queryStudentInfo(studentId + "").enqueue(new BaseCallback<BaseResponse<StudentBean>>() {
            @Override
            public void onSuccess(BaseResponse baseResponse) {
                if (onRequestListener != null)
                    onRequestListener.onSuccess(baseResponse.getRecord());

            }

            @Override
            public void onFailed(int code, String message) {
                if (onRequestListener != null) onRequestListener.onError(message);
            }
        });
    }

    public interface OnRequestListener {
        void onSuccess(Object object);

        void onError(String message);
    }
}
