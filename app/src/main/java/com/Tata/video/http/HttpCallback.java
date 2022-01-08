package com.Tata.video.http;

import android.app.Dialog;
import android.content.Intent;
import android.util.Log;

import com.alibaba.fastjson.JSON;
import com.lzy.okgo.callback.AbsCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;
import com.Tata.video.AppContext;
import com.Tata.video.activity.LoginActivity;
import com.Tata.video.utils.L;
import com.Tata.video.utils.ToastUtil;

import java.net.ConnectException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.net.UnknownServiceException;

/**
 * Created by cxf on 2017/8/7.
 */

public abstract class HttpCallback extends AbsCallback<JsonBean> {

    private Dialog mLoadingDialog;

    @Override
    public JsonBean convertResponse(okhttp3.Response response) throws Throwable {
        //Log.e("#服务器返onSuccess1","2== " + response.body().string());
        JsonBean bean = JSON.parseObject(response.body().string(), JsonBean.class);
        response.close();
        return bean;
    }

    @Override
    public void onSuccess(Response<JsonBean> response) {
        Log.e("#服务器返onSuccess1","response" + response.toString());
        JsonBean bean = response.body();
        Log.e("#服务器返onSuccess1","1111" + bean);
        if (bean != null && 200 == bean.getRet()) {
            Data data = bean.getData();
            if (data != null) {
                if (700 == data.getCode()) {
                    //token过期，重新登录
                    Intent intent = new Intent(AppContext.sInstance, LoginActivity.class);
                    intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    AppContext.sInstance.startActivity(intent);
                    ToastUtil.show(data.getMsg());
                } else {
                    Log.e("#服务器返onSuccess","1111");
                    onSuccess(data.getCode(), data.getMsg(), data.getInfo());
                }
            } else {
                Log.e("#服务器返回值ret: ", bean.getRet() + " msg: " + bean.getMsg());
            }

        } else {
            Log.e("#服务器返sponse异常","1111");
        }
    }

    @Override
    public void onError(Response<JsonBean> response) {
        Throwable t = response.getException();
        Log.e("#网络请求错误---->", t.getClass() + " : " + t.getMessage());
        //Log.e("#网络请求错误---->" + t.getClass() + " : " + t.getMessage());
       // System.out.print("t.getClass()");
        //System.out.print(t.getClass());
        //System.out.print(t.getMessage());
        if (t instanceof ConnectException || t instanceof UnknownHostException || t instanceof UnknownServiceException || t instanceof SocketException) {
            ToastUtil.show("Network Error");
        }
        if (showLoadingDialog() && mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
        onError();
    }

    public void onError() {

    }


    public abstract void onSuccess(int code, String msg, String[] info);

    @Override
    public void onStart(Request<JsonBean, ? extends Request> request) {
        onStart();
    }

    public void onStart() {
        if (showLoadingDialog()) {
            if (mLoadingDialog == null) {
                mLoadingDialog = createLoadingDialog();
            }
            if (mLoadingDialog != null) {
                mLoadingDialog.show();
            }
        }
    }

    @Override
    public void onFinish() {
        if (showLoadingDialog() && mLoadingDialog != null) {
            mLoadingDialog.dismiss();
        }
    }

    public Dialog createLoadingDialog() {
        return null;
    }

    public boolean showLoadingDialog() {
        return false;
    }

}
