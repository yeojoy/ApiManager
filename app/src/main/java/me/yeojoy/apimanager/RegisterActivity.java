package me.yeojoy.apimanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import io.reactivex.disposables.CompositeDisposable;
import me.yeojoy.apimanager.app.ApiApplication;
import me.yeojoy.apimanager.app.BaseActivity;
import me.yeojoy.apimanager.network.ApiManager;
import me.yeojoy.apimanager.network.RetrofitFactory;
import me.yeojoy.apimanager.network.api.StoreApi;
import me.yeojoy.apimanager.network.api.UserApi;
import me.yeojoy.apimanager.network.model.request.UserRegisterRequest;
import me.yeojoy.apimanager.network.model.response.AuthResponse;
import me.yeojoy.apimanager.network.model.response.BaseResponse;
import me.yeojoy.apimanager.utils.SoftKeyboardUtil;
import me.yeojoy.apimanager.utils.UserUtil;

public class RegisterActivity extends BaseActivity implements ApiManager.RxNetworkBinder {
    private static final String TAG = RegisterActivity.class.getSimpleName();

    private CompositeDisposable mCompositeDisposable;

    private EditText editTextUsername, editTextPassword, editTextConfirmPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mCompositeDisposable = new CompositeDisposable();

        editTextUsername = findViewById(R.id.edit_text_username);
        editTextPassword = findViewById(R.id.edit_text_password);
        editTextConfirmPassword = findViewById(R.id.edit_text_confirm_password);

        textViewResult = findViewById(R.id.text_view);

        findViewById(R.id.button_request_create_user).setOnClickListener(view -> {
            requestCreateUser();
            SoftKeyboardUtil.hideKeyboard(this);
        });
    }

    private void requestCreateUser() {
        String username = editTextUsername.getText().toString();
        if (TextUtils.isEmpty(username)) {
            logResult("No Username!!!");

            return;
        }

        String password = editTextPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            logResult("No password!!!");
            return;
        }

        String confirmPassword = editTextConfirmPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            logResult("No Confirm password!!!");
            return;
        }

        if (!password.equals(confirmPassword)) {
            logResult("Password Not matched!!!");
            return;
        }

        try {
            password = UserUtil.computeHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logResult(e.getMessage());
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logResult(e.getMessage());
            return;
        }

        UserRegisterRequest request = new UserRegisterRequest(username, password);

        new ApiManager.Builder(this)
                .setRequest(RetrofitFactory.createDefaultRetrofit().create(UserApi.class).register(request))
                .setOnResponse(this::onGetResponse)
                .setOnError(this::onError)
                .execute();
    }

    private void onError(int statusCode, BaseResponse response, Throwable throwable) {
        if (throwable != null) {
            if (response != null && !TextUtils.isEmpty(response.message)) {
                StringBuilder stringBuilder = new StringBuilder();
                stringBuilder.append("\n").append(statusCode);
                stringBuilder.append(" >>> ").append(response.message);
                logResult(stringBuilder.toString());
            }
        }
        Log.e(TAG, "error message : " + throwable.getMessage());
    }

    private void onGetResponse(BaseResponse response) {
        Log.d(TAG, "response : " + response);
        if (response != null && !TextUtils.isEmpty(response.message)) {
            logResult(response.message);
        }

        requestLogin();
    }

    private void requestLogin() {
        String username = editTextUsername.getText().toString();
        String password = editTextPassword.getText().toString();
        try {
            password = UserUtil.computeHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logResult(e.getMessage());
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logResult(e.getMessage());
            return;
        }

        UserRegisterRequest request = new UserRegisterRequest(username, password);

        new ApiManager.Builder(this)
                .setRequest(RetrofitFactory.createDefaultRetrofit().create(UserApi.class).login(request))
                .setOnResponse(apiResponse -> {
                    if (apiResponse instanceof AuthResponse) {
                        AuthResponse response = AuthResponse.class.cast(apiResponse);
                        ApiApplication.accessToken = response.accessToken;

                        logResult("login Success!");
                    }

                    textViewResult.postDelayed(() -> finish(), 500);
                })
                .setOnError(this::onError)
                .execute();

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
