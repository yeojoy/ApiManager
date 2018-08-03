package me.yeojoy.apimanager.network;

import com.google.gson.Gson;
import com.google.gson.TypeAdapter;

import java.io.IOException;

import io.reactivex.functions.Consumer;
import me.yeojoy.apimanager.network.model.response.BaseResponse;
import okhttp3.ResponseBody;
import retrofit2.HttpException;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public class RxError implements Consumer<Throwable> {

    private ApiManager.RxNetworkBinder mBinder;
    private OnError mOnError;

    public RxError(ApiManager.RxNetworkBinder binder, OnError onError) {
        mBinder = binder;
        mOnError = onError;
    }

    @Override
    public void accept(Throwable throwable) {
        if (mOnError != null) {
            mOnError.OnError(throwable);
        }
    }

    public interface OnError {

        void OnError(Throwable throwable);
    }
}
