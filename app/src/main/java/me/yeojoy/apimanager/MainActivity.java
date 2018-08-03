package me.yeojoy.apimanager;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.net.ConnectException;
import java.util.Locale;

import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.schedulers.Schedulers;
import me.yeojoy.apimanager.network.ApiManager;
import me.yeojoy.apimanager.network.RetrofitFactory;
import me.yeojoy.apimanager.network.api.GithubApi;
import me.yeojoy.apimanager.network.model.Repository;
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

        textViewResult = findViewById(R.id.text_view);

        findViewById(R.id.button_request).setOnClickListener(this::onClickRequestButton);
        findViewById(R.id.button_flowable).setOnClickListener(this::onClickFlowableButton);
        findViewById(R.id.button_observable).setOnClickListener(this::onClickObservableButton);
    }

    private void onClickObservableButton(View view) {
        Observable foo = Observable.range(0, 1_000_000_000);
        foo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(x -> {
//                    Thread.sleep(1000);
                    System.out.println("[very busy receiver] i'm busy. very busy.");
                    System.out.println(String.format(Locale.getDefault(), "receiving id: %1$s %2$50.50s", Thread.currentThread().getName(), x));
                    textViewResult.append(String.format(Locale.getDefault(), "receiving id: %1$s %2$50.50s", Thread.currentThread().getName(), x));
                    textViewResult.append("\n");
                }, throwable -> {
                    Log.e(TAG, throwable.toString());
                    textViewResult.append(throwable.toString());
                    textViewResult.append("\n");
                }, () -> {
                    Log.d(TAG, "Observable complete.");
                    textViewResult.append("Observable complete.");
                    textViewResult.append("\n");
                });
    }

    private void onClickFlowableButton(View view) {
        Flowable foo = Flowable.range(0, 1_000_000_000);

        foo.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(x -> {
//                    Thread.sleep(1000);
                    System.out.println("[very busy receiver] i'm busy. very busy.");
                    System.out.println(String.format(Locale.getDefault(), "receiving id: %1$s %2$50.50s", Thread.currentThread().getName(), x));
                    textViewResult.append(String.format("receiving id: %s %50.50s", Thread.currentThread().getName(), x));
                    textViewResult.append("\n");
                }, throwable -> {
                    Log.e(TAG, throwable.toString());
                    textViewResult.append(throwable.toString());
                    textViewResult.append("\n");
                }, () -> {
                    Log.d(TAG, "Flowable complete.");
                    textViewResult.append("Flowable complete.");
                    textViewResult.append("\n");
                });
    }

    private void onClickRequestButton(View view) {
        new ApiManager.Builder(this)
                .setRequest(RetrofitFactory.createDefaultRetrofit().create(GithubApi.class).repositories("yeojoy"))
                .setOnResponse(this::onGetResponse)
                .setOnError(this::onError)
                .execute();
    }

    private void onError(Throwable throwable) {
        if (throwable != null) {
            TextView textView = (TextView) findViewById(R.id.text_view);
            if (throwable instanceof ConnectException) {
                textView.setText("서버에 연결할 수가 없습니다.\n\n");
                textView.append(throwable.getMessage());
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
            GithubRepositoryResponse githubResponse = GithubRepositoryResponse.class.cast(response);

            for (Repository r : githubResponse.repositories) {
                textView.append("\n");
                textView.append(r.name + " " + r.defaultBranch + " : " + r.url);
                textView.append("\n");
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
