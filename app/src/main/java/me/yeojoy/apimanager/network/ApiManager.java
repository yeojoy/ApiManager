package me.yeojoy.apimanager.network;

import android.content.Context;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Action;
import io.reactivex.schedulers.Schedulers;
import me.yeojoy.apimanager.network.model.response.BaseResponse;

/**
 * Created by yeojoy on 2017. 10. 16..
 */

public class ApiManager {
    private static final String TAG = ApiManager.class.getSimpleName();

    private RxNetworkBinder mBinder;

    private Observable<? extends BaseResponse> mRequest;
    private RxError.OnError mOnError;
    private RxResponse.OnResponse mOnResponse;

    public ApiManager(RxNetworkBinder binder) {
        mBinder = binder;
    }

    public void execute() {
        Disposable disposable = mRequest.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new RxResponse(mBinder, mOnResponse),
                        new RxError(mBinder, mOnError), new Action() {
            @Override
            public void run() throws Exception {

            }
        });
        mBinder.getCompositeDisposible().add(disposable);
    }

    public static class Builder {
        private ApiManager mApiManager;

        public Builder(RxNetworkBinder binder) {
            mApiManager = new ApiManager(binder);
        }

        public Builder setRequest(Observable<? extends BaseResponse> request) {
            mApiManager.setRequest(request);
            return this;
        }

        public Builder setOnResponse(RxResponse.OnResponse onResponse) {
            mApiManager.setOnResponse(onResponse);
            return this;
        }

        public Builder setOnError(RxError.OnError onError) {
            mApiManager.setOnError(onError);
            return this;
        }

        public void execute() {
            mApiManager.execute();
        }
    }

    private void setOnResponse(RxResponse.OnResponse onResponse) {
        mOnResponse = onResponse;
    }

    private void setOnError(RxError.OnError onError) {
        mOnError = onError;
    }

    private void setRequest(Observable<? extends BaseResponse> request) {
        mRequest = request;
    }

    public interface RxNetworkBinder {
        CompositeDisposable getCompositeDisposible();
        Context getContext();
    }
}
