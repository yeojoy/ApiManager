package me.yeojoy.apimanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import io.reactivex.disposables.CompositeDisposable;
import me.yeojoy.apimanager.network.ApiManager;
import me.yeojoy.apimanager.network.RetrofitFactory;
import me.yeojoy.apimanager.network.api.UserApi;
import me.yeojoy.apimanager.network.model.request.UserRegisterRequest;
import me.yeojoy.apimanager.network.model.response.AuthResponse;
import me.yeojoy.apimanager.network.model.response.BaseResponse;
import me.yeojoy.apimanager.utils.UserUtil;

public class LoginActivity extends AppCompatActivity implements ApiManager.RxNetworkBinder {
    private static final String TAG = LoginActivity.class.getSimpleName();

    private CompositeDisposable mCompositeDisposable;
    private EditText editTextUsername, editTextPassword;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mCompositeDisposable = new CompositeDisposable();

        editTextUsername = findViewById(R.id.edit_text_username);
        editTextPassword = findViewById(R.id.edit_text_password);
        textViewResult = findViewById(R.id.text_view);

        findViewById(R.id.button_request_login).setOnClickListener(view -> requestLogin());
    }

    private void requestLogin() {

        String username = editTextUsername.getText().toString();
        if (TextUtils.isEmpty(username)) {
            logText("No Username!!!");

            return;
        }

        String password = editTextPassword.getText().toString();
        if (TextUtils.isEmpty(username)) {
            logText("No password!!!");
            return;
        }

        try {
            password = UserUtil.computeHash(password);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            logText(e.getMessage());
            return;
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            logText(e.getMessage());
            return;
        }

        UserRegisterRequest request = new UserRegisterRequest(username, password);

        new ApiManager.Builder(this)
                .setRequest(RetrofitFactory.createDefaultRetrofit().create(UserApi.class).login(request))
                .setOnResponse(this::onGetResponse)
                .setOnError(this::onError)
                .execute();
    }

    private void onError(int statusCode, BaseResponse response, Throwable throwable) {
        if (throwable != null) {

            if (response != null && !TextUtils.isEmpty(response.message)) {
                textViewResult.append("\n");
                textViewResult.append(String.valueOf(statusCode));
                textViewResult.append(" >>> ");
                textViewResult.append(response.message);
            }
        }
        Log.e(TAG, "error message : " + throwable.getMessage());
    }

    private void onGetResponse(BaseResponse response) {
        Log.d(TAG, "response : " + response);
        AuthResponse authResponse = AuthResponse.class.cast(response);
        if (!TextUtils.isEmpty(authResponse.accessToken) && !TextUtils.isEmpty(authResponse.refreshToken)) {
            logText("login success!!");
            ApiApplication.accessToken = authResponse.accessToken;
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

    private void logText(String message) {
        textViewResult.setText(message);
    }

}
