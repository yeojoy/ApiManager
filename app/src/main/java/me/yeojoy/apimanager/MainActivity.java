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
import me.yeojoy.apimanager.network.model.Repository;
import me.yeojoy.apimanager.network.model.request.UserRegisterRequest;
import me.yeojoy.apimanager.network.model.response.AuthResponse;
import me.yeojoy.apimanager.network.model.response.BaseResponse;
import me.yeojoy.apimanager.network.model.response.GithubRepositoryResponse;

public class MainActivity extends AppCompatActivity implements ApiManager.RxNetworkBinder {
    private static final String TAG = MainActivity.class.getSimpleName();

    private CompositeDisposable mCompositeDisposable;

    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_with_constraint_layout);
        mCompositeDisposable = new CompositeDisposable();

        String name = "yeojoy";
        String password = null;
        try {
            password = computeHash("duwhd112").toUpperCase();
            Log.d(TAG, "------------------------------------------------------------");
            Log.d(TAG, "password >>>> " + password);
            Log.d(TAG, "------------------------------------------------------------");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        UserRegisterRequest request = new UserRegisterRequest(name, password);

//        textViewResult = (TextView) findViewById(R.id.text_view);
//
//        findViewById(R.id.button_request).setOnClickListener(this::onClickRequestButton);
//        findViewById(R.id.button_flowable).setOnClickListener(this::onClickFlowableButton);
//        findViewById(R.id.button_observable).setOnClickListener(this::onClickObservableButton);
//    }
//
//    private void onClickObservableButton(View view) {
//        Observable foo = Observable.range(0, 1_000_000_000);
//        foo.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(x -> {
////                    Thread.sleep(1000);
//                    System.out.println("[very busy receiver] i'm busy. very busy.");
//                    System.out.println(String.format(Locale.getDefault(), "receiving id: %1$s %2$50.50s", Thread.currentThread().getName(), x));
//                    textViewResult.append(String.format(Locale.getDefault(), "receiving id: %1$s %2$50.50s", Thread.currentThread().getName(), x));
//                    textViewResult.append("\n");
//                }, throwable -> {
//                    Log.e(TAG, throwable.toString());
//                    textViewResult.append(throwable.toString());
//                    textViewResult.append("\n");
//                }, () -> {
//                    Log.d(TAG, "Observable complete.");
//                    textViewResult.append("Observable complete.");
//                    textViewResult.append("\n");
//                });
//    }
//
//    private void onClickFlowableButton(View view) {
//        Flowable foo = Flowable.range(0, 1_000_000_000);
//
//        foo.subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(x -> {
////                    Thread.sleep(1000);
//                    System.out.println("[very busy receiver] i'm busy. very busy.");
//                    System.out.println(String.format(Locale.getDefault(), "receiving id: %1$s %2$50.50s", Thread.currentThread().getName(), x));
//                    textViewResult.append(String.format("receiving id: %s %50.50s", Thread.currentThread().getName(), x));
//                    textViewResult.append("\n");
//                }, throwable -> {
//                    Log.e(TAG, throwable.toString());
//                    textViewResult.append(throwable.toString());
//                    textViewResult.append("\n");
//                }, () -> {
//                    Log.d(TAG, "Flowable complete.");
//                    textViewResult.append("Flowable complete.");
//                    textViewResult.append("\n");
//                });
//    }
//
//    private void onClickRequestButton(View view) {
        new ApiManager.Builder(this)
                .setRequest(RetrofitFactory.createDefaultRetrofit().create(UserApi.class)
                        .login(request))
                .setOnResponse(response -> {
                    if (response instanceof AuthResponse) {
                        AuthResponse authResponse = AuthResponse.class.cast(response);
                        if (!TextUtils.isEmpty(authResponse.accessToken)) {
                            ApiApplication.accessToken = authResponse.accessToken;

                            requestAllStores();
                        }
                    }
                })
                .setOnError(this::onError)
                .execute();
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
        TextView textView = findViewById(R.id.text_view);
        if (response != null) {
            textView.setText("message : " + response.message);
            textView.append("\n-----------------------------------------------------------\n");
//            GithubRepositoryResponse githubResponse = GithubRepositoryResponse.class.cast(response);
//
//            for (Repository r : githubResponse.repositories) {
//                textView.append("\n");
//                textView.append(r.name + " " + r.defaultBranch + " : " + r.url);
//                textView.append("\n");
//            }
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

    public String computeHash(String input) throws NoSuchAlgorithmException, UnsupportedEncodingException {
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        digest.reset();

        byte[] byteData = digest.digest(input.getBytes("UTF-8"));
        StringBuffer sb = new StringBuffer();

        for (int i = 0; i < byteData.length; i++){
            sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
