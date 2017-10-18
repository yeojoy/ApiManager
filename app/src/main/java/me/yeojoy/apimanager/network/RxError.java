package me.yeojoy.apimanager.network;

import io.reactivex.functions.Consumer;

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
