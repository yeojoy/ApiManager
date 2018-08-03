package me.yeojoy.apimanager;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.TextView;

import io.reactivex.disposables.CompositeDisposable;
import me.yeojoy.apimanager.network.ApiManager;
import me.yeojoy.apimanager.network.RetrofitFactory;
import me.yeojoy.apimanager.network.api.StoreApi;
import me.yeojoy.apimanager.network.api.UserApi;
import me.yeojoy.apimanager.network.model.Store;
import me.yeojoy.apimanager.network.model.response.AllStoresResponse;
import me.yeojoy.apimanager.network.model.response.AuthResponse;
import me.yeojoy.apimanager.network.model.response.BaseResponse;

public class MainActivity extends AppCompatActivity implements ApiManager.RxNetworkBinder {
    private static final String TAG = MainActivity.class.getSimpleName();

    private CompositeDisposable mCompositeDisposable;

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_constraint_layout);
        mCompositeDisposable = new CompositeDisposable();

        textViewResult = findViewById(R.id.text_view);

        findViewById(R.id.button_create_user).setOnClickListener(view
                -> startActivity(new Intent(this, RegisterActivity.class)));

        findViewById(R.id.button_login).setOnClickListener(view
                -> startActivity(new Intent(this, LoginActivity.class)));

        findViewById(R.id.button_request).setOnClickListener(view -> requestAllStores());
    }

    private void requestAllStores() {
        Log.i(TAG, "requestAllStores()");
        new ApiManager.Builder(this)
                .setRequest(RetrofitFactory.createDefaultRetrofit().create(StoreApi.class)
                        .getAllStores("Bearer " + ApiApplication.accessToken))
                .setOnResponse(this::onGetResponse)
                .setOnError(this::onError)
                .execute();
    }

    private void onError(int statusCode, BaseResponse response, Throwable throwable) {
        switch (statusCode) {
            case 401: {
                if (response != null && !TextUtils.isEmpty(response.message)) {
                    textViewResult.append("\n");
                    textViewResult.append(response.message);
                }
            }
        }
        textViewResult.append(throwable.getMessage());
    }

    private void onGetResponse(BaseResponse response) {
        Log.d(TAG, "response : " + response);
        if (response != null && response instanceof AllStoresResponse) {
            AllStoresResponse allStoresResponse = AllStoresResponse.class.cast(response);
            for (Store store : allStoresResponse.stores) {
                textViewResult.append("\n-----------------------------------------------------------\n");
                textViewResult.setText(store.toString());
            }

        }
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
