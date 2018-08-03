package me.yeojoy.apimanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.disposables.CompositeDisposable;
import me.yeojoy.apimanager.network.ApiManager;
import me.yeojoy.apimanager.network.RetrofitFactory;
import me.yeojoy.apimanager.network.api.StoreApi;
import me.yeojoy.apimanager.network.api.UserApi;
import me.yeojoy.apimanager.network.model.request.UserRegisterRequest;
import me.yeojoy.apimanager.network.model.response.AuthResponse;
import me.yeojoy.apimanager.network.model.response.BaseResponse;

public class RegisterActivity extends AppCompatActivity implements ApiManager.RxNetworkBinder {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private CompositeDisposable mCompositeDisposable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_constraint_layout);
        mCompositeDisposable = new CompositeDisposable();

    }

    private void onError(BaseResponse response, Throwable throwable) {
        if (throwable != null) {
            TextView textView = findViewById(R.id.text_view);
            textView.setText("서버에 연결할 수가 없습니다.\n\n");
            textView.append(throwable.getMessage());

            if (response != null && !TextUtils.isEmpty(response.message)) {
                textView.append("\n");
                textView.append(response.message);
            }
        }
        Log.e(TAG, "error message : " + throwable.getMessage());
    }

    private void onGetResponse(BaseResponse response) {
        Log.d(TAG, "response : " + response);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        mCompositeDisposable.clear();
    }

    @Override
    public CompositeDisposable getCompositeDisposible() {
        return mCompositeDisposable;
    }

    @Override
    public Context getContext() {
        return this;
    }

}
