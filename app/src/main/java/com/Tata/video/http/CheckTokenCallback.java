package com.Tata.video.http;

import android.util.Log;

import com.lzy.okgo.model.Response;
import com.Tata.video.AppConfig;
import com.Tata.video.utils.L;

/**
 * Created by cxf on 2018/7/12.
 */

public abstract class CheckTokenCallback extends HttpCallback {


    @Override
    public void onSuccess(Response<JsonBean> response) {
        JsonBean bean = response.body();
        if (bean != null && 200 == bean.getRet()) {
            Data data = bean.getData();
            if (data != null) {
                if (700 == data.getCode()) {
                    AppConfig.getInstance().logout();
                }
                onSuccess(data.getCode(), data.getMsg(), data.getInfo());
            } else {
                Log.e("#服务器返回值异常--->ret: ", bean.getRet() + " msg: " + bean.getMsg());
            }

        } else {
            Log.e("#服务器返回值异常--->ret: ", bean.getRet() + " msg: " + bean.getMsg());
        }
    }

}
