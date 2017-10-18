package me.yeojoy.apimanager.network;

import android.util.Log;

import io.reactivex.functions.Consumer;
import me.yeojoy.apimanager.network.model.response.BaseResponse;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public class RxResponse implements Consumer<BaseResponse> {
    private static final String TAG = RxResponse.class.getSimpleName();

    private ApiManager.RxNetworkBinder mBinder;
    private OnResponse mOnResponse;

    public RxResponse(ApiManager.RxNetworkBinder binder, OnResponse onResponse) {
        mBinder = binder;
        mOnResponse = onResponse;
    }

    @Override
    public void accept(BaseResponse response) throws Exception {
        Log.i(TAG, "call accept() method.");

        if (mOnResponse != null) {
            mOnResponse.OnResponse(response);
        }
    }

    public interface OnResponse {
        void OnResponse(BaseResponse apiResponse);
    }
}
